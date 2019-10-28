package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItSurveyResult extends BaseEntity<Integer> {
    /**
     * surveyResultId
     */
    private Integer surveyResultId;

    /**
     * surveyId
     */
    private Integer surveyId;

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

    private static final long serialVersionUID = 7947863916176302618L;

    /**
     * {@link #surveyResultId}
     */
    public Integer getSurveyResultId() {
        return surveyResultId;
    }

    /**
     * {@link #surveyResultId}
     */
    public void setSurveyResultId(Integer surveyResultId) {
        this.surveyResultId = surveyResultId;
    }

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

    @Override
    public String getIdPropertyName() {
        return "surveyResultId";
    }

    @Override
    public Integer getIdValue() {
        return surveyResultId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.surveyResultId = id;
    }

    public static JSONObject toJSON(ItSurveyResult e) {
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

    public static List<JSONObject> toJSON(List<ItSurveyResult> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ItSurveyResult.toJSON(this);
    }

    public static class Fields {
        public static final String SURVEY_RESULT_ID = "surveyResultId";

        public static final String SURVEY_ID = "surveyId";

        public static final String SUBMITTER_USER_ID = "submitterUserId";

        public static final String SUBMITTER_NAME = "submitterName";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String SURVEY_RESULT_ID__NE = "ne_surveyResultId";

        public static final String SURVEY_RESULT_ID__IN = "list_surveyResultId";

        public static final String SURVEY_RESULT_ID__BEGIN = "begin_surveyResultId";

        public static final String SURVEY_RESULT_ID__END = "end_surveyResultId";

        public static final String SURVEY_ID__NE = "ne_surveyId";

        public static final String SURVEY_ID__IN = "list_surveyId";

        public static final String SURVEY_ID__BEGIN = "begin_surveyId";

        public static final String SURVEY_ID__END = "end_surveyId";

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
    }
}