package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UcManageRoleRight extends BaseEntity<Integer> {
    /**
     * id
     */
    private Integer id;

    /**
     * roleId
     */
    private String roleId;

    /**
     * rightId
     */
    private String rightId;

    private static final long serialVersionUID = -1369006755693292698L;

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
     * {@link #roleId}
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * {@link #roleId}
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * {@link #rightId}
     */
    public String getRightId() {
        return rightId;
    }

    /**
     * {@link #rightId}
     */
    public void setRightId(String rightId) {
        this.rightId = rightId == null ? null : rightId.trim();
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

    public static JSONObject toJSON(UcManageRoleRight e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcManageRoleRight> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcManageRoleRight.toJSON(this);
    }

    public static class Fields {
        public static final String ID = "id";

        public static final String ROLE_ID = "roleId";

        public static final String RIGHT_ID = "rightId";
    }

    public static class Query {
        public static final String ID__NE = "ne_id";

        public static final String ID__IN = "list_id";

        public static final String ID__BEGIN = "begin_id";

        public static final String ID__END = "end_id";

        public static final String ROLE_ID__NE = "ne_roleId";

        public static final String ROLE_ID__LIKE = "like_roleId";

        public static final String ROLE_ID__IN = "list_roleId";

        public static final String ROLE_ID__BEGIN = "begin_roleId";

        public static final String ROLE_ID__END = "end_roleId";

        public static final String RIGHT_ID__NE = "ne_rightId";

        public static final String RIGHT_ID__LIKE = "like_rightId";

        public static final String RIGHT_ID__IN = "list_rightId";

        public static final String RIGHT_ID__BEGIN = "begin_rightId";

        public static final String RIGHT_ID__END = "end_rightId";
    }
}