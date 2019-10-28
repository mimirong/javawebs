package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaServiceGuideAttConfig extends BaseEntity<Integer> {
    /**
     * attConfigId
     */
    private Integer attConfigId;

    /**
     * attConfigName
     */
    private String attConfigName;

    /**
     * guideId
     */
    private Integer guideId;

    /**
     * isRequired
     */
    private Boolean isRequired;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = 6712768634021167150L;

    /**
     * {@link #attConfigId}
     */
    public Integer getAttConfigId() {
        return attConfigId;
    }

    /**
     * {@link #attConfigId}
     */
    public void setAttConfigId(Integer attConfigId) {
        this.attConfigId = attConfigId;
    }

    /**
     * {@link #attConfigName}
     */
    public String getAttConfigName() {
        return attConfigName;
    }

    /**
     * {@link #attConfigName}
     */
    public void setAttConfigName(String attConfigName) {
        this.attConfigName = attConfigName == null ? null : attConfigName.trim();
    }

    /**
     * {@link #guideId}
     */
    public Integer getGuideId() {
        return guideId;
    }

    /**
     * {@link #guideId}
     */
    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    /**
     * {@link #isRequired}
     */
    public Boolean getIsRequired() {
        return isRequired;
    }

    /**
     * {@link #isRequired}
     */
    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
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

    @Override
    public String getIdPropertyName() {
        return "attConfigId";
    }

    @Override
    public Integer getIdValue() {
        return attConfigId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.attConfigId = id;
    }

    public static JSONObject toJSON(GaServiceGuideAttConfig e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<GaServiceGuideAttConfig> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaServiceGuideAttConfig.toJSON(this);
    }

    public static class Fields {
        public static final String ATT_CONFIG_ID = "attConfigId";

        public static final String ATT_CONFIG_NAME = "attConfigName";

        public static final String GUIDE_ID = "guideId";

        public static final String IS_REQUIRED = "isRequired";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String ATT_CONFIG_ID__NE = "ne_attConfigId";

        public static final String ATT_CONFIG_ID__IN = "list_attConfigId";

        public static final String ATT_CONFIG_ID__BEGIN = "begin_attConfigId";

        public static final String ATT_CONFIG_ID__END = "end_attConfigId";

        public static final String ATT_CONFIG_NAME__NE = "ne_attConfigName";

        public static final String ATT_CONFIG_NAME__LIKE = "like_attConfigName";

        public static final String ATT_CONFIG_NAME__IN = "list_attConfigName";

        public static final String ATT_CONFIG_NAME__BEGIN = "begin_attConfigName";

        public static final String ATT_CONFIG_NAME__END = "end_attConfigName";

        public static final String GUIDE_ID__NE = "ne_guideId";

        public static final String GUIDE_ID__IN = "list_guideId";

        public static final String GUIDE_ID__BEGIN = "begin_guideId";

        public static final String GUIDE_ID__END = "end_guideId";

        public static final String IS_REQUIRED__NE = "ne_isRequired";

        public static final String IS_REQUIRED__IN = "list_isRequired";

        public static final String IS_REQUIRED__BEGIN = "begin_isRequired";

        public static final String IS_REQUIRED__END = "end_isRequired";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";
    }
}