package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PtCategory;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PtCategoryMapper extends BaseMapper<PtCategory, String>, ManagementMapper<PtCategory, String> {
    PtCategory selectById(String categoryId);

    List<PtCategory> selectByMap(Map<String, Object> map);

    List<PtCategory> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PtCategory obj);

    Integer deleteById(String categoryId);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PtCategory obj);

    Integer updateSelectiveByMap(@Param("record") PtCategory obj, @Param("map") Map<String, Object> param);
}