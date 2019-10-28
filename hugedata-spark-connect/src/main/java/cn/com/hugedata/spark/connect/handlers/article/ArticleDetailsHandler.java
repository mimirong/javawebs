package cn.com.hugedata.spark.connect.handlers.article;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.UrlManager;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.storage.entity.PtArticleAttachment;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.connect.BaseHandler;
import cn.com.hugedata.spark.connect.InterfaceResponse;

public class ArticleDetailsHandler extends BaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleDetailsHandler.class);

    @Autowired
    private PtArticleMapper ptArticleMapper;

    @Autowired
    private PtArticleAttachmentMapper ptArticleAttachmentMapper;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @Autowired
    private UrlManager urlManager;
    
    @Override
    public InterfaceResponse execute(JSONObject req) throws ServiceException {
        // 获取参数
        Integer articleId = req.getInteger("articleId");
        if (articleId == null) {
            LOGGER.error("Parameter articleId is empty.");
            throw new ServiceException("EmptyArticleId", "文章ID不能为空");
        }
        
        // 查询文章信息
        PtArticle article = ptArticleMapper.selectById(articleId);
        if (article == null) {
            LOGGER.error("Article id not found: {}", articleId);
            throw new ServiceException("ArticleNotFound", "文章不存在");
        }
        JSONObject articleObject = (JSONObject) JSON.toJSON(article);
        articleObject.put("detailUrl", urlManager.getConnectUrl() + "/article/detail/" + article.getArticleId());
        articleObject.put("coverFileUrl", fileUrlHelperService.fixDownloadUrl(article.getCoverFileId(), article.getCoverFileName()));
        articleObject.remove("idValue");
        articleObject.remove("idPropertyName");
        articleObject.remove("brief");
        articleObject.remove("content");
        articleObject.remove("extraData");
        articleObject.remove("publisherUserId");
        articleObject.remove("publisherName");

        // 附件信息
        List<PtArticleAttachment> attachments = ptArticleAttachmentMapper.selectByMap(MyBatisUtils.buildParameterMap(
                PtArticleAttachment.Fields.ARTICLE_ID, articleId,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PtArticleAttachment.Fields.SORT_INDEX, OrderItem.ASC))
        ));
        JSONArray attachmentsArray = new JSONArray();
        for (PtArticleAttachment att : attachments) {
            JSONObject obj = new JSONObject();
            obj.put("fileId", att.getFileId());
            obj.put("fileName", att.getFileName());
            obj.put("url", fileUrlHelperService.fixDownloadUrl(att.getFileId(), att.getFileName()));
            attachmentsArray.add(obj);
        }
        articleObject.put("attachments", attachmentsArray);
        
        // 返回
        JSONObject resp = new JSONObject();
        resp.put("article", articleObject);
        return InterfaceResponse.createSuccessResponse(resp);
    }

}
