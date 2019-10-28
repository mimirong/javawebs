package cn.com.hugedata.spark.connect.handlers.serviceGuide;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class ListGuidesHandler extends BaseHandler {


    private Integer start;
    
    private Integer length;
    
    private String deptCode;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ListGuidesHandler.class);

    @Autowired
    private GaServiceGuideMapper gaServiceGuideMapper;


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
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                GaServiceGuide.Fields.DEPT_CODE, deptCode,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(GaServiceGuide.Fields.GUIDE_ID, OrderItem.DESC))
        );
        List<GaServiceGuide> data = gaServiceGuideMapper.selectByMap(query, new RowBounds(start, length));
        
        JSONObject resp = new JSONObject();
        resp.put("guides", data);
        resp.put("start", start);
        resp.put("length", length);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        start = req.getInteger("start");
        length = req.getInteger("length");
        deptCode = req.getString("deptCode");
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
        
        if (deptCode == null ) {
            LOGGER.error("Parameter [deptCode] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-deptCode", "deptCode不能为空");
        }
        
    }

   


}
