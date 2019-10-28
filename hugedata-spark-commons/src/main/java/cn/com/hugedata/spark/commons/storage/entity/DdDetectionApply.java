package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.DdDetectionApplyConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DdDetectionApply extends BaseEntity<Integer> implements DdDetectionApplyConstants {
    /**
     * applyId
     */
    private Integer applyId;

    /**
     * applyType
     */
    private String applyType;

    /**
     * status
     */
    private String status;

    /**
     * resourceId
     */
    private String resourceId;

    /**
     * resourceName
     */
    private String resourceName;

    /**
     * companyName
     */
    private String companyName;

    /**
     * contact
     */
    private String contact;

    /**
     * contactName
     */
    private String contactName;

    /**
     * applierUserId
     */
    private Integer applierUserId;

    /**
     * applierName
     */
    private String applierName;

    /**
     * approverUserId
     */
    private Integer approverUserId;

    /**
     * approverName
     */
    private String approverName;

    /**
     * approveComment
     */
    private String approveComment;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * approveTime
     */
    private Date approveTime;

    /**
     * comment
     */
    private String comment;

    /**
     * extraData
     */
    private String extraData;

    private static final long serialVersionUID = -496463838073534313L;

    /**
     * {@link #applyId}
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * {@link #applyId}
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * {@link #applyType}
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * {@link #applyType}
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
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

    /**
     * {@link #resourceId}
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * {@link #resourceId}
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    /**
     * {@link #resourceName}
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * {@link #resourceName}
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    /**
     * {@link #companyName}
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * {@link #companyName}
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * {@link #contact}
     */
    public String getContact() {
        return contact;
    }

    /**
     * {@link #contact}
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * {@link #contactName}
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * {@link #contactName}
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * {@link #applierUserId}
     */
    public Integer getApplierUserId() {
        return applierUserId;
    }

    /**
     * {@link #applierUserId}
     */
    public void setApplierUserId(Integer applierUserId) {
        this.applierUserId = applierUserId;
    }

    /**
     * {@link #applierName}
     */
    public String getApplierName() {
        return applierName;
    }

    /**
     * {@link #applierName}
     */
    public void setApplierName(String applierName) {
        this.applierName = applierName == null ? null : applierName.trim();
    }

    /**
     * {@link #approverUserId}
     */
    public Integer getApproverUserId() {
        return approverUserId;
    }

    /**
     * {@link #approverUserId}
     */
    public void setApproverUserId(Integer approverUserId) {
        this.approverUserId = approverUserId;
    }

    /**
     * {@link #approverName}
     */
    public String getApproverName() {
        return approverName;
    }

    /**
     * {@link #approverName}
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    /**
     * {@link #approveComment}
     */
    public String getApproveComment() {
        return approveComment;
    }

    /**
     * {@link #approveComment}
     */
    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment == null ? null : approveComment.trim();
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
     * {@link #approveTime}
     */
    public Date getApproveTime() {
        return approveTime;
    }

    /**
     * {@link #approveTime}
     */
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    /**
     * {@link #comment}
     */
    public String getComment() {
        return comment;
    }

    /**
     * {@link #comment}
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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
        return "applyId";
    }

    @Override
    public Integer getIdValue() {
        return applyId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.applyId = id;
    }

    public static JSONObject toJSON(DdDetectionApply e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        if (e.getApproveTime() != null) {
            obj.put("approveTimeStr", fmt.format(e.getApproveTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<DdDetectionApply> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return DdDetectionApply.toJSON(this);
    }

    public static class Fields {
        public static final String APPLY_ID = "applyId";

        public static final String APPLY_TYPE = "applyType";

        public static final String STATUS = "status";

        public static final String RESOURCE_ID = "resourceId";

        public static final String RESOURCE_NAME = "resourceName";

        public static final String COMPANY_NAME = "companyName";

        public static final String CONTACT = "contact";

        public static final String CONTACT_NAME = "contactName";

        public static final String APPLIER_USER_ID = "applierUserId";

        public static final String APPLIER_NAME = "applierName";

        public static final String APPROVER_USER_ID = "approverUserId";

        public static final String APPROVER_NAME = "approverName";

        public static final String APPROVE_COMMENT = "approveComment";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String APPROVE_TIME = "approveTime";

        public static final String COMMENT = "comment";

        public static final String EXTRA_DATA = "extraData";
    }

    public static class Query {
        public static final String APPLY_ID__NE = "ne_applyId";

        public static final String APPLY_ID__IN = "list_applyId";

        public static final String APPLY_ID__BEGIN = "begin_applyId";

        public static final String APPLY_ID__END = "end_applyId";

        public static final String APPLY_TYPE__NE = "ne_applyType";

        public static final String APPLY_TYPE__LIKE = "like_applyType";

        public static final String APPLY_TYPE__IN = "list_applyType";

        public static final String APPLY_TYPE__BEGIN = "begin_applyType";

        public static final String APPLY_TYPE__END = "end_applyType";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";

        public static final String RESOURCE_ID__NE = "ne_resourceId";

        public static final String RESOURCE_ID__LIKE = "like_resourceId";

        public static final String RESOURCE_ID__IN = "list_resourceId";

        public static final String RESOURCE_ID__BEGIN = "begin_resourceId";

        public static final String RESOURCE_ID__END = "end_resourceId";

        public static final String RESOURCE_NAME__NE = "ne_resourceName";

        public static final String RESOURCE_NAME__LIKE = "like_resourceName";

        public static final String RESOURCE_NAME__IN = "list_resourceName";

        public static final String RESOURCE_NAME__BEGIN = "begin_resourceName";

        public static final String RESOURCE_NAME__END = "end_resourceName";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String CONTACT__NE = "ne_contact";

        public static final String CONTACT__LIKE = "like_contact";

        public static final String CONTACT__IN = "list_contact";

        public static final String CONTACT__BEGIN = "begin_contact";

        public static final String CONTACT__END = "end_contact";

        public static final String CONTACT_NAME__NE = "ne_contactName";

        public static final String CONTACT_NAME__LIKE = "like_contactName";

        public static final String CONTACT_NAME__IN = "list_contactName";

        public static final String CONTACT_NAME__BEGIN = "begin_contactName";

        public static final String CONTACT_NAME__END = "end_contactName";

        public static final String APPLIER_USER_ID__NE = "ne_applierUserId";

        public static final String APPLIER_USER_ID__IN = "list_applierUserId";

        public static final String APPLIER_USER_ID__BEGIN = "begin_applierUserId";

        public static final String APPLIER_USER_ID__END = "end_applierUserId";

        public static final String APPLIER_NAME__NE = "ne_applierName";

        public static final String APPLIER_NAME__LIKE = "like_applierName";

        public static final String APPLIER_NAME__IN = "list_applierName";

        public static final String APPLIER_NAME__BEGIN = "begin_applierName";

        public static final String APPLIER_NAME__END = "end_applierName";

        public static final String APPROVER_USER_ID__NE = "ne_approverUserId";

        public static final String APPROVER_USER_ID__IN = "list_approverUserId";

        public static final String APPROVER_USER_ID__BEGIN = "begin_approverUserId";

        public static final String APPROVER_USER_ID__END = "end_approverUserId";

        public static final String APPROVER_NAME__NE = "ne_approverName";

        public static final String APPROVER_NAME__LIKE = "like_approverName";

        public static final String APPROVER_NAME__IN = "list_approverName";

        public static final String APPROVER_NAME__BEGIN = "begin_approverName";

        public static final String APPROVER_NAME__END = "end_approverName";

        public static final String APPROVE_COMMENT__NE = "ne_approveComment";

        public static final String APPROVE_COMMENT__LIKE = "like_approveComment";

        public static final String APPROVE_COMMENT__IN = "list_approveComment";

        public static final String APPROVE_COMMENT__BEGIN = "begin_approveComment";

        public static final String APPROVE_COMMENT__END = "end_approveComment";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String APPROVE_TIME__NE = "ne_approveTime";

        public static final String APPROVE_TIME__IN = "list_approveTime";

        public static final String APPROVE_TIME__BEGIN = "begin_approveTime";

        public static final String APPROVE_TIME__END = "end_approveTime";

        public static final String COMMENT__NE = "ne_comment";

        public static final String COMMENT__LIKE = "like_comment";

        public static final String COMMENT__IN = "list_comment";

        public static final String COMMENT__BEGIN = "begin_comment";

        public static final String COMMENT__END = "end_comment";

        public static final String EXTRA_DATA__NE = "ne_extraData";

        public static final String EXTRA_DATA__LIKE = "like_extraData";

        public static final String EXTRA_DATA__IN = "list_extraData";

        public static final String EXTRA_DATA__BEGIN = "begin_extraData";

        public static final String EXTRA_DATA__END = "end_extraData";
    }
}