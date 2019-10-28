package cn.com.hugedata.spark.management.features.OsDetecApprove;

import cn.com.hugedata.spark.commons.storage.entity.DdDetectionApply;
import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsDetecApproveModel extends DdDetectionApply implements FeatureModel {

    private static final long serialVersionUID = 1L;

    private String serialNum;

    @Override
    public String findModelId() {
        return "" + getApplyId();
    }

    @Override
    public String findModelName() {
        return getApplyType();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
}
