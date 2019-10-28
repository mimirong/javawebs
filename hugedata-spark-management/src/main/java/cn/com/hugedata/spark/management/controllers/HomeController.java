package cn.com.hugedata.spark.management.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.management.commons.ServerConstants;

@Controller
public class HomeController {

    /**
     * 显示欢迎页.
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/home/index");
        return mv;
    }
    
    /**
     * 显示空白页.
     * @return
     */
    @RequestMapping("/empty")
    public ModelAndView empty() {
        ModelAndView mv = new ModelAndView("/home/empty");
        return mv;
    }
    
    /**
     * 显示"没有权限"页面.
     * @return
     */
    @RequestMapping("/noPrivilege")
    public ModelAndView noPrivilege() {
        ModelAndView mv = new ModelAndView("/home/no_privilege");
        return mv;
    }
    
    /**
     * 切换后台管理系统.
     * @param systemType 系统类型
     * @return
     */
    @RequestMapping("/switchSystem")
    public ModelAndView switchSystem(
            @RequestParam("systemType") String systemType,
            HttpServletRequest request) {
        request.getSession().setAttribute(ServerConstants.ACTIVE_SYSTEM_TYPE_SESSION_KEY, systemType);
        return new ModelAndView("/home/switch_system");
    }
    
}
