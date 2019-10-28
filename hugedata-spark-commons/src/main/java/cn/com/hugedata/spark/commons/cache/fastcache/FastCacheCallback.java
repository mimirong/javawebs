package cn.com.hugedata.spark.commons.cache.fastcache;

/**
 * 用于{@link FastCache}回调进行实际的查询.
 * 
 * @param <K>
 * @param <V>
 */
public interface FastCacheCallback<K, V> {

    /**
     * 从数据库(或其它)查询.
     * @param k 查询关键字
     * @return  查询结果
     */
    V query(K k);
    
}
