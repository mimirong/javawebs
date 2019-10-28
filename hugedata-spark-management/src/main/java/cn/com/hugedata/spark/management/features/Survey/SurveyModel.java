package cn.com.hugedata.spark.management.features.Survey;


import java.util.Date;

import cn.com.hugedata.spark.commons.web.features.FeatureModel;


public class SurveyModel implements FeatureModel {
    /**
     * surveyId
     */
    private Integer surveyId;

    /**
     * title
     */
    private String title;

    /**
     * brief
     */
    private String brief;

    /**
     * source
     */
    private String source;

    /**
     * startTime
     */
    private Date startTime;

    /**
     * endTime
     */
    private Date endTime;

    /**
     * submitCount
     */
    private Integer submitCount;

    /**
     * creatorUserId
     */
    private Integer creatorUserId;

    /**
     * creatorName
     */
    private String creatorName;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * isPublished
     */
    private Boolean isPublished;

    /** 开始时间字符串 yyyy-MM-dd. */
    private String startTimeStr;
    
    /** 结束时间字符串 yyyy-MM-dd. */
    private String endTimeStr;

    @Override
    public String findModelId() {
        return String.valueOf(surveyId);
    }

    @Override
    public String findModelName() {
        return title;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

}
