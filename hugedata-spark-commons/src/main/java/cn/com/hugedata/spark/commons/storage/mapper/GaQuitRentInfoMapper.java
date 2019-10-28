package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaQuitRentInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaQuitRentInfoMapper extends BaseMapper<GaQuitRentInfo, Integer>, ManagementMapper<GaQuitRentInfo, Integer> {
    GaQuitRentInfo selectById(Integer infoId);

    List<GaQuitRentInfo> selectByMap(Map<String, Object> map);

    List<GaQuitRentInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaQuitRentInfo obj);

    Integer deleteById(Integer infoId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaQuitRentInfo obj);

    Integer updateSelectiveByMap(@Param("record") GaQuitRentInfo obj, @Param("map") Map<String, Object> param);
}