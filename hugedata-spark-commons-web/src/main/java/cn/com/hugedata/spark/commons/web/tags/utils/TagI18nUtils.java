package cn.com.hugedata.spark.commons.web.tags.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import cn.com.hugedata.spark.commons.web.utils.WebApplicationI18nUtils;

/**
 * 用于处理标签文本国际化.
 * @author gaopeng
 */
public class TagI18nUtils {

    /**
     * 在标签中获取国际化文本，如果文本以"!"开头，直接返回原文本.
     * @param request Http Request
     * @param str     国际化文本key 或 以"!"开头的文本
     * @return        国际化文本
     */
    public static String makeI18nText(HttpServletRequest request, String str) {
        if (str.startsWith("!")) {
            return str.substring(1);
        }
        return WebApplicationI18nUtils.getMessage(request, str);
    }

    /**
     * 在标签中获取国际化文本，如果文本以"!"开头，直接返回原文本.
     * @param sc  PageContext实例
     * @param str 国际化文本key 或 以"!"开头的文本
     * @return    国际化文本
     */
    public static String makeI18nText(PageContext pc, String str) {
        return makeI18nText((HttpServletRequest) pc.getRequest(), str);
    }
}
