package cn.com.hugedata.spark.management.features.PmProject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.MessageService;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.LogOperation;
import cn.com.hugedata.spark.commons.storage.entity.PmDocType;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthInvest;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthReport;
import cn.com.hugedata.spark.commons.storage.entity.PmProject;
import cn.com.hugedata.spark.commons.storage.entity.PmProjectDoc;
import cn.com.hugedata.spark.commons.storage.entity.PmYearPlan;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcCompanyInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.PmDocTypeMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmMonthInvestMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmMonthReportMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmProjectDocMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmProjectMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmYearPlanMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcCompanyInfoMapper;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.storage.querymapper.ApplyMergeQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.management.features.base.ManagementFeatureServiceImpl;
@Service
public class PmProjectService
        extends ManagementFeatureServiceImpl<PmProject, Integer, PmProjectModel, PmProjectMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmProjectService.class);
    
    @Autowired
    UcCompanyInfoMapper ucCompanyInfoMapper;
    
    @Autowired
    UcUserInfoMapper ucUserInfoMapper;
    
    @Autowired
    private PmProjectMapper pmProjectMapper;

    @Autowired
    private PmMonthInvestMapper pmMonthInvestMapper;
    
    @Autowired
    private PmProjectDocMapper pmProjectDocMapper;
    
    @Autowired
    private PmYearPlanMapper pmYearPlanMapper;
    
    @Autowired
    private PmMonthReportMapper pmMonthReportMapper;
    
    @Autowired
    private ApplyMergeQueryMapper applyMergeQueryMapper;
    
    @Autowired
    private FileStoreService fileStoreService;
    
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private PmDocTypeMapper pmDocTypeMapper;
    
    @Override
    protected String getLogTargetName() {
        return LogOperation.TARGET_PM_PROJECT;
    }
    
    public List<UcCompanyInfo> getCompanies() throws ServiceException{
        //首先获取公司类型有效用户
        List<UcUserInfo> us = ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(UcUserInfo.Fields.USER_TYPE,"COMPANY",UcUserInfo.Fields.USABLE,1));
        List<Integer> ids = new ArrayList<Integer>();
        for(UcUserInfo u : us){
            if(u.getCompanyId() != null && !ids.contains(u.getCompanyId())){
                ids.add(u.getCompanyId());
            }
        }
        return   ucCompanyInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(UcCompanyInfo.Query.COMPANY_ID__IN,ids));
    }

    /**
    * @Title: getInfUsers
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param companyId
    * @return
    * @throws
    */
    public List<UcUserInfo> getInfUsers(int companyId)throws ServiceException{
        return   ucUserInfoMapper.selectByMap(MyBatisUtils.buildParameterMap(UcUserInfo.Fields.COMPANY_ID,companyId,UcUserInfo.Fields.USABLE,1));
    }

    @Override
    public PmProject add(PmProjectModel model) throws ServiceException
    {
        int beginYear = Integer.valueOf(model.getBeginEnd().substring(0, 4));
        int endYear = Integer.valueOf(model.getBeginEnd().substring(5, 9));
        model.setBeginYear(beginYear);
        model.setEndYear(endYear);
        
        model.setCreateTime(Calendar.getInstance().getTime());
        model.setProjectStatus("1");
        model.setProjectDeleted("0");
         PmProject  p =  super.add(model);
        
        try
        {
            messageService.createSystemMessage(UcMessage.TYPE_PROJECT_MANAGE, "增加了新的项目", "增加了新的项目", p.getInfUserId(), String.valueOf(p.getProjectId()));
        }
        catch (ServiceException e)
        {
            throw new ServiceException("sendMsgFailed","发送消息失败!"); 
        }
        return p ;
        
    }
    
    public PmProject getDetail(Integer projectId)
    {
        //将红点删除
        PmProject pmProject = pmProjectMapper.selectById(projectId);
        if(pmProject != null && "1".equals(pmProject.getFrontUpdated())){
            pmProject.setFrontUpdated("0");
            pmProjectMapper.updateSelectiveById(pmProject);
        }
        return pmProject;
    }


    /**
    * @Title: getMonthInvests
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @return
    * @throws
    */
    public List<PmMonthInvest> getMonthInvests(Integer projectId)
    {
        // TODO Auto-generated method stub
        return pmMonthInvestMapper.selectByMap(MyBatisUtils.buildParameterMap(PmMonthInvest.Fields.PROJECT_ID,projectId));
    }

    public  PageEntity<PmProjectDoc> listDoc(Integer projectId, String typeCodeBegin,
            Integer start, Integer length)
    {
        
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmProjectDoc.Fields.PROJECT_ID,projectId,
                PmProjectDoc.Fields.DELETED,0,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmProjectDoc.Fields.UPLOAD_TIME, OrderItem.DESC))
        );
       String docType = "1";
        if(!"100".equals(typeCodeBegin)){
            docType = "2";
          
        }
        query.put( PmProjectDoc.Fields.DOC_TYPE, docType);
        
      //王宁新需求： 首先进行文件类型监测 为添加到该项目的文件类型则都要添加到该项目中
        List<PmProjectDoc> allProjectDocs = pmProjectDocMapper.selectByMap(query);
        List<PmDocType> docTypes = this.getDocTypes(docType);
        for(PmDocType dt : docTypes){
            boolean exist = false;
            for(PmProjectDoc d: allProjectDocs){
                if(d.getTypeName().equals(dt.getTypeName())){
                    exist = true;
                    break;
                }
            }
            if(!exist){
                PmProjectDoc nd = new PmProjectDoc();
                nd.setDocType(dt.getDocType());
                nd.setTypeCode(String.valueOf(dt.getTypeId()));
                nd.setTypeName(dt.getTypeName());
                nd.setDeleted(false);
                nd.setProjectId(projectId);
                pmProjectDocMapper.insertSelective(nd);
            }
        }
        
        List<PmProjectDoc> data = pmProjectDocMapper.selectByMap(query, new RowBounds(start, length));
        for(PmProjectDoc d : data){
            if(d.getFileId() != null){
                SysFileInfo file = fileStoreService.loadFileInfo( d.getFileId());
                d.setFileId(fileUrlHelperService.fixDownloadUrl(d.getFileId(),file.getFileName()));
            }
        }
        int count = pmProjectDocMapper.countByMap(query);
        return new PageEntity<>(data, start, length, count);
    }
    
    public List<PmDocType> getDocTypes(String docType)
    {
        return pmDocTypeMapper.selectByMap(MyBatisUtils.buildParameterMap(PmDocType.Fields.DOC_TYPE,docType));
    }
    
    
    public  PageEntity<PmProject> listProject(String projectName,
            Integer start, Integer length)
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmProject.Fields.PROJECT_DELETED,'0',
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmProject.Fields.CREATE_TIME, OrderItem.DESC))
        );
        if(projectName != null && !"".equals(projectName)){
            query.put(PmProject.Query.PROJECT_NAME__LIKE,projectName);
        }
      
        List<PmProject> data = pmProjectMapper.selectByMap(query, new RowBounds(start, length));
        int count = pmProjectMapper.countByMap(query);
        return new PageEntity<>(data, start, length, count);
    }
    
    public Object deleteDoc(Integer docId) throws ServiceException
    {
        PmProjectDoc d= pmProjectDocMapper.selectById(docId);
        d.setDeleted(true);
        d.setUpdateTime(Calendar.getInstance().getTime());
        pmProjectDocMapper.updateSelectiveById(d);
        PmProject p = pmProjectMapper.selectById(d.getProjectId());
        try
        {
            messageService.createSystemMessage(UcMessage.TYPE_PROJECT_MANAGE, d.getFileName() + "文件资料被删除",d.getFileName() + "文件资料被删除", p.getInfUserId(), String.valueOf(d.getProjectId()));
        }
        catch (ServiceException e)
        {
            throw new ServiceException("deleteFileFailed","发送删除消息失败!"); 
        }
        return null;
    }
    
    public Object deleteProject(Integer projectId) throws ServiceException
    {
        PmProject d= pmProjectMapper.selectById(projectId);
        d.setProjectDeleted("1");
        pmProjectMapper.updateSelectiveById(d);
        try
        {
            messageService.createSystemMessage(UcMessage.TYPE_PROJECT_MANAGE, d.getProjectName() + "项目被删除",d.getProjectName() + "项目被删除",d.getInfUserId(), String.valueOf(d.getProjectId()));
        }
        catch (ServiceException e)
        {
            throw new ServiceException("deleteFileFailed","发送删除消息失败!"); 
        }
        return null;
    }
    
    public Object chgDocStatus(Integer docId, String status) throws ServiceException
    {
        PmProjectDoc d= pmProjectDocMapper.selectById(docId);
        d.setStatus(status);
        d.setUpdateTime(Calendar.getInstance().getTime());
        pmProjectDocMapper.updateSelectiveById(d);
        return null;
    }
    
    
    
    public PageEntity<PmYearPlan> listPlan(Integer projectId, Integer start, Integer length)
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmYearPlan.Fields.PROJECT_ID,projectId,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmYearPlan.Fields.PLAN_ID, OrderItem.DESC))
        );
        List<PmYearPlan> data = pmYearPlanMapper.selectByMap(query, new RowBounds(start, length));
        int count = pmYearPlanMapper.countByMap(query);
        return new PageEntity<>(data, start, length, count);
    }

    /**
    * @Title: approvePlan
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param planId
    * @param approveResult
    * @return
    * @throws
    */
    public Object approvePlan(Integer planId, String approveResult,boolean approveStatus) throws ServiceException
    {
        PmYearPlan p = pmYearPlanMapper.selectById(planId);
        p.setApproveResult(approveResult);
        p.setPlanStatus(approveStatus);
        pmYearPlanMapper.updateSelectiveById(p);
        PmProject pp = pmProjectMapper.selectById(p.getProjectId());
        try
        {
            if(approveStatus){
                messageService.createSystemMessage(UcMessage.TYPE_PROJECT_MANAGE, "项目年度计划审核通过", "项目年度计划审核通过", pp.getInfUserId(), String.valueOf(p.getProjectId()));
            }else{
                messageService.createSystemMessage(UcMessage.TYPE_PROJECT_MANAGE, "项目年度计划审核不通过", "项目年度计划审核不通过", pp.getInfUserId(), String.valueOf(p.getProjectId()));
            }
           
        }
        catch (ServiceException e)
        {
            throw new ServiceException("sendMsgFailed","发送消息失败!"); 
        }
        
        
        return null;
    }
    
    
    public Double getYearInvestedTotal(int projectId,int year)
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmMonthInvest.Fields.PROJECT_ID,projectId,
                PmMonthInvest.Fields.INVEST_STATUS,1,
                PmMonthInvest.Query.INVEST_MONTH__LIKE,year,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmMonthInvest.Fields.INVEST_ID, OrderItem.DESC)));
        List<PmMonthInvest> mis = pmMonthInvestMapper.selectByMap(query);
        if(mis != null && mis.size() > 0){
            Double t = 0.00D;
            for(PmMonthInvest i : mis){
                t = t+i.getAmount();
            }
            return t;
        }
        return 0.00D;
    }
    
    public Double getInvestedTotal(int projectId)
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmMonthInvest.Fields.PROJECT_ID,projectId,
                PmMonthInvest.Fields.INVEST_STATUS,1,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmMonthInvest.Fields.INVEST_ID, OrderItem.DESC)));
        List<PmMonthInvest> mis = pmMonthInvestMapper.selectByMap(query);
        if(mis != null && mis.size() > 0){
            Double t = 0.00D;
            for(PmMonthInvest i : mis){
                t = t+i.getAmount();
            }
            return t;
        }
        return 0.00D;
    }


    /**
    * @Title: addMonthReport
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param year
    * @param month
    * @return
    * @throws
    */
    public PmMonthReport addMonthReport(int projectId, int year, Integer month)
    {
        PmMonthReport p = new PmMonthReport();
        p.setProjectId(projectId);
        p.setReportYear(year);
        p.setReportMonth(month);
        p.setReportStatus(-1);
        p.setCreateTime(Calendar.getInstance().getTime());
        pmMonthReportMapper.insertSelective(p);
        return p;
    }


    /**
    * @Title: addMonthInvest
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param reportId
    * @param comeTypeId
    * @param comeTypeName
    * @param comeFrom
    * @param amount
    * @param month
    * @throws
    */
    public void addMonthInvest(Integer projectId, Integer reportId,
            Integer comeTypeId, String comeTypeName, String comeFrom,
            Double amount, Integer month) throws ServiceException
    {
        PmMonthInvest i = new PmMonthInvest();
        i.setProjectId(projectId);
        i.setReportId(reportId);
        i.setComeTypeId(comeTypeId);
        i.setComeTypeName(comeTypeName);
        i.setComeFrom(comeFrom);
        i.setAmount(amount);
        Calendar now = Calendar.getInstance();
        now.set(Calendar.MONTH, month-1);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        i.setInvestMonth(f.format(now.getTime()));
        i.setInvestStatus(-1);
        pmMonthInvestMapper.insertSelective(i);
        
    }


    /**
    * @Title: deleteInvest
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param investId
    * @return
    * @throws
    */
    public Object deleteInvest(Integer investId)
    {
        pmMonthInvestMapper.deleteById(investId);
        return null;
    }


    /**
    * @Title: listMonthInvest
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param reportId
    * @return
    * @throws
    */
    public List<PmMonthInvest> listMonthInvest(Integer projectId, Integer reportId)
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmMonthInvest.Fields.PROJECT_ID,projectId,
                PmMonthInvest.Fields.REPORT_ID,reportId,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmMonthInvest.Fields.INVEST_ID, OrderItem.DESC))
        );
        List<PmMonthInvest> data = pmMonthInvestMapper.selectByMap(query);
        return data;
    }


    /**
    * @Title: submitReport
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param reportId
    * @param investedNum
    * @param useDesign
    * @param useOversee
    * @param useEngineer
    * @param useBuy
    * @param useAsset
    * @param useTotal
    * @param current
    * @param asset
    * @param difficult
    * @param difficultOrg
    * @param comment
    * @param approveInfo
    * @throws
    */
    public void submitReport(Integer reportId, Double investedNum,
            Double useDesign, Double useOversee, Double useEngineer,
            Double useBuy, Double useAsset, Double useTotal, String current,
            String asset, String difficult, String difficultOrg,
            String comment, String approveInfo) throws ServiceException
    {
        PmMonthReport p = pmMonthReportMapper.selectById(reportId);
        p.setInvestedNum(investedNum);
        p.setUseDesign(useDesign);
        p.setUseAsset(useAsset);
        p.setUseBuy(useBuy);
        p.setUseEngineer(useEngineer);
        p.setUseOversee(useOversee);
        p.setUseTotal(useTotal);
        p.setCurrent(current);
        p.setAsset(asset);
        p.setDifficult(difficult);
        p.setDifficultOrg(difficultOrg);
        p.setComment(comment);
        p.setApproveInfo(approveInfo);
        p.setReportStatus(1);
        pmMonthReportMapper.updateSelectiveById(p);
        
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmMonthInvest.Fields.REPORT_ID,reportId,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmMonthInvest.Fields.INVEST_ID, OrderItem.DESC))
        );
        List<PmMonthInvest> data = pmMonthInvestMapper.selectByMap(query);
        if(data != null && data.size() > 0){
            for(PmMonthInvest i : data){
                i.setInvestStatus(1);
                pmMonthInvestMapper.updateSelectiveById(i);
            }
        }
        
        PmProject pp = pmProjectMapper.selectById(p.getProjectId());
        try
        {
            messageService.createSystemMessage(UcMessage.TYPE_PROJECT_MANAGE, "项目月报已审核", "项目月报已审核", pp.getInfUserId(), String.valueOf(p.getProjectId()));
        }
        catch (ServiceException e)
        {
            throw new ServiceException("deleteFileFailed","发送消息失败!"); 
        }
    }
    
    
    public PmMonthReport getMonthReport(Integer projectId,Integer year,Integer month)
    {
       
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmMonthReport.Fields.PROJECT_ID,projectId,
                PmMonthReport.Fields.REPORT_YEAR,year,
                PmMonthReport.Fields.REPORT_MONTH,month  ,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmMonthReport.Fields.REPORT_ID, OrderItem.DESC)));
        List<PmMonthReport> rs = pmMonthReportMapper.selectByMap(query);
        if(rs != null && rs.size() > 0){
            return rs.get(0);
        }
        return null;
    }


    /**
    * @Title: getMonthReports
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param year
    * @return
    * @throws
    */
    public List<PmMonthReport> getMonthReports(int projectId, int year)
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmMonthReport.Fields.PROJECT_ID,projectId,
                PmMonthReport.Fields.REPORT_YEAR,year,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmMonthReport.Fields.REPORT_ID, OrderItem.DESC)));
        return pmMonthReportMapper.selectByMap(query);
    }


    /**
    * @Title: getMonthListInfo
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param year
    * @return
    * @throws
    */
    public List<Map<String, Object>> getMonthListInfo(int projectId, int year)
    {
        List<Map<String,Object>> ms = new ArrayList<Map<String,Object>>();
        List<PmMonthReport>  rs = this.getMonthReports(projectId, year);
        for(int i = 1 ; i < 13 ; i ++ ){
            boolean hasRecord = false;
            for(PmMonthReport r : rs){
                if(r.getReportMonth() == i){
                    Map<String,Object> m = new HashMap<String,Object>();
                    m.put("month", i);
                    m.put("status", r.getReportStatus());
                    m.put("year", year);
                    ms.add(m);
                    hasRecord = true;
                    break;
                }
            }
            
            if(!hasRecord){
                Map<String,Object> m = new HashMap<String,Object>();
                m.put("month", i);
                m.put("status", -1);
                m.put("year", year);
                ms.add(m);
            }
        }
        return ms;
    }


    /**
    * @Title: getYearPlan
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param year
    * @return
    * @throws
    */
    public PmYearPlan getYearPlan(int projectId, int year)
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmYearPlan.Fields.PROJECT_ID,projectId,
                PmYearPlan.Fields.PLAN_YEAR,year,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmYearPlan.Fields.PLAN_ID, OrderItem.DESC)));
        List<PmYearPlan> yps = pmYearPlanMapper.selectByMap(query);
        if(yps != null && yps.size() > 0){
            return yps.get(0);
        }
        return null;
    }
    
    
    public void deletePlan(Integer planId) throws ServiceException
    {
        PmYearPlan p  = pmYearPlanMapper.selectById(planId);
        if(p == null ){
            throw new ServiceException("planNotExist","计划已经不存在");
        }
        if(p.getPlanStatus() ){
            throw new ServiceException("planApproved","计划已审核,不能删除!");
        }
        pmYearPlanMapper.deleteById(planId);
        PmProject pp = pmProjectMapper.selectById(p.getProjectId());
        try
        {
            messageService.createSystemMessage(UcMessage.TYPE_PROJECT_MANAGE, "项目年度计划被删除", "项目年度计划被删除", pp.getInfUserId(), String.valueOf(p.getProjectId()));
        }
        catch (ServiceException e)
        {
            throw new ServiceException("sendMsgFailed","发送消息失败!"); 
        }
    }

    /**
    * @Title: getProjectAreas
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @return
    * @throws
    */
    public List<String> getProjectAreas()
    {
        List<PmProject> ps = pmProjectMapper.selectByMap(null);
        if(ps != null){
            List<String> as = new ArrayList<String>();
            for(PmProject p : ps){
                if(p.getProjectArea() != null && !"".equals(p.getProjectArea().trim()) && !as.contains(p.getProjectArea())){
                    as.add(p.getProjectArea());
                }
            }
            return as;
        }
        return null;
    }

    /**
    * @Title: listProjectAdvance
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param yearAmountType
    * @param totalAmountType
    * @param startYear
    * @param endYear
    * @param yearAmount
    * @param totalAmount
    * @param projectArea
    * @param start
    * @param length
    * @return
    * @throws
    */
    public PageEntity<PmProject> listProjectAdvance( Integer startYear, Integer endYear,
            Double gtYearAmount, Double gtTotalAmount, 
            Double ltYearAmount, Double ltTotalAmount, String projectArea,
            Integer start, Integer length)
    {
        List<PmProject> ps =  applyMergeQueryMapper.listProjectAdvance( 
               MyBatisUtils.buildParameterMap("gtYearAmount",gtYearAmount,
                       "gtTotalAmount",gtTotalAmount,
                       "startYear",startYear,
                       "endYear",endYear,
                       "ltYearAmount",ltYearAmount,
                       "ltTotalAmount",ltTotalAmount,
                       "projectArea",projectArea
                       ), new RowBounds(start, length));
        int count = applyMergeQueryMapper.countProjectAdvance(MyBatisUtils.buildParameterMap(
                "gtYearAmount",gtYearAmount,
                "gtTotalAmount",gtTotalAmount,
                "startYear",startYear,
                "endYear",endYear,
                "ltYearAmount",ltYearAmount,
                "ltTotalAmount",ltTotalAmount,
                "projectArea",projectArea
                       ));
        return new PageEntity<>(ps, start, length, count);
    }

    /**
    * @Title: getDocTypes
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param valueOf
    * @return
    * @throws
    */
    public String getDocTypes(Integer projectId)
    {

        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmProjectDoc.Fields.PROJECT_ID,projectId,
                PmProjectDoc.Fields.DELETED,0,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmProjectDoc.Fields.UPLOAD_TIME, OrderItem.DESC))
        );
       
       
        List<PmProjectDoc> data = pmProjectDocMapper.selectByMap(query);
        List<String> docTypes = new ArrayList<String>();
        String r = "";
        for(PmProjectDoc d : data){
            if(!docTypes.contains(d.getTypeName())){
                docTypes.add(d.getTypeName());
                r = r + d.getTypeName() + ",";
            }
        }
        return r;
    }

    /**
    * @Title: getLastMonthReport
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param valueOf
    * @return
    * @throws
    */
    public PmMonthReport getLastMonthReport(Integer projectId)
    {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmMonthReport.Fields.PROJECT_ID,projectId,
                PmMonthReport.Fields.REPORT_YEAR,c.get(Calendar.YEAR),
                PmMonthReport.Fields.REPORT_MONTH,c.get(Calendar.MONTH)+1,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmMonthReport.Fields.REPORT_ID, OrderItem.DESC)));
        List<PmMonthReport> ms =  pmMonthReportMapper.selectByMap(query);
        if(ms != null && ms.size() > 0){
            return ms.get(0);
        }
        return null;
    }
}
