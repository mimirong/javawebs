package cn.com.hugedata.spark.connect.handlers.messagePost;

import org.junit.Test;

import cn.com.hugedata.spark.connect.HandlerTestBase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AddHandlerTest extends HandlerTestBase {

    @Test
    public void test() {
        JSONObject req = new JSONObject();
        req.put("postTime",155555067890l);
        req.put("postAddr","yinhao 49 男大");
        req.put("eventDesc","学生00");
        req.put("postLevel", "1");
        req.put("happenTime",14555500004l);
        req.put("posterName","yxxxinhao");
        req.put("posterMobile","23332145644");
        JSONArray fs = new JSONArray();
        fs.add("F2017122715304876384FIKVPPHAYWLU");
        fs.add("F20171228143804018IZTM1UBO6BAEQL");
        req.put("fileIds",fs);
        AddHandler handler = createHandler(AddHandler.class);
        JSONObject resp = runLoginWithUserId(handler, req,1335833);
       // JSONObject resp = runWithoutLogin(handler, req);
        assertSuccess(resp);
        
//        private Long postTime;
//        private String postAddr;
//        private String eventDesc;
//        private Long happenTime;
//        private String posterName;
//        private String posterMobile;
//        private String file1Id;
//        private String file2Id;
//        private String file3Id;
//        private String file4Id;
    }

}
