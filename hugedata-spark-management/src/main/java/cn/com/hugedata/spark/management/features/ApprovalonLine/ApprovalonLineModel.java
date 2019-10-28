package cn.com.hugedata.spark.management.features.ApprovalonLine;

import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class ApprovalonLineModel extends ApServiceInfo implements FeatureModel {

    private static final long serialVersionUID = 4301871757760568985L;
    
    private String attachmentsJson;
    
    private String attConfigsJson;
    
    private String procListJson;

    @Override
    public String findModelId() {
        return String.valueOf(getServiceId());
    }

    @Override
    public String findModelName() {
        return getBusinessNo();
    }

    public String getAttachmentsJson() {
        return attachmentsJson;
    }

    public void setAttachmentsJson(String attachmentsJson) {
        this.attachmentsJson = attachmentsJson;
    }

    public String getAttConfigsJson() {
        return attConfigsJson;
    }

    public void setAttConfigsJson(String attConfigsJson) {
        this.attConfigsJson = attConfigsJson;
    }

    public String getProcListJson() {
        return procListJson;
    }

    public void setProcListJson(String procListJson) {
        this.procListJson = procListJson;
    }
    
}
