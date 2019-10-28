package cn.com.hugedata.spark.commons.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 国际化相关的工具类.
 * @author gaopeng
 */
public final class GlobalI18nUtils {

    /**
     * 服务端支持的语言列表
     */
    private static List<Locale> locales = Arrays.asList(
            Locale.forLanguageTag("zh-CN"),
            Locale.forLanguageTag("en")
    );
    
    /**
     * 获取所有支持的语言.
     * @return 所有支持的语言
     */
    public static List<Locale> getAvailableLocales() {
        return locales;
    }
    
}
