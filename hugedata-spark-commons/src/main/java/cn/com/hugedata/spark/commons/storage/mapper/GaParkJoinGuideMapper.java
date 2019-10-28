package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaParkJoinGuideMapper extends BaseMapper<GaParkJoinGuide, Integer>, ManagementMapper<GaParkJoinGuide, Integer> {
    GaParkJoinGuide selectById(Integer guideId);

    List<GaParkJoinGuide> selectByMap(Map<String, Object> map);

    List<GaParkJoinGuide> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaParkJoinGuide obj);

    Integer deleteById(Integer guideId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaParkJoinGuide obj);

    Integer updateSelectiveByMap(@Param("record") GaParkJoinGuide obj, @Param("map") Map<String, Object> param);
}