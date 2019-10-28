package cn.com.hugedata.spark.commons.storage.querymapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import cn.com.hugedata.spark.commons.storage.entity.PmProject;
import cn.com.hugedata.spark.commons.storage.queryentity.ApplyMergeInfo;

public interface ApplyMergeQueryMapper {

    public List<ApplyMergeInfo> queryByMap(Map<String, Object> params);

    public List<ApplyMergeInfo> queryByMap(Map<String, Object> params, RowBounds rb);

    public int countByMap(Map<String, Object> params);  
    
    public List<PmProject> listProjectAdvance(Map<String, Object> params,RowBounds rowBounds);
    
    public int countProjectAdvance(Map<String, Object> params);  
}
