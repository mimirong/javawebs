package cn.com.hugedata.spark.connect.handlers.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.service.email.SendEmailService;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserVerificationSession;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserVerificationSessionMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class PasswordRecoverySendCodeHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordRecoverySendCodeHandler.class);
    
    /** 验证码有效时间（分）. */
    private static final int SESSION_VALIDITY_TIME_IN_MINUTES = 15;
    
    @Autowired
    private UcUserVerificationSessionMapper ucUserVerificationSessionMapper;
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;

    @Autowired
    private UrlManager urlManager;
    
    @Autowired
    private SendEmailService sendEmailService;
    
    private String paramEmail;
    
    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        // 获取请求参数
        paramEmail = req.getString("email");
        
        // 检查参数
        checkParameters();
        
        // 发送邮件
        UcUserInfo user = queryUserByEmail(paramEmail);
        UcUserVerificationSession session = createVerificationSession(
                UcUserVerificationSession.TYPE_E_FINDPWD, null, paramEmail, user);
        sendVerificationEmail(session);
        
        // 返回
        JSONObject resp = new JSONObject();
        resp.put("sessionId", session.getSessionId());
        resp.put("sessionKey", session.getSessionKey());
        return InterfaceResponse.createSuccessResponse(resp);
    }

    /**
     * 检查参数.
     */
    private void checkParameters() throws ServiceException {
        if (StringUtils.isEmpty(paramEmail)) {
            LOGGER.error("Email is empty.");
            throw new ServiceException("EmptyEmail", "邮箱不能为空");
        }
    }

    /**
     * 创建验证Session.
     * @param type   发送类型 email/mobile
     * @param email  发送的邮箱
     * @param mobile 发送的手机号码
     * @param user   用户信息
     * @return       创建的Session
     */
    private UcUserVerificationSession createVerificationSession(String type, String mobile, String email, UcUserInfo user) {
        UcUserVerificationSession session = new UcUserVerificationSession();
        session.setSessionId(generateSessionId());
        session.setSessionKey(RandomStringUtils.randomAlphanumeric(32));
        session.setCode(RandomStringUtils.randomNumeric(6));
        if (type.equals("mobile")) {
            session.setVerificationType(UcUserVerificationSession.TYPE_M_FINDPWD);
            session.setMobile(mobile);
        } else {
            session.setVerificationType(UcUserVerificationSession.TYPE_E_FINDPWD);
            session.setEmail(email);
        }
        session.setStatus(UcUserVerificationSession.STATUS_CREATED);
        session.setCreateTime(new Date());
        session.setValidityTime(DateUtils.addMinutes(new Date(), SESSION_VALIDITY_TIME_IN_MINUTES));
        session.setUserId(String.valueOf(user.getUserId()));
        session.setExtraData(null);
        session.setFinishTime(null);
        ucUserVerificationSessionMapper.insertSelective(session);
        return session;
    }
    
    /**
     * 发送验证邮件.
     * @param session 短信验证Session
     */
    private void sendVerificationEmail(final UcUserVerificationSession session) throws ServiceException {
        final String umsUrl = urlManager.getUmsUrl();
        final String portalUrl = urlManager.getGovAffairsPortalUrl();
        
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", session.getCode());
            data.put("umsUrl", umsUrl);
            data.put("portalUrl", portalUrl);
            sendEmailService.sendEmail(session.getEmail(), "password_recovery_email.ftl", data);
            LOGGER.info("Successfully sent password recovery email to {}", session.getEmail());
        } catch (ServiceException e) {
            LOGGER.error("Failed to send password recovery email.", e);
        }
    }

    /**
     * 根据邮箱查询用户信息，如果邮箱不存在，抛出异常.
     * @param email             需要查询的邮箱
     * @return                  用户信息
     * @throws ServiceException 邮箱不存在
     */
    private UcUserInfo queryUserByEmail(String email) throws ServiceException {
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.EMAIL, email
        ));
        if (list == null || list.isEmpty()) {
            LOGGER.error("User email not found: {}", email);
            throw new ServiceException("PasswordRecoveryService-EmailNotFound", "邮箱不存在");
        }
        return list.get(0);
    }

    /**
     * 生成SessionId.
     * @return Session id
     */
    private String generateSessionId() {
        return "MR" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) 
                + RandomStringUtils.randomAlphanumeric(13);
    }
}
