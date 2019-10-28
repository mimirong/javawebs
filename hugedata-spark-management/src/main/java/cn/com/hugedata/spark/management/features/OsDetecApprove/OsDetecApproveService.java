package cn.com.hugedata.spark.management.features.OsDetecApprove;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.constant.DdDetectionApplyConstants;
import cn.com.hugedata.spark.commons.storage.entity.DdDetectionApply;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.storage.entity.PtCategory;
import cn.com.hugedata.spark.commons.storage.mapper.DdDetectionApplyMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OsDetecApproveService
        extends FeatureServiceImpl<DdDetectionApply, Integer, OsDetecApproveModel, DdDetectionApplyMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsDetecApproveService.class);

    @Autowired
    private DdDetectionApplyMapper ddDetectionApplyMapper;

    @Override
    public PageEntity<DdDetectionApply> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {

        orders = Arrays.asList(new OrderItem(DdDetectionApply.Fields.APPLY_ID, OrderItem.DESC),
                new OrderItem(DdDetectionApply.Fields.APPLY_TYPE, OrderItem.DESC));

        return super.list(query, pageStart, pageSize, orders);
    }

    /**
     * 提交审批
     * @param applyId
     * @param approveComment
     * @return
     */
    public void submitApprove(Integer applyId, String approveComment) throws ServiceException{
        DdDetectionApply ddDetectionApply = new DdDetectionApply();
        ddDetectionApply.setApplyId(applyId);
        ddDetectionApply.setStatus(DdDetectionApplyConstants.STATUS_FINISHED);
        ddDetectionApply.setApproverUserId(LoginUtils.getCurrentLogin().getUserId());
        ddDetectionApply.setApproverName(LoginUtils.getCurrentLogin().getName());
        ddDetectionApply.setApproveComment(approveComment);
        ddDetectionApply.setApproveTime(new Date());
        ddDetectionApplyMapper.updateSelectiveById(ddDetectionApply);
    }

}
