package cn.com.hugedata.spark.commons.storage.constant;

public interface OsTechServiceApplyConstants {

    /** 申请类型：云主机. */
    String APPLY_TYPE_COMPUTING = "COMPUTING";

    /** 申请类型：云存储. */
    String APPLY_TYPE_STORAGE = "STORAGE";
    
    
    
    /** 状态: 待审批. */
    String STATUS_CREATED = "CREATED";
    
    /** 状态: 已审批. */
    String STATUS_APPROVED = "APPROVED";

    /** 状态：审批不通过. */
    String STATUS_REJECTED = "REJECTED";
}
