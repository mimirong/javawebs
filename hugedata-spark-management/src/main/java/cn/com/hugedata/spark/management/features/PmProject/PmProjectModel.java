package cn.com.hugedata.spark.management.features.PmProject;

import cn.com.hugedata.spark.commons.storage.entity.PmProject;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class PmProjectModel extends PmProject implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return String.valueOf(getProjectId());
    }

    @Override
    public String findModelName() {
        return getProjectName();
    }

}
