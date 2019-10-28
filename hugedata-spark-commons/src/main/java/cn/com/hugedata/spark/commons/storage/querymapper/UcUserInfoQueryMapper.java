package cn.com.hugedata.spark.commons.storage.querymapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;

public interface UcUserInfoQueryMapper {

    List<UcUserInfo> selectByMap(Map<String, Object> params);

    List<UcUserInfo> selectByMap(Map<String, Object> params, RowBounds rb);

    int countByMap(Map<String, Object> deptCode);
    
}
