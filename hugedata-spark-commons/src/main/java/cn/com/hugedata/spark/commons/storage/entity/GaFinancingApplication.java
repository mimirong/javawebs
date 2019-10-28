package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.GaFinancingApplicationConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaFinancingApplication extends BaseEntity<Integer> implements GaFinancingApplicationConstants {
    /**
     * applicationId
     */
    private Integer applicationId;

    /**
     * companyName
     */
    private String companyName;

    /**
     * contact
     */
    private String contact;

    /**
     * contactMobile
     */
    private String contactMobile;

    /**
     * businessInfo
     */
    private String businessInfo;

    /**
     * businessLicence
     */
    private String businessLicence;

    /**
     * applicationForm
     */
    private String applicationForm;

    /**
     * status
     */
    private String status;

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
     * createTime
     */
    private Date createTime;

    /**
     * approveTime
     */
    private Date approveTime;

    /**
     * staffSize
     */
    private String staffSize;

    /**
     * businessLicenceFilename
     */
    private String businessLicenceFilename;

    /**
     * applicationFormFilename
     */
    private String applicationFormFilename;

    /**
     * content
     */
    private String content;

    /**
     * remark
     */
    private String remark;

    private static final long serialVersionUID = 9130568666973725397L;

    /**
     * {@link #applicationId}
     */
    public Integer getApplicationId() {
        return applicationId;
    }

    /**
     * {@link #applicationId}
     */
    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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
     * {@link #contactMobile}
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * {@link #contactMobile}
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    /**
     * {@link #businessInfo}
     */
    public String getBusinessInfo() {
        return businessInfo;
    }

    /**
     * {@link #businessInfo}
     */
    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo == null ? null : businessInfo.trim();
    }

    /**
     * {@link #businessLicence}
     */
    public String getBusinessLicence() {
        return businessLicence;
    }

    /**
     * {@link #businessLicence}
     */
    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence == null ? null : businessLicence.trim();
    }

    /**
     * {@link #applicationForm}
     */
    public String getApplicationForm() {
        return applicationForm;
    }

    /**
     * {@link #applicationForm}
     */
    public void setApplicationForm(String applicationForm) {
        this.applicationForm = applicationForm == null ? null : applicationForm.trim();
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
     * {@link #staffSize}
     */
    public String getStaffSize() {
        return staffSize;
    }

    /**
     * {@link #staffSize}
     */
    public void setStaffSize(String staffSize) {
        this.staffSize = staffSize == null ? null : staffSize.trim();
    }

    /**
     * {@link #businessLicenceFilename}
     */
    public String getBusinessLicenceFilename() {
        return businessLicenceFilename;
    }

    /**
     * {@link #businessLicenceFilename}
     */
    public void setBusinessLicenceFilename(String businessLicenceFilename) {
        this.businessLicenceFilename = businessLicenceFilename == null ? null : businessLicenceFilename.trim();
    }

    /**
     * {@link #applicationFormFilename}
     */
    public String getApplicationFormFilename() {
        return applicationFormFilename;
    }

    /**
     * {@link #applicationFormFilename}
     */
    public void setApplicationFormFilename(String applicationFormFilename) {
        this.applicationFormFilename = applicationFormFilename == null ? null : applicationFormFilename.trim();
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

    @Override
    public String getIdPropertyName() {
        return "applicationId";
    }

    @Override
    public Integer getIdValue() {
        return applicationId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.applicationId = id;
    }

    public static JSONObject toJSON(GaFinancingApplication e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getApproveTime() != null) {
            obj.put("approveTimeStr", fmt.format(e.getApproveTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<GaFinancingApplication> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaFinancingApplication.toJSON(this);
    }

    public static class Fields {
        public static final String APPLICATION_ID = "applicationId";

        public static final String COMPANY_NAME = "companyName";

        public static final String CONTACT = "contact";

        public static final String CONTACT_MOBILE = "contactMobile";

        public static final String BUSINESS_INFO = "businessInfo";

        public static final String BUSINESS_LICENCE = "businessLicence";

        public static final String APPLICATION_FORM = "applicationForm";

        public static final String STATUS = "status";

        public static final String APPLIER_USER_ID = "applierUserId";

        public static final String APPLIER_NAME = "applierName";

        public static final String APPROVER_USER_ID = "approverUserId";

        public static final String APPROVER_NAME = "approverName";

        public static final String CREATE_TIME = "createTime";

        public static final String APPROVE_TIME = "approveTime";

        public static final String STAFF_SIZE = "staffSize";

        public static final String BUSINESS_LICENCE_FILENAME = "businessLicenceFilename";

        public static final String APPLICATION_FORM_FILENAME = "applicationFormFilename";

        public static final String CONTENT = "content";

        public static final String REMARK = "remark";
    }

    public static class Query {
        public static final String APPLICATION_ID__NE = "ne_applicationId";

        public static final String APPLICATION_ID__IN = "list_applicationId";

        public static final String APPLICATION_ID__BEGIN = "begin_applicationId";

        public static final String APPLICATION_ID__END = "end_applicationId";

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

        public static final String CONTACT_MOBILE__NE = "ne_contactMobile";

        public static final String CONTACT_MOBILE__LIKE = "like_contactMobile";

        public static final String CONTACT_MOBILE__IN = "list_contactMobile";

        public static final String CONTACT_MOBILE__BEGIN = "begin_contactMobile";

        public static final String CONTACT_MOBILE__END = "end_contactMobile";

        public static final String BUSINESS_INFO__NE = "ne_businessInfo";

        public static final String BUSINESS_INFO__LIKE = "like_businessInfo";

        public static final String BUSINESS_INFO__IN = "list_businessInfo";

        public static final String BUSINESS_INFO__BEGIN = "begin_businessInfo";

        public static final String BUSINESS_INFO__END = "end_businessInfo";

        public static final String BUSINESS_LICENCE__NE = "ne_businessLicence";

        public static final String BUSINESS_LICENCE__LIKE = "like_businessLicence";

        public static final String BUSINESS_LICENCE__IN = "list_businessLicence";

        public static final String BUSINESS_LICENCE__BEGIN = "begin_businessLicence";

        public static final String BUSINESS_LICENCE__END = "end_businessLicence";

        public static final String APPLICATION_FORM__NE = "ne_applicationForm";

        public static final String APPLICATION_FORM__LIKE = "like_applicationForm";

        public static final String APPLICATION_FORM__IN = "list_applicationForm";

        public static final String APPLICATION_FORM__BEGIN = "begin_applicationForm";

        public static final String APPLICATION_FORM__END = "end_applicationForm";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";

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

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String APPROVE_TIME__NE = "ne_approveTime";

        public static final String APPROVE_TIME__IN = "list_approveTime";

        public static final String APPROVE_TIME__BEGIN = "begin_approveTime";

        public static final String APPROVE_TIME__END = "end_approveTime";

        public static final String STAFF_SIZE__NE = "ne_staffSize";

        public static final String STAFF_SIZE__LIKE = "like_staffSize";

        public static final String STAFF_SIZE__IN = "list_staffSize";

        public static final String STAFF_SIZE__BEGIN = "begin_staffSize";

        public static final String STAFF_SIZE__END = "end_staffSize";

        public static final String BUSINESS_LICENCE_FILENAME__NE = "ne_businessLicenceFilename";

        public static final String BUSINESS_LICENCE_FILENAME__LIKE = "like_businessLicenceFilename";

        public static final String BUSINESS_LICENCE_FILENAME__IN = "list_businessLicenceFilename";

        public static final String BUSINESS_LICENCE_FILENAME__BEGIN = "begin_businessLicenceFilename";

        public static final String BUSINESS_LICENCE_FILENAME__END = "end_businessLicenceFilename";

        public static final String APPLICATION_FORM_FILENAME__NE = "ne_applicationFormFilename";

        public static final String APPLICATION_FORM_FILENAME__LIKE = "like_applicationFormFilename";

        public static final String APPLICATION_FORM_FILENAME__IN = "list_applicationFormFilename";

        public static final String APPLICATION_FORM_FILENAME__BEGIN = "begin_applicationFormFilename";

        public static final String APPLICATION_FORM_FILENAME__END = "end_applicationFormFilename";

        public static final String CONTENT__NE = "ne_content";

        public static final String CONTENT__LIKE = "like_content";

        public static final String CONTENT__IN = "list_content";

        public static final String CONTENT__BEGIN = "begin_content";

        public static final String CONTENT__END = "end_content";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";
    }
}