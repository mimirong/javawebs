package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.PtTechImageConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PtTechImage extends BaseEntity<Integer> implements PtTechImageConstants {
    /**
     * imageId
     */
    private Integer imageId;

    /**
     * name
     */
    private String name;

    /**
     * fileId
     */
    private String fileId;

    /**
     * fileName
     */
    private String fileName;

    /**
     * previewFileId
     */
    private String previewFileId;

    /**
     * previewFileName
     */
    private String previewFileName;

    /**
     * linkUrl
     */
    private String linkUrl;

    /**
     * sortIndex
     */
    private Integer sortIndex;

    /**
     * isVisible
     */
    private Boolean isVisible;

    /**
     * createTime
     */
    private Date createTime;

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

    private static final long serialVersionUID = -307826721142617013L;

    /**
     * {@link #imageId}
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * {@link #imageId}
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    /**
     * {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * {@link #name}
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * {@link #previewFileId}
     */
    public String getPreviewFileId() {
        return previewFileId;
    }

    /**
     * {@link #previewFileId}
     */
    public void setPreviewFileId(String previewFileId) {
        this.previewFileId = previewFileId == null ? null : previewFileId.trim();
    }

    /**
     * {@link #previewFileName}
     */
    public String getPreviewFileName() {
        return previewFileName;
    }

    /**
     * {@link #previewFileName}
     */
    public void setPreviewFileName(String previewFileName) {
        this.previewFileName = previewFileName == null ? null : previewFileName.trim();
    }

    /**
     * {@link #linkUrl}
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * {@link #linkUrl}
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
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

    @Override
    public String getIdPropertyName() {
        return "imageId";
    }

    @Override
    public Integer getIdValue() {
        return imageId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.imageId = id;
    }

    public static JSONObject toJSON(PtTechImage e) {
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

    public static List<JSONObject> toJSON(List<PtTechImage> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PtTechImage.toJSON(this);
    }

    public static class Fields {
        public static final String IMAGE_ID = "imageId";

        public static final String NAME = "name";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String PREVIEW_FILE_ID = "previewFileId";

        public static final String PREVIEW_FILE_NAME = "previewFileName";

        public static final String LINK_URL = "linkUrl";

        public static final String SORT_INDEX = "sortIndex";

        public static final String IS_VISIBLE = "isVisible";

        public static final String CREATE_TIME = "createTime";

        public static final String CATEGORY_ID = "categoryId";

        public static final String TITLE = "title";

        public static final String BRIEF = "brief";
    }

    public static class Query {
        public static final String IMAGE_ID__NE = "ne_imageId";

        public static final String IMAGE_ID__IN = "list_imageId";

        public static final String IMAGE_ID__BEGIN = "begin_imageId";

        public static final String IMAGE_ID__END = "end_imageId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

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

        public static final String PREVIEW_FILE_ID__NE = "ne_previewFileId";

        public static final String PREVIEW_FILE_ID__LIKE = "like_previewFileId";

        public static final String PREVIEW_FILE_ID__IN = "list_previewFileId";

        public static final String PREVIEW_FILE_ID__BEGIN = "begin_previewFileId";

        public static final String PREVIEW_FILE_ID__END = "end_previewFileId";

        public static final String PREVIEW_FILE_NAME__NE = "ne_previewFileName";

        public static final String PREVIEW_FILE_NAME__LIKE = "like_previewFileName";

        public static final String PREVIEW_FILE_NAME__IN = "list_previewFileName";

        public static final String PREVIEW_FILE_NAME__BEGIN = "begin_previewFileName";

        public static final String PREVIEW_FILE_NAME__END = "end_previewFileName";

        public static final String LINK_URL__NE = "ne_linkUrl";

        public static final String LINK_URL__LIKE = "like_linkUrl";

        public static final String LINK_URL__IN = "list_linkUrl";

        public static final String LINK_URL__BEGIN = "begin_linkUrl";

        public static final String LINK_URL__END = "end_linkUrl";

        public static final String SORT_INDEX__NE = "ne_sortIndex";

        public static final String SORT_INDEX__IN = "list_sortIndex";

        public static final String SORT_INDEX__BEGIN = "begin_sortIndex";

        public static final String SORT_INDEX__END = "end_sortIndex";

        public static final String IS_VISIBLE__NE = "ne_isVisible";

        public static final String IS_VISIBLE__IN = "list_isVisible";

        public static final String IS_VISIBLE__BEGIN = "begin_isVisible";

        public static final String IS_VISIBLE__END = "end_isVisible";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

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
    }
}