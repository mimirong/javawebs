package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.GaPmRefundConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaPmRefund extends BaseEntity<Integer> implements GaPmRefundConstants {
    /**
     * refundId
     */
    private Integer refundId;

    /**
     * companyName
     */
    private String companyName;

    /**
     * remark
     */
    private String remark;

    /**
     * amount
     */
    private Integer amount;

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
     * createTime
     */
    private Date createTime;

    /**
     * estimatedRefundTime
     */
    private Date estimatedRefundTime;

    /**
     * refundTime
     */
    private Date refundTime;

    private static final long serialVersionUID = 380118135965471116L;

    /**
     * {@link #refundId}
     */
    public Integer getRefundId() {
        return refundId;
    }

    /**
     * {@link #refundId}
     */
    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
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
     * {@link #estimatedRefundTime}
     */
    public Date getEstimatedRefundTime() {
        return estimatedRefundTime;
    }

    /**
     * {@link #estimatedRefundTime}
     */
    public void setEstimatedRefundTime(Date estimatedRefundTime) {
        this.estimatedRefundTime = estimatedRefundTime;
    }

    /**
     * {@link #refundTime}
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     * {@link #refundTime}
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    @Override
    public String getIdPropertyName() {
        return "refundId";
    }

    @Override
    public Integer getIdValue() {
        return refundId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.refundId = id;
    }

    public static JSONObject toJSON(GaPmRefund e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getEstimatedRefundTime() != null) {
            obj.put("estimatedRefundTimeStr", fmt.format(e.getEstimatedRefundTime()));
        }
        if (e.getRefundTime() != null) {
            obj.put("refundTimeStr", fmt.format(e.getRefundTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<GaPmRefund> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaPmRefund.toJSON(this);
    }

    public static class Fields {
        public static final String REFUND_ID = "refundId";

        public static final String COMPANY_NAME = "companyName";

        public static final String REMARK = "remark";

        public static final String AMOUNT = "amount";

        public static final String STATUS = "status";

        public static final String USER_ID = "userId";

        public static final String USER_NAME = "userName";

        public static final String CREATE_TIME = "createTime";

        public static final String ESTIMATED_REFUND_TIME = "estimatedRefundTime";

        public static final String REFUND_TIME = "refundTime";
    }

    public static class Query {
        public static final String REFUND_ID__NE = "ne_refundId";

        public static final String REFUND_ID__IN = "list_refundId";

        public static final String REFUND_ID__BEGIN = "begin_refundId";

        public static final String REFUND_ID__END = "end_refundId";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";

        public static final String AMOUNT__NE = "ne_amount";

        public static final String AMOUNT__IN = "list_amount";

        public static final String AMOUNT__BEGIN = "begin_amount";

        public static final String AMOUNT__END = "end_amount";

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

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String ESTIMATED_REFUND_TIME__NE = "ne_estimatedRefundTime";

        public static final String ESTIMATED_REFUND_TIME__IN = "list_estimatedRefundTime";

        public static final String ESTIMATED_REFUND_TIME__BEGIN = "begin_estimatedRefundTime";

        public static final String ESTIMATED_REFUND_TIME__END = "end_estimatedRefundTime";

        public static final String REFUND_TIME__NE = "ne_refundTime";

        public static final String REFUND_TIME__IN = "list_refundTime";

        public static final String REFUND_TIME__BEGIN = "begin_refundTime";

        public static final String REFUND_TIME__END = "end_refundTime";
    }
}