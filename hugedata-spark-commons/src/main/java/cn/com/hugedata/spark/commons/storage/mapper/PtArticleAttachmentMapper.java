package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PtArticleAttachment;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PtArticleAttachmentMapper extends BaseMapper<PtArticleAttachment, Integer>, ManagementMapper<PtArticleAttachment, Integer> {
    PtArticleAttachment selectById(Integer attachmentId);

    List<PtArticleAttachment> selectByMap(Map<String, Object> map);

    List<PtArticleAttachment> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PtArticleAttachment obj);

    Integer deleteById(Integer attachmentId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PtArticleAttachment obj);

    Integer updateSelectiveByMap(@Param("record") PtArticleAttachment obj, @Param("map") Map<String, Object> param);
}