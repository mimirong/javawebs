package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PmYearPlan;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PmYearPlanMapper extends BaseMapper<PmYearPlan, Integer>, ManagementMapper<PmYearPlan, Integer> {
    PmYearPlan selectById(Integer planId);

    List<PmYearPlan> selectByMap(Map<String, Object> map);

    List<PmYearPlan> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PmYearPlan obj);

    Integer deleteById(Integer planId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PmYearPlan obj);

    Integer updateSelectiveByMap(@Param("record") PmYearPlan obj, @Param("map") Map<String, Object> param);
}