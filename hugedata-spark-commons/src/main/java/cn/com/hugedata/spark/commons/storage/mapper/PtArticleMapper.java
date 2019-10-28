package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PtArticleMapper extends BaseMapper<PtArticle, Integer>, ManagementMapper<PtArticle, Integer> {
    PtArticle selectById(Integer articleId);

    List<PtArticle> selectByMap(Map<String, Object> map);

    List<PtArticle> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PtArticle obj);

    Integer deleteById(Integer articleId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PtArticle obj);

    Integer updateSelectiveByMap(@Param("record") PtArticle obj, @Param("map") Map<String, Object> param);
}