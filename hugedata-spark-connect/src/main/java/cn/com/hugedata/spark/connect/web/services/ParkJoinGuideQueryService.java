package cn.com.hugedata.spark.connect.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import cn.com.hugedata.spark.commons.storage.mapper.GaParkJoinGuideMapper;

@Service
public class ParkJoinGuideQueryService {
    
    @Autowired
    private GaParkJoinGuideMapper gaParkJoinGuideMapper;
    
    /**
     * 查询入园指南详情.
     * @param guideId 入园指南ID
     * @return
     */
    public GaParkJoinGuide queryGuide(int guideId) {
        return gaParkJoinGuideMapper.selectById(guideId);
    }
    
}
