package cn.com.hugedata.spark.management.features.OsLinkUrlMan;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.storage.mapper.PtTechImageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OsLinkUrlManService
        extends FeatureServiceImpl<PtTechImage, Integer, OsLinkUrlManModel, PtTechImageMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsLinkUrlManService.class);

    @Autowired
    private PtTechImageMapper ptTechImageMapper;

    @Override
    public PageEntity<PtTechImage> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        query.put(PtTechImage.Query.CATEGORY_ID__IN,
                Arrays.asList(PtTechImage.TYPE_LINK_URL_INTELLECTUAL_SERVICE_GUIDE,
                        PtTechImage.TYPE_LINK_URL_INTELLECTUAL_DATA_SERVICE,
                        PtTechImage.TYPE_LINK_URL_CREDIT_SYSTEM_COM_INFO_QUERY,
                        PtTechImage.TYPE_LINK_URL_CREDIT_SYSTEM_COM_INFO_SUBMIT));

        orders = Arrays.asList(new OrderItem(PtTechImage.Fields.IMAGE_ID, OrderItem.DESC));

        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public PtTechImage add(OsLinkUrlManModel model) throws ServiceException {
        model.setCreateTime(new Date());
        model.setIsVisible(true);

        checkCategoryExist(model.getCategoryId());

        return super.add(model);
    }

    /**
     * 判断指定类别的链接是否已经存在.
     * @param categoryId    类别ID
     */
    private void checkCategoryExist(String categoryId) throws ServiceException {
        if (StringUtils.isEmpty(categoryId)) {
            return;
        }
        List<PtTechImage> list = ptTechImageMapper.selectByMap(MyBatisUtils.buildParameterMap(
                PtTechImage.Fields.CATEGORY_ID, categoryId
        ));
        if (list != null && !list.isEmpty()) {
            LOGGER.error("Category exists.");
            throw new ServiceException("OsLinkUrlManService-CategoryExists", "该类别的链接已存在，不可重复添加。");
        }
    }

}
