package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UcDeptInfo extends BaseEntity<Integer> {
    /**
     * deptId
     */
    private Integer deptId;

    /**
     * refDeptId
     */
    private String refDeptId;

    /**
     * name
     */
    private String name;

    /**
     * orgId
     */
    private Integer orgId;

    /**
     * parentDeptId
     */
    private Integer parentDeptId;

    /**
     * fullId
     */
    private String fullId;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    private static final long serialVersionUID = -6211656172127880024L;

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
     * {@link #refDeptId}
     */
    public String getRefDeptId() {
        return refDeptId;
    }

    /**
     * {@link #refDeptId}
     */
    public void setRefDeptId(String refDeptId) {
        this.refDeptId = refDeptId == null ? null : refDeptId.trim();
    }

    /**
     * {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * {@link #name}
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * {@link #orgId}
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * {@link #orgId}
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * {@link #parentDeptId}
     */
    public Integer getParentDeptId() {
        return parentDeptId;
    }

    /**
     * {@link #parentDeptId}
     */
    public void setParentDeptId(Integer parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    /**
     * {@link #fullId}
     */
    public String getFullId() {
        return fullId;
    }

    /**
     * {@link #fullId}
     */
    public void setFullId(String fullId) {
        this.fullId = fullId == null ? null : fullId.trim();
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
     * {@link #updateTime}
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * {@link #updateTime}
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public static JSONObject toJSON(UcDeptInfo e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcDeptInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcDeptInfo.toJSON(this);
    }

    public static class Fields {
        public static final String DEPT_ID = "deptId";

        public static final String REF_DEPT_ID = "refDeptId";

        public static final String NAME = "name";

        public static final String ORG_ID = "orgId";

        public static final String PARENT_DEPT_ID = "parentDeptId";

        public static final String FULL_ID = "fullId";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";
    }

    public static class Query {
        public static final String DEPT_ID__NE = "ne_deptId";

        public static final String DEPT_ID__IN = "list_deptId";

        public static final String DEPT_ID__BEGIN = "begin_deptId";

        public static final String DEPT_ID__END = "end_deptId";

        public static final String REF_DEPT_ID__NE = "ne_refDeptId";

        public static final String REF_DEPT_ID__LIKE = "like_refDeptId";

        public static final String REF_DEPT_ID__IN = "list_refDeptId";

        public static final String REF_DEPT_ID__BEGIN = "begin_refDeptId";

        public static final String REF_DEPT_ID__END = "end_refDeptId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String ORG_ID__NE = "ne_orgId";

        public static final String ORG_ID__IN = "list_orgId";

        public static final String ORG_ID__BEGIN = "begin_orgId";

        public static final String ORG_ID__END = "end_orgId";

        public static final String PARENT_DEPT_ID__NE = "ne_parentDeptId";

        public static final String PARENT_DEPT_ID__IN = "list_parentDeptId";

        public static final String PARENT_DEPT_ID__BEGIN = "begin_parentDeptId";

        public static final String PARENT_DEPT_ID__END = "end_parentDeptId";

        public static final String FULL_ID__NE = "ne_fullId";

        public static final String FULL_ID__LIKE = "like_fullId";

        public static final String FULL_ID__IN = "list_fullId";

        public static final String FULL_ID__BEGIN = "begin_fullId";

        public static final String FULL_ID__END = "end_fullId";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";
    }
}