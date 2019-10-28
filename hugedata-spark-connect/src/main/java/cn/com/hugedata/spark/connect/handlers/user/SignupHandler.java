package cn.com.hugedata.spark.connect.handlers.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcCompanyInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

/**
 * 注册接口.
 */
public class SignupHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignupHandler.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcCompanyInfoMapper ucCompanyInfoMapper;
    
    private String paramUsername;
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
    
    private Integer createdUserId;
    private Integer createdCompanyId;

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        // 获取参数
        paramUsername = req.getString("username");
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
       
        checkParameters();
        
        createUser();
        
        JSONObject data = new JSONObject();
        data.put("userId", createdUserId);
        data.put("companyId", createdCompanyId);
        return InterfaceResponse.createSuccessResponse(data);
    }
    
    /**
     * 检查请求参数.
     */
    private void checkParameters() throws ServiceException {
        if (StringUtils.isEmpty(paramUsername)) {
            LOGGER.error("Parameter username is empty");
            throw new ServiceException("UserNameEmpty", "用户名不能为空.");
        }
        if (StringUtils.isEmpty(paramPassword)) {
            LOGGER.error("Parameter password is empty");
            throw new ServiceException("PasswordEmpty", "密码不能为空.");
        }
        if (StringUtils.isEmpty(paramCompanyName)) {
            LOGGER.error("Parameter companyName is empty");
            throw new ServiceException("CompanyNameEmpty", "企业名称不能为空.");
        }
        if (StringUtils.isEmpty(paramCompanyType)) {
            LOGGER.error("Parameter companyType is empty");
            throw new ServiceException("CompanyTypeEmpty", "企业类型不能为空.");
        }
        if (StringUtils.isEmpty(paramCompanyAddress)) {
            LOGGER.error("Parameter companyAddress is empty");
            throw new ServiceException("CompanyAddressEmpty", "单位地址不能为空.");
        }
        if (StringUtils.isEmpty(paramOrganizationCode)) {
            LOGGER.error("Parameter organizationCode is empty");
            throw new ServiceException("OrganizationCodeEmpty", "组织机构代码不能为空.");
        }
        if (StringUtils.isEmpty(paramLicenceCode)) {
            LOGGER.error("Parameter licenceCode is empty");
            throw new ServiceException("LicenceCodeEmpty", "营业执照号码不能为空.");
        }
        if (StringUtils.isEmpty(paramRepresentative)) {
            LOGGER.error("Parameter representative is empty");
            throw new ServiceException("RepresentativeEmpty", "法人代表不能为空.");
        }
        if (StringUtils.isEmpty(paramRepresentativeId)) {
            LOGGER.error("Parameter representativeId is empty");
            throw new ServiceException("RepresentativeIdEmpty", "法人身份证号码不能为空.");
        }
        if (StringUtils.isEmpty(paramContactName)) {
            LOGGER.error("Parameter contactName is empty");
            throw new ServiceException("ContactNameEmpty", "联系人不能为空.");
        }
        if (StringUtils.isEmpty(paramContactMobile)) {
            LOGGER.error("Parameter contactMobile is empty");
            throw new ServiceException("ContactMobileEmpty", "联系人电话不能为空.");
        }
        if (StringUtils.isEmpty(paramContactEmail)) {
            LOGGER.error("Parameter contactEmail is empty");
            throw new ServiceException("ContactEmailEmpty", "联系人邮箱不能为空.");
        }
    }

    /**
     * 创建用户.
     */
    @Transactional(rollbackFor = Exception.class)
    public void createUser() throws ServiceException {
        // 判断用户是否已经存在
        checkUsernameExist(paramUsername);
        checkEmailExist(paramContactEmail);
        checkMobileExist(paramContactMobile);
        
        // 创建企业
        UcCompanyInfo company = new UcCompanyInfo();
        company.setCompanyName(paramCompanyName);
        company.setCompanyType(paramCompanyType);
        company.setCompanyAddress(paramCompanyAddress);
        company.setOrganizationCode(paramOrganizationCode);
        company.setLicenceCode(paramLicenceCode);
        company.setRepresentative(paramRepresentative);
        company.setRepresentativeId(paramRepresentativeId);
        company.setContactName(paramContactName);
        company.setContactMobile(paramContactMobile);
        company.setContactEmail(paramContactEmail);
        company.setCreateTime(new Date());
        company.setUpdateTime(new Date());
        ucCompanyInfoMapper.insertSelective(company);
        
        // 创建用户
        UcUserInfo user = new UcUserInfo();
        user.setUserId(null);
        user.setUserType(UcUserInfo.USER_TYPE_COMPANY);
        user.setLoginName(paramUsername);
        user.setLoginNameUpper(paramUsername.toUpperCase());
        user.setPasswordKey(PasswordEncryption.randomKey());
        user.setPassword(PasswordEncryption.encrypt(paramPassword, user.getPasswordKey()));
        user.setMobile(paramContactMobile);
        user.setName(paramUsername);
        user.setEmail(paramContactEmail);
        user.setEmailUpper(paramContactEmail.toUpperCase());
        user.setFullAddress(paramCompanyAddress);
        user.setCompanyId(company.getCompanyId());
        user.setCompanyName(paramCompanyName);
        user.setUsable(false);
        user.setApproveStatus(UcUserInfo.APPROVE_STATUS_CREATED);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        ucUserInfoMapper.insertSelective(user);
        
        // 更新createor
        UcCompanyInfo update = new UcCompanyInfo();
        update.setCreatorUserId(user.getUserId());
        ucCompanyInfoMapper.updateSelectiveById(update);
        
        createdUserId = user.getUserId();
        createdCompanyId = company.getCompanyId();
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
     * @param paramUsername          需要判断的手机号码
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
     * @param paramUsername          需要判断的邮箱
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
