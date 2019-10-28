package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitApplication;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaParkQuitApplicationMapper extends BaseMapper<GaParkQuitApplication, Integer>, ManagementMapper<GaParkQuitApplication, Integer> {
    GaParkQuitApplication selectById(Integer applicationId);

    List<GaParkQuitApplication> selectByMap(Map<String, Object> map);

    List<GaParkQuitApplication> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaParkQuitApplication obj);

    Integer deleteById(Integer applicationId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaParkQuitApplication obj);

    Integer updateSelectiveByMap(@Param("record") GaParkQuitApplication obj, @Param("map") Map<String, Object> param);
}