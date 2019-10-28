package cn.com.hugedata.spark.govaffairs.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcCompanyInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;

@Service
public class UserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcCompanyInfoMapper ucCompanyInfoMapper;
    
    /**
     * 查询一个用户的用户信息和所属企业信息.
     * @param userId 用户ID
     * @return       用户信息和所属企业信息
     */
    public UserAndCompanyInfo queryUserAndCompanyInfo(int userId) {
        UcUserInfo userInfo = ucUserInfoMapper.selectById(userId);
        UcCompanyInfo companyInfo = ucCompanyInfoMapper.selectById(userInfo.getCompanyId());
        
        UserAndCompanyInfo info = new UserAndCompanyInfo();
        info.userInfo = userInfo;
        info.companyInfo = companyInfo;
        return info;
    }
    
    /**
     * 更新用户信息.
     */
    @Transactional(rollbackFor = Exception.class)
    public void modifyUser(
            int userId, 
            String password, 
            String companyName, 
            String companyType, 
            String companyAddress,
            String organizationCode, 
            String licenceCode, 
            String representative, 
            String representativeId, 
            String contactName,
            String contactMobile, 
            String contactEmail) throws ServiceException {
        
        UcUserInfo user = queryUserInfo(userId);
        
        // 判断用户是否已经存在
        checkEmailExist(contactEmail, userId);
        checkMobileExist(contactMobile, userId);
        
        // 更新用户信息
        UcUserInfo userUpdate = new UcUserInfo();
        userUpdate.setUserId(userId);
        if (StringUtils.isNotEmpty(password)) {
            userUpdate.setPasswordKey(PasswordEncryption.randomKey());
            userUpdate.setPassword(PasswordEncryption.encrypt(password, userUpdate.getPasswordKey()));
        }
        userUpdate.setMobile(contactMobile);
        userUpdate.setEmail(contactEmail);
        userUpdate.setEmailUpper(contactEmail.toUpperCase());
        userUpdate.setFullAddress(companyAddress);
        userUpdate.setCompanyName(companyName);
        userUpdate.setUpdateTime(new Date());
        ucUserInfoMapper.updateSelectiveById(userUpdate);
        
        // 更新企业信息
        UcCompanyInfo companyUpdate = new UcCompanyInfo();
        companyUpdate.setCompanyId(user.getCompanyId());
        companyUpdate.setCompanyName(companyName);
        companyUpdate.setCompanyType(companyType);
        companyUpdate.setCompanyAddress(companyAddress);
        companyUpdate.setOrganizationCode(organizationCode);
        companyUpdate.setLicenceCode(licenceCode);
        companyUpdate.setRepresentative(representative);
        companyUpdate.setRepresentativeId(representativeId);
        companyUpdate.setContactName(contactName);
        companyUpdate.setContactMobile(contactMobile);
        companyUpdate.setContactEmail(contactEmail);
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
            LOGGER.error("User name exists.");
            throw new ServiceException("SignUpService-MobileExists", "联系人电话已经存在");
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
    
    /**
     * 用户信息和企业信息.
     */
    public static class UserAndCompanyInfo {
        
        private UcUserInfo userInfo;
        
        private UcCompanyInfo companyInfo;

        public UcUserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UcUserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public UcCompanyInfo getCompanyInfo() {
            return companyInfo;
        }

        public void setCompanyInfo(UcCompanyInfo companyInfo) {
            this.companyInfo = companyInfo;
        }
        
    }

}
