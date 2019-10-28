package cn.com.hugedata.spark.ums.job;

import cn.com.hugedata.spark.commons.storage.entity.UcDeptInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcOrgInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.insertmapper.UcDeptInfoInsertMapper;
import cn.com.hugedata.spark.commons.storage.insertmapper.UcOrgInfoInsertMapper;
import cn.com.hugedata.spark.commons.storage.insertmapper.UcUserInfoInsertMapper;
//import cn.com.hugedata.weaverconnect.entity.WeaverDept;
//import cn.com.hugedata.weaverconnect.entity.WeaverOrg;
//import cn.com.hugedata.weaverconnect.entity.WeaverUser;
//import cn.com.hugedata.weaverconnect.service.WeaverDeptService;
//import cn.com.hugedata.weaverconnect.service.WeaverOrgService;
//import cn.com.hugedata.weaverconnect.service.WeaverUserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LvJianXing on 2017/1/17.
 * 同步泛微数据任务
 */
public class SynchronizeWeaverDataJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeWeaverDataJob.class);

    /** Spring Application Context. */
    private ApplicationContext applicationContext = null;

//    private WeaverDeptService weaverDeptService;
//    private WeaverOrgService weaverOrgService;
//    private WeaverUserService weaverUserService;

    private UcDeptInfoInsertMapper ucDeptInfoInsertMapper;
    private UcOrgInfoInsertMapper ucOrgInfoInsertMapper;
    private UcUserInfoInsertMapper ucUserInfoInsertMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("/*----SynchronizeWeaverDataJob Starting.----*/");

//        //初始化Mapper和Service
//        initMapperAndService();
//
//        //TODO 初步是同步所有记录，后期再进行优化。20170118 1517
//        //同步部门
//        synchronizeDept();
//        //同步组织或公司
//        synchronizeOrg();
//        //同步用户信息
//        synchronizeUser();

        logger.info("/*----SynchronizeWeaverDataJob End.----*/");
    }

    /**
     * 获取ApplicationContext.
     * @return
     */
    private ApplicationContext findApplicationContext() {
        if (applicationContext == null) {
            ServletContext servletContext = ContextLoaderListener.getCurrentWebApplicationContext().getServletContext();
            applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        }
        return applicationContext;
    }

//    /**
//     * 初始化Mapper和Service
//     */
//    private void initMapperAndService() {
//        ApplicationContext ac = findApplicationContext();
//
//        weaverDeptService = (WeaverDeptService) ac.getBean("weaverDeptService");
//        weaverOrgService = (WeaverOrgService) ac.getBean("weaverOrgService");
//        weaverUserService = (WeaverUserService) ac.getBean("weaverUserService");
//
//        ucDeptInfoInsertMapper = (UcDeptInfoInsertMapper) ac.getBean("ucDeptInfoInsertMapper");
//        ucOrgInfoInsertMapper = (UcOrgInfoInsertMapper) ac.getBean("ucOrgInfoInsertMapper");
//        ucUserInfoInsertMapper = (UcUserInfoInsertMapper) ac.getBean("ucUserInfoInsertMapper");
//    }
//
//    /**
//     * 同步部门信息
//     */
//    private void synchronizeDept() {
//
//        if(weaverDeptService == null || ucDeptInfoInsertMapper == null){
//            logger.error("SynchronizeDept Error:Service or mappper is null.");
//            return;
//        }
//
//        List<WeaverDept> weaverDeptList = weaverDeptService.queryDeptsAll();
//        if (weaverDeptList == null || weaverDeptList.size() == 0) {
//            logger.error("SynchronizeDept Error:Remote service Exception or Unneed to synchronize.");
//            return;
//        }
//
//        List<UcDeptInfo> ucDeptInfoList = new ArrayList<UcDeptInfo>();
//
//        for (WeaverDept weaverDept : weaverDeptList) {
//            UcDeptInfo ucDeptInfo = new UcDeptInfo();
//            ucDeptInfo.setRefDeptId(String.valueOf(weaverDept.getDeptId()));//泛微的部门ID
//            ucDeptInfo.setName(weaverDept.getDeptName());//部门名称
//            ucDeptInfo.setOrgId(weaverDept.getSubId());//组织或公司ID
//            ucDeptInfo.setParentDeptId(weaverDept.getSupDeptId());//上级部门ID
//            ucDeptInfo.setCreateTime(new Date());//创建时间，仅第一次插入
//            ucDeptInfo.setUpdateTime(new Date());//更新时间，每次同步时更新
//
//            ucDeptInfoList.add(ucDeptInfo);
//        }
//
//        int resultCode = ucDeptInfoInsertMapper.insertAll(ucDeptInfoList);
//
//        logger.info("SynchronizeDept Result:" + resultCode + "/" + weaverDeptList.size() + ".");
//
//    }
//
//    /**
//     * 同步组织或公司
//     */
//    private void synchronizeOrg() {
//
//        if(weaverOrgService == null || ucOrgInfoInsertMapper == null){
//            logger.error("SynchronizeOrg Error:Service or mappper is null.");
//            return;
//        }
//
//        List<WeaverOrg> weaverOrgList = weaverOrgService.queryOrgsAll();
//        if(weaverOrgList == null || weaverOrgList.size() == 0){
//            logger.error("SynchronizeOrg Error:Remote service Exception or Unneed to synchronize.");
//            return;
//        }
//
//        List<UcOrgInfo> ucOrgInfoList = new ArrayList<UcOrgInfo>();
//
//        for(WeaverOrg weaverOrg:weaverOrgList){
//            UcOrgInfo ucOrgInfo = new UcOrgInfo();
//            ucOrgInfo.setRefOrgId(String.valueOf(weaverOrg.getSubId()));//泛微的组织或公司ID
//            ucOrgInfo.setName(weaverOrg.getSubName());//分部名称
//            ucOrgInfo.setParentOrgId(weaverOrg.getSupSubId());//父级分部ID
//            ucOrgInfo.setCreateTime(new Date());//创建时间，仅第一次插入
//            ucOrgInfo.setUpdateTime(new Date());//更新时间，每次同步时更新
//
//            ucOrgInfoList.add(ucOrgInfo);
//        }
//
//        int resultCode = ucOrgInfoInsertMapper.insertAll(ucOrgInfoList);
//
//        logger.info("SynchronizeOrg Result:" + resultCode + "/" + weaverOrgList.size() + ".");
//
//    }
//
//    /**
//     * 同步用户信息
//     */
//    private void synchronizeUser() {
//
//        if(weaverUserService == null || ucUserInfoInsertMapper == null){
//            logger.error("SynchronizeUser Error:Service or mappper is null.");
//            return;
//        }
//
//        List<WeaverUser> weaverUserList = weaverUserService.queryUsersAll();
//        if(weaverUserList == null || weaverUserList.size() == 0){
//            logger.error("SynchronizeUser Error:Remote service Exception or Unneed to synchronize.");
//            return;
//        }
//
//        List<UcUserInfo> usableUserInfoList = new ArrayList<UcUserInfo>();
//        List<UcUserInfo> unUsalbeUserInfoList = new ArrayList<UcUserInfo>();
//
//        for(WeaverUser weaverUser : weaverUserList){
//            if (null == weaverUser.getLoginId() || "".equals(weaverUser.getLoginId())) {
//                UcUserInfo ucUserInfo = new UcUserInfo();
//                ucUserInfo.setRefUserId(String.valueOf(weaverUser.getHrmId()));//泛微的用户ID
//                ucUserInfo.setUsable(false);//用户是否可用
//                ucUserInfo.setUpdateTime(new Date());//更新时间，每次同步时更新
//                unUsalbeUserInfoList.add(ucUserInfo);
//                continue;
//            }
//
//            UcUserInfo ucUserInfo = new UcUserInfo();
//            ucUserInfo.setRefUserId(String.valueOf(weaverUser.getHrmId()));//泛微的用户ID
//            ucUserInfo.setUserType("PARK");//同步时默认为：PARK（园区管委会用户）
//            ucUserInfo.setLoginName(weaverUser.getLoginId());//登录名
//            ucUserInfo.setLoginNameUpper(weaverUser.getLoginId().toUpperCase());//登录名大写
//            ucUserInfo.setPasswordHash(weaverUser.getPasswd());//用户加密后的密码
//            ucUserInfo.setDeptId(weaverUser.getDeptId());//用户部门ID
//            ucUserInfo.setName(weaverUser.getName());//用户姓名
//            ucUserInfo.setSex(weaverUser.getXb());//用户性别
//            ucUserInfo.setUsable(true);//用户是否可用
//            ucUserInfo.setCreateTime(new Date());//创建时间，仅第一次插入
//            ucUserInfo.setUpdateTime(new Date());//更新时间，每次同步时更新
//
//            usableUserInfoList.add(ucUserInfo);
//        }
//
//        int resultCode = ucUserInfoInsertMapper.insertAll(usableUserInfoList);
//
//        if (!unUsalbeUserInfoList.isEmpty()) {
//            resultCode += ucUserInfoInsertMapper.synchronizeDimissUsers(unUsalbeUserInfoList);
//        }
//
//        logger.info("SynchronizeUser Result:" + resultCode + "/" + weaverUserList.size() + ".");
//
//    }

}
