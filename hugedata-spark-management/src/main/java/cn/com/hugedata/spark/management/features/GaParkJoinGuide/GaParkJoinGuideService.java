package cn.com.hugedata.spark.management.features.GaParkJoinGuide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinGuideMapper;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class GaParkJoinGuideService
        extends FeatureServiceImpl<GaParkJoinGuide, Integer, GaParkJoinGuideModel, GaParkJoinGuideMapper> {

    @Override
    public PageEntity<GaParkJoinGuide> list(Map<String, Object> query, int pageStart, int pageSize,
            List<OrderItem> orders) {
        orders = Arrays.asList(new OrderItem(GaParkJoinGuide.Fields.GUIDE_ID, OrderItem.DESC));
        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public GaParkJoinGuide add(GaParkJoinGuideModel model) throws ServiceException {
        parseDate(model);
        model.setCreatorUserId(LoginUtils.getCurrentLogin().getUserId());
        model.setIsVisible(true);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return super.add(model);
    }

    @Override
    public void modify(GaParkJoinGuideModel model) throws ServiceException {
        parseDate(model);
        model.setUpdateTime(new Date());
        super.modify(model);
    }

    /**
     * 处理Model中的日期.
     */
    private void parseDate(GaParkJoinGuideModel model) throws ServiceException {
        try {
            if (StringUtils.isNotEmpty(model.getPublishTimeStr())) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                model.setPublishTime(fmt.parse(model.getPublishTimeStr()));
            }
        } catch (ParseException e) {
            throw new ServiceException("InvalidDateFormat", "发布日期不正确");
        }
    }

}
