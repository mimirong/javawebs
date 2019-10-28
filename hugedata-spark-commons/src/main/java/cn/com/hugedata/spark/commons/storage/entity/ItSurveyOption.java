package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ItSurveyOption extends BaseEntity<Integer> {
    /**
     * optionId
     */
    private Integer optionId;

    /**
     * surveyId
     */
    private Integer surveyId;

    /**
     * questionId
     */
    private Integer questionId;

    /**
     * optionCode
     */
    private Integer optionCode;

    /**
     * optionText
     */
    private String optionText;

    private static final long serialVersionUID = 7905731162741877831L;

    /**
     * {@link #optionId}
     */
    public Integer getOptionId() {
        return optionId;
    }

    /**
     * {@link #optionId}
     */
    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
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
     * {@link #optionCode}
     */
    public Integer getOptionCode() {
        return optionCode;
    }

    /**
     * {@link #optionCode}
     */
    public void setOptionCode(Integer optionCode) {
        this.optionCode = optionCode;
    }

    /**
     * {@link #optionText}
     */
    public String getOptionText() {
        return optionText;
    }

    /**
     * {@link #optionText}
     */
    public void setOptionText(String optionText) {
        this.optionText = optionText == null ? null : optionText.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "optionId";
    }

    @Override
    public Integer getIdValue() {
        return optionId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.optionId = id;
    }

    public static JSONObject toJSON(ItSurveyOption e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<ItSurveyOption> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ItSurveyOption.toJSON(this);
    }

    public static class Fields {
        public static final String OPTION_ID = "optionId";

        public static final String SURVEY_ID = "surveyId";

        public static final String QUESTION_ID = "questionId";

        public static final String OPTION_CODE = "optionCode";

        public static final String OPTION_TEXT = "optionText";
    }

    public static class Query {
        public static final String OPTION_ID__NE = "ne_optionId";

        public static final String OPTION_ID__IN = "list_optionId";

        public static final String OPTION_ID__BEGIN = "begin_optionId";

        public static final String OPTION_ID__END = "end_optionId";

        public static final String SURVEY_ID__NE = "ne_surveyId";

        public static final String SURVEY_ID__IN = "list_surveyId";

        public static final String SURVEY_ID__BEGIN = "begin_surveyId";

        public static final String SURVEY_ID__END = "end_surveyId";

        public static final String QUESTION_ID__NE = "ne_questionId";

        public static final String QUESTION_ID__IN = "list_questionId";

        public static final String QUESTION_ID__BEGIN = "begin_questionId";

        public static final String QUESTION_ID__END = "end_questionId";

        public static final String OPTION_CODE__NE = "ne_optionCode";

        public static final String OPTION_CODE__IN = "list_optionCode";

        public static final String OPTION_CODE__BEGIN = "begin_optionCode";

        public static final String OPTION_CODE__END = "end_optionCode";

        public static final String OPTION_TEXT__NE = "ne_optionText";

        public static final String OPTION_TEXT__LIKE = "like_optionText";

        public static final String OPTION_TEXT__IN = "list_optionText";

        public static final String OPTION_TEXT__BEGIN = "begin_optionText";

        public static final String OPTION_TEXT__END = "end_optionText";
    }
}