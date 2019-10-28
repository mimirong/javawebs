package cn.com.hugedata.spark.management.features.GaServiceGuide;

import cn.com.hugedata.spark.commons.storage.entity.GaServiceGuide;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class GaServiceGuideModel extends GaServiceGuide implements FeatureModel {

    private static final long serialVersionUID = 1L;

    private String attachmentData;
    
    private String attConfigJson;

    @Override
    public String findModelId() {
        return "" + getGuideId();
    }

    @Override
    public String findModelName() {
        return getGuideName();
    }

    public String getAttConfigJson() {
        return attConfigJson;
    }

    public void setAttConfigJson(String attConfigJson) {
        this.attConfigJson = attConfigJson;
    }

    public String getAttachmentData() {
        return attachmentData;
    }

    public void setAttachmentData(String attachmentData) {
        this.attachmentData = attachmentData;
    }
}
