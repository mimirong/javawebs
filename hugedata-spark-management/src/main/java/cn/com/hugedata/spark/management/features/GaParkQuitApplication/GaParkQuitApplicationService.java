package cn.com.hugedata.spark.management.features.GaParkQuitApplication;

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
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitApplication;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkQuitApplicationMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class GaParkQuitApplicationService extends
        FeatureServiceImpl<GaParkQuitApplication, Integer, GaParkQuitApplicationModel, GaParkQuitApplicationMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GaParkQuitApplicationService.class);

    @Autowired
    private GaParkQuitApplicationMapper gaParkQuitApplicationMapper;
    
    @Autowired
    private MessageService messageService;

    @Override
    public PageEntity<GaParkQuitApplication> list(Map<String, Object> query, int pageStart, int pageSize,
            List<OrderItem> orders) {
        orders = Arrays.asList(new OrderItem(GaParkQuitApplication.Fields.APPLICATION_ID, OrderItem.DESC));
        return super.list(query, pageStart, pageSize, orders);
    }

    public void submitApprove(int applicationId, String approveComment) throws ServiceException {
        GaParkQuitApplication app = gaParkQuitApplicationMapper.selectById(applicationId);
        if (app == null) {
            LOGGER.error("Park Quit application not found: {}", applicationId);
            throw new ServiceException("ParkQuitApplicationNotFound", "退园申请信息不存在");
        }

        if (!app.getStatus().equals(GaParkQuitApplication.STATUS_CREATED)) {
            LOGGER.error("Park Quit application status is invalid: {}, {}", applicationId, app.getStatus());
            throw new ServiceException("InvalidParkQuitApplicationStatus", "退园申请信息状态不正确");
        }

        GaParkQuitApplication update = new GaParkQuitApplication();
        update.setApplicationId(applicationId);
        update.setApproveComment(approveComment);
        update.setStatus(GaParkQuitApplication.STATUS_APPROVED);
        update.setApproverUserId(LoginUtils.getCurrentLogin().getUserId());
        update.setApproverName(LoginUtils.getCurrentLogin().getName());
        update.setApproveTime(new Date());
        gaParkQuitApplicationMapper.updateSelectiveById(update);

        messageService.createSystemMessage(
                UcMessage.TYPE_PARK_QUIT_APPLY,
                "您提交的退园申请已受理", 
                "您提交的退园申请已受理", 
                app.getApplierUserId(), 
                null);
    }

}
