package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.UcManageRightConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UcManageRight extends BaseEntity<String> implements UcManageRightConstants {
    /**
     * rightId
     */
    private String rightId;

    /**
     * parentId
     */
    private String parentId;

    /**
     * name
     */
    private String name;

    /**
     * systemType
     */
    private String systemType;

    /**
     * url
     */
    private String url;

    /**
     * display
     */
    private Boolean display;

    /**
     * sortIndex
     */
    private Integer sortIndex;

    /**
     * iconName
     */
    private String iconName;

    private static final long serialVersionUID = 671014082856291389L;

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

    /**
     * {@link #parentId}
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * {@link #parentId}
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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
     * {@link #systemType}
     */
    public String getSystemType() {
        return systemType;
    }

    /**
     * {@link #systemType}
     */
    public void setSystemType(String systemType) {
        this.systemType = systemType == null ? null : systemType.trim();
    }

    /**
     * {@link #url}
     */
    public String getUrl() {
        return url;
    }

    /**
     * {@link #url}
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * {@link #display}
     */
    public Boolean getDisplay() {
        return display;
    }

    /**
     * {@link #display}
     */
    public void setDisplay(Boolean display) {
        this.display = display;
    }

    /**
     * {@link #sortIndex}
     */
    public Integer getSortIndex() {
        return sortIndex;
    }

    /**
     * {@link #sortIndex}
     */
    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    /**
     * {@link #iconName}
     */
    public String getIconName() {
        return iconName;
    }

    /**
     * {@link #iconName}
     */
    public void setIconName(String iconName) {
        this.iconName = iconName == null ? null : iconName.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "rightId";
    }

    @Override
    public String getIdValue() {
        return rightId;
    }

    @Override
    public void setIdValue(String id) {
        this.rightId = id;
    }

    public static JSONObject toJSON(UcManageRight e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcManageRight> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcManageRight.toJSON(this);
    }

    public static class Fields {
        public static final String RIGHT_ID = "rightId";

        public static final String PARENT_ID = "parentId";

        public static final String NAME = "name";

        public static final String SYSTEM_TYPE = "systemType";

        public static final String URL = "url";

        public static final String DISPLAY = "display";

        public static final String SORT_INDEX = "sortIndex";

        public static final String ICON_NAME = "iconName";
    }

    public static class Query {
        public static final String RIGHT_ID__NE = "ne_rightId";

        public static final String RIGHT_ID__LIKE = "like_rightId";

        public static final String RIGHT_ID__IN = "list_rightId";

        public static final String RIGHT_ID__BEGIN = "begin_rightId";

        public static final String RIGHT_ID__END = "end_rightId";

        public static final String PARENT_ID__NE = "ne_parentId";

        public static final String PARENT_ID__LIKE = "like_parentId";

        public static final String PARENT_ID__IN = "list_parentId";

        public static final String PARENT_ID__BEGIN = "begin_parentId";

        public static final String PARENT_ID__END = "end_parentId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String SYSTEM_TYPE__NE = "ne_systemType";

        public static final String SYSTEM_TYPE__LIKE = "like_systemType";

        public static final String SYSTEM_TYPE__IN = "list_systemType";

        public static final String SYSTEM_TYPE__BEGIN = "begin_systemType";

        public static final String SYSTEM_TYPE__END = "end_systemType";

        public static final String URL__NE = "ne_url";

        public static final String URL__LIKE = "like_url";

        public static final String URL__IN = "list_url";

        public static final String URL__BEGIN = "begin_url";

        public static final String URL__END = "end_url";

        public static final String DISPLAY__NE = "ne_display";

        public static final String DISPLAY__IN = "list_display";

        public static final String DISPLAY__BEGIN = "begin_display";

        public static final String DISPLAY__END = "end_display";

        public static final String SORT_INDEX__NE = "ne_sortIndex";

        public static final String SORT_INDEX__IN = "list_sortIndex";

        public static final String SORT_INDEX__BEGIN = "begin_sortIndex";

        public static final String SORT_INDEX__END = "end_sortIndex";

        public static final String ICON_NAME__NE = "ne_iconName";

        public static final String ICON_NAME__LIKE = "like_iconName";

        public static final String ICON_NAME__IN = "list_iconName";

        public static final String ICON_NAME__BEGIN = "begin_iconName";

        public static final String ICON_NAME__END = "end_iconName";
    }
}