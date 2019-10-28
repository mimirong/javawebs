package cn.com.hugedata.spark.connect.handlers.tech;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class CreateComputingApplyHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("specCpu", "3核");
        req.put("specMemory", "2345M");
        req.put("specDisk", "79G");
        req.put("specBandwidth", "7M");
        req.put("specId", "1");
        req.put("specName", "axdf");
        req.put("totalPrice", 100);
        req.put("serviceDuration", 3);
        req.put("amount", 4);
        
        CreateComputingApplyHandler handler = createHandler(CreateComputingApplyHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req, 2);
        
        assertSuccess(resp);
    }

}
