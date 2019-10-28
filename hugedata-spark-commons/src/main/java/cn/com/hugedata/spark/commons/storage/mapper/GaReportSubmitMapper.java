package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaReportSubmit;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaReportSubmitMapper extends BaseMapper<GaReportSubmit, Integer>, ManagementMapper<GaReportSubmit, Integer> {
    GaReportSubmit selectById(Integer submitId);

    List<GaReportSubmit> selectByMap(Map<String, Object> map);

    List<GaReportSubmit> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaReportSubmit obj);

    Integer deleteById(Integer submitId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaReportSubmit obj);

    Integer updateSelectiveByMap(@Param("record") GaReportSubmit obj, @Param("map") Map<String, Object> param);
}