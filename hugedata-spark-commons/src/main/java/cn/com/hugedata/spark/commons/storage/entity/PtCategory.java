package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.PtCategoryConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PtCategory extends BaseEntity<String> implements PtCategoryConstants {
    /**
     * categoryId
     */
    private String categoryId;

    /**
     * categoryName
     */
    private String categoryName;

    /**
     * isInternal
     */
    private Boolean isInternal;

    /**
     * isEditable
     */
    private Boolean isEditable;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = 1849880571551314940L;

    /**
     * {@link #categoryId}
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * {@link #categoryId}
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    /**
     * {@link #categoryName}
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * {@link #categoryName}
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * {@link #isInternal}
     */
    public Boolean getIsInternal() {
        return isInternal;
    }

    /**
     * {@link #isInternal}
     */
    public void setIsInternal(Boolean isInternal) {
        this.isInternal = isInternal;
    }

    /**
     * {@link #isEditable}
     */
    public Boolean getIsEditable() {
        return isEditable;
    }

    /**
     * {@link #isEditable}
     */
    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
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
        return "categoryId";
    }

    @Override
    public String getIdValue() {
        return categoryId;
    }

    @Override
    public void setIdValue(String id) {
        this.categoryId = id;
    }

    public static JSONObject toJSON(PtCategory e) {
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

    public static List<JSONObject> toJSON(List<PtCategory> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PtCategory.toJSON(this);
    }

    public static class Fields {
        public static final String CATEGORY_ID = "categoryId";

        public static final String CATEGORY_NAME = "categoryName";

        public static final String IS_INTERNAL = "isInternal";

        public static final String IS_EDITABLE = "isEditable";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String CATEGORY_ID__NE = "ne_categoryId";

        public static final String CATEGORY_ID__LIKE = "like_categoryId";

        public static final String CATEGORY_ID__IN = "list_categoryId";

        public static final String CATEGORY_ID__BEGIN = "begin_categoryId";

        public static final String CATEGORY_ID__END = "end_categoryId";

        public static final String CATEGORY_NAME__NE = "ne_categoryName";

        public static final String CATEGORY_NAME__LIKE = "like_categoryName";

        public static final String CATEGORY_NAME__IN = "list_categoryName";

        public static final String CATEGORY_NAME__BEGIN = "begin_categoryName";

        public static final String CATEGORY_NAME__END = "end_categoryName";

        public static final String IS_INTERNAL__NE = "ne_isInternal";

        public static final String IS_INTERNAL__IN = "list_isInternal";

        public static final String IS_INTERNAL__BEGIN = "begin_isInternal";

        public static final String IS_INTERNAL__END = "end_isInternal";

        public static final String IS_EDITABLE__NE = "ne_isEditable";

        public static final String IS_EDITABLE__IN = "list_isEditable";

        public static final String IS_EDITABLE__BEGIN = "begin_isEditable";

        public static final String IS_EDITABLE__END = "end_isEditable";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";
    }
}