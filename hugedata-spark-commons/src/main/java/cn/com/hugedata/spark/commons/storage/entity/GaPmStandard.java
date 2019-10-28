package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.GaPmStandardConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaPmStandard extends BaseEntity<String> implements GaPmStandardConstants {
    /**
     * standardId
     */
    private String standardId;

    /**
     * name
     */
    private String name;

    /**
     * iconFileId
     */
    private String iconFileId;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * remark
     */
    private String remark;

    private static final long serialVersionUID = -394258395330819369L;

    /**
     * {@link #standardId}
     */
    public String getStandardId() {
        return standardId;
    }

    /**
     * {@link #standardId}
     */
    public void setStandardId(String standardId) {
        this.standardId = standardId == null ? null : standardId.trim();
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
     * {@link #iconFileId}
     */
    public String getIconFileId() {
        return iconFileId;
    }

    /**
     * {@link #iconFileId}
     */
    public void setIconFileId(String iconFileId) {
        this.iconFileId = iconFileId == null ? null : iconFileId.trim();
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

    @Override
    public String getIdPropertyName() {
        return "standardId";
    }

    @Override
    public String getIdValue() {
        return standardId;
    }

    @Override
    public void setIdValue(String id) {
        this.standardId = id;
    }

    public static JSONObject toJSON(GaPmStandard e) {
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

    public static List<JSONObject> toJSON(List<GaPmStandard> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaPmStandard.toJSON(this);
    }

    public static class Fields {
        public static final String STANDARD_ID = "standardId";

        public static final String NAME = "name";

        public static final String ICON_FILE_ID = "iconFileId";

        public static final String CREATE_TIME = "createTime";

        public static final String REMARK = "remark";
    }

    public static class Query {
        public static final String STANDARD_ID__NE = "ne_standardId";

        public static final String STANDARD_ID__LIKE = "like_standardId";

        public static final String STANDARD_ID__IN = "list_standardId";

        public static final String STANDARD_ID__BEGIN = "begin_standardId";

        public static final String STANDARD_ID__END = "end_standardId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String ICON_FILE_ID__NE = "ne_iconFileId";

        public static final String ICON_FILE_ID__LIKE = "like_iconFileId";

        public static final String ICON_FILE_ID__IN = "list_iconFileId";

        public static final String ICON_FILE_ID__BEGIN = "begin_iconFileId";

        public static final String ICON_FILE_ID__END = "end_iconFileId";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";
    }
}