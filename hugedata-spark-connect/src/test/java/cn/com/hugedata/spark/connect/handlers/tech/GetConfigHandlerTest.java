package cn.com.hugedata.spark.connect.handlers.tech;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class GetConfigHandlerTest extends HandlerTestBase {

    @Test
    public void testComputing() {
        JSONObject req = new JSONObject();
        req.put("serviceType", "COMPUTING");
        
        GetConfigHandler handler = createHandler(GetConfigHandler.class);
        JSONObject resp = runLoginWithDefault(handler, req);
        
        assertSuccess(resp);
    }

    @Test
    public void testStorage() {
        JSONObject req = new JSONObject();
        req.put("serviceType", "STORAGE");
        
        GetConfigHandler handler = createHandler(GetConfigHandler.class);
        JSONObject resp = runLoginWithDefault(handler, req);
        
        assertSuccess(resp);
    }

}
