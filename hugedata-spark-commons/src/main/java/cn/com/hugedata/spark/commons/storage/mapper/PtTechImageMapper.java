package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PtTechImageMapper extends BaseMapper<PtTechImage, Integer>, ManagementMapper<PtTechImage, Integer> {
    PtTechImage selectById(Integer imageId);

    List<PtTechImage> selectByMap(Map<String, Object> map);

    List<PtTechImage> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PtTechImage obj);

    Integer deleteById(Integer imageId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PtTechImage obj);

    Integer updateSelectiveByMap(@Param("record") PtTechImage obj, @Param("map") Map<String, Object> param);
}