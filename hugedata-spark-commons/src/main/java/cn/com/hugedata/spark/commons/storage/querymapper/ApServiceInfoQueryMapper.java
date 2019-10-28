package cn.com.hugedata.spark.commons.storage.querymapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;

public interface ApServiceInfoQueryMapper {

    List<ApServiceInfo> selectForParkUser(Map<String, Object> map);

    List<ApServiceInfo> selectForParkUser(Map<String, Object> map, RowBounds rb);

    Integer countForParkUser(Map<String, Object> map);
    
}
