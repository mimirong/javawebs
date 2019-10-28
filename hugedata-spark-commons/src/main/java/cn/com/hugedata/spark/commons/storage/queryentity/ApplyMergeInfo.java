package cn.com.hugedata.spark.commons.storage.queryentity;

import java.util.Date;

public class ApplyMergeInfo {

    private String applyId;
    
    private String applyType;
    
    private String title;
    
    private Integer applierUserId;
    
    private String applierName;
    
    private String contact;
    
    private String status;
    
    private Date createTime;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getApplierUserId() {
        return applierUserId;
    }

    public void setApplierUserId(Integer applierUserId) {
        this.applierUserId = applierUserId;
    }

    public String getApplierName() {
        return applierName;
    }

    public void setApplierName(String applierName) {
        this.applierName = applierName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
