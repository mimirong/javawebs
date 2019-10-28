package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaServiceGuideMapper extends BaseMapper<GaServiceGuide, Integer>, ManagementMapper<GaServiceGuide, Integer> {
    GaServiceGuide selectById(Integer guideId);

    List<GaServiceGuide> selectByMap(Map<String, Object> map);

    List<GaServiceGuide> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaServiceGuide obj);

    Integer deleteById(Integer guideId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaServiceGuide obj);

    Integer updateSelectiveByMap(@Param("record") GaServiceGuide obj, @Param("map") Map<String, Object> param);
}