package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PtHomeImage;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PtHomeImageMapper extends BaseMapper<PtHomeImage, Integer>, ManagementMapper<PtHomeImage, Integer> {
    PtHomeImage selectById(Integer imageId);

    List<PtHomeImage> selectByMap(Map<String, Object> map);

    List<PtHomeImage> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PtHomeImage obj);

    Integer deleteById(Integer imageId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PtHomeImage obj);

    Integer updateSelectiveByMap(@Param("record") PtHomeImage obj, @Param("map") Map<String, Object> param);
}