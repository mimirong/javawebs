package cn.com.hugedata.spark.admin.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = { "/", "/index" })
    public ModelAndView index() {
        return new ModelAndView("/home/welcome");
    }
    
    @RequestMapping("/home/welcome")
    public ModelAndView welcome() {
        return new ModelAndView("/home/welcome");
    }

    @RequestMapping("/empty")
    public ModelAndView empty() {
        return new ModelAndView("/home/empty");
    }
    
}
