package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaFinancingApplication;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaFinancingApplicationMapper extends BaseMapper<GaFinancingApplication, Integer>, ManagementMapper<GaFinancingApplication, Integer> {
    GaFinancingApplication selectById(Integer applicationId);

    List<GaFinancingApplication> selectByMap(Map<String, Object> map);

    List<GaFinancingApplication> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaFinancingApplication obj);

    Integer deleteById(Integer applicationId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaFinancingApplication obj);

    Integer updateSelectiveByMap(@Param("record") GaFinancingApplication obj, @Param("map") Map<String, Object> param);
}