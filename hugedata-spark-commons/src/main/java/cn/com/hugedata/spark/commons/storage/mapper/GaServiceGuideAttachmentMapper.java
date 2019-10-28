package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttachment;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaServiceGuideAttachmentMapper extends BaseMapper<GaServiceGuideAttachment, Integer>, ManagementMapper<GaServiceGuideAttachment, Integer> {
    GaServiceGuideAttachment selectById(Integer attachmentId);

    List<GaServiceGuideAttachment> selectByMap(Map<String, Object> map);

    List<GaServiceGuideAttachment> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaServiceGuideAttachment obj);

    Integer deleteById(Integer attachmentId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaServiceGuideAttachment obj);

    Integer updateSelectiveByMap(@Param("record") GaServiceGuideAttachment obj, @Param("map") Map<String, Object> param);
}