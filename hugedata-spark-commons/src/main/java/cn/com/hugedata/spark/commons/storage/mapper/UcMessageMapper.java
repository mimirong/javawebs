package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcMessageMapper extends BaseMapper<UcMessage, Integer>, ManagementMapper<UcMessage, Integer> {
    UcMessage selectById(Integer messageId);

    List<UcMessage> selectByMap(Map<String, Object> map);

    List<UcMessage> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcMessage obj);

    Integer deleteById(Integer messageId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcMessage obj);

    Integer updateSelectiveByMap(@Param("record") UcMessage obj, @Param("map") Map<String, Object> param);
}