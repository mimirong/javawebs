package cn.com.hugedata.spark.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.management.services.update.Update20180124;

@Controller
@RequestMapping("/update")
public class UpdateController {
    
    @Autowired
    private Update20180124 update20180124;

    @RequestMapping(value = "/update20180124", produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String update20170124() {
        update20180124.update();
        return "OK";
    }
}
