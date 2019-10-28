package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcAdminUser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcAdminUserMapper extends BaseMapper<UcAdminUser, String>, ManagementMapper<UcAdminUser, String> {
    UcAdminUser selectById(String userId);

    List<UcAdminUser> selectByMap(Map<String, Object> map);

    List<UcAdminUser> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcAdminUser obj);

    Integer deleteById(String userId);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcAdminUser obj);

    Integer updateSelectiveByMap(@Param("record") UcAdminUser obj, @Param("map") Map<String, Object> param);
}