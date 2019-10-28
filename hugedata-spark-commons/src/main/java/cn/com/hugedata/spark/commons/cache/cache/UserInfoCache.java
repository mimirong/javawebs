package cn.com.hugedata.spark.commons.cache.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.cache.base.BaseCache;
import cn.com.hugedata.spark.commons.cache.base.CacheProcessCallback;
import cn.com.hugedata.spark.commons.cache.base.CacheUtils;
import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;

/**
 * 用于查询用户信息的缓存.
 * @author gaopeng
 */
@Service
public class UserInfoCache extends BaseCache {
    
    /**
     * 模块名.
     */
    private static final String CACHE_MODULE = UserInfoCache.class.getSimpleName();
    
    /**
     * 超时时间.
     */
    private static final int EXPIRE_IN_SECONDS = 30;
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;

    /**
     * 根据用户UID查询本地的用户信息.
     * @param uid                 用户uid
     * @return                    用户信息
     * @throws ServiceException 查询失败
     */
    public UcUserInfo findByUid(final int uid) throws ServiceException {
        final String cacheKey = CacheUtils.createCacheKey(CACHE_MODULE, "ByUid", String.valueOf(uid));
        return process(cacheKey, true, new CacheProcessCallback<UcUserInfo>() {
            @Override
            public UcUserInfo readFromCache() throws ServiceException {
                UcUserInfo user = (UcUserInfo) objectTemplate.opsForValue().get(cacheKey);
                if (user == null) {
                    user = new UcUserInfo();
                }
                return user;
            }

            @Override
            public UcUserInfo readFromDatabase() throws ServiceException {
                return ucUserInfoMapper.selectById(uid);
            }

            @Override
            public void writeToCache(UcUserInfo data) throws ServiceException {
                objectTemplate.opsForValue().set(cacheKey, data, EXPIRE_IN_SECONDS, TimeUnit.SECONDS);
            }

            @Override
            public UcUserInfo beforeReturn(UcUserInfo data) throws ServiceException {
                if (data == null || data.getUserId() == null) {
                    return null;
                }
                return data;
            }
        });
    }
    
}
