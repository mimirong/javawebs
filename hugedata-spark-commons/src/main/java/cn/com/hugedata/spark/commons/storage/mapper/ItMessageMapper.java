package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ItMessage;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ItMessageMapper extends BaseMapper<ItMessage, Integer>, ManagementMapper<ItMessage, Integer> {
    ItMessage selectById(Integer messageId);

    List<ItMessage> selectByMap(Map<String, Object> map);

    List<ItMessage> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ItMessage obj);

    Integer deleteById(Integer messageId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ItMessage obj);

    Integer updateSelectiveByMap(@Param("record") ItMessage obj, @Param("map") Map<String, Object> param);
}