package cn.com.hugedata.spark.management.features.GaMessagePost;

import cn.com.hugedata.spark.commons.storage.entity.GaMessagePost;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class GaMessagePostModel extends GaMessagePost implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return String.valueOf(getPostId());
    }

    @Override
    public String findModelName() {
        return "";
    }

}
