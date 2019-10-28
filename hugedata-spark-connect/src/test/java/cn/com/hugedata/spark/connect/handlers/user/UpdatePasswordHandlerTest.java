package cn.com.hugedata.spark.connect.handlers.user;

import static org.junit.Assert.assertNotEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class UpdatePasswordHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        // CREATE USER
        JSONObject req = new JSONObject();        
        req.put("username", "UnitTest_" + RandomStringUtils.randomAlphabetic(8).toUpperCase());
        req.put("password", "111111");
        req.put("companyName", "UnitTest_" + RandomStringUtils.randomAlphabetic(16));
        req.put("companyType", RandomStringUtils.randomAlphabetic(16));
        req.put("companyAddress", RandomStringUtils.randomAlphabetic(16));
        req.put("organizationCode", RandomStringUtils.randomAlphabetic(16));
        req.put("licenceCode", RandomStringUtils.randomAlphabetic(16));
        req.put("representative", RandomStringUtils.randomAlphabetic(16));
        req.put("representativeId", RandomStringUtils.randomAlphabetic(16));
        req.put("contactName", RandomStringUtils.randomAlphabetic(16));
        req.put("contactMobile", RandomStringUtils.randomAlphabetic(16));
        req.put("contactEmail", RandomStringUtils.randomAlphabetic(16));
        SignupHandler handler = createHandler(SignupHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
        int userId = resp.getJSONObject("data").getIntValue("userId");
        
        // UPDATE
        JSONObject req2 = new JSONObject();
        req2.put("oldPassword", "111111");
        req2.put("newPassword", "222222");
        UpdatePasswordHandler handler2 = createHandler(UpdatePasswordHandler.class);
        resp = runLoginWithUserId(handler2, req2, userId);
        assertSuccess(resp);

        // UPDATE
        JSONObject req3 = new JSONObject();
        req3.put("oldPassword", "222222");
        req3.put("newPassword", "333333");
        UpdatePasswordHandler handler3 = createHandler(UpdatePasswordHandler.class);
        resp = runLoginWithUserId(handler3, req3, userId);
        assertSuccess(resp);
        
        // UPDATE
        JSONObject req4 = new JSONObject();
        req4.put("oldPassword", "111111");
        req4.put("newPassword", "555555");
        UpdatePasswordHandler handler4 = createHandler(UpdatePasswordHandler.class);
        resp = runLoginWithUserId(handler4, req4, userId);
        assertNotEquals(0, resp.getIntValue("result"));
    }

}
