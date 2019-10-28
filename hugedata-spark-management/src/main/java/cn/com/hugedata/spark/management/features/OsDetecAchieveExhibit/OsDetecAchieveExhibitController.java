package cn.com.hugedata.spark.management.features.OsDetecAchieveExhibit;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 成果展示
 */
@Controller
@RequestMapping("/features/OsDetecAchieveExhibit")
public class OsDetecAchieveExhibitController
        extends FeatureControllerImpl<PtArticle, Integer, OsDetecAchieveExhibitService, OsDetecAchieveExhibitModel> {
    
    @RequestMapping("/setArticleVisible")
    @ResponseBody
    public AjaxResponse setArticleVisible(
            @RequestParam("articleId") Integer articleId,
            @RequestParam("visible") Boolean visible) {
        try {
            service.setArticleVisible(articleId, visible);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
}
