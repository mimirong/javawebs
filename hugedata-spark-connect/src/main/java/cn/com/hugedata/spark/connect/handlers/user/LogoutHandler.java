package cn.com.hugedata.spark.connect.handlers.user;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.push.JiguangPushDeviceService;
import cn.com.hugedata.spark.commons.service.session.LoginSessionService;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

/**
 * 退出登录，用于清除推送记录.
 */
public class LogoutHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutHandler.class);
    
    @Autowired
    private JiguangPushDeviceService pushDeviceService;
    
    @Autowired
    private LoginSessionService loginSessionService;
    
    @Override
    public InterfaceResponse execute(JSONObject requestData) throws ServiceException {
        // 获取参数
        String registrationId = requestData.getString("registrationId");
        if (StringUtils.isEmpty(registrationId)) {
            LOGGER.error("Parameter [registrationId] is empty");
            throw new ServiceException("ParamIsEmpty", "参数[registrationId]不能为空");
        }
        
        // 删除推送登记
        pushDeviceService.removeDevice(registrationId);

        // 删除LoginSession
        if (StringUtils.isNotEmpty(getLoginToken())) {
            loginSessionService.deleteToken(getLoginToken());
        }
        
        return InterfaceResponse.createSuccessResponse();
    }
    
}
