package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.GaQuitRentInfoConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaQuitRentInfo extends BaseEntity<Integer> implements GaQuitRentInfoConstants {
    /**
     * infoId
     */
    private Integer infoId;

    /**
     * companyName
     */
    private String companyName;

    /**
     * returnAmount
     */
    private Integer returnAmount;

    /**
     * applyTime
     */
    private Date applyTime;

    /**
     * quitTime
     */
    private Date quitTime;

    /**
     * returnTime
     */
    private Date returnTime;

    /**
     * status
     */
    private String status;

    private static final long serialVersionUID = -7022905384463717504L;

    /**
     * {@link #infoId}
     */
    public Integer getInfoId() {
        return infoId;
    }

    /**
     * {@link #infoId}
     */
    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
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
     * {@link #returnAmount}
     */
    public Integer getReturnAmount() {
        return returnAmount;
    }

    /**
     * {@link #returnAmount}
     */
    public void setReturnAmount(Integer returnAmount) {
        this.returnAmount = returnAmount;
    }

    /**
     * {@link #applyTime}
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * {@link #applyTime}
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * {@link #quitTime}
     */
    public Date getQuitTime() {
        return quitTime;
    }

    /**
     * {@link #quitTime}
     */
    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    /**
     * {@link #returnTime}
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * {@link #returnTime}
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
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

    @Override
    public String getIdPropertyName() {
        return "infoId";
    }

    @Override
    public Integer getIdValue() {
        return infoId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.infoId = id;
    }

    public static JSONObject toJSON(GaQuitRentInfo e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getApplyTime() != null) {
            obj.put("applyTimeStr", fmt.format(e.getApplyTime()));
        }
        if (e.getQuitTime() != null) {
            obj.put("quitTimeStr", fmt.format(e.getQuitTime()));
        }
        if (e.getReturnTime() != null) {
            obj.put("returnTimeStr", fmt.format(e.getReturnTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<GaQuitRentInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaQuitRentInfo.toJSON(this);
    }

    public static class Fields {
        public static final String INFO_ID = "infoId";

        public static final String COMPANY_NAME = "companyName";

        public static final String RETURN_AMOUNT = "returnAmount";

        public static final String APPLY_TIME = "applyTime";

        public static final String QUIT_TIME = "quitTime";

        public static final String RETURN_TIME = "returnTime";

        public static final String STATUS = "status";
    }

    public static class Query {
        public static final String INFO_ID__NE = "ne_infoId";

        public static final String INFO_ID__IN = "list_infoId";

        public static final String INFO_ID__BEGIN = "begin_infoId";

        public static final String INFO_ID__END = "end_infoId";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String RETURN_AMOUNT__NE = "ne_returnAmount";

        public static final String RETURN_AMOUNT__IN = "list_returnAmount";

        public static final String RETURN_AMOUNT__BEGIN = "begin_returnAmount";

        public static final String RETURN_AMOUNT__END = "end_returnAmount";

        public static final String APPLY_TIME__NE = "ne_applyTime";

        public static final String APPLY_TIME__IN = "list_applyTime";

        public static final String APPLY_TIME__BEGIN = "begin_applyTime";

        public static final String APPLY_TIME__END = "end_applyTime";

        public static final String QUIT_TIME__NE = "ne_quitTime";

        public static final String QUIT_TIME__IN = "list_quitTime";

        public static final String QUIT_TIME__BEGIN = "begin_quitTime";

        public static final String QUIT_TIME__END = "end_quitTime";

        public static final String RETURN_TIME__NE = "ne_returnTime";

        public static final String RETURN_TIME__IN = "list_returnTime";

        public static final String RETURN_TIME__BEGIN = "begin_returnTime";

        public static final String RETURN_TIME__END = "end_returnTime";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";
    }
}