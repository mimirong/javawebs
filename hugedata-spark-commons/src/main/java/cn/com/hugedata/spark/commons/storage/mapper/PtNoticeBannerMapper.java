package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PtNoticeBanner;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PtNoticeBannerMapper extends BaseMapper<PtNoticeBanner, Integer>, ManagementMapper<PtNoticeBanner, Integer> {
    PtNoticeBanner selectById(Integer imageId);

    List<PtNoticeBanner> selectByMap(Map<String, Object> map);

    List<PtNoticeBanner> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PtNoticeBanner obj);

    Integer deleteById(Integer imageId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PtNoticeBanner obj);

    Integer updateSelectiveByMap(@Param("record") PtNoticeBanner obj, @Param("map") Map<String, Object> param);
}