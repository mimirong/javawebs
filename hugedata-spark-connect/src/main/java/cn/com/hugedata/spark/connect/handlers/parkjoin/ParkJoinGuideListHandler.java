package cn.com.hugedata.spark.connect.handlers.parkjoin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class ParkJoinGuideListHandler extends BaseHandler {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ParkJoinGuideListHandler.class);

    @Autowired
    private GaParkJoinGuideMapper gaParkJoinGuideMapper;
    
    @Autowired
    private UrlManager urlManager;
    
    @Override
    protected boolean isRequireLogin() {
        return false;
    }
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        // 获取请求参数
        Integer start = req.getInteger("start");
        Integer length = req.getInteger("length");
        
        // 处理页码错误
        if (start == null || start < 0) {
            start = 0;
        }
        if (length == null || length < 0) {
            length = 10;
        }
        
        // 查询
        Map<String, Object> query = MyBatisUtils.params(
                GaParkJoinGuide.Fields.IS_VISIBLE, true,
                GaParkJoinGuide.Query.PUBLISH_TIME__END, new Date(),
                new OrderItem(GaParkJoinGuide.Fields.CREATE_TIME, OrderItem.DESC)
        );
        List<GaParkJoinGuide> data = gaParkJoinGuideMapper.selectByMap(query, new RowBounds(start, length));
        int count = gaParkJoinGuideMapper.countByMap(query);
        
        // 返回
        List<JSONObject> list = new ArrayList<>();
        for (GaParkJoinGuide guide : data) {
            guide.setContent("");
            
            JSONObject obj = (JSONObject) JSON.toJSON(guide);
            obj.remove("idPropertyName");
            obj.remove("idValue");
            obj.put("url", urlManager.getConnectUrl() + "/parkjoin/guideDetails?guideId=" + guide.getGuideId());
            list.add(obj);
        }
        
        JSONObject resp = new JSONObject();
        resp.put("list", list);
        resp.put("count", count);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
