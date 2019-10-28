package cn.com.hugedata.spark.connect.handlers.serviceGuide;

import org.junit.Test;

import cn.com.hugedata.spark.connect.HandlerTestBase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ApplyHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("guideId",50);
        req.put("businessDeptPerson","yinhao");
        req.put("cellphone","12345678934");
        JSONArray fs = new JSONArray();
//        fs.add("F2017122715304876384FIKVPPHAYWLU");
//        fs.add("F201712271533591635R5HMV7KBOHKPB");
        req.put("fileIds",fs);
        ApplyHandler handler = createHandler(ApplyHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req,1335833);
       // JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
