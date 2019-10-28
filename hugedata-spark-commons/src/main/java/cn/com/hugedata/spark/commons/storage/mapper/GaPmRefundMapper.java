package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaPmRefund;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaPmRefundMapper extends BaseMapper<GaPmRefund, Integer>, ManagementMapper<GaPmRefund, Integer> {
    GaPmRefund selectById(Integer refundId);

    List<GaPmRefund> selectByMap(Map<String, Object> map);

    List<GaPmRefund> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaPmRefund obj);

    Integer deleteById(Integer refundId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaPmRefund obj);

    Integer updateSelectiveByMap(@Param("record") GaPmRefund obj, @Param("map") Map<String, Object> param);
}