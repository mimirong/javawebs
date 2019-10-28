package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UcPushDevice extends BaseEntity<String> {
    /**
     * registrationId
     */
    private String registrationId;

    /**
     * userId
     */
    private Integer userId;

    /**
     * platformCode
     */
    private String platformCode;

    /**
     * loginToken
     */
    private String loginToken;

    /**
     * updateTime
     */
    private Date updateTime;

    private static final long serialVersionUID = -2473406052603022117L;

    /**
     * {@link #registrationId}
     */
    public String getRegistrationId() {
        return registrationId;
    }

    /**
     * {@link #registrationId}
     */
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId == null ? null : registrationId.trim();
    }

    /**
     * {@link #userId}
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * {@link #userId}
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * {@link #platformCode}
     */
    public String getPlatformCode() {
        return platformCode;
    }

    /**
     * {@link #platformCode}
     */
    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode == null ? null : platformCode.trim();
    }

    /**
     * {@link #loginToken}
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * {@link #loginToken}
     */
    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken == null ? null : loginToken.trim();
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
        return "registrationId";
    }

    @Override
    public String getIdValue() {
        return registrationId;
    }

    @Override
    public void setIdValue(String id) {
        this.registrationId = id;
    }

    public static JSONObject toJSON(UcPushDevice e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcPushDevice> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcPushDevice.toJSON(this);
    }

    public static class Fields {
        public static final String REGISTRATION_ID = "registrationId";

        public static final String USER_ID = "userId";

        public static final String PLATFORM_CODE = "platformCode";

        public static final String LOGIN_TOKEN = "loginToken";

        public static final String UPDATE_TIME = "updateTime";
    }

    public static class Query {
        public static final String REGISTRATION_ID__NE = "ne_registrationId";

        public static final String REGISTRATION_ID__LIKE = "like_registrationId";

        public static final String REGISTRATION_ID__IN = "list_registrationId";

        public static final String REGISTRATION_ID__BEGIN = "begin_registrationId";

        public static final String REGISTRATION_ID__END = "end_registrationId";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String PLATFORM_CODE__NE = "ne_platformCode";

        public static final String PLATFORM_CODE__LIKE = "like_platformCode";

        public static final String PLATFORM_CODE__IN = "list_platformCode";

        public static final String PLATFORM_CODE__BEGIN = "begin_platformCode";

        public static final String PLATFORM_CODE__END = "end_platformCode";

        public static final String LOGIN_TOKEN__NE = "ne_loginToken";

        public static final String LOGIN_TOKEN__LIKE = "like_loginToken";

        public static final String LOGIN_TOKEN__IN = "list_loginToken";

        public static final String LOGIN_TOKEN__BEGIN = "begin_loginToken";

        public static final String LOGIN_TOKEN__END = "end_loginToken";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";
    }
}