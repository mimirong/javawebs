package cn.com.hugedata.spark.connect.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitGuide;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkQuitGuideMapper;

@Service
public class ParkQuitGuideQueryService {
    
    @Autowired
    private GaParkQuitGuideMapper gaParkQuitGuideMapper;
    
    /**
     * 查询入园指南详情.
     * @param guideId 入园指南ID
     * @return
     */
    public GaParkQuitGuide queryGuide(int guideId) {
        return gaParkQuitGuideMapper.selectById(guideId);
    }
    
}
