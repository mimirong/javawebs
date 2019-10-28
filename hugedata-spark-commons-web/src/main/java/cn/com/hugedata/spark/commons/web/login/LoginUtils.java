package cn.com.hugedata.spark.commons.web.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.hugedata.spark.commons.service.session.LoginSessionInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;

public class LoginUtils {

    /**
     * 获取当前登录信息.
     * @return 登录信息
     */
    public static LoginSessionInfo getCurrentLoginSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        return (LoginSessionInfo) request.getAttribute(LoginConstants.LOGIN_SESSION_REQUEST_KEY);
    }
    
    /**
     * 返回当前请求是否已经登录.
     * @return 当前请求是否已经登录
     */
    public static boolean isLoggedIn() {
        return getCurrentLoginSession() != null;
    }
    
    /**
     * 获取当前登录的用户信息.
     * @return 当前登录的用户信息
     */
    public static UcUserInfo getCurrentLogin() {
        LoginSessionInfo s = getCurrentLoginSession();
        if (s == null) {
            return null;
        }
        return s.getUserInfo();
    }
    
    /**
     * 获取当前登录的Token.
     * @return 当前登录的Token.
     */
    public static String getCurrentLoginToken() {
        LoginSessionInfo s = getCurrentLoginSession();
        if (s == null) {
            return null;
        }
        return s.getToken();
    }

    /**
     * 不允许创建实例.
     */
    private LoginUtils() {
    }
}
