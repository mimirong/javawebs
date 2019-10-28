package cn.com.hugedata.spark.management.services.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.service.session.LoginSessionInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRight;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.commons.web.login.LoginConstants;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.management.commons.ServerConstants;

/**
 * 用于处理后台登录权限.
 */
public class ManagementLoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementLoginInterceptor.class);
    
    /** AJAX请求中返回需要登录的错误代码. */
    private static final int AJAX_REQUIRE_LOGIN_RESPONSE = 9;

    @Autowired
    private PrivilegeLoaderService privilegeLoaderService;
    
    @Autowired
    private UrlManager urlManager;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!checkLogin(request, response, handler)) {
            return false;
        }
        
        // 获取当前激活的系统类型
        String systemType = findActiveSystemType(request);
        
        // 获取当前登录的用户信息
        UcUserInfo user = LoginUtils.getCurrentLogin();
        if (user == null) {
            sendRedirectToNoPrivilegePage(request, response);
            return false;
        }
        
        // 检查用户类型
        if (!user.getUserType().equals(UcUserInfo.USER_TYPE_PARK)
                && !user.getUserType().equals(UcUserInfo.USER_TYPE_SYSTEM)) {
            sendRedirectToNoPrivilegePage(request, response);
            return false;
        }
        
        // 加载或读取权限
        PrivilegeInfo privilege = privilegeLoaderService.loadPrivilegeInfo(user);
        privilege.setSystemType(systemType);
        
        // 检查权限
        if (privilege == null || !privilege.checkPrivilege(request.getRequestURI())) {
            sendRedirectToNoPrivilegePage(request, response);
            return false;
        }
        
        return super.preHandle(request, response, handler);
    }

    /**
     * 处理登录用户登录.
     */
    public boolean checkLogin(HttpServletRequest request, HttpServletResponse response, Object handler) 
            throws Exception {
        HttpSession session = request.getSession();
        
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
            loginSession = (LoginSessionInfo) session.getAttribute(ServerConstants.LOGIN_INFO_SESSION_KEY);
        }
        
        // 获取当前登录的用户信息，并判断用户角色是否满足HandlerMethod配置的要求
        if (loginSession != null && loginSession.getUserInfo() != null) {
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
                String url = urlManager.getManagementUrl() + "/login/?intercept=1";
                if (StringUtils.isNotEmpty(loginRedirectUrl)) {
                    String redirectUrl = getRedirectUrlPrefix(request) + loginRedirectUrl;
                    url += "&redirect=" + URLEncoder.encode(redirectUrl, "UTF-8");
                } else {
                    String redirectUrl = urlManager.getManagementUrl();
                    url += "&redirect=" + URLEncoder.encode(redirectUrl, "UTF-8");
                }
                response.sendRedirect(url);
                return false;
            }
        }
        
        return true;
    }

    /**
     * 获取当前激活的系统类型. 默认返回BASIC.
     */
    private String findActiveSystemType(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute(ServerConstants.ACTIVE_SYSTEM_TYPE_SESSION_KEY);
        if (StringUtils.isEmpty(type)) {
            return UcManageRight.SYSTEM_TYPE_APPROVAL;
        }
        return type;
    }
    
    /**
     * 跳转到"没有权限"页面.
     * @throws IOException 
     */
    private void sendRedirectToNoPrivilegePage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/noPrivilege");
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
}
