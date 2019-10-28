package cn.com.hugedata.spark.management.features.PtNoticeBanner;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.PtNoticeBanner;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.features.FeatureControllerImpl;

@Controller
@RequestMapping("/features/PtNoticeBanner")
public class PtNoticeBannerController 
        extends FeatureControllerImpl<PtNoticeBanner, Integer, PtNoticeBannerService, PtNoticeBannerModel> {

    @RequestMapping("/setImageVisible")
    @ResponseBody
    public AjaxResponse setImageVisible(
            @RequestParam("imageId") Integer imageId,
            @RequestParam("visible") Boolean visible) {
        try {
            service.setImageVisible(imageId, visible);
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
