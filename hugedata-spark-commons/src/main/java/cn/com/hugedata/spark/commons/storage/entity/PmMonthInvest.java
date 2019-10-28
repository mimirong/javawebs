package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PmMonthInvest extends BaseEntity<Integer> {
    /**
     * investId
     */
    private Integer investId;

    /**
     * reportId
     */
    private Integer reportId;

    /**
     * comeTypeId
     */
    private Integer comeTypeId;

    /**
     * comeTypeName
     */
    private String comeTypeName;

    /**
     * comeFrom
     */
    private String comeFrom;

    /**
     * amount
     */
    private Double amount;

    /**
     * projectId
     */
    private Integer projectId;

    /**
     * investMonth
     */
    private String investMonth;

    /**
     * investStatus
     */
    private Integer investStatus;

    private static final long serialVersionUID = 1294027627522995301L;

    /**
     * {@link #investId}
     */
    public Integer getInvestId() {
        return investId;
    }

    /**
     * {@link #investId}
     */
    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    /**
     * {@link #reportId}
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * {@link #reportId}
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * {@link #comeTypeId}
     */
    public Integer getComeTypeId() {
        return comeTypeId;
    }

    /**
     * {@link #comeTypeId}
     */
    public void setComeTypeId(Integer comeTypeId) {
        this.comeTypeId = comeTypeId;
    }

    /**
     * {@link #comeTypeName}
     */
    public String getComeTypeName() {
        return comeTypeName;
    }

    /**
     * {@link #comeTypeName}
     */
    public void setComeTypeName(String comeTypeName) {
        this.comeTypeName = comeTypeName == null ? null : comeTypeName.trim();
    }

    /**
     * {@link #comeFrom}
     */
    public String getComeFrom() {
        return comeFrom;
    }

    /**
     * {@link #comeFrom}
     */
    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom == null ? null : comeFrom.trim();
    }

    /**
     * {@link #amount}
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * {@link #amount}
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

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
     * {@link #investMonth}
     */
    public String getInvestMonth() {
        return investMonth;
    }

    /**
     * {@link #investMonth}
     */
    public void setInvestMonth(String investMonth) {
        this.investMonth = investMonth == null ? null : investMonth.trim();
    }

    /**
     * {@link #investStatus}
     */
    public Integer getInvestStatus() {
        return investStatus;
    }

    /**
     * {@link #investStatus}
     */
    public void setInvestStatus(Integer investStatus) {
        this.investStatus = investStatus;
    }

    @Override
    public String getIdPropertyName() {
        return "investId";
    }

    @Override
    public Integer getIdValue() {
        return investId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.investId = id;
    }

    public static JSONObject toJSON(PmMonthInvest e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<PmMonthInvest> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PmMonthInvest.toJSON(this);
    }

    public static class Fields {
        public static final String INVEST_ID = "investId";

        public static final String REPORT_ID = "reportId";

        public static final String COME_TYPE_ID = "comeTypeId";

        public static final String COME_TYPE_NAME = "comeTypeName";

        public static final String COME_FROM = "comeFrom";

        public static final String AMOUNT = "amount";

        public static final String PROJECT_ID = "projectId";

        public static final String INVEST_MONTH = "investMonth";

        public static final String INVEST_STATUS = "investStatus";
    }

    public static class Query {
        public static final String INVEST_ID__NE = "ne_investId";

        public static final String INVEST_ID__IN = "list_investId";

        public static final String INVEST_ID__BEGIN = "begin_investId";

        public static final String INVEST_ID__END = "end_investId";

        public static final String REPORT_ID__NE = "ne_reportId";

        public static final String REPORT_ID__IN = "list_reportId";

        public static final String REPORT_ID__BEGIN = "begin_reportId";

        public static final String REPORT_ID__END = "end_reportId";

        public static final String COME_TYPE_ID__NE = "ne_comeTypeId";

        public static final String COME_TYPE_ID__IN = "list_comeTypeId";

        public static final String COME_TYPE_ID__BEGIN = "begin_comeTypeId";

        public static final String COME_TYPE_ID__END = "end_comeTypeId";

        public static final String COME_TYPE_NAME__NE = "ne_comeTypeName";

        public static final String COME_TYPE_NAME__LIKE = "like_comeTypeName";

        public static final String COME_TYPE_NAME__IN = "list_comeTypeName";

        public static final String COME_TYPE_NAME__BEGIN = "begin_comeTypeName";

        public static final String COME_TYPE_NAME__END = "end_comeTypeName";

        public static final String COME_FROM__NE = "ne_comeFrom";

        public static final String COME_FROM__LIKE = "like_comeFrom";

        public static final String COME_FROM__IN = "list_comeFrom";

        public static final String COME_FROM__BEGIN = "begin_comeFrom";

        public static final String COME_FROM__END = "end_comeFrom";

        public static final String AMOUNT__NE = "ne_amount";

        public static final String AMOUNT__IN = "list_amount";

        public static final String AMOUNT__BEGIN = "begin_amount";

        public static final String AMOUNT__END = "end_amount";

        public static final String PROJECT_ID__NE = "ne_projectId";

        public static final String PROJECT_ID__IN = "list_projectId";

        public static final String PROJECT_ID__BEGIN = "begin_projectId";

        public static final String PROJECT_ID__END = "end_projectId";

        public static final String INVEST_MONTH__NE = "ne_investMonth";

        public static final String INVEST_MONTH__LIKE = "like_investMonth";

        public static final String INVEST_MONTH__IN = "list_investMonth";

        public static final String INVEST_MONTH__BEGIN = "begin_investMonth";

        public static final String INVEST_MONTH__END = "end_investMonth";

        public static final String INVEST_STATUS__NE = "ne_investStatus";

        public static final String INVEST_STATUS__IN = "list_investStatus";

        public static final String INVEST_STATUS__BEGIN = "begin_investStatus";

        public static final String INVEST_STATUS__END = "end_investStatus";
    }
}