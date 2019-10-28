package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaAptitude;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaAptitudeMapper extends BaseMapper<GaAptitude, Integer>, ManagementMapper<GaAptitude, Integer> {
    GaAptitude selectById(Integer aptitudeId);

    List<GaAptitude> selectByMap(Map<String, Object> map);

    List<GaAptitude> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaAptitude obj);

    Integer deleteById(Integer aptitudeId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaAptitude obj);

    Integer updateSelectiveByMap(@Param("record") GaAptitude obj, @Param("map") Map<String, Object> param);
}