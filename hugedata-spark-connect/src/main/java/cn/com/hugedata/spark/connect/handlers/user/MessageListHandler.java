package cn.com.hugedata.spark.connect.handlers.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.mapper.UcMessageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class MessageListHandler extends BaseHandler {

    @Autowired
    private UcMessageMapper ucMessageMapper;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        Integer start = req.getInteger("start");
        Integer length = req.getInteger("length");
        String messageType = req.getString("messageType");
        Boolean fixPageStart = req.getBoolean("fixPageStart");

        if (start == null) {
            start = 0;
        }
        if (length == null) {
            length = 10;
        }
        if (fixPageStart == null) {
            fixPageStart = false;
        }
        
        int count = ucMessageMapper.countByMap(MyBatisUtils.buildParameterMap(
                UcMessage.Fields.RECEIVER_ID, getLogin().getUserId(),
                UcMessage.Fields.MESSAGE_TYPE, messageType
        ));
        
        if (fixPageStart && start > (count - 1) / length * length) {
            start = (count - 1) / length * length;
        }
        
        List<UcMessage> messageList = ucMessageMapper.selectByMap(MyBatisUtils.params(
                UcMessage.Fields.RECEIVER_ID, getLogin().getUserId(),
                UcMessage.Fields.MESSAGE_TYPE,messageType,
                new OrderItem(UcMessage.Fields.MESSAGE_ID, OrderItem.DESC)
        ), new RowBounds(start, length));

        List<JSONObject> retList = new ArrayList<>();
        for (UcMessage m : messageList) {
            JSONObject obj = (JSONObject) JSON.toJSON(m);
            obj.remove("idValue");
            obj.remove("idPropertyName");
            obj.remove("title");
            obj.remove("receiverId");
            obj.remove("receiverName");
            retList.add(obj);
        }
        
        JSONObject resp = new JSONObject();
        resp.put("messages", retList);
        resp.put("count", count);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
