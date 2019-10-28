package cn.com.hugedata.spark.commons.service.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;

/**
 * 用于发送短信的服务类.
 */
@Service
public class SmsSendService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSendService.class);

    /**
     * 发送注册验证短信.
     * @param to                接收人手机号
     * @param code              验证码
     * @param expiryMinutes     超时时间(分钟)
     * @throws ServiceException 发送短信失败
     */
    public void sendRegisterVerificationSms(String to, String code, int expiryMinutes) throws ServiceException {
        LOGGER.info("Sending register verification sms to {} with code {}", to, code);
    }

    /**
     * 发送找回密码验证短信.
     * @param to                接收人手机号
     * @param code              验证码
     * @param expiryMinutes     超时时间(分钟)
     * @throws ServiceException 发送短信失败
     */
    public void sendPasswordRecoveryVerificationSms(String to, String code, int sessionValidityTimeInMinutes)
            throws ServiceException {
        LOGGER.info("Sending password recovery verification sms to {} with code {}", to, code);
    }
    
}
