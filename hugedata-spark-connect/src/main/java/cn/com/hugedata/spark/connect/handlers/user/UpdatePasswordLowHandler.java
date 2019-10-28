package cn.com.hugedata.spark.connect.handlers.user;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.commons.utils.PasswordHash3;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

/**
 * 修改密码接口 (LOW SECURITY).
 */
public class UpdatePasswordLowHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePasswordLowHandler.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        String loginName = req.getString("loginName");
        String mobile = req.getString("mobile");
        String password = req.getString("password");
        
        // 查询用户信息
        UcUserInfo user = queryUserInfo(loginName, mobile);

        // 更新密码
        UcUserInfo update = new UcUserInfo();
        update.setUserId(user.getUserId());
        update.setPasswordKey(PasswordEncryption.randomKey());
        update.setPassword(PasswordEncryption.encrypt(password, update.getPasswordKey()));
        update.setPasswordHash(PasswordHash3.hashPassword(password));
        update.setUpdateTime(new Date());
        ucUserInfoMapper.updateSelectiveById(update);
        
        return InterfaceResponse.createSuccessResponse();
    }

    /**
     * 根据登录名和手机号码查询用户信息.
     */
    private UcUserInfo queryUserInfo(String loginName, String mobile) throws ServiceException {
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.LOGIN_NAME_UPPER, loginName.toUpperCase(),
                UcUserInfo.Fields.MOBILE, mobile
        ));
        if (list == null || list.isEmpty()) {
            LOGGER.error("User with login name: {} and mobile: {} not exist.", loginName, mobile);
            throw new ServiceException("UserNotFound", "账号和手机号不匹配，请核实");
        }
        return list.get(0);
    }

}
