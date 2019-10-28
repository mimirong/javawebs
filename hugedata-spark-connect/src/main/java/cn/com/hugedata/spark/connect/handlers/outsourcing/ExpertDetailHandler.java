package cn.com.hugedata.spark.connect.handlers.outsourcing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.OsExpertsList;
import cn.com.hugedata.spark.commons.storage.mapper.OsExpertsListMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

import com.alibaba.fastjson.JSONObject;

public class ExpertDetailHandler extends BaseHandler {


    private Integer expertId;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpertDetailHandler.class);

    @Autowired
    private OsExpertsListMapper osExpertsListMapper;
    
     
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
        OsExpertsList info = osExpertsListMapper.selectById(expertId);
        resp.put("info",info);
        return InterfaceResponse.createSuccessResponse(resp);
    }

    private void parseParameters(JSONObject req) {
        expertId = req.getInteger("expertId");
    }

    private void checkParameters() throws ServiceException {        
        if (expertId == null ) {
            LOGGER.error("Parameter [expertId] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-expertId", "expertId不能为空");
        }
    }

   
     

}
