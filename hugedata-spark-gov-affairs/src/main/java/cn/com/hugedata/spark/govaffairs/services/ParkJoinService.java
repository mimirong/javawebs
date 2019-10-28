package cn.com.hugedata.spark.govaffairs.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinApplication;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinApplicationMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class ParkJoinService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkJoinService.class);
    
    @Autowired
    private GaParkJoinGuideMapper gaParkJoinGuideMapper;
    
    @Autowired
    private GaParkJoinApplicationMapper gaParkJoinApplicationMapper;
    
    /**
     * 查询入园指南列表.
     * @param start      分页开始记录
     * @param length     每页记录数
     * @return           文章列表
     */
    public PageEntity<GaParkJoinGuide> list(int start, int length) {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                GaParkJoinGuide.Fields.IS_VISIBLE, true,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(GaParkJoinGuide.Fields.GUIDE_ID, OrderItem.DESC))
        );
        List<GaParkJoinGuide> data = gaParkJoinGuideMapper.selectByMap(query, new RowBounds(start, length));
        int count = gaParkJoinGuideMapper.countByMap(query);

        for (GaParkJoinGuide article : data) {
            article.setContent("");
        }
        
        return new PageEntity<>(data, start, length, count);
    }
    
    /**
     * 查询入园指南详情.
     * @param guideId 入园指南ID
     * @return
     */
    public GaParkJoinGuide queryGuide(int guideId) {
        return gaParkJoinGuideMapper.selectById(guideId);
    }
    
    /**
     * 提交入园申请.
     */
    public void submitParkJoinApply(String dataJson) throws ServiceException {
        try {
            JSONObject data = JSON.parseObject(dataJson);
            
            GaParkJoinApplication app = new GaParkJoinApplication();
            app.setCompanyName(data.getString("companyName"));
            app.setContact(data.getString("contact"));
            app.setContactMobile(data.getString("contactMobile"));
            app.setStatus(GaParkJoinApplication.STATUS_CREATED);
            app.setApplierUserId(LoginUtils.getCurrentLogin().getUserId());
            app.setApplierName(LoginUtils.getCurrentLogin().getName());
            app.setCreateTime(new Date());
            app.setContent(dataJson);
            gaParkJoinApplicationMapper.insertSelective(app);
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse park join apply json.", e);
            throw new ServiceException("InvalidJsonData", "解析数据失败");
        }
    }
}
