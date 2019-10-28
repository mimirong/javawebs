package cn.com.hugedata.spark.govaffairs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.storage.entity.PtNoticeBanner;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.govaffairs.services.NoticeService;

/**
 * 通知公告
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    
    @RequestMapping(value = { "", "/", "/index" })
    @Login(required = false)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/spark/notice/notice_index");
        mv.addObject("menuId", "400");
        return mv;
    }
    
    @RequestMapping("/queryBannerImages")
    @Login(required = false)
    @ResponseBody
    public AjaxResponse queryBannerImages() {
        List<PtNoticeBanner> images = noticeService.queryBannerImages();
        return AjaxResponse.createSuccessResponse(images);
    }

    @RequestMapping("/articleList")
    @Login(required = false)
    public ModelAndView articleList(
            @RequestParam(value = "category", defaultValue = "NOTICE_NOTICE") String category) {
        ModelAndView mv = new ModelAndView("/spark/notice/notice_articleList");
        mv.addObject("menuId", findMenuIdFromCategoryId(category));
        mv.addObject("category", category);
        return mv;
    }

    @RequestMapping("/details")
    @Login(required = false)
    public ModelAndView articleDetails(
            @RequestParam("articleId") Integer articleId,
            @RequestParam("categoryId") String categoryId) {
        ModelAndView mv = new ModelAndView("/spark/notice/notice_articleDetails");
        mv.addObject("menuId", findMenuIdFromCategoryId(categoryId));
        mv.addObject("articleId", articleId);
        mv.addObject("categoryId", categoryId);
        return mv;
    }

    /**
     * 根据栏目ID获取当前菜单ID.
     */
    private String findMenuIdFromCategoryId(String categoryId) {
        switch (categoryId) {
            case "NOTICE_PICTURE_NEWS":
                return "400";
            case "NOTICE_NOTICE":
                return "400";
            default:
                return "400";
        }
    }
}
