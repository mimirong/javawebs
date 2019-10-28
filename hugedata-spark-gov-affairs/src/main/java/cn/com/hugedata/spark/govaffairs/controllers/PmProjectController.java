/**  
* @Title:  PmProjectController.java
* @Package cn.com.hugedata.spark.govaffairs.controllers
* @Description: TODO(用一句话描述该文件做什么)
* @author QianQingzhao
* @date  2017年11月15日 下午3:51:04
* @version V1.0  
* Update Logs:
* ****************************************************
* Name:
* Date:
* Description:
******************************************************
*/
package cn.com.hugedata.spark.govaffairs.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthInvest;
import cn.com.hugedata.spark.commons.storage.entity.PmMonthReport;
import cn.com.hugedata.spark.commons.storage.entity.PmProject;
import cn.com.hugedata.spark.commons.storage.entity.PmYearPlan;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.ErrorPageBuilder;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.govaffairs.services.PmProjectService;

/**
 * @ClassName: PmProjectController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author QianQingzhao
 * @date 2017年11月15日 下午3:51:04
 *
 */
@Controller
@RequestMapping("/pmProject")
public class PmProjectController
{
    @Autowired
    private PmProjectService pmProjectService;
    
    @RequestMapping("/list")
    @Login(required = true)
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("/spark/pmProject/list");
        mv.addObject("menuId", "300");
        return mv;
    }
    
    
    @RequestMapping("/listData")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse listData(@RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
        return AjaxResponse.createSuccessResponse(pmProjectService.list(keyword, start, length));
    }
    
    @RequestMapping("/detail")
    @Login(required = true)
    public ModelAndView detail(@RequestParam("projectId") Integer projectId) {
        ModelAndView mv = new ModelAndView("/spark/pmProject/detail");
        mv.addObject("menuId", "300");
        PmProject p = pmProjectService.getDetail(projectId);
        mv.addObject("project", p);
        List<PmMonthInvest> is = pmProjectService.getMonthInvests(projectId);
        mv.addObject("monthInvests", is);
        Double yit  = pmProjectService.getInvestedTotal(projectId);
        mv.addObject("investedTotal", yit);
        return mv;
    }
    
    @RequestMapping("/detailEdit")
    @Login(required = true)
    public ModelAndView detailEdit(@RequestParam("projectId") Integer projectId) {
        ModelAndView mv = new ModelAndView("/spark/pmProject/detailEdit");
        PmProject p = pmProjectService.getDetail(projectId);
        mv.addObject("project", p);
        mv.addObject("menuId", "300");
        return mv;
    }
    
    
    @RequestMapping("/modify")
    @ResponseBody
    public AjaxResponse modify(@RequestParam("projectId") int projectId,@RequestParam("projectName") String projectName ,@RequestParam("projectCode") String projectCode ,@RequestParam("projectAddr") String projectAddr ,@RequestParam("projectArea") String projectArea ,@RequestParam("projectType") String projectType ,@RequestParam("projectContent") String projectContent)  {
        try {
            pmProjectService.modify(projectId,projectName,projectCode,projectAddr,projectArea,projectType,projectContent);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    @RequestMapping("/doc1")
    @Login(required = true)
    public ModelAndView doc1(@RequestParam("projectId") int projectId,
            @RequestParam(value="rd",required=false) Long rd) {
        ModelAndView mv = new ModelAndView("/spark/pmProject/doc1");
        mv.addObject("projectId", projectId);
        mv.addObject("projectName", pmProjectService.getDetail(projectId).getProjectName());
        mv.addObject("menuId", "300");
        mv.addObject("docTypes", pmProjectService.getDocTypes("1"));
        return mv;
    }
    
    @RequestMapping("/doc2")
    @Login(required = true)
    public ModelAndView doc2(@RequestParam("projectId") int projectId,
            @RequestParam(value="rd",required=false) Long rd) {
        ModelAndView mv = new ModelAndView("/spark/pmProject/doc2");
        mv.addObject("projectId", projectId);
        mv.addObject("projectName", pmProjectService.getDetail(projectId).getProjectName());
        mv.addObject("menuId", "300");
        mv.addObject("docTypes", pmProjectService.getDocTypes("2"));
        return mv;
    }
    
    @RequestMapping("/yearPlan")
    @Login(required = true)
    public ModelAndView yearPlan(@RequestParam("projectId") int projectId) {
        ModelAndView mv = new ModelAndView("/spark/pmProject/yearPlan");
        mv.addObject("projectId", projectId);
        mv.addObject("projectName", pmProjectService.getDetail(projectId).getProjectName());
        mv.addObject("menuId", "300");
        return mv;
    }
    
    @RequestMapping("/monthReport")
    @Login(required = true)
    public ModelAndView monthReport(@RequestParam("projectId") int projectId,@RequestParam(value = "month", required=false) Integer month
            ,@RequestParam(value = "year", required=false) Integer year) {
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
        PmMonthReport r = pmProjectService.getMonthReport(projectId,year,month);
        PmProject p = pmProjectService.getDetail(projectId);
        PmYearPlan yp = pmProjectService.getYearPlan(projectId,year);
        ModelAndView mv = new ModelAndView();
        mv.addObject("yearList", yearList);
        mv.addObject("projectId", projectId);
        mv.addObject("project", p);
        mv.addObject("yearPlan", yp);
        Double yit  = pmProjectService.getYearInvestedTotal(projectId,year);
        mv.addObject("yearInvestedTotal", yit);
        if(r != null && r.getReportStatus() != -1 ){
            mv.setViewName("/spark/pmProject/monthReportView");
            mv.addObject("year", year);
            mv.addObject("month", month);
            mv.addObject("report", r);
        }else{
            if(r == null){
                r = pmProjectService.addMonthReport(projectId,year,month);
            }
            mv.setViewName("/spark/pmProject/monthReportAdd");
            mv.addObject("year", year);
            mv.addObject("month", month);
            mv.addObject("report", r);
        }
        List<Map<String,Object>> rs = pmProjectService.getMonthListInfo(projectId,year);
        mv.addObject("monthListInfo", rs);
        mv.addObject("menuId", "300");
        return mv;
    }
    
    @RequestMapping("/addDoc")
    @Login(required = true)
    public ModelAndView addDoc(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value = "typeCode") String typeCode,
            @RequestParam(value = "docType") Integer docType,
            @RequestParam(value = "fileContent") CommonsMultipartFile fileContent,
            @RequestParam(value = "fileName") String fileName,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "approveCode", required=false) String approveCode)
    {
        ModelAndView mv = new ModelAndView();
        Date now = new Date();
        if(docType == 1){
            mv.setViewName("redirect:/pmProject/doc1?projectId="+projectId+"&rd="+now.getTime());
        }else{
            mv.setViewName("redirect:/pmProject/doc2?projectId="+projectId+"&rd="+now.getTime());
        }
        
        try
        {
            pmProjectService.addDoc(projectId, docType,typeCode,typeName, fileName, approveCode, fileContent);
        }
        catch (ServiceException e)
        {
            return new ErrorPageBuilder()
            .setErrorMessageText(e.getMessage())
            .setErrorTitleText(e.getMessage())
            .create();
        }
        mv.addObject("menuId", "300");
        return mv;
    }
    
    @RequestMapping("/checkDocTypeExist")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse checkDocTypeExist(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value = "typeCodeBegin", defaultValue = "100") String typeCodeBegin,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "docId" , required = false) Integer docId) {
        try
        {
            if(docId == null){
                //ZHYQ-1137 项目管理-手续文件、文档资料，前台和后台，添加完一条记录之后，就不可以再对这个类型的进行添加了，只能修改；而且添加之后，空记录就不要再展示了
                //新增
                pmProjectService.checkAddDocTypeExist(projectId,typeCodeBegin,typeName);
            }else{
                //修改
                pmProjectService.checkModifyDocTypeExist(projectId,docId,typeCodeBegin,typeName);
            }
                return AjaxResponse.createSuccessResponse();
        }
        catch (ServiceException e)
        {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    @RequestMapping("/modifyDoc")
    @Login(required = true)
    public ModelAndView modifyDoc(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value = "docId") Integer docId,
            @RequestParam(value = "docType") Integer docType,
            @RequestParam(value = "typeCode") String typeCode,
            @RequestParam(value = "fileContent" , required=false) CommonsMultipartFile fileContent,
            @RequestParam(value = "fileName") String fileName,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "approveCode", required=false) String approveCode)
    {
        ModelAndView mv = new ModelAndView();
        if(docType == 1){
            mv.setViewName("redirect:/pmProject/doc1?projectId="+projectId);
        }else{
            mv.setViewName("redirect:/pmProject/doc2?projectId="+projectId);
        }
        
        try
        {
            pmProjectService.modifyDoc(docId,projectId, typeCode,typeName, fileName, approveCode, fileContent);
        }
        catch (ServiceException e)
        {
            return new ErrorPageBuilder()
            .setErrorMessageText(e.getMessage())
            .setErrorTitleText(e.getMessage())
            .create();
        }
        mv.addObject("menuId", "300");
        return mv;
    }
    
    
    
    @RequestMapping("/deleteInvest")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse deleteInvest(
            @RequestParam(value = "investId") Integer investId) {
        return AjaxResponse.createSuccessResponse(pmProjectService.deleteInvest(investId));
    }
    
    
    @RequestMapping("/addPlan")
    @Login(required = true)
    @ResponseBody
    public AjaxResponse  addPlan(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value = "planYear") String planYear,
            @RequestParam(value = "amount") Double amount,
            @RequestParam(value = "mainActor") String mainActor,
            @RequestParam(value = "content") String content)
    {
        try
        {
            pmProjectService.addPlan(projectId, planYear,amount, mainActor, content);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    @RequestMapping("/modifyPlan")
    @Login(required = true)
    @ResponseBody
    public AjaxResponse  modifyPlan(
            @RequestParam(value = "planId") Integer planId,
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value = "planYear") String planYear,
            @RequestParam(value = "amount") Double amount,
            @RequestParam(value = "mainActor") String mainActor,
            @RequestParam(value = "content") String content)
    {
        try
        {
            pmProjectService.modifyPlan(planId,projectId, planYear,amount, mainActor, content);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    
    
    @RequestMapping("/listDoc")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse listDoc(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam(value="rd",required=false) Long rd,
            @RequestParam(value = "typeCodeBegin", defaultValue = "100") String typeCodeBegin,
            @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
        return AjaxResponse.createSuccessResponse(pmProjectService.listDoc(projectId,typeCodeBegin, start, length));
    }
    
    
    @RequestMapping("/listMonthInvest")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse listMonthInvest(
            @RequestParam(value = "projectId") Integer projectId,@RequestParam("reportId") Integer reportId) {
        return AjaxResponse.createSuccessResponse(pmProjectService.listMonthInvest(projectId,reportId));
    }
    
    
    @RequestMapping("/listPlan")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse listPlan(
            @RequestParam(value = "projectId") Integer projectId,
            @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
        return AjaxResponse.createSuccessResponse(pmProjectService.listPlan(projectId, start, length));
    }
    
    @RequestMapping("/deleteDoc")
    @ResponseBody
    @Login(required = true)
    public AjaxResponse deleteDoc(
            @RequestParam(value = "docId") Integer docId) {
        return AjaxResponse.createSuccessResponse(pmProjectService.deleteDoc(docId));
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
            pmProjectService.addMonthInvest(projectId, reportId,comeTypeId, comeTypeName, comeFrom, amount,month);
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
            @RequestParam(value = "comment") String comment)
    {
        try
        {
            pmProjectService.submitReport(reportId, investedNum,useDesign, useOversee, useEngineer, useBuy,useAsset,useTotal,current,asset,difficult,difficultOrg,comment);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    
    

}
