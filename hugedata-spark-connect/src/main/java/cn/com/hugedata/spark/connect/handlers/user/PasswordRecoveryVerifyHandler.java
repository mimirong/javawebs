package cn.com.hugedata.spark.connect.handlers.user;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserVerificationSession;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserVerificationSessionMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class PasswordRecoveryVerifyHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordRecoveryVerifyHandler.class);
    
    @Autowired
    private UcUserVerificationSessionMapper ucUserVerificationSessionMapper;
    
    private String paramSessionId;
    private String paramSessionKey;
    private String paramCode;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        // 获取请求参数
        paramSessionId = req.getString("sessionId");
        paramSessionKey = req.getString("sessionKey");
        paramCode = req.getString("code");
        
        // 检查参数
        checkParameters();
        
        // 验证处理
        UcUserVerificationSession session = checkSessionForVerify(paramSessionId, paramSessionKey, paramCode);
        verifySession(session);
        
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
        if (StringUtils.isEmpty(paramCode)) {
            LOGGER.error("Parameter code is empty.");
            throw new ServiceException("EmptyCode", "参数不能为空(3)");
        }
    }

    /**
     * 验证找回密码Session是否有效.
     * @param sessionId  Session的ID
     * @param sessionKey Session的Key
     * @param code       验证码
     * @return           如果有效，返回Session信息
     * @throws ServiceException 检查失败
     */
    private UcUserVerificationSession checkSessionForVerify(String sessionId, String sessionKey, String code)
            throws ServiceException {
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
        if (!session.getCode().equals(code)) {
            LOGGER.error("Wrong mobile code for session: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-InvalidSessionCode", "验证码无效或已过期，请重新获取");
        }
        if (session.getValidityTime().getTime() < new Date().getTime()) {
            LOGGER.error("Register session is expired: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-SessionExpired", "验证码无效或已过期，请重新获取");
        }
        if (!session.getStatus().equals(UcUserVerificationSession.STATUS_CREATED)
                && !session.getStatus().equals(UcUserVerificationSession.STATUS_VERIFIED)) {
            LOGGER.error("Invalid register session status: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-SessionExpired", "验证码无效或已过期，请重新获取");
        }
        return session;
    }

    /**
     * 将Session更新为已验证状态.
     * @param session  找回密码Session
     */
    private void verifySession(UcUserVerificationSession session) {
        UcUserVerificationSession update = new UcUserVerificationSession();
        update.setSessionId(session.getSessionId());
        update.setStatus(UcUserVerificationSession.STATUS_VERIFIED);
        ucUserVerificationSessionMapper.updateSelectiveById(update);
    }
    
}
