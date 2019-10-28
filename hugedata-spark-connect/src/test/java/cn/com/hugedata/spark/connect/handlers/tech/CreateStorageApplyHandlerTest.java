package cn.com.hugedata.spark.connect.handlers.tech;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class CreateStorageApplyHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("size", "77M");
        req.put("totalPrice", 155);
        req.put("serviceDuration", 6);
        req.put("amount", 7);
        
        CreateStorageApplyHandler handler = createHandler(CreateStorageApplyHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req, 2);
        
        assertSuccess(resp);
    }
}
