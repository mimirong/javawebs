package cn.com.hugedata.spark.govaffairs.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuideAttachment;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.govaffairs.services.ServiceGuideQueryService;
import cn.com.hugedata.spark.govaffairs.services.ServiceGuideQueryService.ServiceGuideData;

/**
 * 办事指南.
 * 
 * serviceGuide2全部为不需要复杂JS支持的代码，支持低版本浏览器
 */
@Controller
@RequestMapping("/serviceGuide")
public class ServiceGuideController2 {

    @Autowired
    private ServiceGuideQueryService serviceGuideQueryService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @RequestMapping("/list")
    @Login(required = false)
    public ModelAndView list(
            @RequestParam(value = "dept", defaultValue = "7") String deptCode,
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "length", defaultValue = "20") Integer length) {
        PageEntity<GaServiceGuide> data =serviceGuideQueryService.list(deptCode, start, length);
        ModelAndView mv = new ModelAndView("/spark/serviceGuide2/list");
        mv.addObject("deptCode", deptCode);
        mv.addObject("deptName", deptCodeToName(deptCode));
        mv.addObject("data", data);
        mv.addObject("start", start);
        mv.addObject("length", length);
        return mv;
    }

    @RequestMapping(value = { "/details", "/details1" })
    @Login(required = false)
    public ModelAndView details1(
            @RequestParam(value = "guideId") Integer guideId) {
        ServiceGuideData data = serviceGuideQueryService.queryServiceGuideData(guideId);
        data.getGuide().setConditions(data.getGuide().getConditions().replaceAll("\\n", "<br/>"));
        data.getGuide().setMaterial(data.getGuide().getMaterial().replaceAll("\\n", "<br/>"));
        data.getGuide().setProcess(data.getGuide().getProcess().replaceAll("\\n", "<br/>"));
        
        ModelAndView mv = new ModelAndView("/spark/serviceGuide2/details1");
        mv.addObject("guideId", guideId);
        mv.addObject("guide", data.getGuide());
        mv.addObject("attachments", data.getAttachments());
        mv.addObject("deptName", deptCodeToName(data.getGuide().getDeptCode()));
        return mv;
    }
    
    @RequestMapping("/details2")
    @Login(required = false)
    public ModelAndView details2(
            @RequestParam(value = "guideId") Integer guideId) {
        ServiceGuideData data = serviceGuideQueryService.queryServiceGuideData(guideId);
        data.getGuide().setConditions(data.getGuide().getConditions().replaceAll("\\n", "<br/>"));
        data.getGuide().setMaterial(data.getGuide().getMaterial().replaceAll("\\n", "<br/>"));
        data.getGuide().setProcess(data.getGuide().getProcess().replaceAll("\\n", "<br/>"));
        
        ModelAndView mv = new ModelAndView("/spark/serviceGuide2/details2");
        mv.addObject("guideId", guideId);
        mv.addObject("guide", data.getGuide());
        mv.addObject("attachments", data.getAttachments());
        mv.addObject("deptName", deptCodeToName(data.getGuide().getDeptCode()));
        
        if (StringUtils.isNotEmpty(data.getGuide().getFlowImageFileId())) {
            mv.addObject("flowImageUrl", fileUrlHelperService.fixDownloadUrl(data.getGuide().getFlowImageFileId(),
                    data.getGuide().getFlowImageFileName()));
        }
        
        return mv;
    }
    
    @RequestMapping("/details3")
    @Login(required = false)
    public ModelAndView details3(
            @RequestParam(value = "guideId") Integer guideId) {
        ServiceGuideData data = serviceGuideQueryService.queryServiceGuideData(guideId);
        data.getGuide().setConditions(data.getGuide().getConditions().replaceAll("\\n", "<br/>"));
        data.getGuide().setMaterial(data.getGuide().getMaterial().replaceAll("\\n", "<br/>"));
        data.getGuide().setProcess(data.getGuide().getProcess().replaceAll("\\n", "<br/>"));
        
        for (GaServiceGuideAttachment att : data.getAttachments()) {
            att.setPreviewFileId(fileUrlHelperService.fixDownloadUrl(att.getFileId(), att.getFileName()));
        }
        
        ModelAndView mv = new ModelAndView("/spark/serviceGuide2/details3");
        mv.addObject("guideId", guideId);
        mv.addObject("guide", data.getGuide());
        mv.addObject("attachments", data.getAttachments());
        mv.addObject("deptName", deptCodeToName(data.getGuide().getDeptCode()));
        return mv;
    }
    
    private String deptCodeToName(String code) {
        if (code == null) {
            return "";
        }
        switch (code) {
            case "1": return "经济发展局";
            case "2": return "国土规划局";
            case "3": return "工程建设局";
            case "4": return "社会事务局";
            case "5": return "招商合作局";
            case "6": return "财政分局";
            case "7": return "办公室";
            case "8": return "党群纪检绩效办";
            default:  return "";
        }
    }
    
}
