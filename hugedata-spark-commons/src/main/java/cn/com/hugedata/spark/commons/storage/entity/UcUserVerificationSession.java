package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.UcUserVerificationSessionConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UcUserVerificationSession extends BaseEntity<String> implements UcUserVerificationSessionConstants {
    /**
     * sessionId
     */
    private String sessionId;

    /**
     * sessionKey
     */
    private String sessionKey;

    /**
     * code
     */
    private String code;

    /**
     * verificationType
     */
    private String verificationType;

    /**
     * status
     */
    private String status;

    /**
     * email
     */
    private String email;

    /**
     * mobile
     */
    private String mobile;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * validityTime
     */
    private Date validityTime;

    /**
     * userId
     */
    private String userId;

    /**
     * extraData
     */
    private String extraData;

    /**
     * finishTime
     */
    private Date finishTime;

    private static final long serialVersionUID = 7660094506567229569L;

    /**
     * {@link #sessionId}
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * {@link #sessionId}
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * {@link #sessionKey}
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * {@link #sessionKey}
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey == null ? null : sessionKey.trim();
    }

    /**
     * {@link #code}
     */
    public String getCode() {
        return code;
    }

    /**
     * {@link #code}
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * {@link #verificationType}
     */
    public String getVerificationType() {
        return verificationType;
    }

    /**
     * {@link #verificationType}
     */
    public void setVerificationType(String verificationType) {
        this.verificationType = verificationType == null ? null : verificationType.trim();
    }

    /**
     * {@link #status}
     */
    public String getStatus() {
        return status;
    }

    /**
     * {@link #status}
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * {@link #email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * {@link #email}
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * {@link #mobile}
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * {@link #mobile}
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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
     * {@link #validityTime}
     */
    public Date getValidityTime() {
        return validityTime;
    }

    /**
     * {@link #validityTime}
     */
    public void setValidityTime(Date validityTime) {
        this.validityTime = validityTime;
    }

    /**
     * {@link #userId}
     */
    public String getUserId() {
        return userId;
    }

    /**
     * {@link #userId}
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * {@link #extraData}
     */
    public String getExtraData() {
        return extraData;
    }

    /**
     * {@link #extraData}
     */
    public void setExtraData(String extraData) {
        this.extraData = extraData == null ? null : extraData.trim();
    }

    /**
     * {@link #finishTime}
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * {@link #finishTime}
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String getIdPropertyName() {
        return "sessionId";
    }

    @Override
    public String getIdValue() {
        return sessionId;
    }

    @Override
    public void setIdValue(String id) {
        this.sessionId = id;
    }

    public static JSONObject toJSON(UcUserVerificationSession e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getValidityTime() != null) {
            obj.put("validityTimeStr", fmt.format(e.getValidityTime()));
        }
        if (e.getFinishTime() != null) {
            obj.put("finishTimeStr", fmt.format(e.getFinishTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcUserVerificationSession> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcUserVerificationSession.toJSON(this);
    }

    public static class Fields {
        public static final String SESSION_ID = "sessionId";

        public static final String SESSION_KEY = "sessionKey";

        public static final String CODE = "code";

        public static final String VERIFICATION_TYPE = "verificationType";

        public static final String STATUS = "status";

        public static final String EMAIL = "email";

        public static final String MOBILE = "mobile";

        public static final String CREATE_TIME = "createTime";

        public static final String VALIDITY_TIME = "validityTime";

        public static final String USER_ID = "userId";

        public static final String EXTRA_DATA = "extraData";

        public static final String FINISH_TIME = "finishTime";
    }

    public static class Query {
        public static final String SESSION_ID__NE = "ne_sessionId";

        public static final String SESSION_ID__LIKE = "like_sessionId";

        public static final String SESSION_ID__IN = "list_sessionId";

        public static final String SESSION_ID__BEGIN = "begin_sessionId";

        public static final String SESSION_ID__END = "end_sessionId";

        public static final String SESSION_KEY__NE = "ne_sessionKey";

        public static final String SESSION_KEY__LIKE = "like_sessionKey";

        public static final String SESSION_KEY__IN = "list_sessionKey";

        public static final String SESSION_KEY__BEGIN = "begin_sessionKey";

        public static final String SESSION_KEY__END = "end_sessionKey";

        public static final String CODE__NE = "ne_code";

        public static final String CODE__LIKE = "like_code";

        public static final String CODE__IN = "list_code";

        public static final String CODE__BEGIN = "begin_code";

        public static final String CODE__END = "end_code";

        public static final String VERIFICATION_TYPE__NE = "ne_verificationType";

        public static final String VERIFICATION_TYPE__LIKE = "like_verificationType";

        public static final String VERIFICATION_TYPE__IN = "list_verificationType";

        public static final String VERIFICATION_TYPE__BEGIN = "begin_verificationType";

        public static final String VERIFICATION_TYPE__END = "end_verificationType";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";

        public static final String EMAIL__NE = "ne_email";

        public static final String EMAIL__LIKE = "like_email";

        public static final String EMAIL__IN = "list_email";

        public static final String EMAIL__BEGIN = "begin_email";

        public static final String EMAIL__END = "end_email";

        public static final String MOBILE__NE = "ne_mobile";

        public static final String MOBILE__LIKE = "like_mobile";

        public static final String MOBILE__IN = "list_mobile";

        public static final String MOBILE__BEGIN = "begin_mobile";

        public static final String MOBILE__END = "end_mobile";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String VALIDITY_TIME__NE = "ne_validityTime";

        public static final String VALIDITY_TIME__IN = "list_validityTime";

        public static final String VALIDITY_TIME__BEGIN = "begin_validityTime";

        public static final String VALIDITY_TIME__END = "end_validityTime";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__LIKE = "like_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String EXTRA_DATA__NE = "ne_extraData";

        public static final String EXTRA_DATA__LIKE = "like_extraData";

        public static final String EXTRA_DATA__IN = "list_extraData";

        public static final String EXTRA_DATA__BEGIN = "begin_extraData";

        public static final String EXTRA_DATA__END = "end_extraData";

        public static final String FINISH_TIME__NE = "ne_finishTime";

        public static final String FINISH_TIME__IN = "list_finishTime";

        public static final String FINISH_TIME__BEGIN = "begin_finishTime";

        public static final String FINISH_TIME__END = "end_finishTime";
    }
}