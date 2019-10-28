package cn.com.hugedata.spark.commons.storage.queryentity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;

public class GaPmSummaryQuery extends BaseEntity<String> {
    
    private static final long serialVersionUID = 4558060856587770731L;

    private String id;
    
    private String companyName;
    
    private String userId;
    
    private String month;
    
    private String propertyAmount;
    
    private String waterAmount;
    
    private String powerAmount;

    @Override
    public String getIdValue() {
        return id;
    }

    @Override
    public void setIdValue(String id) {
        this.id = id;
    }

    @Override
    public String getIdPropertyName() {
        return "id";
    }

    @Override
    public JSONObject toJSON() {
        return (JSONObject) JSON.toJSON(this);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPropertyAmount() {
        return propertyAmount;
    }

    public void setPropertyAmount(String propertyAmount) {
        this.propertyAmount = propertyAmount;
    }

    public String getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(String waterAmount) {
        this.waterAmount = waterAmount;
    }

    public String getPowerAmount() {
        return powerAmount;
    }

    public void setPowerAmount(String powerAmount) {
        this.powerAmount = powerAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
