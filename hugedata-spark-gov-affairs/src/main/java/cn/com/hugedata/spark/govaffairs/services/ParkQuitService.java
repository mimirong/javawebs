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
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitApplication;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitGuide;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkQuitApplicationMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkQuitGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class ParkQuitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkQuitService.class);
    
    @Autowired
    private GaParkQuitGuideMapper gaParkQuitGuideMapper;
    
    @Autowired
    private GaParkQuitApplicationMapper gaParkQuitApplicationMapper;
    
    /**
     * 查询退园指南列表.
     * @param start      分页开始记录
     * @param length     每页记录数
     * @return           文章列表
     */
    public PageEntity<GaParkQuitGuide> list(int start, int length) {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                GaParkQuitGuide.Fields.IS_VISIBLE, true,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(GaParkQuitGuide.Fields.GUIDE_ID, OrderItem.DESC))
        );
        List<GaParkQuitGuide> data = gaParkQuitGuideMapper.selectByMap(query, new RowBounds(start, length));
        int count = gaParkQuitGuideMapper.countByMap(query);

        for (GaParkQuitGuide article : data) {
            article.setContent("");
        }
        
        return new PageEntity<>(data, start, length, count);
    }
    
    /**
     * 查询退园指南详情.
     * @param guideId 退园指南ID
     * @return
     */
    public GaParkQuitGuide queryGuide(int guideId) {
        return gaParkQuitGuideMapper.selectById(guideId);
    }
    
    /**
     * 提交退园申请.
     */
    public void submitParkQuitApply(String dataJson) throws ServiceException {
        try {
            JSONObject data = JSON.parseObject(dataJson);
            
            GaParkQuitApplication app = new GaParkQuitApplication();
            app.setCompanyName(data.getString("companyName"));
            app.setStatus(GaParkQuitApplication.STATUS_CREATED);
            app.setApplierUserId(LoginUtils.getCurrentLogin().getUserId());
            app.setApplierName(LoginUtils.getCurrentLogin().getName());
            app.setCreateTime(new Date());
            app.setContent(dataJson);
            gaParkQuitApplicationMapper.insertSelective(app);
            
        } catch (JSONException e) {
            LOGGER.error("Failed to parse park quit apply json.", e);
            throw new ServiceException("InvalidJsonData", "解析数据失败");
        }
    }
}
