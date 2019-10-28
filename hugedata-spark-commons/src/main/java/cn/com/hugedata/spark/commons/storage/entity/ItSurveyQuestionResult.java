package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItSurveyQuestionResult extends BaseEntity<Integer> {
    /**
     * resultId
     */
    private Integer resultId;

    /**
     * questionId
     */
    private Integer questionId;

    /**
     * surveyId
     */
    private Integer surveyId;

    /**
     * result
     */
    private String result;

    /**
     * surveyResultId
     */
    private Integer surveyResultId;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * resultText
     */
    private String resultText;

    /**
     * resultData
     */
    private String resultData;

    private static final long serialVersionUID = 6284319272010739323L;

    /**
     * {@link #resultId}
     */
    public Integer getResultId() {
        return resultId;
    }

    /**
     * {@link #resultId}
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    /**
     * {@link #questionId}
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * {@link #questionId}
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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
     * {@link #result}
     */
    public String getResult() {
        return result;
    }

    /**
     * {@link #result}
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

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
     * {@link #resultText}
     */
    public String getResultText() {
        return resultText;
    }

    /**
     * {@link #resultText}
     */
    public void setResultText(String resultText) {
        this.resultText = resultText == null ? null : resultText.trim();
    }

    /**
     * {@link #resultData}
     */
    public String getResultData() {
        return resultData;
    }

    /**
     * {@link #resultData}
     */
    public void setResultData(String resultData) {
        this.resultData = resultData == null ? null : resultData.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "resultId";
    }

    @Override
    public Integer getIdValue() {
        return resultId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.resultId = id;
    }

    public static JSONObject toJSON(ItSurveyQuestionResult e) {
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

    public static List<JSONObject> toJSON(List<ItSurveyQuestionResult> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ItSurveyQuestionResult.toJSON(this);
    }

    public static class Fields {
        public static final String RESULT_ID = "resultId";

        public static final String QUESTION_ID = "questionId";

        public static final String SURVEY_ID = "surveyId";

        public static final String RESULT = "result";

        public static final String SURVEY_RESULT_ID = "surveyResultId";

        public static final String CREATE_TIME = "createTime";

        public static final String RESULT_TEXT = "resultText";

        public static final String RESULT_DATA = "resultData";
    }

    public static class Query {
        public static final String RESULT_ID__NE = "ne_resultId";

        public static final String RESULT_ID__IN = "list_resultId";

        public static final String RESULT_ID__BEGIN = "begin_resultId";

        public static final String RESULT_ID__END = "end_resultId";

        public static final String QUESTION_ID__NE = "ne_questionId";

        public static final String QUESTION_ID__IN = "list_questionId";

        public static final String QUESTION_ID__BEGIN = "begin_questionId";

        public static final String QUESTION_ID__END = "end_questionId";

        public static final String SURVEY_ID__NE = "ne_surveyId";

        public static final String SURVEY_ID__IN = "list_surveyId";

        public static final String SURVEY_ID__BEGIN = "begin_surveyId";

        public static final String SURVEY_ID__END = "end_surveyId";

        public static final String RESULT__NE = "ne_result";

        public static final String RESULT__LIKE = "like_result";

        public static final String RESULT__IN = "list_result";

        public static final String RESULT__BEGIN = "begin_result";

        public static final String RESULT__END = "end_result";

        public static final String SURVEY_RESULT_ID__NE = "ne_surveyResultId";

        public static final String SURVEY_RESULT_ID__IN = "list_surveyResultId";

        public static final String SURVEY_RESULT_ID__BEGIN = "begin_surveyResultId";

        public static final String SURVEY_RESULT_ID__END = "end_surveyResultId";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String RESULT_TEXT__NE = "ne_resultText";

        public static final String RESULT_TEXT__LIKE = "like_resultText";

        public static final String RESULT_TEXT__IN = "list_resultText";

        public static final String RESULT_TEXT__BEGIN = "begin_resultText";

        public static final String RESULT_TEXT__END = "end_resultText";

        public static final String RESULT_DATA__NE = "ne_resultData";

        public static final String RESULT_DATA__LIKE = "like_resultData";

        public static final String RESULT_DATA__IN = "list_resultData";

        public static final String RESULT_DATA__BEGIN = "begin_resultData";

        public static final String RESULT_DATA__END = "end_resultData";
    }
}