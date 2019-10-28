package cn.com.hugedata.spark.ums.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = { "/", "/userCenter" })
    public ModelAndView userCenter() {
        ModelAndView mv = new ModelAndView("/user/user_center");
        return mv;
    }
    
}
