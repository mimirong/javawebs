package cn.com.hugedata.spark.management.features.OsLinkUrlMan;

import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsLinkUrlManModel extends PtTechImage implements FeatureModel {

    private static final long serialVersionUID = 1L;

    @Override
    public String findModelId() {
        return "" + getFileId();
    }

    @Override
    public String findModelName() {
        return getFileName();
    }

}
