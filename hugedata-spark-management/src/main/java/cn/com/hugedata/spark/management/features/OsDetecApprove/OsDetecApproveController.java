package cn.com.hugedata.spark.management.features.OsDetecApprove;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.DdDetectionApply;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 申请审批
 */
@Controller
@RequestMapping("/features/OsDetecApprove")
public class OsDetecApproveController
        extends FeatureControllerImpl<DdDetectionApply, Integer, OsDetecApproveService, OsDetecApproveModel> {

    @Autowired
    private OsDetecApproveService osDetecApproveService;

    /**
     * 提交审核
     * @param applyId
     * @param approveComment
     * @return
     */
    @RequestMapping("/submitApprove")
    @ResponseBody
    public AjaxResponse submitApprove(
            @RequestParam("applyId") Integer applyId,
            @RequestParam("approveComment") String approveComment) {
        try {
            osDetecApproveService.submitApprove(applyId, approveComment);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

}
