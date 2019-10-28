package cn.com.hugedata.spark.ums.services.weaver;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;

/**
 * 用于处理泛微OA单点登录.
 */
@Service
public class WeaverLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeaverLoginService.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    /**
     * OA前台地址.
     */
    @Value("${weaver.webUrl}")
    private String weaverWebUrl;
    
    /**
     * 查询OA登录信息.
     * @param user              当前登录的用户信息.
     * @return                  OA登录信息
     * @throws ServiceException 查询失败
     */
    public WeaverLoginInfo queryLoginInfo(UcUserInfo user) throws ServiceException {
        user = queryUserInfo(user.getUserId());
        
        // 获取密码信息
        if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getPasswordKey())) {
            LOGGER.error("No login password found for user: {}", user.getUserId());
            throw new ServiceException("WeaverLoginService-NoPassword", "无法获取登录密码，请重新登录");
        }
        String password = PasswordEncryption.decrypt(user.getPassword(), user.getPasswordKey());
        
        // 检查密码是否一致
        if (StringUtils.isNotEmpty(user.getPasswordHash())) {
            String hash = DigestUtils.md5Hex(password);
            if (!hash.toUpperCase().equals(user.getPasswordHash())) {
                LOGGER.error("Password not same as password hash.", user.getUserId());
                throw new ServiceException("WeaverLoginService-PasswordWrong", "密码不正确，请重新登录");
            }
        }
        
        WeaverLoginInfo info = new WeaverLoginInfo();
        info.setWebUrl(weaverWebUrl);
        info.setLoginName(user.getLoginName());
        info.setPassword(password);
        return info;
    }
    
    /**
     * 查询用户信息.
     * @param userId            用户ID
     * @return                  用户信息
     * @throws ServiceException 查询失败
     */
    private UcUserInfo queryUserInfo(int userId) throws ServiceException {
        UcUserInfo user = ucUserInfoMapper.selectById(userId);
        if (user == null) {
            LOGGER.error("User id not found: {}", userId);
            throw new ServiceException("WeaverLoginService-UserNotFound", "用户信息不存在");
        }
        return user;
    }
    
    /**
     * 登录OA所需的信息.
     */
    public static class WeaverLoginInfo {
        
        private String loginName;
        
        private String password;
        
        private String webUrl;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }
    }
}
