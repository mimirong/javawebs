package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.OsMeetingTraining;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OsMeetingTrainingMapper extends BaseMapper<OsMeetingTraining, Integer>, ManagementMapper<OsMeetingTraining, Integer> {
    OsMeetingTraining selectById(Integer trainingId);

    List<OsMeetingTraining> selectByMap(Map<String, Object> map);

    List<OsMeetingTraining> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(OsMeetingTraining obj);

    Integer deleteById(Integer trainingId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(OsMeetingTraining obj);

    Integer updateSelectiveByMap(@Param("record") OsMeetingTraining obj, @Param("map") Map<String, Object> param);
}