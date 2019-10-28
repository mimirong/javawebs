package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.OsExpertsList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OsExpertsListMapper extends BaseMapper<OsExpertsList, Integer>, ManagementMapper<OsExpertsList, Integer> {
    OsExpertsList selectById(Integer expertId);

    List<OsExpertsList> selectByMap(Map<String, Object> map);

    List<OsExpertsList> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(OsExpertsList obj);

    Integer deleteById(Integer expertId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(OsExpertsList obj);

    Integer updateSelectiveByMap(@Param("record") OsExpertsList obj, @Param("map") Map<String, Object> param);
}