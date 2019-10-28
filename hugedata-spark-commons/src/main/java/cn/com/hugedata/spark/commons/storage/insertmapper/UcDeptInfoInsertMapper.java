package cn.com.hugedata.spark.commons.storage.insertmapper;

import cn.com.hugedata.spark.commons.storage.entity.UcDeptInfo;

import java.util.List;

/**
 * Created by LvJianXing on 2017/1/18.
 */
public interface UcDeptInfoInsertMapper  {

    Integer insertAll(List<UcDeptInfo> ucDeptInfoList);

}
