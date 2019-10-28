package cn.com.hugedata.spark.govaffairs.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.entity.PtHomeImage;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.govaffairs.services.HomeImageService;

@Controller
public class HomeController {
    
    @Autowired
    private HomeImageService homeImageService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @RequestMapping(value = { "/","/index" })
    @Login(required = false)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/spark/index/newIndex");
        return mv ;  
    }

    @RequestMapping("/queryHomeImages")
    @ResponseBody
    @Login(required = false)
    public AjaxResponse queryHomeImages() {
        List<PtHomeImage> images = homeImageService.queryHomeImages();
        List<JSONObject> respImages = new ArrayList<>();
        for (PtHomeImage image : images) {
            JSONObject obj = new JSONObject();
            obj.put("imageId", image.getImageId());
            obj.put("imageUrl", fileUrlHelperService.fixDownloadUrl(image.getFileId()));
            obj.put("linkUrl", image.getLinkUrl());
            respImages.add(obj);
        }
        return AjaxResponse.createSuccessResponse(respImages);
    }
}
