package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class MessageUnreadCountHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        MessageUnreadCountHandler handler = createHandler(MessageUnreadCountHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req, 3);
        assertSuccess(resp);
    }

}
