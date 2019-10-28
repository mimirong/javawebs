package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceProcess;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ApServiceProcessMapper extends BaseMapper<ApServiceProcess, Integer>, ManagementMapper<ApServiceProcess, Integer> {
    ApServiceProcess selectById(Integer processId);

    List<ApServiceProcess> selectByMap(Map<String, Object> map);

    List<ApServiceProcess> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ApServiceProcess obj);

    Integer deleteById(Integer processId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ApServiceProcess obj);

    Integer updateSelectiveByMap(@Param("record") ApServiceProcess obj, @Param("map") Map<String, Object> param);
}