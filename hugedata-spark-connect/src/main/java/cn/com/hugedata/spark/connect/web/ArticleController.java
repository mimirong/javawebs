package cn.com.hugedata.spark.connect.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleMapper;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    PtArticleMapper ptArticleMapper;

    @RequestMapping("/detail/{articleId}")
    public ModelAndView detail(@PathVariable("articleId") Integer articleId)    {

        ModelAndView mv = new ModelAndView("/article");
        PtArticle article = ptArticleMapper.selectById(articleId);
        mv.addObject("article",article);
        return mv;
    }

}
