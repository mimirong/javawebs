package cn.com.hugedata.spark.govaffairs.controllers;

import cn.com.hugedata.spark.commons.storage.entity.PtCategory;
import cn.com.hugedata.spark.commons.web.login.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 便民服务
 *
 */
@Controller
@RequestMapping("/handyService")
public class HandyServiceController {

    @RequestMapping(value = { "", "/", "/index" })
    @Login(required = false)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/spark/handyService/handyService_index");
        mv.addObject("menuId", "500");
        return mv;
    }

    @RequestMapping("/articleList")
    @Login(required = false)
    public ModelAndView articleList(
            @RequestParam(value = "category", defaultValue = "HANDY_SERVICE_RESIDENCE") String category) {
        ModelAndView mv = new ModelAndView("/spark/handyService/handyService_articleList");
        mv.addObject("menuId", findMenuIdFromCategoryId(category));
        mv.addObject("category", category);
        return mv;
    }

    @RequestMapping("/details")
    @Login(required = false)
    public ModelAndView articleDetails(
            @RequestParam("articleId") Integer articleId,
            @RequestParam("categoryId") String categoryId,
            @RequestParam(value = "isHideBack", required = false) Integer isHideBack) {
        ModelAndView mv = new ModelAndView("/spark/handyService/handyService_articleDetails");
        mv.addObject("menuId", findMenuIdFromCategoryId(categoryId));
        mv.addObject("articleId", articleId);
        mv.addObject("categoryId", categoryId);
        mv.addObject("isHideBack", isHideBack);
        return mv;
    }

    /**
     * 根据栏目ID获取当前菜单ID.
     */
    private String findMenuIdFromCategoryId(String categoryId) {
        switch (categoryId) {
            case PtCategory.HANDY_SERVICE_RESIDENCE:
                return "501";
            case PtCategory.HANDY_SERVICE_SCHOOL:
                return "502";
            case PtCategory.HANDY_SERVICE_HOSPITAL:
                return "503";
            case PtCategory.HANDY_SERVICE_BUS:
                return "504";
            case PtCategory.HANDY_SERVICE_BANK:
                return "505";
            default:
                return "501";
        }
    }

}
