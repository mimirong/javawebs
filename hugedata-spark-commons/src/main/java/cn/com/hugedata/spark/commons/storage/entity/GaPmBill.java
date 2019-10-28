package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.GaPmBillConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaPmBill extends BaseEntity<Integer> implements GaPmBillConstants {
    /**
     * billId
     */
    private Integer billId;

    /**
     * billType
     */
    private String billType;

    /**
     * month
     */
    private String month;

    /**
     * amount
     */
    private Integer amount;

    /**
     * remark
     */
    private String remark;

    /**
     * status
     */
    private String status;

    /**
     * userId
     */
    private Integer userId;

    /**
     * userName
     */
    private String userName;

    /**
     * companyName
     */
    private String companyName;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * expireTime
     */
    private Date expireTime;

    /**
     * finishTime
     */
    private Date finishTime;

    /**
     * usageAmount
     */
    private Integer usageAmount;

    private static final long serialVersionUID = 1393977112548660465L;

    /**
     * {@link #billId}
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * {@link #billId}
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * {@link #billType}
     */
    public String getBillType() {
        return billType;
    }

    /**
     * {@link #billType}
     */
    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
    }

    /**
     * {@link #month}
     */
    public String getMonth() {
        return month;
    }

    /**
     * {@link #month}
     */
    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
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
     * {@link #userName}
     */
    public String getUserName() {
        return userName;
    }

    /**
     * {@link #userName}
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
     * {@link #expireTime}
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * {@link #expireTime}
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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

    /**
     * {@link #usageAmount}
     */
    public Integer getUsageAmount() {
        return usageAmount;
    }

    /**
     * {@link #usageAmount}
     */
    public void setUsageAmount(Integer usageAmount) {
        this.usageAmount = usageAmount;
    }

    @Override
    public String getIdPropertyName() {
        return "billId";
    }

    @Override
    public Integer getIdValue() {
        return billId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.billId = id;
    }

    public static JSONObject toJSON(GaPmBill e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getExpireTime() != null) {
            obj.put("expireTimeStr", fmt.format(e.getExpireTime()));
        }
        if (e.getFinishTime() != null) {
            obj.put("finishTimeStr", fmt.format(e.getFinishTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<GaPmBill> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaPmBill.toJSON(this);
    }

    public static class Fields {
        public static final String BILL_ID = "billId";

        public static final String BILL_TYPE = "billType";

        public static final String MONTH = "month";

        public static final String AMOUNT = "amount";

        public static final String REMARK = "remark";

        public static final String STATUS = "status";

        public static final String USER_ID = "userId";

        public static final String USER_NAME = "userName";

        public static final String COMPANY_NAME = "companyName";

        public static final String CREATE_TIME = "createTime";

        public static final String EXPIRE_TIME = "expireTime";

        public static final String FINISH_TIME = "finishTime";

        public static final String USAGE_AMOUNT = "usageAmount";
    }

    public static class Query {
        public static final String BILL_ID__NE = "ne_billId";

        public static final String BILL_ID__IN = "list_billId";

        public static final String BILL_ID__BEGIN = "begin_billId";

        public static final String BILL_ID__END = "end_billId";

        public static final String BILL_TYPE__NE = "ne_billType";

        public static final String BILL_TYPE__LIKE = "like_billType";

        public static final String BILL_TYPE__IN = "list_billType";

        public static final String BILL_TYPE__BEGIN = "begin_billType";

        public static final String BILL_TYPE__END = "end_billType";

        public static final String MONTH__NE = "ne_month";

        public static final String MONTH__LIKE = "like_month";

        public static final String MONTH__IN = "list_month";

        public static final String MONTH__BEGIN = "begin_month";

        public static final String MONTH__END = "end_month";

        public static final String AMOUNT__NE = "ne_amount";

        public static final String AMOUNT__IN = "list_amount";

        public static final String AMOUNT__BEGIN = "begin_amount";

        public static final String AMOUNT__END = "end_amount";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String USER_NAME__NE = "ne_userName";

        public static final String USER_NAME__LIKE = "like_userName";

        public static final String USER_NAME__IN = "list_userName";

        public static final String USER_NAME__BEGIN = "begin_userName";

        public static final String USER_NAME__END = "end_userName";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String EXPIRE_TIME__NE = "ne_expireTime";

        public static final String EXPIRE_TIME__IN = "list_expireTime";

        public static final String EXPIRE_TIME__BEGIN = "begin_expireTime";

        public static final String EXPIRE_TIME__END = "end_expireTime";

        public static final String FINISH_TIME__NE = "ne_finishTime";

        public static final String FINISH_TIME__IN = "list_finishTime";

        public static final String FINISH_TIME__BEGIN = "begin_finishTime";

        public static final String FINISH_TIME__END = "end_finishTime";

        public static final String USAGE_AMOUNT__NE = "ne_usageAmount";

        public static final String USAGE_AMOUNT__IN = "list_usageAmount";

        public static final String USAGE_AMOUNT__BEGIN = "begin_usageAmount";

        public static final String USAGE_AMOUNT__END = "end_usageAmount";
    }
}