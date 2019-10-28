package cn.com.hugedata.spark.connect.handlers.user;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.mapper.UcMessageMapper;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class MessageMarkReadMultiHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageMarkReadMultiHandler.class);
    
    @Autowired
    private UcMessageMapper ucMessageMapper;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        JSONArray messageIdArray = req.getJSONArray("messageIdList");
        if (messageIdArray == null) {
            LOGGER.error("Message id list is null.");
            throw new ServiceException("EmptyParameter", "参数messageIdList不能为空");
        }
        
        for (int i = 0; i < messageIdArray.size(); i++) {
            Integer messageId = messageIdArray.getInteger(i);
            
            UcMessage message = ucMessageMapper.selectById(messageId);
            if (message == null) {
                continue;
            }

            if (!message.getReceiverId().equals(getLogin().getUserId())) {
                continue;
            }
            
            if (!message.getIsRead()) {
                UcMessage update = new UcMessage();
                update.setMessageId(messageId);
                update.setIsRead(true);
                update.setReadTime(new Date());
                ucMessageMapper.updateSelectiveById(update);
            }
        }
        
        return InterfaceResponse.createSuccessResponse();
    }

}
