package cn.com.hugedata.spark.management.features.Interactive;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ItMessage;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.mapper.ItMessageMapper;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;
@Service
public class InteractiveService
        extends ManagementFeatureServiceImpl<ItMessage, Integer, InteractiveModel, ItMessageMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InteractiveService.class);
    
    @Autowired
    ItMessageMapper itMessageMapper;
    
    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_AP_SERVICE_ONLINE;
    }
    


    @Override
    public PageEntity<ItMessage> list(Map<String, Object> query, int pageStart, int pageSize,
                                          List<OrderItem> orders) {
    	orders = new ArrayList<OrderItem>();
        orders.add(new OrderItem(ItMessage.Fields.SUBMIT_TIME, OrderItem.DESC));

        
        PageEntity<ItMessage> page = super.list(query, pageStart, pageSize, orders);

        return page;
    }

    @Override
    public void modify(InteractiveModel model) throws ServiceException{
    	model.setReplierName(LoginUtils.getCurrentLogin().getLoginName());
    	model.setReplyTime(new Date());
        super.modify(model);
    }
    public Integer fakeDel(List<Integer> messageIdList,Boolean isDeleted){
    	ItMessage itMessage = new ItMessage();
    	itMessage.setIsDeleted(isDeleted);
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put(ItMessage.Query.MESSAGE_ID__IN, messageIdList);
    	return itMessageMapper.updateSelectiveByMap(itMessage, param);
    }
    
    public ItMessage toReply(int messageId){
    	ItMessage itMessage = itMessageMapper.selectById(messageId);
    	return itMessage;
    }
}
