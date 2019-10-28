package cn.com.hugedata.spark.connect.handlers.user;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.utils.PasswordHash3;
import cn.com.hugedata.spark.connect.HandlerTestBase;

public class Login2HandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("loginName", "test");
        req.put("password", PasswordHash3.hashPassword("666666"));
        
        Login2Handler handler = createHandler(Login2Handler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
