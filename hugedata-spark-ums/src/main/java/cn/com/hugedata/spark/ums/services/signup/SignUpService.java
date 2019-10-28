package cn.com.hugedata.spark.ums.services.signup;

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

/**
 * 处理用户注册的服务类.
 */
@Service
public class SignUpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignUpService.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcCompanyInfoMapper ucCompanyInfoMapper;
    
    /**
     * 创建用户.
     */
    @Transactional(rollbackFor = Exception.class)
    public void createUser(
            String username, 
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
        // 判断用户是否已经存在
        checkUsernameExist(username);
        checkEmailExist(contactEmail);
        checkMobileExist(contactMobile);
        
        // 创建企业
        UcCompanyInfo company = new UcCompanyInfo();
        company.setCompanyName(companyName);
        company.setCompanyType(companyType);
        company.setCompanyAddress(companyAddress);
        company.setOrganizationCode(organizationCode);
        company.setLicenceCode(licenceCode);
        company.setRepresentative(representative);
        company.setRepresentativeId(representativeId);
        company.setContactName(contactName);
        company.setContactMobile(contactMobile);
        company.setContactEmail(contactEmail);
        company.setCreateTime(new Date());
        company.setUpdateTime(new Date());
        ucCompanyInfoMapper.insertSelective(company);
        
        // 创建用户
        UcUserInfo user = new UcUserInfo();
        user.setUserId(null);
        user.setUserType(UcUserInfo.USER_TYPE_COMPANY);
        user.setLoginName(username);
        user.setLoginNameUpper(username.toUpperCase());
        user.setPasswordKey(PasswordEncryption.randomKey());
        user.setPassword(PasswordEncryption.encrypt(password, user.getPasswordKey()));
        user.setMobile(contactMobile);
        user.setName(username);
        user.setEmail(contactEmail);
        user.setEmailUpper(contactEmail.toUpperCase());
        user.setFullAddress(companyAddress);
        user.setCompanyId(company.getCompanyId());
        user.setCompanyName(companyName);
        user.setUsable(false);
        user.setApproveStatus(UcUserInfo.APPROVE_STATUS_CREATED);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        ucUserInfoMapper.insertSelective(user);
        
        // 更新createor
        UcCompanyInfo update = new UcCompanyInfo();
        update.setCreatorUserId(user.getUserId());
        ucCompanyInfoMapper.updateSelectiveById(update);
    }
    
    /**
     * 判断用户名是否已经存在.
     * @param username          需要判断的用户名
     * @throws ServiceException 用户名已经存在
     */
    private void checkUsernameExist(String username) throws ServiceException {
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.LOGIN_NAME_UPPER, username.toUpperCase()
        ));
        if (list != null && !list.isEmpty()) {
            LOGGER.error("User name exists.");
            throw new ServiceException("SignUpService-UsernameExists", "用户名已经被注册");
        }
    }
    
    /**
     * 判断用户手机号码是否已经存在.
     * @param username          需要判断的手机号码
     * @throws ServiceException 用户名已经存在
     */
    private void checkMobileExist(String mobile) throws ServiceException {
        if (StringUtils.isEmpty(mobile)) {
            return;
        }
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.MOBILE, mobile
        ));
        if (list != null && !list.isEmpty()) {
            LOGGER.error("User name exists.");
            throw new ServiceException("SignUpService-MobileExists", "联系人电话已经被注册");
        }
    }
    
    /**
     * 判断用户邮箱是否已经存在.
     * @param username          需要判断的邮箱
     * @throws ServiceException 用户名已经存在
     */
    private void checkEmailExist(String email) throws ServiceException {
        if (StringUtils.isEmpty(email)) {
            return;
        }
        List<UcUserInfo> list = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(
                UcUserInfo.Fields.EMAIL_UPPER, email.toUpperCase()
        ));
        if (list != null && !list.isEmpty()) {
            LOGGER.error("User name exists.");
            throw new ServiceException("SignUpService-EmailExists", "邮箱已经被注册");
        }
    }
}
