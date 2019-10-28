package cn.com.hugedata.spark.management.features.AppManage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class AppManageService
        extends FeatureServiceImpl<PtArticle, Integer, AppManageModel, PtArticleMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppManageService.class);

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
                Arrays.asList(PtCategory.HANDY_SERVICE_RESIDENCE,
                        PtCategory.HANDY_SERVICE_SCHOOL,
                        PtCategory.HANDY_SERVICE_HOSPITAL,
                        PtCategory.HANDY_SERVICE_BUS));

        orders = Arrays.asList(new OrderItem(PtArticle.Fields.ARTICLE_ID, OrderItem.DESC));

        return super.list(query, pageStart, pageSize, orders);
    }

    @Override
    public PtArticle add(AppManageModel model) throws ServiceException {
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
    public void modify(AppManageModel model) throws ServiceException {
        parseDate(model);
        model.setUpdateTime(new Date());
        super.modify(model);
        PtArticle article = queryArticle(model.getArticleId());
        saveAttachments(model, article.getArticleId(), article.getCategoryId());
    }

    public AppManageModel queryArticle() throws ServiceException {
        List<PtArticle> as = ptArticleMapper.selectByMap(MyBatisUtils.buildParameterMap(PtArticle.Fields.CATEGORY_ID,PtCategory.APP_MANAGE_MESSAGE));
        if(as != null && as.size() > 0){
            AppManageModel model = super.queryForModify(as.get(0).getArticleId());
            loadAttachmentsForModify(model, as.get(0).getArticleId());
            return model;
        }else{
            throw new ServiceException("APP_MANAGE_MESSAGE","消息送报须知数据未配置");
        }
    }
    
    
    
    

    @Override
    public AppManageModel queryForModify(Integer id) throws ServiceException
    {
        AppManageModel model = super.queryForModify(id);
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
    private void parseDate(AppManageModel model) throws ServiceException {
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
    private void saveImages(AppManageModel model, int articleId, String categoryId) throws ServiceException {
        ptArticleImageMapper.deleteByMap(MyBatisUtils.buildParameterMap(
                PtArticleImage.Fields.ARTICLE_ID, "" + articleId
        ));
        JSONArray imageArray = JSON.parseArray(model.getUploadImageData());
        // 如果没有图片需要删除封面
        if (imageArray == null || imageArray.size() == 0) {
            PtArticle update = new PtArticle();
            update.setArticleId(articleId);
            update.setCoverFileId("");
            update.setCoverFileName("");
            ptArticleMapper.updateSelectiveById(update);
        }
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
    private void saveAttachments(AppManageModel model, int articleId, String categoryId) {
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
    private void loadImagesForModify(AppManageModel model, Integer id) {
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
    private void loadAttachmentsForModify(AppManageModel model, Integer id) {
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
