package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttConfig;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaServiceGuideAttConfigMapper extends BaseMapper<GaServiceGuideAttConfig, Integer>, ManagementMapper<GaServiceGuideAttConfig, Integer> {
    GaServiceGuideAttConfig selectById(Integer attConfigId);

    List<GaServiceGuideAttConfig> selectByMap(Map<String, Object> map);

    List<GaServiceGuideAttConfig> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaServiceGuideAttConfig obj);

    Integer deleteById(Integer attConfigId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaServiceGuideAttConfig obj);

    Integer updateSelectiveByMap(@Param("record") GaServiceGuideAttConfig obj, @Param("map") Map<String, Object> param);
}