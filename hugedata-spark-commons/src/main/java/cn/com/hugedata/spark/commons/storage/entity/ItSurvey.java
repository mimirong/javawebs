package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItSurvey extends BaseEntity<Integer> {
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

    private static final long serialVersionUID = 2428897991333743942L;

    /**
     * {@link #surveyId}
     */
    public Integer getSurveyId() {
        return surveyId;
    }

    /**
     * {@link #surveyId}
     */
    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
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
     * {@link #source}
     */
    public String getSource() {
        return source;
    }

    /**
     * {@link #source}
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * {@link #startTime}
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * {@link #startTime}
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * {@link #endTime}
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * {@link #endTime}
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * {@link #submitCount}
     */
    public Integer getSubmitCount() {
        return submitCount;
    }

    /**
     * {@link #submitCount}
     */
    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    /**
     * {@link #creatorUserId}
     */
    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    /**
     * {@link #creatorUserId}
     */
    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    /**
     * {@link #creatorName}
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * {@link #creatorName}
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
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
     * {@link #isPublished}
     */
    public Boolean getIsPublished() {
        return isPublished;
    }

    /**
     * {@link #isPublished}
     */
    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    @Override
    public String getIdPropertyName() {
        return "surveyId";
    }

    @Override
    public Integer getIdValue() {
        return surveyId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.surveyId = id;
    }

    public static JSONObject toJSON(ItSurvey e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getStartTime() != null) {
            obj.put("startTimeStr", fmt.format(e.getStartTime()));
        }
        if (e.getEndTime() != null) {
            obj.put("endTimeStr", fmt.format(e.getEndTime()));
        }
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<ItSurvey> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ItSurvey.toJSON(this);
    }

    public static class Fields {
        public static final String SURVEY_ID = "surveyId";

        public static final String TITLE = "title";

        public static final String BRIEF = "brief";

        public static final String SOURCE = "source";

        public static final String START_TIME = "startTime";

        public static final String END_TIME = "endTime";

        public static final String SUBMIT_COUNT = "submitCount";

        public static final String CREATOR_USER_ID = "creatorUserId";

        public static final String CREATOR_NAME = "creatorName";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String IS_PUBLISHED = "isPublished";
    }

    public static class Query {
        public static final String SURVEY_ID__NE = "ne_surveyId";

        public static final String SURVEY_ID__IN = "list_surveyId";

        public static final String SURVEY_ID__BEGIN = "begin_surveyId";

        public static final String SURVEY_ID__END = "end_surveyId";

        public static final String TITLE__NE = "ne_title";

        public static final String TITLE__LIKE = "like_title";

        public static final String TITLE__IN = "list_title";

        public static final String TITLE__BEGIN = "begin_title";

        public static final String TITLE__END = "end_title";

        public static final String BRIEF__NE = "ne_brief";

        public static final String BRIEF__LIKE = "like_brief";

        public static final String BRIEF__IN = "list_brief";

        public static final String BRIEF__BEGIN = "begin_brief";

        public static final String BRIEF__END = "end_brief";

        public static final String SOURCE__NE = "ne_source";

        public static final String SOURCE__LIKE = "like_source";

        public static final String SOURCE__IN = "list_source";

        public static final String SOURCE__BEGIN = "begin_source";

        public static final String SOURCE__END = "end_source";

        public static final String START_TIME__NE = "ne_startTime";

        public static final String START_TIME__IN = "list_startTime";

        public static final String START_TIME__BEGIN = "begin_startTime";

        public static final String START_TIME__END = "end_startTime";

        public static final String END_TIME__NE = "ne_endTime";

        public static final String END_TIME__IN = "list_endTime";

        public static final String END_TIME__BEGIN = "begin_endTime";

        public static final String END_TIME__END = "end_endTime";

        public static final String SUBMIT_COUNT__NE = "ne_submitCount";

        public static final String SUBMIT_COUNT__IN = "list_submitCount";

        public static final String SUBMIT_COUNT__BEGIN = "begin_submitCount";

        public static final String SUBMIT_COUNT__END = "end_submitCount";

        public static final String CREATOR_USER_ID__NE = "ne_creatorUserId";

        public static final String CREATOR_USER_ID__IN = "list_creatorUserId";

        public static final String CREATOR_USER_ID__BEGIN = "begin_creatorUserId";

        public static final String CREATOR_USER_ID__END = "end_creatorUserId";

        public static final String CREATOR_NAME__NE = "ne_creatorName";

        public static final String CREATOR_NAME__LIKE = "like_creatorName";

        public static final String CREATOR_NAME__IN = "list_creatorName";

        public static final String CREATOR_NAME__BEGIN = "begin_creatorName";

        public static final String CREATOR_NAME__END = "end_creatorName";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String IS_PUBLISHED__NE = "ne_isPublished";

        public static final String IS_PUBLISHED__IN = "list_isPublished";

        public static final String IS_PUBLISHED__BEGIN = "begin_isPublished";

        public static final String IS_PUBLISHED__END = "end_isPublished";
    }
}