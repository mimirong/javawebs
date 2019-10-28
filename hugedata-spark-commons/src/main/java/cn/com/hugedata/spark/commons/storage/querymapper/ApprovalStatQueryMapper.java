package cn.com.hugedata.spark.commons.storage.querymapper;

import java.util.List;
import java.util.Map;

import cn.com.hugedata.spark.commons.storage.queryentity.ApprovalStatItem;

/**
 * 查询办事服务统计的Mapper.
 */
public interface ApprovalStatQueryMapper {

    List<ApprovalStatItem> summaryByUser(Map<String, Object> params);
    
}
