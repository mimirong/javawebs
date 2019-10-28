package cn.com.hugedata.spark.management.features.OsIntellectual;

import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsIntellectualModel extends PtArticle implements FeatureModel {

    private static final long serialVersionUID = 1L;

    private String publishTimeStr;
    
    private String uploadImageData;
    
    private String uploadAttachmentData;
    
    @Override
    public String findModelId() {
        return "" + getArticleId();
    }

    @Override
    public String findModelName() {
        return getTitle();
    }

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
    }

    public String getUploadImageData() {
        return uploadImageData;
    }

    public void setUploadImageData(String uploadImageData) {
        this.uploadImageData = uploadImageData;
    }

    public String getUploadAttachmentData() {
        return uploadAttachmentData;
    }

    public void setUploadAttachmentData(String uploadAttachmentData) {
        this.uploadAttachmentData = uploadAttachmentData;
    }

}