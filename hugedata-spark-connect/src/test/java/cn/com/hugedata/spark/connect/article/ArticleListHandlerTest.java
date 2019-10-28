package cn.com.hugedata.spark.connect.article;

import cn.com.hugedata.spark.connect.HandlerTestBase;
import cn.com.hugedata.spark.connect.handlers.article.ArticleListHandler;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class ArticleListHandlerTest extends HandlerTestBase {

    @Test
    public void test(){
        JSONObject req = new JSONObject();
        req.put("categoryIdArray", "['APP_MANAGE_MESSAGE']");
        req.put("start", 0);
        req.put("count", 10);
        req.put("isNeedPicture", 0);
        ArticleListHandler handler = createHandler(ArticleListHandler.class);
        JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
    }

}
