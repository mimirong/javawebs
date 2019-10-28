package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.SysImagePreview;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SysImagePreviewMapper extends BaseMapper<SysImagePreview, Integer>, ManagementMapper<SysImagePreview, Integer> {
    SysImagePreview selectById(Integer previewId);

    List<SysImagePreview> selectByMap(Map<String, Object> map);

    List<SysImagePreview> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(SysImagePreview obj);

    Integer deleteById(Integer previewId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(SysImagePreview obj);

    Integer updateSelectiveByMap(@Param("record") SysImagePreview obj, @Param("map") Map<String, Object> param);
}