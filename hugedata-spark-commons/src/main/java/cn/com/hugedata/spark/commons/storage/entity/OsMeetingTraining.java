package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OsMeetingTraining extends BaseEntity<Integer> {

    //20180130 fenglier 扩展的属性，非代码自动生成 begin
    // 是否过期
    private String outOfDate;

    private String registrationTimeStr;
    private String registrationDeadlineStr;
    private String trainingStartTimeStr;
    private String trainingEndTimeStr;

    private String briefFileDownloadUrl; // 内容附件下载url
    private String announcementsFileDownloadUrl; // 注意事项附件下载url
    //20180130 fenglier 扩展的属性，非代码自动生成 end

    /**
     * trainingId
     */
    private Integer trainingId;

    /**
     * name
     */
    private String name;

    /**
     * trainingType
     */
    private String trainingType;

    /**
     * adaptObject
     */
    private String adaptObject;

    /**
     * registrationTime
     */
    private Date registrationTime;

    /**
     * registrationDeadline
     */
    private Date registrationDeadline;

    /**
     * trainingStartTime
     */
    private Date trainingStartTime;

    /**
     * trainingEndTime
     */
    private Date trainingEndTime;

    /**
     * address
     */
    private String address;

    /**
     * registrationWay
     */
    private String registrationWay;

    /**
     * maxPlayers
     */
    private Integer maxPlayers;

    /**
     * trainingWay
     */
    private String trainingWay;

    /**
     * price
     */
    private Integer price;

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
     * brief
     */
    private String brief;

    /**
     * briefFileId
     */
    private String briefFileId;

    /**
     * briefFileName
     */
    private String briefFileName;

    /**
     * announcements
     */
    private String announcements;

    /**
     * annoFileId
     */
    private String annoFileId;

    /**
     * annoFileName
     */
    private String annoFileName;

    /**
     * contact
     */
    private String contact;

    /**
     * phone
     */
    private String phone;

    /**
     * mobile
     */
    private String mobile;

    /**
     * email
     */
    private String email;

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

    private static final long serialVersionUID = -3499579081846214903L;

    public String getOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(String outOfDate) {
        this.outOfDate = outOfDate;
    }

    public String getRegistrationTimeStr() {
        return registrationTimeStr;
    }

    public void setRegistrationTimeStr(String registrationTimeStr) {
        this.registrationTimeStr = registrationTimeStr;
    }

    public String getRegistrationDeadlineStr() {
        return registrationDeadlineStr;
    }

    public void setRegistrationDeadlineStr(String registrationDeadlineStr) {
        this.registrationDeadlineStr = registrationDeadlineStr;
    }

    public String getTrainingStartTimeStr() {
        return trainingStartTimeStr;
    }

    public void setTrainingStartTimeStr(String trainingStartTimeStr) {
        this.trainingStartTimeStr = trainingStartTimeStr;
    }

    public String getTrainingEndTimeStr() {
        return trainingEndTimeStr;
    }

    public void setTrainingEndTimeStr(String trainingEndTimeStr) {
        this.trainingEndTimeStr = trainingEndTimeStr;
    }

    public String getBriefFileDownloadUrl() {
        return briefFileDownloadUrl;
    }

    public void setBriefFileDownloadUrl(String briefFileDownloadUrl) {
        this.briefFileDownloadUrl = briefFileDownloadUrl;
    }

    public String getAnnouncementsFileDownloadUrl() {
        return announcementsFileDownloadUrl;
    }

    public void setAnnouncementsFileDownloadUrl(String announcementsFileDownloadUrl) {
        this.announcementsFileDownloadUrl = announcementsFileDownloadUrl;
    }

    /**
     * {@link #trainingId}
     */
    public Integer getTrainingId() {
        return trainingId;
    }

    /**
     * {@link #trainingId}
     */
    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
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
     * {@link #trainingType}
     */
    public String getTrainingType() {
        return trainingType;
    }

    /**
     * {@link #trainingType}
     */
    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType == null ? null : trainingType.trim();
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
     * {@link #registrationTime}
     */
    public Date getRegistrationTime() {
        return registrationTime;
    }

    /**
     * {@link #registrationTime}
     */
    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    /**
     * {@link #registrationDeadline}
     */
    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    /**
     * {@link #registrationDeadline}
     */
    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    /**
     * {@link #trainingStartTime}
     */
    public Date getTrainingStartTime() {
        return trainingStartTime;
    }

    /**
     * {@link #trainingStartTime}
     */
    public void setTrainingStartTime(Date trainingStartTime) {
        this.trainingStartTime = trainingStartTime;
    }

    /**
     * {@link #trainingEndTime}
     */
    public Date getTrainingEndTime() {
        return trainingEndTime;
    }

    /**
     * {@link #trainingEndTime}
     */
    public void setTrainingEndTime(Date trainingEndTime) {
        this.trainingEndTime = trainingEndTime;
    }

    /**
     * {@link #address}
     */
    public String getAddress() {
        return address;
    }

    /**
     * {@link #address}
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * {@link #registrationWay}
     */
    public String getRegistrationWay() {
        return registrationWay;
    }

    /**
     * {@link #registrationWay}
     */
    public void setRegistrationWay(String registrationWay) {
        this.registrationWay = registrationWay == null ? null : registrationWay.trim();
    }

    /**
     * {@link #maxPlayers}
     */
    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * {@link #maxPlayers}
     */
    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * {@link #trainingWay}
     */
    public String getTrainingWay() {
        return trainingWay;
    }

    /**
     * {@link #trainingWay}
     */
    public void setTrainingWay(String trainingWay) {
        this.trainingWay = trainingWay == null ? null : trainingWay.trim();
    }

    /**
     * {@link #price}
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * {@link #price}
     */
    public void setPrice(Integer price) {
        this.price = price;
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
     * {@link #briefFileId}
     */
    public String getBriefFileId() {
        return briefFileId;
    }

    /**
     * {@link #briefFileId}
     */
    public void setBriefFileId(String briefFileId) {
        this.briefFileId = briefFileId == null ? null : briefFileId.trim();
    }

    /**
     * {@link #briefFileName}
     */
    public String getBriefFileName() {
        return briefFileName;
    }

    /**
     * {@link #briefFileName}
     */
    public void setBriefFileName(String briefFileName) {
        this.briefFileName = briefFileName == null ? null : briefFileName.trim();
    }

    /**
     * {@link #announcements}
     */
    public String getAnnouncements() {
        return announcements;
    }

    /**
     * {@link #announcements}
     */
    public void setAnnouncements(String announcements) {
        this.announcements = announcements == null ? null : announcements.trim();
    }

    /**
     * {@link #annoFileId}
     */
    public String getAnnoFileId() {
        return annoFileId;
    }

    /**
     * {@link #annoFileId}
     */
    public void setAnnoFileId(String annoFileId) {
        this.annoFileId = annoFileId == null ? null : annoFileId.trim();
    }

    /**
     * {@link #annoFileName}
     */
    public String getAnnoFileName() {
        return annoFileName;
    }

    /**
     * {@link #annoFileName}
     */
    public void setAnnoFileName(String annoFileName) {
        this.annoFileName = annoFileName == null ? null : annoFileName.trim();
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
     * {@link #mobile}
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * {@link #mobile}
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * {@link #email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * {@link #email}
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
        return "trainingId";
    }

    @Override
    public Integer getIdValue() {
        return trainingId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.trainingId = id;
    }

    public static JSONObject toJSON(OsMeetingTraining e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getRegistrationTime() != null) {
            obj.put("registrationTimeStr", fmt.format(e.getRegistrationTime()));
        }
        if (e.getRegistrationDeadline() != null) {
            obj.put("registrationDeadlineStr", fmt.format(e.getRegistrationDeadline()));
        }
        if (e.getTrainingStartTime() != null) {
            obj.put("trainingStartTimeStr", fmt.format(e.getTrainingStartTime()));
        }
        if (e.getTrainingEndTime() != null) {
            obj.put("trainingEndTimeStr", fmt.format(e.getTrainingEndTime()));
        }
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<OsMeetingTraining> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return OsMeetingTraining.toJSON(this);
    }

    public static class Fields {
        public static final String TRAINING_ID = "trainingId";

        public static final String NAME = "name";

        public static final String TRAINING_TYPE = "trainingType";

        public static final String ADAPT_OBJECT = "adaptObject";

        public static final String REGISTRATION_TIME = "registrationTime";

        public static final String REGISTRATION_DEADLINE = "registrationDeadline";

        public static final String TRAINING_START_TIME = "trainingStartTime";

        public static final String TRAINING_END_TIME = "trainingEndTime";

        public static final String ADDRESS = "address";

        public static final String REGISTRATION_WAY = "registrationWay";

        public static final String MAX_PLAYERS = "maxPlayers";

        public static final String TRAINING_WAY = "trainingWay";

        public static final String PRICE = "price";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String PREVIEW_FILE_ID = "previewFileId";

        public static final String PREVIEW_FILE_NAME = "previewFileName";

        public static final String BRIEF = "brief";

        public static final String BRIEF_FILE_ID = "briefFileId";

        public static final String BRIEF_FILE_NAME = "briefFileName";

        public static final String ANNOUNCEMENTS = "announcements";

        public static final String ANNO_FILE_ID = "annoFileId";

        public static final String ANNO_FILE_NAME = "annoFileName";

        public static final String CONTACT = "contact";

        public static final String PHONE = "phone";

        public static final String MOBILE = "mobile";

        public static final String EMAIL = "email";

        public static final String SORT_INDEX = "sortIndex";

        public static final String IS_VISIBLE = "isVisible";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";
    }

    public static class Query {
        public static final String TRAINING_ID__NE = "ne_trainingId";

        public static final String TRAINING_ID__IN = "list_trainingId";

        public static final String TRAINING_ID__BEGIN = "begin_trainingId";

        public static final String TRAINING_ID__END = "end_trainingId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String TRAINING_TYPE__NE = "ne_trainingType";

        public static final String TRAINING_TYPE__LIKE = "like_trainingType";

        public static final String TRAINING_TYPE__IN = "list_trainingType";

        public static final String TRAINING_TYPE__BEGIN = "begin_trainingType";

        public static final String TRAINING_TYPE__END = "end_trainingType";

        public static final String ADAPT_OBJECT__NE = "ne_adaptObject";

        public static final String ADAPT_OBJECT__LIKE = "like_adaptObject";

        public static final String ADAPT_OBJECT__IN = "list_adaptObject";

        public static final String ADAPT_OBJECT__BEGIN = "begin_adaptObject";

        public static final String ADAPT_OBJECT__END = "end_adaptObject";

        public static final String REGISTRATION_TIME__NE = "ne_registrationTime";

        public static final String REGISTRATION_TIME__IN = "list_registrationTime";

        public static final String REGISTRATION_TIME__BEGIN = "begin_registrationTime";

        public static final String REGISTRATION_TIME__END = "end_registrationTime";

        public static final String REGISTRATION_DEADLINE__NE = "ne_registrationDeadline";

        public static final String REGISTRATION_DEADLINE__IN = "list_registrationDeadline";

        public static final String REGISTRATION_DEADLINE__BEGIN = "begin_registrationDeadline";

        public static final String REGISTRATION_DEADLINE__END = "end_registrationDeadline";

        public static final String TRAINING_START_TIME__NE = "ne_trainingStartTime";

        public static final String TRAINING_START_TIME__IN = "list_trainingStartTime";

        public static final String TRAINING_START_TIME__BEGIN = "begin_trainingStartTime";

        public static final String TRAINING_START_TIME__END = "end_trainingStartTime";

        public static final String TRAINING_END_TIME__NE = "ne_trainingEndTime";

        public static final String TRAINING_END_TIME__IN = "list_trainingEndTime";

        public static final String TRAINING_END_TIME__BEGIN = "begin_trainingEndTime";

        public static final String TRAINING_END_TIME__END = "end_trainingEndTime";

        public static final String ADDRESS__NE = "ne_address";

        public static final String ADDRESS__LIKE = "like_address";

        public static final String ADDRESS__IN = "list_address";

        public static final String ADDRESS__BEGIN = "begin_address";

        public static final String ADDRESS__END = "end_address";

        public static final String REGISTRATION_WAY__NE = "ne_registrationWay";

        public static final String REGISTRATION_WAY__LIKE = "like_registrationWay";

        public static final String REGISTRATION_WAY__IN = "list_registrationWay";

        public static final String REGISTRATION_WAY__BEGIN = "begin_registrationWay";

        public static final String REGISTRATION_WAY__END = "end_registrationWay";

        public static final String MAX_PLAYERS__NE = "ne_maxPlayers";

        public static final String MAX_PLAYERS__IN = "list_maxPlayers";

        public static final String MAX_PLAYERS__BEGIN = "begin_maxPlayers";

        public static final String MAX_PLAYERS__END = "end_maxPlayers";

        public static final String TRAINING_WAY__NE = "ne_trainingWay";

        public static final String TRAINING_WAY__LIKE = "like_trainingWay";

        public static final String TRAINING_WAY__IN = "list_trainingWay";

        public static final String TRAINING_WAY__BEGIN = "begin_trainingWay";

        public static final String TRAINING_WAY__END = "end_trainingWay";

        public static final String PRICE__NE = "ne_price";

        public static final String PRICE__IN = "list_price";

        public static final String PRICE__BEGIN = "begin_price";

        public static final String PRICE__END = "end_price";

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

        public static final String BRIEF__NE = "ne_brief";

        public static final String BRIEF__LIKE = "like_brief";

        public static final String BRIEF__IN = "list_brief";

        public static final String BRIEF__BEGIN = "begin_brief";

        public static final String BRIEF__END = "end_brief";

        public static final String BRIEF_FILE_ID__NE = "ne_briefFileId";

        public static final String BRIEF_FILE_ID__LIKE = "like_briefFileId";

        public static final String BRIEF_FILE_ID__IN = "list_briefFileId";

        public static final String BRIEF_FILE_ID__BEGIN = "begin_briefFileId";

        public static final String BRIEF_FILE_ID__END = "end_briefFileId";

        public static final String BRIEF_FILE_NAME__NE = "ne_briefFileName";

        public static final String BRIEF_FILE_NAME__LIKE = "like_briefFileName";

        public static final String BRIEF_FILE_NAME__IN = "list_briefFileName";

        public static final String BRIEF_FILE_NAME__BEGIN = "begin_briefFileName";

        public static final String BRIEF_FILE_NAME__END = "end_briefFileName";

        public static final String ANNOUNCEMENTS__NE = "ne_announcements";

        public static final String ANNOUNCEMENTS__LIKE = "like_announcements";

        public static final String ANNOUNCEMENTS__IN = "list_announcements";

        public static final String ANNOUNCEMENTS__BEGIN = "begin_announcements";

        public static final String ANNOUNCEMENTS__END = "end_announcements";

        public static final String ANNO_FILE_ID__NE = "ne_annoFileId";

        public static final String ANNO_FILE_ID__LIKE = "like_annoFileId";

        public static final String ANNO_FILE_ID__IN = "list_annoFileId";

        public static final String ANNO_FILE_ID__BEGIN = "begin_annoFileId";

        public static final String ANNO_FILE_ID__END = "end_annoFileId";

        public static final String ANNO_FILE_NAME__NE = "ne_annoFileName";

        public static final String ANNO_FILE_NAME__LIKE = "like_annoFileName";

        public static final String ANNO_FILE_NAME__IN = "list_annoFileName";

        public static final String ANNO_FILE_NAME__BEGIN = "begin_annoFileName";

        public static final String ANNO_FILE_NAME__END = "end_annoFileName";

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

        public static final String MOBILE__NE = "ne_mobile";

        public static final String MOBILE__LIKE = "like_mobile";

        public static final String MOBILE__IN = "list_mobile";

        public static final String MOBILE__BEGIN = "begin_mobile";

        public static final String MOBILE__END = "end_mobile";

        public static final String EMAIL__NE = "ne_email";

        public static final String EMAIL__LIKE = "like_email";

        public static final String EMAIL__IN = "list_email";

        public static final String EMAIL__BEGIN = "begin_email";

        public static final String EMAIL__END = "end_email";

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