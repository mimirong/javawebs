package cn.com.hugedata.spark.connect.handlers.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class GetWebUrlHandler extends BaseHandler {

    @Autowired
    private UrlManager urlManager;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        String type = req.getString("type");
        
        if (StringUtils.isEmpty(type)) {
            throw new ServiceException("EmptyType", "类型不能为空");
        }
        
        String url = null;
        switch (type.toUpperCase()) {
            case "DATA_APP":
                url = urlManager.getDataAppUrl() + "/";
                break;
            case "OFFICIAL_SITE":
                url = "http://www.ylkjy.com/";
                break;
        }
        
        JSONObject resp = new JSONObject();
        resp.put("url", url);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
