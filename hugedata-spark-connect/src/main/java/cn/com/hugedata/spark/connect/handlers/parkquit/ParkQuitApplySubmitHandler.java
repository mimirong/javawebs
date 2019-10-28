package cn.com.hugedata.spark.connect.handlers.parkquit;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitApplication;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkQuitApplicationMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class ParkQuitApplySubmitHandler extends BaseHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ParkQuitApplySubmitHandler.class);
    
    @Autowired
    private GaParkQuitApplicationMapper gaParkQuitApplicationMapper;

    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        try {
            GaParkQuitApplication app = new GaParkQuitApplication();
            app.setCompanyName(req.getString("companyName"));
            app.setStatus(GaParkQuitApplication.STATUS_CREATED);
            app.setApplierUserId(getLogin().getUserId());
            app.setApplierName(getLogin().getName());
            app.setCreateTime(new Date());
            app.setContent(req.toJSONString());
            gaParkQuitApplicationMapper.insertSelective(app);
            
            return InterfaceResponse.createSuccessResponse();
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse park quit apply json.", e);
            throw new ServiceException("InvalidJsonData", "解析数据失败");
        }
    }
}
