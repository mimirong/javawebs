package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.UcCompanyInfoConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UcCompanyInfo extends BaseEntity<Integer> implements UcCompanyInfoConstants {
    /**
     * companyId
     */
    private Integer companyId;

    /**
     * companyName
     */
    private String companyName;

    /**
     * companyType
     */
    private String companyType;

    /**
     * companyAddress
     */
    private String companyAddress;

    /**
     * organizationCode
     */
    private String organizationCode;

    /**
     * licenceCode
     */
    private String licenceCode;

    /**
     * representative
     */
    private String representative;

    /**
     * representativeId
     */
    private String representativeId;

    /**
     * contactName
     */
    private String contactName;

    /**
     * contactMobile
     */
    private String contactMobile;

    /**
     * contactEmail
     */
    private String contactEmail;

    /**
     * creatorUserId
     */
    private Integer creatorUserId;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    private static final long serialVersionUID = -8358432976767729083L;

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
     * {@link #companyType}
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * {@link #companyType}
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    /**
     * {@link #companyAddress}
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * {@link #companyAddress}
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    /**
     * {@link #organizationCode}
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     * {@link #organizationCode}
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    /**
     * {@link #licenceCode}
     */
    public String getLicenceCode() {
        return licenceCode;
    }

    /**
     * {@link #licenceCode}
     */
    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode == null ? null : licenceCode.trim();
    }

    /**
     * {@link #representative}
     */
    public String getRepresentative() {
        return representative;
    }

    /**
     * {@link #representative}
     */
    public void setRepresentative(String representative) {
        this.representative = representative == null ? null : representative.trim();
    }

    /**
     * {@link #representativeId}
     */
    public String getRepresentativeId() {
        return representativeId;
    }

    /**
     * {@link #representativeId}
     */
    public void setRepresentativeId(String representativeId) {
        this.representativeId = representativeId == null ? null : representativeId.trim();
    }

    /**
     * {@link #contactName}
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * {@link #contactName}
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * {@link #contactMobile}
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * {@link #contactMobile}
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    /**
     * {@link #contactEmail}
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * {@link #contactEmail}
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    /**
     * {@link #creatorUserId}
     */
    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    /**
     * {@link #creatorUserId}
     */
    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
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
        return "companyId";
    }

    @Override
    public Integer getIdValue() {
        return companyId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.companyId = id;
    }

    public static JSONObject toJSON(UcCompanyInfo e) {
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

    public static List<JSONObject> toJSON(List<UcCompanyInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcCompanyInfo.toJSON(this);
    }

    public static class Fields {
        public static final String COMPANY_ID = "companyId";

        public static final String COMPANY_NAME = "companyName";

        public static final String COMPANY_TYPE = "companyType";

        public static final String COMPANY_ADDRESS = "companyAddress";

        public static final String ORGANIZATION_CODE = "organizationCode";

        public static final String LICENCE_CODE = "licenceCode";

        public static final String REPRESENTATIVE = "representative";

        public static final String REPRESENTATIVE_ID = "representativeId";

        public static final String CONTACT_NAME = "contactName";

        public static final String CONTACT_MOBILE = "contactMobile";

        public static final String CONTACT_EMAIL = "contactEmail";

        public static final String CREATOR_USER_ID = "creatorUserId";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";
    }

    public static class Query {
        public static final String COMPANY_ID__NE = "ne_companyId";

        public static final String COMPANY_ID__IN = "list_companyId";

        public static final String COMPANY_ID__BEGIN = "begin_companyId";

        public static final String COMPANY_ID__END = "end_companyId";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String COMPANY_TYPE__NE = "ne_companyType";

        public static final String COMPANY_TYPE__LIKE = "like_companyType";

        public static final String COMPANY_TYPE__IN = "list_companyType";

        public static final String COMPANY_TYPE__BEGIN = "begin_companyType";

        public static final String COMPANY_TYPE__END = "end_companyType";

        public static final String COMPANY_ADDRESS__NE = "ne_companyAddress";

        public static final String COMPANY_ADDRESS__LIKE = "like_companyAddress";

        public static final String COMPANY_ADDRESS__IN = "list_companyAddress";

        public static final String COMPANY_ADDRESS__BEGIN = "begin_companyAddress";

        public static final String COMPANY_ADDRESS__END = "end_companyAddress";

        public static final String ORGANIZATION_CODE__NE = "ne_organizationCode";

        public static final String ORGANIZATION_CODE__LIKE = "like_organizationCode";

        public static final String ORGANIZATION_CODE__IN = "list_organizationCode";

        public static final String ORGANIZATION_CODE__BEGIN = "begin_organizationCode";

        public static final String ORGANIZATION_CODE__END = "end_organizationCode";

        public static final String LICENCE_CODE__NE = "ne_licenceCode";

        public static final String LICENCE_CODE__LIKE = "like_licenceCode";

        public static final String LICENCE_CODE__IN = "list_licenceCode";

        public static final String LICENCE_CODE__BEGIN = "begin_licenceCode";

        public static final String LICENCE_CODE__END = "end_licenceCode";

        public static final String REPRESENTATIVE__NE = "ne_representative";

        public static final String REPRESENTATIVE__LIKE = "like_representative";

        public static final String REPRESENTATIVE__IN = "list_representative";

        public static final String REPRESENTATIVE__BEGIN = "begin_representative";

        public static final String REPRESENTATIVE__END = "end_representative";

        public static final String REPRESENTATIVE_ID__NE = "ne_representativeId";

        public static final String REPRESENTATIVE_ID__LIKE = "like_representativeId";

        public static final String REPRESENTATIVE_ID__IN = "list_representativeId";

        public static final String REPRESENTATIVE_ID__BEGIN = "begin_representativeId";

        public static final String REPRESENTATIVE_ID__END = "end_representativeId";

        public static final String CONTACT_NAME__NE = "ne_contactName";

        public static final String CONTACT_NAME__LIKE = "like_contactName";

        public static final String CONTACT_NAME__IN = "list_contactName";

        public static final String CONTACT_NAME__BEGIN = "begin_contactName";

        public static final String CONTACT_NAME__END = "end_contactName";

        public static final String CONTACT_MOBILE__NE = "ne_contactMobile";

        public static final String CONTACT_MOBILE__LIKE = "like_contactMobile";

        public static final String CONTACT_MOBILE__IN = "list_contactMobile";

        public static final String CONTACT_MOBILE__BEGIN = "begin_contactMobile";

        public static final String CONTACT_MOBILE__END = "end_contactMobile";

        public static final String CONTACT_EMAIL__NE = "ne_contactEmail";

        public static final String CONTACT_EMAIL__LIKE = "like_contactEmail";

        public static final String CONTACT_EMAIL__IN = "list_contactEmail";

        public static final String CONTACT_EMAIL__BEGIN = "begin_contactEmail";

        public static final String CONTACT_EMAIL__END = "end_contactEmail";

        public static final String CREATOR_USER_ID__NE = "ne_creatorUserId";

        public static final String CREATOR_USER_ID__IN = "list_creatorUserId";

        public static final String CREATOR_USER_ID__BEGIN = "begin_creatorUserId";

        public static final String CREATOR_USER_ID__END = "end_creatorUserId";

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