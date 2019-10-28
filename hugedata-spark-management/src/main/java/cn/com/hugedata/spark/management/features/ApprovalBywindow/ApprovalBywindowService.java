package cn.com.hugedata.spark.management.features.ApprovalBywindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceAttachment;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceProcess;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttConfig;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttachment;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceProcessMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttConfigMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideMapper;
import cn.com.hugedata.spark.commons.storage.querymapper.ApServiceInfoQueryMapper;
import cn.com.hugedata.spark.commons.storage.querymapper.UcUserInfoQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;
import cn.com.hugedata.spark.management.services.approval.ApprovalService;

@Service
public class ApprovalBywindowService
        extends ManagementFeatureServiceImpl<ApServiceInfo, Integer, ApprovalBywindowModel, ApServiceInfoMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalBywindowService.class);
    
    @Autowired
    private ApServiceInfoMapper apServiceInfoMapper;
    
    @Autowired
    private ApServiceAttachmentMapper apServiceAttachmentMapper;
    
    @Autowired
    private ApServiceProcessMapper apServiceProcessMapper;
    
    @Autowired
    private GaServiceGuideMapper gaServiceGuideMapper;
    
    @Autowired
    private GaServiceGuideAttachmentMapper gaServiceGuideAttachmentMapper;
    
    @Autowired
    private GaServiceGuideAttConfigMapper gaServiceGuideAttConfigMapper;

    @Autowired
    private ApprovalService approvalService;
    
    @Autowired
    private UcUserInfoQueryMapper ucUserInfoQueryMapper;
    
    @Autowired
    private ApServiceInfoQueryMapper apServiceInfoQueryMapper;
    
    
    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_AP_SERVICE_BYWINDOW;
    }

    @Override
    public PageEntity<ApServiceInfo> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        // 状态
        String status = (String) query.get("status");
        if (StringUtils.isNotEmpty(status)) {
            switch (status) {
                case "11": //待预审
                    query.put(ApServiceInfo.Fields.STATUS, ApServiceInfo.STATUS_CREATED);
                    break;
                case "12": //已受理
                    query.remove(ApServiceInfo.Fields.STATUS);
                    query.put(ApServiceInfo.Query.STATUS__IN, 
                            Arrays.asList(ApServiceInfo.STATUS_TO_FIRST_CHECK,
                                          ApServiceInfo.STATUS_TO_REVIEW,
                                          ApServiceInfo.STATUS_TO_DECIDE,
                                          ApServiceInfo.STATUS_TO_DIRECTOR_DECIDE,
                                          ApServiceInfo.STATUS_TO_SECRETARY_DECIDE,
                                          ApServiceInfo.STATUS_FINISH_CONFIRM,
                                          ApServiceInfo.STATUS_REJECT_CONFIRM));
                    break;
                case "13": //已办结
                    query.put(ApServiceInfo.Fields.STATUS, ApServiceInfo.STATUS_FINISHED);
                    break;
                case "14": //废弃件
                    query.put(ApServiceInfo.Query.STATUS__IN, 
                            Arrays.asList(ApServiceInfo.STATUS_REJECTED,
                                          ApServiceInfo.STATUS_CANCELLED));
                    break;
            }
        }
        
        // 排序
        orders = new ArrayList<OrderItem>();
        orders.add(new OrderItem(ApServiceInfo.Fields.SERVICE_ID, OrderItem.DESC));
        
        // 查询条件
        query.put(ApServiceInfo.Fields.BUSINESS_TYPE, ApServiceInfo.BUSINESS_TYPE_WINDOW);
        query.put("loginUserId", LoginUtils.getCurrentLogin().getUserId());
        
        PageEntity<ApServiceInfo> page = super.list(query, pageStart, pageSize, orders);
        return page;
    }

    @Override
    protected List<? super ApServiceInfo> executeSelectByMap(Map<String, Object> query, int pageStart, int pageSize) {
        return apServiceInfoQueryMapper.selectForParkUser(query, new RowBounds(pageStart, pageSize));
    }

    @Override
    protected Integer executeCountByMap(Map<String, Object> query) {
        return apServiceInfoQueryMapper.countForParkUser(query);
    }

    @Override
    public ApprovalBywindowModel queryForModify(Integer id) throws ServiceException {
        ApprovalBywindowModel model = super.queryForModify(id);
        queryAttachments(id, model);
        queryAttConfigs(Integer.parseInt(model.getGuidecode()), model);
        queryProcList(id, model);
        return model;
    }
    
    /**
     * 查询附件信息并保存到model.
     */
    private void queryAttachments(int serviceId, ApprovalBywindowModel model) {
        List<ApServiceAttachment> attachments = apServiceAttachmentMapper.selectByMap(MyBatisUtils.params(
                ApServiceAttachment.Fields.SERVICE_ID, serviceId,
                new OrderItem(ApServiceAttachment.Fields.SORT_INDEX, OrderItem.ASC)
        ));
        model.setAttachmentsJson(JSON.toJSONString(attachments));
    }
    
    /**
     * 查询办理材料配置信息并保存到model.
     */
    private void queryAttConfigs(int guideId, ApprovalBywindowModel model) {
        List<GaServiceGuideAttConfig> attConfigs = gaServiceGuideAttConfigMapper.selectByMap(MyBatisUtils.params(
                GaServiceGuideAttConfig.Fields.GUIDE_ID, guideId,
                new OrderItem(GaServiceGuideAttConfig.Fields.ATT_CONFIG_ID, OrderItem.ASC)
        ));
        model.setAttConfigsJson(JSON.toJSONString(attConfigs));
    }
    
    /**
     * 查询处理记录信息并保存到model.
     */
    private void queryProcList(int serviceId, ApprovalBywindowModel model) {
        List<ApServiceProcess> procList = apServiceProcessMapper.selectByMap(MyBatisUtils.params(
                ApServiceProcess.Fields.SERVICE_ID, serviceId,
                new OrderItem(ApServiceProcess.Fields.PROCESS_ID, OrderItem.ASC)
        ));
        model.setProcListJson(JSON.toJSONString(procList));
    }
    
    @Override
    public ApServiceInfo add(ApprovalBywindowModel model) throws ServiceException {
        try {
            int guideId = Integer.parseInt(model.getGuidecode());
            String businessDeptPerson = model.getBusinessDeptPerson();
            String cellphone = model.getCellphone();
            String businessNo = model.getBusinessNo();
            String remark = model.getRemark();
            String attachmentsJson = model.getAttachmentsJson();
            int nextUserId = model.getCurrentUserId();
            int nextDeptId = model.getCurrentDeptId();
            
            // 查询办事指南信息
            GaServiceGuide guide = gaServiceGuideMapper.selectById(guideId);
            if (guide == null) {
                LOGGER.error("Service guide not found: {}", guideId);
                throw new ServiceException("ServiceGuideNotFound", "办事指南信息不存在");
            }
            
            // 保存申请信息
            ApServiceInfo service = new ApServiceInfo();
            service.setDeptcode(Integer.parseInt(guide.getDeptCode()));
            service.setDeptname(guide.getDeptName());
            service.setGuidecode("" + guideId);
            service.setGuidename(guide.getGuideName());
            service.setBusinessType(ApServiceInfo.BUSINESS_TYPE_WINDOW);
            service.setBusinessNo(businessNo);
            service.setBusinessDeptPerson(businessDeptPerson);
            service.setCellphone(cellphone);
            service.setCreateTime(new Date());
            service.setCreateCellphone(LoginUtils.getCurrentLogin().getMobile());
            service.setCreateUserId(String.valueOf(LoginUtils.getCurrentLogin().getUserId()));
            service.setStatus(ApServiceInfo.STATUS_CREATED);
            if (StringUtils.isNotEmpty(guide.getPromisedTimeLimit())) {
                service.setTimeLimit(guide.getPromisedTimeLimit());
            } else if (StringUtils.isNotEmpty(guide.getLegalTimeLimit())) {
                service.setTimeLimit(guide.getLegalTimeLimit());
            } else {
                service.setTimeLimit("");
            }
            apServiceInfoMapper.insertSelective(service);
            
            // 查询所有附件配置
            List<GaServiceGuideAttConfig> configList = gaServiceGuideAttConfigMapper.selectByMap(MyBatisUtils.params(
                    GaServiceGuideAttConfig.Fields.GUIDE_ID, guideId
            ));
            Map<Integer, GaServiceGuideAttConfig> configMap = new TreeMap<>();
            Map<Integer, GaServiceGuideAttConfig> configHasNoFile = new TreeMap<>();
            for (GaServiceGuideAttConfig ac : configList) {
                configMap.put(ac.getAttConfigId(), ac);
                configHasNoFile.put(ac.getAttConfigId(), ac);
            }
            
            // 保存附件
            JSONArray attArray = JSON.parseArray(attachmentsJson);
            for (int i = 0; i < attArray.size(); i++) {
                JSONObject attItem = attArray.getJSONObject(i);
                
                GaServiceGuideAttConfig ac = configMap.get(attItem.getInteger("attConfigId"));
                String attConfigName;
                if (ac != null) {
                    attConfigName = ac.getAttConfigName();
                } else {
                    attConfigName = attItem.getString("attConfigName");
                }
                
                ApServiceAttachment att = new ApServiceAttachment();
                att.setServiceId(service.getServiceId());
                att.setAttConfigId(attItem.getInteger("attConfigId"));
                att.setAttConfigName(attConfigName);
                att.setFileId(attItem.getString("fileId"));
                att.setFileName(attItem.getString("fileName"));
                att.setRemark(attItem.getString("remark"));
                att.setApproveStatus(ApServiceAttachment.STATUS_CREATED);
                att.setSortIndex(i);
                att.setCreateTime(new Date());
                apServiceAttachmentMapper.insertSelective(att);
                
                configHasNoFile.remove(attItem.getInteger("attConfigId"));
            }
            
            // 如果没有文件，插入一个占位数据
            for (Entry<Integer, GaServiceGuideAttConfig> e : configHasNoFile.entrySet()) {
                GaServiceGuideAttConfig ac = e.getValue();
                ApServiceAttachment att = new ApServiceAttachment();
                att.setServiceId(service.getServiceId());
                att.setAttConfigId(ac.getAttConfigId());
                att.setAttConfigName(ac.getAttConfigName());
                att.setFileId("NOFILE");
                att.setFileName("未提交");
                att.setRemark("");
                att.setApproveStatus(ApServiceAttachment.STATUS_CREATED);
                att.setSortIndex(0);
                att.setCreateTime(new Date());
                apServiceAttachmentMapper.insertSelective(att);
            }
            
            // Deliver
            JSONArray attachmentStatusArray = new JSONArray();
            for (int i = 0; i < attArray.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("attConfigId", attArray.getJSONObject(i).getInteger("attConfigId"));
                obj.put("status", "APPROVED");
                attachmentStatusArray.add(obj);
            }
            approvalService.deliver(LoginUtils.getCurrentLogin(), service.getServiceId(), remark,
                    nextUserId, nextDeptId, attachmentStatusArray.toJSONString());
            
            return service;
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse json", e);
            throw new ServiceException("FailedParseAttJson", "解析附件信息失败");
        }
    }
    
    /**
     * 查询一个部门下的办理事项信息.
     */
    public List<GaServiceGuide> queryGuideList(Integer deptId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put(GaServiceGuide.Fields.DEPT_CODE, deptId);
        return gaServiceGuideMapper.selectByMap(queryMap);
    }

    
    /**
     * 查询一个部门下的用户.
     */
    public List<UcUserInfo> queryDeptUsers(String deptCode, String serviceRole) {
        if (StringUtils.isEmpty(serviceRole)) {
            serviceRole = null;
        }
        
        return ucUserInfoQueryMapper.selectByMap(MyBatisUtils.params(
                "deptCode", Integer.parseInt(deptCode),
                "serviceRole", serviceRole,
                new OrderItem(UcUserInfo.Fields.NAME, OrderItem.ASC)
        ));
    }
    
    /**
     * 查询一个办事事项的详细信息.
     */
    public ServiceGuideData queryGuideDetails(Integer guideId) {
        // 查询办事指南信息
        GaServiceGuide guide = gaServiceGuideMapper.selectById(guideId);
        if (guide == null) {
            return null;
        }

        // 查询附件信息
        List<GaServiceGuideAttachment> attachmentList = gaServiceGuideAttachmentMapper.selectByMap(MyBatisUtils.params(
                GaServiceGuideAttachment.Fields.GUIDE_ID, guideId,
                new OrderItem(GaServiceGuideAttachment.Fields.SORT_INDEX, OrderItem.ASC)
        ));
        
        // 查询办理材料信息
        List<GaServiceGuideAttConfig> configs = gaServiceGuideAttConfigMapper.selectByMap(MyBatisUtils.params(
                GaServiceGuideAttConfig.Fields.GUIDE_ID, guideId,
                new OrderItem(GaServiceGuideAttConfig.Fields.ATT_CONFIG_ID, OrderItem.ASC)
        ));

        // 返回
        ServiceGuideData data = new ServiceGuideData();
        data.guide = guide;
        data.attachments = attachmentList;
        data.attConfigs = configs;
        return data;
    }

    /**
     * 表示一个办事事项的详细信息.
     */
    public static class ServiceGuideData {

        private GaServiceGuide guide;
        
        private List<GaServiceGuideAttachment> attachments;
        
        private List<GaServiceGuideAttConfig> attConfigs;

        public GaServiceGuide getGuide() {
            return guide;
        }

        public void setGuide(GaServiceGuide guide) {
            this.guide = guide;
        }

        public List<GaServiceGuideAttachment> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<GaServiceGuideAttachment> attachments) {
            this.attachments = attachments;
        }

        public List<GaServiceGuideAttConfig> getAttConfigs() {
            return attConfigs;
        }

        public void setAttConfigs(List<GaServiceGuideAttConfig> attConfigs) {
            this.attConfigs = attConfigs;
        }
    }
}
