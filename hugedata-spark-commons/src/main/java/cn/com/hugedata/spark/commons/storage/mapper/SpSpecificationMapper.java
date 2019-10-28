package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.SpSpecification;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SpSpecificationMapper extends BaseMapper<SpSpecification, Integer>, ManagementMapper<SpSpecification, Integer> {
    SpSpecification selectById(Integer specId);

    List<SpSpecification> selectByMap(Map<String, Object> map);

    List<SpSpecification> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(SpSpecification obj);

    Integer deleteById(Integer specId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(SpSpecification obj);

    Integer updateSelectiveByMap(@Param("record") SpSpecification obj, @Param("map") Map<String, Object> param);
}