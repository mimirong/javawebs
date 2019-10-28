package cn.com.hugedata.spark.management.features.PtNoticeBanner;

import cn.com.hugedata.spark.commons.storage.entity.PtNoticeBanner;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class PtNoticeBannerModel extends PtNoticeBanner implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return "" + getImageId();
    }

    @Override
    public String findModelName() {
        return "" + getImageId();
    }

}
