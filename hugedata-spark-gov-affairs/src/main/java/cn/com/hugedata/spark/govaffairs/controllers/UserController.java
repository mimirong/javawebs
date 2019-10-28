package cn.com.hugedata.spark.govaffairs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.govaffairs.services.UserService;
import cn.com.hugedata.spark.govaffairs.services.UserService.UserAndCompanyInfo;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userCenter")
    public ModelAndView userCenter() {
        UcUserInfo login = LoginUtils.getCurrentLogin();
        UserAndCompanyInfo info = userService.queryUserAndCompanyInfo(login.getUserId());
        
        ModelAndView mv = new ModelAndView("/spark/user/user_center");
        mv.addObject("user", info.getUserInfo());
        if (info.getCompanyInfo() != null) {
            mv.addObject("company", info.getCompanyInfo());
            mv.addObject("companyTypeName", companyTypeText(info.getCompanyInfo().getCompanyType()));
        }
        return mv;
    }
    
    @RequestMapping("/modifyUserInfo")
    public ModelAndView modifyUserInfo() {
        UcUserInfo login = LoginUtils.getCurrentLogin();
        UserAndCompanyInfo info = userService.queryUserAndCompanyInfo(login.getUserId());
        
        ModelAndView mv = new ModelAndView("/spark/user/user_modifyUserInfo");
        mv.addObject("user", info.getUserInfo());
        if (info.getCompanyInfo() != null) {
            mv.addObject("company", info.getCompanyInfo());
            mv.addObject("companyTypeName", companyTypeText(info.getCompanyInfo().getCompanyType()));
        }
        return mv;
    }

    @RequestMapping("/doModifyUserInfo")
    @ResponseBody
    public AjaxResponse doModifyUserInfo(
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "companyType", required = false) String companyType,
            @RequestParam(value = "companyAddress", required = false) String companyAddress,
            @RequestParam(value = "organizationCode", required = false) String organizationCode,
            @RequestParam(value = "licenceCode", required = false) String licenceCode,
            @RequestParam(value = "representative", required = false) String representative,
            @RequestParam(value = "representativeId", required = false) String representativeId,
            @RequestParam(value = "contactName", required = false) String contactName,
            @RequestParam(value = "contactMobile", required = false) String contactMobile,
            @RequestParam(value = "contactEmail", required = false) String contactEmail) {
        try {
            UcUserInfo login = LoginUtils.getCurrentLogin();
            userService.modifyUser(login.getUserId(), password, companyName, companyType, companyAddress, organizationCode,
                    licenceCode, representative, representativeId, contactName, contactMobile, contactEmail);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 将企业类型转为文本.
     */
    private String companyTypeText(String val) {
        val = "" + val;
        switch (val) {
            case "1": return "行政单位";
            case "2": return "事业单位";
            case "3": return "企业单位";
            case "4": return "军事单位";
            case "5": return "其他单位";
            case "6": return "个人";
            default:  return "";
        }
    }
}
