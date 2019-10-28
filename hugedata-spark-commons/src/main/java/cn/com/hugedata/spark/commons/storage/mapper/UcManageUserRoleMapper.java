package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcManageUserRole;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcManageUserRoleMapper extends BaseMapper<UcManageUserRole, Integer>, ManagementMapper<UcManageUserRole, Integer> {
    UcManageUserRole selectById(Integer id);

    List<UcManageUserRole> selectByMap(Map<String, Object> map);

    List<UcManageUserRole> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcManageUserRole obj);

    Integer deleteById(Integer id);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcManageUserRole obj);

    Integer updateSelectiveByMap(@Param("record") UcManageUserRole obj, @Param("map") Map<String, Object> param);
}