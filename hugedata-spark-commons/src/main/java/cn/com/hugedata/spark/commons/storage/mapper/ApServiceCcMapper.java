package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceCc;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ApServiceCcMapper extends BaseMapper<ApServiceCc, Integer>, ManagementMapper<ApServiceCc, Integer> {
    ApServiceCc selectById(Integer id);

    List<ApServiceCc> selectByMap(Map<String, Object> map);

    List<ApServiceCc> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ApServiceCc obj);

    Integer deleteById(Integer id);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ApServiceCc obj);

    Integer updateSelectiveByMap(@Param("record") ApServiceCc obj, @Param("map") Map<String, Object> param);
}