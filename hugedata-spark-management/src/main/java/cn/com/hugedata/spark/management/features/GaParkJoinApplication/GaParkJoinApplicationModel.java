package cn.com.hugedata.spark.management.features.GaParkJoinApplication;

import cn.com.hugedata.spark.commons.storage.entity.GaParkJoinApplication;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class GaParkJoinApplicationModel extends GaParkJoinApplication implements FeatureModel {

    private static final long serialVersionUID = -3676015387274612577L;

    @Override
    public String findModelId() {
        return "" + getApplicationId();
    }

    @Override
    public String findModelName() {
        return getCompanyName();
    }

}
