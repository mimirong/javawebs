package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.ApServiceProcessConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApServiceProcess extends BaseEntity<Integer> implements ApServiceProcessConstants {
    /**
     * processId
     */
    private Integer processId;

    /**
     * serviceId
     */
    private Integer serviceId;

    /**
     * serviceStatus
     */
    private String serviceStatus;

    /**
     * seq
     */
    private Integer seq;

    /**
     * userId
     */
    private Integer userId;

    /**
     * userName
     */
    private String userName;

    /**
     * deptCode
     */
    private String deptCode;

    /**
     * deptName
     */
    private String deptName;

    /**
     * status
     */
    private String status;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * remark
     */
    private String remark;

    /**
     * attachmentsStatus
     */
    private String attachmentsStatus;

    private static final long serialVersionUID = -1723490177868454223L;

    /**
     * {@link #processId}
     */
    public Integer getProcessId() {
        return processId;
    }

    /**
     * {@link #processId}
     */
    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    /**
     * {@link #serviceId}
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * {@link #serviceId}
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * {@link #serviceStatus}
     */
    public String getServiceStatus() {
        return serviceStatus;
    }

    /**
     * {@link #serviceStatus}
     */
    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus == null ? null : serviceStatus.trim();
    }

    /**
     * {@link #seq}
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * {@link #seq}
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
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
     * {@link #deptCode}
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * {@link #deptCode}
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * {@link #deptName}
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * {@link #deptName}
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
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
     * {@link #attachmentsStatus}
     */
    public String getAttachmentsStatus() {
        return attachmentsStatus;
    }

    /**
     * {@link #attachmentsStatus}
     */
    public void setAttachmentsStatus(String attachmentsStatus) {
        this.attachmentsStatus = attachmentsStatus == null ? null : attachmentsStatus.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "processId";
    }

    @Override
    public Integer getIdValue() {
        return processId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.processId = id;
    }

    public static JSONObject toJSON(ApServiceProcess e) {
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

    public static List<JSONObject> toJSON(List<ApServiceProcess> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ApServiceProcess.toJSON(this);
    }

    public static class Fields {
        public static final String PROCESS_ID = "processId";

        public static final String SERVICE_ID = "serviceId";

        public static final String SERVICE_STATUS = "serviceStatus";

        public static final String SEQ = "seq";

        public static final String USER_ID = "userId";

        public static final String USER_NAME = "userName";

        public static final String DEPT_CODE = "deptCode";

        public static final String DEPT_NAME = "deptName";

        public static final String STATUS = "status";

        public static final String CREATE_TIME = "createTime";

        public static final String REMARK = "remark";

        public static final String ATTACHMENTS_STATUS = "attachmentsStatus";
    }

    public static class Query {
        public static final String PROCESS_ID__NE = "ne_processId";

        public static final String PROCESS_ID__IN = "list_processId";

        public static final String PROCESS_ID__BEGIN = "begin_processId";

        public static final String PROCESS_ID__END = "end_processId";

        public static final String SERVICE_ID__NE = "ne_serviceId";

        public static final String SERVICE_ID__IN = "list_serviceId";

        public static final String SERVICE_ID__BEGIN = "begin_serviceId";

        public static final String SERVICE_ID__END = "end_serviceId";

        public static final String SERVICE_STATUS__NE = "ne_serviceStatus";

        public static final String SERVICE_STATUS__LIKE = "like_serviceStatus";

        public static final String SERVICE_STATUS__IN = "list_serviceStatus";

        public static final String SERVICE_STATUS__BEGIN = "begin_serviceStatus";

        public static final String SERVICE_STATUS__END = "end_serviceStatus";

        public static final String SEQ__NE = "ne_seq";

        public static final String SEQ__IN = "list_seq";

        public static final String SEQ__BEGIN = "begin_seq";

        public static final String SEQ__END = "end_seq";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String USER_NAME__NE = "ne_userName";

        public static final String USER_NAME__LIKE = "like_userName";

        public static final String USER_NAME__IN = "list_userName";

        public static final String USER_NAME__BEGIN = "begin_userName";

        public static final String USER_NAME__END = "end_userName";

        public static final String DEPT_CODE__NE = "ne_deptCode";

        public static final String DEPT_CODE__LIKE = "like_deptCode";

        public static final String DEPT_CODE__IN = "list_deptCode";

        public static final String DEPT_CODE__BEGIN = "begin_deptCode";

        public static final String DEPT_CODE__END = "end_deptCode";

        public static final String DEPT_NAME__NE = "ne_deptName";

        public static final String DEPT_NAME__LIKE = "like_deptName";

        public static final String DEPT_NAME__IN = "list_deptName";

        public static final String DEPT_NAME__BEGIN = "begin_deptName";

        public static final String DEPT_NAME__END = "end_deptName";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";

        public static final String ATTACHMENTS_STATUS__NE = "ne_attachmentsStatus";

        public static final String ATTACHMENTS_STATUS__LIKE = "like_attachmentsStatus";

        public static final String ATTACHMENTS_STATUS__IN = "list_attachmentsStatus";

        public static final String ATTACHMENTS_STATUS__BEGIN = "begin_attachmentsStatus";

        public static final String ATTACHMENTS_STATUS__END = "end_attachmentsStatus";
    }
}