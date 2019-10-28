package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.UcUserInfoConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UcUserInfo extends BaseEntity<Integer> implements UcUserInfoConstants {
    /**
     * userId
     */
    private Integer userId;

    /**
     * refUserId
     */
    private String refUserId;

    /**
     * userType
     */
    private String userType;

    /**
     * loginName
     */
    private String loginName;

    /**
     * loginNameUpper
     */
    private String loginNameUpper;

    /**
     * passwordHash
     */
    private String passwordHash;

    /**
     * password
     */
    private String password;

    /**
     * passwordKey
     */
    private String passwordKey;

    /**
     * deptId
     */
    private Integer deptId;

    /**
     * name
     */
    private String name;

    /**
     * idCard
     */
    private String idCard;

    /**
     * mobile
     */
    private String mobile;

    /**
     * telephone
     */
    private String telephone;

    /**
     * email
     */
    private String email;

    /**
     * emailUpper
     */
    private String emailUpper;

    /**
     * fullAddress
     */
    private String fullAddress;

    /**
     * headImagePreviewId
     */
    private String headImagePreviewId;

    /**
     * headImageId
     */
    private String headImageId;

    /**
     * sex
     */
    private String sex;

    /**
     * birthday
     */
    private String birthday;

    /**
     * companyName
     */
    private String companyName;

    /**
     * approveStatus
     */
    private String approveStatus;

    /**
     * usable
     */
    private Boolean usable;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * windowId
     */
    private Integer windowId;

    /**
     * companyId
     */
    private Integer companyId;

    /**
     * windowName
     */
    private String windowName;

    /**
     * deptName
     */
    private String deptName;

    /**
     * serviceRole
     */
    private String serviceRole;

    private static final long serialVersionUID = -4861513716245127040L;

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
     * {@link #refUserId}
     */
    public String getRefUserId() {
        return refUserId;
    }

    /**
     * {@link #refUserId}
     */
    public void setRefUserId(String refUserId) {
        this.refUserId = refUserId == null ? null : refUserId.trim();
    }

    /**
     * {@link #userType}
     */
    public String getUserType() {
        return userType;
    }

    /**
     * {@link #userType}
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * {@link #loginName}
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * {@link #loginName}
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * {@link #loginNameUpper}
     */
    public String getLoginNameUpper() {
        return loginNameUpper;
    }

    /**
     * {@link #loginNameUpper}
     */
    public void setLoginNameUpper(String loginNameUpper) {
        this.loginNameUpper = loginNameUpper == null ? null : loginNameUpper.trim();
    }

    /**
     * {@link #passwordHash}
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * {@link #passwordHash}
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash == null ? null : passwordHash.trim();
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
     * {@link #passwordKey}
     */
    public String getPasswordKey() {
        return passwordKey;
    }

    /**
     * {@link #passwordKey}
     */
    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey == null ? null : passwordKey.trim();
    }

    /**
     * {@link #deptId}
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * {@link #deptId}
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * {@link #name}
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * {@link #idCard}
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * {@link #idCard}
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
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
     * {@link #telephone}
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * {@link #telephone}
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
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
     * {@link #emailUpper}
     */
    public String getEmailUpper() {
        return emailUpper;
    }

    /**
     * {@link #emailUpper}
     */
    public void setEmailUpper(String emailUpper) {
        this.emailUpper = emailUpper == null ? null : emailUpper.trim();
    }

    /**
     * {@link #fullAddress}
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * {@link #fullAddress}
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress == null ? null : fullAddress.trim();
    }

    /**
     * {@link #headImagePreviewId}
     */
    public String getHeadImagePreviewId() {
        return headImagePreviewId;
    }

    /**
     * {@link #headImagePreviewId}
     */
    public void setHeadImagePreviewId(String headImagePreviewId) {
        this.headImagePreviewId = headImagePreviewId == null ? null : headImagePreviewId.trim();
    }

    /**
     * {@link #headImageId}
     */
    public String getHeadImageId() {
        return headImageId;
    }

    /**
     * {@link #headImageId}
     */
    public void setHeadImageId(String headImageId) {
        this.headImageId = headImageId == null ? null : headImageId.trim();
    }

    /**
     * {@link #sex}
     */
    public String getSex() {
        return sex;
    }

    /**
     * {@link #sex}
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * {@link #birthday}
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * {@link #birthday}
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * {@link #companyName}
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * {@link #companyName}
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * {@link #approveStatus}
     */
    public String getApproveStatus() {
        return approveStatus;
    }

    /**
     * {@link #approveStatus}
     */
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
    }

    /**
     * {@link #usable}
     */
    public Boolean getUsable() {
        return usable;
    }

    /**
     * {@link #usable}
     */
    public void setUsable(Boolean usable) {
        this.usable = usable;
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
     * {@link #windowId}
     */
    public Integer getWindowId() {
        return windowId;
    }

    /**
     * {@link #windowId}
     */
    public void setWindowId(Integer windowId) {
        this.windowId = windowId;
    }

    /**
     * {@link #companyId}
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * {@link #companyId}
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * {@link #windowName}
     */
    public String getWindowName() {
        return windowName;
    }

    /**
     * {@link #windowName}
     */
    public void setWindowName(String windowName) {
        this.windowName = windowName == null ? null : windowName.trim();
    }

    /**
     * {@link #deptName}
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * {@link #deptName}
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * {@link #serviceRole}
     */
    public String getServiceRole() {
        return serviceRole;
    }

    /**
     * {@link #serviceRole}
     */
    public void setServiceRole(String serviceRole) {
        this.serviceRole = serviceRole == null ? null : serviceRole.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "userId";
    }

    @Override
    public Integer getIdValue() {
        return userId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.userId = id;
    }

    public static JSONObject toJSON(UcUserInfo e) {
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

    public static List<JSONObject> toJSON(List<UcUserInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcUserInfo.toJSON(this);
    }

    public static class Fields {
        public static final String USER_ID = "userId";

        public static final String REF_USER_ID = "refUserId";

        public static final String USER_TYPE = "userType";

        public static final String LOGIN_NAME = "loginName";

        public static final String LOGIN_NAME_UPPER = "loginNameUpper";

        public static final String PASSWORD_HASH = "passwordHash";

        public static final String PASSWORD = "password";

        public static final String PASSWORD_KEY = "passwordKey";

        public static final String DEPT_ID = "deptId";

        public static final String NAME = "name";

        public static final String ID_CARD = "idCard";

        public static final String MOBILE = "mobile";

        public static final String TELEPHONE = "telephone";

        public static final String EMAIL = "email";

        public static final String EMAIL_UPPER = "emailUpper";

        public static final String FULL_ADDRESS = "fullAddress";

        public static final String HEAD_IMAGE_PREVIEW_ID = "headImagePreviewId";

        public static final String HEAD_IMAGE_ID = "headImageId";

        public static final String SEX = "sex";

        public static final String BIRTHDAY = "birthday";

        public static final String COMPANY_NAME = "companyName";

        public static final String APPROVE_STATUS = "approveStatus";

        public static final String USABLE = "usable";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String WINDOW_ID = "windowId";

        public static final String COMPANY_ID = "companyId";

        public static final String WINDOW_NAME = "windowName";

        public static final String DEPT_NAME = "deptName";

        public static final String SERVICE_ROLE = "serviceRole";
    }

    public static class Query {
        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String REF_USER_ID__NE = "ne_refUserId";

        public static final String REF_USER_ID__LIKE = "like_refUserId";

        public static final String REF_USER_ID__IN = "list_refUserId";

        public static final String REF_USER_ID__BEGIN = "begin_refUserId";

        public static final String REF_USER_ID__END = "end_refUserId";

        public static final String USER_TYPE__NE = "ne_userType";

        public static final String USER_TYPE__LIKE = "like_userType";

        public static final String USER_TYPE__IN = "list_userType";

        public static final String USER_TYPE__BEGIN = "begin_userType";

        public static final String USER_TYPE__END = "end_userType";

        public static final String LOGIN_NAME__NE = "ne_loginName";

        public static final String LOGIN_NAME__LIKE = "like_loginName";

        public static final String LOGIN_NAME__IN = "list_loginName";

        public static final String LOGIN_NAME__BEGIN = "begin_loginName";

        public static final String LOGIN_NAME__END = "end_loginName";

        public static final String LOGIN_NAME_UPPER__NE = "ne_loginNameUpper";

        public static final String LOGIN_NAME_UPPER__LIKE = "like_loginNameUpper";

        public static final String LOGIN_NAME_UPPER__IN = "list_loginNameUpper";

        public static final String LOGIN_NAME_UPPER__BEGIN = "begin_loginNameUpper";

        public static final String LOGIN_NAME_UPPER__END = "end_loginNameUpper";

        public static final String PASSWORD_HASH__NE = "ne_passwordHash";

        public static final String PASSWORD_HASH__LIKE = "like_passwordHash";

        public static final String PASSWORD_HASH__IN = "list_passwordHash";

        public static final String PASSWORD_HASH__BEGIN = "begin_passwordHash";

        public static final String PASSWORD_HASH__END = "end_passwordHash";

        public static final String PASSWORD__NE = "ne_password";

        public static final String PASSWORD__LIKE = "like_password";

        public static final String PASSWORD__IN = "list_password";

        public static final String PASSWORD__BEGIN = "begin_password";

        public static final String PASSWORD__END = "end_password";

        public static final String PASSWORD_KEY__NE = "ne_passwordKey";

        public static final String PASSWORD_KEY__LIKE = "like_passwordKey";

        public static final String PASSWORD_KEY__IN = "list_passwordKey";

        public static final String PASSWORD_KEY__BEGIN = "begin_passwordKey";

        public static final String PASSWORD_KEY__END = "end_passwordKey";

        public static final String DEPT_ID__NE = "ne_deptId";

        public static final String DEPT_ID__IN = "list_deptId";

        public static final String DEPT_ID__BEGIN = "begin_deptId";

        public static final String DEPT_ID__END = "end_deptId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String ID_CARD__NE = "ne_idCard";

        public static final String ID_CARD__LIKE = "like_idCard";

        public static final String ID_CARD__IN = "list_idCard";

        public static final String ID_CARD__BEGIN = "begin_idCard";

        public static final String ID_CARD__END = "end_idCard";

        public static final String MOBILE__NE = "ne_mobile";

        public static final String MOBILE__LIKE = "like_mobile";

        public static final String MOBILE__IN = "list_mobile";

        public static final String MOBILE__BEGIN = "begin_mobile";

        public static final String MOBILE__END = "end_mobile";

        public static final String TELEPHONE__NE = "ne_telephone";

        public static final String TELEPHONE__LIKE = "like_telephone";

        public static final String TELEPHONE__IN = "list_telephone";

        public static final String TELEPHONE__BEGIN = "begin_telephone";

        public static final String TELEPHONE__END = "end_telephone";

        public static final String EMAIL__NE = "ne_email";

        public static final String EMAIL__LIKE = "like_email";

        public static final String EMAIL__IN = "list_email";

        public static final String EMAIL__BEGIN = "begin_email";

        public static final String EMAIL__END = "end_email";

        public static final String EMAIL_UPPER__NE = "ne_emailUpper";

        public static final String EMAIL_UPPER__LIKE = "like_emailUpper";

        public static final String EMAIL_UPPER__IN = "list_emailUpper";

        public static final String EMAIL_UPPER__BEGIN = "begin_emailUpper";

        public static final String EMAIL_UPPER__END = "end_emailUpper";

        public static final String FULL_ADDRESS__NE = "ne_fullAddress";

        public static final String FULL_ADDRESS__LIKE = "like_fullAddress";

        public static final String FULL_ADDRESS__IN = "list_fullAddress";

        public static final String FULL_ADDRESS__BEGIN = "begin_fullAddress";

        public static final String FULL_ADDRESS__END = "end_fullAddress";

        public static final String HEAD_IMAGE_PREVIEW_ID__NE = "ne_headImagePreviewId";

        public static final String HEAD_IMAGE_PREVIEW_ID__LIKE = "like_headImagePreviewId";

        public static final String HEAD_IMAGE_PREVIEW_ID__IN = "list_headImagePreviewId";

        public static final String HEAD_IMAGE_PREVIEW_ID__BEGIN = "begin_headImagePreviewId";

        public static final String HEAD_IMAGE_PREVIEW_ID__END = "end_headImagePreviewId";

        public static final String HEAD_IMAGE_ID__NE = "ne_headImageId";

        public static final String HEAD_IMAGE_ID__LIKE = "like_headImageId";

        public static final String HEAD_IMAGE_ID__IN = "list_headImageId";

        public static final String HEAD_IMAGE_ID__BEGIN = "begin_headImageId";

        public static final String HEAD_IMAGE_ID__END = "end_headImageId";

        public static final String SEX__NE = "ne_sex";

        public static final String SEX__LIKE = "like_sex";

        public static final String SEX__IN = "list_sex";

        public static final String SEX__BEGIN = "begin_sex";

        public static final String SEX__END = "end_sex";

        public static final String BIRTHDAY__NE = "ne_birthday";

        public static final String BIRTHDAY__LIKE = "like_birthday";

        public static final String BIRTHDAY__IN = "list_birthday";

        public static final String BIRTHDAY__BEGIN = "begin_birthday";

        public static final String BIRTHDAY__END = "end_birthday";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String APPROVE_STATUS__NE = "ne_approveStatus";

        public static final String APPROVE_STATUS__LIKE = "like_approveStatus";

        public static final String APPROVE_STATUS__IN = "list_approveStatus";

        public static final String APPROVE_STATUS__BEGIN = "begin_approveStatus";

        public static final String APPROVE_STATUS__END = "end_approveStatus";

        public static final String USABLE__NE = "ne_usable";

        public static final String USABLE__IN = "list_usable";

        public static final String USABLE__BEGIN = "begin_usable";

        public static final String USABLE__END = "end_usable";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String WINDOW_ID__NE = "ne_windowId";

        public static final String WINDOW_ID__IN = "list_windowId";

        public static final String WINDOW_ID__BEGIN = "begin_windowId";

        public static final String WINDOW_ID__END = "end_windowId";

        public static final String COMPANY_ID__NE = "ne_companyId";

        public static final String COMPANY_ID__IN = "list_companyId";

        public static final String COMPANY_ID__BEGIN = "begin_companyId";

        public static final String COMPANY_ID__END = "end_companyId";

        public static final String WINDOW_NAME__NE = "ne_windowName";

        public static final String WINDOW_NAME__LIKE = "like_windowName";

        public static final String WINDOW_NAME__IN = "list_windowName";

        public static final String WINDOW_NAME__BEGIN = "begin_windowName";

        public static final String WINDOW_NAME__END = "end_windowName";

        public static final String DEPT_NAME__NE = "ne_deptName";

        public static final String DEPT_NAME__LIKE = "like_deptName";

        public static final String DEPT_NAME__IN = "list_deptName";

        public static final String DEPT_NAME__BEGIN = "begin_deptName";

        public static final String DEPT_NAME__END = "end_deptName";

        public static final String SERVICE_ROLE__NE = "ne_serviceRole";

        public static final String SERVICE_ROLE__LIKE = "like_serviceRole";

        public static final String SERVICE_ROLE__IN = "list_serviceRole";

        public static final String SERVICE_ROLE__BEGIN = "begin_serviceRole";

        public static final String SERVICE_ROLE__END = "end_serviceRole";
    }
}