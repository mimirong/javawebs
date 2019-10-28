package cn.com.hugedata.spark.govaffairs.services.approval;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceAttachment;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceProcess;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttConfig;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceProcessMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttConfigMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

/**
 * 办事指南查询服务.
 */
@Service
public class ApprovalService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalService.class);

    @Autowired
    private ApServiceInfoMapper apServiceInfoMapper;

    @Autowired
    private ApServiceAttachmentMapper apServiceAttachmentMapper;

    @Autowired
    private ApServiceProcessMapper apServiceProcessMapper;
    
    @Autowired
    private GaServiceGuideAttConfigMapper gaServiceGuideAttConfigMapper;
    
    /**
     * 查询办事指南列表.
     * @param deptCode 部门
     * @param start    分页开始记录
     * @param length   每页记录数
     * @return         分页查询结果
     */
    public PageEntity<ApServiceInfo> list(Map<String,Object> queryMap, int start, int length) {
    	List<OrderItem> orders = new ArrayList<OrderItem>();
    	orders.add(new OrderItem(ApServiceInfo.Fields.CREATE_TIME, OrderItem.DESC));
    	queryMap.put("order", orders);
        List<ApServiceInfo> data = apServiceInfoMapper.selectByMap(queryMap, new RowBounds(start, length));
        int count = apServiceInfoMapper.countByMap(queryMap);
        return new PageEntity<ApServiceInfo>(data, start, length, count);
    }
    
    /**
     * 查询办件详情 .
     */
    public ApServiceDetails queryServiceDetails(int serviceId) throws ServiceException {
        ApServiceInfo serviceInfo = apServiceInfoMapper.selectById(serviceId);
        if (serviceInfo == null) {
            LOGGER.error("Service id not found: {}", serviceId);
            throw new ServiceException("ServiceIdNotFound", "办件信息不存在");
        }
        
        List<ApServiceAttachment> attachments = apServiceAttachmentMapper.selectByMap(MyBatisUtils.params(
                ApServiceAttachment.Fields.SERVICE_ID, serviceId,
                new OrderItem(ApServiceAttachment.Fields.SORT_INDEX, OrderItem.ASC)
        ));
        
        List<GaServiceGuideAttConfig> attConfigs = gaServiceGuideAttConfigMapper.selectByMap(MyBatisUtils.params(
                GaServiceGuideAttConfig.Fields.GUIDE_ID, Integer.parseInt(serviceInfo.getGuidecode()),
                new OrderItem(GaServiceGuideAttConfig.Fields.ATT_CONFIG_ID, OrderItem.ASC)
        ));
        
        List<ApServiceProcess> procList = apServiceProcessMapper.selectByMap(MyBatisUtils.params(
                ApServiceProcess.Fields.SERVICE_ID, serviceId,
                new OrderItem(ApServiceProcess.Fields.PROCESS_ID, OrderItem.ASC)
        ));
        
        ApServiceDetails details = new ApServiceDetails();
        details.serviceInfo = serviceInfo;
        details.attachments = attachments;
        details.attConfigs = attConfigs;
        details.procList = procList;
        return details;
    }

    /**
     * 办件详情.
     */
    public static class ApServiceDetails {
        
        private ApServiceInfo serviceInfo;
        
        private List<ApServiceAttachment> attachments;
        
        private List<GaServiceGuideAttConfig> attConfigs;
        
        private List<ApServiceProcess> procList;
        
        public ApServiceInfo getServiceInfo() {
            return serviceInfo;
        }

        public void setServiceInfo(ApServiceInfo serviceInfo) {
            this.serviceInfo = serviceInfo;
        }

        public List<ApServiceAttachment> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<ApServiceAttachment> attachments) {
            this.attachments = attachments;
        }

        public List<GaServiceGuideAttConfig> getAttConfigs() {
            return attConfigs;
        }

        public void setAttConfigs(List<GaServiceGuideAttConfig> attConfigs) {
            this.attConfigs = attConfigs;
        }

        public List<ApServiceProcess> getProcList() {
            return procList;
        }

        public void setProcList(List<ApServiceProcess> procList) {
            this.procList = procList;
        }
    }
}
