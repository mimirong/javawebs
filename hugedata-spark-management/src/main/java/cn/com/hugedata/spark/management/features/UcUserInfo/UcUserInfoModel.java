package cn.com.hugedata.spark.management.features.UcUserInfo;

import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class UcUserInfoModel extends UcUserInfo implements FeatureModel {

    private static final long serialVersionUID = 1L;
    
    private String deptJson;

    @Override
    public String findModelId() {
        return "" + getUserId();
    }

    @Override
    public String findModelName() {
        return getName();
    }

    public String getDeptJson() {
        return deptJson;
    }

    public void setDeptJson(String deptJson) {
        this.deptJson = deptJson;
    }
}
