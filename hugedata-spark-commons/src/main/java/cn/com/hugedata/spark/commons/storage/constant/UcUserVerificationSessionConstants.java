package cn.com.hugedata.spark.commons.storage.constant;

public interface UcUserVerificationSessionConstants {

    /** 类型：根据手机验证码注册. */
    String TYPE_M_REGISTER = "M_REGISTER";
    
    /** 类型：根据手机验证码找回密码. */
    String TYPE_M_FINDPWD = "M_FINDPWD";
    
    /** 类型：根据邮箱码找回密码. */
    String TYPE_E_FINDPWD = "E_FINDPWD";
    
    
    /** 状态:创建. */
    String STATUS_CREATED = "CREATED";

    /** 状态:已验证. */
    String STATUS_VERIFIED = "VERIFIED";

    /** 状态:完成. */
    String STATUS_FINISHED = "FINISHED";

    /** 状态:超时. */
    String STATUS_TIMEOUT = "TIMEOUT";
}
