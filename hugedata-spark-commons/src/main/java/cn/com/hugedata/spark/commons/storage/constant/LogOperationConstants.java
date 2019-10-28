package cn.com.hugedata.spark.commons.storage.constant;

public interface LogOperationConstants {

    //==================================================================================================================
    // 操作来源
    //==================================================================================================================
    
    /** 表示是在后台管理中进行的操作. */
    String OPERATION_TYPE_MANAGEMENT = "MANAGEMENT";

    
    //==================================================================================================================
    // 操作类型
    //==================================================================================================================
    
    /** 操作类型：后台管理. */
    public static final String OPERATION_TYPE_ENTERPRISE_MANAGER = "MANAGER";

    /** 操作类型：系统后台管理. */
    public static final String OPERATION_TYPE_ADMINISTRATION = "ADMINISTRATION";
    
    /** 操作类型：接口（客户端操作）. */
    public static final String OPERATION_TYPE_INTERFACE = "INTERFACE";

    
    //==================================================================================================================
    // 操作目标
    //==================================================================================================================

    /** 操作目标：无. */
    public static final String TARGET_NULL = "NULL";
    
    /** 操作目标: 园区用户. */
    public static final String TARGET_PARK_USERS = "PARK_USERS";

    /** 操作目标: 园区部门. */
    public static final String TARGET_PARK_DEPTS = "PARK_DEPTS";

    /** 操作目标: 角色. */
    public static final String TARGET_ROLES = "ROLES";
    
    /** 操作目标：用户帐号（企业账号）. */
    public static final String TARGET_COMPANY_USERS = "COMPANY_USERS";

    /** 操作目标：招商信息管理. */
    public static final String TARGET_GA_RENT_INFO = "GA_RENT_INFO";

    /** 操作目标：企业退租管理. */
    public static final String TARGET_GA_QUIT_RENT = "GA_QUIT_RENT";
    /** 操作目标：企业入园管理. */
    public static final String TARGET_APPLY_IN = "APPLY_IN";
    
    /** 操作目标：企业入园指南管理. */
    public static final String TARGET_APPLY_IN_GUIDE = "APPLY_IN_GUIDE";
    
    /** 操作目标：企业退园管理. */
    public static final String TARGET_APPLY_OUT = "APPLY_OUT";
    
    /** 操作目标：企业退园指南管理. */
    public static final String TARGET_APPLY_OUT_GUIDE = "APPLY_OUT_GUIDE";
    
    /** 操作目标：房租管理. */
    public static final String TARGET_GA_PM_BILL_RENT = "GA_PM_BILL_RENT";

    /** 操作目标：保证金管理. */
    public static final String TARGET_GA_PM_BILL_EARNEST = "GA_PM_BILL_EARNEST";

    /** 操作目标：物业水电管理. */
    public static final String TARGET_GA_PM_BILL_PWP = "GA_PM_BILL_PWP";

    /** 操作目标：退费管理. */
    public static final String TARGET_GA_PM_REFUND = "GA_PM_REFUND";

    /** 操作目标：物业收费标准管理. */
    public static final String TARGET_GA_PM_STANDARD = "GA_PM_STANDARD";

    /** 操作目标：融资申请管理. */
    public static final String TARGET_GA_FINACE = "GA_FINACE";

    /** 操作目标：统计报表模板管理. */
    public static final String TARGET_GA_REPORT_TEMPLATE = "GA_REPORT_TEMPLATE";

    /** 操作目标：统计报表管理. */
    public static final String TARGET_GA_REPORT_SUBMIT = "GA_REPORT_SUBMIT";

    /** 操作目标：场地证明管理. */
    public static final String TARGET_GA_SITE_PROOF= "GA_SITE_PROOF";

    /** 操作目标：资质认证管理. */
    public static final String TARGET_GA_APTITUDE= "GA_SITE_PROOF";

    /** 操作目标：企业信息管理. */
    public static final String TARGET_OS_COMPANYINFO= "OS_COMPANYINFO";

    /** 操作目标：意向企业管理. */
    public static final String TARGET_OS_INTENTIONCOMPANY= "OS_INTENTIONCOMPANY";

    /** 操作目标：企业服务信息管理. */
    public static final String TARGET_OS_COMPANYSERVICE= "OS_COMPANYSERVICE";

    /** 操作目标：企业合同管理. */
    public static final String TARGET_OS_COMPANYCONTRACT= "OS_COMPANYCONTRACT";

    /** 操作目标：企业项目管理. */
    public static final String TARGET_OS_COMPANYPROJECT= "OS_COMPANYPROJECT";

    /** 操作目标：企业反馈管理. */
    public static final String TARGET_OS_COMPANYFEEDBACK= "OS_COMPANYFEEDBACK";

    /** 操作目标：企业求才. */
    public static final String TARGET_OS_JOBOFFER= "OS_JOBOFFER";

    /** 操作目标：技能培训. */
    public static final String TARGET_OS_TRANING= "OS_TRANING";

    /** 操作目标：质量技术服务申请. */
    public static final String TARGET_QUAOLITY_SERVICE_APPLY= "QUAOLITY_SERVICE_APPLY";

    /** 操作目标：质量技术服务信息. */
    public static final String TARGET_QUAOLITY_SERVICE_INFO= "QUAOLITY_SERVICE_INFO";

    /** 操作目标：公司诚信管理. */
    public static final String TARGET_OS_COMPANY_CREDIT= "OS_COMPANY_CREDIT";

    /** 操作目标：个人诚信管理. */
    public static final String TARGET_OS_PERSONAL_CREDIT= "OS_PERSONAL_CREDIT";

    /** 操作目标：知识产权服务申请. */
    public static final String TARGET_I_P_SERVICE_APPLY= "I_P_SERVICE_APPLY";

    /** 操作目标：知识产权服务信息. */
    public static final String TARGET_I_P_SERVICE_INFO= "I_P_SERVICE_INFO";

    /** 操作目标：招标管理信息. */
    public static final String TARGET_EC_BIDDINGINFO= "EC_BIDDINGINFO";

    /** 操作目标：需求信息管理. */
    public static final String TARGET_EC_REQUIREMENTINFO= "EC_REQUIREMENTINFO";

    /** 操作目标：电子商务->招商信息管理 */
    public static final String TARGET_EC_INVESTMENT= "EC_INVESTMENT";

    /** 操作目标：小程序Banner管理. */
    public static final String TARGET_MP_BANNER= "MP_BANNER";

    /** 操作目标：小程序部门电话管理. */
    public static final String TARGET_MP_DEPT_CONTACTS= "MP_DEPT_CONTACTS";

    /** 操作目标：小程序园区服务简介管理. */
    public static final String TARGET_MP_SERVICE_INTRODUCE= "MP_SERVICE_INTRODUCE";
    
    /** 操作目标：行政审批在线办理. */
    public static final String TARGET_AP_SERVICE_ONLINE= "AP_SERVICE_ONLINE";
    
    /** 操作目标：项目管理. */
    public static final String TARGET_PM_PROJECT= "PM_PROJECT";
    
    /** 操作目标：行政审批窗口办理. */
    public static final String TARGET_AP_SERVICE_BYWINDOW= "AP_SERVICE_BYWINDOW";
    
    /** 操作目标：信息送报. */
    public static final String TARGET_MESSAGE_POST= "GA_MESSAGE_POST";



    //==================================================================================================================
    // 操作
    //==================================================================================================================

    /** 操作：新增. */
    public static final String OPERATION_ADD = "ADD";
    
    /** 操作：修改. */
    public static final String OPERATION_MODIFY = "MODIFY";
    
    /** 操作：删除. */
    public static final String OPERATION_DELETE = "DELETE";
    
    /** 操作：导入. */
    public static final String OPERATION_IMPORT = "IMPORT";
    
    /** 操作：接受. */
    public static final String OPERATION_ACCEPT = "ACCEPT";
    
    /** 操作：拒绝. */
    public static final String OPERATION_REJECT = "REJECT";
    
    /** 操作：确认. */
    public static final String OPERATION_CONFIRM = "CONFIRM";
    
    /** 操作：取消. */
    public static final String OPERATION_CANCEL = "CANCEL";
    
    /** 操作：合并. */
    public static final String OPERATION_MERGE = "MERGE";
    
    /** 操作：角色-配置权限. */
    public static final String OPERATION_ROLE_UPDATE_RIGHTS = "ROLE_UPDATE_RIGHTS";
    
    /** 操作：用户-配置角色. */
    public static final String OPERATION_USER_UPDATE_ROLES = "USER_UPDATE_ROLES";
}
