package cn.com.hugedata.spark.management.features.OsExpertsList;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.OsExpertsList;
import cn.com.hugedata.spark.commons.storage.entity.PtHomeImage;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import cn.com.hugedata.spark.management.features.PtHomeImage.PtHomeImageModel;
import cn.com.hugedata.spark.management.features.PtHomeImage.PtHomeImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/features/OsExpertsList")
public class OsExpertsListController
        extends FeatureControllerImpl<OsExpertsList, Integer, OsExpertsListService, OsExpertsListModel> {

    @RequestMapping("/setExpertVisible")
    @ResponseBody
    public AjaxResponse setExpertVisible(
            @RequestParam("expertId") Integer expertId,
            @RequestParam("visible") Boolean visible) {
        try {
            service.setExpertVisible(expertId, visible);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    /**
     * 保存图片排序.
     * @param idList 排序的ID列表
     * @return       处理结果
     */
    @RequestMapping("/setOrder")
    @ResponseBody
    public AjaxResponse setOrder(
            @RequestParam("idlist[]") List<Integer> idList,
            @RequestParam("categoryId") String categoryId) {
        service.updateImagesOrder(idList, categoryId);
        return AjaxResponse.createSuccessResponse();
    }

}
