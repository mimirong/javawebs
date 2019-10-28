package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaPmBill;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaPmBillMapper extends BaseMapper<GaPmBill, Integer>, ManagementMapper<GaPmBill, Integer> {
    GaPmBill selectById(Integer billId);

    List<GaPmBill> selectByMap(Map<String, Object> map);

    List<GaPmBill> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaPmBill obj);

    Integer deleteById(Integer billId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaPmBill obj);

    Integer updateSelectiveByMap(@Param("record") GaPmBill obj, @Param("map") Map<String, Object> param);
}