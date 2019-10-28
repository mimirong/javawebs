package cn.com.hugedata.spark.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;

/**
 * 所有接口服务的基类.
 */
public abstract class BaseHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHandler.class);
    
    /** 当前登录的用户. */
    protected UcUserInfo login;
    
    /** 当前登录token. */
    protected String loginToken;
    
    /** Spring ApplicationContext. */
    protected ApplicationContext applicationContext;
    
    /** 表示Handler是否已经完成初始化. */
    private boolean isInitialized = false;
    
    /**
     * 执行接口请求.
     * @param data 请求数据
     * @return     返回结果
     */
    public abstract InterfaceResponse execute(JSONObject requestData) throws ServiceException;
    
    /**
     * 父类执行接口请求.
     * @param data              请求数据
     * @return                  返回结果
     */
    public InterfaceResponse executeInner(JSONObject data) throws ServiceException {
        if (!this.isInitialized) {
            LOGGER.error("Handler is not initialized.");
            throw new RuntimeException("Handler没有初始化");
        }
        return execute(data);
    }
    
    /**
     * 初始化接口Hanlder.
     * 子类覆盖此方法并进行初始化，子类中必须调用super.initialize()
     * @throws ServiceException 初始化失败
     */
    public void initialize() throws ServiceException {
        this.isInitialized = true;
    }
 
    /**
     * 获取错误代码.
     * @param serviceErrorCode ServiceException的错误代码
     * @return                 int类型错误代码
     */
    public int findErrorCode(String serviceErrorCode) {
        return InterfaceResponse.RESPONSE_ERROR;
    }
    
    /**
     * 接口是否需要登录.
     * @return 是否徐徐
     */
    protected boolean isRequireLogin() {
        return true;
    }

    public UcUserInfo getLogin() {
        return login;
    }

    public void setLogin(UcUserInfo login) {
        this.login = login;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
    
}
