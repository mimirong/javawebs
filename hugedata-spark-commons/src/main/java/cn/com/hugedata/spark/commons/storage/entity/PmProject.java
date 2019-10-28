package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PmProject extends BaseEntity<Integer> {
    /**
     * projectId
     */
    private Integer projectId;

    /**
     * projectName
     */
    private String projectName;

    /**
     * projectCode
     */
    private String projectCode;

    /**
     * projectAddr
     */
    private String projectAddr;

    /**
     * projectArea
     */
    private String projectArea;

    /**
     * projectType
     */
    private String projectType;

    /**
     * projectContent
     */
    private String projectContent;

    /**
     * mainActor
     */
    private String mainActor;

    /**
     * dutyOrg
     */
    private String dutyOrg;

    /**
     * dutyMan
     */
    private String dutyMan;

    /**
     * contactLead
     */
    private String contactLead;

    /**
     * repoName
     */
    private String repoName;

    /**
     * beginEnd
     */
    private String beginEnd;

    /**
     * investTotal
     */
    private Double investTotal;

    /**
     * accountComment
     */
    private String accountComment;

    /**
     * projectStatus
     */
    private String projectStatus;

    /**
     * infCompanyId
     */
    private Integer infCompanyId;

    /**
     * infCompanyName
     */
    private String infCompanyName;

    /**
     * infUserId
     */
    private Integer infUserId;

    /**
     * infUserName
     */
    private String infUserName;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * beginYear
     */
    private Integer beginYear;

    /**
     * endYear
     */
    private Integer endYear;

    /**
     * frontUpdated
     */
    private String frontUpdated;

    /**
     * projectDeleted
     */
    private String projectDeleted;

    private static final long serialVersionUID = 7228405477860436887L;

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
     * {@link #projectName}
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * {@link #projectName}
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * {@link #projectCode}
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * {@link #projectCode}
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    /**
     * {@link #projectAddr}
     */
    public String getProjectAddr() {
        return projectAddr;
    }

    /**
     * {@link #projectAddr}
     */
    public void setProjectAddr(String projectAddr) {
        this.projectAddr = projectAddr == null ? null : projectAddr.trim();
    }

    /**
     * {@link #projectArea}
     */
    public String getProjectArea() {
        return projectArea;
    }

    /**
     * {@link #projectArea}
     */
    public void setProjectArea(String projectArea) {
        this.projectArea = projectArea == null ? null : projectArea.trim();
    }

    /**
     * {@link #projectType}
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * {@link #projectType}
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    /**
     * {@link #projectContent}
     */
    public String getProjectContent() {
        return projectContent;
    }

    /**
     * {@link #projectContent}
     */
    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent == null ? null : projectContent.trim();
    }

    /**
     * {@link #mainActor}
     */
    public String getMainActor() {
        return mainActor;
    }

    /**
     * {@link #mainActor}
     */
    public void setMainActor(String mainActor) {
        this.mainActor = mainActor == null ? null : mainActor.trim();
    }

    /**
     * {@link #dutyOrg}
     */
    public String getDutyOrg() {
        return dutyOrg;
    }

    /**
     * {@link #dutyOrg}
     */
    public void setDutyOrg(String dutyOrg) {
        this.dutyOrg = dutyOrg == null ? null : dutyOrg.trim();
    }

    /**
     * {@link #dutyMan}
     */
    public String getDutyMan() {
        return dutyMan;
    }

    /**
     * {@link #dutyMan}
     */
    public void setDutyMan(String dutyMan) {
        this.dutyMan = dutyMan == null ? null : dutyMan.trim();
    }

    /**
     * {@link #contactLead}
     */
    public String getContactLead() {
        return contactLead;
    }

    /**
     * {@link #contactLead}
     */
    public void setContactLead(String contactLead) {
        this.contactLead = contactLead == null ? null : contactLead.trim();
    }

    /**
     * {@link #repoName}
     */
    public String getRepoName() {
        return repoName;
    }

    /**
     * {@link #repoName}
     */
    public void setRepoName(String repoName) {
        this.repoName = repoName == null ? null : repoName.trim();
    }

    /**
     * {@link #beginEnd}
     */
    public String getBeginEnd() {
        return beginEnd;
    }

    /**
     * {@link #beginEnd}
     */
    public void setBeginEnd(String beginEnd) {
        this.beginEnd = beginEnd == null ? null : beginEnd.trim();
    }

    /**
     * {@link #investTotal}
     */
    public Double getInvestTotal() {
        return investTotal;
    }

    /**
     * {@link #investTotal}
     */
    public void setInvestTotal(Double investTotal) {
        this.investTotal = investTotal;
    }

    /**
     * {@link #accountComment}
     */
    public String getAccountComment() {
        return accountComment;
    }

    /**
     * {@link #accountComment}
     */
    public void setAccountComment(String accountComment) {
        this.accountComment = accountComment == null ? null : accountComment.trim();
    }

    /**
     * {@link #projectStatus}
     */
    public String getProjectStatus() {
        return projectStatus;
    }

    /**
     * {@link #projectStatus}
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    /**
     * {@link #infCompanyId}
     */
    public Integer getInfCompanyId() {
        return infCompanyId;
    }

    /**
     * {@link #infCompanyId}
     */
    public void setInfCompanyId(Integer infCompanyId) {
        this.infCompanyId = infCompanyId;
    }

    /**
     * {@link #infCompanyName}
     */
    public String getInfCompanyName() {
        return infCompanyName;
    }

    /**
     * {@link #infCompanyName}
     */
    public void setInfCompanyName(String infCompanyName) {
        this.infCompanyName = infCompanyName == null ? null : infCompanyName.trim();
    }

    /**
     * {@link #infUserId}
     */
    public Integer getInfUserId() {
        return infUserId;
    }

    /**
     * {@link #infUserId}
     */
    public void setInfUserId(Integer infUserId) {
        this.infUserId = infUserId;
    }

    /**
     * {@link #infUserName}
     */
    public String getInfUserName() {
        return infUserName;
    }

    /**
     * {@link #infUserName}
     */
    public void setInfUserName(String infUserName) {
        this.infUserName = infUserName == null ? null : infUserName.trim();
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
     * {@link #beginYear}
     */
    public Integer getBeginYear() {
        return beginYear;
    }

    /**
     * {@link #beginYear}
     */
    public void setBeginYear(Integer beginYear) {
        this.beginYear = beginYear;
    }

    /**
     * {@link #endYear}
     */
    public Integer getEndYear() {
        return endYear;
    }

    /**
     * {@link #endYear}
     */
    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    /**
     * {@link #frontUpdated}
     */
    public String getFrontUpdated() {
        return frontUpdated;
    }

    /**
     * {@link #frontUpdated}
     */
    public void setFrontUpdated(String frontUpdated) {
        this.frontUpdated = frontUpdated == null ? null : frontUpdated.trim();
    }

    /**
     * {@link #projectDeleted}
     */
    public String getProjectDeleted() {
        return projectDeleted;
    }

    /**
     * {@link #projectDeleted}
     */
    public void setProjectDeleted(String projectDeleted) {
        this.projectDeleted = projectDeleted == null ? null : projectDeleted.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "projectId";
    }

    @Override
    public Integer getIdValue() {
        return projectId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.projectId = id;
    }

    public static JSONObject toJSON(PmProject e) {
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

    public static List<JSONObject> toJSON(List<PmProject> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PmProject.toJSON(this);
    }

    public static class Fields {
        public static final String PROJECT_ID = "projectId";

        public static final String PROJECT_NAME = "projectName";

        public static final String PROJECT_CODE = "projectCode";

        public static final String PROJECT_ADDR = "projectAddr";

        public static final String PROJECT_AREA = "projectArea";

        public static final String PROJECT_TYPE = "projectType";

        public static final String PROJECT_CONTENT = "projectContent";

        public static final String MAIN_ACTOR = "mainActor";

        public static final String DUTY_ORG = "dutyOrg";

        public static final String DUTY_MAN = "dutyMan";

        public static final String CONTACT_LEAD = "contactLead";

        public static final String REPO_NAME = "repoName";

        public static final String BEGIN_END = "beginEnd";

        public static final String INVEST_TOTAL = "investTotal";

        public static final String ACCOUNT_COMMENT = "accountComment";

        public static final String PROJECT_STATUS = "projectStatus";

        public static final String INF_COMPANY_ID = "infCompanyId";

        public static final String INF_COMPANY_NAME = "infCompanyName";

        public static final String INF_USER_ID = "infUserId";

        public static final String INF_USER_NAME = "infUserName";

        public static final String CREATE_TIME = "createTime";

        public static final String BEGIN_YEAR = "beginYear";

        public static final String END_YEAR = "endYear";

        public static final String FRONT_UPDATED = "frontUpdated";

        public static final String PROJECT_DELETED = "projectDeleted";
    }

    public static class Query {
        public static final String PROJECT_ID__NE = "ne_projectId";

        public static final String PROJECT_ID__IN = "list_projectId";

        public static final String PROJECT_ID__BEGIN = "begin_projectId";

        public static final String PROJECT_ID__END = "end_projectId";

        public static final String PROJECT_NAME__NE = "ne_projectName";

        public static final String PROJECT_NAME__LIKE = "like_projectName";

        public static final String PROJECT_NAME__IN = "list_projectName";

        public static final String PROJECT_NAME__BEGIN = "begin_projectName";

        public static final String PROJECT_NAME__END = "end_projectName";

        public static final String PROJECT_CODE__NE = "ne_projectCode";

        public static final String PROJECT_CODE__LIKE = "like_projectCode";

        public static final String PROJECT_CODE__IN = "list_projectCode";

        public static final String PROJECT_CODE__BEGIN = "begin_projectCode";

        public static final String PROJECT_CODE__END = "end_projectCode";

        public static final String PROJECT_ADDR__NE = "ne_projectAddr";

        public static final String PROJECT_ADDR__LIKE = "like_projectAddr";

        public static final String PROJECT_ADDR__IN = "list_projectAddr";

        public static final String PROJECT_ADDR__BEGIN = "begin_projectAddr";

        public static final String PROJECT_ADDR__END = "end_projectAddr";

        public static final String PROJECT_AREA__NE = "ne_projectArea";

        public static final String PROJECT_AREA__LIKE = "like_projectArea";

        public static final String PROJECT_AREA__IN = "list_projectArea";

        public static final String PROJECT_AREA__BEGIN = "begin_projectArea";

        public static final String PROJECT_AREA__END = "end_projectArea";

        public static final String PROJECT_TYPE__NE = "ne_projectType";

        public static final String PROJECT_TYPE__LIKE = "like_projectType";

        public static final String PROJECT_TYPE__IN = "list_projectType";

        public static final String PROJECT_TYPE__BEGIN = "begin_projectType";

        public static final String PROJECT_TYPE__END = "end_projectType";

        public static final String PROJECT_CONTENT__NE = "ne_projectContent";

        public static final String PROJECT_CONTENT__LIKE = "like_projectContent";

        public static final String PROJECT_CONTENT__IN = "list_projectContent";

        public static final String PROJECT_CONTENT__BEGIN = "begin_projectContent";

        public static final String PROJECT_CONTENT__END = "end_projectContent";

        public static final String MAIN_ACTOR__NE = "ne_mainActor";

        public static final String MAIN_ACTOR__LIKE = "like_mainActor";

        public static final String MAIN_ACTOR__IN = "list_mainActor";

        public static final String MAIN_ACTOR__BEGIN = "begin_mainActor";

        public static final String MAIN_ACTOR__END = "end_mainActor";

        public static final String DUTY_ORG__NE = "ne_dutyOrg";

        public static final String DUTY_ORG__LIKE = "like_dutyOrg";

        public static final String DUTY_ORG__IN = "list_dutyOrg";

        public static final String DUTY_ORG__BEGIN = "begin_dutyOrg";

        public static final String DUTY_ORG__END = "end_dutyOrg";

        public static final String DUTY_MAN__NE = "ne_dutyMan";

        public static final String DUTY_MAN__LIKE = "like_dutyMan";

        public static final String DUTY_MAN__IN = "list_dutyMan";

        public static final String DUTY_MAN__BEGIN = "begin_dutyMan";

        public static final String DUTY_MAN__END = "end_dutyMan";

        public static final String CONTACT_LEAD__NE = "ne_contactLead";

        public static final String CONTACT_LEAD__LIKE = "like_contactLead";

        public static final String CONTACT_LEAD__IN = "list_contactLead";

        public static final String CONTACT_LEAD__BEGIN = "begin_contactLead";

        public static final String CONTACT_LEAD__END = "end_contactLead";

        public static final String REPO_NAME__NE = "ne_repoName";

        public static final String REPO_NAME__LIKE = "like_repoName";

        public static final String REPO_NAME__IN = "list_repoName";

        public static final String REPO_NAME__BEGIN = "begin_repoName";

        public static final String REPO_NAME__END = "end_repoName";

        public static final String BEGIN_END__NE = "ne_beginEnd";

        public static final String BEGIN_END__LIKE = "like_beginEnd";

        public static final String BEGIN_END__IN = "list_beginEnd";

        public static final String BEGIN_END__BEGIN = "begin_beginEnd";

        public static final String BEGIN_END__END = "end_beginEnd";

        public static final String INVEST_TOTAL__NE = "ne_investTotal";

        public static final String INVEST_TOTAL__IN = "list_investTotal";

        public static final String INVEST_TOTAL__BEGIN = "begin_investTotal";

        public static final String INVEST_TOTAL__END = "end_investTotal";

        public static final String ACCOUNT_COMMENT__NE = "ne_accountComment";

        public static final String ACCOUNT_COMMENT__LIKE = "like_accountComment";

        public static final String ACCOUNT_COMMENT__IN = "list_accountComment";

        public static final String ACCOUNT_COMMENT__BEGIN = "begin_accountComment";

        public static final String ACCOUNT_COMMENT__END = "end_accountComment";

        public static final String PROJECT_STATUS__NE = "ne_projectStatus";

        public static final String PROJECT_STATUS__LIKE = "like_projectStatus";

        public static final String PROJECT_STATUS__IN = "list_projectStatus";

        public static final String PROJECT_STATUS__BEGIN = "begin_projectStatus";

        public static final String PROJECT_STATUS__END = "end_projectStatus";

        public static final String INF_COMPANY_ID__NE = "ne_infCompanyId";

        public static final String INF_COMPANY_ID__IN = "list_infCompanyId";

        public static final String INF_COMPANY_ID__BEGIN = "begin_infCompanyId";

        public static final String INF_COMPANY_ID__END = "end_infCompanyId";

        public static final String INF_COMPANY_NAME__NE = "ne_infCompanyName";

        public static final String INF_COMPANY_NAME__LIKE = "like_infCompanyName";

        public static final String INF_COMPANY_NAME__IN = "list_infCompanyName";

        public static final String INF_COMPANY_NAME__BEGIN = "begin_infCompanyName";

        public static final String INF_COMPANY_NAME__END = "end_infCompanyName";

        public static final String INF_USER_ID__NE = "ne_infUserId";

        public static final String INF_USER_ID__IN = "list_infUserId";

        public static final String INF_USER_ID__BEGIN = "begin_infUserId";

        public static final String INF_USER_ID__END = "end_infUserId";

        public static final String INF_USER_NAME__NE = "ne_infUserName";

        public static final String INF_USER_NAME__LIKE = "like_infUserName";

        public static final String INF_USER_NAME__IN = "list_infUserName";

        public static final String INF_USER_NAME__BEGIN = "begin_infUserName";

        public static final String INF_USER_NAME__END = "end_infUserName";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String BEGIN_YEAR__NE = "ne_beginYear";

        public static final String BEGIN_YEAR__IN = "list_beginYear";

        public static final String BEGIN_YEAR__BEGIN = "begin_beginYear";

        public static final String BEGIN_YEAR__END = "end_beginYear";

        public static final String END_YEAR__NE = "ne_endYear";

        public static final String END_YEAR__IN = "list_endYear";

        public static final String END_YEAR__BEGIN = "begin_endYear";

        public static final String END_YEAR__END = "end_endYear";

        public static final String FRONT_UPDATED__NE = "ne_frontUpdated";

        public static final String FRONT_UPDATED__LIKE = "like_frontUpdated";

        public static final String FRONT_UPDATED__IN = "list_frontUpdated";

        public static final String FRONT_UPDATED__BEGIN = "begin_frontUpdated";

        public static final String FRONT_UPDATED__END = "end_frontUpdated";

        public static final String PROJECT_DELETED__NE = "ne_projectDeleted";

        public static final String PROJECT_DELETED__LIKE = "like_projectDeleted";

        public static final String PROJECT_DELETED__IN = "list_projectDeleted";

        public static final String PROJECT_DELETED__BEGIN = "begin_projectDeleted";

        public static final String PROJECT_DELETED__END = "end_projectDeleted";
    }
}