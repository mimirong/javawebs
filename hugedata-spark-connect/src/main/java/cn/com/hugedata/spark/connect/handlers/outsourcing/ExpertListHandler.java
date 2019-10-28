package cn.com.hugedata.spark.connect.handlers.outsourcing;

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
import cn.com.hugedata.spark.commons.storage.entity.OsExpertsList;
import cn.com.hugedata.spark.commons.storage.mapper.OsExpertsListMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class ExpertListHandler extends BaseHandler {
   
    private Integer start;
    private Integer length;
    private String professionFieldId;
    private String searchWord;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpertListHandler.class);

    @Autowired
    private OsExpertsListMapper osExpertsListMapper;


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
        Map<String, Object> queryMap = MyBatisUtils.buildParameterMap(
                OsExpertsList.Fields.PROFESSION_FIELD_ID, professionFieldId,
                OsExpertsList.Fields.IS_VISIBLE, true,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsExpertsList.Fields.SORT_INDEX, OrderItem.ASC),
                        new OrderItem(OsExpertsList.Fields.EXPERT_ID, OrderItem.DESC))
        );

        if(StringUtils.isNotEmpty(searchWord)) {
            queryMap.put("like_keyWord",searchWord);
        }
        List<OsExpertsList> data = osExpertsListMapper.selectByMap(queryMap, new RowBounds(start, length));
        JSONObject resp = new JSONObject();
        resp.put("experts", data);
        resp.put("start", start);
        resp.put("length", length);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        start = req.getInteger("start");
        length = req.getInteger("length");
        professionFieldId = req.getString("professionFieldId");
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

   


}
