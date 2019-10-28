package cn.com.hugedata.spark.commons.cache.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 用于统计缓存的性能数据.
 * @author gaopeng
 */
@Service
public class CacheProfilerService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheProfilerService.class);
    
    /**
     * 记录类型：缓存命中.
     */
    public static final int TYPE_CACHE_HIT = 1;

    /**
     * 记录类型：缓存未命中，从数据库成功读取.
     */
    public static final int TYPE_CACHE_MISS = 2 ;

    /**
     * 记录类型：从缓存加载异常.
     */
    public static final int TYPE_EXCEPTION_READ_CACHE = 3;

    /**
     * 是否启用性能数据收集.
     */
    private static final boolean PROFILER_ENABLED = true;
    
    /**
     * 在Redis中保存性能数据的Key.
     */
    private static final String PROFILER_CACHE_KEY = "CacheSummary";
    
    /**
     * 用于读取和保存String的RedisTemplate.
     */
    @Autowired
    private RedisTemplate<String, String> stringTemplate;
    
    /**
     * 在内存中保存的性能数据.
     */
    private Map<String, CacheProfilerInfo> data = new HashMap<String, CacheProfilerInfo>();
    
    /**
     * 用于计数.
     */
    private long updateCounter = 0;
    
    /**
     * 更新一个缓存数据
     * @param cacheKey 缓存Key
     * @param logType  记录类型
     */
    public void update(String cacheKey, int logType) {
        if (!PROFILER_ENABLED) {
            return;
        }
//        LOGGER.debug("Update cache summary: {}, {}", cacheKey, logType);
        
        // 获得用于统计的Profile Key
        String profileKey = getProfileKey(cacheKey);
        
        // 在内存中记录
        updateCounter++;
        CacheProfilerInfo info = data.get(profileKey);
        if (info == null) {
            info = new CacheProfilerInfo();
            info.profileKey = profileKey;
            data.put(profileKey, info);
        }
        
        switch (logType) {
            case TYPE_CACHE_HIT:
                info.hit++;
                break;
            case TYPE_CACHE_MISS:
                info.miss++;
                break;
            case TYPE_EXCEPTION_READ_CACHE:
                info.exceptionReadCache++;
                break;
        }

        // 每1000次输出日志并记录到Redis
        if (updateCounter % 1000 == 0) {
            logCacheSummary();
        }
    }

    /**
     * 输出缓存统计到日志.
     */
    private void logCacheSummary() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, CacheProfilerInfo> e : data.entrySet()) {
            CacheProfilerInfo i = e.getValue();
            String ratio = String.format("%.2f", i.hit * 10000 / (i.hit + i.miss + i.exceptionReadCache) / 100.0f);
            sb.append(String.format("[CacheSummary] %s: hit:%d miss:%d exception:%d hit-ratio:%s%%",
                i.profileKey, i.hit, i.miss, i.exceptionReadCache, ratio));
            sb.append("\n");
        }
        LOGGER.info("Cache summary ({}):", updateCounter);
        LOGGER.info(sb.toString());
        
        stringTemplate.opsForValue().set(PROFILER_CACHE_KEY, sb.toString());
    }
    
    /**
     * 从缓存Key值中获取用于统计性能的key值.
     * @param cacheKey 缓存Key
     * @return         性能统计的Key
     */
    private String getProfileKey(String cacheKey) {
        int pos = cacheKey.indexOf(CacheUtils.KEY_SEPARATOR);
        if (pos < 0) {
            return cacheKey;
        }
        pos = cacheKey.indexOf(CacheUtils.KEY_SEPARATOR, pos + 2);
        if (pos < 0) {
            return cacheKey;
        }
        pos = cacheKey.indexOf(CacheUtils.KEY_SEPARATOR, pos + 2);
        if (pos < 0) {
            return cacheKey;
        }
        return cacheKey.substring(0, pos);
    }
    
    /**
     * 缓存命中信息.
     */
    public static class CacheProfilerInfo {
        
        private String profileKey;
        
        private long hit = 0;
        
        private long miss = 0;
        
        private long exceptionReadCache = 0;

    }
}
