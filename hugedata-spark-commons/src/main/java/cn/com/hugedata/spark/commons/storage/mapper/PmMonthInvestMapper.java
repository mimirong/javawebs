package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthInvest;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PmMonthInvestMapper extends BaseMapper<PmMonthInvest, Integer>, ManagementMapper<PmMonthInvest, Integer> {
    PmMonthInvest selectById(Integer investId);

    List<PmMonthInvest> selectByMap(Map<String, Object> map);

    List<PmMonthInvest> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(PmMonthInvest obj);

    Integer deleteById(Integer investId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(PmMonthInvest obj);

    Integer updateSelectiveByMap(@Param("record") PmMonthInvest obj, @Param("map") Map<String, Object> param);
}