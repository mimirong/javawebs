package cn.com.hugedata.spark.ums.services.user;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.service.email.SendEmailService;
import cn.com.hugedata.spark.commons.service.sms.SmsSendService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserVerificationSession;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserVerificationSessionMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.commons.utils.PasswordHash3;

/**
 * 找回密码功能.
 */
@Service
public class PasswordRecoveryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordRecoveryService.class);
    
    /** 短信验证码有效时间（分）. */
    private static final int SESSION_VALIDITY_TIME_IN_MINUTES = 15;
    
    @Autowired
    private UcUserVerificationSessionMapper ucUserVerificationSessionMapper;
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private SmsSendService smsSendService;
    
    @Autowired
    private SendEmailService sendEmailService;
    
    @Autowired
    private UrlManager urlManager;

    /** 同一手机号码每天最多发送短信数量. */
    @Value("${sms.maxSmsPerDay}")
    private int paramMaxSmsPerDay;

    /** 最短发送间隔（单位为秒）. */
    @Value("${sms.smsSendInterval}")
    private int paramSmsSendInterval;
    
    /**
     * 发送注册短信验证码.
     * @param type              发送类型 email/mobile
     * @param email             发送的邮箱
     * @param mobile            发送的手机号码
     * @throws ServiceException 发送失败
     * @return                  验证Session的信息
     */
    public UcUserVerificationSession sendVerificationCode(String type, String email, String mobile) 
            throws ServiceException {
        switch (type) {
            case "email":
                {
                    if (StringUtils.isEmpty(email)) {
                        throw new ServiceException("PasswordRecoveryService-EmailIsEmpty", "邮箱不能为空");
                    }
                    UcUserInfo user = queryUserByEmail(email);
                    UcUserVerificationSession session = createVerificationSession(type, mobile, email, user);
                    sendVerificationEmail(session);
                    return session;
                }
                
            case "mobile":
                {
                    if (StringUtils.isEmpty(mobile)) {
                        throw new ServiceException("PasswordRecoveryService-MobileIsEmpty", "手机号码不能为空");
                    }
                    UcUserInfo user = queryUserByMobile(mobile);
                    checkMaxSmsSend(mobile);
                    checkMinSendInterval(mobile);
                    UcUserVerificationSession session = createVerificationSession(type, mobile, email, user);
                    sendVerificationSms(session);
                    return session;
                }
                
            default:
                throw new ServiceException("PasswordRecoveryService-InvalidRecoveryType", "找回密码类型无效"); 
        }
    }

    /**
     * 检查手机号码当天发送短信是否超过数量限制.
     * @param mobile            手机号码
     * @throws ServiceException 发送超过数量限制
     */
    private void checkMaxSmsSend(String mobile) throws ServiceException {
        // 计算当天开始结束时间
        Date dayStart = DateUtils.truncate(new Date(), Calendar.DATE);
        Date dayEnd = DateUtils.addMilliseconds(DateUtils.addDays(dayStart, 1), -1);
        
        // 查询手机号码当天已发送短信数量
        int dayCount = ucUserVerificationSessionMapper.countByMap(
                MyBatisUtils.buildParameterMap(
                        UcUserVerificationSession.Fields.MOBILE, mobile,
                        UcUserVerificationSession.Query.CREATE_TIME__BEGIN, dayStart,
                        UcUserVerificationSession.Query.CREATE_TIME__END, dayEnd
                )
        );
        if (dayCount >= paramMaxSmsPerDay) {
            LOGGER.error("Too many sms send for: {}", mobile);
            throw new ServiceException("SignUpService-MaxSmsSent", "一天最多只能发送" + paramMaxSmsPerDay + "条短信");
        }
    }
    
    /**
     * 检查短信发送最短间隔.
     * @param mobile            手机号码
     * @throws ServiceException 发送超过最短间隔
     */
    private void checkMinSendInterval(String mobile) throws ServiceException {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                UcUserVerificationSession.Fields.MOBILE, mobile,
                OrderItem.ORDER_KEY, Arrays.asList(
                        new OrderItem(UcUserVerificationSession.Fields.CREATE_TIME, OrderItem.DESC))
        );
        List<UcUserVerificationSession> lastSession 
                = ucUserVerificationSessionMapper.selectByMap(query, new RowBounds(0, 1));
        if (lastSession == null || lastSession.isEmpty()) {
            return;
        }
        if (new Date().getTime() - lastSession.get(0).getCreateTime().getTime() 
                < paramSmsSendInterval * DateUtils.MILLIS_PER_SECOND) {
            throw new ServiceException("SignUpService-MinInterval", "短信发送频率过快，请1分钟后重试");
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
     * 生成SessionId.
     * @return Session id
     */
    private String generateSessionId() {
        return "MR" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) 
                + RandomStringUtils.randomAlphanumeric(13);
    }

    /**
     * 发送验证短信.
     * @param session 短信验证Session
     */
    private void sendVerificationSms(final UcUserVerificationSession session) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    smsSendService.sendPasswordRecoveryVerificationSms(session.getMobile(), session.getCode(),
                            SESSION_VALIDITY_TIME_IN_MINUTES);
                } catch (ServiceException e) {
                    LOGGER.error("Failed to send register verification sms.");
                }
            }
        }).start();
    }


    /**
     * 发送验证邮件.
     * @param session 短信验证Session
     */
    private void sendVerificationEmail(final UcUserVerificationSession session) throws ServiceException {
        final String umsUrl = urlManager.getUmsUrl();
        final String portalUrl = urlManager.getGovAffairsPortalUrl();
        new Thread(new Runnable() {
            @Override
            public void run() {
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
        }).start();
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
     * 根据手机号码查询用户信息，如果手机号码不存在，抛出异常.
     * @param mobile            需要查询的手机号码
     * @return                  用户信息
     * @throws ServiceException 手机号码不存在
     */
    private UcUserInfo queryUserByMobile(String mobile) throws ServiceException {
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.MOBILE, mobile
        ));
        if (list == null || list.isEmpty()) {
            LOGGER.error("User mobile not found: {}", mobile);
            throw new ServiceException("PasswordRecoveryService-MobileNotFound", "手机号码不存在");
        }
        return list.get(0);
    }
    
//    /**
//     * 验证找回密码验证码是否有效.
//     * @param sessionId         找回密码Session的ID
//     * @param sessionKey        找回密码Session的Key
//     * @param code              验证码
//     * @throws ServiceException 验证失败
//     */
//    public void verifyCode(String sessionId, String sessionKey, String code) throws ServiceException {
//        UcUserVerificationSession session = checkSessionForVerify(sessionId, sessionKey, code);
//        verifySession(session);
//    }
    
//    /**
//     * 验证找回密码Session是否有效.
//     * @param sessionId         Session的ID
//     * @param sessionKey        Session的Key
//     * @param code              手机验证码
//     * @return                  如果有效，返回Session信息
//     * @throws ServiceException 检查失败 
//     */
//    private UcUserVerificationSession checkSessionForVerify(String sessionId, String sessionKey, 
//            String code) throws ServiceException {
//        UcUserVerificationSession session = ucUserVerificationSessionMapper.selectById(sessionId);
//        if (session == null) {
//            LOGGER.error("Session id not found for create user: {}", sessionId);
//            throw new ServiceException("PasswordRecoveryService-SessionIdNotFound", "验证码无效或已过期，请重新获取");
//        }
//        if (!session.getSessionKey().equals(sessionKey)) {
//            LOGGER.error("Invalid session key for session: {}", sessionId);
//            throw new ServiceException("PasswordRecoveryService-InvalidSessionKey", "验证码无效或已过期，请重新获取");
//        }
//        if (!session.getVerificationType().equals(UcUserVerificationSession.TYPE_M_FINDPWD)
//                && !session.getVerificationType().equals(UcUserVerificationSession.TYPE_E_FINDPWD)) {
//            LOGGER.error("Invalid session type for session: {}", sessionId);
//            throw new ServiceException("PasswordRecoveryService-InvalidSessionType", "验证码无效或已过期，请重新获取");
//        }
//        if (!session.getCode().equals(code)) {
//            LOGGER.error("Wrong mobile code for session: {}", sessionId);
//            throw new ServiceException("PasswordRecoveryService-InvalidSessionCode", "验证码无效或已过期，请重新获取");
//        }
//        if (session.getValidityTime().getTime() < new Date().getTime()) {
//            LOGGER.error("Register session is expired: {}", sessionId);
//            throw new ServiceException("PasswordRecoveryService-SessionExpired", "验证码无效或已过期，请重新获取");
//        }
//        if (!session.getStatus().equals(UcUserVerificationSession.STATUS_CREATED)
//                && !session.getStatus().equals(UcUserVerificationSession.STATUS_VERIFIED)) {
//            LOGGER.error("Invalid register session status: {}", sessionId);
//            throw new ServiceException("PasswordRecoveryService-SessionExpired", "验证码无效或已过期，请重新获取");
//        }
//        return session;
//    }
    
//    /**
//     * 将Session更新为已验证状态.
//     * @param session 找回密码Session
//     */
//    private void verifySession(UcUserVerificationSession session) {
//        UcUserVerificationSession update = new UcUserVerificationSession();
//        update.setSessionId(session.getSessionId());
//        update.setStatus(UcUserVerificationSession.STATUS_VERIFIED);
//        ucUserVerificationSessionMapper.updateSelectiveById(update);
//    }

    /**
     * 重置密码.
     * @param sessionId         找回密码Session的ID
     * @param sessionKey        找回密码Session的Key
     * @param password          新密码
     * @throws ServiceException 重置密码失败
     */
    public void resetPassword(String sessionId, String sessionKey, String code, String password) throws ServiceException {
        UcUserVerificationSession session = checkSessionForReset(sessionId, sessionKey, code);
        finishSession(session);
        
        // 更新密码
        UcUserInfo update = new UcUserInfo();
        update.setUserId(Integer.parseInt(session.getUserId()));
        update.setPasswordHash(PasswordHash3.hashPassword(password));
        update.setPasswordKey(PasswordEncryption.randomKey());
        update.setPassword(PasswordEncryption.encrypt(password, update.getPasswordKey()));
        ucUserInfoMapper.updateSelectiveById(update);
    }

    /**
     * 验证找回密码Session是否有效.
     * @param sessionId         Session的ID
     * @param sessionKey        Session的Key
     * @param code              手机验证码
     * @return                  如果有效，返回Session信息
     * @throws ServiceException 检查失败 
     */
    private UcUserVerificationSession checkSessionForReset(String sessionId, String sessionKey, String code) throws ServiceException {
        UcUserVerificationSession session = ucUserVerificationSessionMapper.selectById(sessionId);
        if (session == null) {
            LOGGER.error("Session id not found for create user: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-SessionIdNotFound", "验证码无效或已过期，请重新获取");
        }
        if (!session.getSessionKey().equals(sessionKey)) {
            LOGGER.error("Invalid session key for session: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-InvalidSessionKey", "验证码无效或已过期，请重新获取");
        }
        if (!session.getCode().equals(code)) {
            LOGGER.error("Wrong mobile code for session: {}", sessionId);
            throw new ServiceException("PasswordRecoveryService-InvalidSessionCode", "验证码无效或已过期，请重新获取");
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
        if (!session.getStatus().equals(UcUserVerificationSession.STATUS_CREATED)) {
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
