package cn.com.hugedata.spark.commons.storage.mapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SysFileInfoMapper extends BaseMapper<SysFileInfo, String>, ManagementMapper<SysFileInfo, String> {
    SysFileInfo selectById(String fileId);

    List<SysFileInfo> selectByMap(Map<String, Object> map);

    List<SysFileInfo> selectByMap(Map<String, Object> map, RowBounds rb);

    Integer countByMap(Map<String, Object> map);

    Integer insertSelective(SysFileInfo obj);

    Integer deleteById(String fileId);

    Integer deleteByIds(List<String> ids);

    Integer deleteByMap(Map<String, Object> map);

    Integer updateSelectiveById(SysFileInfo obj);

    Integer updateSelectiveByMap(@Param("record") SysFileInfo obj, @Param("map") Map<String, Object> param);
}