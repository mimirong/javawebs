package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PtArticleAttachment extends BaseEntity<Integer> {
    /**
     * attachmentId
     */
    private Integer attachmentId;

    /**
     * articleId
     */
    private String articleId;

    /**
     * categoryId
     */
    private String categoryId;

    /**
     * fileId
     */
    private String fileId;

    /**
     * fileName
     */
    private String fileName;

    /**
     * remark
     */
    private String remark;

    /**
     * sortIndex
     */
    private Integer sortIndex;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = 1006683276242611687L;

    /**
     * {@link #attachmentId}
     */
    public Integer getAttachmentId() {
        return attachmentId;
    }

    /**
     * {@link #attachmentId}
     */
    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    /**
     * {@link #articleId}
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * {@link #articleId}
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    /**
     * {@link #categoryId}
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * {@link #categoryId}
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    /**
     * {@link #fileId}
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * {@link #fileId}
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * {@link #fileName}
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * {@link #fileName}
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * {@link #remark}
     */
    public String getRemark() {
        return remark;
    }

    /**
     * {@link #remark}
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * {@link #sortIndex}
     */
    public Integer getSortIndex() {
        return sortIndex;
    }

    /**
     * {@link #sortIndex}
     */
    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    /**
     * {@link #createTime}
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * {@link #createTime}
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getIdPropertyName() {
        return "attachmentId";
    }

    @Override
    public Integer getIdValue() {
        return attachmentId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.attachmentId = id;
    }

    public static JSONObject toJSON(PtArticleAttachment e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<PtArticleAttachment> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PtArticleAttachment.toJSON(this);
    }

    public static class Fields {
        public static final String ATTACHMENT_ID = "attachmentId";

        public static final String ARTICLE_ID = "articleId";

        public static final String CATEGORY_ID = "categoryId";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String REMARK = "remark";

        public static final String SORT_INDEX = "sortIndex";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String ATTACHMENT_ID__NE = "ne_attachmentId";

        public static final String ATTACHMENT_ID__IN = "list_attachmentId";

        public static final String ATTACHMENT_ID__BEGIN = "begin_attachmentId";

        public static final String ATTACHMENT_ID__END = "end_attachmentId";

        public static final String ARTICLE_ID__NE = "ne_articleId";

        public static final String ARTICLE_ID__LIKE = "like_articleId";

        public static final String ARTICLE_ID__IN = "list_articleId";

        public static final String ARTICLE_ID__BEGIN = "begin_articleId";

        public static final String ARTICLE_ID__END = "end_articleId";

        public static final String CATEGORY_ID__NE = "ne_categoryId";

        public static final String CATEGORY_ID__LIKE = "like_categoryId";

        public static final String CATEGORY_ID__IN = "list_categoryId";

        public static final String CATEGORY_ID__BEGIN = "begin_categoryId";

        public static final String CATEGORY_ID__END = "end_categoryId";

        public static final String FILE_ID__NE = "ne_fileId";

        public static final String FILE_ID__LIKE = "like_fileId";

        public static final String FILE_ID__IN = "list_fileId";

        public static final String FILE_ID__BEGIN = "begin_fileId";

        public static final String FILE_ID__END = "end_fileId";

        public static final String FILE_NAME__NE = "ne_fileName";

        public static final String FILE_NAME__LIKE = "like_fileName";

        public static final String FILE_NAME__IN = "list_fileName";

        public static final String FILE_NAME__BEGIN = "begin_fileName";

        public static final String FILE_NAME__END = "end_fileName";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";

        public static final String SORT_INDEX__NE = "ne_sortIndex";

        public static final String SORT_INDEX__IN = "list_sortIndex";

        public static final String SORT_INDEX__BEGIN = "begin_sortIndex";

        public static final String SORT_INDEX__END = "end_sortIndex";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";
    }
}