package cn.com.hugedata.spark.govaffairs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.web.login.Login;

/**
 * 综合服务.
 */
@Controller
@RequestMapping("/integrated")
public class IntegratedController {

    @RequestMapping("/index")
    @Login(required = false)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/spark/integrated/integrated_index");
        mv.addObject("menuId", 900);
        return mv;
    }
    
}
