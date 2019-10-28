package cn.com.hugedata.spark.management.features.PmProject;

import cn.com.hugedata.spark.commons.storage.entity.PmDocType;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class PmDocTypeModel extends PmDocType implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return String.valueOf(getTypeId());
    }

    @Override
    public String findModelName() {
        return getTypeName();
    }

}
