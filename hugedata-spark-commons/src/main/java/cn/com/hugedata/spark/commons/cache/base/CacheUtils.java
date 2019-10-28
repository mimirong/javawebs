package cn.com.hugedata.spark.commons.cache.base;

public class CacheUtils {

    public static final String KEY_SEPARATOR = "//";
    
    /**
     * 创建保存到Redis中的key.
     * @param module 模块名，如AppCache
     * @param name   名称，如App
     * @param args   参数，如AppId
     * @return       缓存使用的key
     */
    public static String createCacheKey(String module, String name, String...args) {
        StringBuilder sb = new StringBuilder();
        sb.append("CACHE");
        sb.append(KEY_SEPARATOR);
        sb.append(module);
        sb.append(KEY_SEPARATOR);
        sb.append(name);
        for (String str : args) {
            sb.append(KEY_SEPARATOR).append(str);
        }
        return sb.toString();
    }
    
}
