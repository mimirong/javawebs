package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcDeptInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcDeptInfoMapper extends BaseMapper<UcDeptInfo, Integer>, ManagementMapper<UcDeptInfo, Integer> {
    UcDeptInfo selectById(Integer deptId);

    List<UcDeptInfo> selectByMap(Map<String, Object> map);

    List<UcDeptInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcDeptInfo obj);

    Integer deleteById(Integer deptId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcDeptInfo obj);

    Integer updateSelectiveByMap(@Param("record") UcDeptInfo obj, @Param("map") Map<String, Object> param);
}