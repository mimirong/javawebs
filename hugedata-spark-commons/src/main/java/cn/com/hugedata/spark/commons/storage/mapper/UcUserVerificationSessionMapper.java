package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcUserVerificationSession;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcUserVerificationSessionMapper extends BaseMapper<UcUserVerificationSession, String>, ManagementMapper<UcUserVerificationSession, String> {
    UcUserVerificationSession selectById(String sessionId);

    List<UcUserVerificationSession> selectByMap(Map<String, Object> map);

    List<UcUserVerificationSession> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcUserVerificationSession obj);

    Integer deleteById(String sessionId);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcUserVerificationSession obj);

    Integer updateSelectiveByMap(@Param("record") UcUserVerificationSession obj, @Param("map") Map<String, Object> param);
}