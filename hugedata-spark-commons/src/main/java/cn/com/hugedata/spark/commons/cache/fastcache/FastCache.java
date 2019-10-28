package cn.com.hugedata.spark.commons.cache.fastcache;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 内存缓存用于提高查询速度.
 * 
 * @param <K> 缓存Key类型
 * @param <V> 缓存Value类型
 * 
 * @author gaopeng
 */
public class FastCache<K, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastCache.class);
    
    private static final boolean DEBUG = false;
    
    /** 默认的缓存最大保存数量. */
    private static final int DEFAULT_MAX_SIZE = 32;
    
    /** 缓存的数据. */
    private HashMap<K, FastCacheItem<K, V>> cached = new HashMap<K, FastCacheItem<K, V>>();
    
    /** 用于操作缓存数据的锁. */
    private ReentrantReadWriteLock locker = new ReentrantReadWriteLock();
    
    /** 缓存查询回调. */
    private FastCacheCallback<K, V> callback;
    
    /** 缓存最大保存数量. */
    private int maxCacheSize;
    
    /** 缓存有效期. */
    private int expireInSeconds = 0;

    /**
     * 创建缓存工具类实例.
     * @param callback     查询方法
     */
    public FastCache(FastCacheCallback<K, V> callback) {
        this(callback, DEFAULT_MAX_SIZE);
    }

    /**
     * 创建缓存工具类实例.
     * @param callback     查询方法
     * @param maxCacheSize 缓存最大数量
     */
    public FastCache(FastCacheCallback<K, V> callback, int maxCacheSize) {
        super();
        this.callback = callback;
        this.maxCacheSize = maxCacheSize;
    }
    
    /**
     * 设置缓存超时时间.
     * @param timeInSeconds 超时时间，单位为秒
     */
    public void setExpireTime(int timeInSeconds) {
        this.expireInSeconds = timeInSeconds;
    }
    
    /**
     * 设置缓存最大大小.
     * @param size 最大大小
     */
    public void setMaxCacheSize(int size) {
        this.maxCacheSize = size;
    }
    
    /**
     * 通过缓存查询.
     * @param k 查询关键字
     * @return  查询结果
     */
    public V query(K k) {
        // 从缓存获取值
        FastCacheItem<K, V> item = readCacheLocked(k);
        V value = null;
        if (item != null
                && (System.currentTimeMillis() - item.getCacheTime() < expireInSeconds * 1000 || expireInSeconds <= 0)) {
            value = item.getValue();
        }
        if (value != null) {
            if (DEBUG) { LOGGER.debug("Cache hit: {}", k); }
            return value;
        }
        if (DEBUG) { LOGGER.debug("Cache miss: {}", k); }
        
        // 从数据库获取值
        value = callback.query(k);
        if (value != null) {
            if (maxCacheSize <= 0 || cached.size() < maxCacheSize) {
                item = new FastCacheItem<K, V>();
                item.setCacheTime(System.currentTimeMillis());
                item.setKey(k);
                item.setValue(value);
                writeCacheLocked(k, item);
            } else {
                LOGGER.warn("Cache max size exceeded.");
            }
        }
        
        return value;
    }

    /**
     * 从缓存读取数据.
     */
    private FastCacheItem<K, V> readCacheLocked(K k) {
        Lock rlock = locker.readLock();
        rlock.lock();
        try {
             return cached.get(k);
        } finally {
            rlock.unlock();
        }
    }
    
    /**
     * 向缓存写入数据.
     */
    private void writeCacheLocked(K k, FastCacheItem<K, V> item) {
        Lock wlock = locker.writeLock();
        wlock.lock();
        try {
            cached.put(k, item);
        } finally {
            wlock.unlock();
        }
    }
    
    /**
     * 删除缓存中的数据.
     * @param k 查询关键字
     */
    public void delete(K k) {
        cached.remove(k);
    }
    
    /**
     * 清理过期缓存数据.
     */
    public void clean() {
        if (expireInSeconds <= 0) {
            return;
        }
        
        Lock wlock = locker.writeLock();
        wlock.lock();
        try {
            for (Entry<K, FastCacheItem<K, V>> e : cached.entrySet()) {
                FastCacheItem<K, V> item = e.getValue();
                if (System.currentTimeMillis() - item.getCacheTime() > expireInSeconds * 1000) {
                    cached.remove(e.getKey());
                }
            }
        } finally {
            wlock.unlock();
        }
    }
}
