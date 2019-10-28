package cn.com.hugedata.spark.admin.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用于获取登录信息并保存到HttpRequest.
 * @author yejun
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginInterceptor.class);
    
    @Autowired
    private AdminLoginService adminLoginService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    		throws Exception {
        // 方法结束时是否需要跳转至登录页面
        boolean requireLogin = true;
        
        // 获取当前登录的用户信息，并判断用户角色是否满足HandlerMethod配置的要求
        boolean loggedIn = adminLoginService.loadLoginInfo();
        if (loggedIn) {
            requireLogin = false;
        } else {
            LOGGER.debug("Login user info not found.");
        }
        
        // 判断HandlerMethod中配置的是否需要登录
        if (requireLogin && handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Login a = hm.getMethodAnnotation(Login.class);
            if (a != null && !a.required()) {
                requireLogin = false;
                LOGGER.debug("Login not required due to Login annotation");
                //请求头部包含XMLHttpRequest,说明是ajax请求
                if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))){
                	//对于ajax请求，若会话超时,则设置一个sessionstatus，在前端 $.ajaxSetup会获取这个值
                	response.setHeader("sessionstatus", "timeout");
                }
            }
        }
        
        // 如果仍然需要登录跳转到登录页面
        if (requireLogin) {
            String url = request.getContextPath() + "/user/login.aspx";
            response.sendRedirect(url);
            return false;
        }
        
        return true;
    }
}
