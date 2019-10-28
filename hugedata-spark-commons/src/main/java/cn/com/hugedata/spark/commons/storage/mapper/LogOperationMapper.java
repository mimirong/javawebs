package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogOperationMapper extends BaseMapper<LogOperation, Integer>, ManagementMapper<LogOperation, Integer> {
    LogOperation selectById(Integer logId);

    List<LogOperation> selectByMap(Map<String, Object> map);

    List<LogOperation> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(LogOperation obj);

    Integer deleteById(Integer logId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(LogOperation obj);

    Integer updateSelectiveByMap(@Param("record") LogOperation obj, @Param("map") Map<String, Object> param);
}