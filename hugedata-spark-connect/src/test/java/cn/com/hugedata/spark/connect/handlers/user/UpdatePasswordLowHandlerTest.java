package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class UpdatePasswordLowHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("loginName", "test");
        req.put("mobile", "18651824467");
        req.put("password", "111111");
        
        UpdatePasswordLowHandler handler = createHandler(UpdatePasswordLowHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        
        assertSuccess(resp);
    }

}
