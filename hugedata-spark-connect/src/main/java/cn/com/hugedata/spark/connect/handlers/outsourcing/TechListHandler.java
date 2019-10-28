package cn.com.hugedata.spark.connect.handlers.outsourcing;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsTechAchieve;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechAchieveMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class TechListHandler extends BaseHandler {
   
    private Integer start;
    private Integer length;
    private String professionFieldId;
    private String achieveTypeId;
    private String investmentValumeScope;
    private String searchWord;
    
    
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TechListHandler.class);

    @Autowired
    private OsTechAchieveMapper osTechAchieveMapper;


    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    @Override
    public InterfaceResponse execute(JSONObject requestData)
            throws ServiceException
    {
        parseParameters(requestData);
        checkParameters();
        
        
        
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
                data = dataResult.subList(start, start + length);
            }else if(dataResult.size() > start && dataResult.size() <= (start + length)){
                data = dataResult.subList(start, dataResult.size());
            }else{
                data = new ArrayList<OsTechAchieve>(); 
            }
        }
        
        
        JSONObject resp = new JSONObject();
        resp.put("techs", data);
        resp.put("start", start);
        resp.put("length", length);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        start = req.getInteger("start");
        length = req.getInteger("length");
        professionFieldId = req.getString("professionFieldId");
        achieveTypeId = req.getString("achieveTypeId");
        investmentValumeScope = req.getString("investmentValumeScope");
        searchWord = req.getString("searchWord");
    }

    private void checkParameters() throws ServiceException {
        
        if (start == null ) {
            LOGGER.error("Parameter [start] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-start", "start不能为空");
        }
        
        if (length == null ) {
            LOGGER.error("Parameter [length] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-length", "length不能为空");
        }
        
    }

   
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
