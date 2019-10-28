package cn.com.hugedata.spark.management.features.OsMeetingTraining;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.OsMeetingTraining;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/features/OsMeetingTraining")
public class OsMeetingTrainingController
        extends FeatureControllerImpl<OsMeetingTraining, Integer, OsMeetingTrainingService, OsMeetingTrainingModel> {
    
    @RequestMapping("/setTrainingVisible")
    @ResponseBody
    public AjaxResponse setTrainingVisible(
            @RequestParam("trainingId") Integer trainingId,
            @RequestParam("visible") Boolean visible) {
        try {
            service.setTraingVisible(trainingId, visible);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 保存排序.
     * @param idList 排序的ID列表
     * @return       处理结果
     */
    @RequestMapping("/setOrder")
    @ResponseBody
    public AjaxResponse setOrder(
            @RequestParam("idlist[]") List<Integer> idList) {
        service.updateOrder(idList);
        return AjaxResponse.createSuccessResponse();
    }
    
}
