package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpProjectImage extends BaseEntity<Integer> {
    /**
     * imageId
     */
    private Integer imageId;

    /**
     * projectId
     */
    private String projectId;

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

    private static final long serialVersionUID = -2124652808410152301L;

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
     * {@link #projectId}
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * {@link #projectId}
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
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

    public static JSONObject toJSON(SpProjectImage e) {
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

    public static List<JSONObject> toJSON(List<SpProjectImage> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return SpProjectImage.toJSON(this);
    }

    public static class Fields {
        public static final String IMAGE_ID = "imageId";

        public static final String PROJECT_ID = "projectId";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String PREVIEW_FILE_ID = "previewFileId";

        public static final String PREVIEW_FILE_NAME = "previewFileName";

        public static final String REMARK = "remark";

        public static final String SORT_INDEX = "sortIndex";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String IMAGE_ID__NE = "ne_imageId";

        public static final String IMAGE_ID__IN = "list_imageId";

        public static final String IMAGE_ID__BEGIN = "begin_imageId";

        public static final String IMAGE_ID__END = "end_imageId";

        public static final String PROJECT_ID__NE = "ne_projectId";

        public static final String PROJECT_ID__LIKE = "like_projectId";

        public static final String PROJECT_ID__IN = "list_projectId";

        public static final String PROJECT_ID__BEGIN = "begin_projectId";

        public static final String PROJECT_ID__END = "end_projectId";

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