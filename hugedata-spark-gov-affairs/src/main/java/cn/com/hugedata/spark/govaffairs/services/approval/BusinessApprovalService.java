package cn.com.hugedata.spark.govaffairs.services.approval;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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
import cn.com.hugedata.spark.commons.service.approval.ContentUtil;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceAttachment;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceProcess;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttConfig;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceProcessMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttConfigMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class BusinessApprovalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessApprovalService.class);
    
    @Autowired
    private GaServiceGuideMapper gaServiceGuideMapper;
    
    @Autowired
    private GaServiceGuideAttConfigMapper gaServiceGuideAttConfigMapper;
    
	@Autowired
    private ApServiceInfoMapper apServiceInfoMapper;
	
	@Autowired
	private ApServiceAttachmentMapper apServiceAttachmentMapper;
	
	@Autowired
	private ApServiceProcessMapper apServiceProcessMapper;

	/**
	 * 创建新的在线办理.
	 */
    public ApServiceInfo createService(
            int guideId, 
            String businessDeptPerson,
            String cellphone,
            String attachmentsJson) throws ServiceException { 
        
        try {
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
            service.setBusinessType(ApServiceInfo.BUSINESS_TYPE_ONLINE);
            service.setBusinessNo("ZX" + ContentUtil.produceRandom());
            service.setBusinessDeptPerson(businessDeptPerson);
            service.setCellphone(cellphone);
            service.setCreateTime(new Date());
            service.setCreateCellphone(LoginUtils.getCurrentLogin().getMobile());
            service.setCreateUserId(String.valueOf(LoginUtils.getCurrentLogin().getUserId()));
            service.setStatus(ApServiceInfo.STATUS_CREATED);
            if (StringUtils.isNotEmpty(guide.getPromisedTimeLimit())) {
                service.setTimeLimit(guide.getPromisedTimeLimit());
            } else {
                service.setTimeLimit(guide.getLegalTimeLimit());
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
                if (ac == null) {
                    LOGGER.error("Service guide att config not found: {}", attItem.getInteger("attConfigId"));
                    throw new ServiceException("AttConfigNotFound", "办理材料配置信息不正确");
                }
                
                ApServiceAttachment att = new ApServiceAttachment();
                att.setServiceId(service.getServiceId());
                att.setAttConfigId(ac.getAttConfigId());
                att.setAttConfigName(ac.getAttConfigName());
                att.setFileId(attItem.getString("fileId"));
                att.setFileName(attItem.getString("fileName"));
                att.setRemark(attItem.getString("remark"));
                att.setApproveStatus(ApServiceAttachment.STATUS_CREATED);
                att.setSortIndex(i);
                att.setCreateTime(new Date());
                apServiceAttachmentMapper.insertSelective(att);
                
                configHasNoFile.remove(ac.getAttConfigId());
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
            
            return service;
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse json", e);
            throw new ServiceException("FailedParseAttJson", "解析附件信息失败");
        }
    }

    /**
     * 撤销办事.
     */
    public void cancelService(UcUserInfo login, int serviceId) throws ServiceException {
        ApServiceInfo service = queryServiceInfo(serviceId);
        
        // 保存处理信息
        ApServiceProcess proc = new ApServiceProcess();
        proc.setServiceId(serviceId);
        proc.setServiceStatus(service.getStatus());
        proc.setSeq(0);
        proc.setUserId(login.getUserId());
        proc.setUserName(login.getName());
        proc.setDeptCode("" + service.getDeptcode());
        proc.setDeptName(service.getDeptname());
        proc.setStatus(ApServiceProcess.STATUS_CANCEL);
        proc.setRemark("事项提交人撤销办事");
        proc.setCreateTime(new Date());
        apServiceProcessMapper.insertSelective(proc);
        
        // 更新办件信息
        ApServiceInfo serviceUpdate = new ApServiceInfo();
        serviceUpdate.setServiceId(serviceId);
        serviceUpdate.setStatus(ApServiceInfo.STATUS_CANCELLED);
        serviceUpdate.setCurrentUserId(0);
        serviceUpdate.setCurrentUserName("");
        serviceUpdate.setCurrentDeptId(0);
        serviceUpdate.setCurrentDeptName("");
        serviceUpdate.setRemark("事项提交人撤销办事");
        serviceUpdate.setFinishTime(new Date());
        apServiceInfoMapper.updateSelectiveById(serviceUpdate);
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
}
