package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.OsRequireAtt;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OsRequireAttMapper extends BaseMapper<OsRequireAtt, Integer>, ManagementMapper<OsRequireAtt, Integer> {
    OsRequireAtt selectById(Integer attId);

    List<OsRequireAtt> selectByMap(Map<String, Object> map);

    List<OsRequireAtt> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(OsRequireAtt obj);

    Integer deleteById(Integer attId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(OsRequireAtt obj);

    Integer updateSelectiveByMap(@Param("record") OsRequireAtt obj, @Param("map") Map<String, Object> param);
}