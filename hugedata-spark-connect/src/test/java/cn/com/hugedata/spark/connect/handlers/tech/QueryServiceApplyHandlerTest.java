package cn.com.hugedata.spark.connect.handlers.tech;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class QueryServiceApplyHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        
        QueryServiceApplyHandler handler = createHandler(QueryServiceApplyHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req, 2);
        
        assertSuccess(resp);
    }

}
