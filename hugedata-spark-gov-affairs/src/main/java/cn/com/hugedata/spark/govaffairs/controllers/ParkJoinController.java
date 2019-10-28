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
import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.govaffairs.services.ParkJoinService;

import java.text.SimpleDateFormat;

/**
 * 入园指南/入园申请.
 */
@Controller
@RequestMapping("/parkJoin")
public class ParkJoinController {
    
    @Autowired
    private ParkJoinService parkJoinService;

    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    /**
     * 进入入园指南列表页.
     */
    @RequestMapping("/listGuide")
    @Login(required = false)
    public ModelAndView listGuide() {
        ModelAndView mv = new ModelAndView("/spark/parkJoin/parkJoin_guideList");
        mv.addObject("menuId", "901");
        return mv;
    }
    
    /**
     * 入园指南列表.
     */
    @RequestMapping("/listGuideData")
    @ResponseBody
    @Login(required = false)
    public PageEntity<GaParkJoinGuide> listGuideData(
            @RequestParam("start") Integer start, 
            @RequestParam("length") Integer length) {
        return parkJoinService.list(start, length);
    }

    /**
     * 入园指南详情页.
     */
    @RequestMapping("/guideDetails")
    @Login(required = false)
    public ModelAndView guideDetails(@RequestParam("guideId") Integer guideId) {
        GaParkJoinGuide guide = parkJoinService.queryGuide(guideId);
        
        ModelAndView mv = new ModelAndView("/spark/parkJoin/parkJoin_guideDetails");
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
     * 入园申请页.
     */
    @RequestMapping("/apply")
    @ResponseBody
    public ModelAndView apply() {
        ModelAndView mv = new ModelAndView("/spark/parkJoin/parkJoin_apply");
        mv.addObject("menuId", "901");
        return mv;
    }
    
    /**
     * 提交入园申请.
     */
    @RequestMapping("/submitApply")
    @ResponseBody
    public AjaxResponse submitApply(@RequestParam("data") String dataJson) {
        try {
            parkJoinService.submitParkJoinApply(dataJson);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
}
