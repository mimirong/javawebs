package cn.com.hugedata.spark.connect.handlers.serviceGuide;

import org.junit.Test;

import cn.com.hugedata.spark.connect.HandlerTestBase;
import cn.com.hugedata.spark.connect.handlers.serviceGuide.ListGuidesHandler;

import com.alibaba.fastjson.JSONObject;

public class ListGuidesHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("start",0);
        req.put("length",10);
        req.put("deptCode",1);
        ListGuidesHandler handler = createHandler(ListGuidesHandler.class);
        //JSONObject resp = runLoginWithUserId(handler, req,3);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
