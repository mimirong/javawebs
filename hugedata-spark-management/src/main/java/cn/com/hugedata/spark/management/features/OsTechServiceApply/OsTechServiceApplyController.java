package cn.com.hugedata.spark.management.features.OsTechServiceApply;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceApply;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/OsTechServiceApply")
public class OsTechServiceApplyController
        extends FeatureControllerImpl<OsTechServiceApply, Integer, OsTechServiceApplyService, OsTechServiceApplyModel> {

    @RequestMapping("/approve")
    @ResponseBody
    public AjaxResponse approve(@RequestParam("applyId") int applyId) {
        try {
            service.approve(applyId);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
}
