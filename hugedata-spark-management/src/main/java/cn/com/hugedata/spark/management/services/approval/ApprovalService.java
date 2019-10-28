package cn.com.hugedata.spark.management.services.approval;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.MessageService;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceAttachment;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceCc;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceProcess;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.entity.UcParkDept;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceCcMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceProcessMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcParkDeptMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

@Service
public class ApprovalService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalService.class);

    @Autowired
    private ApServiceInfoMapper apServiceInfoMapper;
    
    @Autowired
    private MessageService messageService;

    @Autowired
    private ApServiceAttachmentMapper apServiceAttachmentMapper;
    
    @Autowired
    private ApServiceProcessMapper apServiceProcessMapper;
    
    @Autowired
    private ApServiceCcMapper apServiceCcMapper;
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcParkDeptMapper ucParkDeptMapper;
    
    @Autowired
    private FileStoreService fileStoreService;
    
    /**
     * 下一步(deliver)操作.
     * @param login                 当前登录用户
     * @param serviceId             办件ID
     * @param remark                处理意见
     * @param nextUserId            下一步用户ID，仅在"预审"和"初审"时可以指定
     * @param attachmentStatusJson  附件审批信息 JSONArray   [{ attConfigId:**, status:** }, ...]
     * @throws ServiceException     处理失败
     */
    public void deliver(
            UcUserInfo login,
            int serviceId,
            String remark,
            Integer nextUserId,
            Integer nextDeptId,
            String attachmentStatusJson) throws ServiceException {
        ApServiceInfo service = queryServiceInfo(serviceId);
        checkAllowDeliver(login, service);
        UcUserInfo nextUser = checkNextStepUser(service, nextUserId);
        UcParkDept nextDept = checkNextStepDept(service, nextDeptId);
        UcUserInfo createUser = queryUserInfo(Integer.parseInt(service.getCreateUserId()));
        
        // 保存处理信息
        ApServiceProcess proc = new ApServiceProcess();
        proc.setServiceId(serviceId);
        proc.setServiceStatus(service.getStatus());
        proc.setSeq(0);
        proc.setUserId(login.getUserId());
        proc.setUserName(login.getName());
        proc.setDeptCode("" + service.getDeptcode());
        proc.setDeptName(service.getDeptname());
        proc.setStatus(ApServiceProcess.STATUS_DELIVER);
        proc.setRemark(remark);
        proc.setAttachmentsStatus(attachmentStatusJson);
        proc.setCreateTime(new Date());
        apServiceProcessMapper.insertSelective(proc);
        
        // 更新办件信息
        ApServiceInfo serviceUpdate = new ApServiceInfo();
        serviceUpdate.setServiceId(serviceId);
        serviceUpdate.setStatus(statusAfterDeliver(service));
        
        if (nextUser != null) {
            serviceUpdate.setCurrentUserId(nextUser.getUserId());
            serviceUpdate.setCurrentUserName(nextUser.getName());
        } else {
            serviceUpdate.setCurrentUserId(0);
            serviceUpdate.setCurrentUserName("");
        }
        
        if (nextDept != null) {
            serviceUpdate.setCurrentDeptId(nextDept.getDeptId());
            serviceUpdate.setCurrentDeptName(nextDept.getDeptName());
        } else {
            serviceUpdate.setCurrentDeptId(0);
            serviceUpdate.setCurrentDeptName("");
        }
        
        if (service.getStatus().equals(ApServiceInfo.STATUS_CREATED)) {
            serviceUpdate.setAcceptUserId("" + login.getUserId());
            serviceUpdate.setAcceptUserName(login.getName());
            serviceUpdate.setAcceptCellphone(login.getMobile());
            serviceUpdate.setAcceptTime(new Date());
            
            if (UcUserInfo.USER_TYPE_COMPANY.equals(createUser.getUserType())) {
                String message = "办件" + service.getBusinessNo() + "已受理"; 
                messageService.createSystemMessage(UcMessage.TYPE_AP_SERVICE, message, message,
                        Integer.valueOf(service.getCreateUserId()), String.valueOf(service.getServiceId()));
            }
        }
        apServiceInfoMapper.updateSelectiveById(serviceUpdate);
        
        // 生成受理通知书
        createAcceptNotice(apServiceInfoMapper.selectById(serviceId));
        
        // 更新附件状态
        try {
            JSONArray arr = JSON.parseArray(attachmentStatusJson);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Integer attConfigId = obj.getInteger("attConfigId");
                String status = obj.getString("status");
                
                ApServiceAttachment attUpdate = new ApServiceAttachment();
                attUpdate.setApproveStatus(status);
                apServiceAttachmentMapper.updateSelectiveByMap(attUpdate, MyBatisUtils.params(
                        ApServiceAttachment.Fields.SERVICE_ID, serviceId,
                        ApServiceAttachment.Fields.ATT_CONFIG_ID, attConfigId
                ));
            }
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse attachment status json.", e);
            throw new ServiceException("FailedParseAttJson", "解析附件审批信息失败");
        }
    }

    /**
     * 审批不通过(reject)操作.
     * @param login                 当前登录用户
     * @param serviceId             办件ID
     * @param remark                处理意见
     * @param attachmentStatusJson  附件审批信息 JSONArray   [{ attConfigId:**, status:** }, ...]
     * @throws ServiceException     处理失败
     */
    public void reject(
            UcUserInfo login,
            int serviceId,
            String remark,
            String attachmentStatusJson) throws ServiceException {
        ApServiceInfo service = queryServiceInfo(serviceId);
        checkAllowReject(login, service);
        
        // 保存处理信息
        ApServiceProcess proc = new ApServiceProcess();
        proc.setServiceId(serviceId);
        proc.setServiceStatus(service.getStatus());
        proc.setSeq(0);
        proc.setUserId(login.getUserId());
        proc.setUserName(login.getName());
        proc.setDeptCode("" + service.getDeptcode());
        proc.setDeptName(service.getDeptname());
        proc.setStatus(ApServiceProcess.STATUS_REJECT);
        proc.setRemark(remark);
        proc.setAttachmentsStatus(attachmentStatusJson);
        proc.setCreateTime(new Date());
        apServiceProcessMapper.insertSelective(proc);
        
        // 更新办件信息
        ApServiceInfo serviceUpdate = new ApServiceInfo();
        serviceUpdate.setServiceId(serviceId);
        serviceUpdate.setStatus(ApServiceInfo.STATUS_REJECT_CONFIRM);
        serviceUpdate.setCurrentUserId(0);
        serviceUpdate.setCurrentUserName("");
        serviceUpdate.setCurrentDeptId(0);
        serviceUpdate.setCurrentDeptName("");
        if (service.getStatus().equals(ApServiceInfo.STATUS_CREATED)) {
            serviceUpdate.setAcceptUserId("" + login.getUserId());
            serviceUpdate.setAcceptUserName(login.getName());
            serviceUpdate.setAcceptCellphone(login.getMobile());
            serviceUpdate.setAcceptTime(new Date());
        }
        apServiceInfoMapper.updateSelectiveById(serviceUpdate);
        
        // 更新附件状态
        try {
            JSONArray arr = JSON.parseArray(attachmentStatusJson);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Integer attConfigId = obj.getInteger("attConfigId");
                String status = obj.getString("status");
                
                ApServiceAttachment attUpdate = new ApServiceAttachment();
                attUpdate.setApproveStatus(status);
                apServiceAttachmentMapper.updateSelectiveByMap(attUpdate, MyBatisUtils.params(
                        ApServiceAttachment.Fields.SERVICE_ID, serviceId,
                        ApServiceAttachment.Fields.ATT_CONFIG_ID, attConfigId
                ));
            }
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse attachment status json.", e);
            throw new ServiceException("FailedParseAttJson", "解析附件审批信息失败");
        }
    }

    /**
     * 审批通过(approve)操作.
     * @param login                 当前登录用户
     * @param serviceId             办件ID
     * @param remark                处理意见
     * @param attachmentStatusJson  附件审批信息 JSONArray   [{ attConfigId:**, status:** }, ...]
     * @throws ServiceException     处理失败
     */
    public void approve(
            UcUserInfo login,
            int serviceId,
            String remark,
            String attachmentStatusJson) throws ServiceException {
        ApServiceInfo service = queryServiceInfo(serviceId);
        checkAllowApprove(login, service);
        
        // 保存处理信息
        ApServiceProcess proc = new ApServiceProcess();
        proc.setServiceId(serviceId);
        proc.setServiceStatus(service.getStatus());
        proc.setSeq(0);
        proc.setUserId(login.getUserId());
        proc.setUserName(login.getName());
        proc.setDeptCode("" + service.getDeptcode());
        proc.setDeptName(service.getDeptname());
        proc.setStatus(ApServiceProcess.STATUS_APPROVE);
        proc.setRemark(remark);
        proc.setAttachmentsStatus(attachmentStatusJson);
        proc.setCreateTime(new Date());
        apServiceProcessMapper.insertSelective(proc);
        
        // 更新办件信息
        ApServiceInfo serviceUpdate = new ApServiceInfo();
        serviceUpdate.setServiceId(serviceId);
        serviceUpdate.setStatus(ApServiceInfo.STATUS_FINISH_CONFIRM);
        serviceUpdate.setCurrentUserId(0);
        serviceUpdate.setCurrentUserName("");
        serviceUpdate.setCurrentDeptId(0);
        serviceUpdate.setCurrentDeptName("");
        apServiceInfoMapper.updateSelectiveById(serviceUpdate);
        
        // 更新附件状态
        try {
            JSONArray arr = JSON.parseArray(attachmentStatusJson);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Integer attConfigId = obj.getInteger("attConfigId");
                String status = obj.getString("status");
                
                ApServiceAttachment attUpdate = new ApServiceAttachment();
                attUpdate.setApproveStatus(status);
                apServiceAttachmentMapper.updateSelectiveByMap(attUpdate, MyBatisUtils.params(
                        ApServiceAttachment.Fields.SERVICE_ID, serviceId,
                        ApServiceAttachment.Fields.ATT_CONFIG_ID, attConfigId
                ));
            }
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse attachment status json.", e);
            throw new ServiceException("FailedParseAttJson", "解析附件审批信息失败");
        }
    }


    /**
     * 确认办结/废弃(finish)操作.
     * @param login                 当前登录用户
     * @param serviceId             办件ID
     * @param isApproved            true为审批通过, false为审批不通过
     * @param remark                处理意见
     * @param finishDocFileId       办结文书文件ID
     * @param finishDocFileName     办结文书文件名
     * @param ccDeptId              抄送部门ID
     * @param ccUserId              抄送用户ID
     * @param attachmentStatusJson  附件审批信息 JSONArray   [{ attConfigId:**, status:** }, ...]
     * @throws ServiceException     处理失败
     */
    public void finish(
            UcUserInfo login,
            int serviceId,
            boolean isApproved,
            String remark,
            String finishDocFileId,
            String finishDocFileName,
            Integer ccDeptId,
            Integer ccUserId,
            String attachmentStatusJson) throws ServiceException {
        ApServiceInfo service = queryServiceInfo(serviceId);
        checkAllowFinish(login, service);
        UcUserInfo createUser = queryUserInfo(Integer.parseInt(service.getCreateUserId()));
        
        // 查询抄送用户
        UcUserInfo ccUser = null;
        if (ccUserId != null && ccUserId != 0) {
            ccUser = queryUserInfo(ccUserId);
        }
        
        UcParkDept ccDept = null;
        if (ccDeptId != null && ccDeptId != 0) {
            ccDept = queryDeptInfo(ccDeptId);
        }
        
        // 当前状态为废弃待确认时，不允许审批通过
        if (service.getStatus().equals(ApServiceInfo.STATUS_REJECT_CONFIRM) && isApproved) {
            LOGGER.error("Invalid status to finish approved: ", service.getServiceId());
            throw new ServiceException("InvalidStatusToFinishApproved", "无法最终确认通过");
        }
        
        // 保存处理信息
        ApServiceProcess proc = new ApServiceProcess();
        proc.setServiceId(serviceId);
        proc.setServiceStatus(service.getStatus());
        proc.setSeq(0);
        proc.setUserId(login.getUserId());
        proc.setUserName(login.getName());
        proc.setDeptCode("" + service.getDeptcode());
        proc.setDeptName(service.getDeptname());
        if (isApproved) {
            proc.setStatus(ApServiceProcess.STATUS_FINISH_APPROVED);            
        } else {
            proc.setStatus(ApServiceProcess.STATUS_FINISH_REJECTED);
        }
        proc.setRemark(remark);
        proc.setAttachmentsStatus(attachmentStatusJson);
        proc.setCreateTime(new Date());
        apServiceProcessMapper.insertSelective(proc);
        
        // 保存抄送信息
        if (ccUser != null && ccDept != null) {
        ApServiceCc cc = new ApServiceCc();
            cc.setServiceId(serviceId);
            cc.setCcUserId(ccUser.getUserId());
            cc.setCcUserName(ccUser.getName());
            cc.setCcDeptId(ccDept.getDeptId());
            cc.setCcDeptName(ccDept.getDeptName());
            cc.setCreateTime(new Date());
            apServiceCcMapper.insertSelective(cc);
        }
        
        // 更新办件信息
        ApServiceInfo serviceUpdate = new ApServiceInfo();
        serviceUpdate.setServiceId(serviceId);
        if (isApproved) {
            serviceUpdate.setStatus(ApServiceInfo.STATUS_FINISHED);
        } else {
            serviceUpdate.setStatus(ApServiceInfo.STATUS_REJECTED);
        }
        serviceUpdate.setCurrentUserId(0);
        serviceUpdate.setCurrentUserName("");
        serviceUpdate.setCurrentDeptId(0);
        serviceUpdate.setCurrentDeptName("");
        serviceUpdate.setFinishDocFileId(finishDocFileId);
        serviceUpdate.setFinishDocFileName(finishDocFileName);
        serviceUpdate.setRemark(remark);
        serviceUpdate.setFinishTime(new Date());
        apServiceInfoMapper.updateSelectiveById(serviceUpdate);
        
        // 发送消息
        if (UcUserInfo.USER_TYPE_COMPANY.equals(createUser.getUserType())) {
            if (service.getStatus().equals(ApServiceInfo.STATUS_FINISH_CONFIRM)) {
                String message = "办件" + service.getBusinessNo() + "已办结"; 
                messageService.createSystemMessage(UcMessage.TYPE_AP_SERVICE, message, message,
                        Integer.valueOf(service.getCreateUserId()), String.valueOf(service.getServiceId()));
            } else {
                String message = "办件" + service.getBusinessNo() + "已废弃"; 
                messageService.createSystemMessage(UcMessage.TYPE_AP_SERVICE, message, message,
                        Integer.valueOf(service.getCreateUserId()), String.valueOf(service.getServiceId()));
            }
        }
        
        // 更新附件状态
        try {
            JSONArray arr = JSON.parseArray(attachmentStatusJson);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Integer attConfigId = obj.getInteger("attConfigId");
                String status = obj.getString("status");
                
                ApServiceAttachment attUpdate = new ApServiceAttachment();
                attUpdate.setApproveStatus(status);
                apServiceAttachmentMapper.updateSelectiveByMap(attUpdate, MyBatisUtils.params(
                        ApServiceAttachment.Fields.SERVICE_ID, serviceId,
                        ApServiceAttachment.Fields.ATT_CONFIG_ID, attConfigId
                ));
            }
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse attachment status json.", e);
            throw new ServiceException("FailedParseAttJson", "解析附件审批信息失败");
        }
    }
    
    /**
     * 根据ID查询办件信息.
     */
    private ApServiceInfo queryServiceInfo(int serviceId) throws ServiceException {
        ApServiceInfo service = apServiceInfoMapper.selectById(serviceId);
        if (service == null) {
            LOGGER.error("Service id not found: ", serviceId);
            throw new ServiceException("ServiceNotFound", "办件信息不存在");
        }
        return service;
    }
    
    /**
     * 根据ID查询用户信息.
     */
    private UcUserInfo queryUserInfo(int userId) throws ServiceException {
        UcUserInfo user = ucUserInfoMapper.selectById(userId);
        if (user == null) {
            LOGGER.error("User id not found: {}", userId);
            throw new ServiceException("UserNotFound", "用户不存在");
        }
        return user;
    }
    
    /**
     * 根据ID查询部门信息.
     */
    private UcParkDept queryDeptInfo(int deptId) throws ServiceException {
        UcParkDept dept = ucParkDeptMapper.selectById(deptId);
        if (dept == null) {
            LOGGER.error("Dept id not found: {}", deptId);
            throw new ServiceException("DeptNotFound", "部门不存在");
        }
        return dept;
    }
    
    /**
     * 检查办件是否可以Deliver.
     */
    private void checkAllowDeliver(UcUserInfo login, ApServiceInfo service) throws ServiceException {
        switch (service.getStatus()) {
            case ApServiceInfo.STATUS_CREATED:
                if (!UcUserInfo.SR_WINDOW.equals(login.getServiceRole())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_FIRST_CHECK:
                if (!UcUserInfo.SR_DEPUTY_DIRECTOR.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_REVIEW:
                if (!UcUserInfo.SR_DIRECTOR.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_DECIDE:
                if (!UcUserInfo.SR_DEPUTY_CHIEF.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_DIRECTOR_DECIDE:
                if (!UcUserInfo.SR_CHIEF.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_SECRETARY_DECIDE:
            case ApServiceInfo.STATUS_FINISH_CONFIRM:
            case ApServiceInfo.STATUS_REJECT_CONFIRM:
            case ApServiceInfo.STATUS_FINISHED:
            case ApServiceInfo.STATUS_REJECTED:
            default:
                LOGGER.error("Invalid status to deliver: serviceId:{}, status:{}", service.getServiceId(), service.getStatus());
                throw new ServiceException("InvalidStatusToDeliver", "办件当前状态不能进行下一步操作");
        }
    }
    
    /**
     * 检查办件是否可以Reject.
     */
    private void checkAllowReject(UcUserInfo login, ApServiceInfo service) throws ServiceException {
        switch (service.getStatus()) {
            case ApServiceInfo.STATUS_CREATED:
                if (!UcUserInfo.SR_WINDOW.equals(login.getServiceRole())) {
                    LOGGER.error("No privilege to reject: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行下一步操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_FIRST_CHECK:
                if (!UcUserInfo.SR_DEPUTY_DIRECTOR.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_REVIEW:
                if (!UcUserInfo.SR_DIRECTOR.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_DECIDE:
                if (!UcUserInfo.SR_DEPUTY_CHIEF.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_DIRECTOR_DECIDE:
                if (!UcUserInfo.SR_CHIEF.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_SECRETARY_DECIDE:
                if (!UcUserInfo.SR_SECRETARY_GENERAL.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_FINISH_CONFIRM:
            case ApServiceInfo.STATUS_REJECT_CONFIRM:
            case ApServiceInfo.STATUS_FINISHED:
            case ApServiceInfo.STATUS_REJECTED:
            default:
                LOGGER.error("Invalid status to reject: serviceId:{}, status:{}", service.getServiceId(), service.getStatus());
                throw new ServiceException("InvalidStatusToReject", "办件当前状态不能进行审批不通过操作");
        }
    }
    
    /**
     * 检查办件是否可以Approve.
     */
    private void checkAllowApprove(UcUserInfo login, ApServiceInfo service) throws ServiceException {
        switch (service.getStatus()) {
            case ApServiceInfo.STATUS_TO_REVIEW:
                if (!UcUserInfo.SR_DIRECTOR.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_DECIDE:
                if (!UcUserInfo.SR_DEPUTY_CHIEF.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_DIRECTOR_DECIDE:
                if (!UcUserInfo.SR_CHIEF.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_SECRETARY_DECIDE:
                if (!UcUserInfo.SR_SECRETARY_GENERAL.equals(login.getServiceRole())
                        || !login.getUserId().equals(service.getCurrentUserId())) {
                    LOGGER.error("No privilege to deliver: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行操作");
                }
                break;
            case ApServiceInfo.STATUS_CREATED:
            case ApServiceInfo.STATUS_TO_FIRST_CHECK:
            case ApServiceInfo.STATUS_FINISH_CONFIRM:
            case ApServiceInfo.STATUS_REJECT_CONFIRM:
            case ApServiceInfo.STATUS_FINISHED:
            case ApServiceInfo.STATUS_REJECTED:
            default:
                LOGGER.error("Invalid status to reject: serviceId:{}, status:{}", service.getServiceId(), service.getStatus());
                throw new ServiceException("InvalidStatusToReject", "办件当前状态不能进行审批通过操作");
        }
    }
    
    /**
     * 检查办件是否可以Finish.
     */
    private void checkAllowFinish(UcUserInfo login, ApServiceInfo service) throws ServiceException {
        switch (service.getStatus()) {
            case ApServiceInfo.STATUS_FINISH_CONFIRM:
            case ApServiceInfo.STATUS_REJECT_CONFIRM:
                if (!UcUserInfo.SR_WINDOW.equals(login.getServiceRole())) {
                    LOGGER.error("No privilege to reject: {}", service.getServiceId());
                    throw new ServiceException("NoPrivilegeToDeliver", "您没有权限进行下一步操作");
                }
                break;
            case ApServiceInfo.STATUS_TO_REVIEW:
            case ApServiceInfo.STATUS_TO_DECIDE:
            case ApServiceInfo.STATUS_TO_DIRECTOR_DECIDE:
            case ApServiceInfo.STATUS_TO_SECRETARY_DECIDE:
            case ApServiceInfo.STATUS_CREATED:
            case ApServiceInfo.STATUS_TO_FIRST_CHECK:
            case ApServiceInfo.STATUS_FINISHED:
            case ApServiceInfo.STATUS_REJECTED:
            default:
                LOGGER.error("Invalid status to reject: serviceId:{}, status:{}", service.getServiceId(), service.getStatus());
                throw new ServiceException("InvalidStatusToReject", "办件当前状态不能进行审批通过操作");
        }
    }
    
    /**
     * Deliver后的状态.
     */
    private String statusAfterDeliver(ApServiceInfo service) throws ServiceException {
        switch (service.getStatus()) {
            case ApServiceInfo.STATUS_CREATED:
                return ApServiceInfo.STATUS_TO_FIRST_CHECK;
            case ApServiceInfo.STATUS_TO_FIRST_CHECK:
                return ApServiceInfo.STATUS_TO_REVIEW;
            case ApServiceInfo.STATUS_TO_REVIEW:
                return ApServiceInfo.STATUS_TO_DECIDE;
            case ApServiceInfo.STATUS_TO_DECIDE:
                return ApServiceInfo.STATUS_TO_DIRECTOR_DECIDE;
            case ApServiceInfo.STATUS_TO_DIRECTOR_DECIDE:
                return ApServiceInfo.STATUS_TO_SECRETARY_DECIDE;
            case ApServiceInfo.STATUS_TO_SECRETARY_DECIDE:
            case ApServiceInfo.STATUS_FINISH_CONFIRM:
            case ApServiceInfo.STATUS_REJECT_CONFIRM:
            case ApServiceInfo.STATUS_FINISHED:
            case ApServiceInfo.STATUS_REJECTED:
            default:
                LOGGER.error("Invalid status to deliver: serviceId:{}, status:{}", service.getServiceId(), service.getStatus());
                throw new ServiceException("InvalidStatusToDeliver", "办件当前状态不能进行下一步操作");
        }
    }
    
    /**
     * 检查下一步用户信息.
     */
    private UcUserInfo checkNextStepUser(ApServiceInfo service, Integer nextUserId) throws ServiceException {
//        // 只有预审和初审可以指定下一步用户
//        if (!service.getStatus().equals(ApServiceInfo.STATUS_CREATED) 
//                && !service.getStatus().equals(ApServiceInfo.STATUS_TO_FIRST_CHECK)) {
//            LOGGER.error("Invalid status to specify next user: {}", service.getStatus());
//            throw new ServiceException("InvalidStatusToSpecifyNextUser", "只有预审和初审时可以指定接收用户");
//        }
        return queryUserInfo(nextUserId);
    }
    
    /**
     * 获取下一步部门信息.
     */
    private UcParkDept checkNextStepDept(ApServiceInfo service, Integer nextDeptId) throws ServiceException {
        if (nextDeptId == null) {
            nextDeptId = 0;
        }
        
        UcParkDept dept = new UcParkDept();
        switch (nextDeptId) {
            case 1:
                dept.setDeptId(1);
                dept.setDeptName("经济发展局");
                return dept;
            case 2:
                dept.setDeptId(2);
                dept.setDeptName("国土规划局");
                return dept;
            case 3:
                dept.setDeptId(3);
                dept.setDeptName("工程建设局");
                return dept;
            case 4:
                dept.setDeptId(4);
                dept.setDeptName("社会事务局");
                return dept;
            case 5:
                dept.setDeptId(5);
                dept.setDeptName("招商合作局");
                return dept;
            case 6:
                dept.setDeptId(6);
                dept.setDeptName("财政分局");
                return dept;
            case 7:
                dept.setDeptId(7);
                dept.setDeptName("办公室");
                return dept;
            case 8:
                dept.setDeptId(8);
                dept.setDeptName("党群纪检绩效办");
                return dept;
            case 99:
                dept.setDeptId(99);
                dept.setDeptName("其它");
                return dept;
            default:
                LOGGER.error("Next step dept not found: {}", nextDeptId);
                throw new ServiceException("NextDeptNotFound", "部门信息不存在");
        }
    }
    
    /**
     * 生成受理通知书.
     */
    private void createAcceptNotice(ApServiceInfo service) throws ServiceException {
        // 查询附件
        List<ApServiceAttachment> attlist = apServiceAttachmentMapper.selectByMap(MyBatisUtils.params(
                ApServiceAttachment.Fields.SERVICE_ID, service.getServiceId(),
                ApServiceAttachment.Query.FILE_ID__NE, "NOFILE",
                new OrderItem(ApServiceAttachment.Fields.ATT_CONFIG_ID, OrderItem.ASC),
                new OrderItem(ApServiceAttachment.Fields.FILE_NAME, OrderItem.ASC)
        ));
        
        // 查询办理用户
        UcUserInfo createUser = null;
        if (StringUtils.isNotEmpty(service.getCreateUserId())) {
            createUser = ucUserInfoMapper.selectById(Integer.parseInt(service.getCreateUserId()));
        }
        
        // 生成
        AcceptNoticePdfGenerator generator = new AcceptNoticePdfGenerator();
        byte[] pdfData = generator.generate(service, attlist, createUser);
        
        // 保存文件
        String fileName = "受理通知书-" + service.getBusinessNo() + ".pdf";
        String fileId = fileStoreService.save(fileName, pdfData);
        
        // 保存fileId
        ApServiceInfo update = new ApServiceInfo();
        update.setServiceId(service.getServiceId());
        update.setAcceptNoticeFileId(fileId);
        update.setAcceptNoticeFileName(fileName);
        apServiceInfoMapper.updateSelectiveById(update);
    }
    
}
