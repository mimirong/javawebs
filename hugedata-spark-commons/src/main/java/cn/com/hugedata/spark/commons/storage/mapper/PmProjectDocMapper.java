package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PmProjectDoc;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PmProjectDocMapper extends BaseMapper<PmProjectDoc, Integer>, ManagementMapper<PmProjectDoc, Integer> {
    PmProjectDoc selectById(Integer docId);

    List<PmProjectDoc> selectByMap(Map<String, Object> map);

    List<PmProjectDoc> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PmProjectDoc obj);

    Integer deleteById(Integer docId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PmProjectDoc obj);

    Integer updateSelectiveByMap(@Param("record") PmProjectDoc obj, @Param("map") Map<String, Object> param);
}