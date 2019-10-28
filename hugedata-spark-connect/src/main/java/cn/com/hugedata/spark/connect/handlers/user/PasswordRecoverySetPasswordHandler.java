package cn.com.hugedata.spark.connect.handlers.user;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserVerificationSession;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserVerificationSessionMapper;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.commons.utils.PasswordHash3;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class PasswordRecoverySetPasswordHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordRecoverySetPasswordHandler.class);
    
    @Autowired
    private UcUserVerificationSessionMapper ucUserVerificationSessionMapper;
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;

    private String paramSessionId;
    private String paramSessionKey;
    private String paramPassword;
    
    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        // 获取请求参数
        paramSessionId = req.getString("sessionId");
        paramSessionKey = req.getString("sessionKey");
        paramPassword = req.getString("password");
        
        // 检查参数
        checkParameters();
        
        // 检查Session
        UcUserVerificationSession session = checkSessionForReset(paramSessionId, paramSessionKey);
        finishSession(session);
        
        // 更新密码
        UcUserInfo update = new UcUserInfo();
        update.setUserId(Integer.parseInt(session.getUserId()));
        update.setPasswordHash(PasswordHash3.hashPassword(paramPassword));
        update.setPasswordKey(PasswordEncryption.randomKey());
        update.setPassword(PasswordEncryption.encrypt(paramPassword, update.getPasswordKey()));
        ucUserInfoMapper.updateSelectiveById(update);
        
        return InterfaceResponse.createSuccessResponse();
    }

    private void checkParameters() throws ServiceException {
        if (StringUtils.isEmpty(paramSessionId)) {
            LOGGER.error("Parameter sessionId is empty.");
            throw new ServiceException("EmptySessionId", "参数不能为空(1)");
        }
        if (StringUtils.isEmpty(paramSessionKey)) {
            LOGGER.error("Parameter sessionKey is empty.");
            throw new ServiceException("EmptySessionKey", "参数不能为空(2)");
        }
        if (StringUtils.isEmpty(paramPassword)) {
            LOGGER.error("Parameter password is empty.");
            throw new ServiceException("EmptyPassword", "密码不能为空");
        }
    }

    /**
     * 验证找回密码Session是否有效.
     * @param sessionId         Session的ID
     * @param sessionKey        Session的Key
     * @return                  如果有效，返回Session信息
     * @throws ServiceException 检查失败 
     */
    private UcUserVerificationSession checkSessionForReset(String sessionId, String sessionKey) throws ServiceException {
        UcUserVerificationSession session = ucUserVerificationSessionMapper.selectById(sessionId);
        if (session == null) {
            LOGGER.error("Session id not found for create user: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-SessionIdNotFound", "验证码无效或已过期，请重新获取");
        }
        if (!session.getSessionKey().equals(sessionKey)) {
            LOGGER.error("Invalid session key for session: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-InvalidSessionKey", "验证码无效或已过期，请重新获取");
        }
        if (!session.getVerificationType().equals(UcUserVerificationSession.TYPE_M_FINDPWD)
                && !session.getVerificationType().equals(UcUserVerificationSession.TYPE_E_FINDPWD)) {
            LOGGER.error("Invalid session type for session: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-InvalidSessionType", "验证码无效或已过期，请重新获取");
        }
        if (session.getValidityTime().getTime() < new Date().getTime()) {
            LOGGER.error("Register session is expired: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-SessionExpired", "验证码无效或已过期，请重新获取");
        }
        if (!session.getStatus().equals(UcUserVerificationSession.STATUS_VERIFIED)) {
            LOGGER.error("Invalid register session status: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-SessionExpired", "验证码无效或已过期，请重新获取");
        }
        return session;
    }
    
    /**
     * 将注册Session完成.
     * @param session 找回密码Session
     */
    private void finishSession(UcUserVerificationSession session) {
        UcUserVerificationSession update = new UcUserVerificationSession();
        update.setSessionId(session.getSessionId());
        update.setStatus(UcUserVerificationSession.STATUS_FINISHED);
        ucUserVerificationSessionMapper.updateSelectiveById(update);
    }
}
