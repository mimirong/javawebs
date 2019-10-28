package cn.com.hugedata.spark.connect.handlers.tech;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceApply;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceConfig;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceApplyMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class QueryServiceApplyHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryServiceApplyHandler.class);

    @Autowired
    private OsTechServiceApplyMapper osTechServiceApplyMapper;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        String serviceType = req.getString("serviceType");
        Integer start = req.getInteger("start");
        Integer length = req.getInteger("length");
        
        if (StringUtils.isNotEmpty(serviceType) 
                && !OsTechServiceConfig.TYPE_COMPUTING.equals(serviceType)
                && !OsTechServiceConfig.TYPE_STORAGE.equals(serviceType)) {
            LOGGER.error("Invalid service type: {}", serviceType);
            throw new ServiceException("Invalid service type", "服务类型无效");
        }
        if (start == null) {
            start = 0;
        }
        if (length == null) {
            length = 10;
        }
        
        Map<String, Object> query = MyBatisUtils.params(
                OsTechServiceApply.Fields.APPLIER_ID, getLogin().getUserId(),
                OsTechServiceApply.Fields.APPLY_TYPE, serviceType,
                new OrderItem(OsTechServiceApply.Fields.APPLY_ID, OrderItem.DESC)
        );
        
        List<OsTechServiceApply> data = osTechServiceApplyMapper.selectByMap(query, new RowBounds(start, length));
        int count = osTechServiceApplyMapper.countByMap(query);
        
        List<JSONObject> retlist = new ArrayList<>();
        for (OsTechServiceApply apply : data) {
            JSONObject obj = (JSONObject) JSON.toJSON(apply);
            obj.remove("idPropertyName");
            obj.remove("idValue");
            obj.remove("contactName");
            obj.remove("contactMobile");
            obj.remove("companyName");
            obj.remove("applierId");
            obj.remove("applierName");
            obj.remove("extraData");
            if (StringUtils.isNotEmpty(apply.getExtraData())) {
                obj.put("specs", JSON.parse(apply.getExtraData()));
            }
            retlist.add(obj);
        }
        
        JSONObject resp = new JSONObject();
        resp.put("count", count);
        resp.put("list", retlist);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
