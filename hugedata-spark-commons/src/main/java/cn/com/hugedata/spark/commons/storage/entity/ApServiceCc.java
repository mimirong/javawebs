package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApServiceCc extends BaseEntity<Integer> {
    /**
     * id
     */
    private Integer id;

    /**
     * serviceId
     */
    private Integer serviceId;

    /**
     * ccUserId
     */
    private Integer ccUserId;

    /**
     * ccUserName
     */
    private String ccUserName;

    /**
     * ccDeptId
     */
    private Integer ccDeptId;

    /**
     * ccDeptName
     */
    private String ccDeptName;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = -3076511612510407752L;

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
     * {@link #ccUserId}
     */
    public Integer getCcUserId() {
        return ccUserId;
    }

    /**
     * {@link #ccUserId}
     */
    public void setCcUserId(Integer ccUserId) {
        this.ccUserId = ccUserId;
    }

    /**
     * {@link #ccUserName}
     */
    public String getCcUserName() {
        return ccUserName;
    }

    /**
     * {@link #ccUserName}
     */
    public void setCcUserName(String ccUserName) {
        this.ccUserName = ccUserName == null ? null : ccUserName.trim();
    }

    /**
     * {@link #ccDeptId}
     */
    public Integer getCcDeptId() {
        return ccDeptId;
    }

    /**
     * {@link #ccDeptId}
     */
    public void setCcDeptId(Integer ccDeptId) {
        this.ccDeptId = ccDeptId;
    }

    /**
     * {@link #ccDeptName}
     */
    public String getCcDeptName() {
        return ccDeptName;
    }

    /**
     * {@link #ccDeptName}
     */
    public void setCcDeptName(String ccDeptName) {
        this.ccDeptName = ccDeptName == null ? null : ccDeptName.trim();
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

    public static JSONObject toJSON(ApServiceCc e) {
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

    public static List<JSONObject> toJSON(List<ApServiceCc> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ApServiceCc.toJSON(this);
    }

    public static class Fields {
        public static final String ID = "id";

        public static final String SERVICE_ID = "serviceId";

        public static final String CC_USER_ID = "ccUserId";

        public static final String CC_USER_NAME = "ccUserName";

        public static final String CC_DEPT_ID = "ccDeptId";

        public static final String CC_DEPT_NAME = "ccDeptName";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String ID__NE = "ne_id";

        public static final String ID__IN = "list_id";

        public static final String ID__BEGIN = "begin_id";

        public static final String ID__END = "end_id";

        public static final String SERVICE_ID__NE = "ne_serviceId";

        public static final String SERVICE_ID__IN = "list_serviceId";

        public static final String SERVICE_ID__BEGIN = "begin_serviceId";

        public static final String SERVICE_ID__END = "end_serviceId";

        public static final String CC_USER_ID__NE = "ne_ccUserId";

        public static final String CC_USER_ID__IN = "list_ccUserId";

        public static final String CC_USER_ID__BEGIN = "begin_ccUserId";

        public static final String CC_USER_ID__END = "end_ccUserId";

        public static final String CC_USER_NAME__NE = "ne_ccUserName";

        public static final String CC_USER_NAME__LIKE = "like_ccUserName";

        public static final String CC_USER_NAME__IN = "list_ccUserName";

        public static final String CC_USER_NAME__BEGIN = "begin_ccUserName";

        public static final String CC_USER_NAME__END = "end_ccUserName";

        public static final String CC_DEPT_ID__NE = "ne_ccDeptId";

        public static final String CC_DEPT_ID__IN = "list_ccDeptId";

        public static final String CC_DEPT_ID__BEGIN = "begin_ccDeptId";

        public static final String CC_DEPT_ID__END = "end_ccDeptId";

        public static final String CC_DEPT_NAME__NE = "ne_ccDeptName";

        public static final String CC_DEPT_NAME__LIKE = "like_ccDeptName";

        public static final String CC_DEPT_NAME__IN = "list_ccDeptName";

        public static final String CC_DEPT_NAME__BEGIN = "begin_ccDeptName";

        public static final String CC_DEPT_NAME__END = "end_ccDeptName";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";
    }
}