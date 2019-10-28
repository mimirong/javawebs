package cn.com.hugedata.spark.commons.storage.insertmapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UcUserInfoInsertMapper extends BaseMapper<UcUserInfo, Integer>, ManagementMapper<UcUserInfo, Integer> {

    Integer insertAll(List<UcUserInfo> ucUserInfoList);

    Integer synchronizeDimissUsers(List<UcUserInfo> ucUserInfoList);

}