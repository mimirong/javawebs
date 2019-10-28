package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PmDocType;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PmDocTypeMapper extends BaseMapper<PmDocType, Integer>, ManagementMapper<PmDocType, Integer> {
    PmDocType selectById(Integer typeId);

    List<PmDocType> selectByMap(Map<String, Object> map);

    List<PmDocType> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PmDocType obj);

    Integer deleteById(Integer typeId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PmDocType obj);

    Integer updateSelectiveByMap(@Param("record") PmDocType obj, @Param("map") Map<String, Object> param);
}