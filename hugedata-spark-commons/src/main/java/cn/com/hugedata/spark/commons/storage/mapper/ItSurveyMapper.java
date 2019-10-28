package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ItSurvey;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ItSurveyMapper extends BaseMapper<ItSurvey, Integer>, ManagementMapper<ItSurvey, Integer> {
    ItSurvey selectById(Integer surveyId);

    List<ItSurvey> selectByMap(Map<String, Object> map);

    List<ItSurvey> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ItSurvey obj);

    Integer deleteById(Integer surveyId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ItSurvey obj);

    Integer updateSelectiveByMap(@Param("record") ItSurvey obj, @Param("map") Map<String, Object> param);
}