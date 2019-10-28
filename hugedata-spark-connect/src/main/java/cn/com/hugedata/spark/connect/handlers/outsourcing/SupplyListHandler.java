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
import cn.com.hugedata.spark.commons.storage.entity.SpServiceProject;
import cn.com.hugedata.spark.commons.storage.mapper.SpServiceProjectMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class SupplyListHandler extends BaseHandler {

   
    private Integer start;
    private Integer length;
    private String supplyType;
    private String sortType;
    private String serviceField;
    private String searchWord;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplyListHandler.class);

    @Autowired
    private SpServiceProjectMapper spServiceProjectMapper;


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
        Map<String, Object> query = MyBatisUtils.buildParameterMap();
        
        if (!StringUtils.isEmpty(supplyType)) {
            query.put(SpServiceProject.Fields.SUPPLY_TYPE, supplyType);
        }
        
        if (!StringUtils.isEmpty(serviceField)) {
            query.put(SpServiceProject.Fields.SERVICE_FIELD, serviceField);
        }
        
        if (StringUtils.isEmpty(sortType)) {
            sortType = SpServiceProject.QUERY_SORT_LATEST_PUBLISH;
        }
        
        if (SpServiceProject.QUERY_SORT_VISITOR_COUNT.equals(sortType)) {
            query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(SpServiceProject.Fields.VISITOR_COUNT, OrderItem.DESC)));
        } else if (SpServiceProject.QUERY_SORT_LATEST_PUBLISH.equals(sortType)) {
            query.put(OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(SpServiceProject.Fields.CREATE_TIME, OrderItem.DESC)));
        }
        if (!StringUtils.isEmpty(searchWord)) {
            query.put(SpServiceProject.Query.SEARCH_KEY__LIKE, searchWord);
        }
        
        
        List<SpServiceProject> data = spServiceProjectMapper.selectByMap(query, new RowBounds(start, length));
        
        JSONObject resp = new JSONObject();
        resp.put("supplies", data);
        resp.put("start", start);
        resp.put("length", length);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        start = req.getInteger("start");
        length = req.getInteger("length");
        supplyType = req.getString("supplyType");
        serviceField = req.getString("serviceField");
        sortType = req.getString("sortType");
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
