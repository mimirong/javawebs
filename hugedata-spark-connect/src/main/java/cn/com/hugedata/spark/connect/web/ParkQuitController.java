package cn.com.hugedata.spark.connect.web;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitGuide;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.connect.web.services.ParkQuitGuideQueryService;

@Controller
@RequestMapping("/parkquit")
public class ParkQuitController {
    
    @Autowired
    private ParkQuitGuideQueryService parkQuitGuideQueryService;

    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    /**
     * 入园指南详情页.
     */
    @RequestMapping("/guideDetails")
    @Login(required = false)
    public ModelAndView guideDetails(@RequestParam("guideId") Integer guideId) {
        GaParkQuitGuide guide = parkQuitGuideQueryService.queryGuide(guideId);
        
        ModelAndView mv = new ModelAndView("/parkquit_guide");
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

}
