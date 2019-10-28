package cn.com.hugedata.spark.commons.web.features;

/**
 * 表示一个无效的FeatureModel.
 * @author 高鹏
 */
public class NullModel implements FeatureModel {

    @Override
    public String findModelId() {
        return null;
    }

    @Override
    public String findModelName() {
        return null;
    }

}
