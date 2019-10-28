package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcOrgInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcOrgInfoMapper extends BaseMapper<UcOrgInfo, Integer>, ManagementMapper<UcOrgInfo, Integer> {
    UcOrgInfo selectById(Integer orgId);

    List<UcOrgInfo> selectByMap(Map<String, Object> map);

    List<UcOrgInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcOrgInfo obj);

    Integer deleteById(Integer orgId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcOrgInfo obj);

    Integer updateSelectiveByMap(@Param("record") UcOrgInfo obj, @Param("map") Map<String, Object> param);
}