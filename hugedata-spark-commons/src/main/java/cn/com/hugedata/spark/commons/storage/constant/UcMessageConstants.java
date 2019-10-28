package cn.com.hugedata.spark.commons.storage.constant;

public interface UcMessageConstants {

    /** 服务类型: 入园申请. */
    String TYPE_PARK_JOIN_APPLY = "PARK_JOIN_APPLY";

    /** 服务类型: 退园申请. */
    String TYPE_PARK_QUIT_APPLY = "PARK_QUIT_APPLY";
    
    
    /** 服务类型: 项目管理. */
    String TYPE_PROJECT_MANAGE = "PROJECT_MANAGE";
    
    /** 服务类型: 在线办理. */
    String TYPE_AP_SERVICE = "AP_SERVICE";
 
    /** 系统发送者的senderId. */
    int SYSTEM_SENDER_ID = -100;
    
    /** 系统发送者的senderName. */
    String SYSTEM_SENDER_NAME = "SYSTEM";
}
