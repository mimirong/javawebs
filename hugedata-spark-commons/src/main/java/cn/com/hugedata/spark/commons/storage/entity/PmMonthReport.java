package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PmMonthReport extends BaseEntity<Integer> {
    /**
     * reportId
     */
    private Integer reportId;

    /**
     * reportYear
     */
    private Integer reportYear;

    /**
     * reportMonth
     */
    private Integer reportMonth;

    /**
     * projectId
     */
    private Integer projectId;

    /**
     * investedNum
     */
    private Double investedNum;

    /**
     * useDesign
     */
    private Double useDesign;

    /**
     * useOversee
     */
    private Double useOversee;

    /**
     * useEngineer
     */
    private Double useEngineer;

    /**
     * useBuy
     */
    private Double useBuy;

    /**
     * useAsset
     */
    private Double useAsset;

    /**
     * useTotal
     */
    private Double useTotal;

    /**
     * current
     */
    private String current;

    /**
     * asset
     */
    private String asset;

    /**
     * difficult
     */
    private String difficult;

    /**
     * difficultOrg
     */
    private String difficultOrg;

    /**
     * comment
     */
    private String comment;

    /**
     * approveInfo
     */
    private String approveInfo;

    /**
     * reportStatus
     */
    private Integer reportStatus;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = -1077741590625627484L;

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
     * {@link #reportYear}
     */
    public Integer getReportYear() {
        return reportYear;
    }

    /**
     * {@link #reportYear}
     */
    public void setReportYear(Integer reportYear) {
        this.reportYear = reportYear;
    }

    /**
     * {@link #reportMonth}
     */
    public Integer getReportMonth() {
        return reportMonth;
    }

    /**
     * {@link #reportMonth}
     */
    public void setReportMonth(Integer reportMonth) {
        this.reportMonth = reportMonth;
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
     * {@link #investedNum}
     */
    public Double getInvestedNum() {
        return investedNum;
    }

    /**
     * {@link #investedNum}
     */
    public void setInvestedNum(Double investedNum) {
        this.investedNum = investedNum;
    }

    /**
     * {@link #useDesign}
     */
    public Double getUseDesign() {
        return useDesign;
    }

    /**
     * {@link #useDesign}
     */
    public void setUseDesign(Double useDesign) {
        this.useDesign = useDesign;
    }

    /**
     * {@link #useOversee}
     */
    public Double getUseOversee() {
        return useOversee;
    }

    /**
     * {@link #useOversee}
     */
    public void setUseOversee(Double useOversee) {
        this.useOversee = useOversee;
    }

    /**
     * {@link #useEngineer}
     */
    public Double getUseEngineer() {
        return useEngineer;
    }

    /**
     * {@link #useEngineer}
     */
    public void setUseEngineer(Double useEngineer) {
        this.useEngineer = useEngineer;
    }

    /**
     * {@link #useBuy}
     */
    public Double getUseBuy() {
        return useBuy;
    }

    /**
     * {@link #useBuy}
     */
    public void setUseBuy(Double useBuy) {
        this.useBuy = useBuy;
    }

    /**
     * {@link #useAsset}
     */
    public Double getUseAsset() {
        return useAsset;
    }

    /**
     * {@link #useAsset}
     */
    public void setUseAsset(Double useAsset) {
        this.useAsset = useAsset;
    }

    /**
     * {@link #useTotal}
     */
    public Double getUseTotal() {
        return useTotal;
    }

    /**
     * {@link #useTotal}
     */
    public void setUseTotal(Double useTotal) {
        this.useTotal = useTotal;
    }

    /**
     * {@link #current}
     */
    public String getCurrent() {
        return current;
    }

    /**
     * {@link #current}
     */
    public void setCurrent(String current) {
        this.current = current == null ? null : current.trim();
    }

    /**
     * {@link #asset}
     */
    public String getAsset() {
        return asset;
    }

    /**
     * {@link #asset}
     */
    public void setAsset(String asset) {
        this.asset = asset == null ? null : asset.trim();
    }

    /**
     * {@link #difficult}
     */
    public String getDifficult() {
        return difficult;
    }

    /**
     * {@link #difficult}
     */
    public void setDifficult(String difficult) {
        this.difficult = difficult == null ? null : difficult.trim();
    }

    /**
     * {@link #difficultOrg}
     */
    public String getDifficultOrg() {
        return difficultOrg;
    }

    /**
     * {@link #difficultOrg}
     */
    public void setDifficultOrg(String difficultOrg) {
        this.difficultOrg = difficultOrg == null ? null : difficultOrg.trim();
    }

    /**
     * {@link #comment}
     */
    public String getComment() {
        return comment;
    }

    /**
     * {@link #comment}
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * {@link #approveInfo}
     */
    public String getApproveInfo() {
        return approveInfo;
    }

    /**
     * {@link #approveInfo}
     */
    public void setApproveInfo(String approveInfo) {
        this.approveInfo = approveInfo == null ? null : approveInfo.trim();
    }

    /**
     * {@link #reportStatus}
     */
    public Integer getReportStatus() {
        return reportStatus;
    }

    /**
     * {@link #reportStatus}
     */
    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
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

    @Override
    public String getIdPropertyName() {
        return "reportId";
    }

    @Override
    public Integer getIdValue() {
        return reportId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.reportId = id;
    }

    public static JSONObject toJSON(PmMonthReport e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<PmMonthReport> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PmMonthReport.toJSON(this);
    }

    public static class Fields {
        public static final String REPORT_ID = "reportId";

        public static final String REPORT_YEAR = "reportYear";

        public static final String REPORT_MONTH = "reportMonth";

        public static final String PROJECT_ID = "projectId";

        public static final String INVESTED_NUM = "investedNum";

        public static final String USE_DESIGN = "useDesign";

        public static final String USE_OVERSEE = "useOversee";

        public static final String USE_ENGINEER = "useEngineer";

        public static final String USE_BUY = "useBuy";

        public static final String USE_ASSET = "useAsset";

        public static final String USE_TOTAL = "useTotal";

        public static final String CURRENT = "current";

        public static final String ASSET = "asset";

        public static final String DIFFICULT = "difficult";

        public static final String DIFFICULT_ORG = "difficultOrg";

        public static final String COMMENT = "comment";

        public static final String APPROVE_INFO = "approveInfo";

        public static final String REPORT_STATUS = "reportStatus";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String REPORT_ID__NE = "ne_reportId";

        public static final String REPORT_ID__IN = "list_reportId";

        public static final String REPORT_ID__BEGIN = "begin_reportId";

        public static final String REPORT_ID__END = "end_reportId";

        public static final String REPORT_YEAR__NE = "ne_reportYear";

        public static final String REPORT_YEAR__IN = "list_reportYear";

        public static final String REPORT_YEAR__BEGIN = "begin_reportYear";

        public static final String REPORT_YEAR__END = "end_reportYear";

        public static final String REPORT_MONTH__NE = "ne_reportMonth";

        public static final String REPORT_MONTH__IN = "list_reportMonth";

        public static final String REPORT_MONTH__BEGIN = "begin_reportMonth";

        public static final String REPORT_MONTH__END = "end_reportMonth";

        public static final String PROJECT_ID__NE = "ne_projectId";

        public static final String PROJECT_ID__IN = "list_projectId";

        public static final String PROJECT_ID__BEGIN = "begin_projectId";

        public static final String PROJECT_ID__END = "end_projectId";

        public static final String INVESTED_NUM__NE = "ne_investedNum";

        public static final String INVESTED_NUM__IN = "list_investedNum";

        public static final String INVESTED_NUM__BEGIN = "begin_investedNum";

        public static final String INVESTED_NUM__END = "end_investedNum";

        public static final String USE_DESIGN__NE = "ne_useDesign";

        public static final String USE_DESIGN__IN = "list_useDesign";

        public static final String USE_DESIGN__BEGIN = "begin_useDesign";

        public static final String USE_DESIGN__END = "end_useDesign";

        public static final String USE_OVERSEE__NE = "ne_useOversee";

        public static final String USE_OVERSEE__IN = "list_useOversee";

        public static final String USE_OVERSEE__BEGIN = "begin_useOversee";

        public static final String USE_OVERSEE__END = "end_useOversee";

        public static final String USE_ENGINEER__NE = "ne_useEngineer";

        public static final String USE_ENGINEER__IN = "list_useEngineer";

        public static final String USE_ENGINEER__BEGIN = "begin_useEngineer";

        public static final String USE_ENGINEER__END = "end_useEngineer";

        public static final String USE_BUY__NE = "ne_useBuy";

        public static final String USE_BUY__IN = "list_useBuy";

        public static final String USE_BUY__BEGIN = "begin_useBuy";

        public static final String USE_BUY__END = "end_useBuy";

        public static final String USE_ASSET__NE = "ne_useAsset";

        public static final String USE_ASSET__IN = "list_useAsset";

        public static final String USE_ASSET__BEGIN = "begin_useAsset";

        public static final String USE_ASSET__END = "end_useAsset";

        public static final String USE_TOTAL__NE = "ne_useTotal";

        public static final String USE_TOTAL__IN = "list_useTotal";

        public static final String USE_TOTAL__BEGIN = "begin_useTotal";

        public static final String USE_TOTAL__END = "end_useTotal";

        public static final String CURRENT__NE = "ne_current";

        public static final String CURRENT__LIKE = "like_current";

        public static final String CURRENT__IN = "list_current";

        public static final String CURRENT__BEGIN = "begin_current";

        public static final String CURRENT__END = "end_current";

        public static final String ASSET__NE = "ne_asset";

        public static final String ASSET__LIKE = "like_asset";

        public static final String ASSET__IN = "list_asset";

        public static final String ASSET__BEGIN = "begin_asset";

        public static final String ASSET__END = "end_asset";

        public static final String DIFFICULT__NE = "ne_difficult";

        public static final String DIFFICULT__LIKE = "like_difficult";

        public static final String DIFFICULT__IN = "list_difficult";

        public static final String DIFFICULT__BEGIN = "begin_difficult";

        public static final String DIFFICULT__END = "end_difficult";

        public static final String DIFFICULT_ORG__NE = "ne_difficultOrg";

        public static final String DIFFICULT_ORG__LIKE = "like_difficultOrg";

        public static final String DIFFICULT_ORG__IN = "list_difficultOrg";

        public static final String DIFFICULT_ORG__BEGIN = "begin_difficultOrg";

        public static final String DIFFICULT_ORG__END = "end_difficultOrg";

        public static final String COMMENT__NE = "ne_comment";

        public static final String COMMENT__LIKE = "like_comment";

        public static final String COMMENT__IN = "list_comment";

        public static final String COMMENT__BEGIN = "begin_comment";

        public static final String COMMENT__END = "end_comment";

        public static final String APPROVE_INFO__NE = "ne_approveInfo";

        public static final String APPROVE_INFO__LIKE = "like_approveInfo";

        public static final String APPROVE_INFO__IN = "list_approveInfo";

        public static final String APPROVE_INFO__BEGIN = "begin_approveInfo";

        public static final String APPROVE_INFO__END = "end_approveInfo";

        public static final String REPORT_STATUS__NE = "ne_reportStatus";

        public static final String REPORT_STATUS__IN = "list_reportStatus";

        public static final String REPORT_STATUS__BEGIN = "begin_reportStatus";

        public static final String REPORT_STATUS__END = "end_reportStatus";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";
    }
}