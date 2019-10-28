package cn.com.hugedata.spark.connect.handlers.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.service.push.JiguangPushDeviceService;
import cn.com.hugedata.spark.commons.service.session.LoginSessionInfo;
import cn.com.hugedata.spark.commons.service.session.LoginSessionService;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcCompanyInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.commons.utils.PasswordHash3;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class Login2Handler extends BaseHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Login2Handler.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;

    @Autowired
    private UcCompanyInfoMapper ucCompanyInfoMapper;
    
    @Autowired
    private LoginSessionService loginSessionService;
    
    @Autowired
    private JiguangPushDeviceService pushDeviceService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    private String paramLoginName;
    private String paramPassword;
    private String paramPlatform;
    private String paramRegistrationId;
    private String paramDeviceModel;
    private String paramDeviceId;
    private String paramVersion;
    private Integer paramVersionCode;
    private String paramSystemVersion;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    @Override
    public InterfaceResponse execute(JSONObject requestData) throws ServiceException {
        parseParameters(requestData);
        checkParameters();
        
        // 查询用户信息
        UcUserInfo user = queryUserInfo(paramLoginName);
        
        // 判断用户是否已经停用
        checkUserUsable(user);
        
        // 判断密码
        checkPassword(user, paramPassword);
        
        // 登录检查成功
        String token = loginSessionService.createToken("CONNECT", String.valueOf(user.getUserId()));
        LoginSessionInfo loginInfo = new LoginSessionInfo();
        loginInfo.setUserInfo(user);
        loginInfo.setPlatform(paramPlatform);
        loginInfo.setDeviceModel(paramDeviceModel);
        loginInfo.setDeviceId(paramDeviceId);
        loginInfo.setVersion(paramVersion);
        loginInfo.setVersionCode(paramVersionCode);
        loginInfo.setSystemVersion(paramSystemVersion);
        loginSessionService.saveLoginInfo(token, loginInfo);
        
        // 更新推送数据
        if (StringUtils.isNotEmpty(paramRegistrationId)) {
            pushDeviceService.updateDevice(paramRegistrationId, user.getUserId(), paramPlatform, token);
        }
        
        // 查询企业信息
        UcCompanyInfo company = null;
        if (user.getCompanyId() != null && user.getCompanyId() > 0) {
            company = queryCompanyInfo(user.getCompanyId());
        }

        // 返回
        JSONObject userObj = (JSONObject) JSON.toJSON(user);
        userObj.remove("password");
        userObj.remove("passwordHash");
        userObj.remove("passwordKey");
        userObj.remove("loginNameUpper");
        userObj.remove("emailUpper");
        userObj.remove("idPropertyName");
        userObj.remove("idValue");
        userObj.remove("loginNameUpper");
        userObj.remove("emailUpper");
        userObj.put("headImageUrl", fileUrlHelperService.fixDownloadUrl(user.getHeadImageId()));
        userObj.put("headImagePreviewUrl", fileUrlHelperService.fixDownloadUrl(user.getHeadImagePreviewId()));
        
        JSONObject companyObj = null;
        if (company != null) {
            companyObj = (JSONObject) JSON.toJSON(company);
            companyObj.remove("idPropertyName");
            companyObj.remove("idValue");
        }
        
        JSONObject resp = new JSONObject();
        resp.put("user", userObj);
        resp.put("company", companyObj);
        resp.put("token", token);
        return InterfaceResponse.createSuccessResponse(resp);
    }
    
    private void parseParameters(JSONObject req) {
        paramLoginName = req.getString("loginName");
        paramPassword = req.getString("password");
        paramPlatform = req.getString("platform");
        paramRegistrationId = req.getString("registrationId");
        paramDeviceModel = req.getString("deviceModel");
        paramDeviceId = req.getString("deviceId");
        paramVersion = req.getString("version");
        paramVersionCode = req.getInteger("versionCode");
        paramSystemVersion = req.getString("systemVersion");

        if (StringUtils.isNotEmpty(paramPlatform)) {
            paramPlatform = paramPlatform.toUpperCase();
        }
    }

    private void checkParameters() throws ServiceException {
        if (StringUtils.isEmpty(paramLoginName)) {
            LOGGER.error("Parameter [loginName] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-LoginNameIsEmpty", "登录名不能为空");
        }
        if (StringUtils.isEmpty(paramPassword)) {
            LOGGER.error("Parameter [password] is empty.");
            throw new ServiceException(this.getClass().getSimpleName() + "-PasswordIsEmpty", "密码不能为空");
        }
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
                updatePasswordHash(user, passwordHash);
                return;
            }
        }

        LOGGER.error("Wrong password: {}", user.getLoginName());
        throw new ServiceException("LoginService-WrongPassword", "登录名或密码错误");
    }

    private void updatePasswordHash(UcUserInfo user, String hash) {
        UcUserInfo update = new UcUserInfo();
        update.setUserId(user.getUserId());
        update.setPasswordHash(hash);
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
            throw new ServiceException("LoginService-UserIsNotUsable", "用户已被停用");
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

    /**
     * 查询企业信息.
     * @param companyId         企业ID
     * @return                  企业信息
     * @throws ServiceException 查询失败
     */
    private UcCompanyInfo queryCompanyInfo(Integer companyId) throws ServiceException {
        UcCompanyInfo company = ucCompanyInfoMapper.selectById(companyId);
        return company;
    }
}
