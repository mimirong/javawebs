package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaReportTemplate;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaReportTemplateMapper extends BaseMapper<GaReportTemplate, Integer>, ManagementMapper<GaReportTemplate, Integer> {
    GaReportTemplate selectById(Integer templateId);

    List<GaReportTemplate> selectByMap(Map<String, Object> map);

    List<GaReportTemplate> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaReportTemplate obj);

    Integer deleteById(Integer templateId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaReportTemplate obj);

    Integer updateSelectiveByMap(@Param("record") GaReportTemplate obj, @Param("map") Map<String, Object> param);
}