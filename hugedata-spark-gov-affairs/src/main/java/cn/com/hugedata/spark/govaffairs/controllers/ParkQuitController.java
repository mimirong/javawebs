package cn.com.hugedata.spark.govaffairs.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitGuide;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.govaffairs.services.ParkQuitService;

import java.text.SimpleDateFormat;

/**
 * 退园指南/退园申请.
 */
@Controller
@RequestMapping("/parkQuit")
public class ParkQuitController {
    
    @Autowired
    private ParkQuitService parkQuitService;

    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    /**
     * 进入退园指南列表页.
     */
    @RequestMapping("/listGuide")
    @Login(required = false)
    public ModelAndView listGuide() {
        ModelAndView mv = new ModelAndView("/spark/parkQuit/parkQuit_guideList");
        mv.addObject("menuId", "901");
        return mv;
    }
    
    /**
     * 退园指南列表.
     */
    @RequestMapping("/listGuideData")
    @ResponseBody
    @Login(required = false)
    public PageEntity<GaParkQuitGuide> listGuideData(
            @RequestParam("start") Integer start, 
            @RequestParam("length") Integer length) {
        return parkQuitService.list(start, length);
    }

    /**
     * 退园指南详情页.
     */
    @RequestMapping("/guideDetails")
    @Login(required = false)
    public ModelAndView guideDetails(@RequestParam("guideId") Integer guideId) {
        GaParkQuitGuide guide = parkQuitService.queryGuide(guideId);
        
        ModelAndView mv = new ModelAndView("/spark/parkQuit/parkQuit_guideDetails");
        mv.addObject("menuId", "901");
        mv.addObject("guide", guide);
        if(guide.getPublishTime() != null){
            String publishTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(guide.getPublishTime());
            mv.addObject("publishTimeStr", publishTimeStr);
        }
        if (guide != null && StringUtils.isNotEmpty(guide.getAttachmentFileId())) {
            String url = fileUrlHelperService.fixDownloadUrl(guide.getAttachmentFileId(), guide.getAttachmentFileName());
            mv.addObject("attachmentUrl", url);
        }
        return mv;
    }
    
    /**
     * 退园申请页.
     */
    @RequestMapping("/apply")
    @ResponseBody
    public ModelAndView apply() {
        ModelAndView mv = new ModelAndView("/spark/parkQuit/parkQuit_apply");
        mv.addObject("menuId", "901");
        return mv;
    }
    
    /**
     * 提交退园申请.
     */
    @RequestMapping("/submitApply")
    @ResponseBody
    public AjaxResponse submitApply(@RequestParam("data") String dataJson) {
        try {
            parkQuitService.submitParkQuitApply(dataJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
}
