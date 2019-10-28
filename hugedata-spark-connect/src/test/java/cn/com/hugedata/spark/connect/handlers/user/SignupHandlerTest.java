package cn.com.hugedata.spark.connect.handlers.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class SignupHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
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
    }

}
