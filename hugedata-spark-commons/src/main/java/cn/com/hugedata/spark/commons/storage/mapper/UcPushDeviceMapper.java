package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcPushDevice;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UcPushDeviceMapper extends BaseMapper<UcPushDevice, String>, ManagementMapper<UcPushDevice, String> {
    UcPushDevice selectById(String registrationId);

    List<UcPushDevice> selectByMap(Map<String, Object> map);

    List<UcPushDevice> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(UcPushDevice obj);

    Integer deleteById(String registrationId);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(UcPushDevice obj);

    Integer updateSelectiveByMap(@Param("record") UcPushDevice obj, @Param("map") Map<String, Object> param);
}