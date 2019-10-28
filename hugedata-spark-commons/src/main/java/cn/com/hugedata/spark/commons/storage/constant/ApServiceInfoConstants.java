package cn.com.hugedata.spark.commons.storage.constant;

public interface ApServiceInfoConstants {

    /** 类型：在线办理. */
    String BUSINESS_TYPE_ONLINE = "onLine";
    
    /** 类型：窗口办理. */
    String BUSINESS_TYPE_WINDOW = "byWindow";
 

    
    /** 状态：待预审. */
    String STATUS_CREATED = "110";
    
    /** 状态：待初审. */
    String STATUS_TO_FIRST_CHECK = "120";
    
    /** 状态：待复审. */
    String STATUS_TO_REVIEW = "130";
    
    /** 状态：待决定. */
    String STATUS_TO_DECIDE = "140";
    
    /** 状态：待主任决定. */
    String STATUS_TO_DIRECTOR_DECIDE = "150";
    
    /** 状态：待书记决定. */
    String STATUS_TO_SECRETARY_DECIDE = "160";
    
    /** 状态：办结待确认. */
    String STATUS_FINISH_CONFIRM = "210";
    
    /** 状态：已办结. */
    String STATUS_FINISHED = "211";
    
    /** 状态：废弃待确认. */
    String STATUS_REJECT_CONFIRM = "220";
    
    /** 状态：已废弃 . */
    String STATUS_REJECTED = "221";
    
    
    /** 状态：已撤销. */
    String STATUS_CANCELLED = "231";
}
