package cn.com.hugedata.spark.commons.storage.constant;

public interface EcBiddingInfoConstants {

    /** 状态: 招标中. */
    String STATUS_IN_PROGRESS = "IN_PROGRESS";

    /** 状态: 已结束. */
    String STATUS_FINISHED = "FINISHED";
    
    //--------------------------------------------------------------------------

    /** 行业类型: 建筑. */
    String INDUSTRY_TYPE_CONSTRUCT = "CONSTRUCT";
    
    /** 行业类型: 设计. */
    String INDUSTRY_TYPE_DESIGN = "DESIGN";
    
    /** 行业类型: 设备采购. */
    String INDUSTRY_TYPE_EQUIPEMENT = "EQUIPEMENT";
    
    /** 行业类型: 其它. */
    String INDUSTRY_TYPE_OTHER = "OTHER";
}
