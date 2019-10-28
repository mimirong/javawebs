package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaRentInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaRentInfoMapper extends BaseMapper<GaRentInfo, Integer>, ManagementMapper<GaRentInfo, Integer> {
    GaRentInfo selectById(Integer rentInfoId);

    List<GaRentInfo> selectByMap(Map<String, Object> map);

    List<GaRentInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaRentInfo obj);

    Integer deleteById(Integer rentInfoId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaRentInfo obj);

    Integer updateSelectiveByMap(@Param("record") GaRentInfo obj, @Param("map") Map<String, Object> param);
}