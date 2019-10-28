package cn.com.hugedata.spark.management.commons;

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
     * 在Session中保存权限配置信息的key.
     */
    public static final String PRIVILEGE_SESSION_KEY = "PrivilegeInfo";

    /**
     * 在Session中保存登录信息的Key.
     */
    public static final String LOGIN_INFO_SESSION_KEY = "ManagementLoginInfo";

    /**
     * 在Session中保存当前激活的系统类型的key.
     */
    public static final String ACTIVE_SYSTEM_TYPE_SESSION_KEY = "ActiveSystemType";
    
    /**
     * 不允许创建实例.
     */
    private ServerConstants() {
    }
}
