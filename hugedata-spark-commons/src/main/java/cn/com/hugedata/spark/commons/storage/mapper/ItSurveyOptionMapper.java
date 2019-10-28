package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ItSurveyOption;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ItSurveyOptionMapper extends BaseMapper<ItSurveyOption, Integer>, ManagementMapper<ItSurveyOption, Integer> {
    ItSurveyOption selectById(Integer optionId);

    List<ItSurveyOption> selectByMap(Map<String, Object> map);

    List<ItSurveyOption> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ItSurveyOption obj);

    Integer deleteById(Integer optionId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ItSurveyOption obj);

    Integer updateSelectiveByMap(@Param("record") ItSurveyOption obj, @Param("map") Map<String, Object> param);
}