package cn.com.hugedata.spark.management.features.GaParkJoinApplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinApplication;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/GaParkJoinApplication")
public class GaParkJoinApplicationController
        extends FeatureControllerImpl<GaParkJoinApplication, Integer, GaParkJoinApplicationService, GaParkJoinApplicationModel> {
 
    @RequestMapping("/submitApprove")
    @ResponseBody
    public AjaxResponse submitApprove(
            @RequestParam("applicationId") Integer applicationId,
            @RequestParam("approveComment") String approveComment) {
        try {
            service.submitApprove(applicationId, approveComment);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

}
