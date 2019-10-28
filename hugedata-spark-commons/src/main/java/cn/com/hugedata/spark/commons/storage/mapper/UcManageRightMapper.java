package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRight;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcManageRightMapper extends BaseMapper<UcManageRight, String>, ManagementMapper<UcManageRight, String> {
    UcManageRight selectById(String rightId);

    List<UcManageRight> selectByMap(Map<String, Object> map);

    List<UcManageRight> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcManageRight obj);

    Integer deleteById(String rightId);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcManageRight obj);

    Integer updateSelectiveByMap(@Param("record") UcManageRight obj, @Param("map") Map<String, Object> param);
}