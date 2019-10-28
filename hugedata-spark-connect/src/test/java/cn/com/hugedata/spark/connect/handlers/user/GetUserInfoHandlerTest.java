package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class GetUserInfoHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("userId", "6");
        
        GetUserInfoHandler handler = createHandler(GetUserInfoHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
