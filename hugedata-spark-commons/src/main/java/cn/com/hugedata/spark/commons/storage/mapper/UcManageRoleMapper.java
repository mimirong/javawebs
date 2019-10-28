package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRole;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcManageRoleMapper extends BaseMapper<UcManageRole, String>, ManagementMapper<UcManageRole, String> {
    UcManageRole selectById(String roleId);

    List<UcManageRole> selectByMap(Map<String, Object> map);

    List<UcManageRole> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcManageRole obj);

    Integer deleteById(String roleId);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcManageRole obj);

    Integer updateSelectiveByMap(@Param("record") UcManageRole obj, @Param("map") Map<String, Object> param);
}