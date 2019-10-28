package cn.com.hugedata.spark.connect;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.session.LoginSessionInfo;
import cn.com.hugedata.spark.commons.service.session.LoginSessionService;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;

/**
 * 用于测试Handler的基类.
 * @author gaopeng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext-test.xml", 
        "classpath:applicationContext-common.xml" })
@Ignore
public class HandlerTestBase implements ApplicationContextAware, HandlerTestConstants {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerTestBase.class);

    @Autowired
    private UcUserInfoMapper userInfoMapper;
    
    @Autowired
    private LoginSessionService loginSessionService;
    
    private ApplicationContext applicationContext;

    /**
     * 创建用于测试的Handler实例.
     * @param clazz             Handler类
     * @return                  创建的Handler实例
     * @throws ServiceException Hanlder初始化失败
     */
    @SuppressWarnings("unchecked")
    protected <T extends BaseHandler> T createHandler(Class<T> clazz) {
        try {
            if (HandlerMapper.findMethodByHandlerClass(clazz) == null) {
                LOGGER.error("Handler not registered in HandlerMapper.");
                throw new RuntimeException("Handler not registered in HandlerMapper.");
            }
            
            BaseHandler handler = clazz.newInstance();
            applicationContext.getAutowireCapableBeanFactory().autowireBean(handler);
            handler.setApplicationContext(applicationContext);
            handler.initialize();
            return (T) handler;
            
        } catch (InstantiationException e) {
            LOGGER.error("Failed to create handler instance", e);
            throw new RuntimeException(e);
            
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to create handler instance", e);
            throw new RuntimeException(e);
            
        } catch (ServiceException e) {
            LOGGER.error("Failed to initialize handler", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 执行Handler.
     * @param handler 需要执行的Handler实例
     * @param req     请求数据
     * @return        响应数据
     */
    protected JSONObject runWithoutLogin(BaseHandler handler, JSONObject req) {
        InterfaceResponse resp;
        try {
            resp = handler.executeInner(req);
        } catch (ServiceException e) {
            resp = InterfaceResponse.createFailResponse(handler.findErrorCode(e.getErrorCode()), e.getMessage());
        }
        String json = resp.toJSONString();
        System.out.println("==========");
        System.out.println(json);
        System.out.println("==========");
        return JSON.parseObject(json);
    }
    
    /**
     * 以默认用户登录并执行Handler测试.
     * @param handler 需要执行的Handler实例
     * @param req     请求数据
     * @return        响应数据
     */
    protected JSONObject runLoginWithDefault(BaseHandler handler, JSONObject req) {
        return runLoginWithUserId(handler, req, MOCK_USER_ID);
    }
    
    /**
     * 以指定用户登录并执行Handler测试.
     * @param handler 需要执行的Handler实例
     * @param req     请求数据
     * @param userId  登录的用户ID
     * @return        响应数据
     */
    protected JSONObject runLoginWithUserId(BaseHandler handler, JSONObject req, int userId) {
        InterfaceResponse resp;
        try {
            LoginSessionInfo session = createLoginSessionForUserId(userId);
            handler.setLogin(session.getUserInfo());
            
            resp = handler.executeInner(req);
        } catch (ServiceException e) {
            resp = InterfaceResponse.createFailResponse(handler.findErrorCode(e.getErrorCode()), e.getMessage());
        }
        String json = resp.toJSONString();
        System.out.println("==========");
        System.out.println(json);
        System.out.println("==========");
        return JSON.parseObject(json);
    }
    
    /**
     * 创建一个用于测试的LoginSession信息.
     * @param userId 登录用户ID
     * @return    登录Session信息
     */
    private LoginSessionInfo createLoginSessionForUserId(int userId) {
        // 查询用户信息
        UcUserInfo user = userInfoMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("Login userId not found: " + userId);
        }
        
        LoginSessionInfo session = new LoginSessionInfo();
        session.setUserInfo(user);
        session.setToken(loginSessionService.createToken("MOCK", String.valueOf(userId)));
        session.setCreateTime(System.currentTimeMillis());
        return session;
    }
    
    /**
     * Assert返回结果为成功.
     * @param resp 返回结果JSON
     */
    protected void assertSuccess(JSONObject resp) {
        assertEquals(InterfaceResponse.RESPONSE_SUCCESS, resp.getIntValue("result"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
}
