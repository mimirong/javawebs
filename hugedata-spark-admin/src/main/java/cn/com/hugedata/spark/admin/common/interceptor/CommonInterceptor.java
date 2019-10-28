package cn.com.hugedata.spark.admin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.hugedata.spark.commons.service.UrlManager;

/**
 * Spring MVC通用拦截器，用于在ModelAndView增加contextPath等属性(.
 * 
 * @author 高鹏
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UrlManager urlManager;
    
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView mv)
            throws Exception {
        if (mv != null && !mv.getViewName().startsWith("redirect:")) {
            mv.addObject("contextPath", req.getContextPath());
            mv.addObject("umsUrl", urlManager.getUmsUrl());
            mv.addObject("managementUrl", urlManager.getManagementUrl());
        }
    }

}
