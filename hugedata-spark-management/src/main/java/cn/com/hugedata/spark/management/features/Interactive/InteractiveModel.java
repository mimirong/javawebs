package cn.com.hugedata.spark.management.features.Interactive;


import java.util.Date;
import java.util.List;

import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class InteractiveModel implements FeatureModel {
	 /**
     * messageId
     */
    private Integer messageId;
    /**
     * messageIdList
     */
    private List<Integer> messageIdList;

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

    
    
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getReplierCompany() {
		return replierCompany;
	}

	public void setReplierCompany(String replierCompany) {
		this.replierCompany = replierCompany;
	}

	public String getReplierName() {
		return replierName;
	}

	public void setReplierName(String replierName) {
		this.replierName = replierName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public List<Integer> getMessageIdList() {
		return messageIdList;
	}

	public void setMessageIdList(List<Integer> messageIdList) {
		this.messageIdList = messageIdList;
	}

	@Override
    public String findModelId() {
        return String.valueOf(messageId);
    }

    @Override
    public String findModelName() {
        return title;
    }
    
}
