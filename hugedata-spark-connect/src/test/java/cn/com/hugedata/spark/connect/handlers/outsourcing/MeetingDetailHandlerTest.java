package cn.com.hugedata.spark.connect.handlers.outsourcing;

import org.junit.Test;

import cn.com.hugedata.spark.connect.HandlerTestBase;

import com.alibaba.fastjson.JSONObject;

public class MeetingDetailHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("trainingId",15);
        MeetingDetailHandler handler = createHandler(MeetingDetailHandler.class);
        //JSONObject resp = runLoginWithUserId(handler, req,3);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
