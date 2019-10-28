package cn.com.hugedata.spark.commons.web.login;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.service.session.LoginSessionInfo;
import cn.com.hugedata.spark.commons.service.session.LoginSessionService;
import cn.com.hugedata.spark.commons.web.AjaxResponse;

/**
 * 用于在请求时获取登录信息的Interceptor.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
    
    /**
     * AJAX请求中返回需要登录的错误代码.
     */
    private static final int AJAX_REQUIRE_LOGIN_RESPONSE = 9;
    
    @Autowired
    private LoginSessionService loginSessionService;
    
    @Autowired
    private UrlManager urlManager;
    
    /**
     * 默认的登录成功跳转URL.
     */
    private String defaultRedirectUrl;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
            throws Exception {
        // 方法结束时是否需要跳转至登录页面
        boolean requireLogin = true;
        
        // 登录成功后返回的URL
        String loginRedirectUrl = null;
        
        // 获取当前登录的用户信息
        String loginToken = request.getParameter(LoginConstants.LOGIN_TOKEN_REQUEST_PARAM);
        if (StringUtils.isEmpty(loginToken)) {
            loginToken = findLoginTokenFromCookie(request);
        } else {
            setLoginCookie(request, response, loginToken);
        }
        
        LoginSessionInfo loginSession = null;
        if (StringUtils.isNotEmpty(loginToken)) {
            loginSession = loginSessionService.findLoginInfo(loginToken);
        }
        
        // 获取当前登录的用户信息，并判断用户角色是否满足HandlerMethod配置的要求
        if (loginSession != null && loginSession.getUserInfo() != null) {        
            loginSessionService.refreshSession(loginToken);
            request.setAttribute(LoginConstants.LOGIN_SESSION_REQUEST_KEY, loginSession);
            request.setAttribute(LoginConstants.LOGIN_USER_REQUEST_KEY, loginSession.getUserInfo());
            requireLogin = false; 
            LOGGER.debug("Login user found: {}", loginSession.getUserInfo().getUserId());
        }
        
        // 判断HandlerMethod中配置的是否需要登录
        if (requireLogin && handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            
            // 处理Login注解
            Login a = hm.getMethodAnnotation(Login.class);
            if (a != null) {
                if (a.required()) {
                    loginRedirectUrl = a.redirectUrl();
                } else {
                    requireLogin = false;
                }
            }
        }
        
        // 如果仍然需要登录跳转到登录页面
        if (requireLogin) {
            // AJAX请求
            if (checkAjaxRequest(request)) {
                try (PrintWriter w = response.getWriter()) {
                    response.setContentType("application/json");
                    AjaxResponse resp = new AjaxResponse();
                    resp.setResult(AJAX_REQUIRE_LOGIN_RESPONSE);
                    resp.setMessage("请重新登录");
                    resp.setData(new Object[0]);  //让Datatables能够正常处理
                    w.println(JSON.toJSONString(resp));
                    return false;
                }
            } else {
                String url = urlManager.getUmsUrl() + "/login?intercept=1";
                String redirectUrl = "";
                if (StringUtils.isNotEmpty(loginRedirectUrl)) {
                    // 由@Login注解指定了跳转URL
                    redirectUrl = getRedirectUrlPrefix(request) + loginRedirectUrl;
                } else if (request.getMethod().equals("GET")) {
                    // GET请求
                    redirectUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
                } else {
                    // 其它
                    redirectUrl = (StringUtils.isNotEmpty(defaultRedirectUrl) ? defaultRedirectUrl : urlManager.getGovAffairsPortalUrl());
                }
                url += "&redirect=" + URLEncoder.encode(redirectUrl, "UTF-8");
                response.sendRedirect(url);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 判断一个请求是否为Ajax请求.
     * @param request Http请求
     * @return        请求是否为Ajax请求
     */
    private boolean checkAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        return (!accept.contains("text/html") && accept.contains("application/json"));
    }
    
    /**
     * 在Cookie中保存登录信息.
     * @param request     Http Request
     * @param response    Http Response
     * @param loginToken  登录token
     */
    private void setLoginCookie(HttpServletRequest request, HttpServletResponse response, String loginToken) {
        Cookie cookie = new Cookie(LoginConstants.LOGIN_TOKEN_COOKIE_KEY, loginToken);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }

    /**
     * 获取跳转URL中http://xxx:nn/contextPath部分.
     * @param request HttpRequest
     * @return URL部分
     */
    private String getRedirectUrlPrefix(HttpServletRequest request) {
        int pos = 0;
        String url = request.getHeader("Referer");
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        if (url.startsWith("http://")) {
            pos += "http://".length();
        } else if (url.startsWith("https://")) {
            pos += "https://".length();
        }
        pos = url.indexOf('/', pos);
        return url.substring(0, pos) + request.getContextPath();
    }
    
    /**
     * 从Cookie中获取登录token.
     * @param request Http Request
     * @return        登录Token
     */
    private String findLoginTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        
        // 从cookie获取loginkey
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LoginConstants.LOGIN_TOKEN_COOKIE_KEY.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        
        return null;
    }

    public String getDefaultRedirectUrl() {
        return defaultRedirectUrl;
    }

    public void setDefaultRedirectUrl(String defaultRedirectUrl) {
        this.defaultRedirectUrl = defaultRedirectUrl;
    }
}
