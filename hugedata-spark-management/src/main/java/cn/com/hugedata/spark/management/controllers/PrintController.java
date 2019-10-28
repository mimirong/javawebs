package cn.com.hugedata.spark.management.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.management.services.PrintCacheService;
import cn.com.hugedata.spark.management.services.PrintCacheService.PrintData;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/print")
public class PrintController {

    @Autowired
    private PrintCacheService printCacheService;
    
    /**
     * 进入打印页面.
     * @param key 保存打印数据时返回的Key
     * @return    ModelAndView
     */
    @RequestMapping("/print")
    public ModelAndView print(@RequestParam("key") String key) {
        PrintData data = printCacheService.load(key);
        if (data != null && StringUtils.isNotEmpty(data.getData())) {
            ModelAndView mv = new ModelAndView("/print/print");
            mv.addObject("data", data.getData());
            mv.addObject("title", data.getTitle());
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("/print/print_error");
            mv.addObject("message", "无法获取待打印的数据");
            return mv;
        }
    }
    
    /**
     * 保存需要打印的数据.
     * @param title 打印页面标题
     * @param data  打印页面内容HTML
     * @return      处理结果
     */
    @RequestMapping("/prepare")
    @ResponseBody
    public AjaxResponse prepare(
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "data") String data) {
        String key = printCacheService.save(title, data);
        JSONObject resp = new JSONObject();
        resp.put("key", key);
        return AjaxResponse.createSuccessResponse(resp);
    }
}
