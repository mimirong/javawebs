package cn.com.hugedata.spark.govaffairs.services;

import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.INVESTMENT_VOLUME_SCOPE_1;
import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.INVESTMENT_VOLUME_SCOPE_2;
import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.INVESTMENT_VOLUME_SCOPE_3;
import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.INVESTMENT_VOLUME_SCOPE_4;
import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.MONETARY_UNIT_MILLION_YUAN;
import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.MONETARY_UNIT_TEN_THOUSND_YUAN;
import static cn.com.hugedata.spark.commons.storage.constant.OsTechAchieveConstants.MONETARY_UNIT_YUAN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsTechAchieve;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechAchieveMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

@Service
public class OsTechAchieveQueryService {

    //private static final Logger LOGGER = LoggerFactory.getLogger(OsTechAchieveMapper.class);

    @Autowired
    private OsTechAchieveMapper osTechAchieveMapper;

    /**
     * 查询技术成果列表.
     * @param start         分页开始记录
     * @param length        每页记录数
     * @return
     */
    public PageEntity<OsTechAchieve> listTechAchieve(Integer start,
                                                     Integer length,
                                                     String professionFieldId,
                                                     String achieveTypeId,
                                                     String investmentValumeScope,
                                                     String searchWord) {

        if(StringUtils.isEmpty(professionFieldId)) {
            professionFieldId = null;
        }
        if(StringUtils.isEmpty(achieveTypeId)) {
            achieveTypeId = null;
        }

        Map<String, Object> queryMap = MyBatisUtils.buildParameterMap(
                OsTechAchieve.Fields.PROFESSION_FIELD_ID, professionFieldId,
                OsTechAchieve.Fields.ACHIEVE_TYPE, achieveTypeId,
                OsTechAchieve.Fields.IS_VISIBLE, true,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsTechAchieve.Fields.SORT_INDEX, OrderItem.ASC),
                        new OrderItem(OsTechAchieve.Fields.ACHIEVE_ID, OrderItem.DESC))
        );

        if(StringUtils.isNotEmpty(searchWord)) {
            // 模糊查询
//            query.put(OsTechAchieve.Query.NAME__LIKE, searchWord);

            //OsMeetingTrainingMapper.xml中添加了的"like_keyWord"查询动作，指定查哪些
            queryMap.put("like_keyWord",searchWord);
        }

        List<OsTechAchieve> data = osTechAchieveMapper.selectByMap(queryMap, new RowBounds(start, length));
        int count = osTechAchieveMapper.countByMap(queryMap);

        // 如果用户存在的投资金额筛选标签，那么在这里做单位换算，并且过滤结果
        List<OsTechAchieve> dataResult = new ArrayList<OsTechAchieve>();

        if (StringUtils.isNotEmpty(investmentValumeScope)) {
            data = osTechAchieveMapper.selectByMap(queryMap);
            for(OsTechAchieve item : data) {
                long volumeYuan = (long)item.getInvestmentVolume() * (long)convertMonetaryUnit2Yuan(item.getMonetaryUnit());

                switch (investmentValumeScope) {
                    case INVESTMENT_VOLUME_SCOPE_1:
                        if(volumeYuan < 100000l) {
                            dataResult.add(item);
                        }
                        break;
                    case INVESTMENT_VOLUME_SCOPE_2:
                        if(volumeYuan >= 100000l && volumeYuan < 500000l) {
                            dataResult.add(item);
                        }
                        break;
                    case INVESTMENT_VOLUME_SCOPE_3:
                        if(volumeYuan >= 500000l && volumeYuan < 1000000l) {
                            dataResult.add(item);
                        }
                        break;
                    case INVESTMENT_VOLUME_SCOPE_4:
                        if(volumeYuan >= 1000000l) {
                            dataResult.add(item);
                        }
                        break;
                    default:
                        break;
                }
            }
            
            if(dataResult.size() > (start + length)){
                return new PageEntity<>(dataResult.subList(start, start + length), start, length, dataResult.size());
            }else if(dataResult.size() > start && dataResult.size() <= (start + length)){
                return new PageEntity<>(dataResult.subList(start, dataResult.size()), start, length, dataResult.size());
            }
            return new PageEntity<>(new ArrayList<OsTechAchieve>(), start, length, dataResult.size());
        }
        else {
            return new PageEntity<>(data, start, length, count);
        }
    }

    /**
     * 查询技术成果详情.
     */
    public OsTechAchieve queryTechAchieveDetail(int achieveId) {
        return osTechAchieveMapper.selectById(achieveId);
    }

    /**
     * 将货币单位转换成元.
     */
    private int convertMonetaryUnit2Yuan(String monetaryUnit) {
        int unit_yuan = 0;
        switch (monetaryUnit) {
            case MONETARY_UNIT_YUAN:
                unit_yuan = 1;
                break;
            case MONETARY_UNIT_TEN_THOUSND_YUAN:
                unit_yuan = 10000;
                break;
            case MONETARY_UNIT_MILLION_YUAN:
                unit_yuan = 1000000;
                break;
            default:
                unit_yuan = 1;
                break;
        }
        return unit_yuan;
    }

}
