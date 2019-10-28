package cn.com.hugedata.spark.management.features.OsTechStorageConfig;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceConfig;
import cn.com.hugedata.spark.commons.storage.mapper.OsTechServiceConfigMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

@Service
public class OsTechStorageConfigService {

    @Autowired
    private OsTechServiceConfigMapper osTechServiceConfigMapper;
    
    /**
     * 查询云存储申请配置.
     * @return 云存储申请配置
     */
    public Map<String, Object> queryConfig() {
        List<OsTechServiceConfig> configs = osTechServiceConfigMapper.selectByMap(MyBatisUtils.params(
                OsTechServiceConfig.Fields.CONFIG_TYPE, OsTechServiceConfig.TYPE_STORAGE
        ));

        Map<String, Object> map = new TreeMap<>();
        for (OsTechServiceConfig cfg : configs) {
            String name = cfg.getConfigName();
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
            
            try {
                map.put(name, JSON.parse(cfg.getConfigValue()));
            } catch (JSONException e) {
                map.put(name, cfg.getConfigValue());
            }
        }
        return map;
    }
    
    /**
     * 更新配置信息.
     * @param config 配置信息
     */
    public void updateConfig(Map<String, String> config) {
        for (Entry<String, String> e : config.entrySet()) {
            String configName = e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1);
            
            OsTechServiceConfig updater = new OsTechServiceConfig();
            updater.setConfigName(configName);
            updater.setConfigValue(e.getValue());
            osTechServiceConfigMapper.updateSelectiveById(updater);
        }
    }
}
