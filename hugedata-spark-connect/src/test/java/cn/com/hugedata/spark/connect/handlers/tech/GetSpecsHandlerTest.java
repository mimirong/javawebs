package cn.com.hugedata.spark.connect.handlers.tech;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class GetSpecsHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("specType", "COMPUTING");
        
        GetSpecsHandler handler = createHandler(GetSpecsHandler.class);
        JSONObject resp = runLoginWithDefault(handler, req);
        
        assertSuccess(resp);
    }

}
