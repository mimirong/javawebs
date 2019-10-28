package cn.com.hugedata.spark.management.features.OsTechImage;

import cn.com.hugedata.spark.commons.storage.entity.PtArticle;
import cn.com.hugedata.spark.commons.storage.entity.PtTechImage;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class OsTechImageModel extends PtTechImage implements FeatureModel {

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
