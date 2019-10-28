package cn.com.hugedata.spark.management.features.PtHomeImage;

import cn.com.hugedata.spark.commons.storage.entity.PtHomeImage;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class PtHomeImageModel extends PtHomeImage implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return getImageId() + "";
    }

    @Override
    public String findModelName() {
        return getName();
    }

}
