package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class PasswordRecoverySetPasswordHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("sessionId", "MR20171222110748725Be31eSLMe5ZTO");
        req.put("sessionKey", "QmkURnrzihgycjIijqBAp7MqxwOwiRtj");
        req.put("password", "111111");
        
        PasswordRecoverySetPasswordHandler handler = createHandler(PasswordRecoverySetPasswordHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        
        assertSuccess(resp);
    }

}
