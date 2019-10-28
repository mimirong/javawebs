package cn.com.hugedata.spark.connect.handlers.user;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class PasswordRecoverySendCodeHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("email", "gaopeng@hugedata.com.cn");
        
        PasswordRecoverySendCodeHandler handler = createHandler(PasswordRecoverySendCodeHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        
        assertSuccess(resp);
        assertNotNull(JSONPath.eval(resp, "data.sessionId"));
        assertNotNull(JSONPath.eval(resp, "data.sessionKey"));
    }

}
