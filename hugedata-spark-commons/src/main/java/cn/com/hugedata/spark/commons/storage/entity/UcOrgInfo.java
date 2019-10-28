package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UcOrgInfo extends BaseEntity<Integer> {
    /**
     * orgId
     */
    private Integer orgId;

    /**
     * refOrgId
     */
    private String refOrgId;

    /**
     * name
     */
    private String name;

    /**
     * parentOrgId
     */
    private Integer parentOrgId;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    private static final long serialVersionUID = -5712325688126697528L;

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
     * {@link #refOrgId}
     */
    public String getRefOrgId() {
        return refOrgId;
    }

    /**
     * {@link #refOrgId}
     */
    public void setRefOrgId(String refOrgId) {
        this.refOrgId = refOrgId == null ? null : refOrgId.trim();
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
     * {@link #parentOrgId}
     */
    public Integer getParentOrgId() {
        return parentOrgId;
    }

    /**
     * {@link #parentOrgId}
     */
    public void setParentOrgId(Integer parentOrgId) {
        this.parentOrgId = parentOrgId;
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
        return "orgId";
    }

    @Override
    public Integer getIdValue() {
        return orgId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.orgId = id;
    }

    public static JSONObject toJSON(UcOrgInfo e) {
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

    public static List<JSONObject> toJSON(List<UcOrgInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcOrgInfo.toJSON(this);
    }

    public static class Fields {
        public static final String ORG_ID = "orgId";

        public static final String REF_ORG_ID = "refOrgId";

        public static final String NAME = "name";

        public static final String PARENT_ORG_ID = "parentOrgId";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";
    }

    public static class Query {
        public static final String ORG_ID__NE = "ne_orgId";

        public static final String ORG_ID__IN = "list_orgId";

        public static final String ORG_ID__BEGIN = "begin_orgId";

        public static final String ORG_ID__END = "end_orgId";

        public static final String REF_ORG_ID__NE = "ne_refOrgId";

        public static final String REF_ORG_ID__LIKE = "like_refOrgId";

        public static final String REF_ORG_ID__IN = "list_refOrgId";

        public static final String REF_ORG_ID__BEGIN = "begin_refOrgId";

        public static final String REF_ORG_ID__END = "end_refOrgId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String PARENT_ORG_ID__NE = "ne_parentOrgId";

        public static final String PARENT_ORG_ID__IN = "list_parentOrgId";

        public static final String PARENT_ORG_ID__BEGIN = "begin_parentOrgId";

        public static final String PARENT_ORG_ID__END = "end_parentOrgId";

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