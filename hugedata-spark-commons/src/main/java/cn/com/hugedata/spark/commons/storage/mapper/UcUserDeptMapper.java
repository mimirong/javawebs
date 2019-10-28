package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcUserDept;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcUserDeptMapper extends BaseMapper<UcUserDept, Integer>, ManagementMapper<UcUserDept, Integer> {
    UcUserDept selectById(Integer recordId);

    List<UcUserDept> selectByMap(Map<String, Object> map);

    List<UcUserDept> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcUserDept obj);

    Integer deleteById(Integer recordId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcUserDept obj);

    Integer updateSelectiveByMap(@Param("record") UcUserDept obj, @Param("map") Map<String, Object> param);
}