package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OsExpertsList extends BaseEntity<Integer> {
    /**
     * expertId
     */
    private Integer expertId;

    /**
     * name
     */
    private String name;

    /**
     * position
     */
    private String position;

    /**
     * jobTitle
     */
    private String jobTitle;

    /**
     * dept
     */
    private String dept;

    /**
     * professionFieldId
     */
    private String professionFieldId;

    /**
     * expertField
     */
    private String expertField;

    /**
     * expertTitle
     */
    private String expertTitle;

    /**
     * brief
     */
    private String brief;

    /**
     * sex
     */
    private Boolean sex;

    /**
     * ethnic
     */
    private String ethnic;

    /**
     * nativePlace
     */
    private String nativePlace;

    /**
     * education
     */
    private String education;

    /**
     * fileId
     */
    private String fileId;

    /**
     * previewFileId
     */
    private String previewFileId;

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
     * updateTime
     */
    private Date updateTime;

    /**
     * fileName
     */
    private String fileName;

    /**
     * previewFileName
     */
    private String previewFileName;

    /**
     * organization
     */
    private String organization;

    private static final long serialVersionUID = 5688482211585722111L;

    /**
     * {@link #expertId}
     */
    public Integer getExpertId() {
        return expertId;
    }

    /**
     * {@link #expertId}
     */
    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
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
     * {@link #position}
     */
    public String getPosition() {
        return position;
    }

    /**
     * {@link #position}
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * {@link #jobTitle}
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * {@link #jobTitle}
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle == null ? null : jobTitle.trim();
    }

    /**
     * {@link #dept}
     */
    public String getDept() {
        return dept;
    }

    /**
     * {@link #dept}
     */
    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    /**
     * {@link #professionFieldId}
     */
    public String getProfessionFieldId() {
        return professionFieldId;
    }

    /**
     * {@link #professionFieldId}
     */
    public void setProfessionFieldId(String professionFieldId) {
        this.professionFieldId = professionFieldId == null ? null : professionFieldId.trim();
    }

    /**
     * {@link #expertField}
     */
    public String getExpertField() {
        return expertField;
    }

    /**
     * {@link #expertField}
     */
    public void setExpertField(String expertField) {
        this.expertField = expertField == null ? null : expertField.trim();
    }

    /**
     * {@link #expertTitle}
     */
    public String getExpertTitle() {
        return expertTitle;
    }

    /**
     * {@link #expertTitle}
     */
    public void setExpertTitle(String expertTitle) {
        this.expertTitle = expertTitle == null ? null : expertTitle.trim();
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

    /**
     * {@link #sex}
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * {@link #sex}
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * {@link #ethnic}
     */
    public String getEthnic() {
        return ethnic;
    }

    /**
     * {@link #ethnic}
     */
    public void setEthnic(String ethnic) {
        this.ethnic = ethnic == null ? null : ethnic.trim();
    }

    /**
     * {@link #nativePlace}
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * {@link #nativePlace}
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace == null ? null : nativePlace.trim();
    }

    /**
     * {@link #education}
     */
    public String getEducation() {
        return education;
    }

    /**
     * {@link #education}
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
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
     * {@link #organization}
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * {@link #organization}
     */
    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "expertId";
    }

    @Override
    public Integer getIdValue() {
        return expertId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.expertId = id;
    }

    public static JSONObject toJSON(OsExpertsList e) {
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
        return obj;
    }

    public static List<JSONObject> toJSON(List<OsExpertsList> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return OsExpertsList.toJSON(this);
    }

    public static class Fields {
        public static final String EXPERT_ID = "expertId";

        public static final String NAME = "name";

        public static final String POSITION = "position";

        public static final String JOB_TITLE = "jobTitle";

        public static final String DEPT = "dept";

        public static final String PROFESSION_FIELD_ID = "professionFieldId";

        public static final String EXPERT_FIELD = "expertField";

        public static final String EXPERT_TITLE = "expertTitle";

        public static final String BRIEF = "brief";

        public static final String SEX = "sex";

        public static final String ETHNIC = "ethnic";

        public static final String NATIVE_PLACE = "nativePlace";

        public static final String EDUCATION = "education";

        public static final String FILE_ID = "fileId";

        public static final String PREVIEW_FILE_ID = "previewFileId";

        public static final String SORT_INDEX = "sortIndex";

        public static final String IS_VISIBLE = "isVisible";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String FILE_NAME = "fileName";

        public static final String PREVIEW_FILE_NAME = "previewFileName";

        public static final String ORGANIZATION = "organization";
    }

    public static class Query {
        public static final String EXPERT_ID__NE = "ne_expertId";

        public static final String EXPERT_ID__IN = "list_expertId";

        public static final String EXPERT_ID__BEGIN = "begin_expertId";

        public static final String EXPERT_ID__END = "end_expertId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String POSITION__NE = "ne_position";

        public static final String POSITION__LIKE = "like_position";

        public static final String POSITION__IN = "list_position";

        public static final String POSITION__BEGIN = "begin_position";

        public static final String POSITION__END = "end_position";

        public static final String JOB_TITLE__NE = "ne_jobTitle";

        public static final String JOB_TITLE__LIKE = "like_jobTitle";

        public static final String JOB_TITLE__IN = "list_jobTitle";

        public static final String JOB_TITLE__BEGIN = "begin_jobTitle";

        public static final String JOB_TITLE__END = "end_jobTitle";

        public static final String DEPT__NE = "ne_dept";

        public static final String DEPT__LIKE = "like_dept";

        public static final String DEPT__IN = "list_dept";

        public static final String DEPT__BEGIN = "begin_dept";

        public static final String DEPT__END = "end_dept";

        public static final String PROFESSION_FIELD_ID__NE = "ne_professionFieldId";

        public static final String PROFESSION_FIELD_ID__LIKE = "like_professionFieldId";

        public static final String PROFESSION_FIELD_ID__IN = "list_professionFieldId";

        public static final String PROFESSION_FIELD_ID__BEGIN = "begin_professionFieldId";

        public static final String PROFESSION_FIELD_ID__END = "end_professionFieldId";

        public static final String EXPERT_FIELD__NE = "ne_expertField";

        public static final String EXPERT_FIELD__LIKE = "like_expertField";

        public static final String EXPERT_FIELD__IN = "list_expertField";

        public static final String EXPERT_FIELD__BEGIN = "begin_expertField";

        public static final String EXPERT_FIELD__END = "end_expertField";

        public static final String EXPERT_TITLE__NE = "ne_expertTitle";

        public static final String EXPERT_TITLE__LIKE = "like_expertTitle";

        public static final String EXPERT_TITLE__IN = "list_expertTitle";

        public static final String EXPERT_TITLE__BEGIN = "begin_expertTitle";

        public static final String EXPERT_TITLE__END = "end_expertTitle";

        public static final String BRIEF__NE = "ne_brief";

        public static final String BRIEF__LIKE = "like_brief";

        public static final String BRIEF__IN = "list_brief";

        public static final String BRIEF__BEGIN = "begin_brief";

        public static final String BRIEF__END = "end_brief";

        public static final String SEX__NE = "ne_sex";

        public static final String SEX__IN = "list_sex";

        public static final String SEX__BEGIN = "begin_sex";

        public static final String SEX__END = "end_sex";

        public static final String ETHNIC__NE = "ne_ethnic";

        public static final String ETHNIC__LIKE = "like_ethnic";

        public static final String ETHNIC__IN = "list_ethnic";

        public static final String ETHNIC__BEGIN = "begin_ethnic";

        public static final String ETHNIC__END = "end_ethnic";

        public static final String NATIVE_PLACE__NE = "ne_nativePlace";

        public static final String NATIVE_PLACE__LIKE = "like_nativePlace";

        public static final String NATIVE_PLACE__IN = "list_nativePlace";

        public static final String NATIVE_PLACE__BEGIN = "begin_nativePlace";

        public static final String NATIVE_PLACE__END = "end_nativePlace";

        public static final String EDUCATION__NE = "ne_education";

        public static final String EDUCATION__LIKE = "like_education";

        public static final String EDUCATION__IN = "list_education";

        public static final String EDUCATION__BEGIN = "begin_education";

        public static final String EDUCATION__END = "end_education";

        public static final String FILE_ID__NE = "ne_fileId";

        public static final String FILE_ID__LIKE = "like_fileId";

        public static final String FILE_ID__IN = "list_fileId";

        public static final String FILE_ID__BEGIN = "begin_fileId";

        public static final String FILE_ID__END = "end_fileId";

        public static final String PREVIEW_FILE_ID__NE = "ne_previewFileId";

        public static final String PREVIEW_FILE_ID__LIKE = "like_previewFileId";

        public static final String PREVIEW_FILE_ID__IN = "list_previewFileId";

        public static final String PREVIEW_FILE_ID__BEGIN = "begin_previewFileId";

        public static final String PREVIEW_FILE_ID__END = "end_previewFileId";

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

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String FILE_NAME__NE = "ne_fileName";

        public static final String FILE_NAME__LIKE = "like_fileName";

        public static final String FILE_NAME__IN = "list_fileName";

        public static final String FILE_NAME__BEGIN = "begin_fileName";

        public static final String FILE_NAME__END = "end_fileName";

        public static final String PREVIEW_FILE_NAME__NE = "ne_previewFileName";

        public static final String PREVIEW_FILE_NAME__LIKE = "like_previewFileName";

        public static final String PREVIEW_FILE_NAME__IN = "list_previewFileName";

        public static final String PREVIEW_FILE_NAME__BEGIN = "begin_previewFileName";

        public static final String PREVIEW_FILE_NAME__END = "end_previewFileName";

        public static final String ORGANIZATION__NE = "ne_organization";

        public static final String ORGANIZATION__LIKE = "like_organization";

        public static final String ORGANIZATION__IN = "list_organization";

        public static final String ORGANIZATION__BEGIN = "begin_organization";

        public static final String ORGANIZATION__END = "end_organization";
    }
}