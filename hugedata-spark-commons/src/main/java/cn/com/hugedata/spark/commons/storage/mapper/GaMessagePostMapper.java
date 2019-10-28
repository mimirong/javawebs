package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaMessagePost;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaMessagePostMapper extends BaseMapper<GaMessagePost, Integer>, ManagementMapper<GaMessagePost, Integer> {
    GaMessagePost selectById(Integer postId);

    List<GaMessagePost> selectByMap(Map<String, Object> map);

    List<GaMessagePost> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaMessagePost obj);

    Integer deleteById(Integer postId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaMessagePost obj);

    Integer updateSelectiveByMap(@Param("record") GaMessagePost obj, @Param("map") Map<String, Object> param);
}