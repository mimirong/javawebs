package cn.com.hugedata.spark.management.features.GaParkQuitGuide;

import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitGuide;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class GaParkQuitGuideModel extends GaParkQuitGuide implements FeatureModel {

    private static final long serialVersionUID = -5527304202839539619L;
    
    private String publishTimeStr;

    @Override
    public String findModelId() {
        return "" + getGuideId();
    }

    @Override
    public String findModelName() {
        return getTitle();
    }

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
    }

}
