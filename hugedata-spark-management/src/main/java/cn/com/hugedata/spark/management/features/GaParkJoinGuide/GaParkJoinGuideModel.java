package cn.com.hugedata.spark.management.features.GaParkJoinGuide;

import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinGuide;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class GaParkJoinGuideModel extends GaParkJoinGuide implements FeatureModel {

    private static final long serialVersionUID = -4001034577925228988L;
    
    private String publishTimeStr;

    @Override
    public String findModelId() {
        return "" + getGuideId();
    }

    @Override
    public String findModelName() {
        return "" + getTitle();
    }

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
    }

}
