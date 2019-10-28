package cn.com.hugedata.spark.management.services;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 用于保存待打印内容的Service.
 */
@Service
public class PrintCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCacheService.class);
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 保存需要打印的数据到Redis.
     * @param title 标题
     * @param html  需要打印的页面HTML
     * @return      保存的Key，用于进入打印页面
     */
    public String save(String title, String html) {
        String key = createKey();
        String titleKey = key + "_title";
        LOGGER.info("Save for print: {}", key);
        redisTemplate.opsForValue().set(key, html, 60, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(titleKey, title, 60, TimeUnit.MINUTES);
        return key;
    }
    
    /**
     * 根据保存时返回的key，获取需要打印的数据.
     * @param key 保存时返回的Key
     * @return    需要打印的数据
     */
    public PrintData load(String key) {
        PrintData ret = new PrintData();
        ret.data = redisTemplate.opsForValue().get(key);
        ret.title = redisTemplate.opsForValue().get(key + "_title");
        return ret;
    }
    
    /**
     * 生成一个用于在Redis中保存数据的Key.
     * @return 用于在Redis中保存数据的Key
     */
    private String createKey() {
        return "PRINT_" + System.currentTimeMillis() + RandomStringUtils.randomAlphanumeric(16);
    }
    
    /**
     * 打印数据.
     */
    public static class PrintData {
        
        /** 标题. */
        private String title;
        
        /** 页面HTML. */
        private String data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
