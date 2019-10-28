package cn.com.hugedata.spark.commons.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcMessageMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;

/**
 * 内部消息通知服务.
 */
@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private UcMessageMapper ucMessageMapper;
    
//    @PostConstruct
//    public void test() throws Exception {
//        createSystemMessage(UcMessage.TYPE_PARK_JOIN_APPLY, "入园申请成功", "Success Success Success ", 1);
//        createSystemMessage(UcMessage.TYPE_PARK_QUIT_APPLY, "退园成功", "Success Success Success adfasdf", 1);
//        createSystemMessage(UcMessage.TYPE_FINANCING_APPLY, "融资申请成功", "Success Success Success y4 57ir", 1);
//    }
    
    /**
     * 创建并保存系统消息.
     * @param messageType       消息类型, 使用UcMessage.TYPE_*常量
     * @param title             消息标题
     * @param content           消息内容
     * @param recvUserId        接受人用户ID
     * @param referenceId       关联项目的ID
     * @throws ServiceException 创建消息失败
     */
    public void createSystemMessage(String messageType, String title, String content, int recvUserId, String referenceId) 
            throws ServiceException {
        // 查询用户信息
        UcUserInfo user = ucUserInfoMapper.selectById(recvUserId);
        if (user == null) {
            LOGGER.error("Message receiver user id not found: {}", recvUserId);
            throw new ServiceException("MessageService-RecvUserIdNotFound", "用户不存在");
        }
        
        // 保存消息
        LOGGER.info("Creating message for user: {}/{} << {}:{}", user.getUserId(), user.getLoginName(), 
                messageType, title);
        UcMessage message = new UcMessage();
        message.setMessageType(messageType);
        message.setTitle(title);
        message.setContent(content);
        message.setSenderId(UcMessage.SYSTEM_SENDER_ID);
        message.setSenderName(UcMessage.SYSTEM_SENDER_NAME);
        message.setReceiverId(user.getUserId());
        message.setReceiverName(user.getLoginName());
        message.setReferenceId(referenceId);
        message.setIsRead(false);
        message.setSendTime(new Date());
        ucMessageMapper.insertSelective(message);
    }

}
