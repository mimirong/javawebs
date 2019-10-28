package cn.com.hugedata.spark.commons.web.utils;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 用于在Web项目中获取国际化文本.
 * @author gaopeng
 */
public final class WebApplicationI18nUtils {
    
    /**
     * 获取消息.
     * @param request Http Request
     * @param code    消息key
     * @return        消息文本
     */
    public static String getMessage(HttpServletRequest request, String code) {
        ServletContext sc = request.getSession().getServletContext();
        Locale locale = RequestContextUtils.getLocale(request);
        return getMessageSource(sc, code).getMessage(code, null, locale);
    }
    
    /**
     * 获取消息.
     * @param sc   ServletContext
     * @param code 消息key
     * @param args 消息参数
     * @return     消息文本
     */
    public static String getMessage(HttpServletRequest request, String code, Object[] args) {
        ServletContext sc = request.getSession().getServletContext();
        Locale locale = RequestContextUtils.getLocale(request);
        return getMessageSource(sc, code).getMessage(code, args, locale);
    }
    
    /**
     * 在Web项目中获取MessageSource.
     * @param sc   ServletContext
     * @param code 消息code
     * @return     MessageSource实例
     */
    private static MessageSource getMessageSource(ServletContext sc, String code) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
        if (applicationContext == null) {
            throw new NoSuchMessageException(code);
        }
        
        MessageSource messageSource = applicationContext.getBean(MessageSource.class);
        if (messageSource == null) {
            throw new NoSuchMessageException(code);
        }
        
        return messageSource;
    }

    /**
     * 不允许创建实例.
     */
    private WebApplicationI18nUtils() {
    }
}
