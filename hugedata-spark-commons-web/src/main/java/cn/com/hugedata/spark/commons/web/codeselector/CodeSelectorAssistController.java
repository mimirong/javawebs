package cn.com.hugedata.spark.commons.web.codeselector;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.web.AjaxResponse;

@Controller
@RequestMapping("/commons-web/code-selector")
public class CodeSelectorAssistController implements ApplicationContextAware {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeSelectorAssistController.class);

    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    @RequestMapping("/listAll")
    @ResponseBody
    public AjaxResponse listAll(@RequestParam("codeGroup") String codeGroup) {
        try {
             CodeSelectorDataProvider provider = findProvider(codeGroup);
             List<CodeInfo> list = provider.listAll();
             return AjaxResponse.createSuccessResponse(list);
         } catch (ServiceException e) {
             return AjaxResponse.createFailResponse(e.getMessage());
         }
    }
 
    private CodeSelectorDataProvider findProvider(String codeGroup) throws ServiceException {
        CodeSelectorDataProvider provider = CodeSelector.getProvider(codeGroup, applicationContext);
        if (provider == null) {
            LOGGER.error("Code group not registered: {}", codeGroup);
            throw new ServiceException(this.getClass().getSimpleName() + "-CodeGroupNotFound", "代码组不存在");
        }
        return provider;
    }
} 
