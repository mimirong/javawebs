package cn.com.hugedata.spark.connect.handlers.outsourcing;

import org.junit.Test;

import cn.com.hugedata.spark.connect.HandlerTestBase;

import com.alibaba.fastjson.JSONObject;

public class TechListHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("start",0);
        req.put("length",10);
       // req.put("professionFieldId","SP_SERVICE_FIELD_NDT");
        req.put("investmentValumeScope","lt_10");
        
        TechListHandler handler = createHandler(TechListHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
