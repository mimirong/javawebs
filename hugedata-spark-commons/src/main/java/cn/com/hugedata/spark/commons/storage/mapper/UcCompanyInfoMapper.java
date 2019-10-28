package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcCompanyInfoMapper extends BaseMapper<UcCompanyInfo, Integer>, ManagementMapper<UcCompanyInfo, Integer> {
    UcCompanyInfo selectById(Integer companyId);

    List<UcCompanyInfo> selectByMap(Map<String, Object> map);

    List<UcCompanyInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcCompanyInfo obj);

    Integer deleteById(Integer companyId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcCompanyInfo obj);

    Integer updateSelectiveByMap(@Param("record") UcCompanyInfo obj, @Param("map") Map<String, Object> param);
}