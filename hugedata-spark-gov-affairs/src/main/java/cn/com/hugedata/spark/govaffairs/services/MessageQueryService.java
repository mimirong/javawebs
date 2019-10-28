package cn.com.hugedata.spark.govaffairs.services;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcMessageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class MessageQueryService {

    @Autowired
    private UcMessageMapper ucMessageMapper;
    
    /**
     * 分页查询用户消息.
     * @param user              用户信息
     * @param messageType       服务类型
     * @param dateStart         查询日期范围开始
     * @param dateEnd           查询日期范围结束
     * @param pageStart         分页开始记录
     * @param pageSize          分页每页记录数
     * @return                  消息列表
     * @throws ServiceException 查询失败
     */
    public PageEntity<UcMessage> queryMessages(UcUserInfo user, String messageType, Date dateStart, Date dateEnd, 
            int pageStart, int pageSize) throws ServiceException {
        Map<String, Object> query = new TreeMap<>();
        query.put(UcMessage.Fields.RECEIVER_ID, user.getUserId());
        query.put(UcMessage.Fields.MESSAGE_TYPE, messageType);
        if (dateStart != null) {
            query.put(UcMessage.Query.SEND_TIME__BEGIN, DateUtils.truncate(dateStart, Calendar.DATE));
        }
        if (dateEnd != null) {
            query.put(UcMessage.Query.SEND_TIME__END, 
                    DateUtils.addMilliseconds(DateUtils.addDays(DateUtils.truncate(dateEnd, Calendar.DATE), 1), -1));
        }
        
        query.put(OrderItem.ORDER_KEY, 
                Arrays.asList(new OrderItem(UcMessage.Fields.MESSAGE_ID, OrderItem.DESC)));
        
        int count = ucMessageMapper.countByMap(query);
        List<UcMessage> data = ucMessageMapper.selectByMap(query, new RowBounds(pageStart, pageSize));
        
        return new PageEntity<>(data, pageStart, pageSize, count);
    }
    
    /**
     * 更新消息为已读.
     * @param messageId 消息ID
     */
    public void updateReadStatus(int messageId) {
        UcMessage update = new UcMessage();
        update.setMessageId(messageId);
        update.setIsRead(true);
        ucMessageMapper.updateSelectiveById(update);
    }
    
    /**
     * 删除消息
     * @param login     当前登录用户
     * @param messageId 消息ID
     */
    public void deleteMessage(UcUserInfo login, int messageId) {
        UcMessage m = ucMessageMapper.selectById(messageId);
        if (m == null) {
            return;
        }
        if (!m.getReceiverId().equals(login.getUserId())) {
            return;
        }
        ucMessageMapper.deleteById(messageId);
    }

    /**
     * 判断当前登录用户是否存在未读消息.
     * @return
     */
    public int countUnreadMessage() {
        int count = ucMessageMapper.countByMap(MyBatisUtils.buildParameterMap(
                UcMessage.Fields.RECEIVER_ID, LoginUtils.getCurrentLogin().getUserId(),
                UcMessage.Fields.IS_READ, false
        ));
        
        // 判断是否仅剩入园和退园相关消息，如果是则将入园退园相关消息标为已读
        if(count > 0){
            int pc = ucMessageMapper.countByMap(MyBatisUtils.buildParameterMap(
                    UcMessage.Fields.RECEIVER_ID, LoginUtils.getCurrentLogin().getUserId(),
                    UcMessage.Query.MESSAGE_TYPE__IN,Arrays.asList("PARK_JOIN_APPLY","PARK_QUIT_APPLY"),
                    UcMessage.Fields.IS_READ, false
            ));
            
            if(pc == count){
                List<UcMessage> ps =  ucMessageMapper.selectByMap(MyBatisUtils.buildParameterMap(
                        UcMessage.Fields.RECEIVER_ID, LoginUtils.getCurrentLogin().getUserId(),
                        UcMessage.Query.MESSAGE_TYPE__IN,Arrays.asList("PARK_JOIN_APPLY","PARK_QUIT_APPLY"),
                        UcMessage.Fields.IS_READ, false
                ));
                
                for(UcMessage m : ps){
                    m.setIsRead(true);
                    ucMessageMapper.updateSelectiveById(m);
                }
            }
        }
        
        return count;
    }
}
