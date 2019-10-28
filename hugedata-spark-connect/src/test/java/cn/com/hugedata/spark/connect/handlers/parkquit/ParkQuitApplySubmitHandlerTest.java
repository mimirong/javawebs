package cn.com.hugedata.spark.connect.handlers.parkquit;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class ParkQuitApplySubmitHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("companyName", RandomStringUtils.randomAlphabetic(8));
        req.put("representative", RandomStringUtils.randomAlphabetic(8));
        req.put("contact", RandomStringUtils.randomAlphabetic(8));
        req.put("quitDate", "2018-01-01");
        
        ParkQuitApplySubmitHandler handler = createHandler(ParkQuitApplySubmitHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req, 5);
        assertSuccess(resp);
    }

}
