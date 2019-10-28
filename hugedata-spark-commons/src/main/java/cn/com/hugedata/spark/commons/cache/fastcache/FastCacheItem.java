package cn.com.hugedata.spark.commons.cache.fastcache;

public class FastCacheItem<K, V> {

    private K key;
    
    private V value;
    
    private long cacheTime;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }
    
}
