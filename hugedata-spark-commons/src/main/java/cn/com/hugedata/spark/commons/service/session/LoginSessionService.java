package cn.com.hugedata.spark.commons.service.session;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;

/**
 * 用于处理登录Token相关的操作 .
 * @author gaopeng
 */
@Service
public class LoginSessionService {
    
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSessionService.class);

    /**
     * Session超时时间，单位为秒.
     */
    @Value("${user.loginSessionExpire}")
    private Long loginSessionExpire;
    
    /**
     * 用于读取和保存Serializable的RedisTemplate.
     */
    @Autowired
    protected RedisTemplate<String, Serializable> objectTemplate;
    
    /**
     * 创建一个新的空Session.
     * @param type   登录类型，如"web", "connect"
     * @param userId 用户ID
     * @return 新创建的Token
     */
    public String createToken(String type, String userId) {
        String timePart = String.valueOf(System.currentTimeMillis());
        String randomPart = RandomStringUtils.randomAlphanumeric(16);
        
        if (StringUtils.isEmpty(type)) {
            type = "DEFAULT";
        }
        
        String token = "LOGIN-" + type.toUpperCase() + "-" + userId + "-" + timePart + "-" + randomPart;
        LOGGER.info("Login token created: {}", token);
        return token;
    }
    
    /**
     * 保存Token信息.
     * @param token Token
     * @param user  用户信息
     */
    public void saveLoginInfo(String token, LoginSessionInfo loginInfo) {
        loginInfo.setCreateTime(new Date().getTime());
        loginInfo.setToken(token);
        objectTemplate.opsForValue().set(token, loginInfo, loginSessionExpire, TimeUnit.SECONDS);
    }

    /**
     * 保存Token信息.
     * @param token               Token
     * @param loginInfo           登录信息
     * @param expireTimeInSeconds 超时时间(单位为秒)
     */
    public void saveLoginInfo(String token, LoginSessionInfo loginInfo, int expireTimeInSeconds) {
        loginInfo.setCreateTime(new Date().getTime());
        loginInfo.setToken(token);
        objectTemplate.opsForValue().set(token, loginInfo, expireTimeInSeconds, TimeUnit.SECONDS);
    }
    
    /**
     * 获取登录信息.
     * @param token Token
     * @return 登录信息，如果Token不存在，返回null
     */
    public LoginSessionInfo findLoginInfo(String token) {
        try {
            // 获取Token信息
            LOGGER.info("Loading login info from token: {}", token);
            LoginSessionInfo loginInfo = (LoginSessionInfo) objectTemplate.opsForValue().get(token);
            if (loginInfo != null) {
                LOGGER.info("Found user login info (new).");
                return loginInfo;   
            }
        } catch (JSONException e) {
            LOGGER.info("Token is not login info, parse to User.");
        } catch (Exception e) {
            LOGGER.error("Failed to get login info from cache.", e);
        }
        
        return null;
    }
    
    /**
     * 刷新登录Session的有效期.
     * @param token 登录Token
     */
    public void refreshSession(String token) {
        objectTemplate.expire(token, loginSessionExpire, TimeUnit.SECONDS);
    }
    
    /**
     * 删除登录Token信息.
     * @param token 登录Token
     */
    public void deleteToken(String token) {
        objectTemplate.delete(token);
    }
    
}
