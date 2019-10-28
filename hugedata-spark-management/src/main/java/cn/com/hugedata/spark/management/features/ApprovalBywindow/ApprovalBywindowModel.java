package cn.com.hugedata.spark.management.features.ApprovalBywindow;

import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class ApprovalBywindowModel extends ApServiceInfo implements FeatureModel{

    private static final long serialVersionUID = 1L;
    
    private String attachmentsJson;
    
    private String attConfigsJson;
    
    private String procListJson;

    @Override
    public String findModelId() {
        return "" + getServiceId();
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
