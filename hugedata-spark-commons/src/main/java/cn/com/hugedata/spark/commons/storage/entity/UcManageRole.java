package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UcManageRole extends BaseEntity<String> {
    /**
     * roleId
     */
    private String roleId;

    /**
     * name
     */
    private String name;

    /**
     * description
     */
    private String description;

    /**
     * customMenu
     */
    private String customMenu;

    private static final long serialVersionUID = 2634542040078665094L;

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
     * {@link #description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@link #description}
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * {@link #customMenu}
     */
    public String getCustomMenu() {
        return customMenu;
    }

    /**
     * {@link #customMenu}
     */
    public void setCustomMenu(String customMenu) {
        this.customMenu = customMenu == null ? null : customMenu.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "roleId";
    }

    @Override
    public String getIdValue() {
        return roleId;
    }

    @Override
    public void setIdValue(String id) {
        this.roleId = id;
    }

    public static JSONObject toJSON(UcManageRole e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<UcManageRole> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return UcManageRole.toJSON(this);
    }

    public static class Fields {
        public static final String ROLE_ID = "roleId";

        public static final String NAME = "name";

        public static final String DESCRIPTION = "description";

        public static final String CUSTOM_MENU = "customMenu";
    }

    public static class Query {
        public static final String ROLE_ID__NE = "ne_roleId";

        public static final String ROLE_ID__LIKE = "like_roleId";

        public static final String ROLE_ID__IN = "list_roleId";

        public static final String ROLE_ID__BEGIN = "begin_roleId";

        public static final String ROLE_ID__END = "end_roleId";

        public static final String NAME__NE = "ne_name";

        public static final String NAME__LIKE = "like_name";

        public static final String NAME__IN = "list_name";

        public static final String NAME__BEGIN = "begin_name";

        public static final String NAME__END = "end_name";

        public static final String DESCRIPTION__NE = "ne_description";

        public static final String DESCRIPTION__LIKE = "like_description";

        public static final String DESCRIPTION__IN = "list_description";

        public static final String DESCRIPTION__BEGIN = "begin_description";

        public static final String DESCRIPTION__END = "end_description";

        public static final String CUSTOM_MENU__NE = "ne_customMenu";

        public static final String CUSTOM_MENU__LIKE = "like_customMenu";

        public static final String CUSTOM_MENU__IN = "list_customMenu";

        public static final String CUSTOM_MENU__BEGIN = "begin_customMenu";

        public static final String CUSTOM_MENU__END = "end_customMenu";
    }
}