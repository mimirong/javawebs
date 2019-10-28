package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UcParkDept extends BaseEntity<Integer> {
    /**
     * deptId
     */
    private Integer deptId;

    /**
     * deptName
     */
    private String deptName;

    private static final long serialVersionUID = 1098129476368989448L;

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

    @Override
    public String getIdPropertyName() {
        return "deptId";
    }

    @Override
    public Integer getIdValue() {
        return deptId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.deptId = id;
    }

    public static JSONObject toJSON(UcParkDept e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcParkDept> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcParkDept.toJSON(this);
    }

    public static class Fields {
        public static final String DEPT_ID = "deptId";

        public static final String DEPT_NAME = "deptName";
    }

    public static class Query {
        public static final String DEPT_ID__NE = "ne_deptId";

        public static final String DEPT_ID__IN = "list_deptId";

        public static final String DEPT_ID__BEGIN = "begin_deptId";

        public static final String DEPT_ID__END = "end_deptId";

        public static final String DEPT_NAME__NE = "ne_deptName";

        public static final String DEPT_NAME__LIKE = "like_deptName";

        public static final String DEPT_NAME__IN = "list_deptName";

        public static final String DEPT_NAME__BEGIN = "begin_deptName";

        public static final String DEPT_NAME__END = "end_deptName";
    }
}