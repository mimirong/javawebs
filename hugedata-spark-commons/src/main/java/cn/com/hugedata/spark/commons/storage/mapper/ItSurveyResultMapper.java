package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyResult;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ItSurveyResultMapper extends BaseMapper<ItSurveyResult, Integer>, ManagementMapper<ItSurveyResult, Integer> {
    ItSurveyResult selectById(Integer surveyResultId);

    List<ItSurveyResult> selectByMap(Map<String, Object> map);

    List<ItSurveyResult> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ItSurveyResult obj);

    Integer deleteById(Integer surveyResultId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ItSurveyResult obj);

    Integer updateSelectiveByMap(@Param("record") ItSurveyResult obj, @Param("map") Map<String, Object> param);
}