package cn.com.hugedata.spark.connect.handlers.article;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.connect.HandlerTestBase;

public class ArticleDetailsHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("articleId", 28);
        
        ArticleDetailsHandler handler = createHandler(ArticleDetailsHandler.class);
        JSONObject resp = runLoginWithDefault(handler, req);
        
        assertSuccess(resp);
    }

}
