package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRoleRight;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcManageRoleRightMapper extends BaseMapper<UcManageRoleRight, Integer>, ManagementMapper<UcManageRoleRight, Integer> {
    UcManageRoleRight selectById(Integer id);

    List<UcManageRoleRight> selectByMap(Map<String, Object> map);

    List<UcManageRoleRight> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcManageRoleRight obj);

    Integer deleteById(Integer id);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcManageRoleRight obj);

    Integer updateSelectiveByMap(@Param("record") UcManageRoleRight obj, @Param("map") Map<String, Object> param);
}