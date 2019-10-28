package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class PasswordRecoveryVerifyHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("sessionId", "MR20171222110748725Be31eSLMe5ZTO");
        req.put("sessionKey", "QmkURnrzihgycjIijqBAp7MqxwOwiRtj");
        req.put("code", "712638");
        
        PasswordRecoveryVerifyHandler handler = createHandler(PasswordRecoveryVerifyHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        
        assertSuccess(resp);
    }

}
