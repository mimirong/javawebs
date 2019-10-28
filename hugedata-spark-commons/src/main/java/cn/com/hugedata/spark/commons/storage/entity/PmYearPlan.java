package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PmYearPlan extends BaseEntity<Integer> {
    /**
     * planId
     */
    private Integer planId;

    /**
     * planYear
     */
    private String planYear;

    /**
     * amount
     */
    private Double amount;

    /**
     * mainActor
     */
    private String mainActor;

    /**
     * content
     */
    private String content;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * approveResult
     */
    private String approveResult;

    /**
     * planStatus
     */
    private Boolean planStatus;

    /**
     * projectId
     */
    private Integer projectId;

    private static final long serialVersionUID = -1202448466186595024L;

    /**
     * {@link #planId}
     */
    public Integer getPlanId() {
        return planId;
    }

    /**
     * {@link #planId}
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    /**
     * {@link #planYear}
     */
    public String getPlanYear() {
        return planYear;
    }

    /**
     * {@link #planYear}
     */
    public void setPlanYear(String planYear) {
        this.planYear = planYear == null ? null : planYear.trim();
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
     * {@link #mainActor}
     */
    public String getMainActor() {
        return mainActor;
    }

    /**
     * {@link #mainActor}
     */
    public void setMainActor(String mainActor) {
        this.mainActor = mainActor == null ? null : mainActor.trim();
    }

    /**
     * {@link #content}
     */
    public String getContent() {
        return content;
    }

    /**
     * {@link #content}
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
     * {@link #approveResult}
     */
    public String getApproveResult() {
        return approveResult;
    }

    /**
     * {@link #approveResult}
     */
    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult == null ? null : approveResult.trim();
    }

    /**
     * {@link #planStatus}
     */
    public Boolean getPlanStatus() {
        return planStatus;
    }

    /**
     * {@link #planStatus}
     */
    public void setPlanStatus(Boolean planStatus) {
        this.planStatus = planStatus;
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

    @Override
    public String getIdPropertyName() {
        return "planId";
    }

    @Override
    public Integer getIdValue() {
        return planId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.planId = id;
    }

    public static JSONObject toJSON(PmYearPlan e) {
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

    public static List<JSONObject> toJSON(List<PmYearPlan> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PmYearPlan.toJSON(this);
    }

    public static class Fields {
        public static final String PLAN_ID = "planId";

        public static final String PLAN_YEAR = "planYear";

        public static final String AMOUNT = "amount";

        public static final String MAIN_ACTOR = "mainActor";

        public static final String CONTENT = "content";

        public static final String CREATE_TIME = "createTime";

        public static final String APPROVE_RESULT = "approveResult";

        public static final String PLAN_STATUS = "planStatus";

        public static final String PROJECT_ID = "projectId";
    }

    public static class Query {
        public static final String PLAN_ID__NE = "ne_planId";

        public static final String PLAN_ID__IN = "list_planId";

        public static final String PLAN_ID__BEGIN = "begin_planId";

        public static final String PLAN_ID__END = "end_planId";

        public static final String PLAN_YEAR__NE = "ne_planYear";

        public static final String PLAN_YEAR__LIKE = "like_planYear";

        public static final String PLAN_YEAR__IN = "list_planYear";

        public static final String PLAN_YEAR__BEGIN = "begin_planYear";

        public static final String PLAN_YEAR__END = "end_planYear";

        public static final String AMOUNT__NE = "ne_amount";

        public static final String AMOUNT__IN = "list_amount";

        public static final String AMOUNT__BEGIN = "begin_amount";

        public static final String AMOUNT__END = "end_amount";

        public static final String MAIN_ACTOR__NE = "ne_mainActor";

        public static final String MAIN_ACTOR__LIKE = "like_mainActor";

        public static final String MAIN_ACTOR__IN = "list_mainActor";

        public static final String MAIN_ACTOR__BEGIN = "begin_mainActor";

        public static final String MAIN_ACTOR__END = "end_mainActor";

        public static final String CONTENT__NE = "ne_content";

        public static final String CONTENT__LIKE = "like_content";

        public static final String CONTENT__IN = "list_content";

        public static final String CONTENT__BEGIN = "begin_content";

        public static final String CONTENT__END = "end_content";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String APPROVE_RESULT__NE = "ne_approveResult";

        public static final String APPROVE_RESULT__LIKE = "like_approveResult";

        public static final String APPROVE_RESULT__IN = "list_approveResult";

        public static final String APPROVE_RESULT__BEGIN = "begin_approveResult";

        public static final String APPROVE_RESULT__END = "end_approveResult";

        public static final String PLAN_STATUS__NE = "ne_planStatus";

        public static final String PLAN_STATUS__IN = "list_planStatus";

        public static final String PLAN_STATUS__BEGIN = "begin_planStatus";

        public static final String PLAN_STATUS__END = "end_planStatus";

        public static final String PROJECT_ID__NE = "ne_projectId";

        public static final String PROJECT_ID__IN = "list_projectId";

        public static final String PROJECT_ID__BEGIN = "begin_projectId";

        public static final String PROJECT_ID__END = "end_projectId";
    }
}