package cn.com.hugedata.spark.management.features.UcManageRole;

import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class UcManageRoleModel implements FeatureModel {
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

    @Override
    public String findModelId() {
        return roleId;
    }

    @Override
    public String findModelName() {
        return name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomMenu() {
        return customMenu;
    }

    public void setCustomMenu(String customMenu) {
        this.customMenu = customMenu;
    }

}
