package cn.com.hugedata.spark.connect.handlers.tech;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceConfig;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceConfigMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class GetConfigHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetConfigHandler.class);
    
    @Autowired
    private OsTechServiceConfigMapper osTechServiceConfigMapper;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        String serviceType = req.getString("serviceType");
        
        if (!OsTechServiceConfig.TYPE_COMPUTING.equals(serviceType)
                && !OsTechServiceConfig.TYPE_STORAGE.equals(serviceType)) {
            LOGGER.error("Invalid service type: {}", serviceType);
            throw new ServiceException("Invalid service type", "服务类型无效");
        }
        
        List<OsTechServiceConfig> configs = osTechServiceConfigMapper.selectByMap(MyBatisUtils.params(
                OsTechServiceConfig.Fields.CONFIG_TYPE, serviceType
        ));

        JSONObject retobj = new JSONObject();
        for (OsTechServiceConfig cfg : configs) {
            String name = cfg.getConfigName();
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
            
            try {
                retobj.put(name, JSON.parse(cfg.getConfigValue()));
            } catch (JSONException e) {
                retobj.put(name, cfg.getConfigValue());
            }
        }
        
        return InterfaceResponse.createSuccessResponse(retobj);
    }
}
