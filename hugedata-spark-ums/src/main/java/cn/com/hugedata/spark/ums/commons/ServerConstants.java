package cn.com.hugedata.spark.ums.commons;

import java.util.Locale;

/**
 * 服务端相关的常量.
 * @author gaopeng
 */
public final class ServerConstants {

    /**
     * 默认语言.
     */
    public static final String DEFAULT_LOCALE_CODE = "zh-CN";

    /**
     * 默认语言.
     */
    public static final Locale DEFAULT_LOCALE = Locale.forLanguageTag(DEFAULT_LOCALE_CODE);

    /**
     * 不允许创建实例.
     */
    private ServerConstants() {
    }
}
