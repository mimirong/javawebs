package cn.com.hugedata.spark.commons.cache.services;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheUpdateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheUpdateService.class);
    
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据缓存的key删除缓存的数据
     * @param pattern 缓存key，可以包含通配符(*)
     */
    public void deleteCachedValues(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        LOGGER.info("Clearing cache: {} -> {}", pattern, StringUtils.join(keys, ","));
        redisTemplate.delete(keys);
    }
    
}
