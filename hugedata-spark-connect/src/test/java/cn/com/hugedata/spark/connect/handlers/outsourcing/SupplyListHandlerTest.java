package cn.com.hugedata.spark.connect.handlers.outsourcing;

import org.junit.Test;

import cn.com.hugedata.spark.connect.HandlerTestBase;

import com.alibaba.fastjson.JSONObject;

public class SupplyListHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("start",0);
        req.put("length",10);
        req.put("sortType","QUERY_SORT_VISITOR_COUNT");
        SupplyListHandler handler = createHandler(SupplyListHandler.class);
        //JSONObject resp = runLoginWithUserId(handler, req,3);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
