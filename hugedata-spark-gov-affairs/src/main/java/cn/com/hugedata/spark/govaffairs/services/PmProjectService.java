package cn.com.hugedata.spark.govaffairs.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PmDocType;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthInvest;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthReport;
import cn.com.hugedata.spark.commons.storage.entity.PmProject;
import cn.com.hugedata.spark.commons.storage.entity.PmProjectDoc;
import cn.com.hugedata.spark.commons.storage.entity.PmYearPlan;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.mapper.PmDocTypeMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmMonthInvestMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmMonthReportMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmProjectDocMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmProjectMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PmYearPlanMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

/**
 * 办事指南查询服务.
 */
@Service
public class PmProjectService {

    @Autowired
    private PmProjectMapper pmProjectMapper;
    
    @Autowired
    private PmYearPlanMapper pmYearPlanMapper;

    @Autowired
    private PmMonthInvestMapper pmMonthInvestMapper;
    
    @Autowired
    private PmProjectDocMapper pmProjectDocMapper;
    
    @Autowired
    private PmDocTypeMapper pmDocTypeMapper;
    
    @Autowired
    private PmMonthReportMapper pmMonthReportMapper;
    
    @Autowired
    private FileStoreService fileStoreService;
    
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;

  
    public PageEntity<PmProject> list(String keyword, int start, int length) {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmProject.Fields.INF_USER_ID,LoginUtils.getCurrentLogin().getUserId(),
                PmProject.Fields.PROJECT_DELETED,'0',
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmProject.Fields.CREATE_TIME, OrderItem.DESC))
        );
       
        if(!"".equals(keyword)){
            query.put( PmProject.Query.PROJECT_NAME__LIKE, keyword);
        }
        List<PmProject> data = pmProjectMapper.selectByMap(query, new RowBounds(start, length));
        int count = pmProjectMapper.countByMap(query);
        return new PageEntity<>(data, start, length, count);
    }


    /**
    * @Title: getDetail
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @return
    * @throws
    */
    public PmProject getDetail(Integer projectId)
    {
        return pmProjectMapper.selectById(projectId);
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
    
    
    public List<PmDocType> getDocTypes(String docType)
    {
        return pmDocTypeMapper.selectByMap(MyBatisUtils.buildParameterMap(PmDocType.Fields.DOC_TYPE,docType));
    }
    
    
    


    /**
    * @Title: modify
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param projectName
    * @param projectCode
    * @param projectAddr
    * @param projectArea
    * @param projectType
    * @param projectContent
    * @throws
    */
    public void modify(int projectId, String projectName, String projectCode,
            String projectAddr, String projectArea, String projectType,
            String projectContent)  throws ServiceException
    {
        PmProject pmProject = pmProjectMapper.selectById(projectId);
        pmProject.setProjectName(projectName);
        pmProject.setProjectCode(projectCode);
        pmProject.setProjectAddr(projectAddr);
        pmProject.setProjectArea(projectArea);
        pmProject.setProjectType(projectType);
        pmProject.setProjectContent(projectContent);
        pmProject.setFrontUpdated("1");
        pmProjectMapper.updateSelectiveById(pmProject);
    }


    /**
    * @Title: addDoc
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param typeCode
    * @param typeName
    * @param fileName
    * @param approveCode
    * @param fileContent
    * @throws
    */
    public void addDoc(Integer projectId, Integer docType, String typeCode, String typeName,
            String fileName, String approveCode,
            CommonsMultipartFile fileContent)throws ServiceException {
                String fileId = fileStoreService.save(fileContent);
                 PmProjectDoc obj = new PmProjectDoc();
                 obj.setProjectId(projectId);
                 obj.setTypeCode(typeCode);
                 if(docType == 1){
                     obj.setDocType("1");
                 }else{
                     obj.setDocType("2");
                 }
                 obj.setTypeName(typeName);
                 obj.setFileName(fileName);
                 obj.setApproveCode(approveCode);
                 obj.setFileId(fileId);
                 obj.setUploadUserName(LoginUtils.getCurrentLogin().getName());
                 obj.setUploadUserId(LoginUtils.getCurrentLogin().getUserId());
                 obj.setUploadTime(Calendar.getInstance().getTime());
                 obj.setDeleted(false);
                 obj.setStatus("2");
                 pmProjectDocMapper.insertSelective(obj);
                 
                 PmProject pmProject = pmProjectMapper.selectById(projectId);
                 pmProject.setFrontUpdated("1");
                 pmProjectMapper.updateSelectiveById(pmProject);
                 
             }
    
    public void modifyDoc(Integer docId,Integer projectId, String typeCode, String typeName,
            String fileName, String approveCode,
            CommonsMultipartFile fileContent)throws ServiceException {
                PmProjectDoc d = pmProjectDocMapper.selectById(docId);
                if(fileContent != null && fileContent.getSize() > 0){
                    fileStoreService.deleteFile(d.getFileId());
                    String fileId = fileStoreService.save(fileContent);
                    d.setFileId(fileId);
                }
                 d.setFileName(fileName);
                 d.setTypeName(typeName);
                 d.setApproveCode(approveCode);
                 d.setUploadUserName(LoginUtils.getCurrentLogin().getName());
                 d.setUploadUserId(LoginUtils.getCurrentLogin().getUserId());
                 d.setUploadTime(Calendar.getInstance().getTime());
                 d.setDeleted(false);
                 d.setStatus("2");
                 pmProjectDocMapper.updateSelectiveById(d);
                 
                 PmProject pmProject = pmProjectMapper.selectById(projectId);
                 pmProject.setFrontUpdated("1");
                 pmProjectMapper.updateSelectiveById(pmProject);
             }
    
    


    /**
    * @Title: listDoc
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param typeCodeBegin
    * @param start
    * @param length
    * @return
    * @throws
    */
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


    /**
    * @Title: deleteDoc
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param docId
    * @return
    * @throws
    */
    public Object deleteDoc(Integer docId)
    {
        PmProjectDoc d= pmProjectDocMapper.selectById(docId);
        d.setDeleted(true);
        d.setUpdateTime(Calendar.getInstance().getTime());
        pmProjectDocMapper.updateSelectiveById(d);
        PmProject pmProject = pmProjectMapper.selectById(d.getProjectId());
        pmProject.setFrontUpdated("1");
        pmProjectMapper.updateSelectiveById(pmProject);
        return null;
    }


    /**
    * @Title: addPlan
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param planYear
    * @param amount
    * @param mainActor
    * @param content
    * @throws
    */
    public void addPlan(Integer projectId, String planYear, Double amount,
            String mainActor, String content) throws ServiceException
    {
        PmYearPlan p = new PmYearPlan();
        p.setProjectId(projectId);
        p.setAmount(amount);
        p.setContent(content);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if(planYear.equals("1")){
            year = year + 1;
            p.setPlanYear(String.valueOf(year));
        }else{
            p.setPlanYear(String.valueOf(year));
        }
        //验证计划是否已经存在
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmYearPlan.Fields.PROJECT_ID,projectId,
                PmYearPlan.Fields.PLAN_YEAR,year
        );
        Integer count = pmYearPlanMapper.countByMap(query);
        if(count > 0){
            throw new ServiceException("planExist",year+"计划已经存在");
        }
        
        p.setMainActor(mainActor);
        p.setCreateTime(Calendar.getInstance().getTime());
        p.setPlanStatus(false);
        pmYearPlanMapper.insertSelective(p);
        
        PmProject pmProject = pmProjectMapper.selectById(projectId);
        pmProject.setFrontUpdated("1");
        pmProjectMapper.updateSelectiveById(pmProject);
    }

    public void modifyPlan(Integer planId,Integer projectId, String planYear, Double amount,
            String mainActor, String content) throws ServiceException
    {
        
        PmYearPlan p = pmYearPlanMapper.selectById(planId);
        p.setProjectId(projectId);
        p.setAmount(amount);
        p.setContent(content);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if(planYear.equals("1")){
            year = year + 1;
            p.setPlanYear(String.valueOf(year));
        }else{
            p.setPlanYear(String.valueOf(year));
        }
        //验证计划是否已经存在
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmYearPlan.Fields.PROJECT_ID,projectId,
                PmYearPlan.Fields.PLAN_YEAR,year,
                PmYearPlan.Query.PLAN_ID__NE,planId
        );
        Integer count = pmYearPlanMapper.countByMap(query);
        if(count > 0){
            throw new ServiceException("planExist",year+"计划已经存在");
        }
        
        p.setMainActor(mainActor);
        p.setCreateTime(Calendar.getInstance().getTime());
        p.setPlanStatus(false);
        pmYearPlanMapper.updateSelectiveById(p);
        
        PmProject pmProject = pmProjectMapper.selectById(projectId);
        pmProject.setFrontUpdated("1");
        pmProjectMapper.updateSelectiveById(pmProject);
    }

    /**
    * @Title: listPlan
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @param start
    * @param length
    * @return
    * @throws
    */
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
    * @Title: getMonthReportByMonth
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param month
    * @return
    * @throws
    */
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
                    m.put("year", year);
                    m.put("status", r.getReportStatus());
                    ms.add(m);
                    hasRecord = true;
                    break;
                }
            }
            
            if(!hasRecord){
                Map<String,Object> m = new HashMap<String,Object>();
                m.put("month", i);
                m.put("year", year);
                m.put("status", -1);
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


    /**
    * @Title: getYearInvestedTotal
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author QianQingzhao
    * @param projectId
    * @return
    * @throws
    */
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
            String comment ) throws ServiceException
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
       
        p.setReportStatus(0);
        pmMonthReportMapper.updateSelectiveById(p);
        
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmMonthInvest.Fields.REPORT_ID,reportId,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PmMonthInvest.Fields.INVEST_ID, OrderItem.DESC))
        );
        List<PmMonthInvest> data = pmMonthInvestMapper.selectByMap(query);
        if(data != null && data.size() > 0){
            for(PmMonthInvest i : data){
                i.setInvestStatus(0);
                pmMonthInvestMapper.updateSelectiveById(i);
            }
        }
        
        PmProject pmProject = pmProjectMapper.selectById(p.getProjectId());
        pmProject.setFrontUpdated("1");
        pmProjectMapper.updateSelectiveById(pmProject);
    }


    
    public void checkAddDocTypeExist(Integer projectId,String typeCodeBegin, String typeName)throws ServiceException
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmProjectDoc.Fields.PROJECT_ID,projectId,
                PmProjectDoc.Fields.DELETED,0 
        );
       String docType = "1";
        if(!"100".equals(typeCodeBegin)){
            docType = "2";
          
        }
        query.put( PmProjectDoc.Fields.DOC_TYPE, docType);
        query.put( PmProjectDoc.Fields.TYPE_NAME, typeName);
        
      //王宁新需求： 首先进行文件类型监测 为添加到该项目的文件类型则都要添加到该项目中
        List<PmProjectDoc> docs = pmProjectDocMapper.selectByMap(query);
        if(docs != null && docs.size() >0){
            throw new ServiceException("AddDocTypeExist","所增加的文件类型已经存在!");
        }
        
        
    }
    
    public void checkModifyDocTypeExist(Integer projectId,Integer docId,String typeCodeBegin, String typeName)throws ServiceException
    {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PmProjectDoc.Fields.PROJECT_ID,projectId,
                PmProjectDoc.Fields.DELETED,0,
                PmProjectDoc.Query.DOC_ID__NE,docId 
        );
       String docType = "1";
        if(!"100".equals(typeCodeBegin)){
            docType = "2";
          
        }
        query.put( PmProjectDoc.Fields.DOC_TYPE, docType);
        query.put( PmProjectDoc.Fields.TYPE_NAME, typeName);
        
      //王宁新需求： 首先进行文件类型监测 为添加到该项目的文件类型则都要添加到该项目中
        List<PmProjectDoc> docs = pmProjectDocMapper.selectByMap(query);
        if(docs != null && docs.size() >0){
            throw new ServiceException("ModifyDocTypeExist","所修改的文件类型已经存在!");
        }
        
        
    }
}
