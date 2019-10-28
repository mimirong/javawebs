package cn.com.hugedata.spark.connect.helpers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelperController {
    
    @RequestMapping(value = { "", "/" })
    @ResponseBody
    public String index() {
        return "OK";
    }

    @RequestMapping("/help")
    public ModelAndView help() {
        ModelAndView mv = new ModelAndView("/help");
        return mv;
    }
}
