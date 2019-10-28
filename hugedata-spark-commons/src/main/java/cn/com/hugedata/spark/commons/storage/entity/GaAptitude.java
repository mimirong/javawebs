package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaAptitude extends BaseEntity<Integer> {
    /**
     * aptitudeId
     */
    private Integer aptitudeId;

    /**
     * title
     */
    private String title;

    /**
     * attachmentFileName
     */
    private String attachmentFileName;

    /**
     * attachmentFileId
     */
    private String attachmentFileId;

    /**
     * creatorUserId
     */
    private Integer creatorUserId;

    /**
     * creatorName
     */
    private String creatorName;

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
     * publisher
     */
    private String publisher;

    /**
     * publishDeptId
     */
    private Integer publishDeptId;

    /**
     * publishDeptName
     */
    private String publishDeptName;

    /**
     * content
     */
    private String content;

    private static final long serialVersionUID = 3610292899890248475L;

    /**
     * {@link #aptitudeId}
     */
    public Integer getAptitudeId() {
        return aptitudeId;
    }

    /**
     * {@link #aptitudeId}
     */
    public void setAptitudeId(Integer aptitudeId) {
        this.aptitudeId = aptitudeId;
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
     * {@link #attachmentFileName}
     */
    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    /**
     * {@link #attachmentFileName}
     */
    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName == null ? null : attachmentFileName.trim();
    }

    /**
     * {@link #attachmentFileId}
     */
    public String getAttachmentFileId() {
        return attachmentFileId;
    }

    /**
     * {@link #attachmentFileId}
     */
    public void setAttachmentFileId(String attachmentFileId) {
        this.attachmentFileId = attachmentFileId == null ? null : attachmentFileId.trim();
    }

    /**
     * {@link #creatorUserId}
     */
    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    /**
     * {@link #creatorUserId}
     */
    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    /**
     * {@link #creatorName}
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * {@link #creatorName}
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
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
     * {@link #publisher}
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * {@link #publisher}
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    /**
     * {@link #publishDeptId}
     */
    public Integer getPublishDeptId() {
        return publishDeptId;
    }

    /**
     * {@link #publishDeptId}
     */
    public void setPublishDeptId(Integer publishDeptId) {
        this.publishDeptId = publishDeptId;
    }

    /**
     * {@link #publishDeptName}
     */
    public String getPublishDeptName() {
        return publishDeptName;
    }

    /**
     * {@link #publishDeptName}
     */
    public void setPublishDeptName(String publishDeptName) {
        this.publishDeptName = publishDeptName == null ? null : publishDeptName.trim();
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

    @Override
    public String getIdPropertyName() {
        return "aptitudeId";
    }

    @Override
    public Integer getIdValue() {
        return aptitudeId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.aptitudeId = id;
    }

    public static JSONObject toJSON(GaAptitude e) {
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

    public static List<JSONObject> toJSON(List<GaAptitude> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaAptitude.toJSON(this);
    }

    public static class Fields {
        public static final String APTITUDE_ID = "aptitudeId";

        public static final String TITLE = "title";

        public static final String ATTACHMENT_FILE_NAME = "attachmentFileName";

        public static final String ATTACHMENT_FILE_ID = "attachmentFileId";

        public static final String CREATOR_USER_ID = "creatorUserId";

        public static final String CREATOR_NAME = "creatorName";

        public static final String IS_VISIBLE = "isVisible";

        public static final String PUBLISH_TIME = "publishTime";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String PUBLISHER = "publisher";

        public static final String PUBLISH_DEPT_ID = "publishDeptId";

        public static final String PUBLISH_DEPT_NAME = "publishDeptName";

        public static final String CONTENT = "content";
    }

    public static class Query {
        public static final String APTITUDE_ID__NE = "ne_aptitudeId";

        public static final String APTITUDE_ID__IN = "list_aptitudeId";

        public static final String APTITUDE_ID__BEGIN = "begin_aptitudeId";

        public static final String APTITUDE_ID__END = "end_aptitudeId";

        public static final String TITLE__NE = "ne_title";

        public static final String TITLE__LIKE = "like_title";

        public static final String TITLE__IN = "list_title";

        public static final String TITLE__BEGIN = "begin_title";

        public static final String TITLE__END = "end_title";

        public static final String ATTACHMENT_FILE_NAME__NE = "ne_attachmentFileName";

        public static final String ATTACHMENT_FILE_NAME__LIKE = "like_attachmentFileName";

        public static final String ATTACHMENT_FILE_NAME__IN = "list_attachmentFileName";

        public static final String ATTACHMENT_FILE_NAME__BEGIN = "begin_attachmentFileName";

        public static final String ATTACHMENT_FILE_NAME__END = "end_attachmentFileName";

        public static final String ATTACHMENT_FILE_ID__NE = "ne_attachmentFileId";

        public static final String ATTACHMENT_FILE_ID__LIKE = "like_attachmentFileId";

        public static final String ATTACHMENT_FILE_ID__IN = "list_attachmentFileId";

        public static final String ATTACHMENT_FILE_ID__BEGIN = "begin_attachmentFileId";

        public static final String ATTACHMENT_FILE_ID__END = "end_attachmentFileId";

        public static final String CREATOR_USER_ID__NE = "ne_creatorUserId";

        public static final String CREATOR_USER_ID__IN = "list_creatorUserId";

        public static final String CREATOR_USER_ID__BEGIN = "begin_creatorUserId";

        public static final String CREATOR_USER_ID__END = "end_creatorUserId";

        public static final String CREATOR_NAME__NE = "ne_creatorName";

        public static final String CREATOR_NAME__LIKE = "like_creatorName";

        public static final String CREATOR_NAME__IN = "list_creatorName";

        public static final String CREATOR_NAME__BEGIN = "begin_creatorName";

        public static final String CREATOR_NAME__END = "end_creatorName";

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

        public static final String PUBLISHER__NE = "ne_publisher";

        public static final String PUBLISHER__LIKE = "like_publisher";

        public static final String PUBLISHER__IN = "list_publisher";

        public static final String PUBLISHER__BEGIN = "begin_publisher";

        public static final String PUBLISHER__END = "end_publisher";

        public static final String PUBLISH_DEPT_ID__NE = "ne_publishDeptId";

        public static final String PUBLISH_DEPT_ID__IN = "list_publishDeptId";

        public static final String PUBLISH_DEPT_ID__BEGIN = "begin_publishDeptId";

        public static final String PUBLISH_DEPT_ID__END = "end_publishDeptId";

        public static final String PUBLISH_DEPT_NAME__NE = "ne_publishDeptName";

        public static final String PUBLISH_DEPT_NAME__LIKE = "like_publishDeptName";

        public static final String PUBLISH_DEPT_NAME__IN = "list_publishDeptName";

        public static final String PUBLISH_DEPT_NAME__BEGIN = "begin_publishDeptName";

        public static final String PUBLISH_DEPT_NAME__END = "end_publishDeptName";

        public static final String CONTENT__NE = "ne_content";

        public static final String CONTENT__LIKE = "like_content";

        public static final String CONTENT__IN = "list_content";

        public static final String CONTENT__BEGIN = "begin_content";

        public static final String CONTENT__END = "end_content";
    }
}