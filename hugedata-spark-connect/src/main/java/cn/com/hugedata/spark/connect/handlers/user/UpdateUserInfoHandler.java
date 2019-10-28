package cn.com.hugedata.spark.connect.handlers.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcCompanyInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.commons.utils.PasswordHash3;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class UpdateUserInfoHandler extends BaseHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserInfoHandler.class);

    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcCompanyInfoMapper ucCompanyInfoMapper;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;

    private String paramPassword;
    private String paramCompanyName;
    private String paramCompanyType;
    private String paramCompanyAddress;
    private String paramOrganizationCode;
    private String paramLicenceCode;
    private String paramRepresentative;
    private String paramRepresentativeId;
    private String paramContactName;
    private String paramContactMobile;
    private String paramContactEmail;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        paramPassword = req.getString("password");
        paramCompanyName = req.getString("companyName");
        paramCompanyType = req.getString("companyType");
        paramCompanyAddress = req.getString("companyAddress");
        paramOrganizationCode = req.getString("organizationCode");
        paramLicenceCode = req.getString("licenceCode");
        paramRepresentative = req.getString("representative");
        paramRepresentativeId = req.getString("representativeId");
        paramContactName = req.getString("contactName");
        paramContactMobile = req.getString("contactMobile");
        paramContactEmail = req.getString("contactEmail");
        
        modifyUser();
        
        return queryUserAndCompanyInfo();
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void modifyUser() throws ServiceException {
        int userId = getLogin().getUserId();
        
        UcUserInfo user = queryUserInfo(userId);
        
        // 判断用户是否已经存在
        checkEmailExist(paramContactEmail, userId);
        checkMobileExist(paramContactMobile, userId);
        
        // 更新用户信息
        UcUserInfo userUpdate = new UcUserInfo();
        userUpdate.setUserId(userId);
        if (StringUtils.isNotEmpty(paramPassword)) {
            userUpdate.setPasswordHash(PasswordHash3.hashPassword(paramPassword));
            userUpdate.setPasswordKey(PasswordEncryption.randomKey());
            userUpdate.setPassword(PasswordEncryption.encrypt(paramPassword, userUpdate.getPasswordKey()));
        }
        userUpdate.setMobile(paramContactMobile);
        userUpdate.setEmail(paramContactEmail);
        if (paramContactEmail != null) {
            userUpdate.setEmailUpper(paramContactEmail.toUpperCase());
        }
        userUpdate.setFullAddress(paramCompanyAddress);
        userUpdate.setCompanyName(paramCompanyName);
        userUpdate.setUpdateTime(new Date());
        ucUserInfoMapper.updateSelectiveById(userUpdate);
        
        // 更新企业信息
        UcCompanyInfo companyUpdate = new UcCompanyInfo();
        companyUpdate.setCompanyId(user.getCompanyId());
        companyUpdate.setCompanyName(paramCompanyName);
        companyUpdate.setCompanyType(paramCompanyType);
        companyUpdate.setCompanyAddress(paramCompanyAddress);
        companyUpdate.setOrganizationCode(paramOrganizationCode);
        companyUpdate.setLicenceCode(paramLicenceCode);
        companyUpdate.setRepresentative(paramRepresentative);
        companyUpdate.setRepresentativeId(paramRepresentativeId);
        companyUpdate.setContactName(paramContactName);
        companyUpdate.setContactMobile(paramContactMobile);
        companyUpdate.setContactEmail(paramContactEmail);
        companyUpdate.setUpdateTime(new Date());
        ucCompanyInfoMapper.updateSelectiveById(companyUpdate);
    }

    
    /**
     * 判断用户手机号码是否已经存在.
     */
    private void checkMobileExist(String mobile, Integer userId) throws ServiceException {
        if (StringUtils.isEmpty(mobile)) {
            return;
        }
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.MOBILE, mobile,
                UcUserInfo.Query.USER_ID__NE, userId
        ));
        if (list != null && !list.isEmpty()) {
            LOGGER.error("User mobile exists.");
            throw new ServiceException("SignUpService-MobileExists", "联系电话已经存在");
        }
    }
    
    /**
     * 判断用户邮箱是否已经存在.
     */
    private void checkEmailExist(String email, Integer userId) throws ServiceException {
        if (StringUtils.isEmpty(email)) {
            return;
        }
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.EMAIL_UPPER, email.toUpperCase(),
                UcUserInfo.Query.USER_ID__NE, userId
        ));
        if (list != null && !list.isEmpty()) {
            LOGGER.error("User name exists.");
            throw new ServiceException("SignUpService-EmailExists", "邮箱已经存在");
        }
    }
    
    /**
     * 查询用户信息.
     */
    private UcUserInfo queryUserInfo(int userId) throws ServiceException {
        UcUserInfo user = ucUserInfoMapper.selectById(userId);
        if (user == null) {
            LOGGER.error("User id not found: {}", userId);
            throw new ServiceException("UserIdNotFound", "用户信息不存在");
        }
        return user;
    }
    
    private InterfaceResponse queryUserAndCompanyInfo() throws ServiceException {
        int userId = getLogin().getUserId();
        
        // 查询用户信息
        UcUserInfo user = ucUserInfoMapper.selectById(userId);
        if (user == null) {
            LOGGER.error("User not found: {}", userId);
            throw new ServiceException("UserNotFound", "用户不存在");
        }
        
        // 查询企业信息
        UcCompanyInfo companyInfo = null;
        if (UcUserInfo.USER_TYPE_COMPANY.equals(user.getUserType()) || user.getCompanyId() != null) {
            companyInfo = ucCompanyInfoMapper.selectById(user.getCompanyId());
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
        if (companyInfo != null) {
            companyObj = (JSONObject) JSON.toJSON(companyInfo);
            companyObj.remove("idPropertyName");
            companyObj.remove("idValue");
        }
        
        JSONObject resp = new JSONObject();
        resp.put("user", userObj);
        resp.put("company", companyObj);
        return InterfaceResponse.createSuccessResponse(resp);
    }
}
