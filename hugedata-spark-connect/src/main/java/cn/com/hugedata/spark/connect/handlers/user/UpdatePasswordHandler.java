package cn.com.hugedata.spark.connect.handlers.user;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.commons.utils.PasswordHash3;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class UpdatePasswordHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePasswordHandler.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        String oldPassword = req.getString("oldPassword");
        String newPassword = req.getString("newPassword");
        
        if (StringUtils.isEmpty(oldPassword)) {
            LOGGER.error("Parameter oldPassword is empty.");
            throw new ServiceException("EmptyOldPassword", "原密码不能为空");
        }
        if (StringUtils.isEmpty(newPassword)) {
            LOGGER.error("Parameter newPassword is empty.");
            throw new ServiceException("EmptyNewPassword", "新密码不能为空");
        }
        
        
        // 获取用户信息
        UcUserInfo user = ucUserInfoMapper.selectById(getLogin().getUserId());
        if (user == null) {
            LOGGER.error("User not exist: {}", getLogin().getUserId());
            throw new ServiceException("UserNotFound", "用户不存在");
        }
        
        // 检查密码
        String passwordHash = PasswordHash3.hashPassword(oldPassword);
        checkPassword(user, passwordHash);
        
        // 更新密码
        UcUserInfo update = new UcUserInfo();
        update.setUserId(user.getUserId());
        update.setPasswordHash(PasswordHash3.hashPassword(newPassword));
        update.setPasswordKey(PasswordEncryption.randomKey());
        update.setPassword(PasswordEncryption.encrypt(newPassword, update.getPasswordKey()));
        update.setUpdateTime(new Date());
        ucUserInfoMapper.updateSelectiveById(update);
        
        return InterfaceResponse.createSuccessResponse();
    }

    /**
     * 检查用户密码是否正确，如果不正确，抛出异常.
     * @param user              用户信息
     * @param passwordHash          密码Hash
     * @throws ServiceException 密码不正确
     */
    private void checkPassword(UcUserInfo user, String passwordHash) throws ServiceException {
        // 根据PasswordHash验证
        if (StringUtils.isNotEmpty(user.getPasswordHash())) {
            if (passwordHash.toUpperCase().equals(user.getPasswordHash().toUpperCase())) {
                return;
            }
        }
        
        // 根据PasswordKey和Password验证
        if (StringUtils.isNotEmpty(user.getPasswordKey()) && StringUtils.isNotEmpty(user.getPassword())) {
            String hash = PasswordHash3.hashPassword(
                    PasswordEncryption.decrypt(user.getPassword(), user.getPasswordKey()));
            if (hash.toUpperCase().equals(passwordHash.toUpperCase())) {
                return;
            }
        }

        LOGGER.error("Wrong password: {}", user.getLoginName());
        throw new ServiceException("WrongPassword", "原密码错误");
    }
}
