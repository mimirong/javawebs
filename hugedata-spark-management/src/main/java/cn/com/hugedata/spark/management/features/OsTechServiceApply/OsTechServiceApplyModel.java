package cn.com.hugedata.spark.management.features.OsTechServiceApply;

import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceApply;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsTechServiceApplyModel extends OsTechServiceApply implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return "" + getApplyId();
    }

    @Override
    public String findModelName() {
        return "";
    }

}
