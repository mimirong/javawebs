package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.LogOperationConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogOperation extends BaseEntity<Integer> implements LogOperationConstants {
    /**
     * logId
     */
    private Integer logId;

    /**
     * userId
     */
    private Integer userId;

    /**
     * username
     */
    private String username;

    /**
     * operationType
     */
    private String operationType;

    /**
     * target
     */
    private String target;

    /**
     * targetInfo
     */
    private String targetInfo;

    /**
     * operation
     */
    private String operation;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * data
     */
    private String data;

    private static final long serialVersionUID = -6015098440389337327L;

    /**
     * {@link #logId}
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * {@link #logId}
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
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
     * {@link #username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * {@link #username}
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * {@link #operationType}
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * {@link #operationType}
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    /**
     * {@link #target}
     */
    public String getTarget() {
        return target;
    }

    /**
     * {@link #target}
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * {@link #targetInfo}
     */
    public String getTargetInfo() {
        return targetInfo;
    }

    /**
     * {@link #targetInfo}
     */
    public void setTargetInfo(String targetInfo) {
        this.targetInfo = targetInfo == null ? null : targetInfo.trim();
    }

    /**
     * {@link #operation}
     */
    public String getOperation() {
        return operation;
    }

    /**
     * {@link #operation}
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
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
     * {@link #data}
     */
    public String getData() {
        return data;
    }

    /**
     * {@link #data}
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "logId";
    }

    @Override
    public Integer getIdValue() {
        return logId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.logId = id;
    }

    public static JSONObject toJSON(LogOperation e) {
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

    public static List<JSONObject> toJSON(List<LogOperation> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return LogOperation.toJSON(this);
    }

    public static class Fields {
        public static final String LOG_ID = "logId";

        public static final String USER_ID = "userId";

        public static final String USERNAME = "username";

        public static final String OPERATION_TYPE = "operationType";

        public static final String TARGET = "target";

        public static final String TARGET_INFO = "targetInfo";

        public static final String OPERATION = "operation";

        public static final String CREATE_TIME = "createTime";

        public static final String DATA = "data";
    }

    public static class Query {
        public static final String LOG_ID__NE = "ne_logId";

        public static final String LOG_ID__IN = "list_logId";

        public static final String LOG_ID__BEGIN = "begin_logId";

        public static final String LOG_ID__END = "end_logId";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String USERNAME__NE = "ne_username";

        public static final String USERNAME__LIKE = "like_username";

        public static final String USERNAME__IN = "list_username";

        public static final String USERNAME__BEGIN = "begin_username";

        public static final String USERNAME__END = "end_username";

        public static final String OPERATION_TYPE__NE = "ne_operationType";

        public static final String OPERATION_TYPE__LIKE = "like_operationType";

        public static final String OPERATION_TYPE__IN = "list_operationType";

        public static final String OPERATION_TYPE__BEGIN = "begin_operationType";

        public static final String OPERATION_TYPE__END = "end_operationType";

        public static final String TARGET__NE = "ne_target";

        public static final String TARGET__LIKE = "like_target";

        public static final String TARGET__IN = "list_target";

        public static final String TARGET__BEGIN = "begin_target";

        public static final String TARGET__END = "end_target";

        public static final String TARGET_INFO__NE = "ne_targetInfo";

        public static final String TARGET_INFO__LIKE = "like_targetInfo";

        public static final String TARGET_INFO__IN = "list_targetInfo";

        public static final String TARGET_INFO__BEGIN = "begin_targetInfo";

        public static final String TARGET_INFO__END = "end_targetInfo";

        public static final String OPERATION__NE = "ne_operation";

        public static final String OPERATION__LIKE = "like_operation";

        public static final String OPERATION__IN = "list_operation";

        public static final String OPERATION__BEGIN = "begin_operation";

        public static final String OPERATION__END = "end_operation";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String DATA__NE = "ne_data";

        public static final String DATA__LIKE = "like_data";

        public static final String DATA__IN = "list_data";

        public static final String DATA__BEGIN = "begin_data";

        public static final String DATA__END = "end_data";
    }
}