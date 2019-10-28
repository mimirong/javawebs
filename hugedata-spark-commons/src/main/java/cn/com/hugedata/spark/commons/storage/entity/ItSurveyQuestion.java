package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ItSurveyQuestion extends BaseEntity<Integer> {
    /**
     * questionId
     */
    private Integer questionId;

    /**
     * surveyId
     */
    private Integer surveyId;

    /**
     * sortIndex
     */
    private Integer sortIndex;

    /**
     * questionText
     */
    private String questionText;

    /**
     * isRequired
     */
    private Boolean isRequired;

    /**
     * questionType
     */
    private String questionType;

    /**
     * configData
     */
    private String configData;

    /**
     * summaryData
     */
    private String summaryData;

    private static final long serialVersionUID = -4397522670635836583L;

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
     * {@link #questionText}
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * {@link #questionText}
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText == null ? null : questionText.trim();
    }

    /**
     * {@link #isRequired}
     */
    public Boolean getIsRequired() {
        return isRequired;
    }

    /**
     * {@link #isRequired}
     */
    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * {@link #questionType}
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * {@link #questionType}
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    /**
     * {@link #configData}
     */
    public String getConfigData() {
        return configData;
    }

    /**
     * {@link #configData}
     */
    public void setConfigData(String configData) {
        this.configData = configData == null ? null : configData.trim();
    }

    /**
     * {@link #summaryData}
     */
    public String getSummaryData() {
        return summaryData;
    }

    /**
     * {@link #summaryData}
     */
    public void setSummaryData(String summaryData) {
        this.summaryData = summaryData == null ? null : summaryData.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "questionId";
    }

    @Override
    public Integer getIdValue() {
        return questionId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.questionId = id;
    }

    public static JSONObject toJSON(ItSurveyQuestion e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<ItSurveyQuestion> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ItSurveyQuestion.toJSON(this);
    }

    public static class Fields {
        public static final String QUESTION_ID = "questionId";

        public static final String SURVEY_ID = "surveyId";

        public static final String SORT_INDEX = "sortIndex";

        public static final String QUESTION_TEXT = "questionText";

        public static final String IS_REQUIRED = "isRequired";

        public static final String QUESTION_TYPE = "questionType";

        public static final String CONFIG_DATA = "configData";

        public static final String SUMMARY_DATA = "summaryData";
    }

    public static class Query {
        public static final String QUESTION_ID__NE = "ne_questionId";

        public static final String QUESTION_ID__IN = "list_questionId";

        public static final String QUESTION_ID__BEGIN = "begin_questionId";

        public static final String QUESTION_ID__END = "end_questionId";

        public static final String SURVEY_ID__NE = "ne_surveyId";

        public static final String SURVEY_ID__IN = "list_surveyId";

        public static final String SURVEY_ID__BEGIN = "begin_surveyId";

        public static final String SURVEY_ID__END = "end_surveyId";

        public static final String SORT_INDEX__NE = "ne_sortIndex";

        public static final String SORT_INDEX__IN = "list_sortIndex";

        public static final String SORT_INDEX__BEGIN = "begin_sortIndex";

        public static final String SORT_INDEX__END = "end_sortIndex";

        public static final String QUESTION_TEXT__NE = "ne_questionText";

        public static final String QUESTION_TEXT__LIKE = "like_questionText";

        public static final String QUESTION_TEXT__IN = "list_questionText";

        public static final String QUESTION_TEXT__BEGIN = "begin_questionText";

        public static final String QUESTION_TEXT__END = "end_questionText";

        public static final String IS_REQUIRED__NE = "ne_isRequired";

        public static final String IS_REQUIRED__IN = "list_isRequired";

        public static final String IS_REQUIRED__BEGIN = "begin_isRequired";

        public static final String IS_REQUIRED__END = "end_isRequired";

        public static final String QUESTION_TYPE__NE = "ne_questionType";

        public static final String QUESTION_TYPE__LIKE = "like_questionType";

        public static final String QUESTION_TYPE__IN = "list_questionType";

        public static final String QUESTION_TYPE__BEGIN = "begin_questionType";

        public static final String QUESTION_TYPE__END = "end_questionType";

        public static final String CONFIG_DATA__NE = "ne_configData";

        public static final String CONFIG_DATA__LIKE = "like_configData";

        public static final String CONFIG_DATA__IN = "list_configData";

        public static final String CONFIG_DATA__BEGIN = "begin_configData";

        public static final String CONFIG_DATA__END = "end_configData";

        public static final String SUMMARY_DATA__NE = "ne_summaryData";

        public static final String SUMMARY_DATA__LIKE = "like_summaryData";

        public static final String SUMMARY_DATA__IN = "list_summaryData";

        public static final String SUMMARY_DATA__BEGIN = "begin_summaryData";

        public static final String SUMMARY_DATA__END = "end_summaryData";
    }
}