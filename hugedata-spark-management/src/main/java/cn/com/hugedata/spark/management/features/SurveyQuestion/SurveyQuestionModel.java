package cn.com.hugedata.spark.management.features.SurveyQuestion;

import java.util.List;

import cn.com.hugedata.spark.commons.storage.entity.ItSurveyOption;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class SurveyQuestionModel implements FeatureModel{
    
    /**
     * questionId
     */
    private Integer questionId;

    /**
     * surveyId
     */
    private Integer surveyId;
    
    /**
     * title
     */
    private String title;

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
    
    private List<ItSurveyOption> itSurveyOptionList;


    @Override
    public String findModelId() {
        return String.valueOf(questionId);
    }

    @Override
    public String findModelName() {
        return questionText;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getConfigData() {
        return configData;
    }

    public void setConfigData(String configData) {
        this.configData = configData;
    }

    public String getSummaryData() {
        return summaryData;
    }

    public void setSummaryData(String summaryData) {
        this.summaryData = summaryData;
    }

	public List<ItSurveyOption> getItSurveyOptionList() {
		return itSurveyOptionList;
	}

	public void setItSurveyOptionList(List<ItSurveyOption> itSurveyOptionList) {
		this.itSurveyOptionList = itSurveyOptionList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
}
