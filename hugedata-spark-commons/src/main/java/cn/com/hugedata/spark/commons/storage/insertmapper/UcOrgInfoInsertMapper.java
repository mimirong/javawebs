package cn.com.hugedata.spark.commons.storage.insertmapper;

import cn.com.hugedata.spark.commons.storage.base.BaseMapper;
import cn.com.hugedata.spark.commons.storage.base.ManagementMapper;
import cn.com.hugedata.spark.commons.storage.entity.UcOrgInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UcOrgInfoInsertMapper extends BaseMapper<UcOrgInfo, Integer>, ManagementMapper<UcOrgInfo, Integer> {

    Integer insertAll(List<UcOrgInfo> ucOrgInfoList);

}