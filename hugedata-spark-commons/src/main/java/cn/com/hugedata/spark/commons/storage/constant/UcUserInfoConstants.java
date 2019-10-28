package cn.com.hugedata.spark.commons.storage.constant;

public interface UcUserInfoConstants {

    /** 用户类型:企业用户. */
    public static final String USER_TYPE_COMPANY = "COMPANY";
    
    /** 用户类型:园区管委会用户. */
    public static final String USER_TYPE_PARK = "PARK";
    
    /** 用户类型:系统用户. */
    public static final String USER_TYPE_SYSTEM = "SYSTEM";
    
    
    /** 审核状态：未审核. */
    String APPROVE_STATUS_CREATED = "CREATED";
    
    /** 审核状态：已审核. */
    String APPROVE_STATUS_APPROVED = "APPROVED";
    
    
    // 用户角色 ServiceRoles

    /** 用户角色: 窗口. */
    String SR_WINDOW = "WINDOW";

    /** 用户角色: 副局长. */
    String SR_DEPUTY_DIRECTOR = "DEPUTY_DIRECTOR";
    
    /** 用户角色: 局长. */
    String SR_DIRECTOR = "DIRECTOR";
    
    /** 用户角色: 副主任. */
    String SR_DEPUTY_CHIEF = "DEPUTY_CHIEF";
    
    /** 用户角色: 主任. */
    String SR_CHIEF = "CHIEF";

    /** 用户角色: 书记. */
    String SR_SECRETARY_GENERAL = "SECRETARY_GENERAL";
}
