package cn.com.hugedata.spark.management.features.UcCompanyUser;

import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class UcCompanyUserModel extends UcUserInfo implements FeatureModel {

    private static final long serialVersionUID = 1L;
    
    private String companyInfoJson;

    @Override
    public String findModelId() {
        return String.valueOf(getUserId());
    }

    @Override
    public String findModelName() {
        return getLoginName();
    }

    public String getCompanyInfoJson() {
        return companyInfoJson;
    }

    public void setCompanyInfoJson(String companyInfoJson) {
        this.companyInfoJson = companyInfoJson;
    }

}
