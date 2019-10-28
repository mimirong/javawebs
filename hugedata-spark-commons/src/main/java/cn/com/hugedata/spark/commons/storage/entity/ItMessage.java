package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItMessage extends BaseEntity<Integer> {
    /**
     * messageId
     */
    private Integer messageId;

    /**
     * messageType
     */
    private String messageType;

    /**
     * userId
     */
    private Integer userId;

    /**
     * name
     */
    private String name;

    /**
     * userType
     */
    private String userType;

    /**
     * idCard
     */
    private String idCard;

    /**
     * mobile
     */
    private String mobile;

    /**
     * email
     */
    private String email;

    /**
     * address
     */
    private String address;

    /**
     * title
     */
    private String title;

    /**
     * password
     */
    private String password;

    /**
     * status
     */
    private String status;

    /**
     * isDeleted
     */
    private Boolean isDeleted;

    /**
     * submitTime
     */
    private Date submitTime;

    /**
     * replyTime
     */
    private Date replyTime;

    /**
     * messageCode
     */
    private String messageCode;

    /**
     * replierCompany
     */
    private String replierCompany;

    /**
     * replierName
     */
    private String replierName;

    /**
     * content
     */
    private String content;

    /**
     * replyContent
     */
    private String replyContent;

    private static final long serialVersionUID = -3317255053403486468L;

    /**
     * {@link #messageId}
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * {@link #messageId}
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * {@link #messageType}
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * {@link #messageType}
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    /**
     * {@link #userId}
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * {@link #userId}
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * {@link #userType}
     */
    public String getUserType() {
        return userType;
    }

    /**
     * {@link #userType}
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * {@link #idCard}
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * {@link #idCard}
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
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
     * {@link #title}
     */
    public String getTitle() {
        return title;
    }

    /**
     * {@link #title}
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * {@link #password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * {@link #password}
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
     * {@link #isDeleted}
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * {@link #isDeleted}
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * {@link #submitTime}
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * {@link #submitTime}
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * {@link #replyTime}
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * {@link #replyTime}
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * {@link #messageCode}
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * {@link #messageCode}
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode == null ? null : messageCode.trim();
    }

    /**
     * {@link #replierCompany}
     */
    public String getReplierCompany() {
        return replierCompany;
    }

    /**
     * {@link #replierCompany}
     */
    public void setReplierCompany(String replierCompany) {
        this.replierCompany = replierCompany == null ? null : replierCompany.trim();
    }

    /**
     * {@link #replierName}
     */
    public String getReplierName() {
        return replierName;
    }

    /**
     * {@link #replierName}
     */
    public void setReplierName(String replierName) {
        this.replierName = replierName == null ? null : replierName.trim();
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
     * {@link #replyContent}
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * {@link #replyContent}
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "messageId";
    }

    @Override
    public Integer getIdValue() {
        return messageId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.messageId = id;
    }

    public static JSONObject toJSON(ItMessage e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getSubmitTime() != null) {
            obj.put("submitTimeStr", fmt.format(e.getSubmitTime()));
        }
        if (e.getReplyTime() != null) {
            obj.put("replyTimeStr", fmt.format(e.getReplyTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<ItMessage> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ItMessage.toJSON(this);
    }

    public static class Fields {
        public static final String MESSAGE_ID = "messageId";

        public static final String MESSAGE_TYPE = "messageType";

        public static final String USER_ID = "userId";

        public static final String NAME = "name";

        public static final String USER_TYPE = "userType";

        public static final String ID_CARD = "idCard";

        public static final String MOBILE = "mobile";

        public static final String EMAIL = "email";

        public static final String ADDRESS = "address";

        public static final String TITLE = "title";

        public static final String PASSWORD = "password";

        public static final String STATUS = "status";

        public static final String IS_DELETED = "isDeleted";

        public static final String SUBMIT_TIME = "submitTime";

        public static final String REPLY_TIME = "replyTime";

        public static final String MESSAGE_CODE = "messageCode";

        public static final String REPLIER_COMPANY = "replierCompany";

        public static final String REPLIER_NAME = "replierName";

        public static final String CONTENT = "content";

        public static final String REPLY_CONTENT = "replyContent";
    }

    public static class Query {
        public static final String MESSAGE_ID__NE = "ne_messageId";

        public static final String MESSAGE_ID__IN = "list_messageId";

        public static final String MESSAGE_ID__BEGIN = "begin_messageId";

        public static final String MESSAGE_ID__END = "end_messageId";

        public static final String MESSAGE_TYPE__NE = "ne_messageType";

        public static final String MESSAGE_TYPE__LIKE = "like_messageType";

        public static final String MESSAGE_TYPE__IN = "list_messageType";

        public static final String MESSAGE_TYPE__BEGIN = "begin_messageType";

        public static final String MESSAGE_TYPE__END = "end_messageType";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String USER_TYPE__NE = "ne_userType";

        public static final String USER_TYPE__LIKE = "like_userType";

        public static final String USER_TYPE__IN = "list_userType";

        public static final String USER_TYPE__BEGIN = "begin_userType";

        public static final String USER_TYPE__END = "end_userType";

        public static final String ID_CARD__NE = "ne_idCard";

        public static final String ID_CARD__LIKE = "like_idCard";

        public static final String ID_CARD__IN = "list_idCard";

        public static final String ID_CARD__BEGIN = "begin_idCard";

        public static final String ID_CARD__END = "end_idCard";

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

        public static final String ADDRESS__NE = "ne_address";

        public static final String ADDRESS__LIKE = "like_address";

        public static final String ADDRESS__IN = "list_address";

        public static final String ADDRESS__BEGIN = "begin_address";

        public static final String ADDRESS__END = "end_address";

        public static final String TITLE__NE = "ne_title";

        public static final String TITLE__LIKE = "like_title";

        public static final String TITLE__IN = "list_title";

        public static final String TITLE__BEGIN = "begin_title";

        public static final String TITLE__END = "end_title";

        public static final String PASSWORD__NE = "ne_password";

        public static final String PASSWORD__LIKE = "like_password";

        public static final String PASSWORD__IN = "list_password";

        public static final String PASSWORD__BEGIN = "begin_password";

        public static final String PASSWORD__END = "end_password";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";

        public static final String IS_DELETED__NE = "ne_isDeleted";

        public static final String IS_DELETED__IN = "list_isDeleted";

        public static final String IS_DELETED__BEGIN = "begin_isDeleted";

        public static final String IS_DELETED__END = "end_isDeleted";

        public static final String SUBMIT_TIME__NE = "ne_submitTime";

        public static final String SUBMIT_TIME__IN = "list_submitTime";

        public static final String SUBMIT_TIME__BEGIN = "begin_submitTime";

        public static final String SUBMIT_TIME__END = "end_submitTime";

        public static final String REPLY_TIME__NE = "ne_replyTime";

        public static final String REPLY_TIME__IN = "list_replyTime";

        public static final String REPLY_TIME__BEGIN = "begin_replyTime";

        public static final String REPLY_TIME__END = "end_replyTime";

        public static final String MESSAGE_CODE__NE = "ne_messageCode";

        public static final String MESSAGE_CODE__LIKE = "like_messageCode";

        public static final String MESSAGE_CODE__IN = "list_messageCode";

        public static final String MESSAGE_CODE__BEGIN = "begin_messageCode";

        public static final String MESSAGE_CODE__END = "end_messageCode";

        public static final String REPLIER_COMPANY__NE = "ne_replierCompany";

        public static final String REPLIER_COMPANY__LIKE = "like_replierCompany";

        public static final String REPLIER_COMPANY__IN = "list_replierCompany";

        public static final String REPLIER_COMPANY__BEGIN = "begin_replierCompany";

        public static final String REPLIER_COMPANY__END = "end_replierCompany";

        public static final String REPLIER_NAME__NE = "ne_replierName";

        public static final String REPLIER_NAME__LIKE = "like_replierName";

        public static final String REPLIER_NAME__IN = "list_replierName";

        public static final String REPLIER_NAME__BEGIN = "begin_replierName";

        public static final String REPLIER_NAME__END = "end_replierName";

        public static final String CONTENT__NE = "ne_content";

        public static final String CONTENT__LIKE = "like_content";

        public static final String CONTENT__IN = "list_content";

        public static final String CONTENT__BEGIN = "begin_content";

        public static final String CONTENT__END = "end_content";

        public static final String REPLY_CONTENT__NE = "ne_replyContent";

        public static final String REPLY_CONTENT__LIKE = "like_replyContent";

        public static final String REPLY_CONTENT__IN = "list_replyContent";

        public static final String REPLY_CONTENT__BEGIN = "begin_replyContent";

        public static final String REPLY_CONTENT__END = "end_replyContent";
    }
}