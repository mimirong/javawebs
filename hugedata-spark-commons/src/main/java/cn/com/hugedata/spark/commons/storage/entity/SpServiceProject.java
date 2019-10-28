package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.SpServiceProjectConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpServiceProject extends BaseEntity<Integer> implements SpServiceProjectConstants {
    /**
     * projectId
     */
    private Integer projectId;

    /**
     * serviceField
     */
    private String serviceField;

    /**
     * supplyType
     */
    private String supplyType;

    /**
     * supplyTheme
     */
    private String supplyTheme;

    /**
     * supplyBrief
     */
    private String supplyBrief;

    /**
     * coverFileId
     */
    private String coverFileId;

    /**
     * coverFileName
     */
    private String coverFileName;

    /**
     * detailDesc
     */
    private String detailDesc;

    /**
     * searchKey
     */
    private String searchKey;

    /**
     * addressDetail
     */
    private String addressDetail;

    /**
     * contact
     */
    private String contact;

    /**
     * contactMobile
     */
    private String contactMobile;

    /**
     * email
     */
    private String email;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * visitorCount
     */
    private Integer visitorCount;

    /**
     * price
     */
    private String price;

    /**
     * publisherUserId
     */
    private Integer publisherUserId;

    /**
     * publisherName
     */
    private String publisherName;

    /**
     * companyId
     */
    private Integer companyId;

    /**
     * companyName
     */
    private String companyName;

    /**
     * addressProvince
     */
    private String addressProvince;

    private static final long serialVersionUID = 3613878946355466567L;

    /**
     * {@link #projectId}
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * {@link #projectId}
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * {@link #serviceField}
     */
    public String getServiceField() {
        return serviceField;
    }

    /**
     * {@link #serviceField}
     */
    public void setServiceField(String serviceField) {
        this.serviceField = serviceField == null ? null : serviceField.trim();
    }

    /**
     * {@link #supplyType}
     */
    public String getSupplyType() {
        return supplyType;
    }

    /**
     * {@link #supplyType}
     */
    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType == null ? null : supplyType.trim();
    }

    /**
     * {@link #supplyTheme}
     */
    public String getSupplyTheme() {
        return supplyTheme;
    }

    /**
     * {@link #supplyTheme}
     */
    public void setSupplyTheme(String supplyTheme) {
        this.supplyTheme = supplyTheme == null ? null : supplyTheme.trim();
    }

    /**
     * {@link #supplyBrief}
     */
    public String getSupplyBrief() {
        return supplyBrief;
    }

    /**
     * {@link #supplyBrief}
     */
    public void setSupplyBrief(String supplyBrief) {
        this.supplyBrief = supplyBrief == null ? null : supplyBrief.trim();
    }

    /**
     * {@link #coverFileId}
     */
    public String getCoverFileId() {
        return coverFileId;
    }

    /**
     * {@link #coverFileId}
     */
    public void setCoverFileId(String coverFileId) {
        this.coverFileId = coverFileId == null ? null : coverFileId.trim();
    }

    /**
     * {@link #coverFileName}
     */
    public String getCoverFileName() {
        return coverFileName;
    }

    /**
     * {@link #coverFileName}
     */
    public void setCoverFileName(String coverFileName) {
        this.coverFileName = coverFileName == null ? null : coverFileName.trim();
    }

    /**
     * {@link #detailDesc}
     */
    public String getDetailDesc() {
        return detailDesc;
    }

    /**
     * {@link #detailDesc}
     */
    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    /**
     * {@link #searchKey}
     */
    public String getSearchKey() {
        return searchKey;
    }

    /**
     * {@link #searchKey}
     */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey == null ? null : searchKey.trim();
    }

    /**
     * {@link #addressDetail}
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * {@link #addressDetail}
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    /**
     * {@link #contact}
     */
    public String getContact() {
        return contact;
    }

    /**
     * {@link #contact}
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
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
     * {@link #visitorCount}
     */
    public Integer getVisitorCount() {
        return visitorCount;
    }

    /**
     * {@link #visitorCount}
     */
    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    /**
     * {@link #price}
     */
    public String getPrice() {
        return price;
    }

    /**
     * {@link #price}
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * {@link #publisherUserId}
     */
    public Integer getPublisherUserId() {
        return publisherUserId;
    }

    /**
     * {@link #publisherUserId}
     */
    public void setPublisherUserId(Integer publisherUserId) {
        this.publisherUserId = publisherUserId;
    }

    /**
     * {@link #publisherName}
     */
    public String getPublisherName() {
        return publisherName;
    }

    /**
     * {@link #publisherName}
     */
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName == null ? null : publisherName.trim();
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
     * {@link #addressProvince}
     */
    public String getAddressProvince() {
        return addressProvince;
    }

    /**
     * {@link #addressProvince}
     */
    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince == null ? null : addressProvince.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "projectId";
    }

    @Override
    public Integer getIdValue() {
        return projectId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.projectId = id;
    }

    public static JSONObject toJSON(SpServiceProject e) {
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

    public static List<JSONObject> toJSON(List<SpServiceProject> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return SpServiceProject.toJSON(this);
    }

    public static class Fields {
        public static final String PROJECT_ID = "projectId";

        public static final String SERVICE_FIELD = "serviceField";

        public static final String SUPPLY_TYPE = "supplyType";

        public static final String SUPPLY_THEME = "supplyTheme";

        public static final String SUPPLY_BRIEF = "supplyBrief";

        public static final String COVER_FILE_ID = "coverFileId";

        public static final String COVER_FILE_NAME = "coverFileName";

        public static final String DETAIL_DESC = "detailDesc";

        public static final String SEARCH_KEY = "searchKey";

        public static final String ADDRESS_DETAIL = "addressDetail";

        public static final String CONTACT = "contact";

        public static final String CONTACT_MOBILE = "contactMobile";

        public static final String EMAIL = "email";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String VISITOR_COUNT = "visitorCount";

        public static final String PRICE = "price";

        public static final String PUBLISHER_USER_ID = "publisherUserId";

        public static final String PUBLISHER_NAME = "publisherName";

        public static final String COMPANY_ID = "companyId";

        public static final String COMPANY_NAME = "companyName";

        public static final String ADDRESS_PROVINCE = "addressProvince";
    }

    public static class Query {
        public static final String PROJECT_ID__NE = "ne_projectId";

        public static final String PROJECT_ID__IN = "list_projectId";

        public static final String PROJECT_ID__BEGIN = "begin_projectId";

        public static final String PROJECT_ID__END = "end_projectId";

        public static final String SERVICE_FIELD__NE = "ne_serviceField";

        public static final String SERVICE_FIELD__LIKE = "like_serviceField";

        public static final String SERVICE_FIELD__IN = "list_serviceField";

        public static final String SERVICE_FIELD__BEGIN = "begin_serviceField";

        public static final String SERVICE_FIELD__END = "end_serviceField";

        public static final String SUPPLY_TYPE__NE = "ne_supplyType";

        public static final String SUPPLY_TYPE__LIKE = "like_supplyType";

        public static final String SUPPLY_TYPE__IN = "list_supplyType";

        public static final String SUPPLY_TYPE__BEGIN = "begin_supplyType";

        public static final String SUPPLY_TYPE__END = "end_supplyType";

        public static final String SUPPLY_THEME__NE = "ne_supplyTheme";

        public static final String SUPPLY_THEME__LIKE = "like_supplyTheme";

        public static final String SUPPLY_THEME__IN = "list_supplyTheme";

        public static final String SUPPLY_THEME__BEGIN = "begin_supplyTheme";

        public static final String SUPPLY_THEME__END = "end_supplyTheme";

        public static final String SUPPLY_BRIEF__NE = "ne_supplyBrief";

        public static final String SUPPLY_BRIEF__LIKE = "like_supplyBrief";

        public static final String SUPPLY_BRIEF__IN = "list_supplyBrief";

        public static final String SUPPLY_BRIEF__BEGIN = "begin_supplyBrief";

        public static final String SUPPLY_BRIEF__END = "end_supplyBrief";

        public static final String COVER_FILE_ID__NE = "ne_coverFileId";

        public static final String COVER_FILE_ID__LIKE = "like_coverFileId";

        public static final String COVER_FILE_ID__IN = "list_coverFileId";

        public static final String COVER_FILE_ID__BEGIN = "begin_coverFileId";

        public static final String COVER_FILE_ID__END = "end_coverFileId";

        public static final String COVER_FILE_NAME__NE = "ne_coverFileName";

        public static final String COVER_FILE_NAME__LIKE = "like_coverFileName";

        public static final String COVER_FILE_NAME__IN = "list_coverFileName";

        public static final String COVER_FILE_NAME__BEGIN = "begin_coverFileName";

        public static final String COVER_FILE_NAME__END = "end_coverFileName";

        public static final String DETAIL_DESC__NE = "ne_detailDesc";

        public static final String DETAIL_DESC__LIKE = "like_detailDesc";

        public static final String DETAIL_DESC__IN = "list_detailDesc";

        public static final String DETAIL_DESC__BEGIN = "begin_detailDesc";

        public static final String DETAIL_DESC__END = "end_detailDesc";

        public static final String SEARCH_KEY__NE = "ne_searchKey";

        public static final String SEARCH_KEY__LIKE = "like_searchKey";

        public static final String SEARCH_KEY__IN = "list_searchKey";

        public static final String SEARCH_KEY__BEGIN = "begin_searchKey";

        public static final String SEARCH_KEY__END = "end_searchKey";

        public static final String ADDRESS_DETAIL__NE = "ne_addressDetail";

        public static final String ADDRESS_DETAIL__LIKE = "like_addressDetail";

        public static final String ADDRESS_DETAIL__IN = "list_addressDetail";

        public static final String ADDRESS_DETAIL__BEGIN = "begin_addressDetail";

        public static final String ADDRESS_DETAIL__END = "end_addressDetail";

        public static final String CONTACT__NE = "ne_contact";

        public static final String CONTACT__LIKE = "like_contact";

        public static final String CONTACT__IN = "list_contact";

        public static final String CONTACT__BEGIN = "begin_contact";

        public static final String CONTACT__END = "end_contact";

        public static final String CONTACT_MOBILE__NE = "ne_contactMobile";

        public static final String CONTACT_MOBILE__LIKE = "like_contactMobile";

        public static final String CONTACT_MOBILE__IN = "list_contactMobile";

        public static final String CONTACT_MOBILE__BEGIN = "begin_contactMobile";

        public static final String CONTACT_MOBILE__END = "end_contactMobile";

        public static final String EMAIL__NE = "ne_email";

        public static final String EMAIL__LIKE = "like_email";

        public static final String EMAIL__IN = "list_email";

        public static final String EMAIL__BEGIN = "begin_email";

        public static final String EMAIL__END = "end_email";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String VISITOR_COUNT__NE = "ne_visitorCount";

        public static final String VISITOR_COUNT__IN = "list_visitorCount";

        public static final String VISITOR_COUNT__BEGIN = "begin_visitorCount";

        public static final String VISITOR_COUNT__END = "end_visitorCount";

        public static final String PRICE__NE = "ne_price";

        public static final String PRICE__LIKE = "like_price";

        public static final String PRICE__IN = "list_price";

        public static final String PRICE__BEGIN = "begin_price";

        public static final String PRICE__END = "end_price";

        public static final String PUBLISHER_USER_ID__NE = "ne_publisherUserId";

        public static final String PUBLISHER_USER_ID__IN = "list_publisherUserId";

        public static final String PUBLISHER_USER_ID__BEGIN = "begin_publisherUserId";

        public static final String PUBLISHER_USER_ID__END = "end_publisherUserId";

        public static final String PUBLISHER_NAME__NE = "ne_publisherName";

        public static final String PUBLISHER_NAME__LIKE = "like_publisherName";

        public static final String PUBLISHER_NAME__IN = "list_publisherName";

        public static final String PUBLISHER_NAME__BEGIN = "begin_publisherName";

        public static final String PUBLISHER_NAME__END = "end_publisherName";

        public static final String COMPANY_ID__NE = "ne_companyId";

        public static final String COMPANY_ID__IN = "list_companyId";

        public static final String COMPANY_ID__BEGIN = "begin_companyId";

        public static final String COMPANY_ID__END = "end_companyId";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String ADDRESS_PROVINCE__NE = "ne_addressProvince";

        public static final String ADDRESS_PROVINCE__LIKE = "like_addressProvince";

        public static final String ADDRESS_PROVINCE__IN = "list_addressProvince";

        public static final String ADDRESS_PROVINCE__BEGIN = "begin_addressProvince";

        public static final String ADDRESS_PROVINCE__END = "end_addressProvince";
    }
}