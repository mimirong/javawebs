package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OsRequireInfoMapper extends BaseMapper<OsRequireInfo, Integer>, ManagementMapper<OsRequireInfo, Integer> {
    OsRequireInfo selectById(Integer requireId);

    List<OsRequireInfo> selectByMap(Map<String, Object> map);

    List<OsRequireInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(OsRequireInfo obj);

    Integer deleteById(Integer requireId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(OsRequireInfo obj);

    Integer updateSelectiveByMap(@Param("record") OsRequireInfo obj, @Param("map") Map<String, Object> param);
}