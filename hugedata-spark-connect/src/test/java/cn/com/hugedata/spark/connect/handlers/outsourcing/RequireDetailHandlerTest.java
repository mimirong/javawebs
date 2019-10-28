package cn.com.hugedata.spark.connect.handlers.outsourcing;

import org.junit.Test;

import cn.com.hugedata.spark.connect.HandlerTestBase;

import com.alibaba.fastjson.JSONObject;

public class RequireDetailHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("requireId",37);
        RequireDetailHandler handler = createHandler(RequireDetailHandler.class);
        //JSONObject resp = runLoginWithUserId(handler, req,3);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
