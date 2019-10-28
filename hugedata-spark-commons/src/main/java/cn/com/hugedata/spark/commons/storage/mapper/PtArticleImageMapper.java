package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PtArticleImage;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PtArticleImageMapper extends BaseMapper<PtArticleImage, Integer>, ManagementMapper<PtArticleImage, Integer> {
    PtArticleImage selectById(Integer imageId);

    List<PtArticleImage> selectByMap(Map<String, Object> map);

    List<PtArticleImage> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PtArticleImage obj);

    Integer deleteById(Integer imageId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PtArticleImage obj);

    Integer updateSelectiveByMap(@Param("record") PtArticleImage obj, @Param("map") Map<String, Object> param);
}