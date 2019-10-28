package cn.com.hugedata.spark.management.features.OsMeetingTraining;

import cn.com.hugedata.spark.commons.storage.entity.OsMeetingTraining;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsMeetingTrainingModel extends OsMeetingTraining implements FeatureModel {

    private static final long serialVersionUID = 1L;

    private String registrationTimeStr;
    private String registrationDeadlineStr;
    private String trainingStartTimeStr;
    private String trainingEndTimeStr;
    
    @Override
    public String findModelId() {
        return "" + getTrainingId();
    }

    @Override
    public String findModelName() {
        return getName();
    }

    public String getRegistrationTimeStr() {
        return registrationTimeStr;
    }

    public void setRegistrationTimeStr(String registrationTimeStr) {
        this.registrationTimeStr = registrationTimeStr;
    }

    public String getRegistrationDeadlineStr() {
        return registrationDeadlineStr;
    }

    public void setRegistrationDeadlineStr(String registrationDeadlineStr) {
        this.registrationDeadlineStr = registrationDeadlineStr;
    }

    public String getTrainingStartTimeStr() {
        return trainingStartTimeStr;
    }

    public void setTrainingStartTimeStr(String trainingStartTimeStr) {
        this.trainingStartTimeStr = trainingStartTimeStr;
    }

    public String getTrainingEndTimeStr() {
        return trainingEndTimeStr;
    }

    public void setTrainingEndTimeStr(String trainingEndTimeStr) {
        this.trainingEndTimeStr = trainingEndTimeStr;
    }
}
