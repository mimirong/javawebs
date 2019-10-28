package cn.com.hugedata.spark.connect.handlers.outsourcing;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireInfo;
import cn.com.hugedata.spark.commons.storage.mapper.OsRequireInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class RequireListHandler extends BaseHandler {

   
    private Integer start;
    private Integer length;
    private String orderType;
    private String requireArea;
    private String searchWord;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RequireListHandler.class);

    @Autowired
    private OsRequireInfoMapper osRequireMapper;


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
        Date now = new Date();
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                OsRequireInfo.Fields.DELETED,"0",
                OsRequireInfo.Query.DEAD_DATE__BEGIN,now,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsRequireInfo.Fields.PUBLISH_TIME, OrderItem.DESC))
        );
        
        if("按浏览量".equals(orderType)){
            query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsRequireInfo.Fields.READ_NUM, OrderItem.DESC),new OrderItem(OsRequireInfo.Fields.PUBLISH_TIME, OrderItem.DESC)));
        }else{
            query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(OsRequireInfo.Fields.PUBLISH_TIME, OrderItem.DESC),new OrderItem(OsRequireInfo.Fields.READ_NUM, OrderItem.DESC)));
        }

        if(StringUtils.isNotEmpty(searchWord)) {
            query.put(OsRequireInfo.Query.KEY_WORD__LIKE, searchWord);
        }
        
        if(StringUtils.isNotEmpty(requireArea)) {
            query.put(OsRequireInfo.Fields.REQUIRE_AREA, requireArea);
        }
        
        List<OsRequireInfo> data = osRequireMapper.selectByMap(query, new RowBounds(start, length));
        
        JSONObject resp = new JSONObject();
        resp.put("requires", data);
        resp.put("start", start);
        resp.put("length", length);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        start = req.getInteger("start");
        length = req.getInteger("length");
        orderType = req.getString("orderType");
        requireArea = req.getString("requireArea");
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
