package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogInterfaceCall extends BaseEntity<Integer> {
    /**
     * id
     */
    private Integer id;

    /**
     * userId
     */
    private Integer userId;

    /**
     * method
     */
    private String method;

    /**
     * clientType
     */
    private String clientType;

    /**
     * clientVersion
     */
    private String clientVersion;

    /**
     * clientVersionCode
     */
    private Integer clientVersionCode;

    /**
     * deviceModel
     */
    private String deviceModel;

    /**
     * deviceId
     */
    private String deviceId;

    /**
     * systemVersion
     */
    private String systemVersion;

    /**
     * requestTime
     */
    private Date requestTime;

    /**
     * processDuration
     */
    private Integer processDuration;

    /**
     * isSuccess
     */
    private Boolean isSuccess;

    /**
     * errorMessage
     */
    private String errorMessage;

    /**
     * requestLength
     */
    private Integer requestLength;

    /**
     * responseLength
     */
    private Integer responseLength;

    private static final long serialVersionUID = 6244324594194109596L;

    /**
     * {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * {@link #id}
     */
    public void setId(Integer id) {
        this.id = id;
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
     * {@link #method}
     */
    public String getMethod() {
        return method;
    }

    /**
     * {@link #method}
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * {@link #clientType}
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * {@link #clientType}
     */
    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    /**
     * {@link #clientVersion}
     */
    public String getClientVersion() {
        return clientVersion;
    }

    /**
     * {@link #clientVersion}
     */
    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion == null ? null : clientVersion.trim();
    }

    /**
     * {@link #clientVersionCode}
     */
    public Integer getClientVersionCode() {
        return clientVersionCode;
    }

    /**
     * {@link #clientVersionCode}
     */
    public void setClientVersionCode(Integer clientVersionCode) {
        this.clientVersionCode = clientVersionCode;
    }

    /**
     * {@link #deviceModel}
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * {@link #deviceModel}
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    /**
     * {@link #deviceId}
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * {@link #deviceId}
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    /**
     * {@link #systemVersion}
     */
    public String getSystemVersion() {
        return systemVersion;
    }

    /**
     * {@link #systemVersion}
     */
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion == null ? null : systemVersion.trim();
    }

    /**
     * {@link #requestTime}
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * {@link #requestTime}
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * {@link #processDuration}
     */
    public Integer getProcessDuration() {
        return processDuration;
    }

    /**
     * {@link #processDuration}
     */
    public void setProcessDuration(Integer processDuration) {
        this.processDuration = processDuration;
    }

    /**
     * {@link #isSuccess}
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * {@link #isSuccess}
     */
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * {@link #errorMessage}
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * {@link #errorMessage}
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    /**
     * {@link #requestLength}
     */
    public Integer getRequestLength() {
        return requestLength;
    }

    /**
     * {@link #requestLength}
     */
    public void setRequestLength(Integer requestLength) {
        this.requestLength = requestLength;
    }

    /**
     * {@link #responseLength}
     */
    public Integer getResponseLength() {
        return responseLength;
    }

    /**
     * {@link #responseLength}
     */
    public void setResponseLength(Integer responseLength) {
        this.responseLength = responseLength;
    }

    @Override
    public String getIdPropertyName() {
        return "id";
    }

    @Override
    public Integer getIdValue() {
        return id;
    }

    @Override
    public void setIdValue(Integer id) {
        this.id = id;
    }

    public static JSONObject toJSON(LogInterfaceCall e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getRequestTime() != null) {
            obj.put("requestTimeStr", fmt.format(e.getRequestTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<LogInterfaceCall> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return LogInterfaceCall.toJSON(this);
    }

    public static class Fields {
        public static final String ID = "id";

        public static final String USER_ID = "userId";

        public static final String METHOD = "method";

        public static final String CLIENT_TYPE = "clientType";

        public static final String CLIENT_VERSION = "clientVersion";

        public static final String CLIENT_VERSION_CODE = "clientVersionCode";

        public static final String DEVICE_MODEL = "deviceModel";

        public static final String DEVICE_ID = "deviceId";

        public static final String SYSTEM_VERSION = "systemVersion";

        public static final String REQUEST_TIME = "requestTime";

        public static final String PROCESS_DURATION = "processDuration";

        public static final String IS_SUCCESS = "isSuccess";

        public static final String ERROR_MESSAGE = "errorMessage";

        public static final String REQUEST_LENGTH = "requestLength";

        public static final String RESPONSE_LENGTH = "responseLength";
    }

    public static class Query {
        public static final String ID__NE = "ne_id";

        public static final String ID__IN = "list_id";

        public static final String ID__BEGIN = "begin_id";

        public static final String ID__END = "end_id";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String METHOD__NE = "ne_method";

        public static final String METHOD__LIKE = "like_method";

        public static final String METHOD__IN = "list_method";

        public static final String METHOD__BEGIN = "begin_method";

        public static final String METHOD__END = "end_method";

        public static final String CLIENT_TYPE__NE = "ne_clientType";

        public static final String CLIENT_TYPE__LIKE = "like_clientType";

        public static final String CLIENT_TYPE__IN = "list_clientType";

        public static final String CLIENT_TYPE__BEGIN = "begin_clientType";

        public static final String CLIENT_TYPE__END = "end_clientType";

        public static final String CLIENT_VERSION__NE = "ne_clientVersion";

        public static final String CLIENT_VERSION__LIKE = "like_clientVersion";

        public static final String CLIENT_VERSION__IN = "list_clientVersion";

        public static final String CLIENT_VERSION__BEGIN = "begin_clientVersion";

        public static final String CLIENT_VERSION__END = "end_clientVersion";

        public static final String CLIENT_VERSION_CODE__NE = "ne_clientVersionCode";

        public static final String CLIENT_VERSION_CODE__IN = "list_clientVersionCode";

        public static final String CLIENT_VERSION_CODE__BEGIN = "begin_clientVersionCode";

        public static final String CLIENT_VERSION_CODE__END = "end_clientVersionCode";

        public static final String DEVICE_MODEL__NE = "ne_deviceModel";

        public static final String DEVICE_MODEL__LIKE = "like_deviceModel";

        public static final String DEVICE_MODEL__IN = "list_deviceModel";

        public static final String DEVICE_MODEL__BEGIN = "begin_deviceModel";

        public static final String DEVICE_MODEL__END = "end_deviceModel";

        public static final String DEVICE_ID__NE = "ne_deviceId";

        public static final String DEVICE_ID__LIKE = "like_deviceId";

        public static final String DEVICE_ID__IN = "list_deviceId";

        public static final String DEVICE_ID__BEGIN = "begin_deviceId";

        public static final String DEVICE_ID__END = "end_deviceId";

        public static final String SYSTEM_VERSION__NE = "ne_systemVersion";

        public static final String SYSTEM_VERSION__LIKE = "like_systemVersion";

        public static final String SYSTEM_VERSION__IN = "list_systemVersion";

        public static final String SYSTEM_VERSION__BEGIN = "begin_systemVersion";

        public static final String SYSTEM_VERSION__END = "end_systemVersion";

        public static final String REQUEST_TIME__NE = "ne_requestTime";

        public static final String REQUEST_TIME__IN = "list_requestTime";

        public static final String REQUEST_TIME__BEGIN = "begin_requestTime";

        public static final String REQUEST_TIME__END = "end_requestTime";

        public static final String PROCESS_DURATION__NE = "ne_processDuration";

        public static final String PROCESS_DURATION__IN = "list_processDuration";

        public static final String PROCESS_DURATION__BEGIN = "begin_processDuration";

        public static final String PROCESS_DURATION__END = "end_processDuration";

        public static final String IS_SUCCESS__NE = "ne_isSuccess";

        public static final String IS_SUCCESS__IN = "list_isSuccess";

        public static final String IS_SUCCESS__BEGIN = "begin_isSuccess";

        public static final String IS_SUCCESS__END = "end_isSuccess";

        public static final String ERROR_MESSAGE__NE = "ne_errorMessage";

        public static final String ERROR_MESSAGE__LIKE = "like_errorMessage";

        public static final String ERROR_MESSAGE__IN = "list_errorMessage";

        public static final String ERROR_MESSAGE__BEGIN = "begin_errorMessage";

        public static final String ERROR_MESSAGE__END = "end_errorMessage";

        public static final String REQUEST_LENGTH__NE = "ne_requestLength";

        public static final String REQUEST_LENGTH__IN = "list_requestLength";

        public static final String REQUEST_LENGTH__BEGIN = "begin_requestLength";

        public static final String REQUEST_LENGTH__END = "end_requestLength";

        public static final String RESPONSE_LENGTH__NE = "ne_responseLength";

        public static final String RESPONSE_LENGTH__IN = "list_responseLength";

        public static final String RESPONSE_LENGTH__BEGIN = "begin_responseLength";

        public static final String RESPONSE_LENGTH__END = "end_responseLength";
    }
}