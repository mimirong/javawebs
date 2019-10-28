package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.SpProjectImage;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SpProjectImageMapper extends BaseMapper<SpProjectImage, Integer>, ManagementMapper<SpProjectImage, Integer> {
    SpProjectImage selectById(Integer imageId);

    List<SpProjectImage> selectByMap(Map<String, Object> map);

    List<SpProjectImage> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(SpProjectImage obj);

    Integer deleteById(Integer imageId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(SpProjectImage obj);

    Integer updateSelectiveByMap(@Param("record") SpProjectImage obj, @Param("map") Map<String, Object> param);
}