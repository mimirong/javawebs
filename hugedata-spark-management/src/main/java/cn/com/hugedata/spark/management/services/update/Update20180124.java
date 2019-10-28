package cn.com.hugedata.spark.management.services.update;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttConfig;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideAttConfigMapper;
import cn.com.hugedata.spark.commons.storage.mapper.GaServiceGuideMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

/**
 * 更新办事指南附件信息.
 */
@Service
public class Update20180124 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Update20180124.class);
    
    @Autowired
    private GaServiceGuideMapper gaServiceGuideMapper;

    @Autowired
    private GaServiceGuideAttConfigMapper gaServiceGuideAttConfigMapper;

    public void update() {
        List<GaServiceGuide> guideList = gaServiceGuideMapper.selectByMap(MyBatisUtils.EMPTY_MAP);
        for (GaServiceGuide guide : guideList) {
            // 没有配置申请材料，不处理
            String text = guide.getMaterial();
            if (StringUtils.isEmpty(text)) {
                LOGGER.info("Guide {} has no material.", guide.getGuideId());
                continue;
            }
            text = text.trim();
            if (StringUtils.isEmpty(text)) {
                LOGGER.info("Guide {} has no material.", guide.getGuideId());
                continue;
            }
            
            // 存在新版申请材料信息，不处理
            int count = gaServiceGuideAttConfigMapper.countByMap(MyBatisUtils.params(
                    GaServiceGuideAttConfig.Fields.GUIDE_ID, guide.getGuideId()
            ));
            if (count > 0) {
                LOGGER.info("Guide {} is up to date.", guide.getGuideId());
                continue;
            }
            
            // 处理申请材料信息
            String[] items = text.split("\n");
            for (String item : items) {
                item = item.trim();
                if (StringUtils.isEmpty(item)) {
                    continue;
                }
                
                if (item.length() > 100) {
                    item = item.substring(0, 99);
                }
                
                LOGGER.info("Creating att config for guide {}:{} --> {}", guide.getGuideId(), guide.getGuideName(), item);
                
                GaServiceGuideAttConfig ac = new GaServiceGuideAttConfig();
                ac.setAttConfigName(item);
                ac.setGuideId(guide.getGuideId());
                ac.setIsRequired(true);
                ac.setCreateTime(new Date());
                gaServiceGuideAttConfigMapper.insertSelective(ac);
            }
        }
    }
    
}
