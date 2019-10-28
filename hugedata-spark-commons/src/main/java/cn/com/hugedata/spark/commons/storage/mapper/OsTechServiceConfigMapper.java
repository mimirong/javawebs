package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceConfig;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OsTechServiceConfigMapper extends BaseMapper<OsTechServiceConfig, String>, ManagementMapper<OsTechServiceConfig, String> {
    OsTechServiceConfig selectById(String configName);

    List<OsTechServiceConfig> selectByMap(Map<String, Object> map);

    List<OsTechServiceConfig> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(OsTechServiceConfig obj);

    Integer deleteById(String configName);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(OsTechServiceConfig obj);

    Integer updateSelectiveByMap(@Param("record") OsTechServiceConfig obj, @Param("map") Map<String, Object> param);
}