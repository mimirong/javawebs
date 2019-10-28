package cn.com.hugedata.spark.management.features.GaParkQuitApplication;

import cn.com.hugedata.spark.commons.storage.entity.GaParkQuitApplication;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class GaParkQuitApplicationModel extends GaParkQuitApplication implements FeatureModel {

    private static final long serialVersionUID = 2692418938129127861L;

    @Override
    public String findModelId() {
        return "" + getApplicationId();
    }

    @Override
    public String findModelName() {
        return "" + getCompanyName();
    }

}
