package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.UcMessageConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UcMessage extends BaseEntity<Integer> implements UcMessageConstants {
    /**
     * messageId
     */
    private Integer messageId;

    /**
     * messageType
     */
    private String messageType;

    /**
     * title
     */
    private String title;

    /**
     * senderId
     */
    private Integer senderId;

    /**
     * senderName
     */
    private String senderName;

    /**
     * receiverId
     */
    private Integer receiverId;

    /**
     * receiverName
     */
    private String receiverName;

    /**
     * isRead
     */
    private Boolean isRead;

    /**
     * sendTime
     */
    private Date sendTime;

    /**
     * readTime
     */
    private Date readTime;

    /**
     * referenceId
     */
    private String referenceId;

    /**
     * content
     */
    private String content;

    private static final long serialVersionUID = 9138220000577986011L;

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
     * {@link #senderId}
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * {@link #senderId}
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * {@link #senderName}
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * {@link #senderName}
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    /**
     * {@link #receiverId}
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * {@link #receiverId}
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * {@link #receiverName}
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * {@link #receiverName}
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * {@link #isRead}
     */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
     * {@link #isRead}
     */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    /**
     * {@link #sendTime}
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * {@link #sendTime}
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * {@link #readTime}
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * {@link #readTime}
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * {@link #referenceId}
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * {@link #referenceId}
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId == null ? null : referenceId.trim();
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

    public static JSONObject toJSON(UcMessage e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getSendTime() != null) {
            obj.put("sendTimeStr", fmt.format(e.getSendTime()));
        }
        if (e.getReadTime() != null) {
            obj.put("readTimeStr", fmt.format(e.getReadTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcMessage> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcMessage.toJSON(this);
    }

    public static class Fields {
        public static final String MESSAGE_ID = "messageId";

        public static final String MESSAGE_TYPE = "messageType";

        public static final String TITLE = "title";

        public static final String SENDER_ID = "senderId";

        public static final String SENDER_NAME = "senderName";

        public static final String RECEIVER_ID = "receiverId";

        public static final String RECEIVER_NAME = "receiverName";

        public static final String IS_READ = "isRead";

        public static final String SEND_TIME = "sendTime";

        public static final String READ_TIME = "readTime";

        public static final String REFERENCE_ID = "referenceId";

        public static final String CONTENT = "content";
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

        public static final String TITLE__NE = "ne_title";

        public static final String TITLE__LIKE = "like_title";

        public static final String TITLE__IN = "list_title";

        public static final String TITLE__BEGIN = "begin_title";

        public static final String TITLE__END = "end_title";

        public static final String SENDER_ID__NE = "ne_senderId";

        public static final String SENDER_ID__IN = "list_senderId";

        public static final String SENDER_ID__BEGIN = "begin_senderId";

        public static final String SENDER_ID__END = "end_senderId";

        public static final String SENDER_NAME__NE = "ne_senderName";

        public static final String SENDER_NAME__LIKE = "like_senderName";

        public static final String SENDER_NAME__IN = "list_senderName";

        public static final String SENDER_NAME__BEGIN = "begin_senderName";

        public static final String SENDER_NAME__END = "end_senderName";

        public static final String RECEIVER_ID__NE = "ne_receiverId";

        public static final String RECEIVER_ID__IN = "list_receiverId";

        public static final String RECEIVER_ID__BEGIN = "begin_receiverId";

        public static final String RECEIVER_ID__END = "end_receiverId";

        public static final String RECEIVER_NAME__NE = "ne_receiverName";

        public static final String RECEIVER_NAME__LIKE = "like_receiverName";

        public static final String RECEIVER_NAME__IN = "list_receiverName";

        public static final String RECEIVER_NAME__BEGIN = "begin_receiverName";

        public static final String RECEIVER_NAME__END = "end_receiverName";

        public static final String IS_READ__NE = "ne_isRead";

        public static final String IS_READ__IN = "list_isRead";

        public static final String IS_READ__BEGIN = "begin_isRead";

        public static final String IS_READ__END = "end_isRead";

        public static final String SEND_TIME__NE = "ne_sendTime";

        public static final String SEND_TIME__IN = "list_sendTime";

        public static final String SEND_TIME__BEGIN = "begin_sendTime";

        public static final String SEND_TIME__END = "end_sendTime";

        public static final String READ_TIME__NE = "ne_readTime";

        public static final String READ_TIME__IN = "list_readTime";

        public static final String READ_TIME__BEGIN = "begin_readTime";

        public static final String READ_TIME__END = "end_readTime";

        public static final String REFERENCE_ID__NE = "ne_referenceId";

        public static final String REFERENCE_ID__LIKE = "like_referenceId";

        public static final String REFERENCE_ID__IN = "list_referenceId";

        public static final String REFERENCE_ID__BEGIN = "begin_referenceId";

        public static final String REFERENCE_ID__END = "end_referenceId";

        public static final String CONTENT__NE = "ne_content";

        public static final String CONTENT__LIKE = "like_content";

        public static final String CONTENT__IN = "list_content";

        public static final String CONTENT__BEGIN = "begin_content";

        public static final String CONTENT__END = "end_content";
    }
}