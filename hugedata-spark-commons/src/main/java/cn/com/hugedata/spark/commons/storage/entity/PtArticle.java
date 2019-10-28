package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PtArticle extends BaseEntity<Integer> {
    /**
     * articleId
     */
    private Integer articleId;

    /**
     * categoryId
     */
    private String categoryId;

    /**
     * title
     */
    private String title;

    /**
     * brief
     */
    private String brief;

    /**
     * author
     */
    private String author;

    /**
     * source
     */
    private String source;

    /**
     * publisherUserId
     */
    private Integer publisherUserId;

    /**
     * publisherName
     */
    private String publisherName;

    /**
     * coverFileId
     */
    private String coverFileId;

    /**
     * coverFileName
     */
    private String coverFileName;

    /**
     * isVisible
     */
    private Boolean isVisible;

    /**
     * publishTime
     */
    private Date publishTime;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * content
     */
    private String content;

    /**
     * extraData
     */
    private String extraData;

    private static final long serialVersionUID = -5574005352188045040L;

    /**
     * {@link #articleId}
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * {@link #articleId}
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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
     * {@link #title}
     */
    public String getTitle() {
        return title;
    }

    /**
     * {@link #title}
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * {@link #brief}
     */
    public String getBrief() {
        return brief;
    }

    /**
     * {@link #brief}
     */
    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    /**
     * {@link #author}
     */
    public String getAuthor() {
        return author;
    }

    /**
     * {@link #author}
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * {@link #source}
     */
    public String getSource() {
        return source;
    }

    /**
     * {@link #source}
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * {@link #publisherUserId}
     */
    public Integer getPublisherUserId() {
        return publisherUserId;
    }

    /**
     * {@link #publisherUserId}
     */
    public void setPublisherUserId(Integer publisherUserId) {
        this.publisherUserId = publisherUserId;
    }

    /**
     * {@link #publisherName}
     */
    public String getPublisherName() {
        return publisherName;
    }

    /**
     * {@link #publisherName}
     */
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName == null ? null : publisherName.trim();
    }

    /**
     * {@link #coverFileId}
     */
    public String getCoverFileId() {
        return coverFileId;
    }

    /**
     * {@link #coverFileId}
     */
    public void setCoverFileId(String coverFileId) {
        this.coverFileId = coverFileId == null ? null : coverFileId.trim();
    }

    /**
     * {@link #coverFileName}
     */
    public String getCoverFileName() {
        return coverFileName;
    }

    /**
     * {@link #coverFileName}
     */
    public void setCoverFileName(String coverFileName) {
        this.coverFileName = coverFileName == null ? null : coverFileName.trim();
    }

    /**
     * {@link #isVisible}
     */
    public Boolean getIsVisible() {
        return isVisible;
    }

    /**
     * {@link #isVisible}
     */
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * {@link #publishTime}
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * {@link #publishTime}
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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

    /**
     * {@link #updateTime}
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * {@link #updateTime}
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * {@link #content}
     */
    public String getContent() {
        return content;
    }

    /**
     * {@link #content}
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * {@link #extraData}
     */
    public String getExtraData() {
        return extraData;
    }

    /**
     * {@link #extraData}
     */
    public void setExtraData(String extraData) {
        this.extraData = extraData == null ? null : extraData.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "articleId";
    }

    @Override
    public Integer getIdValue() {
        return articleId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.articleId = id;
    }

    public static JSONObject toJSON(PtArticle e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getPublishTime() != null) {
            obj.put("publishTimeStr", fmt.format(e.getPublishTime()));
        }
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<PtArticle> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PtArticle.toJSON(this);
    }

    public static class Fields {
        public static final String ARTICLE_ID = "articleId";

        public static final String CATEGORY_ID = "categoryId";

        public static final String TITLE = "title";

        public static final String BRIEF = "brief";

        public static final String AUTHOR = "author";

        public static final String SOURCE = "source";

        public static final String PUBLISHER_USER_ID = "publisherUserId";

        public static final String PUBLISHER_NAME = "publisherName";

        public static final String COVER_FILE_ID = "coverFileId";

        public static final String COVER_FILE_NAME = "coverFileName";

        public static final String IS_VISIBLE = "isVisible";

        public static final String PUBLISH_TIME = "publishTime";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String CONTENT = "content";

        public static final String EXTRA_DATA = "extraData";
    }

    public static class Query {
        public static final String ARTICLE_ID__NE = "ne_articleId";

        public static final String ARTICLE_ID__IN = "list_articleId";

        public static final String ARTICLE_ID__BEGIN = "begin_articleId";

        public static final String ARTICLE_ID__END = "end_articleId";

        public static final String CATEGORY_ID__NE = "ne_categoryId";

        public static final String CATEGORY_ID__LIKE = "like_categoryId";

        public static final String CATEGORY_ID__IN = "list_categoryId";

        public static final String CATEGORY_ID__BEGIN = "begin_categoryId";

        public static final String CATEGORY_ID__END = "end_categoryId";

        public static final String TITLE__NE = "ne_title";

        public static final String TITLE__LIKE = "like_title";

        public static final String TITLE__IN = "list_title";

        public static final String TITLE__BEGIN = "begin_title";

        public static final String TITLE__END = "end_title";

        public static final String BRIEF__NE = "ne_brief";

        public static final String BRIEF__LIKE = "like_brief";

        public static final String BRIEF__IN = "list_brief";

        public static final String BRIEF__BEGIN = "begin_brief";

        public static final String BRIEF__END = "end_brief";

        public static final String AUTHOR__NE = "ne_author";

        public static final String AUTHOR__LIKE = "like_author";

        public static final String AUTHOR__IN = "list_author";

        public static final String AUTHOR__BEGIN = "begin_author";

        public static final String AUTHOR__END = "end_author";

        public static final String SOURCE__NE = "ne_source";

        public static final String SOURCE__LIKE = "like_source";

        public static final String SOURCE__IN = "list_source";

        public static final String SOURCE__BEGIN = "begin_source";

        public static final String SOURCE__END = "end_source";

        public static final String PUBLISHER_USER_ID__NE = "ne_publisherUserId";

        public static final String PUBLISHER_USER_ID__IN = "list_publisherUserId";

        public static final String PUBLISHER_USER_ID__BEGIN = "begin_publisherUserId";

        public static final String PUBLISHER_USER_ID__END = "end_publisherUserId";

        public static final String PUBLISHER_NAME__NE = "ne_publisherName";

        public static final String PUBLISHER_NAME__LIKE = "like_publisherName";

        public static final String PUBLISHER_NAME__IN = "list_publisherName";

        public static final String PUBLISHER_NAME__BEGIN = "begin_publisherName";

        public static final String PUBLISHER_NAME__END = "end_publisherName";

        public static final String COVER_FILE_ID__NE = "ne_coverFileId";

        public static final String COVER_FILE_ID__LIKE = "like_coverFileId";

        public static final String COVER_FILE_ID__IN = "list_coverFileId";

        public static final String COVER_FILE_ID__BEGIN = "begin_coverFileId";

        public static final String COVER_FILE_ID__END = "end_coverFileId";

        public static final String COVER_FILE_NAME__NE = "ne_coverFileName";

        public static final String COVER_FILE_NAME__LIKE = "like_coverFileName";

        public static final String COVER_FILE_NAME__IN = "list_coverFileName";

        public static final String COVER_FILE_NAME__BEGIN = "begin_coverFileName";

        public static final String COVER_FILE_NAME__END = "end_coverFileName";

        public static final String IS_VISIBLE__NE = "ne_isVisible";

        public static final String IS_VISIBLE__IN = "list_isVisible";

        public static final String IS_VISIBLE__BEGIN = "begin_isVisible";

        public static final String IS_VISIBLE__END = "end_isVisible";

        public static final String PUBLISH_TIME__NE = "ne_publishTime";

        public static final String PUBLISH_TIME__IN = "list_publishTime";

        public static final String PUBLISH_TIME__BEGIN = "begin_publishTime";

        public static final String PUBLISH_TIME__END = "end_publishTime";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String CONTENT__NE = "ne_content";

        public static final String CONTENT__LIKE = "like_content";

        public static final String CONTENT__IN = "list_content";

        public static final String CONTENT__BEGIN = "begin_content";

        public static final String CONTENT__END = "end_content";

        public static final String EXTRA_DATA__NE = "ne_extraData";

        public static final String EXTRA_DATA__LIKE = "like_extraData";

        public static final String EXTRA_DATA__IN = "list_extraData";

        public static final String EXTRA_DATA__BEGIN = "begin_extraData";

        public static final String EXTRA_DATA__END = "end_extraData";
    }
}