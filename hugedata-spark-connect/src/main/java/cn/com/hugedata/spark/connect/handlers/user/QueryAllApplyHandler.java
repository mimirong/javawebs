package cn.com.hugedata.spark.connect.handlers.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.queryentity.ApplyMergeInfo;
import cn.com.hugedata.spark.commons.storage.querymapper.ApplyMergeQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class QueryAllApplyHandler extends BaseHandler {

    @Autowired
    private ApplyMergeQueryMapper applyMergeQueryMapper;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        Integer start = req.getInteger("start");
        Integer length = req.getInteger("length");
        String  status = req.getString("status");
        
        if (start == null) {
            start = 0;
        }
        if (length == null) {
            length = 10;
        }
        
        Map<String, Object> query = MyBatisUtils.params(
                "userId", getLogin().getUserId(),
                "status", status
        );
        
        List<ApplyMergeInfo> data = applyMergeQueryMapper.queryByMap(query, new RowBounds(start, length));
        int count = applyMergeQueryMapper.countByMap(query);
        
        JSONObject resp = new JSONObject();
        resp.put("count", count);
        resp.put("list", data);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
