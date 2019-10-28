package cn.com.hugedata.spark.management.features.ApprovalonLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceAttachment;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceProcess;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttConfig;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.ApServiceProcessMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttConfigMapper;
import cn.com.hugedata.spark.commons.storage.querymapper.ApServiceInfoQueryMapper;
import cn.com.hugedata.spark.commons.storage.querymapper.UcUserInfoQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;

@Service
public class ApprovalonLineService
        extends ManagementFeatureServiceImpl<ApServiceInfo, Integer, ApprovalonLineModel, ApServiceInfoMapper> {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalonLineService.class);
    
    @Autowired
    private ApServiceInfoMapper apServiceInfoMapper;

    @Autowired
    private ApServiceAttachmentMapper apServiceAttachmentMapper;
    
    @Autowired
    private GaServiceGuideAttConfigMapper gaServiceGuideAttConfigMapper;
    
    @Autowired
    private ApServiceProcessMapper apServiceProcessMapper;
    
    @Autowired
    private UcUserInfoQueryMapper ucUserInfoQueryMapper;
    
    @Autowired
    private ApServiceInfoQueryMapper apServiceInfoQueryMapper;
    
    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_AP_SERVICE_ONLINE;
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
        query.put(ApServiceInfo.Fields.BUSINESS_TYPE, ApServiceInfo.BUSINESS_TYPE_ONLINE);
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
    public ApprovalonLineModel queryForModify(Integer id) throws ServiceException {
        ApprovalonLineModel model = super.queryForModify(id);
        queryAttachments(id, model);
        queryAttConfigs(Integer.parseInt(model.getGuidecode()), model);
        queryProcList(id, model);
        return model;
    }
    
    /**
     * 查询附件信息并保存到model.
     */
    private void queryAttachments(int serviceId, ApprovalonLineModel model) {
        List<ApServiceAttachment> attachments = apServiceAttachmentMapper.selectByMap(MyBatisUtils.params(
                ApServiceAttachment.Fields.SERVICE_ID, serviceId,
                new OrderItem(ApServiceAttachment.Fields.SORT_INDEX, OrderItem.ASC)
        ));
        model.setAttachmentsJson(JSON.toJSONString(attachments));
    }
    
    /**
     * 查询办理材料配置信息并保存到model.
     */
    private void queryAttConfigs(int guideId, ApprovalonLineModel model) {
        List<GaServiceGuideAttConfig> attConfigs = gaServiceGuideAttConfigMapper.selectByMap(MyBatisUtils.params(
                GaServiceGuideAttConfig.Fields.GUIDE_ID, guideId,
                new OrderItem(GaServiceGuideAttConfig.Fields.ATT_CONFIG_ID, OrderItem.ASC)
        ));
        model.setAttConfigsJson(JSON.toJSONString(attConfigs));
    }
    
    /**
     * 查询处理记录信息并保存到model.
     */
    private void queryProcList(int serviceId, ApprovalonLineModel model) {
        List<ApServiceProcess> procList = apServiceProcessMapper.selectByMap(MyBatisUtils.params(
                ApServiceProcess.Fields.SERVICE_ID, serviceId,
                new OrderItem(ApServiceProcess.Fields.PROCESS_ID, OrderItem.ASC)
        ));
        model.setProcListJson(JSON.toJSONString(procList));
    }
    
    public ApServiceInfo selectById(Integer serviceId)  {
    	return apServiceInfoMapper.selectById(serviceId);
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
}
