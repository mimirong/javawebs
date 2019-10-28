package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.SpSpecificationConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpSpecification extends BaseEntity<Integer> implements SpSpecificationConstants {
    /**
     * specId
     */
    private Integer specId;

    /**
     * projectId
     */
    private String projectId;

    /**
     * specName
     */
    private String specName;

    /**
     * referPrice
     */
    private Float referPrice;

    /**
     * measureUnit
     */
    private String measureUnit;

    /**
     * isNegotiablePrice
     */
    private Boolean isNegotiablePrice;

    /**
     * priceUnit
     */
    private String priceUnit;

    /**
     * specBrief
     */
    private String specBrief;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    private static final long serialVersionUID = 6477216808320024487L;

    /**
     * {@link #specId}
     */
    public Integer getSpecId() {
        return specId;
    }

    /**
     * {@link #specId}
     */
    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    /**
     * {@link #projectId}
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * {@link #projectId}
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     * {@link #specName}
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * {@link #specName}
     */
    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    /**
     * {@link #referPrice}
     */
    public Float getReferPrice() {
        return referPrice;
    }

    /**
     * {@link #referPrice}
     */
    public void setReferPrice(Float referPrice) {
        this.referPrice = referPrice;
    }

    /**
     * {@link #measureUnit}
     */
    public String getMeasureUnit() {
        return measureUnit;
    }

    /**
     * {@link #measureUnit}
     */
    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit == null ? null : measureUnit.trim();
    }

    /**
     * {@link #isNegotiablePrice}
     */
    public Boolean getIsNegotiablePrice() {
        return isNegotiablePrice;
    }

    /**
     * {@link #isNegotiablePrice}
     */
    public void setIsNegotiablePrice(Boolean isNegotiablePrice) {
        this.isNegotiablePrice = isNegotiablePrice;
    }

    /**
     * {@link #priceUnit}
     */
    public String getPriceUnit() {
        return priceUnit;
    }

    /**
     * {@link #priceUnit}
     */
    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit == null ? null : priceUnit.trim();
    }

    /**
     * {@link #specBrief}
     */
    public String getSpecBrief() {
        return specBrief;
    }

    /**
     * {@link #specBrief}
     */
    public void setSpecBrief(String specBrief) {
        this.specBrief = specBrief == null ? null : specBrief.trim();
    }

    /**
     * {@link #createTime}
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * {@link #createTime}
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * {@link #updateTime}
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * {@link #updateTime}
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getIdPropertyName() {
        return "specId";
    }

    @Override
    public Integer getIdValue() {
        return specId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.specId = id;
    }

    public static JSONObject toJSON(SpSpecification e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<SpSpecification> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return SpSpecification.toJSON(this);
    }

    public static class Fields {
        public static final String SPEC_ID = "specId";

        public static final String PROJECT_ID = "projectId";

        public static final String SPEC_NAME = "specName";

        public static final String REFER_PRICE = "referPrice";

        public static final String MEASURE_UNIT = "measureUnit";

        public static final String IS_NEGOTIABLE_PRICE = "isNegotiablePrice";

        public static final String PRICE_UNIT = "priceUnit";

        public static final String SPEC_BRIEF = "specBrief";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";
    }

    public static class Query {
        public static final String SPEC_ID__NE = "ne_specId";

        public static final String SPEC_ID__IN = "list_specId";

        public static final String SPEC_ID__BEGIN = "begin_specId";

        public static final String SPEC_ID__END = "end_specId";

        public static final String PROJECT_ID__NE = "ne_projectId";

        public static final String PROJECT_ID__LIKE = "like_projectId";

        public static final String PROJECT_ID__IN = "list_projectId";

        public static final String PROJECT_ID__BEGIN = "begin_projectId";

        public static final String PROJECT_ID__END = "end_projectId";

        public static final String SPEC_NAME__NE = "ne_specName";

        public static final String SPEC_NAME__LIKE = "like_specName";

        public static final String SPEC_NAME__IN = "list_specName";

        public static final String SPEC_NAME__BEGIN = "begin_specName";

        public static final String SPEC_NAME__END = "end_specName";

        public static final String REFER_PRICE__NE = "ne_referPrice";

        public static final String REFER_PRICE__IN = "list_referPrice";

        public static final String REFER_PRICE__BEGIN = "begin_referPrice";

        public static final String REFER_PRICE__END = "end_referPrice";

        public static final String MEASURE_UNIT__NE = "ne_measureUnit";

        public static final String MEASURE_UNIT__LIKE = "like_measureUnit";

        public static final String MEASURE_UNIT__IN = "list_measureUnit";

        public static final String MEASURE_UNIT__BEGIN = "begin_measureUnit";

        public static final String MEASURE_UNIT__END = "end_measureUnit";

        public static final String IS_NEGOTIABLE_PRICE__NE = "ne_isNegotiablePrice";

        public static final String IS_NEGOTIABLE_PRICE__IN = "list_isNegotiablePrice";

        public static final String IS_NEGOTIABLE_PRICE__BEGIN = "begin_isNegotiablePrice";

        public static final String IS_NEGOTIABLE_PRICE__END = "end_isNegotiablePrice";

        public static final String PRICE_UNIT__NE = "ne_priceUnit";

        public static final String PRICE_UNIT__LIKE = "like_priceUnit";

        public static final String PRICE_UNIT__IN = "list_priceUnit";

        public static final String PRICE_UNIT__BEGIN = "begin_priceUnit";

        public static final String PRICE_UNIT__END = "end_priceUnit";

        public static final String SPEC_BRIEF__NE = "ne_specBrief";

        public static final String SPEC_BRIEF__LIKE = "like_specBrief";

        public static final String SPEC_BRIEF__IN = "list_specBrief";

        public static final String SPEC_BRIEF__BEGIN = "begin_specBrief";

        public static final String SPEC_BRIEF__END = "end_specBrief";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";
    }
}