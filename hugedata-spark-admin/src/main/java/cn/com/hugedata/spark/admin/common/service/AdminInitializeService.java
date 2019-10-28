package cn.com.hugedata.spark.admin.common.service;

import java.util.Date;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.entity.UcAdminUser;
import cn.com.hugedata.spark.commons.storage.mapper.UcAdminUserMapper;
import cn.com.hugedata.spark.commons.utils.IDGen;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.utils.PasswordHash3;

/**
 * 初始化系统管理. 
 */
@Service
public class AdminInitializeService {

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminInitializeService.class);
    
    /** 读取默认系统管理用户的配置文件. */
    private static final String DEFAULT_USER_CONFIG = "admin_init";
    
    @Autowired
    private UcAdminUserMapper ucAdminUserMapper;
    
    /**
     * 初始化系统管理员信息.
     */
    @PostConstruct
    public void init() {
        // 判断是否需要进行初始化
        if (!needInitialize()) {
            LOGGER.info("Admin system initialized before, initialization skipped.");
            return;
        }
        LOGGER.info("Starting to initialize admin system.");
        
        // 从配置文件加载管理员用户信息
        ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_USER_CONFIG);
        String username = bundle.getString("administrator.username");
        String password = bundle.getString("administrator.password");
        String email = bundle.getString("administrator.email");
        String mobile = bundle.getString("administrator.mobile");

        if (StringUtils.isEmpty(username)) {
            LOGGER.error("Admin system initialization failed. Username is empty.");
            throw new RuntimeException("Admin system initialization failed. Username is empty.");
        }
        if (StringUtils.isEmpty(password)) {
            LOGGER.error("Admin system initialization failed. Password is empty.");
            throw new RuntimeException("Admin system initialization failed. Password is empty.");
        }
        
        // 创建管理员用户
        UcAdminUser user = new UcAdminUser();
        user.setUserId(IDGen.generateId());
        user.setUsername(username);
        user.setPassword(PasswordHash3.calcPasswordHash(username, password));
        user.setEmail(email);
        user.setMobile(mobile);
        user.setIsAcceptMessage("false");
        user.setCreateTime(new Date());
        ucAdminUserMapper.insertSelective(user);
    }
    
    /**
     * 返回是否需要进行初始化.
     * @return 是否需要进行初始化
     */
    private boolean needInitialize() {
        int count = ucAdminUserMapper.countByMap(MyBatisUtils.EMPTY_MAP);
        return count == 0;
    }
}
