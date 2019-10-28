package cn.com.hugedata.spark.commons.service.push;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;

/**
 * 用于测试的推送服务.
 */
public class TestPushService implements PushService {

    @Override
    public void pushTags(List<String> tags, String alert, String title, JSONObject extras) throws ServiceException {
        
    }

    @Override
    public void pushUser(Integer userId, String alert, String title, JSONObject extras) throws ServiceException {
        
    }

}
