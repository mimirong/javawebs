package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PmProject;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PmProjectMapper extends BaseMapper<PmProject, Integer>, ManagementMapper<PmProject, Integer> {
    PmProject selectById(Integer projectId);

    List<PmProject> selectByMap(Map<String, Object> map);

    List<PmProject> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PmProject obj);

    Integer deleteById(Integer projectId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PmProject obj);

    Integer updateSelectiveByMap(@Param("record") PmProject obj, @Param("map") Map<String, Object> param);
}