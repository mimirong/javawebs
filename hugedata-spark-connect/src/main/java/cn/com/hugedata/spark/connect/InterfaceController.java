package cn.com.hugedata.spark.connect;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.session.LoginSessionInfo;
import cn.com.hugedata.spark.commons.service.session.LoginSessionService;
import cn.com.hugedata.spark.commons.storage.entity.LogInterfaceCall;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.LogInterfaceCallMapper;

@Controller
@RequestMapping("/connect")
public class InterfaceController implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceController.class);
    
    @Autowired
    private LoginSessionService loginSessionService;
    
    @Autowired
    private LogInterfaceCallMapper logInterfaceCallMapper;
    
    private ApplicationContext applicationContext;
    
    /**
     * APP接口入口.
     * @param method            接口方法名
     * @param requestData       请求数据
     * @param loginSessionToken 登录Token
     */
    @RequestMapping(value = { "", "/", "execute" })
    public void execute(
            @RequestParam(value = "method", required = false) String method,
            @RequestParam(value = "data", required = false, defaultValue = "{}") String requestData,
            @RequestParam(value = "session", required = false) String loginSessionToken,
            @RequestHeader(value = "Accept-Encoding", required = false) String acceptEncoding,
            HttpServletResponse response) throws IOException {
        
        try (OutputStream out = response.getOutputStream()) {
            Date start = new Date();
            
            LOGGER.info("Request [{}]: {}", method, requestData);
            ExecuteResult result = executeInner(method, requestData, loginSessionToken);
            String resp = result.response.toJSONString();
            LOGGER.info("Response [{}]: {}", method, resp);
            
            // 记录接口日志
            LogInterfaceCall log = new LogInterfaceCall();
            LoginSessionInfo loginSessionInfo = result.session;
            if(null != loginSessionInfo && null != loginSessionInfo.getUserInfo()) {
                UcUserInfo userInfo = loginSessionInfo.getUserInfo();
                log.setUserId(userInfo.getUserId());
            }
            log.setMethod(method);
            log.setClientType(result.session != null ? result.session.getPlatform() : null);
            log.setClientVersion(result.session != null ? result.session.getVersion() : null);
            log.setClientVersionCode(result.session != null ? result.session.getVersionCode() : null);
            log.setDeviceId(result.session != null ? result.session.getDeviceId() : null);
            log.setDeviceModel(result.session != null ? result.session.getDeviceModel() : null);
            log.setSystemVersion(result.session != null ? result.session.getSystemVersion() : null);
            log.setRequestTime(start);
            log.setProcessDuration((int) (System.currentTimeMillis() - start.getTime()));
            log.setIsSuccess(0 == result.response.getResult());
            log.setErrorMessage(result.response.getMessage());
            log.setRequestLength(requestData.length());
            log.setResponseLength(resp.length());
            logInterfaceCallMapper.insertSelective(log);
            
            // 发送响应
            response.setContentType("application/json; charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            if (resp.length() > 0 && checkAcceptGzip(acceptEncoding)) {
                response.setHeader("Content-Encoding", "gzip");
                try (GZIPOutputStream gout = new GZIPOutputStream(out)) {
                    gout.write(resp.getBytes("UTF-8"));
                }
            } else {
                out.write(resp.getBytes("UTF-8"));
            }
            response.flushBuffer();
        }
    }

    /**
     * 检查并处理接口操作.
     * @param method            接口方法名
     * @param requestData       请求数据
     * @param loginSessionToken 登录Token
     * @return                  处理结果
     */
    private ExecuteResult executeInner(String method, String requestData, String loginSessionToken) {
        try {
            // 判断参数为空
            if (StringUtils.isEmpty(method)) {
                LOGGER.error("Parameter is empty.");
                return new ExecuteResult(InterfaceResponse.createFailResponse("method不能为空"));
            }
            
            // 获取Handler
            Class<? extends BaseHandler> handlerClass = HandlerMapper.findHandlerClassByMethod(method);
            if (handlerClass == null) {
                LOGGER.error("Method not found: {}", method);
                return new ExecuteResult(InterfaceResponse.createFailResponse("接口不存在"));
            }
            
            // 创建Handler
            BaseHandler handler = handlerClass.newInstance();
            
            // 初始化Handler
            try {
                applicationContext.getAutowireCapableBeanFactory().autowireBean(handler);
                handler.setApplicationContext(applicationContext);
                handler.initialize();
            } catch (ServiceException e) {
                LOGGER.error("Failed to initialize handler.", e);
                return new ExecuteResult(InterfaceResponse.createFailResponse("无法初始化接口"));
            }
            
            // 处理请求参数
            JSONObject requestJson = JSON.parseObject(requestData);
            
            // 获取LoginSession
            LoginSessionInfo loginSession = null;
            if (StringUtils.isEmpty(loginSessionToken)) {
                loginSessionToken = requestJson.getString("session");
            }
            if (StringUtils.isNotEmpty(loginSessionToken)) {
                loginSession = loginSessionService.findLoginInfo(loginSessionToken);
                if (loginSession != null) {
                    handler.setLogin(loginSession.getUserInfo());
                    handler.setLoginToken(loginSessionToken);
                    loginSessionService.refreshSession(loginSessionToken);
                }
            }
            
            // 判断是否需要登录
            if (handler.isRequireLogin() && loginSession == null) {
                LOGGER.error("Login session token is empty.");
                return new ExecuteResult(InterfaceResponse.createFailResponse(
                        InterfaceResponse.RESPONSE_REQUIRE_LOGIN, "接口需要登录"));
            }
            
            // 调用接口服务
            InterfaceResponse resp;
            try {
                LOGGER.info("Invoking handler: {}", handler.getClass().getSimpleName());
                resp = handler.executeInner(requestJson);
            } catch (ServiceException e) {
                LOGGER.error("Failed to execute handler.", e);
                resp = InterfaceResponse.createFailResponse(handler.findErrorCode(e.getErrorCode()), e.getMessage());
            }
            
            return new ExecuteResult(resp, loginSession);
            
        } catch (InstantiationException e) {
            LOGGER.error("Failed to create handler instance.", e);
            return new ExecuteResult(InterfaceResponse.createFailResponse("创建接口服务失败"));
            
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to create handler instance.", e);
            return new ExecuteResult(InterfaceResponse.createFailResponse("创建接口服务失败"));

        } catch (JSONException e) {
            LOGGER.error("Failed to parse request json.", e);
            return new ExecuteResult(InterfaceResponse.createFailResponse("无法解析请求数据"));
            
        } catch (Exception e) {
            LOGGER.error("Failed to invoke handler", e);
            return new ExecuteResult(InterfaceResponse.createFailResponse("接口处理失败"));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    private boolean checkAcceptGzip(String acceptEncoding) {
        if (StringUtils.isEmpty(acceptEncoding)) {
            return false;
        }
        
        String[] strs = acceptEncoding.split(",");
        for (String str : strs) {
            if (str.trim().equals("gzip")) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 接口执行结果.
     */
    private static class ExecuteResult {
        InterfaceResponse response;
        LoginSessionInfo session;
        
        ExecuteResult(InterfaceResponse response) {
            this.response = response;
        }
        
        ExecuteResult(InterfaceResponse response, LoginSessionInfo session) {
            this.response = response;
            this.session = session;
        }
    }
}
