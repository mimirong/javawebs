package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.ApServiceAttachmentConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApServiceAttachment extends BaseEntity<Integer> implements ApServiceAttachmentConstants {
    /**
     * attachmentId
     */
    private Integer attachmentId;

    /**
     * serviceId
     */
    private Integer serviceId;

    /**
     * attConfigId
     */
    private Integer attConfigId;

    /**
     * attConfigName
     */
    private String attConfigName;

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
     * approveStatus
     */
    private String approveStatus;

    /**
     * sortIndex
     */
    private Integer sortIndex;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = 3889885065051476659L;

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
     * {@link #serviceId}
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * {@link #serviceId}
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * {@link #attConfigId}
     */
    public Integer getAttConfigId() {
        return attConfigId;
    }

    /**
     * {@link #attConfigId}
     */
    public void setAttConfigId(Integer attConfigId) {
        this.attConfigId = attConfigId;
    }

    /**
     * {@link #attConfigName}
     */
    public String getAttConfigName() {
        return attConfigName;
    }

    /**
     * {@link #attConfigName}
     */
    public void setAttConfigName(String attConfigName) {
        this.attConfigName = attConfigName == null ? null : attConfigName.trim();
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
     * {@link #approveStatus}
     */
    public String getApproveStatus() {
        return approveStatus;
    }

    /**
     * {@link #approveStatus}
     */
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
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

    public static JSONObject toJSON(ApServiceAttachment e) {
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

    public static List<JSONObject> toJSON(List<ApServiceAttachment> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ApServiceAttachment.toJSON(this);
    }

    public static class Fields {
        public static final String ATTACHMENT_ID = "attachmentId";

        public static final String SERVICE_ID = "serviceId";

        public static final String ATT_CONFIG_ID = "attConfigId";

        public static final String ATT_CONFIG_NAME = "attConfigName";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String REMARK = "remark";

        public static final String APPROVE_STATUS = "approveStatus";

        public static final String SORT_INDEX = "sortIndex";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String ATTACHMENT_ID__NE = "ne_attachmentId";

        public static final String ATTACHMENT_ID__IN = "list_attachmentId";

        public static final String ATTACHMENT_ID__BEGIN = "begin_attachmentId";

        public static final String ATTACHMENT_ID__END = "end_attachmentId";

        public static final String SERVICE_ID__NE = "ne_serviceId";

        public static final String SERVICE_ID__IN = "list_serviceId";

        public static final String SERVICE_ID__BEGIN = "begin_serviceId";

        public static final String SERVICE_ID__END = "end_serviceId";

        public static final String ATT_CONFIG_ID__NE = "ne_attConfigId";

        public static final String ATT_CONFIG_ID__IN = "list_attConfigId";

        public static final String ATT_CONFIG_ID__BEGIN = "begin_attConfigId";

        public static final String ATT_CONFIG_ID__END = "end_attConfigId";

        public static final String ATT_CONFIG_NAME__NE = "ne_attConfigName";

        public static final String ATT_CONFIG_NAME__LIKE = "like_attConfigName";

        public static final String ATT_CONFIG_NAME__IN = "list_attConfigName";

        public static final String ATT_CONFIG_NAME__BEGIN = "begin_attConfigName";

        public static final String ATT_CONFIG_NAME__END = "end_attConfigName";

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

        public static final String APPROVE_STATUS__NE = "ne_approveStatus";

        public static final String APPROVE_STATUS__LIKE = "like_approveStatus";

        public static final String APPROVE_STATUS__IN = "list_approveStatus";

        public static final String APPROVE_STATUS__BEGIN = "begin_approveStatus";

        public static final String APPROVE_STATUS__END = "end_approveStatus";

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