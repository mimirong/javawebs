package cn.com.hugedata.spark.management.services.initialize;

import java.util.Date;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.constant.UcUserInfoConstants;
import cn.com.hugedata.spark.commons.storage.entity.UcOrgInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcOrgInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordEncryption;

/**
 * 后台管理功能初始化. 只在系统第一次启动时执行.
 */
@Service
public class ManagementInitOnceService {

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementInitOnceService.class);
    
    /** 读取默认系统管理用户的配置文件. */
    private static final String DEFAULT_USER_CONFIG = "management_init";
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcOrgInfoMapper ucOrgInfoMapper;

    private String username;
    private String password;
    private String email;
    private String mobile;
    private String defaultOrgName;
    
    /**
     * 初始化.
     */
    @PostConstruct
    public void init() {
        // 判断是否需要进行初始化
        if (!needInitialize()) {
            LOGGER.info("Admin system initialized before, initialization skipped.");
            return;
        }
        LOGGER.info("Starting to initialize management system.");

        // 读取初始化配置
        loadInitConfig();
        
        // 创建管理员用户
        createAdminUser();
        
        // 创建默认分部
        createDefaultOrg();
    }

    /**
     * 读取初始化配置信息.
     */
    private void loadInitConfig() {
        ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_USER_CONFIG);
        username = bundle.getString("administrator.username");
        password = bundle.getString("administrator.password");
        email = bundle.getString("administrator.email");
        mobile = bundle.getString("administrator.mobile");
        defaultOrgName = bundle.getString("default_org.name");

        if (StringUtils.isEmpty(username)) {
            LOGGER.error("Admin system initialization failed. Username is empty.");
            throw new RuntimeException("Admin system initialization failed. Username is empty.");
        }
        if (StringUtils.isEmpty(password)) {
            LOGGER.error("Admin system initialization failed. Password is empty.");
            throw new RuntimeException("Admin system initialization failed. Password is empty.");
        }
        if (StringUtils.isEmpty(defaultOrgName)) {
            LOGGER.error("Admin system initialization failed. Default org name is empty.");
            throw new RuntimeException("Admin system initialization failed. Default org name is empty.");
        }
    }

    /**
     * 创建系统管理员.
     */
    private UcUserInfo createAdminUser() {
        // 创建管理员用户
        UcUserInfo user = new UcUserInfo();
        user.setUserType(UcUserInfoConstants.USER_TYPE_SYSTEM);
        user.setLoginName(username);
        user.setLoginNameUpper(username.toUpperCase());
        user.setPasswordKey(PasswordEncryption.randomKey());
        user.setPassword(PasswordEncryption.encrypt(password, user.getPasswordKey()));
        user.setName(username);
        user.setMobile(mobile);
        if (StringUtils.isNotEmpty(email)) {
            user.setEmail(email);
            user.setEmailUpper(email.toUpperCase());
        }
        user.setUsable(true);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        ucUserInfoMapper.insertSelective(user);
        return user;
    }
    
    /**
     * 创建默认分部信息.
     */
    private void createDefaultOrg() {
        UcOrgInfo org = new UcOrgInfo();
        org.setName(defaultOrgName);
        org.setParentOrgId(null);
        org.setCreateTime(new Date());
        org.setUpdateTime(new Date());
        ucOrgInfoMapper.insertSelective(org);
    }
    
    /**
     * 返回是否需要进行初始化.
     * @return 是否需要进行初始化
     */
    private boolean needInitialize() {
        int count = ucUserInfoMapper.countByMap(
                MyBatisUtils.buildParameterMap(
                        UcUserInfo.Fields.USER_TYPE, UcUserInfo.USER_TYPE_SYSTEM 
                )
        );
        return count == 0;
    }
}
