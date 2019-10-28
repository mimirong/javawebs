package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceSpec;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OsTechServiceSpecMapper extends BaseMapper<OsTechServiceSpec, Integer>, ManagementMapper<OsTechServiceSpec, Integer> {
    OsTechServiceSpec selectById(Integer specId);

    List<OsTechServiceSpec> selectByMap(Map<String, Object> map);

    List<OsTechServiceSpec> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(OsTechServiceSpec obj);

    Integer deleteById(Integer specId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(OsTechServiceSpec obj);

    Integer updateSelectiveByMap(@Param("record") OsTechServiceSpec obj, @Param("map") Map<String, Object> param);
}