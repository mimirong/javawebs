package cn.com.hugedata.spark.management.features.UcDeptInfo;

import java.util.Date;

import cn.com.hugedata.spark.commons.web.features.FeatureModel;

public class UcDeptInfoModel implements FeatureModel {
    /**
     * deptId
     */
    private Integer deptId;

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

    @Override
    public String findModelId() {
        return String.valueOf(deptId);
    }

    @Override
    public String findModelName() {
        return name;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Integer parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFullId() {
        return fullId;
    }

    public void setFullId(String fullId) {
        this.fullId = fullId;
    }

}
