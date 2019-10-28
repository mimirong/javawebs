package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.DdDetectionApply;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DdDetectionApplyMapper extends BaseMapper<DdDetectionApply, Integer>, ManagementMapper<DdDetectionApply, Integer> {
    DdDetectionApply selectById(Integer applyId);

    List<DdDetectionApply> selectByMap(Map<String, Object> map);

    List<DdDetectionApply> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(DdDetectionApply obj);

    Integer deleteById(Integer applyId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(DdDetectionApply obj);

    Integer updateSelectiveByMap(@Param("record") DdDetectionApply obj, @Param("map") Map<String, Object> param);
}