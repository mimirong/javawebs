package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.storage.constant.UcMessageConstants;
import cn.com.hugedata.spark.connect.HandlerTestBase;

public class MessageListHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("messageType", UcMessageConstants.TYPE_PARK_JOIN_APPLY);
        MessageListHandler handler = createHandler(MessageListHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req, 3);
        
        assertSuccess(resp);
    }

}
