package cn.com.hugedata.spark.govaffairs.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.utils.JarMetaUtils;

/**
 * Spring MVC通用拦截器，用于在ModelAndView增加contextPath等属性(.
 * 
 * @author 高鹏
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    
    private static String buildTimestamp = null;

    @Autowired
    private UrlManager urlManager;
    
    static {
        buildTimestamp = JarMetaUtils.findEntry("Build-Timestamp");
        if (StringUtils.isEmpty(buildTimestamp) || buildTimestamp.contains(" ")) {
            buildTimestamp = "" + System.currentTimeMillis();
        }
    }
    
	@Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView mv)
            throws Exception {
        if (mv != null && !mv.getViewName().startsWith("redirect:")) {
            mv.addObject("buildTimestamp", buildTimestamp);
            mv.addObject("contextPath", req.getContextPath());
            mv.addObject("downloadServiceUrl", urlManager.getDownloadServiceUrl());
            mv.addObject("umsUrl", urlManager.getUmsUrl());
            mv.addObject("govAffairsPortalUrl", urlManager.getGovAffairsPortalUrl());
            mv.addObject("managementUrl", urlManager.getManagementUrl());
            mv.addObject("oaWebUrl", urlManager.getOaWebUrl());
        }
    }

}
