package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ApServiceInfoMapper extends BaseMapper<ApServiceInfo, Integer>, ManagementMapper<ApServiceInfo, Integer> {
    ApServiceInfo selectById(Integer serviceId);

    List<ApServiceInfo> selectByMap(Map<String, Object> map);

    List<ApServiceInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ApServiceInfo obj);

    Integer deleteById(Integer serviceId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ApServiceInfo obj);

    Integer updateSelectiveByMap(@Param("record") ApServiceInfo obj, @Param("map") Map<String, Object> param);
}