package cn.com.hugedata.spark.connect.handlers.parkjoin;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class ParkJoinGuideListHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("start", 0);
        req.put("length", 10);
        
        ParkJoinGuideListHandler handler = createHandler(ParkJoinGuideListHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        
        assertSuccess(resp);
        assertNotNull(JSONPath.eval(resp, "data.list"));
    }

}
