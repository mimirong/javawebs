package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import cn.com.hugedata.spark.connect.HandlerTestBase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MessageDeleteMultiHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        JSONArray fs = new JSONArray();
        fs.add(50);
        fs.add(51);
        req.put("messageIdList",fs);
        MessageDeleteMultiHandler handler = createHandler(MessageDeleteMultiHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req,1335833);
       // JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
