package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.SpServiceProject;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SpServiceProjectMapper extends BaseMapper<SpServiceProject, Integer>, ManagementMapper<SpServiceProject, Integer> {
    SpServiceProject selectById(Integer projectId);

    List<SpServiceProject> selectByMap(Map<String, Object> map);

    List<SpServiceProject> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(SpServiceProject obj);

    Integer deleteById(Integer projectId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(SpServiceProject obj);

    Integer updateSelectiveByMap(@Param("record") SpServiceProject obj, @Param("map") Map<String, Object> param);
}