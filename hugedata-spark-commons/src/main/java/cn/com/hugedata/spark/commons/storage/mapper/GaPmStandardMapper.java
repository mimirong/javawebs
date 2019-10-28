package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaPmStandard;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaPmStandardMapper extends BaseMapper<GaPmStandard, String>, ManagementMapper<GaPmStandard, String> {
    GaPmStandard selectById(String standardId);

    List<GaPmStandard> selectByMap(Map<String, Object> map);

    List<GaPmStandard> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaPmStandard obj);

    Integer deleteById(String standardId);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaPmStandard obj);

    Integer updateSelectiveByMap(@Param("record") GaPmStandard obj, @Param("map") Map<String, Object> param);
}