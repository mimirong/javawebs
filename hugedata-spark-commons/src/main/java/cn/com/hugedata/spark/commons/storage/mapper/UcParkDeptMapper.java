package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcParkDept;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcParkDeptMapper extends BaseMapper<UcParkDept, Integer>, ManagementMapper<UcParkDept, Integer> {
    UcParkDept selectById(Integer deptId);

    List<UcParkDept> selectByMap(Map<String, Object> map);

    List<UcParkDept> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcParkDept obj);

    Integer deleteById(Integer deptId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcParkDept obj);

    Integer updateSelectiveByMap(@Param("record") UcParkDept obj, @Param("map") Map<String, Object> param);
}