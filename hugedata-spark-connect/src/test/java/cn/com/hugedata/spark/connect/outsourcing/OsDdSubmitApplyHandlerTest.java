package cn.com.hugedata.spark.connect.outsourcing;

import cn.com.hugedata.spark.connect.HandlerTestBase;
import cn.com.hugedata.spark.connect.handlers.outsourcing.OsDdSubmitApplyHandler;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class OsDdSubmitApplyHandlerTest extends HandlerTestBase {

    @Test
    public void test(){
        JSONObject req = new JSONObject();
        req.put("categoryId", "OS_DETEC_ACHIEVE_EXHIBIT");
        req.put("articleId", "26");
        req.put("articleName", "资源平台");
        req.put("companyName", "南京网络科技有限公司");
        req.put("contact", "15151512455");
        req.put("contactName", "南京嗲比");
        OsDdSubmitApplyHandler handler = createHandler(OsDdSubmitApplyHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req, 3);
        assertSuccess(resp);
    }

}
