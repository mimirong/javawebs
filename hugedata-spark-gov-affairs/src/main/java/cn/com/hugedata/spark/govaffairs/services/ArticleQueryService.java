package cn.com.hugedata.spark.govaffairs.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.storage.entity.PtArticleAttachment;
import cn.com.hugedata.spark.commons.storage.entity.PtArticleImage;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleImageMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class ArticleQueryService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleQueryService.class);

    @Autowired
    private PtArticleMapper ptArticleMapper;
    
    @Autowired
    private PtArticleImageMapper ptArticleImageMapper;
    
    @Autowired
    private PtArticleAttachmentMapper ptArticleAttachmentMapper;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    /**
     * 查询文章列表.
     * @param categoryId 栏目ID
     * @param start      分页开始记录
     * @param length     每页记录数
     * @return           文章列表
     */
    public PageEntity<PtArticle> list(String categoryId, int start, int length, Boolean isNeedContent) {
        Map<String, Object> query = MyBatisUtils.buildParameterMap(
                PtArticle.Fields.CATEGORY_ID, categoryId,
                PtArticle.Fields.IS_VISIBLE, true,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PtArticle.Fields.PUBLISH_TIME, OrderItem.DESC),
                        new OrderItem(PtArticle.Fields.ARTICLE_ID, OrderItem.DESC)),
                PtArticle.Query.PUBLISH_TIME__END, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        );
        List<PtArticle> data = ptArticleMapper.selectByMap(query, new RowBounds(start, length));
        int count = ptArticleMapper.countByMap(query);

        if (!isNeedContent) {
            for (PtArticle article : data) {
                article.setContent("");
            }
        }
        
        return new PageEntity<>(data, start, length, count);
    } 

    /**
     * 查询文章详情.
     * @param articleId         文章ID
     * @return                  文章详情
     * @throws ServiceException 查询失败
     */
    public JSONObject query(int articleId) throws ServiceException {
        // 基本信息
        PtArticle article = ptArticleMapper.selectById(articleId);
        if (article == null) {
            LOGGER.error("Article id not found: {}", articleId);
            throw new ServiceException("ArticleNotFound", "文章不存在");
        }
        
        // 图片信息
        List<PtArticleImage> images = ptArticleImageMapper.selectByMap(MyBatisUtils.buildParameterMap(
                PtArticleImage.Fields.ARTICLE_ID, articleId,
                OrderItem.ORDER_KEY, Arrays.asList(new OrderItem(PtArticleImage.Fields.SORT_INDEX, OrderItem.ASC))
        ));
        JSONArray imagesArray = new JSONArray();
        for (PtArticleImage image : images) {
            JSONObject obj = new JSONObject();
            obj.put("fileId", image.getFileId());
            obj.put("fileName", image.getFileName());
            obj.put("url", fileUrlHelperService.fixDownloadUrl(image.getFileId()));
            imagesArray.add(obj);
        }
        
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
        
        // 返回
        JSONObject data = (JSONObject) JSON.toJSON(article);
        data.put("images", imagesArray);
        data.put("attachments", attachmentsArray);
        return data;
    }
}
