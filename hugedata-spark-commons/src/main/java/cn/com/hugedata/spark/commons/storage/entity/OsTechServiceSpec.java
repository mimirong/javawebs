package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.OsTechServiceSpecConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OsTechServiceSpec extends BaseEntity<Integer> implements OsTechServiceSpecConstants {
    /**
     * specId
     */
    private Integer specId;

    /**
     * specName
     */
    private String specName;

    /**
     * specType
     */
    private String specType;

    /**
     * remark
     */
    private String remark;

    /**
     * price
     */
    private Integer price;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * specData
     */
    private String specData;

    private static final long serialVersionUID = -1659135711781547835L;

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
     * {@link #specType}
     */
    public String getSpecType() {
        return specType;
    }

    /**
     * {@link #specType}
     */
    public void setSpecType(String specType) {
        this.specType = specType == null ? null : specType.trim();
    }

    /**
     * {@link #remark}
     */
    public String getRemark() {
        return remark;
    }

    /**
     * {@link #remark}
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * {@link #price}
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * {@link #price}
     */
    public void setPrice(Integer price) {
        this.price = price;
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

    /**
     * {@link #specData}
     */
    public String getSpecData() {
        return specData;
    }

    /**
     * {@link #specData}
     */
    public void setSpecData(String specData) {
        this.specData = specData == null ? null : specData.trim();
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

    public static JSONObject toJSON(OsTechServiceSpec e) {
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

    public static List<JSONObject> toJSON(List<OsTechServiceSpec> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return OsTechServiceSpec.toJSON(this);
    }

    public static class Fields {
        public static final String SPEC_ID = "specId";

        public static final String SPEC_NAME = "specName";

        public static final String SPEC_TYPE = "specType";

        public static final String REMARK = "remark";

        public static final String PRICE = "price";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String SPEC_DATA = "specData";
    }

    public static class Query {
        public static final String SPEC_ID__NE = "ne_specId";

        public static final String SPEC_ID__IN = "list_specId";

        public static final String SPEC_ID__BEGIN = "begin_specId";

        public static final String SPEC_ID__END = "end_specId";

        public static final String SPEC_NAME__NE = "ne_specName";

        public static final String SPEC_NAME__LIKE = "like_specName";

        public static final String SPEC_NAME__IN = "list_specName";

        public static final String SPEC_NAME__BEGIN = "begin_specName";

        public static final String SPEC_NAME__END = "end_specName";

        public static final String SPEC_TYPE__NE = "ne_specType";

        public static final String SPEC_TYPE__LIKE = "like_specType";

        public static final String SPEC_TYPE__IN = "list_specType";

        public static final String SPEC_TYPE__BEGIN = "begin_specType";

        public static final String SPEC_TYPE__END = "end_specType";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";

        public static final String PRICE__NE = "ne_price";

        public static final String PRICE__IN = "list_price";

        public static final String PRICE__BEGIN = "begin_price";

        public static final String PRICE__END = "end_price";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String SPEC_DATA__NE = "ne_specData";

        public static final String SPEC_DATA__LIKE = "like_specData";

        public static final String SPEC_DATA__IN = "list_specData";

        public static final String SPEC_DATA__BEGIN = "begin_specData";

        public static final String SPEC_DATA__END = "end_specData";
    }
}