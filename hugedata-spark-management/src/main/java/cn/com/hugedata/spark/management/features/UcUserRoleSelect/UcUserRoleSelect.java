package cn.com.hugedata.spark.management.features.UcUserRoleSelect;

import cn.com.hugedata.spark.commons.storage.entity.UcManageRole;

public class UcUserRoleSelect extends UcManageRole {

    private static final long serialVersionUID = -6947082636640608807L;
    
    /**
     * 用户是否已经选择这个角色.
     */
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
