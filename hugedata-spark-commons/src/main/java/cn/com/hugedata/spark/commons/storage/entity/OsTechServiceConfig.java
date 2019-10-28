package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.OsTechServiceConfigConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OsTechServiceConfig extends BaseEntity<String> implements OsTechServiceConfigConstants {
    /**
     * configName
     */
    private String configName;

    /**
     * configType
     */
    private String configType;

    /**
     * configValue
     */
    private String configValue;

    private static final long serialVersionUID = -2844779027542101853L;

    /**
     * {@link #configName}
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * {@link #configName}
     */
    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    /**
     * {@link #configType}
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * {@link #configType}
     */
    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
    }

    /**
     * {@link #configValue}
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * {@link #configValue}
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "configName";
    }

    @Override
    public String getIdValue() {
        return configName;
    }

    @Override
    public void setIdValue(String id) {
        this.configName = id;
    }

    public static JSONObject toJSON(OsTechServiceConfig e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<OsTechServiceConfig> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return OsTechServiceConfig.toJSON(this);
    }

    public static class Fields {
        public static final String CONFIG_NAME = "configName";

        public static final String CONFIG_TYPE = "configType";

        public static final String CONFIG_VALUE = "configValue";
    }

    public static class Query {
        public static final String CONFIG_NAME__NE = "ne_configName";

        public static final String CONFIG_NAME__LIKE = "like_configName";

        public static final String CONFIG_NAME__IN = "list_configName";

        public static final String CONFIG_NAME__BEGIN = "begin_configName";

        public static final String CONFIG_NAME__END = "end_configName";

        public static final String CONFIG_TYPE__NE = "ne_configType";

        public static final String CONFIG_TYPE__LIKE = "like_configType";

        public static final String CONFIG_TYPE__IN = "list_configType";

        public static final String CONFIG_TYPE__BEGIN = "begin_configType";

        public static final String CONFIG_TYPE__END = "end_configType";

        public static final String CONFIG_VALUE__NE = "ne_configValue";

        public static final String CONFIG_VALUE__LIKE = "like_configValue";

        public static final String CONFIG_VALUE__IN = "list_configValue";

        public static final String CONFIG_VALUE__BEGIN = "begin_configValue";

        public static final String CONFIG_VALUE__END = "end_configValue";
    }
}