package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OsTechAchieve extends BaseEntity<Integer> {
    /**
     * achieveId
     */
    private Integer achieveId;

    /**
     * name
     */
    private String name;

    /**
     * professionFieldId
     */
    private String professionFieldId;

    /**
     * achieveType
     */
    private String achieveType;

    /**
     * investmentVolume
     */
    private Integer investmentVolume;

    /**
     * monetaryUnit
     */
    private String monetaryUnit;

    /**
     * maturityStage
     */
    private String maturityStage;

    /**
     * desiredEffect
     */
    private String desiredEffect;

    /**
     * adaptObject
     */
    private String adaptObject;

    /**
     * cooperationWays
     */
    private String cooperationWays;

    /**
     * contact
     */
    private String contact;

    /**
     * phone
     */
    private String phone;

    /**
     * brief
     */
    private String brief;

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

    private static final long serialVersionUID = -4320676398779174392L;

    /**
     * {@link #achieveId}
     */
    public Integer getAchieveId() {
        return achieveId;
    }

    /**
     * {@link #achieveId}
     */
    public void setAchieveId(Integer achieveId) {
        this.achieveId = achieveId;
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
     * {@link #achieveType}
     */
    public String getAchieveType() {
        return achieveType;
    }

    /**
     * {@link #achieveType}
     */
    public void setAchieveType(String achieveType) {
        this.achieveType = achieveType == null ? null : achieveType.trim();
    }

    /**
     * {@link #investmentVolume}
     */
    public Integer getInvestmentVolume() {
        return investmentVolume;
    }

    /**
     * {@link #investmentVolume}
     */
    public void setInvestmentVolume(Integer investmentVolume) {
        this.investmentVolume = investmentVolume;
    }

    /**
     * {@link #monetaryUnit}
     */
    public String getMonetaryUnit() {
        return monetaryUnit;
    }

    /**
     * {@link #monetaryUnit}
     */
    public void setMonetaryUnit(String monetaryUnit) {
        this.monetaryUnit = monetaryUnit == null ? null : monetaryUnit.trim();
    }

    /**
     * {@link #maturityStage}
     */
    public String getMaturityStage() {
        return maturityStage;
    }

    /**
     * {@link #maturityStage}
     */
    public void setMaturityStage(String maturityStage) {
        this.maturityStage = maturityStage == null ? null : maturityStage.trim();
    }

    /**
     * {@link #desiredEffect}
     */
    public String getDesiredEffect() {
        return desiredEffect;
    }

    /**
     * {@link #desiredEffect}
     */
    public void setDesiredEffect(String desiredEffect) {
        this.desiredEffect = desiredEffect == null ? null : desiredEffect.trim();
    }

    /**
     * {@link #adaptObject}
     */
    public String getAdaptObject() {
        return adaptObject;
    }

    /**
     * {@link #adaptObject}
     */
    public void setAdaptObject(String adaptObject) {
        this.adaptObject = adaptObject == null ? null : adaptObject.trim();
    }

    /**
     * {@link #cooperationWays}
     */
    public String getCooperationWays() {
        return cooperationWays;
    }

    /**
     * {@link #cooperationWays}
     */
    public void setCooperationWays(String cooperationWays) {
        this.cooperationWays = cooperationWays == null ? null : cooperationWays.trim();
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
     * {@link #phone}
     */
    public String getPhone() {
        return phone;
    }

    /**
     * {@link #phone}
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    @Override
    public String getIdPropertyName() {
        return "achieveId";
    }

    @Override
    public Integer getIdValue() {
        return achieveId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.achieveId = id;
    }

    public static JSONObject toJSON(OsTechAchieve e) {
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

    public static List<JSONObject> toJSON(List<OsTechAchieve> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return OsTechAchieve.toJSON(this);
    }

    public static class Fields {
        public static final String ACHIEVE_ID = "achieveId";

        public static final String NAME = "name";

        public static final String PROFESSION_FIELD_ID = "professionFieldId";

        public static final String ACHIEVE_TYPE = "achieveType";

        public static final String INVESTMENT_VOLUME = "investmentVolume";

        public static final String MONETARY_UNIT = "monetaryUnit";

        public static final String MATURITY_STAGE = "maturityStage";

        public static final String DESIRED_EFFECT = "desiredEffect";

        public static final String ADAPT_OBJECT = "adaptObject";

        public static final String COOPERATION_WAYS = "cooperationWays";

        public static final String CONTACT = "contact";

        public static final String PHONE = "phone";

        public static final String BRIEF = "brief";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String PREVIEW_FILE_ID = "previewFileId";

        public static final String PREVIEW_FILE_NAME = "previewFileName";

        public static final String SORT_INDEX = "sortIndex";

        public static final String IS_VISIBLE = "isVisible";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";
    }

    public static class Query {
        public static final String ACHIEVE_ID__NE = "ne_achieveId";

        public static final String ACHIEVE_ID__IN = "list_achieveId";

        public static final String ACHIEVE_ID__BEGIN = "begin_achieveId";

        public static final String ACHIEVE_ID__END = "end_achieveId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String PROFESSION_FIELD_ID__NE = "ne_professionFieldId";

        public static final String PROFESSION_FIELD_ID__LIKE = "like_professionFieldId";

        public static final String PROFESSION_FIELD_ID__IN = "list_professionFieldId";

        public static final String PROFESSION_FIELD_ID__BEGIN = "begin_professionFieldId";

        public static final String PROFESSION_FIELD_ID__END = "end_professionFieldId";

        public static final String ACHIEVE_TYPE__NE = "ne_achieveType";

        public static final String ACHIEVE_TYPE__LIKE = "like_achieveType";

        public static final String ACHIEVE_TYPE__IN = "list_achieveType";

        public static final String ACHIEVE_TYPE__BEGIN = "begin_achieveType";

        public static final String ACHIEVE_TYPE__END = "end_achieveType";

        public static final String INVESTMENT_VOLUME__NE = "ne_investmentVolume";

        public static final String INVESTMENT_VOLUME__IN = "list_investmentVolume";

        public static final String INVESTMENT_VOLUME__BEGIN = "begin_investmentVolume";

        public static final String INVESTMENT_VOLUME__END = "end_investmentVolume";

        public static final String MONETARY_UNIT__NE = "ne_monetaryUnit";

        public static final String MONETARY_UNIT__LIKE = "like_monetaryUnit";

        public static final String MONETARY_UNIT__IN = "list_monetaryUnit";

        public static final String MONETARY_UNIT__BEGIN = "begin_monetaryUnit";

        public static final String MONETARY_UNIT__END = "end_monetaryUnit";

        public static final String MATURITY_STAGE__NE = "ne_maturityStage";

        public static final String MATURITY_STAGE__LIKE = "like_maturityStage";

        public static final String MATURITY_STAGE__IN = "list_maturityStage";

        public static final String MATURITY_STAGE__BEGIN = "begin_maturityStage";

        public static final String MATURITY_STAGE__END = "end_maturityStage";

        public static final String DESIRED_EFFECT__NE = "ne_desiredEffect";

        public static final String DESIRED_EFFECT__LIKE = "like_desiredEffect";

        public static final String DESIRED_EFFECT__IN = "list_desiredEffect";

        public static final String DESIRED_EFFECT__BEGIN = "begin_desiredEffect";

        public static final String DESIRED_EFFECT__END = "end_desiredEffect";

        public static final String ADAPT_OBJECT__NE = "ne_adaptObject";

        public static final String ADAPT_OBJECT__LIKE = "like_adaptObject";

        public static final String ADAPT_OBJECT__IN = "list_adaptObject";

        public static final String ADAPT_OBJECT__BEGIN = "begin_adaptObject";

        public static final String ADAPT_OBJECT__END = "end_adaptObject";

        public static final String COOPERATION_WAYS__NE = "ne_cooperationWays";

        public static final String COOPERATION_WAYS__LIKE = "like_cooperationWays";

        public static final String COOPERATION_WAYS__IN = "list_cooperationWays";

        public static final String COOPERATION_WAYS__BEGIN = "begin_cooperationWays";

        public static final String COOPERATION_WAYS__END = "end_cooperationWays";

        public static final String CONTACT__NE = "ne_contact";

        public static final String CONTACT__LIKE = "like_contact";

        public static final String CONTACT__IN = "list_contact";

        public static final String CONTACT__BEGIN = "begin_contact";

        public static final String CONTACT__END = "end_contact";

        public static final String PHONE__NE = "ne_phone";

        public static final String PHONE__LIKE = "like_phone";

        public static final String PHONE__IN = "list_phone";

        public static final String PHONE__BEGIN = "begin_phone";

        public static final String PHONE__END = "end_phone";

        public static final String BRIEF__NE = "ne_brief";

        public static final String BRIEF__LIKE = "like_brief";

        public static final String BRIEF__IN = "list_brief";

        public static final String BRIEF__BEGIN = "begin_brief";

        public static final String BRIEF__END = "end_brief";

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
    }
}