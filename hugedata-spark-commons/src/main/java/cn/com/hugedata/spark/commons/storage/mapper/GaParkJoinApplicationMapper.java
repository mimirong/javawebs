package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinApplication;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaParkJoinApplicationMapper extends BaseMapper<GaParkJoinApplication, Integer>, ManagementMapper<GaParkJoinApplication, Integer> {
    GaParkJoinApplication selectById(Integer applicationId);

    List<GaParkJoinApplication> selectByMap(Map<String, Object> map);

    List<GaParkJoinApplication> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaParkJoinApplication obj);

    Integer deleteById(Integer applicationId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaParkJoinApplication obj);

    Integer updateSelectiveByMap(@Param("record") GaParkJoinApplication obj, @Param("map") Map<String, Object> param);
}