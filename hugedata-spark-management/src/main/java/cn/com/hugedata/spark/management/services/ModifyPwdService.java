package cn.com.hugedata.spark.management.services;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModifyPwdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyPwdService.class);

    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;

    /**
     *  校验输入的当前用户的登录密码是否正确.
     * @param password       旧密码
     * @throws ServiceException 校验失败，异常包含错误信息
     */
    public void checkPassword(String password) throws ServiceException {
        // 检查参数
        if(StringUtils.isEmpty(password)){
            LOGGER.error("Password is empty");
            throw new ServiceException("ModifyPwdService-PasswordIsEmpty", "密码不能为空");
        }

        // 查询用户信息
        UcUserInfo user = queryUserInfo(LoginUtils.getCurrentLogin().getLoginName());

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
        throw new ServiceException("ModifyPwdService-WrongPassword", "输入的旧密码错误");
    }

    /**
     * 更新用户密码信息.
     * @param user     需要更新密码信息的用户
     * @param password 密码
     */
    public void updateUserPassword(UcUserInfo user, String password) throws ServiceException {
        UcUserInfo update = new UcUserInfo();
        update.setUserId(user.getUserId());
        update.setPasswordKey(PasswordEncryption.randomKey());
        update.setPassword(PasswordEncryption.encrypt(password, update.getPasswordKey()));
        ucUserInfoMapper.updateSelectiveById(update);
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

}
