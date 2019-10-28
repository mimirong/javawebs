package cn.com.hugedata.spark.management.features.GaParkQuitApplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitApplication;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.management.features.GaParkQuitApplication.GaParkQuitApplicationModel;
import cn.com.hugedata.spark.management.features.GaParkQuitApplication.GaParkQuitApplicationService;

@Controller
@RequestMapping("/features/GaParkQuitApplication")
public class GaParkQuitApplicationController extends
        FeatureControllerImpl<GaParkQuitApplication, Integer, GaParkQuitApplicationService, GaParkQuitApplicationModel> {

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
