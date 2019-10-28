package cn.com.hugedata.spark.govaffairs.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceApply;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceSpec;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.govaffairs.services.tech.TechComputingService;
import cn.com.hugedata.spark.govaffairs.services.tech.TechStorageService;

/**
 * 公共技术服务. 
 */
@Controller
@RequestMapping("/outsourcing/tech")
public class TechController {

    @Autowired
    private TechComputingService techComputingService;
    
    @Autowired
    private TechStorageService techStorageService;
    
    //==================================================================================================================
    // 云主机
    //==================================================================================================================
    
    /**
     * 云主机申请列表页.
     * @return ModelAndView
     */
    @RequestMapping("/computing/applyList")
    public ModelAndView computingApplyList() {
        ModelAndView mv = new ModelAndView("/spark/tech/tech_computing_applylist");
        mv.addObject("menuId", "905");
        return mv;
    }
    
    /**
     * 查询申请列表.
     * @return
     */
    @RequestMapping("/computing/queryApplyList")
    @ResponseBody
    public AjaxResponse computingQueryApplyList(
            @RequestParam("pageStart") Integer pageStart,
            @RequestParam("pageSize") Integer pageSize) {
        try {
            PageEntity<OsTechServiceApply> page = techComputingService.queryApplyList(
                    LoginUtils.getCurrentLogin(), pageStart, pageSize);
            return AjaxResponse.createSuccessResponse(page);
            
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 云主机套餐页.
     * @return ModelAndView
     */
    @RequestMapping("/computing/specs")
    public ModelAndView computingSpecs() {
        ModelAndView mv = new ModelAndView("/spark/tech/tech_computing_specs");
        mv.addObject("menuId", "905");
        return mv;
    }

    /**
     * 云主机申请.
     * @return
     */
    @RequestMapping("/computing/submitApply")
    @ResponseBody
    public AjaxResponse computingSubmitApply(
            @RequestParam("spec") String specJson,
            @RequestParam("serviceDuration") int serviceDuration,
            @RequestParam("amount") int amount) {
        try {
            techComputingService.submitApply(
                    LoginUtils.getCurrentLogin(), 
                    serviceDuration, 
                    amount, 
                    specJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 删除云主机申请.
     * @return
     */
    @RequestMapping("/computing/deleteApply")
    @ResponseBody
    public AjaxResponse computingDeleteApply(@RequestParam("applyId") int applyId) {
        try {
            techComputingService.deleteApply(applyId);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 查询云主机套餐信息.
     * @return
     */
    @RequestMapping("/computing/querySpecs")
    @ResponseBody
    public AjaxResponse computingQuerySpecs() {
        List<OsTechServiceSpec> specs = techComputingService.querySpecs();
        return AjaxResponse.createSuccessResponse(specs);
    }

    /**
     * 查询云存储申请配置信息.
     * @return
     */
    @RequestMapping("/computing/queryConfig")
    @ResponseBody
    public AjaxResponse computingQueryConfig() {
        Map<String, Object> config = techComputingService.queryConfig();
        return AjaxResponse.createSuccessResponse(config);
    }

    //==================================================================================================================
    // 云存储
    //==================================================================================================================

    /**
     * 云存储申请列表页.
     * @return ModelAndView
     */
    @RequestMapping("/storage/applyList")
    public ModelAndView storageApplyList() {
        ModelAndView mv = new ModelAndView("/spark/tech/tech_storage_applylist");
        mv.addObject("menuId", "905");
        return mv;
    }
    
    /**
     * 查询云存储申请列表.
     * @return
     */
    @RequestMapping("/storage/queryApplyList")
    @ResponseBody
    public AjaxResponse storageQueryApplyList(
            @RequestParam("pageStart") Integer pageStart,
            @RequestParam("pageSize") Integer pageSize) {
        try {
            // 查询并返回
            PageEntity<OsTechServiceApply> page = techStorageService.queryApplyList(
                    LoginUtils.getCurrentLogin(), pageStart, pageSize);
            return AjaxResponse.createSuccessResponse(page);
            
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 云存储申请页.
     * @return ModelAndView
     */
    @RequestMapping("/storage/specs")
    public ModelAndView storageSpecs() {
        ModelAndView mv = new ModelAndView("/spark/tech/tech_storage_specs");
        mv.addObject("menuId", "905");
        return mv;
    }

    /**
     * 云存储申请.
     * @return
     */
    @RequestMapping("/storage/submitApply")
    @ResponseBody
    public AjaxResponse storageSubmitApply(
            @RequestParam("size") String size,
            @RequestParam("serviceDuration") int serviceDuration,
            @RequestParam("amount") int amount,
            @RequestParam("totalPrice") int totalPrice) {
        try {
            techStorageService.submitApply(
                    LoginUtils.getCurrentLogin(), 
                    size,
                    serviceDuration, 
                    amount, 
                    totalPrice);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 删除云存储申请.
     * @return
     */
    @RequestMapping("/storage/deleteApply")
    @ResponseBody
    public AjaxResponse storageDeleteApply(@RequestParam("applyId") int applyId) {
        try {
            techStorageService.deleteApply(applyId);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 查询云存储申请配置信息.
     * @return
     */
    @RequestMapping("/storage/queryConfig")
    @ResponseBody
    public AjaxResponse storageQueryConfig() {
        Map<String, Object> config = techStorageService.queryConfig();
        return AjaxResponse.createSuccessResponse(config);
    }
}
