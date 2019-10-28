package cn.com.hugedata.spark.connect.handlers.tech;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceSpec;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceSpecMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class GetSpecsHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetSpecsHandler.class);
    
    @Autowired
    private OsTechServiceSpecMapper osTechServiceSpecMapper;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        String specType = req.getString("specType");
        
        if (!OsTechServiceSpec.TYPE_COMPUTING.equals(specType)) {
            LOGGER.error("Invalid spec type: {}", specType);
            throw new ServiceException("Invalid spec type", "套餐类型无效");
        }
        
        List<OsTechServiceSpec> specs = osTechServiceSpecMapper.selectByMap(MyBatisUtils.params(
                OsTechServiceSpec.Fields.SPEC_TYPE, specType,
                new OrderItem(OsTechServiceSpec.Fields.SPEC_ID, OrderItem.ASC)
        ));

        List<JSONObject> retlist = new ArrayList<>();
        for (OsTechServiceSpec spec : specs) {
            JSONObject obj = JSON.parseObject(spec.getSpecData());
            obj.put("specName", spec.getSpecName());
            obj.put("specId", spec.getSpecId());
            obj.put("price", spec.getPrice());
            retlist.add(obj);
        }
        
        JSONObject resp = new JSONObject();
        resp.put("list", retlist);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
