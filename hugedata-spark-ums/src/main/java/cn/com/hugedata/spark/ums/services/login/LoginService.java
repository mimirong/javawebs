package cn.com.hugedata.spark.ums.services.login;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.session.LoginSessionInfo;
import cn.com.hugedata.spark.commons.service.session.LoginSessionService;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private LoginSessionService loginSessionService;
    
    /**
     * 登录处理.
     * @param loginName         用户名
     * @param password          密码
     * @return                  登录成功返回token和用户信息
     * @throws ServiceException 登录失败，异常包含错误信息
     */
    public LoginResult login(String loginName, String password) throws ServiceException {
        // 检查参数
        if (StringUtils.isEmpty(loginName)) {
            LOGGER.error("Login name is empty");
            throw new ServiceException("LoginService-LoginNameIsEmpty", "登录名不能为空");
        }
        if (StringUtils.isEmpty("password")) {
            LOGGER.error("Password is empty");
            throw new ServiceException("LoginService-PasswordIsEmpty", "密码不能为空");
        }
        
        // 查询用户信息
        UcUserInfo user = queryUserInfo(loginName);

        // 检查用户类型
        if (!UcUserInfo.USER_TYPE_COMPANY.equals(user.getUserType())) {
            LOGGER.error("Invalid user type to login portal: {}", user.getUserType());
            throw new ServiceException("InvalidUserType", "登录名或密码错误");
        }
        
        // 判断用户是否已经停用
        checkUserUsable(user);
        
        // 判断密码
        checkPassword(user, password);
        
        // 登录检查成功
        String token = loginSessionService.createToken("WEB", String.valueOf(user.getUserId()));
        LoginSessionInfo loginInfo = new LoginSessionInfo();
        loginInfo.setUserInfo(user);
        loginSessionService.saveLoginInfo(token, loginInfo);
        
        user.setPassword(null);
        user.setPasswordHash(null);
        user.setPasswordKey(null);
        
        LoginResult ret = new LoginResult();
        ret.setToken(token);
        ret.setUser(user);
        return ret;
    }

    /**
     * 退出登录.
     */
    public void logout() {
        String token = LoginUtils.getCurrentLoginToken();
        loginSessionService.deleteToken(token);
    }
    
    /**
     * 检查用户密码是否正确，如果不正确，抛出异常.
     * @param user              用户信息
     * @param password          密码
     * @throws ServiceException 密码不正确
     */
    private void checkPassword(UcUserInfo user, String password) throws ServiceException {
        // 根据PasswordHash验证
        if (StringUtils.isNotEmpty(user.getPasswordHash())) {
            String hash = DigestUtils.md5Hex(password);
            if (hash.toUpperCase().equals(user.getPasswordHash().toUpperCase())) {
                updateUserPassword(user, password);
                return;
            }
        }
        
        // 根据PasswordKey和Password验证
        if (StringUtils.isNotEmpty(user.getPasswordKey()) && StringUtils.isNotEmpty(user.getPassword())) {
            String encryptedPassword = PasswordEncryption.encrypt(password, user.getPasswordKey());
            if (encryptedPassword.equals(user.getPassword())) {
                return;
            }
        }

        LOGGER.error("Wrong password: {}", user.getLoginName());
        throw new ServiceException("LoginService-WrongPassword", "登录名或密码错误");
    }

    /**
     * 更新用户密码信息.
     * @param user     需要更新密码信息的用户
     * @param password 密码
     */
    private void updateUserPassword(UcUserInfo user, String password) {
        UcUserInfo update = new UcUserInfo();
        update.setUserId(user.getUserId());
        update.setPasswordKey(PasswordEncryption.randomKey());
        update.setPassword(PasswordEncryption.encrypt(password, update.getPasswordKey()));
        ucUserInfoMapper.updateSelectiveById(update);
    }
    
    /**
     * 检查用户是否可用，如果不可用，抛出异常.
     * @param user              需要检查可用的用户信息
     * @throws ServiceException 用户不可用
     */
    private void checkUserUsable(UcUserInfo user) throws ServiceException {
        if (user.getUsable() != null && !user.getUsable()) {
            LOGGER.error("User is not usable.");
            throw new ServiceException("LoginService-UserIsNotUsable", "用户未审核或已被停用");
        }
    }

    /**
     * 根据登录名查询用户信息，如果用户不存在，抛出异常.
     * @param loginName         登录名
     * @return                  用户信息
     * @throws ServiceException 用户不存在
     */
    private UcUserInfo queryUserInfo(String loginName) throws ServiceException {
        List<UcUserInfo> userList = ucUserInfoMapper.selectByMap(
                MyBatisUtils.buildParameterMap(
                        UcUserInfo.Fields.LOGIN_NAME_UPPER, loginName.toUpperCase()
                )
        );
        if (userList == null || userList.isEmpty()) {
            LOGGER.error("Login name not exist: {}", loginName);
            throw new ServiceException("LoginService-LoginNameNotExist", "登录名或密码错误");
        }
        UcUserInfo user = userList.get(0);
        return user;
    }
    
    
    public static class LoginResult {
        
        private String token;
        
        private UcUserInfo user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UcUserInfo getUser() {
            return user;
        }

        public void setUser(UcUserInfo user) {
            this.user = user;
        }
    }
}
