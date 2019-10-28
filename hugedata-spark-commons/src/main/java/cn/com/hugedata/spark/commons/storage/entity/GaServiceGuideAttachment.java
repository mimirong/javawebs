package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.GaServiceGuideAttachmentConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaServiceGuideAttachment extends BaseEntity<Integer> implements GaServiceGuideAttachmentConstants {
    /**
     * attachmentId
     */
    private Integer attachmentId;

    /**
     * attachmentType
     */
    private String attachmentType;

    /**
     * guideId
     */
    private Integer guideId;

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
     * sortIndex
     */
    private Integer sortIndex;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = -5579856028828197623L;

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
     * {@link #attachmentType}
     */
    public String getAttachmentType() {
        return attachmentType;
    }

    /**
     * {@link #attachmentType}
     */
    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType == null ? null : attachmentType.trim();
    }

    /**
     * {@link #guideId}
     */
    public Integer getGuideId() {
        return guideId;
    }

    /**
     * {@link #guideId}
     */
    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
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

    public static JSONObject toJSON(GaServiceGuideAttachment e) {
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

    public static List<JSONObject> toJSON(List<GaServiceGuideAttachment> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaServiceGuideAttachment.toJSON(this);
    }

    public static class Fields {
        public static final String ATTACHMENT_ID = "attachmentId";

        public static final String ATTACHMENT_TYPE = "attachmentType";

        public static final String GUIDE_ID = "guideId";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String PREVIEW_FILE_ID = "previewFileId";

        public static final String PREVIEW_FILE_NAME = "previewFileName";

        public static final String SORT_INDEX = "sortIndex";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String ATTACHMENT_ID__NE = "ne_attachmentId";

        public static final String ATTACHMENT_ID__IN = "list_attachmentId";

        public static final String ATTACHMENT_ID__BEGIN = "begin_attachmentId";

        public static final String ATTACHMENT_ID__END = "end_attachmentId";

        public static final String ATTACHMENT_TYPE__NE = "ne_attachmentType";

        public static final String ATTACHMENT_TYPE__LIKE = "like_attachmentType";

        public static final String ATTACHMENT_TYPE__IN = "list_attachmentType";

        public static final String ATTACHMENT_TYPE__BEGIN = "begin_attachmentType";

        public static final String ATTACHMENT_TYPE__END = "end_attachmentType";

        public static final String GUIDE_ID__NE = "ne_guideId";

        public static final String GUIDE_ID__IN = "list_guideId";

        public static final String GUIDE_ID__BEGIN = "begin_guideId";

        public static final String GUIDE_ID__END = "end_guideId";

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