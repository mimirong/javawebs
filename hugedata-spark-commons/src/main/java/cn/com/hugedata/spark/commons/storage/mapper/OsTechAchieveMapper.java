package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.OsTechAchieve;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OsTechAchieveMapper extends BaseMapper<OsTechAchieve, Integer>, ManagementMapper<OsTechAchieve, Integer> {
    OsTechAchieve selectById(Integer achieveId);

    List<OsTechAchieve> selectByMap(Map<String, Object> map);

    List<OsTechAchieve> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(OsTechAchieve obj);

    Integer deleteById(Integer achieveId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(OsTechAchieve obj);

    Integer updateSelectiveByMap(@Param("record") OsTechAchieve obj, @Param("map") Map<String, Object> param);
}