package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.GaSiteProof;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GaSiteProofMapper extends BaseMapper<GaSiteProof, Integer>, ManagementMapper<GaSiteProof, Integer> {
    GaSiteProof selectById(Integer siteProofId);

    List<GaSiteProof> selectByMap(Map<String, Object> map);

    List<GaSiteProof> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(GaSiteProof obj);

    Integer deleteById(Integer siteProofId);

    Integer deleteByIds(List<Integer> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(GaSiteProof obj);

    Integer updateSelectiveByMap(@Param("record") GaSiteProof obj, @Param("map") Map<String, Object> param);
}