package cn.com.hugedata.spark.ums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.ums.services.signup.SignUpService;

/**
 * 处理注册的Controller.
 */
@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;
    
    /**
     * 进入注册页面.
     * @return ModelAndView
     */
    @RequestMapping(value = { "", "/", "/signup" })
    @Login(required = false)
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView("/signup/signup");
        return mv;
    }
    
    /**
     * 显示注册协议.
     * @return ModelAndView
     */
    @RequestMapping("/agreement")
    @Login(required = false)
    public ModelAndView agreement() {
        ModelAndView mv = new ModelAndView("/signup/agreement");
        return mv;
    }
    
    /**
     * 注册完成.
     * @return ModelAndView
     */
    @RequestMapping("/signupFinish")
    @Login(required = false)
    public ModelAndView signupFinish() {
        ModelAndView mv = new ModelAndView("/signup/signup_finish");
        return mv;
    }
    
    /**
     * 注册提交.
     */
    @RequestMapping("/doSignUp")
    @Login(required = false)
    @ResponseBody
    public AjaxResponse doSignUp(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("companyName") String companyName,
            @RequestParam("companyType") String companyType,
            @RequestParam("companyAddress") String companyAddress,
            @RequestParam("organizationCode") String organizationCode,
            @RequestParam("licenceCode") String licenceCode,
            @RequestParam("representative") String representative,
            @RequestParam("representativeId") String representativeId,
            @RequestParam("contactName") String contactName,
            @RequestParam("contactMobile") String contactMobile,
            @RequestParam("contactEmail") String contactEmail) {
        try {
            signUpService.createUser(username, password, companyName, companyType, companyAddress, organizationCode,
                    licenceCode, representative, representativeId, contactName, contactMobile, contactEmail);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
}
