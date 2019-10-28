package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UcAdminUser extends BaseEntity<String> {
    /**
     * userId
     */
    private String userId;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * mobile
     */
    private String mobile;

    /**
     * email
     */
    private String email;

    /**
     * isAcceptMessage
     */
    private String isAcceptMessage;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * lastLoginTime
     */
    private Date lastLoginTime;

    private static final long serialVersionUID = -4555354384486390992L;

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
     * {@link #username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * {@link #username}
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * {@link #password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * {@link #password}
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
     * {@link #isAcceptMessage}
     */
    public String getIsAcceptMessage() {
        return isAcceptMessage;
    }

    /**
     * {@link #isAcceptMessage}
     */
    public void setIsAcceptMessage(String isAcceptMessage) {
        this.isAcceptMessage = isAcceptMessage == null ? null : isAcceptMessage.trim();
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
     * {@link #lastLoginTime}
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * {@link #lastLoginTime}
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String getIdPropertyName() {
        return "userId";
    }

    @Override
    public String getIdValue() {
        return userId;
    }

    @Override
    public void setIdValue(String id) {
        this.userId = id;
    }

    public static JSONObject toJSON(UcAdminUser e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getLastLoginTime() != null) {
            obj.put("lastLoginTimeStr", fmt.format(e.getLastLoginTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcAdminUser> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcAdminUser.toJSON(this);
    }

    public static class Fields {
        public static final String USER_ID = "userId";

        public static final String USERNAME = "username";

        public static final String PASSWORD = "password";

        public static final String MOBILE = "mobile";

        public static final String EMAIL = "email";

        public static final String IS_ACCEPT_MESSAGE = "isAcceptMessage";

        public static final String CREATE_TIME = "createTime";

        public static final String LAST_LOGIN_TIME = "lastLoginTime";
    }

    public static class Query {
        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__LIKE = "like_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String USERNAME__NE = "ne_username";

        public static final String USERNAME__LIKE = "like_username";

        public static final String USERNAME__IN = "list_username";

        public static final String USERNAME__BEGIN = "begin_username";

        public static final String USERNAME__END = "end_username";

        public static final String PASSWORD__NE = "ne_password";

        public static final String PASSWORD__LIKE = "like_password";

        public static final String PASSWORD__IN = "list_password";

        public static final String PASSWORD__BEGIN = "begin_password";

        public static final String PASSWORD__END = "end_password";

        public static final String MOBILE__NE = "ne_mobile";

        public static final String MOBILE__LIKE = "like_mobile";

        public static final String MOBILE__IN = "list_mobile";

        public static final String MOBILE__BEGIN = "begin_mobile";

        public static final String MOBILE__END = "end_mobile";

        public static final String EMAIL__NE = "ne_email";

        public static final String EMAIL__LIKE = "like_email";

        public static final String EMAIL__IN = "list_email";

        public static final String EMAIL__BEGIN = "begin_email";

        public static final String EMAIL__END = "end_email";

        public static final String IS_ACCEPT_MESSAGE__NE = "ne_isAcceptMessage";

        public static final String IS_ACCEPT_MESSAGE__LIKE = "like_isAcceptMessage";

        public static final String IS_ACCEPT_MESSAGE__IN = "list_isAcceptMessage";

        public static final String IS_ACCEPT_MESSAGE__BEGIN = "begin_isAcceptMessage";

        public static final String IS_ACCEPT_MESSAGE__END = "end_isAcceptMessage";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String LAST_LOGIN_TIME__NE = "ne_lastLoginTime";

        public static final String LAST_LOGIN_TIME__IN = "list_lastLoginTime";

        public static final String LAST_LOGIN_TIME__BEGIN = "begin_lastLoginTime";

        public static final String LAST_LOGIN_TIME__END = "end_lastLoginTime";
    }
}