package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitGuide;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaParkQuitGuideMapper extends BaseMapper<GaParkQuitGuide, Integer>, ManagementMapper<GaParkQuitGuide, Integer> {
    GaParkQuitGuide selectById(Integer guideId);

    List<GaParkQuitGuide> selectByMap(Map<String, Object> map);

    List<GaParkQuitGuide> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaParkQuitGuide obj);

    Integer deleteById(Integer guideId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaParkQuitGuide obj);

    Integer updateSelectiveByMap(@Param("record") GaParkQuitGuide obj, @Param("map") Map<String, Object> param);
}