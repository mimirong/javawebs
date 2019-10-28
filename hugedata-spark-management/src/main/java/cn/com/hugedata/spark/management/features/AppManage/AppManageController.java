package cn.com.hugedata.spark.management.features.AppManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.web.ErrorPageBuilder;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.commons.web.login.Login;

@Controller
@RequestMapping("/features/AppManage")
public class AppManageController
        extends FeatureControllerImpl<PtArticle, Integer, AppManageService, AppManageModel> {
    
    
    @RequestMapping("/message")
    @Login(required = true)
    public ModelAndView message() {
        ModelAndView mv = new ModelAndView("/features/AppManage/AppManage_Message");
        PtArticle p;
        try
        {
            p = this.service.queryArticle();
            mv.addObject("articleId", p.getArticleId());
        }
        catch (ServiceException e)
        {
            return new ErrorPageBuilder()
            .setErrorMessageText(e.getMessage())
            .setErrorTitleText(e.getMessage())
            .create();
        }
        return mv;
    }
    
}
