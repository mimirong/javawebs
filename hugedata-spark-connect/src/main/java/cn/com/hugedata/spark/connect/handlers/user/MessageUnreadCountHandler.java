package cn.com.hugedata.spark.connect.handlers.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.mapper.UcMessageMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class MessageUnreadCountHandler extends BaseHandler {

    @Autowired
    private UcMessageMapper ucMessageQueryMapper;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        int count = ucMessageQueryMapper.countByMap(MyBatisUtils.params(
                UcMessage.Fields.RECEIVER_ID,getLogin().getUserId(),
                UcMessage.Fields.IS_READ,false
        ));

        JSONObject resp = new JSONObject();
        resp.put("unReadCount", count);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
