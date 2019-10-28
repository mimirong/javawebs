package cn.com.hugedata.spark.connect.handlers.outsourcing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.OsTechAchieve;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechAchieveMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class TechDetailHandler extends BaseHandler {


    private Integer achieveId;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TechDetailHandler.class);

    @Autowired
    private OsTechAchieveMapper osTechAchieveMapper;
    
     
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
        JSONObject resp = new JSONObject();
        OsTechAchieve info = osTechAchieveMapper.selectById(achieveId);
        resp.put("info",info);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        achieveId = req.getInteger("achieveId");
    }

    private void checkParameters() throws ServiceException {        
        if (achieveId == null ) {
            LOGGER.error("Parameter [achieveId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-achieveId", "achieveId不能为空");
        }
    }

   
     

}
