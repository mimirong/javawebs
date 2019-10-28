package cn.com.hugedata.spark.commons.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

/**
 * 验证邮箱是否正确的服务类.
 * @author root
 */
@Service
public class CheckEmailService {

    /**
     * 验证手机号码是否正确.
     * @param mobile 需要验证的手机号码
     * @return       手机号码是否有效
     */
    public boolean isEmailCorrect(String mail) {
        return Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", mail);
    }
}
