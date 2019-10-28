package cn.com.hugedata.spark.management.features.PmProject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.MessageService;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthInvest;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthReport;
import cn.com.hugedata.spark.commons.storage.entity.PmProject;
import cn.com.hugedata.spark.commons.storage.entity.PmYearPlan;
import cn.com.hugedata.spark.commons.storage.entity.UcMessage;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.ErrorPageBuilder;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.commons.web.login.Login;


@Controller
@RequestMapping("/features/PmProject")
public class PmProjectController
        extends FeatureControllerImpl<PmProject, Integer, PmProjectService, PmProjectModel> {
    
    @Autowired
    private MessageService messageService;
    
    
    @RequestMapping(value = "getCompanies", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public AjaxResponse getCompanies(
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            return AjaxResponse.createSuccessResponse(this.service.getCompanies());
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    @RequestMapping(value = "getInfUsers", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public AjaxResponse getInfUsers(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("companyId") int companyId) {
        try {
            return AjaxResponse.createSuccessResponse(this.service.getInfUsers(companyId));
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    
    @Override
    public ModelAndView list(HttpServletRequest request,
            HttpServletResponse response, Locale locale)
    {
        List<String> areas = this.service.getProjectAreas();
        ModelAndView mv = super.list(request, response, locale);
        mv.addObject("projectAreas", areas);
        return mv; 
    }

    @Override
    public ModelAndView detail(HttpServletRequest request,
            HttpServletResponse response, int serviceId, Locale locale)
    {
        ModelAndView mv = super.detail(request, response, serviceId, locale);
        PmProject p = this.service.getDetail(serviceId);
        mv.addObject("project", p);
        List<PmMonthInvest> is = this.service.getMonthInvests(serviceId);
        mv.addObject("monthInvests", is);
        Double it  = this.service.getInvestedTotal(serviceId);
        mv.addObject("investedTotal", it);
        return mv;
    }
    
    
    @RequestMapping("/modifyProject")
    @ResponseBody
    public AjaxResponse modifyProject(@RequestParam("projectId") int projectId,
            @RequestParam("projectName") String projectName ,
            @RequestParam("projectCode") String projectCode ,
            @RequestParam("projectAddr") String projectAddr ,
            @RequestParam("projectArea") String projectArea ,
            @RequestParam("projectType") String projectType ,
            @RequestParam("projectStatus") String projectStatus ,
            @RequestParam("accountComment") String accountComment ,
            @RequestParam("projectContent") String projectContent,
            
            @RequestParam("mainActor") String mainActor ,
            @RequestParam("dutyOrg") String dutyOrg ,
            @RequestParam("dutyMan") String dutyMan,
            @RequestParam("contactLead") String contactLead ,
            @RequestParam("repoName") String repoName ,
            @RequestParam("beginEnd") String beginEnd,
            @RequestParam("investTotal") Double investTotal ,
            @RequestParam("infCompanyId") Integer infCompanyId ,
            @RequestParam("infCompanyName") String infCompanyName,
            @RequestParam("infUserId") Integer infUserId ,
            @RequestParam("infUserName") String infUserName
            )  {
        try {
            PmProjectModel p = this.service.queryForModify(projectId);
            p.setProjectName(projectName);
            p.setProjectCode(projectCode);
            p.setProjectAddr(projectAddr);
            p.setProjectArea(projectArea);
            p.setProjectType(projectType);
            p.setProjectContent(projectContent);
            p.setProjectStatus(projectStatus);
            p.setAccountComment(accountComment);
            p.setMainActor(mainActor);
            p.setDutyOrg(dutyOrg);
            p.setDutyMan(dutyMan);
            p.setContactLead(contactLead);
            p.setRepoName(repoName);
            p.setBeginEnd(beginEnd);
            p.setInvestTotal(investTotal);
            p.setInfCompanyId(infCompanyId);
            p.setInfCompanyName(infCompanyName);
            p.setInfUserId(infUserId);
            p.setInfUserName(infUserName);
            int beginYear = Integer.valueOf(p.getBeginEnd().substring(0, 4));
            int endYear = Integer.valueOf(p.getBeginEnd().substring(5, 9));
            p.setBeginYear(beginYear);
            p.setEndYear(endYear);
            this.service.modify(p);
            messageService.createSystemMessage(UcMessage.TYPE_PROJECT_MANAGE, "项目信息有更新", "项目信息有更新", p.getInfUserId(), String.valueOf(p.getProjectId()));
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    
    @RequestMapping("/doc1")
    @Login(required = true)
    public ModelAndView doc1(@RequestParam("projectId") int projectId) {
        ModelAndView mv = new ModelAndView("/features/PmProject/PmProject_Doc1");
        mv.addObject("projectId", projectId);
        PmProjectModel p;
        try
        {
            p = this.service.queryForModify(projectId);
        }
        catch (ServiceException e)
        {
            return new ErrorPageBuilder()
            .setErrorMessageText(e.getMessage())
            .setErrorTitleText(e.getMessage())
            .create();
        }
        mv.addObject("projectName", p.getProjectName());
        return mv;
    }
    
    @RequestMapping("/doc2")
    @Login(required = true)
    public ModelAndView doc2(@RequestParam("projectId") int projectId) {
        ModelAndView mv = new ModelAndView("/features/PmProject/PmProject_Doc2");
        mv.addObject("projectId", projectId);
        PmProjectModel p;
        try
        {
            p = this.service.queryForModify(projectId);
        }
        catch (ServiceException e)
        {
            return new ErrorPageBuilder()
            .setErrorMessageText(e.getMessage())
            .setErrorTitleText(e.getMessage())
            .create();
        }
        mv.addObject("projectName", p.getProjectName());
        return mv;
    }
    
    
    @RequestMapping("/listDoc")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse listDoc(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value="rd",required=false) Long rd,
            @RequestParam(value = "typeCodeBegin", defaultValue = "100") String typeCodeBegin,
            @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
        return AjaxResponse.createSuccessResponse(this.service.listDoc(projectId,typeCodeBegin, start, length));
    }
    
    
    @RequestMapping("/listProject")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse listProject(
            @RequestParam(value = "projectName" ,required = false) String projectName,
            @RequestParam("searchType") Integer searchType,
            @RequestParam(value="rd",required=false) Long rd,
            @RequestParam(value ="startYear",required = false) Integer startYear,
            @RequestParam(value ="endYear",required = false) Integer endYear,
            @RequestParam(value ="ltYearAmount",required = false) Double ltYearAmount,
            @RequestParam(value ="ltTotalAmount",required = false) Double ltTotalAmount,
            @RequestParam(value ="gtYearAmount",required = false) Double gtYearAmount,
            @RequestParam(value ="gtTotalAmount",required = false) Double gtTotalAmount,
            @RequestParam(value ="projectArea",required = false) String projectArea,
            @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
        if(searchType == 0){
            return AjaxResponse.createSuccessResponse(this.service.listProject(projectName, start, length));
        }else{
            return AjaxResponse.createSuccessResponse(this.service.listProjectAdvance(startYear,endYear,gtYearAmount,gtTotalAmount,ltYearAmount,ltTotalAmount,projectArea, start, length));
        }
        
    }
    
    
    
    
    @RequestMapping("/chgDocStatus")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse chgDocStatus(
            @RequestParam(value = "docId") Integer docId, @RequestParam(value = "status") String status) {
            try
            {
                return AjaxResponse.createSuccessResponse(this.service.chgDocStatus(docId,status));
            } catch (ServiceException e) {
                return AjaxResponse.createFailResponse(e.getMessage());
            }
    }
    
    
    @RequestMapping("/deleteDoc")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse deleteDoc(
            @RequestParam(value = "docId") Integer docId) {
            try
            {
                return AjaxResponse.createSuccessResponse(this.service.deleteDoc(docId));
            } catch (ServiceException e) {
                return AjaxResponse.createFailResponse(e.getMessage());
            }
    }
   
    @RequestMapping("/deleteProject")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse deleteProject(
            @RequestParam(value = "projectId") Integer projectId) {
            try
            {
                return AjaxResponse.createSuccessResponse(this.service.deleteProject(projectId));
            } catch (ServiceException e) {
                return AjaxResponse.createFailResponse(e.getMessage());
            }
    }
    
    
    @RequestMapping("/yearPlan")
    @Login(required = true)
    public ModelAndView yearPlan(@RequestParam("projectId") int projectId) {
        ModelAndView mv = new ModelAndView("/features/PmProject/PmProject_YearPlan");
        mv.addObject("projectId", projectId);
        PmProjectModel p;
        try
        {
            p = this.service.queryForModify(projectId);
        }
        catch (ServiceException e)
        {
            return new ErrorPageBuilder()
            .setErrorMessageText(e.getMessage())
            .setErrorTitleText(e.getMessage())
            .create();
        }
        mv.addObject("projectName", p.getProjectName());
        return mv;
    }
    
    @RequestMapping("/listPlan")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse listPlan(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
        return AjaxResponse.createSuccessResponse(this.service.listPlan(projectId, start, length));
    }
    
    
    @RequestMapping("/approvePlan")
    @Login(required = true)
    public ModelAndView approvePlan(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value = "approveResult") String approveResult,
            @RequestParam(value = "approveStatus") String approveStatus,
            @RequestParam(value = "planId") Integer planId)
    {
        ModelAndView mv = new ModelAndView("redirect:/features/PmProject/yearPlan?projectId="+projectId);
        try
        {
            if(approveStatus.equals("1")){
                this.service.approvePlan(planId,approveResult,true);
            }else{
                this.service.approvePlan(planId,approveResult,false);
            }
           
        }
        catch (ServiceException e)
        {
            return new ErrorPageBuilder()
            .setErrorMessageText(e.getMessage())
            .setErrorTitleText(e.getMessage())
            .create();
        }
        return mv;
    }
    
    @RequestMapping("/monthReport")
    @Login(required = true)
    public ModelAndView monthReport(@RequestParam("projectId") int projectId,@RequestParam(value = "month", required=false) Integer month,@RequestParam(value = "year", required=false) Integer year) {
        if(month == null || year == null){
            month = Calendar.getInstance().get(Calendar.MONTH);
            month = month + 1;
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        
        List<Integer> yearList = new ArrayList<Integer>();
        Integer nowYear  = Calendar.getInstance().get(Calendar.YEAR);
        for(;nowYear >= 2010;){
            yearList.add(nowYear);
            nowYear --;
        }
        PmMonthReport r = this.service.getMonthReport(projectId,year,month);
        PmProject p = this.service.getDetail(projectId);
        PmYearPlan yp = this.service.getYearPlan(projectId,year);
        ModelAndView mv = new ModelAndView();
        mv.addObject("yearList", yearList);
        mv.addObject("projectId", projectId);
        mv.addObject("project", p);
        mv.addObject("yearPlan", yp);
        Double yit  = this.service.getYearInvestedTotal(projectId,year);
        mv.addObject("yearInvestedTotal", yit);
        if(r == null){
            r = this.service.addMonthReport(projectId,year,month);
        }
        if(r != null && r.getReportStatus() != 0 ){
            mv.setViewName("/features/PmProject/PmProject_MonthReportView");
            mv.addObject("year", year);
            mv.addObject("month", month);
            mv.addObject("report", r);
        }else{
            mv.setViewName("/features/PmProject/PmProject_MonthReportAdd");
            mv.addObject("year", year);
            mv.addObject("month", month);
            mv.addObject("report", r);
        }
        List<Map<String,Object>> rs = this.service.getMonthListInfo(projectId,year);
        mv.addObject("monthListInfo", rs);
        return mv;
    }
    
    @RequestMapping("/addMonthInvest")
    @Login(required = true)
    @ResponseBody
    public AjaxResponse  addMonthInvest(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value = "reportId") Integer reportId,
            @RequestParam(value = "comeTypeId") Integer comeTypeId,
            @RequestParam(value = "comeTypeName") String comeTypeName,
            @RequestParam(value = "comeFrom") String comeFrom,
            @RequestParam(value = "amount") Double amount,
            @RequestParam(value = "month") Integer month)
    {
        try
        {
            this.service.addMonthInvest(projectId, reportId,comeTypeId, comeTypeName, comeFrom, amount,month);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    @RequestMapping("/submitReport")
    @Login(required = true)
    @ResponseBody
    public AjaxResponse  submitReport(
            @RequestParam(value = "reportId") Integer reportId,
            @RequestParam(value = "investedNum") Double investedNum,
            @RequestParam(value = "useDesign") Double useDesign,
            @RequestParam(value = "useOversee") Double useOversee,
            @RequestParam(value = "useEngineer") Double useEngineer,
            @RequestParam(value = "useBuy") Double useBuy,
            @RequestParam(value = "useAsset") Double useAsset,
            @RequestParam(value = "useTotal") Double useTotal,
            @RequestParam(value = "current") String current,
            @RequestParam(value = "asset") String asset,
            @RequestParam(value = "difficult") String difficult,
            @RequestParam(value = "difficultOrg") String difficultOrg,
            @RequestParam(value = "comment") String comment,
            @RequestParam(value = "approveInfo") String approveInfo)
    {
        try
        {
            this.service.submitReport(reportId, investedNum,useDesign, useOversee, useEngineer, useBuy,useAsset,useTotal,current,asset,difficult,difficultOrg,comment,approveInfo);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    
    
    @RequestMapping("/deleteInvest")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse deleteInvest(
            @RequestParam(value = "investId") Integer investId) {
        return AjaxResponse.createSuccessResponse(this.service.deleteInvest(investId));
    }
    
    @RequestMapping("/listMonthInvest")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse listMonthInvest(
            @RequestParam(value = "projectId") Integer projectId,@RequestParam("reportId") Integer reportId) {
        return AjaxResponse.createSuccessResponse(this.service.listMonthInvest(projectId,reportId));
    }
    
    
    
    @RequestMapping("/deletePlan")
    @Login(required = true)
    @ResponseBody
    public AjaxResponse deletePlan(
            @RequestParam(value = "planId") Integer planId)
    {
        try
        {
            this.service.deletePlan(planId);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping("/exportExcel")
    public ResponseEntity<byte[]> checkinDetailExport(@RequestParam("projects") String projects) {
        String curYear = new SimpleDateFormat("yyyy").format(new Date());
        Object title = new String("岳麓区"+curYear+"年建设项目投资计划表");
        Object unit = new String("单位：万元");
        
        try (ExportHelper exporter = new ExportHelper(title,unit)) {
            exporter.addHeaders(Arrays.asList("序号", "项目名称", "建设性质", "建设规模和内容", "起止年限", "总投资规模", curYear+"年计划投资", "资金来源投资主体", "实施主体",
                    curYear+"年主要建设内容", "责任单位", "责任人", "联系领导", 
                    "本年完成投资", "统计入库项目名称", "当前工程建设形象进度", "征地拆迁情况", "前期手续办理情况", "存在困难及问题", "问题涉及单位", "备注及建议"));
            if (projects != null) {
                String[] pIds = projects.split(",");
                Integer i = 0;
                DecimalFormat df=new DecimalFormat("#0.00");
                
                for (String pId : pIds) {
                    i++;
                   PmProject p =  this.service.getDetail(Integer.valueOf(pId));
                   PmYearPlan pp = this.service.getYearPlan(Integer.valueOf(pId), Integer.valueOf(curYear));
                 //  List<PmMonthReport> mrs =  this.service.getMonthReports(Integer.valueOf(pId), Integer.valueOf(curYear));
                   PmMonthReport mr =  this.service.getLastMonthReport(Integer.valueOf(pId));
                   String docTypes =  this.service.getDocTypes(Integer.valueOf(pId));
                   exporter.addRow(Arrays.asList((Object) i, p.getProjectName(), p.getProjectType() , p.getProjectContent(),p.getBeginEnd(),df.format(p.getInvestTotal()),
                            pp == null ? "": df.format(pp.getAmount()),pp == null ? "":pp.getMainActor(),p.getMainActor(),pp == null ? "":pp.getContent(),p.getDutyOrg(),p.getDutyMan(),p.getContactLead(),
                                    df.format(this.service.getYearInvestedTotal(Integer.valueOf(pId),Integer.valueOf(curYear))),p.getRepoName(),
                                    mr == null ? "":mr.getCurrent(),mr == null ? "":mr.getAsset(),docTypes,mr == null ? "":mr.getDifficult(),mr == null ? "":mr.getDifficultOrg(),"")
                    );
                }
            }

           
            return exporter.export("projectExport.xlsx", "项目导出.xlsx");

        } catch (ServiceException e) {
            throw new RuntimeException("导出失败", e);

        } catch (IOException e) {
            throw new RuntimeException("导出失败", e);
        }
    }
   
    
}
