package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PmProjectDoc extends BaseEntity<Integer> {
    /**
     * docId
     */
    private Integer docId;

    /**
     * docType
     */
    private String docType;

    /**
     * typeCode
     */
    private String typeCode;

    /**
     * typeName
     */
    private String typeName;

    /**
     * fileId
     */
    private String fileId;

    /**
     * fileName
     */
    private String fileName;

    /**
     * approveCode
     */
    private String approveCode;

    /**
     * uploadUserId
     */
    private Integer uploadUserId;

    /**
     * uploadUserName
     */
    private String uploadUserName;

    /**
     * uploadTime
     */
    private Date uploadTime;

    /**
     * deleted
     */
    private Boolean deleted;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * projectId
     */
    private Integer projectId;

    /**
     * status
     */
    private String status;

    private static final long serialVersionUID = 5559902341400567034L;

    /**
     * {@link #docId}
     */
    public Integer getDocId() {
        return docId;
    }

    /**
     * {@link #docId}
     */
    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    /**
     * {@link #docType}
     */
    public String getDocType() {
        return docType;
    }

    /**
     * {@link #docType}
     */
    public void setDocType(String docType) {
        this.docType = docType == null ? null : docType.trim();
    }

    /**
     * {@link #typeCode}
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * {@link #typeCode}
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * {@link #typeName}
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * {@link #typeName}
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
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
     * {@link #approveCode}
     */
    public String getApproveCode() {
        return approveCode;
    }

    /**
     * {@link #approveCode}
     */
    public void setApproveCode(String approveCode) {
        this.approveCode = approveCode == null ? null : approveCode.trim();
    }

    /**
     * {@link #uploadUserId}
     */
    public Integer getUploadUserId() {
        return uploadUserId;
    }

    /**
     * {@link #uploadUserId}
     */
    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    /**
     * {@link #uploadUserName}
     */
    public String getUploadUserName() {
        return uploadUserName;
    }

    /**
     * {@link #uploadUserName}
     */
    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName == null ? null : uploadUserName.trim();
    }

    /**
     * {@link #uploadTime}
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * {@link #uploadTime}
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * {@link #deleted}
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * {@link #deleted}
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
     * {@link #projectId}
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * {@link #projectId}
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * {@link #status}
     */
    public String getStatus() {
        return status;
    }

    /**
     * {@link #status}
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "docId";
    }

    @Override
    public Integer getIdValue() {
        return docId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.docId = id;
    }

    public static JSONObject toJSON(PmProjectDoc e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getUploadTime() != null) {
            obj.put("uploadTimeStr", fmt.format(e.getUploadTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<PmProjectDoc> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PmProjectDoc.toJSON(this);
    }

    public static class Fields {
        public static final String DOC_ID = "docId";

        public static final String DOC_TYPE = "docType";

        public static final String TYPE_CODE = "typeCode";

        public static final String TYPE_NAME = "typeName";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String APPROVE_CODE = "approveCode";

        public static final String UPLOAD_USER_ID = "uploadUserId";

        public static final String UPLOAD_USER_NAME = "uploadUserName";

        public static final String UPLOAD_TIME = "uploadTime";

        public static final String DELETED = "deleted";

        public static final String UPDATE_TIME = "updateTime";

        public static final String PROJECT_ID = "projectId";

        public static final String STATUS = "status";
    }

    public static class Query {
        public static final String DOC_ID__NE = "ne_docId";

        public static final String DOC_ID__IN = "list_docId";

        public static final String DOC_ID__BEGIN = "begin_docId";

        public static final String DOC_ID__END = "end_docId";

        public static final String DOC_TYPE__NE = "ne_docType";

        public static final String DOC_TYPE__LIKE = "like_docType";

        public static final String DOC_TYPE__IN = "list_docType";

        public static final String DOC_TYPE__BEGIN = "begin_docType";

        public static final String DOC_TYPE__END = "end_docType";

        public static final String TYPE_CODE__NE = "ne_typeCode";

        public static final String TYPE_CODE__LIKE = "like_typeCode";

        public static final String TYPE_CODE__IN = "list_typeCode";

        public static final String TYPE_CODE__BEGIN = "begin_typeCode";

        public static final String TYPE_CODE__END = "end_typeCode";

        public static final String TYPE_NAME__NE = "ne_typeName";

        public static final String TYPE_NAME__LIKE = "like_typeName";

        public static final String TYPE_NAME__IN = "list_typeName";

        public static final String TYPE_NAME__BEGIN = "begin_typeName";

        public static final String TYPE_NAME__END = "end_typeName";

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

        public static final String APPROVE_CODE__NE = "ne_approveCode";

        public static final String APPROVE_CODE__LIKE = "like_approveCode";

        public static final String APPROVE_CODE__IN = "list_approveCode";

        public static final String APPROVE_CODE__BEGIN = "begin_approveCode";

        public static final String APPROVE_CODE__END = "end_approveCode";

        public static final String UPLOAD_USER_ID__NE = "ne_uploadUserId";

        public static final String UPLOAD_USER_ID__IN = "list_uploadUserId";

        public static final String UPLOAD_USER_ID__BEGIN = "begin_uploadUserId";

        public static final String UPLOAD_USER_ID__END = "end_uploadUserId";

        public static final String UPLOAD_USER_NAME__NE = "ne_uploadUserName";

        public static final String UPLOAD_USER_NAME__LIKE = "like_uploadUserName";

        public static final String UPLOAD_USER_NAME__IN = "list_uploadUserName";

        public static final String UPLOAD_USER_NAME__BEGIN = "begin_uploadUserName";

        public static final String UPLOAD_USER_NAME__END = "end_uploadUserName";

        public static final String UPLOAD_TIME__NE = "ne_uploadTime";

        public static final String UPLOAD_TIME__IN = "list_uploadTime";

        public static final String UPLOAD_TIME__BEGIN = "begin_uploadTime";

        public static final String UPLOAD_TIME__END = "end_uploadTime";

        public static final String DELETED__NE = "ne_deleted";

        public static final String DELETED__IN = "list_deleted";

        public static final String DELETED__BEGIN = "begin_deleted";

        public static final String DELETED__END = "end_deleted";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String PROJECT_ID__NE = "ne_projectId";

        public static final String PROJECT_ID__IN = "list_projectId";

        public static final String PROJECT_ID__BEGIN = "begin_projectId";

        public static final String PROJECT_ID__END = "end_projectId";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";
    }
}