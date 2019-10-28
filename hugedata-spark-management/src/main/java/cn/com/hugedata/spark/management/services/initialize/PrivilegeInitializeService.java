package cn.com.hugedata.spark.management.services.initialize;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.storage.entity.UcManageRight;
import cn.com.hugedata.spark.commons.storage.mapper.UcManageRightMapper;

/**
 * 用于初始化权限信息.
 */
@Service
public class PrivilegeInitializeService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeInitializeService.class);

    /** 表示功能没有菜单图标. */
    private static final String NO_ICON = null;

    /** 表示功能在菜单中显示. */
    private static final boolean SHOW_IN_MENU = true;
    
    /** 表示功能不在菜单中显示. */
    private static final boolean HIDE_IN_MENU = false;
    
    @Autowired
    private UcManageRightMapper ucManageRightMapper;

    @Value("${system.alwaysReloadManagementRights}")
    private boolean alwaysReloadManagementRights;
    
    /** 用于自动生成排序(sort_index)字段. */
    private int sort;
    
    /**
     * 初始化权限信息.
     */
    @PostConstruct
    public void initRights() {
        if (alwaysReloadManagementRights) {
            deleteAllRights();
        }
        initFileUploadRight();
        initAccountsRights();
        initApprovalRights();
        initPmRights();
        initOutsourcingRights();
        initPublishInfo();
        initInteractiveRights();
        initIntegratedRights();
    }

    /**
     * 初始化"文件上传"的权限信息.
     */
    private void initFileUploadRight() {
        addFileUpload("file_upload", "", HIDE_IN_MENU, NO_ICON,"/cke/imageUpload");
    }

    /**
     * 新增"账号管理"权限信息.
     */
    private void initAccountsRights() {
        //用户帐号管理
        addAccounts("company_account", "企业帐号管理", SHOW_IN_MENU, NO_ICON, 
                "/features/UcCompanyUser/list",
                "/features/UcCompanyUser/listData",
                "/features/UcCompanyUser/toModify");
        addAccounts("company_account/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/UcCompanyUser/delete");
        addAccounts("company_account/setEnable", "启用/停用", HIDE_IN_MENU, NO_ICON,
                "/features/UcCompanyUser/setEnable");
        addAccounts("company_account/sendApproveMail", "发送开通邮件", HIDE_IN_MENU, NO_ICON,
                "/features/UcCompanyUser/sendApproveMail");

        //用户帐号管理
        addAccounts("park_account", "内部帐号管理", SHOW_IN_MENU, NO_ICON, 
                "/features/UcUserInfo/list",
                "/features/UcUserInfo/listData");
        addAccounts("park_account/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/UcUserInfo/toAdd", 
                "/features/UcUserInfo/doAdd");
        addAccounts("park_account/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/UcUserInfo/toModify", 
                "/features/UcUserInfo/doModify");
        addAccounts("park_account/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/UcUserInfo/delete");
        addAccounts("park_account/setEnable", "启用/停用", HIDE_IN_MENU, NO_ICON,
                "/features/UcUserInfo/setEnable");
    }
    
    /**
     * 新增"行政审批"权限信息.
     */
    private void initApprovalRights() {
        //在线办理
    	addApproval("ol_service", "在线办理", SHOW_IN_MENU, NO_ICON, 
                "/features/ApprovalonLine/list",
                "/features/ApprovalonLine/listData");
    	addApproval("ol_service/modify", "办理", HIDE_IN_MENU, NO_ICON,
                "/features/ApprovalonLine/toModify",
                "/features/ApprovalonLine/deliver",
                "/features/ApprovalonLine/approve",
                "/features/ApprovalonLine/reject",
                "/features/ApprovalonLine/finish",
                "/features/ApprovalonLine/queryDeptUsers");
    	
    	//窗口办理
    	addApproval("wd_service", "窗口办理", SHOW_IN_MENU, NO_ICON,
                "/features/ApprovalBywindow/list",
                "/features/ApprovalBywindow/listData",
                "/features/ApprovalBywindow/printAcceptNotice");
    	addApproval("wd_service/add", "新增", HIDE_IN_MENU, NO_ICON,
    			"/features/ApprovalBywindow/prepareCreate",
    			"/features/ApprovalBywindow/queryGuideDetails",
                "/features/ApprovalBywindow/doAdd",
                "/features/ApprovalBywindow/showUploadAtt",
                "/features/ApprovalBywindow/doAttUpload");
    	addApproval("wd_service/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/ApprovalBywindow/toModify",
                "/features/ApprovalBywindow/deliver",
                "/features/ApprovalBywindow/approve",
                "/features/ApprovalBywindow/reject",
                "/features/ApprovalBywindow/finish",
                "/features/ApprovalBywindow/queryDeptUsers");

        //工作统计
        addApproval("approval_stat", "工作统计", SHOW_IN_MENU, NO_ICON,
                "/features/ApprovalStat/index",
                "/features/ApprovalStat/queryStatData",
                "/features/ApprovalStat/exportDetails");
    }
    
    /**
     * 新增"项目管理"权限信息.
     */
    private void initPmRights() {
        addPm("pm_project", "项目管理", SHOW_IN_MENU, NO_ICON, 
                "/features/PmProject/list",
                "/features/PmProject/exportExcel",
                "/features/PmProject/listProject");
        addPm("pm_project/add", "新增项目", HIDE_IN_MENU, NO_ICON,
                "/features/PmProject/toAdd",
                "/features/PmProject/doAdd",
                "/features/PmProject/deleteProject",
                "/features/PmProject/getCompanies",
                "/features/PmProject/getInfUsers");
        addPm("pm_project/detail", "项目信息", HIDE_IN_MENU, NO_ICON, 
                "/features/PmProject/detail",
                "/features/PmProject/detailData");
        addPm("pm_project/modify", "项目信息", HIDE_IN_MENU, NO_ICON, 
                "/features/PmProject/toModify",
                "/features/PmProject/modifyProject");
        addPm("pm_project/doc1", "手续文件", HIDE_IN_MENU, NO_ICON, 
                "/features/PmProject/yearPlan",
                "/features/PmProject/listPlan",
                "/features/PmProject/deletePlan",
                "/features/PmProject/approvePlan",
                "/features/PmProject/doc1",
                "/features/PmProject/doc2",
                "/features/PmProject/listDoc",
                "/features/PmProject/chgDocStatus",
                "/features/PmProject/deleteDoc");
        
        addPm("pm_project/monthReport", "手续文件", HIDE_IN_MENU, NO_ICON, 
                "/features/PmProject/monthReport",
                "/features/PmProject/addMonthInvest",
                "/features/PmProject/submitReport",
                "/features/PmProject/deleteInvest",
                "/features/PmProject/listMonthInvest");
        
        addPm("pm_project/docType", "配置文件类型", HIDE_IN_MENU, NO_ICON,
                "/features/PmDocType/list",
                "/features/PmDocType/listData",
                "/features/PmDocType/toAdd",
                "/features/PmDocType/doAdd",
                "/features/PmDocType/delete",
                "/features/PmDocType/toModify",
                "/features/PmDocType/doModify");
    }
    /**
     * 新增"外包服务"权限信息.
     */
    private void initOutsourcingRights() {
//        // 检验检测
//        addOutsourcing("outdetec", "检验检测", SHOW_IN_MENU, NO_ICON);
//
//        // 检验检测-申请审批
//        addOutsourcing("outdetec/approve", "申请审批", SHOW_IN_MENU, NO_ICON,
//                "/features/OsDetecApprove/list",
//                "/features/OsDetecApprove/listData");
//        addOutsourcing("outdetec/approve/approve", "审批", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecApprove/submitApprove");
//
//
//        // 检验检测-成果展示
//        addOutsourcing("outdetec/achieve_exhibit", "成果展示", SHOW_IN_MENU, NO_ICON,
//                "/features/OsDetecAchieveExhibit/list",
//                "/features/OsDetecAchieveExhibit/listData");
//        addOutsourcing("outdetec/achieve_exhibit/preview", "预览", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecAchieveExhibit/preview");
//        addOutsourcing("outdetec/achieve_exhibit/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecAchieveExhibit/toAdd",
//                "/features/OsDetecAchieveExhibit/doAdd");
//        addOutsourcing("outdetec/achieve_exhibit/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecAchieveExhibit/toModify",
//                "/features/OsDetecAchieveExhibit/doModify");
//        addOutsourcing("outdetec/achieve_exhibit/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecAchieveExhibit/delete");
//        addOutsourcing("outdetec/achieve_exhibit/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecAchieveExhibit/setArticleVisible");
//
//
//        // 检验检测-资源平台
//        addOutsourcing("outdetec/resource_platform", "资源平台", SHOW_IN_MENU, NO_ICON,
//                "/features/OsDetecResourcePlatform/list",
//                "/features/OsDetecResourcePlatform/listData");
//        addOutsourcing("outdetec/resource_platform/preview", "预览", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecResourcePlatform/preview");
//        addOutsourcing("outdetec/resource_platform/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecResourcePlatform/toAdd",
//                "/features/OsDetecResourcePlatform/doAdd");
//        addOutsourcing("outdetec/resource_platform/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecResourcePlatform/toModify",
//                "/features/OsDetecResourcePlatform/doModify");
//        addOutsourcing("outdetec/resource_platform/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecResourcePlatform/delete");
//        addOutsourcing("outdetec/resource_platform/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecResourcePlatform/setArticleVisible");
//
//        // 检验检测- 政策法规
//        addOutsourcing("outdetec/tech_transfer", "政策法规", SHOW_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTransfer/list",
//                "/features/OsDetecTechTransfer/listData");
//        addOutsourcing("outdetec/tech_transfer/preview", "预览", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTransfer/preview");
//        addOutsourcing("outdetec/tech_transfer/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTransfer/toAdd",
//                "/features/OsDetecTechTransfer/doAdd");
//        addOutsourcing("outdetec/tech_transfer/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTransfer/toModify",
//                "/features/OsDetecTechTransfer/doModify");
//        addOutsourcing("outdetec/tech_transfer/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTransfer/delete");
//        addOutsourcing("outdetec/tech_transfer/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTransfer/setArticleVisible");
//
//        // 检验检测-技术培训
//        addOutsourcing("outdetec/tech_train", "技术培训", SHOW_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTrain/list",
//                "/features/OsDetecTechTrain/listData");
//        addOutsourcing("outdetec/tech_train/preview", "预览", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTrain/preview");
//        addOutsourcing("outdetec/tech_train/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTrain/toAdd",
//                "/features/OsDetecTechTrain/doAdd");
//        addOutsourcing("outdetec/tech_train/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTrain/toModify",
//                "/features/OsDetecTechTrain/doModify");
//        addOutsourcing("outdetec/tech_train/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTrain/delete");
//        addOutsourcing("outdetec/tech_train/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/OsDetecTechTrain/setArticleVisible");
//
//        // 技术服务
//        addOutsourcing("outtech", "技术服务", SHOW_IN_MENU, NO_ICON);
//
//        // 技术服务 / 人力资源
//        addOutsourcing("outtech/hr", "人力资源", SHOW_IN_MENU, NO_ICON,
//                "/features/OsHumanResource/list",
//                "/features/OsHumanResource/listData");
//        addOutsourcing("outtech/hr/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsHumanResource/toAdd",
//                "/features/OsHumanResource/doAdd");
//        addOutsourcing("outtech/hr/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsHumanResource/toModify",
//                "/features/OsHumanResource/doModify");
//        addOutsourcing("outtech/hr/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsHumanResource/delete");
//        addOutsourcing("outtech/hr/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/OsHumanResource/setArticleVisible");
//
//        // 技术服务 / 企业信息服务
//        addOutsourcing("outtech/information", "企业信息服务", SHOW_IN_MENU, NO_ICON,
//                "/features/OsInformation/list",
//                "/features/OsInformation/listData");
//        addOutsourcing("outtech/information/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsInformation/toAdd",
//                "/features/OsInformation/doAdd");
//        addOutsourcing("outtech/information/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsInformation/toModify",
//                "/features/OsInformation/doModify");
//        addOutsourcing("outtech/information/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsInformation/delete");
//        addOutsourcing("outtech/information/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/OsInformation/setArticleVisible");
//
//        // 技术服务 / 项目招商服务
//        addOutsourcing("outtech/invest", "项目招商服务", SHOW_IN_MENU, NO_ICON,
//                "/features/OsInvest/list",
//                "/features/OsInvest/listData");
//        addOutsourcing("outtech/invest/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsInvest/toAdd",
//                "/features/OsInvest/doAdd");
//        addOutsourcing("outtech/invest/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsInvest/toModify",
//                "/features/OsInvest/doModify");
//        addOutsourcing("outtech/invest/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsInvest/delete");
//        addOutsourcing("outtech/invest/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/OsInvest/setArticleVisible");
//
//        // 技术服务 / 图片管理
//        addOutsourcing("outtech/tech_image", "图片管理", SHOW_IN_MENU, NO_ICON,
//                "/features/OsTechImage/list",
//                "/features/OsTechImage/listData");
//        addOutsourcing("outtech/tech_image/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsTechImage/toAdd",
//                "/features/OsTechImage/doAdd");
//        addOutsourcing("outtech/tech_image/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsTechImage/toModify",
//                "/features/OsTechImage/doModify");
//        addOutsourcing("outtech/tech_image/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsTechImage/delete");
//        addOutsourcing("outtech/tech_image/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/OsTechImage/setTechImageVisible");
//        addOutsourcing("outtech/tech_image/setOrder", "保存排序", HIDE_IN_MENU, NO_ICON,
//                "/features/OsTechImage/setOrder");
//
//        // 技术服务 / 链接管理
//        addOutsourcing("outtech/link_url_man", "链接管理", SHOW_IN_MENU, NO_ICON,
//                "/features/OsLinkUrlMan/list",
//                "/features/OsLinkUrlMan/listData");
//        addOutsourcing("outtech/link_url_man/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/OsLinkUrlMan/toAdd",
//                "/features/OsLinkUrlMan/doAdd");
//        addOutsourcing("outtech/link_url_man/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/OsLinkUrlMan/toModify",
//                "/features/OsLinkUrlMan/doModify");
//        addOutsourcing("outtech/link_url_man/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/OsLinkUrlMan/delete");
//
////        // 技术服务 / 知识产权服务
////        addOutsourcing("outtech/intellectual", "知识产权服务", SHOW_IN_MENU, NO_ICON,
////                "/features/OsIntellectual/list",
////                "/features/OsIntellectual/listData");
////        addOutsourcing("outtech/intellectual/add", "新增", HIDE_IN_MENU, NO_ICON,
////                "/features/OsIntellectual/toAdd",
////                "/features/OsIntellectual/doAdd");
////        addOutsourcing("outtech/intellectual/modify", "修改", HIDE_IN_MENU, NO_ICON,
////                "/features/OsIntellectual/toModify",
////                "/features/OsIntellectual/doModify");
////        addOutsourcing("outtech/intellectual/delete", "删除", HIDE_IN_MENU, NO_ICON,
////                "/features/OsIntellectual/delete");
////        addOutsourcing("outtech/intellectual/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
////                "/features/OsIntellectual/setArticleVisible");
//
//
//        // 电子商务
//        addOutsourcing("ecomm", "电子商务", SHOW_IN_MENU, NO_ICON);
//
//        // 电子商务 / 招标公告
//        addOutsourcing("ecomm/biddings", "招标公告", SHOW_IN_MENU, NO_ICON,
//                "/features/EcBiddings/list",
//                "/features/EcBiddings/listData");
//        addOutsourcing("ecomm/biddings/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/EcBiddings/toAdd",
//                "/features/EcBiddings/doAdd");
//        addOutsourcing("ecomm/biddings/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/EcBiddings/toModify",
//                "/features/EcBiddings/doModify");
//        addOutsourcing("ecomm/biddings/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/EcBiddings/delete");
//        addOutsourcing("ecomm/biddings/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/EcBiddings/setArticleVisible");
//
//        // 电子商务 / 项目对接
//        addOutsourcing("ecomm/projects", "项目对接", SHOW_IN_MENU, NO_ICON,
//                "/features/EcProjects/list",
//                "/features/EcProjects/listData");
//        addOutsourcing("ecomm/projects/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/EcProjects/toAdd",
//                "/features/EcProjects/doAdd");
//        addOutsourcing("ecomm/projects/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/EcProjects/toModify",
//                "/features/EcProjects/doModify");
//        addOutsourcing("ecomm/projects/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/EcProjects/delete");
//        addOutsourcing("ecomm/projects/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/EcProjects/setArticleVisible");
//
//        // 电子商务 / 服务外包
//        addOutsourcing("ecomm/outsourcing", "服务外包", SHOW_IN_MENU, NO_ICON,
//                "/features/EcOutsourcing/list",
//                "/features/EcOutsourcing/listData");
//        addOutsourcing("ecomm/outsourcing/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/EcOutsourcing/toAdd",
//                "/features/EcOutsourcing/doAdd");
//        addOutsourcing("ecomm/outsourcing/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/EcOutsourcing/toModify",
//                "/features/EcOutsourcing/doModify");
//        addOutsourcing("ecomm/outsourcing/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/EcOutsourcing/delete");
//        addOutsourcing("ecomm/outsourcing/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/EcOutsourcing/setArticleVisible");
//
//        // 电子商务 / 金融服务
//        addOutsourcing("ecomm/financing", "金融服务", SHOW_IN_MENU, NO_ICON,
//                "/features/EcFinancing/list",
//                "/features/EcFinancing/listData");
//        addOutsourcing("ecomm/financing/add", "新增", HIDE_IN_MENU, NO_ICON,
//                "/features/EcFinancing/toAdd",
//                "/features/EcFinancing/doAdd");
//        addOutsourcing("ecomm/financing/modify", "修改", HIDE_IN_MENU, NO_ICON,
//                "/features/EcFinancing/toModify",
//                "/features/EcFinancing/doModify");
//        addOutsourcing("ecomm/financing/delete", "删除", HIDE_IN_MENU, NO_ICON,
//                "/features/EcFinancing/delete");
//        addOutsourcing("ecomm/financing/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
//                "/features/EcFinancing/setArticleVisible");


        // 20180123 王宁提出的新需求，服务外包下面改为下面两个板块：转化成果和会议培训
        // 服务外包一级菜单：转化成果
        addOutsourcing("achieve_transfer", "转化成果", SHOW_IN_MENU, NO_ICON);
        // 服务外包二级菜单：专家名录
        addOutsourcing("achieve_transfer/experts_list", "专家名录", SHOW_IN_MENU, NO_ICON,
                "/features/OsExpertsList/list",
                "/features/OsExpertsList/listData");
        addOutsourcing("achieve_transfer/experts_list/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/OsExpertsList/toAdd",
                "/features/OsExpertsList/doAdd");
        addOutsourcing("achieve_transfer/experts_list/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/OsExpertsList/toModify",
                "/features/OsExpertsList/doModify");
        addOutsourcing("achieve_transfer/experts_list/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/OsExpertsList/delete");
        addOutsourcing("achieve_transfer/experts_list/sort", "排序", HIDE_IN_MENU, NO_ICON,
                "/features/OsExpertsList/setOrder");
        addOutsourcing("achieve_transfer/experts_list/setvisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/OsExpertsList/setExpertVisible");

        // 服务外包二级菜单：技术成果
        addOutsourcing("achieve_transfer/tech_achieve", "技术成果", SHOW_IN_MENU, NO_ICON,
                "/features/OsTechAchieve/list",
                "/features/OsTechAchieve/listData");
        addOutsourcing("achieve_transfer/tech_achieve/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechAchieve/toAdd",
                "/features/OsTechAchieve/doAdd");
        addOutsourcing("achieve_transfer/tech_achieve/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechAchieve/toModify",
                "/features/OsTechAchieve/doModify");
        addOutsourcing("achieve_transfer/tech_achieve/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechAchieve/delete");
        addOutsourcing("achieve_transfer/tech_achieve/sort", "排序", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechAchieve/setOrder");
        addOutsourcing("achieve_transfer/tech_achieve/setvisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechAchieve/setAchieveVisible");

        // 服务外包一级菜单：会议培训
        addOutsourcing("meeting_training", "会议培训", SHOW_IN_MENU, NO_ICON,
                "/features/OsMeetingTraining/list",
                "/features/OsMeetingTraining/listData");
        addOutsourcing("meeting_training/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/OsMeetingTraining/toAdd",
                "/features/OsMeetingTraining/doAdd");
        addOutsourcing("meeting_training/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/OsMeetingTraining/toModify",
                "/features/OsMeetingTraining/doModify");
        addOutsourcing("meeting_training/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/OsMeetingTraining/delete");
        addOutsourcing("meeting_training/sort", "排序", HIDE_IN_MENU, NO_ICON,
                "/features/OsMeetingTraining/setOrder");
        addOutsourcing("meeting_training/setvisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/OsMeetingTraining/setTrainingVisible");
    }
    
    /**
     * 新增"信息发布"权限信息.
     */
    private void initPublishInfo() {
        // 首页图片
        addPublishInfo("home_image", "首页图片", SHOW_IN_MENU, NO_ICON,
                "/features/PtHomeImage/list",
                "/features/PtHomeImage/listData");
        addPublishInfo("home_image/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/PtHomeImage/toAdd",
                "/features/PtHomeImage/doAdd");
        addPublishInfo("home_image/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/PtHomeImage/toModify",
                "/features/PtHomeImage/doModify");
        addPublishInfo("home_image/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/PtHomeImage/delete");
        addPublishInfo("home_image/sort", "排序", HIDE_IN_MENU, NO_ICON,
                "/features/PtHomeImage/setOrder");
        addPublishInfo("home_image/setvisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/PtHomeImage/setImageVisible");
        
        // 办事指南
        addPublishInfo("service_guide", "办事指南", SHOW_IN_MENU, NO_ICON,
                "/features/GaServiceGuide/list",
                "/features/GaServiceGuide/listData");
        addPublishInfo("service_guide/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/GaServiceGuide/toAdd",
                "/features/GaServiceGuide/doAdd");
        addPublishInfo("service_guide/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/GaServiceGuide/toModify",
                "/features/GaServiceGuide/doModify");
        addPublishInfo("service_guide/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/GaServiceGuide/delete");

        // 通知公告
        addPublishInfo("notice", "通知公告", SHOW_IN_MENU, NO_ICON,
                "/features/PiNotice/list",
                "/features/PiNotice/listData");
        addPublishInfo("notice/preview", "预览", HIDE_IN_MENU, NO_ICON,
                "/features/PiNotice/preview");
        addPublishInfo("notice/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/PiNotice/toAdd",
                "/features/PiNotice/doAdd");
        addPublishInfo("notice/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/PiNotice/toModify",
                "/features/PiNotice/doModify");
        addPublishInfo("notice/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/PiNotice/delete");
        addPublishInfo("notice/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/PiNotice/setArticleVisible");
        
        // 专题栏目
        addPublishInfo("notice_banner", "专题栏目", SHOW_IN_MENU, NO_ICON,
                "/features/PtNoticeBanner/list",
                "/features/PtNoticeBanner/listData");
        addPublishInfo("notice_banner/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/PtNoticeBanner/toAdd",
                "/features/PtNoticeBanner/doAdd");
        addPublishInfo("notice_banner/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/PtNoticeBanner/toModify",
                "/features/PtNoticeBanner/doModify");
        addPublishInfo("notice_banner/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/PtNoticeBanner/delete");
        addPublishInfo("notice_banner/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/PtNoticeBanner/setImageVisible");
        addPublishInfo("notice_banner/setOrder", "排序", HIDE_IN_MENU, NO_ICON,
                "/features/PtNoticeBanner/setOrder");

        // 便民服务
        addPublishInfo("handy_service", "便民服务", SHOW_IN_MENU, NO_ICON,
                "/features/PiHandyService/list",
                "/features/PiHandyService/listData");
        addPublishInfo("handy_service/preview", "预览", HIDE_IN_MENU, NO_ICON,
                "/features/PiHandyService/preview");
        addPublishInfo("handy_service/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/PiHandyService/toAdd",
                "/features/PiHandyService/doAdd");
        addPublishInfo("handy_service/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/PiHandyService/toModify",
                "/features/PiHandyService/doModify");
        addPublishInfo("handy_service/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/PiHandyService/delete");
        addPublishInfo("handy_service/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/PiHandyService/setArticleVisible");
        
        addPublishInfo("app_manage_message", "信息送报", SHOW_IN_MENU, NO_ICON,
                "/features/AppManage/message",
                "/features/AppManage/toModify",
                "/features/AppManage/doModify");

    }
    
    /**
     * 新增"行政审批"权限信息.
     */
    private void initInteractiveRights() {
        //领导信箱
    	addInteractive("it_service", "领导信箱", SHOW_IN_MENU, NO_ICON, 
                "/features/Interactive/list",
                "/features/Interactive/listData");
    	addInteractive("it_service/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/Interactive/toReply",
                "/features/Interactive/doModify");
    	addInteractive("it_service/delete", "假删除", HIDE_IN_MENU, NO_ICON,
                "/features/Interactive/fakeDel");
    	
    	  //调查征集

        addInteractive("interactive", "调查征集", SHOW_IN_MENU, NO_ICON,
                "/features/Survey/list",
                "/features/Survey/listData");
        addInteractive("interactive/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/Survey/toAdd",
                "/features/Survey/doAdd");
        addInteractive("interactive/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/Survey/toUpdate",
                "/features/Survey/doModify");
        addInteractive("interactive/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/Survey/delete");
        addInteractive("interactive/setPublished", "发布/取消发布", HIDE_IN_MENU, NO_ICON,
                "/features/Survey/setPublished",
                "/features/Survey/setPublished");
        addInteractive("interactive/config", "配置", HIDE_IN_MENU, NO_ICON,
                "/features/SurveyQuestion/list",
                "/features/SurveyQuestion/listData",
                "/features/SurveyQuestion/delete",
                "/features/SurveyQuestion/setOrder",
                "/features/SurveyQuestion/saveQuestion",
                "/features/SurveyQuestion/queryQuestion",
                "/features/SurveyQuestion/updateQuestion");
        addInteractive("interactive/summary", "查看结果", HIDE_IN_MENU, NO_ICON,
                "/features/Survey/summary",
                "/features/Survey/summaryData",
                "/features/Survey/resultData");
        
      //信息送报
        addInteractive("message_post", "信息送报", SHOW_IN_MENU, NO_ICON, 
                "/features/GaMessagePost/list",
                "/features/GaMessagePost/listData");
        addInteractive("message_post/detail", "明细", HIDE_IN_MENU, NO_ICON, 
                "/features/GaMessagePost/detail",
                "/features/GaMessagePost/detailData");
    	
    }
    
    
    /**
     * 新增"综合服务"权限信息.
     */
    private void initIntegratedRights() {
        // 综合政务 / 入园申请
        addIntegrated("joinapply", "入园申请", SHOW_IN_MENU, NO_ICON, 
                "/features/GaParkJoinApplication/list",
                "/features/GaParkJoinApplication/listData",
                "/features/GaParkJoinApplication/toModify");
        addIntegrated("joinapply/approve", "审批", HIDE_IN_MENU, NO_ICON,
                "/features/GaParkJoinApplication/submitApprove");
        
        // 综合政务 / 入园指南
        addIntegrated("joinguide", "入园指南", SHOW_IN_MENU, NO_ICON, 
                "/features/GaParkJoinGuide/list",
                "/features/GaParkJoinGuide/listData");
        addIntegrated("joinguide/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/GaParkJoinGuide/toAdd", 
                "/features/GaParkJoinGuide/doAdd");
        addIntegrated("joinguide/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/GaParkJoinGuide/toModify", 
                "/features/GaParkJoinGuide/doModify");
        addIntegrated("joinguide/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/GaParkJoinGuide/delete");
        
        // 综合政务 / 退园申请
        addIntegrated("quitapply", "退园申请", SHOW_IN_MENU, NO_ICON, 
                "/features/GaParkQuitApplication/list",
                "/features/GaParkQuitApplication/listData",
                "/features/GaParkQuitApplication/toModify");
        addIntegrated("quitapply/approve", "审批", HIDE_IN_MENU, NO_ICON,
                "/features/GaParkQuitApplication/submitApprove");
        
        // 综合政务 / 退园指南
        addIntegrated("quitguide", "退园指南", SHOW_IN_MENU, NO_ICON, 
                "/features/GaParkQuitGuide/list",
                "/features/GaParkQuitGuide/listData");
        addIntegrated("quitguide/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/GaParkQuitGuide/toAdd", 
                "/features/GaParkQuitGuide/doAdd");
        addIntegrated("quitguide/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/GaParkQuitGuide/toModify", 
                "/features/GaParkQuitGuide/doModify");
        addIntegrated("quitguide/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/GaParkQuitGuide/delete");
        
        
        // 综合政务 / 场地服务
        addIntegrated("site", "场地服务", SHOW_IN_MENU, NO_ICON, 
                "/features/GaSiteService/list",
                "/features/GaSiteService/listData");
        addIntegrated("site/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/GaSiteService/toAdd", 
                "/features/GaSiteService/doAdd");
        addIntegrated("site/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/GaSiteService/toModify", 
                "/features/GaSiteService/doModify");
        addIntegrated("site/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/GaSiteService/delete");
        addIntegrated("site/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/GaSiteService/setArticleVisible");
        
        // 综合政务 / 费用服务
        addIntegrated("fees", "费用服务", SHOW_IN_MENU, NO_ICON, 
                "/features/GaFeeService/list",
                "/features/GaFeeService/listData");
        addIntegrated("fees/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/GaFeeService/toAdd", 
                "/features/GaFeeService/doAdd");
        addIntegrated("fees/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/GaFeeService/toModify", 
                "/features/GaFeeService/doModify");
        addIntegrated("fees/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/GaFeeService/delete");
        addIntegrated("fees/setVisible", "设置可见", HIDE_IN_MENU, NO_ICON,
                "/features/GaFeeService/setArticleVisible");
        
        // IT基础设施
        addIntegrated("techserv", "IT基础设施", SHOW_IN_MENU, NO_ICON);

        // IT基础设施 / 云主机套餐
        addIntegrated("techserv/service_apply", "申请记录", SHOW_IN_MENU, NO_ICON, 
                "/features/OsTechServiceApply/list",
                "/features/OsTechServiceApply/listData");
        addIntegrated("techserv/service_apply/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechServiceApply/delete");
        addIntegrated("techserv/service_apply/approve", "审批", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechServiceApply/approve");
        
        // IT基础设施 / 云主机套餐
        addIntegrated("techserv/computing_specs", "云主机套餐", SHOW_IN_MENU, NO_ICON, 
                "/features/OsTechServiceSpec/list",
                "/features/OsTechServiceSpec/listData");
        addIntegrated("techserv/computing_specs/add", "新增", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechServiceSpec/toAdd", 
                "/features/OsTechServiceSpec/doAdd");
        addIntegrated("techserv/computing_specs/modify", "修改", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechServiceSpec/toModify", 
                "/features/OsTechServiceSpec/doModify");
        addIntegrated("techserv/computing_specs/delete", "删除", HIDE_IN_MENU, NO_ICON,
                "/features/OsTechServiceSpec/delete");

        // IT基础设施 / 云存储
        addIntegrated("techserv/storage_config", "云存储", SHOW_IN_MENU, NO_ICON, 
                "/features/OsTechStorageConfig/index",
                "/features/OsTechStorageConfig/update");
    }
    
    /**
     * 新增一个"互动交流"的功能.
     */
    private void addInteractive(String rightId, String name, boolean isDisplay,
            String iconName, String... url) {
        insertRight(UcManageRight.SYSTEM_TYPE_IT, rightId, name, isDisplay, iconName, url);
    }

    /**
     * 新增一个"行政审批"的功能.
     */
    private void addApproval(String rightId, String name, boolean isDisplay,
            String iconName, String... url) {
        insertRight(UcManageRight.SYSTEM_TYPE_APPROVAL, rightId, name, isDisplay, iconName, url);
    }

    /**
     * 新增一个"项目管理"的功能.
     */
    private void addPm(String rightId, String name, boolean isDisplay,
            String iconName, String... url) {
        insertRight(UcManageRight.SYSTEM_TYPE_PM, rightId, name, isDisplay, iconName, url);
    }
    
    /**
     * 新增一个"信息发布"的功能.
     */
    private void addPublishInfo(String rightId, String name, boolean isDisplay,
                                 String iconName, String... url) {
        insertRight(UcManageRight.SYSTEM_TYPE_INFORMATION, rightId, name, isDisplay, iconName, url);
    }
    
    /**
     * 新增一个"服务外包"的功能.
     */
    private void addOutsourcing(String rightId, String name, boolean isDisplay,
            String iconName, String... url) {
        insertRight(UcManageRight.SYSTEM_TYPE_OUTSOURCING, rightId, name, isDisplay, iconName, url);
    }

    /**
     * 新增一个"账号管理"的功能.
     */
    private void addAccounts(String rightId, String name, boolean isDisplay,
            String iconName, String... url) {
        insertRight(UcManageRight.SYSTEM_TYPE_ACCOUNTS, rightId, name, isDisplay, iconName, url);
    }

    /**
     * 新增一个"上传文件"的功能.
     */
    private void addFileUpload(String rightId, String name, boolean isDisplay,
                               String iconName, String... url) {
        insertRight(UcManageRight.SYSTEM_TYPE_BASIC, rightId, name, isDisplay, iconName, url);
    }

    /**
     * 新增一个"综合服务"的功能.
     */
    private void addIntegrated(String rightId, String name, boolean isDisplay,
                               String iconName, String... url) {
        insertRight(UcManageRight.SYSTEM_TYPE_INTEGRATED, rightId, name, isDisplay, iconName, url);
    }

    /**
     * 如果权限不存在，插入权限信息.
     * @param systemType 系统类型(所属系统)
     * @param rightId    权限ID
     * @param name       权限名称
     * @param isDisplay  是否在菜单显示
     * @param iconName   图标名
     * @param urlArray   多个权限URL
     */
    private void insertRight(String systemType, String rightId, String name, boolean isDisplay,
            String iconName, String[] urlArray) {
        // 获取parentId
        String[] splitId = rightId.split("\\/");
        String parentId;
        if (splitId.length <= 1) {
            parentId = null;
        } else {
            parentId = rightId.substring(0, rightId.lastIndexOf('/'));
        }
        
        // 插入或更新
        UcManageRight right = null;
        boolean doInsert = alwaysReloadManagementRights;
        if (!doInsert) {
            right = ucManageRightMapper.selectById(rightId);
            doInsert = (right == null); 
        }
        
        if (doInsert) {
            LOGGER.info("Inserting management right: {}", rightId);
            right = new UcManageRight();
            right.setRightId(rightId);
            right.setParentId(parentId);
            right.setName(name);
            right.setSystemType(systemType);
            right.setUrl(StringUtils.join(urlArray, ","));
            right.setDisplay(isDisplay);
            right.setSortIndex(sort++);
            right.setIconName(iconName);
            ucManageRightMapper.insertSelective(right);   
        } else {
            LOGGER.info("Management right exists: {}", rightId);
            UcManageRight update = new UcManageRight();
            update.setRightId(right.getRightId());
            update.setSortIndex(sort++);
            ucManageRightMapper.updateSelectiveById(update);
        }
    }
    
    /**
     * 删除所有权限.
     */
    private void deleteAllRights() {
        List<UcManageRight> rights = ucManageRightMapper.selectByMap(null);
        List<String> rightIdList = new ArrayList<>();
        for (UcManageRight right : rights) {
            rightIdList.add(right.getRightId());
            //ucManageRightMapper.deleteById(right.getRightId());
        }
        if (rightIdList.size() > 0) {
            ucManageRightMapper.deleteByIds(rightIdList);
        }
    }
    
}
