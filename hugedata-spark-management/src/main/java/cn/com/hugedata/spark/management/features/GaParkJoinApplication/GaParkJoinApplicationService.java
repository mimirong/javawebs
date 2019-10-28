package cn.com.hugedata.spark.management.features.GaParkJoinApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.MessageService;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinApplication;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinApplicationMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class GaParkJoinApplicationService 
        extends FeatureServiceImpl<GaParkJoinApplication, Integer, GaParkJoinApplicationModel, GaParkJoinApplicationMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GaParkJoinApplicationService.class);
    
    @Autowired
    private GaParkJoinApplicationMapper gaParkJoinApplicationMapper;
    
    @Autowired
    private MessageService messageService;
    
    @Override
    public PageEntity<GaParkJoinApplication> list(Map<String, Object> query, int pageStart, int pageSize,
            List<OrderItem> orders) {
        orders = Arrays.asList(new OrderItem(GaParkJoinApplication.Fields.APPLICATION_ID, OrderItem.DESC));
        return super.list(query, pageStart, pageSize, orders);
    }
    

    public void submitApprove(int applicationId, String approveComment) throws ServiceException {
        GaParkJoinApplication app = gaParkJoinApplicationMapper.selectById(applicationId);
        if (app == null) {
            LOGGER.error("Park join application not found: {}", applicationId);
            throw new ServiceException("ParkJoinApplicationNotFound", "入园申请信息不存在");
        }
        
        if (!app.getStatus().equals(GaParkJoinApplication.STATUS_CREATED)) {
            LOGGER.error("Park join application status is invalid: {}, {}", applicationId, app.getStatus());
            throw new ServiceException("InvalidParkJoinApplicationStatus", "入园申请信息状态不正确");
        }
        
        GaParkJoinApplication update = new GaParkJoinApplication();
        update.setApplicationId(applicationId);
        update.setApproveComment(approveComment);
        update.setStatus(GaParkJoinApplication.STATUS_APPROVED);
        update.setApproverUserId(LoginUtils.getCurrentLogin().getUserId());
        update.setApproverName(LoginUtils.getCurrentLogin().getName());
        update.setApproveTime(new Date());
        gaParkJoinApplicationMapper.updateSelectiveById(update);

        messageService.createSystemMessage(
                UcMessage.TYPE_PARK_JOIN_APPLY,
                "您提交的入园申请已受理", 
                "您提交的入园申请已受理", 
                app.getApplierUserId(), 
                null);
    }
    
    
}
