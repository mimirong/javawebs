package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaReportSubmit extends BaseEntity<Integer> {
    /**
     * submitId
     */
    private Integer submitId;

    /**
     * templateId
     */
    private Integer templateId;

    /**
     * templateName
     */
    private String templateName;

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
     * reportFileId
     */
    private String reportFileId;

    /**
     * submitterUserId
     */
    private Integer submitterUserId;

    /**
     * submitterName
     */
    private String submitterName;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * approveTime
     */
    private Date approveTime;

    /**
     * reportFileName
     */
    private String reportFileName;

    /**
     * content
     */
    private String content;

    private static final long serialVersionUID = 7350065840956302830L;

    /**
     * {@link #submitId}
     */
    public Integer getSubmitId() {
        return submitId;
    }

    /**
     * {@link #submitId}
     */
    public void setSubmitId(Integer submitId) {
        this.submitId = submitId;
    }

    /**
     * {@link #templateId}
     */
    public Integer getTemplateId() {
        return templateId;
    }

    /**
     * {@link #templateId}
     */
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    /**
     * {@link #templateName}
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * {@link #templateName}
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
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
     * {@link #reportFileId}
     */
    public String getReportFileId() {
        return reportFileId;
    }

    /**
     * {@link #reportFileId}
     */
    public void setReportFileId(String reportFileId) {
        this.reportFileId = reportFileId == null ? null : reportFileId.trim();
    }

    /**
     * {@link #submitterUserId}
     */
    public Integer getSubmitterUserId() {
        return submitterUserId;
    }

    /**
     * {@link #submitterUserId}
     */
    public void setSubmitterUserId(Integer submitterUserId) {
        this.submitterUserId = submitterUserId;
    }

    /**
     * {@link #submitterName}
     */
    public String getSubmitterName() {
        return submitterName;
    }

    /**
     * {@link #submitterName}
     */
    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName == null ? null : submitterName.trim();
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
     * {@link #reportFileName}
     */
    public String getReportFileName() {
        return reportFileName;
    }

    /**
     * {@link #reportFileName}
     */
    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName == null ? null : reportFileName.trim();
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
        return "submitId";
    }

    @Override
    public Integer getIdValue() {
        return submitId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.submitId = id;
    }

    public static JSONObject toJSON(GaReportSubmit e) {
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

    public static List<JSONObject> toJSON(List<GaReportSubmit> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaReportSubmit.toJSON(this);
    }

    public static class Fields {
        public static final String SUBMIT_ID = "submitId";

        public static final String TEMPLATE_ID = "templateId";

        public static final String TEMPLATE_NAME = "templateName";

        public static final String COMPANY_NAME = "companyName";

        public static final String CONTACT = "contact";

        public static final String CONTACT_MOBILE = "contactMobile";

        public static final String REPORT_FILE_ID = "reportFileId";

        public static final String SUBMITTER_USER_ID = "submitterUserId";

        public static final String SUBMITTER_NAME = "submitterName";

        public static final String CREATE_TIME = "createTime";

        public static final String APPROVE_TIME = "approveTime";

        public static final String REPORT_FILE_NAME = "reportFileName";

        public static final String CONTENT = "content";
    }

    public static class Query {
        public static final String SUBMIT_ID__NE = "ne_submitId";

        public static final String SUBMIT_ID__IN = "list_submitId";

        public static final String SUBMIT_ID__BEGIN = "begin_submitId";

        public static final String SUBMIT_ID__END = "end_submitId";

        public static final String TEMPLATE_ID__NE = "ne_templateId";

        public static final String TEMPLATE_ID__IN = "list_templateId";

        public static final String TEMPLATE_ID__BEGIN = "begin_templateId";

        public static final String TEMPLATE_ID__END = "end_templateId";

        public static final String TEMPLATE_NAME__NE = "ne_templateName";

        public static final String TEMPLATE_NAME__LIKE = "like_templateName";

        public static final String TEMPLATE_NAME__IN = "list_templateName";

        public static final String TEMPLATE_NAME__BEGIN = "begin_templateName";

        public static final String TEMPLATE_NAME__END = "end_templateName";

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

        public static final String REPORT_FILE_ID__NE = "ne_reportFileId";

        public static final String REPORT_FILE_ID__LIKE = "like_reportFileId";

        public static final String REPORT_FILE_ID__IN = "list_reportFileId";

        public static final String REPORT_FILE_ID__BEGIN = "begin_reportFileId";

        public static final String REPORT_FILE_ID__END = "end_reportFileId";

        public static final String SUBMITTER_USER_ID__NE = "ne_submitterUserId";

        public static final String SUBMITTER_USER_ID__IN = "list_submitterUserId";

        public static final String SUBMITTER_USER_ID__BEGIN = "begin_submitterUserId";

        public static final String SUBMITTER_USER_ID__END = "end_submitterUserId";

        public static final String SUBMITTER_NAME__NE = "ne_submitterName";

        public static final String SUBMITTER_NAME__LIKE = "like_submitterName";

        public static final String SUBMITTER_NAME__IN = "list_submitterName";

        public static final String SUBMITTER_NAME__BEGIN = "begin_submitterName";

        public static final String SUBMITTER_NAME__END = "end_submitterName";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String APPROVE_TIME__NE = "ne_approveTime";

        public static final String APPROVE_TIME__IN = "list_approveTime";

        public static final String APPROVE_TIME__BEGIN = "begin_approveTime";

        public static final String APPROVE_TIME__END = "end_approveTime";

        public static final String REPORT_FILE_NAME__NE = "ne_reportFileName";

        public static final String REPORT_FILE_NAME__LIKE = "like_reportFileName";

        public static final String REPORT_FILE_NAME__IN = "list_reportFileName";

        public static final String REPORT_FILE_NAME__BEGIN = "begin_reportFileName";

        public static final String REPORT_FILE_NAME__END = "end_reportFileName";

        public static final String CONTENT__NE = "ne_content";

        public static final String CONTENT__LIKE = "like_content";

        public static final String CONTENT__IN = "list_content";

        public static final String CONTENT__BEGIN = "begin_content";

        public static final String CONTENT__END = "end_content";
    }
}