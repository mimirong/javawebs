package cn.com.hugedata.spark.commons.cache.base;

import cn.com.hugedata.spark.commons.exception.ServiceException;

/**
 * 定义一个缓存项的操作. 
 * 
 * @author 高鹏
 *
 * @param <T> 缓存内容的类型
 */
public abstract class CacheProcessCallback<T> {

    /**
     * 从缓存读取数据.
     * 
     * @return 从缓存读取的数据
     * @throws ServiceException
     *      读取时校验或处理失败，如果跑出ServiceException，处理将终止，并将抛出的异常最终抛出
     * @throws RuntimeException 
     *      读取时发生错误(如连接Redis失败)，如果发生错误，readFromDatabase将被调用并将最终返回从
     *      数据库读取的数据
     */
    public abstract T readFromCache() throws ServiceException;
    
    /**
     * 从数据库读取数据.
     * @return 从数据库读取的数据
     * @throws ServiceException 读取时校验或处理失败
     */
    public abstract T readFromDatabase() throws ServiceException;
    
    /**
     * 将从数据库读取的数据保存到缓存.
     * 
     * @param data 从数据库读取的数据
     * @throws ServiceException 读取时校验或处理失败
     * @throws RuntimeException 读取时发生错误(如连接Redis失败)，如果发生错误，错误将被忽略
     */
    public abstract void writeToCache(T data) throws ServiceException;
    
    /**
     * 每次返回数据前进行处理.
     * @param data
     * @return
     * @throws ServiceException
     */
    public T beforeReturn(T data) throws ServiceException {
        return data;
    }
}
