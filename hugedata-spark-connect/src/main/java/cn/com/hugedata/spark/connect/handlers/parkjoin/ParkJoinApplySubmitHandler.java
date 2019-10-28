package cn.com.hugedata.spark.connect.handlers.parkjoin;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinApplication;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinApplicationMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class ParkJoinApplySubmitHandler extends BaseHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ParkJoinApplySubmitHandler.class);
    
    @Autowired
    private GaParkJoinApplicationMapper gaParkJoinApplicationMapper;

    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        try {
            GaParkJoinApplication app = new GaParkJoinApplication();
            app.setCompanyName(req.getString("companyName"));
            app.setContact(req.getString("contact"));
            app.setContactMobile(req.getString("contactMobile"));
            app.setStatus(GaParkJoinApplication.STATUS_CREATED);
            app.setApplierUserId(getLogin().getUserId());
            app.setApplierName(getLogin().getName());
            app.setCreateTime(new Date());
            app.setContent(req.toJSONString());
            gaParkJoinApplicationMapper.insertSelective(app);
            
            return InterfaceResponse.createSuccessResponse();
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse park join apply json.", e);
            throw new ServiceException("InvalidJsonData", "解析数据失败");
        }
    }
}
