package cn.com.hugedata.spark.management.features.OsExpertsList;

import cn.com.hugedata.spark.commons.storage.entity.OsExpertsList;
import cn.com.hugedata.spark.commons.storage.entity.PtHomeImage;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsExpertsListModel extends OsExpertsList implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return getExpertId() + "";
    }

    @Override
    public String findModelName() {
        return getName();
    }

}
