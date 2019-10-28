package cn.com.hugedata.spark.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.management.services.CompanyQueryService;

/**
 * 公共服务.
 */
@Controller
@RequestMapping("/global")
public class GlobalController {

    @Autowired
    private CompanyQueryService companyQueryService;
    
    @RequestMapping("/queryCompanyNamesLike")
    @ResponseBody
    public AjaxResponse queryCompanyNamesLike(@RequestParam("keyword") String keyword) {
        List<String> names = companyQueryService.queryCompanyNamesLike(keyword);
        return AjaxResponse.createSuccessResponse(names);
    }
    
}
