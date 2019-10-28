package cn.com.hugedata.spark.commons.cache.base;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import cn.com.hugedata.spark.commons.exception.ServiceException;

/**
 * 所有缓存的基类.
 * 
 * @author 高鹏
 */
public class BaseCache {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCache.class);

    /**
     * 用于读取和保存String的RedisTemplate.
     */
    @Autowired
    protected RedisTemplate<String, String> stringTemplate;

    /**
     * 用于读取和保存Serializable的RedisTemplate.
     */
    @Autowired
    protected RedisTemplate<String, Serializable> objectTemplate;
    
    /**
     * 缓存性能监控工具.
     */
    @Autowired
    private CacheProfilerService cacheProfilerService;
    
    /**
     * 从数据库或缓存读取数据，并返回数据.
     * @param cacheKey          保存缓存数据的Key (仅用于日志)
     * @param forceReload       是否强制从数据库加载
     * @param callback          实现CacheItem接口，并处理缓存读写
     * @param <T>               缓存数据的类型
     * @return                  从缓存或数据库读取的数据
     * @throws ServiceException 读取时发生异常
     */
    protected <T> T process(String cacheKey, boolean forceReload, CacheProcessCallback<T> callback)
            throws ServiceException {
        // 从缓存读取
        if (!forceReload) {
            try {
                T value = callback.readFromCache();
                if (value != null) {
                    LOGGER.debug("Loaded from cache: {}", cacheKey);
                    cacheProfilerService.update(cacheKey, CacheProfilerService.TYPE_CACHE_HIT);
                    return callback.beforeReturn(value);
                }
            } catch (ServiceException e) {
                throw e;
            } catch (Exception e) {
                LOGGER.error("Failed to read from cache: " + cacheKey + ", clearing cache.", e);
                objectTemplate.delete(cacheKey);
                cacheProfilerService.update(cacheKey, CacheProfilerService.TYPE_EXCEPTION_READ_CACHE);
            }
        }
        
        // 从数据库读取
        LOGGER.info("Loading from database: {}", cacheKey);
        T value = callback.readFromDatabase();
        
        // 保存到缓存
        if (value != null) {
            try {
                callback.writeToCache(value);
            } catch (ServiceException e) {
                throw e;
            } catch (Exception e) {
                LOGGER.error("Failed to write to cache: " + cacheKey, e);
            }
        }

        cacheProfilerService.update(cacheKey, CacheProfilerService.TYPE_CACHE_MISS);
        return callback.beforeReturn(value);
    }

    public RedisTemplate<String, String> getStringTemplate() {
        return stringTemplate;
    }

    public void setStringTemplate(RedisTemplate<String, String> stringTemplate) {
        this.stringTemplate = stringTemplate;
    }

    public RedisTemplate<String, Serializable> getObjectTemplate() {
        return objectTemplate;
    }

    public void setObjectTemplate(RedisTemplate<String, Serializable> objectTemplate) {
        this.objectTemplate = objectTemplate;
    }
}
