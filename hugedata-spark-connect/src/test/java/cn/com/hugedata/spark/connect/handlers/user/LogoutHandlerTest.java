package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class LogoutHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("registrationId", "123");
        
        LogoutHandler handler = createHandler(LogoutHandler.class);
        JSONObject resp = runLoginWithDefault(handler, req);
        
        assertSuccess(resp);
    }
}
