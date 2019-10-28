package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UcUserDept extends BaseEntity<Integer> {
    /**
     * recordId
     */
    private Integer recordId;

    /**
     * userId
     */
    private Integer userId;

    /**
     * deptId
     */
    private Integer deptId;

    private static final long serialVersionUID = 953549581322969739L;

    /**
     * {@link #recordId}
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * {@link #recordId}
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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
     * {@link #deptId}
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * {@link #deptId}
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public String getIdPropertyName() {
        return "recordId";
    }

    @Override
    public Integer getIdValue() {
        return recordId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.recordId = id;
    }

    public static JSONObject toJSON(UcUserDept e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcUserDept> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcUserDept.toJSON(this);
    }

    public static class Fields {
        public static final String RECORD_ID = "recordId";

        public static final String USER_ID = "userId";

        public static final String DEPT_ID = "deptId";
    }

    public static class Query {
        public static final String RECORD_ID__NE = "ne_recordId";

        public static final String RECORD_ID__IN = "list_recordId";

        public static final String RECORD_ID__BEGIN = "begin_recordId";

        public static final String RECORD_ID__END = "end_recordId";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String DEPT_ID__NE = "ne_deptId";

        public static final String DEPT_ID__IN = "list_deptId";

        public static final String DEPT_ID__BEGIN = "begin_deptId";

        public static final String DEPT_ID__END = "end_deptId";
    }
}