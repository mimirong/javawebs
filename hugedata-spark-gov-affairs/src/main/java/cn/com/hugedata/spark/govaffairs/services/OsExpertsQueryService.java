package cn.com.hugedata.spark.govaffairs.services;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsExpertsList;
import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.storage.mapper.OsExpertsListMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PtTechImageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class OsExpertsQueryService {

    //private static final Logger LOGGER = LoggerFactory.getLogger(OsExpertsQueryService.class);

    @Autowired
    private OsExpertsListMapper osExpertsListMapper;

    /**
     * 查询专家列表.
     * @param start         分页开始记录
     * @param length        每页记录数
     * @return
     */
    public PageEntity<OsExpertsList> listExperts(Integer start, Integer length, String professionFieldId, String searchWord) {

        if(StringUtils.isEmpty(professionFieldId)) {
            professionFieldId = null;
        }

        Map<String, Object> queryMap = MyBatisUtils.buildParameterMap(
                OsExpertsList.Fields.PROFESSION_FIELD_ID, professionFieldId,
                OsExpertsList.Fields.IS_VISIBLE, true,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsExpertsList.Fields.SORT_INDEX, OrderItem.ASC),
                        new OrderItem(OsExpertsList.Fields.EXPERT_ID, OrderItem.DESC))
        );

        if(StringUtils.isNotEmpty(searchWord)) {
            // 模糊查询
//            query.put(OsExpertsList.Query.NAME__LIKE, searchWord);
//            query.put(OsExpertsList.Query.JOB_TITLE__LIKE, searchWord);
//            query.put(OsExpertsList.Query.DEPT__LIKE, searchWord);
//            query.put(OsExpertsList.Query.POSITION__LIKE, searchWord);
//            query.put(OsExpertsList.Query.EXPERT_FIELD__LIKE, searchWord);
//            query.put(OsExpertsList.Query.EXPERT_TITLE__LIKE, searchWord);
//            query.put(OsExpertsList.Query.BRIEF__LIKE, searchWord);

            //OsMeetingTrainingMapper.xml中添加了的"like_keyWord"查询动作，指定查哪些
            queryMap.put("like_keyWord",searchWord);
        }

        List<OsExpertsList> data = osExpertsListMapper.selectByMap(queryMap, new RowBounds(start, length));
        int count = osExpertsListMapper.countByMap(queryMap);

        return new PageEntity<>(data, start, length, count);
    }
    
    /**
     * 查询专家详情.
     */
    public OsExpertsList queryExpertDetail(int expertId) {
        return osExpertsListMapper.selectById(expertId);
    }
}
