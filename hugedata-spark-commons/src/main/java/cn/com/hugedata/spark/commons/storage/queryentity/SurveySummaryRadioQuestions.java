package cn.com.hugedata.spark.commons.storage.queryentity;


/**
 * 表示调查征集功能中RADIO类型的选择结果.
 */
public class SurveySummaryRadioQuestions {

    /** 选项ID. */
    private String optionId;
    
    /** 选项描述. */
    private String resultText;
    
    public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	/** 计数. */
    private Integer count;

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
}
