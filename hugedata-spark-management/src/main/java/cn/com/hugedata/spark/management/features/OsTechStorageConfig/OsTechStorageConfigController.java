package cn.com.hugedata.spark.management.features.OsTechStorageConfig;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.web.AjaxResponse;

@Controller
@RequestMapping("/features/OsTechStorageConfig")
public class OsTechStorageConfigController {
    
    @Autowired
    private OsTechStorageConfigService osTechStorageConfigService;
    
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/features/OsTechStorageConfig/OsTechStorageConfig_Index");
        mv.addObject("config", osTechStorageConfigService.queryConfig());
        return mv;
    }

    @RequestMapping("/update")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public AjaxResponse update(HttpServletRequest request) {
        Map<String, String[]> allParams = request.getParameterMap();
        Map<String, String> config = new TreeMap<>();
        for (Entry<String, String[]> param : allParams.entrySet()) {
            config.put(param.getKey(), param.getValue()[0]);
        }
        
        osTechStorageConfigService.updateConfig(config);
        return AjaxResponse.createSuccessResponse();
    }
}
