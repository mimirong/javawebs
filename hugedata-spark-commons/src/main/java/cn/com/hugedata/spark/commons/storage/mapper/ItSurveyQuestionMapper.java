package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyQuestion;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ItSurveyQuestionMapper extends BaseMapper<ItSurveyQuestion, Integer>, ManagementMapper<ItSurveyQuestion, Integer> {
    ItSurveyQuestion selectById(Integer questionId);

    List<ItSurveyQuestion> selectByMap(Map<String, Object> map);

    List<ItSurveyQuestion> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ItSurveyQuestion obj);

    Integer deleteById(Integer questionId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ItSurveyQuestion obj);

    Integer updateSelectiveByMap(@Param("record") ItSurveyQuestion obj, @Param("map") Map<String, Object> param);
}