package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthReport;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PmMonthReportMapper extends BaseMapper<PmMonthReport, Integer>, ManagementMapper<PmMonthReport, Integer> {
    PmMonthReport selectById(Integer reportId);

    List<PmMonthReport> selectByMap(Map<String, Object> map);

    List<PmMonthReport> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PmMonthReport obj);

    Integer deleteById(Integer reportId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PmMonthReport obj);

    Integer updateSelectiveByMap(@Param("record") PmMonthReport obj, @Param("map") Map<String, Object> param);
}