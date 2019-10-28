package cn.com.hugedata.spark.management.features.OsDetecAchieveExhibit;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewInfo;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewService;
import cn.com.hugedata.spark.commons.service.preview.ImagePreviewTypes;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.storage.entity.PtArticleAttachment;
import cn.com.hugedata.spark.commons.storage.entity.PtArticleImage;
import cn.com.hugedata.spark.commons.storage.entity.PtCategory;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleAttachmentMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleImageMapper;
import cn.com.hugedata.spark.commons.storage.mapper.PtArticleMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.features.FeatureServiceImpl;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OsDetecAchieveExhibitService
        extends FeatureServiceImpl<PtArticle, Integer, OsDetecAchieveExhibitModel, PtArticleMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsDetecAchieveExhibitService.class);

    @Autowired
    private PtArticleImageMapper ptArticleImageMapper;

    @Autowired
    private PtArticleAttachmentMapper ptArticleAttachmentMapper;

    @Autowired
    private PtArticleMapper ptArticleMapper;

    @Autowired
    private ImagePreviewService imagePreviewService;

    @Override
    public PageEntity<PtArticle> list(Map<String, Object> query, int pageStart, int pageSize, List<OrderItem> orders) {
        query.put(PtArticle.Query.CATEGORY_ID__IN,
                Arrays.asList(PtCategory.OS_DETEC_ACHIEVE_EXHIBIT));

        orders = Arrays.asList(new OrderItem(PtArticle.Fields.ARTICLE_ID, OrderItem.DESC));

        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public PtArticle add(OsDetecAchieveExhibitModel model) throws ServiceException {
        parseDate(model);
        model.setPublisherUserId(LoginUtils.getCurrentLogin().getUserId());
        model.setPublisherName(LoginUtils.getCurrentLogin().getName());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setIsVisible(false);
        PtArticle article = super.add(model);

        saveImages(model, article.getArticleId(), article.getCategoryId());
        saveAttachments(model, article.getArticleId(), article.getCategoryId());

        return article;
    }

    @Override
    public void modify(OsDetecAchieveExhibitModel model) throws ServiceException {
        parseDate(model);
        model.setUpdateTime(new Date());
        super.modify(model);

        PtArticle article = queryArticle(model.getArticleId());

        saveImages(model, article.getArticleId(), article.getCategoryId());
        saveAttachments(model, article.getArticleId(), article.getCategoryId());
    }

    @Override
    public OsDetecAchieveExhibitModel queryForModify(Integer id) throws ServiceException {
        OsDetecAchieveExhibitModel model = super.queryForModify(id);
        loadImagesForModify(model, id);
        loadAttachmentsForModify(model, id);
        return model;
    }

    /**
     * 设置文章是否可见.
     * @param articleId         文章ID
     * @param visible           是否可见
     * @throws ServiceException 设置失败
     */
    public void setArticleVisible(int articleId, boolean visible) throws ServiceException {
        queryArticle(articleId);

        PtArticle update = new PtArticle();
        update.setArticleId(articleId);
        update.setIsVisible(visible);
        ptArticleMapper.updateSelectiveById(update);
    }

    /**
     * 处理Model中的日期.
     */
    private void parseDate(OsDetecAchieveExhibitModel model) throws ServiceException {
        try {
            if (StringUtils.isNotEmpty(model.getPublishTimeStr())) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                model.setPublishTime(fmt.parse(model.getPublishTimeStr()));
            }
        } catch (ParseException e) {
            throw new ServiceException("InvalidDateFormat", "发布日期不正确");
        }
    }

    /**
     * 保存图片信息.
     */
    private void saveImages(OsDetecAchieveExhibitModel model, int articleId, String categoryId) throws ServiceException {
        ptArticleImageMapper.deleteByMap(MyBatisUtils.buildParameterMap(
                PtArticleImage.Fields.ARTICLE_ID, "" + articleId
        ));
        JSONArray imageArray = JSON.parseArray(model.getUploadImageData());
        for (int i = 0; i < imageArray.size(); i++) {
            // 生成缩略图
            String fileId = imageArray.getJSONObject(i).getString("fileId");
            ImagePreviewInfo prev = imagePreviewService.createPreview(ImagePreviewTypes.ARTICLE, fileId);

            // 保存
            PtArticleImage image = new PtArticleImage();
            image.setArticleId("" + articleId);
            image.setCategoryId(categoryId);
            image.setFileId(prev.getOriginalFileId());
            image.setFileName(prev.getOriginalFileName());
            image.setPreviewFileId(prev.getPreviewFileId());
            image.setPreviewFileName(prev.getPreviewFileName());
            image.setSortIndex(i);
            image.setCreateTime(new Date());
            ptArticleImageMapper.insertSelective(image);

            // 第一张图设置为封面
            if (i == 0) {
                PtArticle update = new PtArticle();
                update.setArticleId(articleId);
                update.setCoverFileId(prev.getPreviewFileId());
                update.setCoverFileName(prev.getPreviewFileName());
                ptArticleMapper.updateSelectiveById(update);
            }
        }
    }

    /**
     * 保存附件信息.
     */
    private void saveAttachments(OsDetecAchieveExhibitModel model, int articleId, String categoryId) {
        ptArticleAttachmentMapper.deleteByMap(MyBatisUtils.buildParameterMap(
                PtArticleAttachment.Fields.ARTICLE_ID, articleId
        ));
        JSONArray array = JSON.parseArray(model.getUploadAttachmentData());
        for (int i = 0; i < array.size(); i++) {
            PtArticleAttachment att = new PtArticleAttachment();
            att.setArticleId("" + articleId);
            att.setCategoryId(categoryId);
            att.setFileId(array.getJSONObject(i).getString("fileId"));
            att.setFileName(array.getJSONObject(i).getString("fileName"));
            att.setSortIndex(i);
            att.setCreateTime(new Date());
            ptArticleAttachmentMapper.insertSelective(att);
        }
    }

    /**
     * 加载图片信息到Model.
     */
    private void loadImagesForModify(OsDetecAchieveExhibitModel model, Integer id) {
        List<PtArticleImage> images = ptArticleImageMapper.selectByMap(MyBatisUtils.buildParameterMap(
                PtArticleImage.Fields.ARTICLE_ID, id
        ));
        JSONArray array = new JSONArray();
        for (PtArticleImage image : images) {
            JSONObject obj = new JSONObject();
            obj.put("fileId", image.getFileId());
            obj.put("fileName", image.getFileName());
            array.add(obj);
        }
        model.setUploadImageData(array.toJSONString());
    }

    /**
     * 加载附件信息到Model.
     */
    private void loadAttachmentsForModify(OsDetecAchieveExhibitModel model, Integer id) {
        List<PtArticleAttachment> attachments = ptArticleAttachmentMapper.selectByMap(MyBatisUtils.buildParameterMap(
                PtArticleAttachment.Fields.ARTICLE_ID, id
        ));
        JSONArray array = new JSONArray();
        for (PtArticleAttachment att : attachments) {
            JSONObject obj = new JSONObject();
            obj.put("fileId", att.getFileId());
            obj.put("fileName", att.getFileName());
            array.add(obj);
        }
        model.setUploadAttachmentData(array.toJSONString());
    }
    
    /**
     * 查询文章信息.
     */
    private PtArticle queryArticle(int articleId) throws ServiceException {
        PtArticle article = ptArticleMapper.selectById(articleId);
        if (article == null) {
            LOGGER.error("Article id not found.");
            throw new ServiceException("ArticleNotFound", "文章信息不存在");
        }
        return article;
    }
}
