package cn.com.hugedata.spark.management.features.OsTechAchieve;

import cn.com.hugedata.spark.commons.storage.entity.OsExpertsList;
import cn.com.hugedata.spark.commons.storage.entity.OsTechAchieve;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsTechAchieveModel extends OsTechAchieve implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return getAchieveId() + "";
    }

    @Override
    public String findModelName() {
        return getName();
    }

}
