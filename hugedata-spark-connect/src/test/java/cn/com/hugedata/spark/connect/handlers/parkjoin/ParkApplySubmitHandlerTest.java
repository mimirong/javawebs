package cn.com.hugedata.spark.connect.handlers.parkjoin;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class ParkApplySubmitHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("companyName", RandomStringUtils.randomAlphabetic(8));
        req.put("representative", RandomStringUtils.randomAlphabetic(8));
        req.put("contact", RandomStringUtils.randomAlphabetic(8));
        req.put("contactMobile", RandomStringUtils.randomAlphabetic(8));
        req.put("telephone", RandomStringUtils.randomAlphabetic(8));
        req.put("regCapDomestic", RandomStringUtils.randomAlphabetic(8));
        req.put("regCapForeign", RandomStringUtils.randomAlphabetic(8));
        req.put("regAddress", RandomStringUtils.randomAlphabetic(8));
        req.put("businessScope", RandomStringUtils.randomAlphabetic(8));
        req.put("companyContactName", RandomStringUtils.randomAlphabetic(8));
        req.put("companyContactTel", RandomStringUtils.randomAlphabetic(8));
        req.put("companyContactFax", RandomStringUtils.randomAlphabetic(8));
        req.put("platformContactName", RandomStringUtils.randomAlphabetic(8));
        req.put("platformContactTel", RandomStringUtils.randomAlphabetic(8));
        req.put("platformContactFax", RandomStringUtils.randomAlphabetic(8));
        req.put("investContactName", RandomStringUtils.randomAlphabetic(8));
        req.put("investContactTel", RandomStringUtils.randomAlphabetic(8));
        req.put("investContactFax", RandomStringUtils.randomAlphabetic(8));
        req.put("companyRemark", RandomStringUtils.randomAlphabetic(8));
        req.put("investRemark", RandomStringUtils.randomAlphabetic(8));
        
        ParkJoinApplySubmitHandler handler = createHandler(ParkJoinApplySubmitHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req, 5);
        assertSuccess(resp);
    }

}
