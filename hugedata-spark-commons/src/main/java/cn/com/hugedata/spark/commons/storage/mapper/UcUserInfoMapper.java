package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcUserInfoMapper extends BaseMapper<UcUserInfo, Integer>, ManagementMapper<UcUserInfo, Integer> {
    UcUserInfo selectById(Integer userId);

    List<UcUserInfo> selectByMap(Map<String, Object> map);

    List<UcUserInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcUserInfo obj);

    Integer deleteById(Integer userId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcUserInfo obj);

    Integer updateSelectiveByMap(@Param("record") UcUserInfo obj, @Param("map") Map<String, Object> param);
}