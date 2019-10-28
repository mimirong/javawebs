package cn.com.hugedata.spark.ums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.ums.services.weaver.WeaverLoginService;
import cn.com.hugedata.spark.ums.services.weaver.WeaverLoginService.WeaverLoginInfo;

/**
 * 用于登录OA的Controller.
 */
@Controller
@RequestMapping("/weaversso")
public class WeaverLoginController {

    @Autowired
    private WeaverLoginService weaverLoginService;
    
    @Autowired
    private UrlManager urlManager;
    
    /**
     * 进入OA跳转页.
     * @return
     */
    @RequestMapping("/go")
    public ModelAndView go() {
        try {
            WeaverLoginInfo info = weaverLoginService.queryLoginInfo(LoginUtils.getCurrentLogin());
            ModelAndView mv = new ModelAndView("/weaver/go_login");
            mv.addObject("data", info);
            return mv;
        } catch (ServiceException e) {
            ModelAndView mv = new ModelAndView("redirect:" + urlManager.getUmsUrl() + "/login");
            return mv;
        }
    }
    
}
