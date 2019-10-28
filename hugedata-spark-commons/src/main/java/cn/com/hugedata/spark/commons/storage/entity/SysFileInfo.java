package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysFileInfo extends BaseEntity<String> {
    /**
     * fileId
     */
    private String fileId;

    /**
     * fileName
     */
    private String fileName;

    /**
     * fileSize
     */
    private Long fileSize;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * mimeType
     */
    private String mimeType;

    /**
     * extension
     */
    private String extension;

    /**
     * ownerId
     */
    private Integer ownerId;

    private static final long serialVersionUID = 344898402769008472L;

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
     * {@link #fileSize}
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * {@link #fileSize}
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
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
     * {@link #mimeType}
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * {@link #mimeType}
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType == null ? null : mimeType.trim();
    }

    /**
     * {@link #extension}
     */
    public String getExtension() {
        return extension;
    }

    /**
     * {@link #extension}
     */
    public void setExtension(String extension) {
        this.extension = extension == null ? null : extension.trim();
    }

    /**
     * {@link #ownerId}
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * {@link #ownerId}
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String getIdPropertyName() {
        return "fileId";
    }

    @Override
    public String getIdValue() {
        return fileId;
    }

    @Override
    public void setIdValue(String id) {
        this.fileId = id;
    }

    public static JSONObject toJSON(SysFileInfo e) {
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

    public static List<JSONObject> toJSON(List<SysFileInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return SysFileInfo.toJSON(this);
    }

    public static class Fields {
        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String FILE_SIZE = "fileSize";

        public static final String CREATE_TIME = "createTime";

        public static final String MIME_TYPE = "mimeType";

        public static final String EXTENSION = "extension";

        public static final String OWNER_ID = "ownerId";
    }

    public static class Query {
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

        public static final String FILE_SIZE__NE = "ne_fileSize";

        public static final String FILE_SIZE__IN = "list_fileSize";

        public static final String FILE_SIZE__BEGIN = "begin_fileSize";

        public static final String FILE_SIZE__END = "end_fileSize";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String MIME_TYPE__NE = "ne_mimeType";

        public static final String MIME_TYPE__LIKE = "like_mimeType";

        public static final String MIME_TYPE__IN = "list_mimeType";

        public static final String MIME_TYPE__BEGIN = "begin_mimeType";

        public static final String MIME_TYPE__END = "end_mimeType";

        public static final String EXTENSION__NE = "ne_extension";

        public static final String EXTENSION__LIKE = "like_extension";

        public static final String EXTENSION__IN = "list_extension";

        public static final String EXTENSION__BEGIN = "begin_extension";

        public static final String EXTENSION__END = "end_extension";

        public static final String OWNER_ID__NE = "ne_ownerId";

        public static final String OWNER_ID__IN = "list_ownerId";

        public static final String OWNER_ID__BEGIN = "begin_ownerId";

        public static final String OWNER_ID__END = "end_ownerId";
    }
}