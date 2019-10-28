package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyQuestionResult;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ItSurveyQuestionResultMapper extends BaseMapper<ItSurveyQuestionResult, Integer>, ManagementMapper<ItSurveyQuestionResult, Integer> {
    ItSurveyQuestionResult selectById(Integer resultId);

    List<ItSurveyQuestionResult> selectByMap(Map<String, Object> map);

    List<ItSurveyQuestionResult> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ItSurveyQuestionResult obj);

    Integer deleteById(Integer resultId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ItSurveyQuestionResult obj);

    Integer updateSelectiveByMap(@Param("record") ItSurveyQuestionResult obj, @Param("map") Map<String, Object> param);
}