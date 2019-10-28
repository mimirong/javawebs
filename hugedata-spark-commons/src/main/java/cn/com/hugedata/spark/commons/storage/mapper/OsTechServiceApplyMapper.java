package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceApply;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OsTechServiceApplyMapper extends BaseMapper<OsTechServiceApply, Integer>, ManagementMapper<OsTechServiceApply, Integer> {
    OsTechServiceApply selectById(Integer applyId);

    List<OsTechServiceApply> selectByMap(Map<String, Object> map);

    List<OsTechServiceApply> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(OsTechServiceApply obj);

    Integer deleteById(Integer applyId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(OsTechServiceApply obj);

    Integer updateSelectiveByMap(@Param("record") OsTechServiceApply obj, @Param("map") Map<String, Object> param);
}