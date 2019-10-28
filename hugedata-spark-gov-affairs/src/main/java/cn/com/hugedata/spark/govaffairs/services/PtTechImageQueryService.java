package cn.com.hugedata.spark.govaffairs.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.storage.mapper.PtTechImageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

@Service
public class PtTechImageQueryService {

    //private static final Logger LOGGER = LoggerFactory.getLogger(PtTechImageQueryService.class);

    @Autowired
    private PtTechImageMapper ptTechImageMapper;

    /**
     * 查询图片.
     * @param categoryId    栏目ID
     * @param start         分页开始记录
     * @param length        每页记录数
     * @return
     */
    public PageEntity<PtTechImage> listTechImages(String categoryId, Integer start, Integer length) {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PtTechImage.Fields.CATEGORY_ID, categoryId,
                PtTechImage.Fields.IS_VISIBLE, true,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PtTechImage.Fields.SORT_INDEX, OrderItem.ASC),
                        new OrderItem(PtTechImage.Fields.IMAGE_ID, OrderItem.DESC))
        );
        List<PtTechImage> data = ptTechImageMapper.selectByMap(query, new RowBounds(start, length));
        int count = ptTechImageMapper.countByMap(query);

        return new PageEntity<>(data, start, length, count);
    }
    
    /**
     * 查询一个技术服务图片.
     */
    public PtTechImage queryImage(int imageId) {
        return ptTechImageMapper.selectById(imageId);
    }
}
