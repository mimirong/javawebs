package cn.com.hugedata.spark.management.features.OsTechServiceSpec;

import cn.com.hugedata.spark.commons.storage.entity.OsTechServiceSpec;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsTechServiceSpecModel extends OsTechServiceSpec implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return "" + getSpecId();
    }

    @Override
    public String findModelName() {
        return getSpecName();
    }

}
