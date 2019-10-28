package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.LogInterfaceCall;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogInterfaceCallMapper extends BaseMapper<LogInterfaceCall, Integer>, ManagementMapper<LogInterfaceCall, Integer> {
    LogInterfaceCall selectById(Integer id);

    List<LogInterfaceCall> selectByMap(Map<String, Object> map);

    List<LogInterfaceCall> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(LogInterfaceCall obj);

    Integer deleteById(Integer id);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(LogInterfaceCall obj);

    Integer updateSelectiveByMap(@Param("record") LogInterfaceCall obj, @Param("map") Map<String, Object> param);
}