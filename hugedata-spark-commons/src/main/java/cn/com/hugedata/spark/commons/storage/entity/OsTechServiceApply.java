package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.OsTechServiceApplyConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OsTechServiceApply extends BaseEntity<Integer> implements OsTechServiceApplyConstants {
    /**
     * applyId
     */
    private Integer applyId;

    /**
     * applyType
     */
    private String applyType;

    /**
     * specId
     */
    private String specId;

    /**
     * specName
     */
    private String specName;

    /**
     * serviceDuration
     */
    private Integer serviceDuration;

    /**
     * serviceStart
     */
    private Date serviceStart;

    /**
     * serviceEnd
     */
    private Date serviceEnd;

    /**
     * amount
     */
    private Integer amount;

    /**
     * price
     */
    private Integer price;

    /**
     * status
     */
    private String status;

    /**
     * companyName
     */
    private String companyName;

    /**
     * contactName
     */
    private String contactName;

    /**
     * contactMobile
     */
    private String contactMobile;

    /**
     * applierId
     */
    private Integer applierId;

    /**
     * applierName
     */
    private String applierName;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * approveRemark
     */
    private String approveRemark;

    /**
     * remark
     */
    private String remark;

    /**
     * extraData
     */
    private String extraData;

    private static final long serialVersionUID = 2372736834588909334L;

    /**
     * {@link #applyId}
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * {@link #applyId}
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * {@link #applyType}
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * {@link #applyType}
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    /**
     * {@link #specId}
     */
    public String getSpecId() {
        return specId;
    }

    /**
     * {@link #specId}
     */
    public void setSpecId(String specId) {
        this.specId = specId == null ? null : specId.trim();
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
     * {@link #serviceDuration}
     */
    public Integer getServiceDuration() {
        return serviceDuration;
    }

    /**
     * {@link #serviceDuration}
     */
    public void setServiceDuration(Integer serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    /**
     * {@link #serviceStart}
     */
    public Date getServiceStart() {
        return serviceStart;
    }

    /**
     * {@link #serviceStart}
     */
    public void setServiceStart(Date serviceStart) {
        this.serviceStart = serviceStart;
    }

    /**
     * {@link #serviceEnd}
     */
    public Date getServiceEnd() {
        return serviceEnd;
    }

    /**
     * {@link #serviceEnd}
     */
    public void setServiceEnd(Date serviceEnd) {
        this.serviceEnd = serviceEnd;
    }

    /**
     * {@link #amount}
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * {@link #amount}
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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
     * {@link #applierId}
     */
    public Integer getApplierId() {
        return applierId;
    }

    /**
     * {@link #applierId}
     */
    public void setApplierId(Integer applierId) {
        this.applierId = applierId;
    }

    /**
     * {@link #applierName}
     */
    public String getApplierName() {
        return applierName;
    }

    /**
     * {@link #applierName}
     */
    public void setApplierName(String applierName) {
        this.applierName = applierName == null ? null : applierName.trim();
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
     * {@link #approveRemark}
     */
    public String getApproveRemark() {
        return approveRemark;
    }

    /**
     * {@link #approveRemark}
     */
    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark == null ? null : approveRemark.trim();
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

    @Override
    public String getIdPropertyName() {
        return "applyId";
    }

    @Override
    public Integer getIdValue() {
        return applyId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.applyId = id;
    }

    public static JSONObject toJSON(OsTechServiceApply e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getServiceStart() != null) {
            obj.put("serviceStartStr", fmt.format(e.getServiceStart()));
        }
        if (e.getServiceEnd() != null) {
            obj.put("serviceEndStr", fmt.format(e.getServiceEnd()));
        }
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<OsTechServiceApply> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return OsTechServiceApply.toJSON(this);
    }

    public static class Fields {
        public static final String APPLY_ID = "applyId";

        public static final String APPLY_TYPE = "applyType";

        public static final String SPEC_ID = "specId";

        public static final String SPEC_NAME = "specName";

        public static final String SERVICE_DURATION = "serviceDuration";

        public static final String SERVICE_START = "serviceStart";

        public static final String SERVICE_END = "serviceEnd";

        public static final String AMOUNT = "amount";

        public static final String PRICE = "price";

        public static final String STATUS = "status";

        public static final String COMPANY_NAME = "companyName";

        public static final String CONTACT_NAME = "contactName";

        public static final String CONTACT_MOBILE = "contactMobile";

        public static final String APPLIER_ID = "applierId";

        public static final String APPLIER_NAME = "applierName";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String APPROVE_REMARK = "approveRemark";

        public static final String REMARK = "remark";

        public static final String EXTRA_DATA = "extraData";
    }

    public static class Query {
        public static final String APPLY_ID__NE = "ne_applyId";

        public static final String APPLY_ID__IN = "list_applyId";

        public static final String APPLY_ID__BEGIN = "begin_applyId";

        public static final String APPLY_ID__END = "end_applyId";

        public static final String APPLY_TYPE__NE = "ne_applyType";

        public static final String APPLY_TYPE__LIKE = "like_applyType";

        public static final String APPLY_TYPE__IN = "list_applyType";

        public static final String APPLY_TYPE__BEGIN = "begin_applyType";

        public static final String APPLY_TYPE__END = "end_applyType";

        public static final String SPEC_ID__NE = "ne_specId";

        public static final String SPEC_ID__LIKE = "like_specId";

        public static final String SPEC_ID__IN = "list_specId";

        public static final String SPEC_ID__BEGIN = "begin_specId";

        public static final String SPEC_ID__END = "end_specId";

        public static final String SPEC_NAME__NE = "ne_specName";

        public static final String SPEC_NAME__LIKE = "like_specName";

        public static final String SPEC_NAME__IN = "list_specName";

        public static final String SPEC_NAME__BEGIN = "begin_specName";

        public static final String SPEC_NAME__END = "end_specName";

        public static final String SERVICE_DURATION__NE = "ne_serviceDuration";

        public static final String SERVICE_DURATION__IN = "list_serviceDuration";

        public static final String SERVICE_DURATION__BEGIN = "begin_serviceDuration";

        public static final String SERVICE_DURATION__END = "end_serviceDuration";

        public static final String SERVICE_START__NE = "ne_serviceStart";

        public static final String SERVICE_START__IN = "list_serviceStart";

        public static final String SERVICE_START__BEGIN = "begin_serviceStart";

        public static final String SERVICE_START__END = "end_serviceStart";

        public static final String SERVICE_END__NE = "ne_serviceEnd";

        public static final String SERVICE_END__IN = "list_serviceEnd";

        public static final String SERVICE_END__BEGIN = "begin_serviceEnd";

        public static final String SERVICE_END__END = "end_serviceEnd";

        public static final String AMOUNT__NE = "ne_amount";

        public static final String AMOUNT__IN = "list_amount";

        public static final String AMOUNT__BEGIN = "begin_amount";

        public static final String AMOUNT__END = "end_amount";

        public static final String PRICE__NE = "ne_price";

        public static final String PRICE__IN = "list_price";

        public static final String PRICE__BEGIN = "begin_price";

        public static final String PRICE__END = "end_price";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

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

        public static final String APPLIER_ID__NE = "ne_applierId";

        public static final String APPLIER_ID__IN = "list_applierId";

        public static final String APPLIER_ID__BEGIN = "begin_applierId";

        public static final String APPLIER_ID__END = "end_applierId";

        public static final String APPLIER_NAME__NE = "ne_applierName";

        public static final String APPLIER_NAME__LIKE = "like_applierName";

        public static final String APPLIER_NAME__IN = "list_applierName";

        public static final String APPLIER_NAME__BEGIN = "begin_applierName";

        public static final String APPLIER_NAME__END = "end_applierName";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String APPROVE_REMARK__NE = "ne_approveRemark";

        public static final String APPROVE_REMARK__LIKE = "like_approveRemark";

        public static final String APPROVE_REMARK__IN = "list_approveRemark";

        public static final String APPROVE_REMARK__BEGIN = "begin_approveRemark";

        public static final String APPROVE_REMARK__END = "end_approveRemark";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";

        public static final String EXTRA_DATA__NE = "ne_extraData";

        public static final String EXTRA_DATA__LIKE = "like_extraData";

        public static final String EXTRA_DATA__IN = "list_extraData";

        public static final String EXTRA_DATA__BEGIN = "begin_extraData";

        public static final String EXTRA_DATA__END = "end_extraData";
    }
}