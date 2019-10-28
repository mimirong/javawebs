package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaFinancingGuide;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaFinancingGuideMapper extends BaseMapper<GaFinancingGuide, Integer>, ManagementMapper<GaFinancingGuide, Integer> {
    GaFinancingGuide selectById(Integer guideId);

    List<GaFinancingGuide> selectByMap(Map<String, Object> map);

    List<GaFinancingGuide> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaFinancingGuide obj);

    Integer deleteById(Integer guideId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaFinancingGuide obj);

    Integer updateSelectiveByMap(@Param("record") GaFinancingGuide obj, @Param("map") Map<String, Object> param);
}