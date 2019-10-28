package cn.com.hugedata.spark.govaffairs.model;



import cn.com.hugedata.spark.commons.storage.entity.ItSurvey;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class SurveyModel implements FeatureModel{
	
	ItSurvey itSurvey;
	
	Boolean isExpired;
	
	public ItSurvey getItSurvey() {
		return itSurvey;
	}

	public void setItSurvey(ItSurvey itSurvey) {
		this.itSurvey = itSurvey;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

	

	@Override
	public String findModelId() {
		return null;
	}

	@Override
	public String findModelName() {
		return null;
		
	}
    
}
