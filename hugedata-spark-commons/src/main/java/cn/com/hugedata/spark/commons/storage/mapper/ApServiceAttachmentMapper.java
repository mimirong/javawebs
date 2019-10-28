package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceAttachment;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ApServiceAttachmentMapper extends BaseMapper<ApServiceAttachment, Integer>, ManagementMapper<ApServiceAttachment, Integer> {
    ApServiceAttachment selectById(Integer attachmentId);

    List<ApServiceAttachment> selectByMap(Map<String, Object> map);

    List<ApServiceAttachment> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(ApServiceAttachment obj);

    Integer deleteById(Integer attachmentId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(ApServiceAttachment obj);

    Integer updateSelectiveByMap(@Param("record") ApServiceAttachment obj, @Param("map") Map<String, Object> param);
}