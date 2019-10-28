package cn.com.hugedata.spark.management.features.OsTechServiceApply;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceApply;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceApplyMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;

@Service
public class OsTechServiceApplyService
        extends FeatureServiceImpl<OsTechServiceApply, Integer, OsTechServiceApplyModel, OsTechServiceApplyMapper> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OsTechServiceApplyService.class);
    
    @Autowired
    private OsTechServiceApplyMapper osTechServiceApplyMapper;

    @Override
    public PageEntity<OsTechServiceApply> list(Map<String, Object> query, int pageStart, int pageSize,
            List<OrderItem> orders) {
        orders = new ArrayList<>();
        orders.add(new OrderItem(OsTechServiceApply.Fields.APPLIER_ID, OrderItem.DESC));
        return super.list(query, pageStart, pageSize, orders);
    }

    public void approve(int applyId) throws ServiceException {
        OsTechServiceApply apply = osTechServiceApplyMapper.selectById(applyId);
        if (apply == null) {
            LOGGER.error("Apply id not found: {}", applyId);
            throw new ServiceException("ApplyIdNotFound", "申请信息不存在");
        }
        
        if (!OsTechServiceApply.STATUS_CREATED.equals(apply.getStatus())) {
            LOGGER.error("Invalid status to approve: {}", apply.getStatus());
            throw new ServiceException("InvalidStatusToApprove", "申请状态不正确");
        }
        
        OsTechServiceApply update = new OsTechServiceApply();
        update.setApplyId(applyId);
        update.setStatus(OsTechServiceApply.STATUS_APPROVED);
        osTechServiceApplyMapper.updateSelectiveById(update);
    }
}
