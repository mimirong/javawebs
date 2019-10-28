package cn.com.hugedata.spark.management.features.OsTechImage;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/features/OsTechImage")
public class OsTechImageController
        extends FeatureControllerImpl<PtTechImage, Integer, OsTechImageService, OsTechImageModel> {

    @RequestMapping("/setTechImageVisible")
    @ResponseBody
    public AjaxResponse setTechImageVisible(
            @RequestParam("imageId") Integer imageId,
            @RequestParam("visible") Boolean visible) {
        try {
            service.setTechImageVisible(imageId, visible);
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
        service.updateTechImagesOrder(idList, categoryId);
        return AjaxResponse.createSuccessResponse();
    }

}
