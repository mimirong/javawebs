package cn.com.hugedata.spark.connect.handlers.user;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.mapper.UcMessageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class MessageAllReadHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageAllReadHandler.class);
    
    @Autowired
    private UcMessageMapper ucMessageMapper;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        List<UcMessage> messages = ucMessageMapper.selectByMap(MyBatisUtils.buildParameterMap(
                    UcMessage.Fields.IS_READ, false,
                    UcMessage.Fields.RECEIVER_ID, getLogin().getUserId())
        );
        
        for (UcMessage message:messages){
            Integer messageId = message.getMessageId();
            if (!message.getReceiverId().equals(getLogin().getUserId())) {
                LOGGER.error("No privilege to update message read status: {}, user: {}", messageId, getLogin().getUserId());
                throw new ServiceException("NoPrivilege", "没有权限更新此条消息");
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
