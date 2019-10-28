package cn.com.hugedata.spark.commons.service.push;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;

public interface PushService {

    /**
     * 根据tag推送.
     * @param tags                 需要推送的tag
     * @param alert                推送通知的文本
     * @param title                推送通知的标题
     * @param extras               附加数据
     * @throws ServiceException    推送失败，异常包含错误信息
     */
    void pushTags(List<String> tags, String alert, String title, JSONObject extras) throws ServiceException;
    
    /**
     * 根据uid推送.
     * @param userId                用户id
     * @param alert                推送通知的文本
     * @param title                推送通知的标题
     * @param extras               附加数据
     * @throws ServiceException    推送失败，异常包含错误信息
     */
    void pushUser(Integer userId, String alert, String title, JSONObject extras) throws ServiceException;
}
