package cn.com.hugedata.spark.govaffairs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.govaffairs.services.ArticleQueryService;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleQueryService articleQueryService;

    @RequestMapping("/listArticles")
    @ResponseBody
    @Login(required = false)
    public PageEntity<PtArticle> listData(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("start") Integer start, 
            @RequestParam("length") Integer length,
            @RequestParam(value = "isNeedContent", defaultValue = "false") Boolean isNeedContent) {
        return articleQueryService.list(categoryId, start, length, isNeedContent);
    }

    @RequestMapping("/details")
    @ResponseBody
    @Login(required = false)
    public AjaxResponse details(@RequestParam("articleId") Integer articleId) {
        try {
            JSONObject data = articleQueryService.query(articleId);
            return AjaxResponse.createSuccessResponse(data);
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
}
