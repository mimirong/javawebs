package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.ApServiceInfoConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApServiceInfo extends BaseEntity<Integer> implements ApServiceInfoConstants {
    /**
     * serviceId
     */
    private Integer serviceId;

    /**
     * businessNo
     */
    private String businessNo;

    /**
     * businessDeptPerson
     */
    private String businessDeptPerson;

    /**
     * status
     */
    private String status;

    /**
     * cellphone
     */
    private String cellphone;

    /**
     * deptcode
     */
    private Integer deptcode;

    /**
     * deptname
     */
    private String deptname;

    /**
     * guidecode
     */
    private String guidecode;

    /**
     * guidename
     */
    private String guidename;

    /**
     * businessType
     */
    private String businessType;

    /**
     * createUserId
     */
    private String createUserId;

    /**
     * createUserName
     */
    private String createUserName;

    /**
     * createWindowId
     */
    private String createWindowId;

    /**
     * createWindowName
     */
    private String createWindowName;

    /**
     * createCellphone
     */
    private String createCellphone;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * acceptUserId
     */
    private String acceptUserId;

    /**
     * acceptUserName
     */
    private String acceptUserName;

    /**
     * acceptWindowId
     */
    private String acceptWindowId;

    /**
     * acceptWindowName
     */
    private String acceptWindowName;

    /**
     * acceptCellphone
     */
    private String acceptCellphone;

    /**
     * acceptTime
     */
    private Date acceptTime;

    /**
     * fileId
     */
    private String fileId;

    /**
     * fileName
     */
    private String fileName;

    /**
     * timeLimit
     */
    private String timeLimit;

    /**
     * currentUserId
     */
    private Integer currentUserId;

    /**
     * currentUserName
     */
    private String currentUserName;

    /**
     * acceptNoticeFileId
     */
    private String acceptNoticeFileId;

    /**
     * acceptNoticeFileName
     */
    private String acceptNoticeFileName;

    /**
     * finishDocFileId
     */
    private String finishDocFileId;

    /**
     * finishDocFileName
     */
    private String finishDocFileName;

    /**
     * finishTime
     */
    private Date finishTime;

    /**
     * currentDeptId
     */
    private Integer currentDeptId;

    /**
     * currentDeptName
     */
    private String currentDeptName;

    /**
     * remark
     */
    private String remark;

    private static final long serialVersionUID = 3977997658149134879L;

    /**
     * {@link #serviceId}
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * {@link #serviceId}
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * {@link #businessNo}
     */
    public String getBusinessNo() {
        return businessNo;
    }

    /**
     * {@link #businessNo}
     */
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    /**
     * {@link #businessDeptPerson}
     */
    public String getBusinessDeptPerson() {
        return businessDeptPerson;
    }

    /**
     * {@link #businessDeptPerson}
     */
    public void setBusinessDeptPerson(String businessDeptPerson) {
        this.businessDeptPerson = businessDeptPerson == null ? null : businessDeptPerson.trim();
    }

    /**
     * {@link #status}
     */
    public String getStatus() {
        return status;
    }

    /**
     * {@link #status}
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * {@link #cellphone}
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * {@link #cellphone}
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     * {@link #deptcode}
     */
    public Integer getDeptcode() {
        return deptcode;
    }

    /**
     * {@link #deptcode}
     */
    public void setDeptcode(Integer deptcode) {
        this.deptcode = deptcode;
    }

    /**
     * {@link #deptname}
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * {@link #deptname}
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    /**
     * {@link #guidecode}
     */
    public String getGuidecode() {
        return guidecode;
    }

    /**
     * {@link #guidecode}
     */
    public void setGuidecode(String guidecode) {
        this.guidecode = guidecode == null ? null : guidecode.trim();
    }

    /**
     * {@link #guidename}
     */
    public String getGuidename() {
        return guidename;
    }

    /**
     * {@link #guidename}
     */
    public void setGuidename(String guidename) {
        this.guidename = guidename == null ? null : guidename.trim();
    }

    /**
     * {@link #businessType}
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * {@link #businessType}
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * {@link #createUserId}
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * {@link #createUserId}
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     * {@link #createUserName}
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * {@link #createUserName}
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    /**
     * {@link #createWindowId}
     */
    public String getCreateWindowId() {
        return createWindowId;
    }

    /**
     * {@link #createWindowId}
     */
    public void setCreateWindowId(String createWindowId) {
        this.createWindowId = createWindowId == null ? null : createWindowId.trim();
    }

    /**
     * {@link #createWindowName}
     */
    public String getCreateWindowName() {
        return createWindowName;
    }

    /**
     * {@link #createWindowName}
     */
    public void setCreateWindowName(String createWindowName) {
        this.createWindowName = createWindowName == null ? null : createWindowName.trim();
    }

    /**
     * {@link #createCellphone}
     */
    public String getCreateCellphone() {
        return createCellphone;
    }

    /**
     * {@link #createCellphone}
     */
    public void setCreateCellphone(String createCellphone) {
        this.createCellphone = createCellphone == null ? null : createCellphone.trim();
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
     * {@link #acceptUserId}
     */
    public String getAcceptUserId() {
        return acceptUserId;
    }

    /**
     * {@link #acceptUserId}
     */
    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId == null ? null : acceptUserId.trim();
    }

    /**
     * {@link #acceptUserName}
     */
    public String getAcceptUserName() {
        return acceptUserName;
    }

    /**
     * {@link #acceptUserName}
     */
    public void setAcceptUserName(String acceptUserName) {
        this.acceptUserName = acceptUserName == null ? null : acceptUserName.trim();
    }

    /**
     * {@link #acceptWindowId}
     */
    public String getAcceptWindowId() {
        return acceptWindowId;
    }

    /**
     * {@link #acceptWindowId}
     */
    public void setAcceptWindowId(String acceptWindowId) {
        this.acceptWindowId = acceptWindowId == null ? null : acceptWindowId.trim();
    }

    /**
     * {@link #acceptWindowName}
     */
    public String getAcceptWindowName() {
        return acceptWindowName;
    }

    /**
     * {@link #acceptWindowName}
     */
    public void setAcceptWindowName(String acceptWindowName) {
        this.acceptWindowName = acceptWindowName == null ? null : acceptWindowName.trim();
    }

    /**
     * {@link #acceptCellphone}
     */
    public String getAcceptCellphone() {
        return acceptCellphone;
    }

    /**
     * {@link #acceptCellphone}
     */
    public void setAcceptCellphone(String acceptCellphone) {
        this.acceptCellphone = acceptCellphone == null ? null : acceptCellphone.trim();
    }

    /**
     * {@link #acceptTime}
     */
    public Date getAcceptTime() {
        return acceptTime;
    }

    /**
     * {@link #acceptTime}
     */
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * {@link #fileId}
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * {@link #fileId}
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * {@link #fileName}
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * {@link #fileName}
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * {@link #timeLimit}
     */
    public String getTimeLimit() {
        return timeLimit;
    }

    /**
     * {@link #timeLimit}
     */
    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit == null ? null : timeLimit.trim();
    }

    /**
     * {@link #currentUserId}
     */
    public Integer getCurrentUserId() {
        return currentUserId;
    }

    /**
     * {@link #currentUserId}
     */
    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    /**
     * {@link #currentUserName}
     */
    public String getCurrentUserName() {
        return currentUserName;
    }

    /**
     * {@link #currentUserName}
     */
    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName == null ? null : currentUserName.trim();
    }

    /**
     * {@link #acceptNoticeFileId}
     */
    public String getAcceptNoticeFileId() {
        return acceptNoticeFileId;
    }

    /**
     * {@link #acceptNoticeFileId}
     */
    public void setAcceptNoticeFileId(String acceptNoticeFileId) {
        this.acceptNoticeFileId = acceptNoticeFileId == null ? null : acceptNoticeFileId.trim();
    }

    /**
     * {@link #acceptNoticeFileName}
     */
    public String getAcceptNoticeFileName() {
        return acceptNoticeFileName;
    }

    /**
     * {@link #acceptNoticeFileName}
     */
    public void setAcceptNoticeFileName(String acceptNoticeFileName) {
        this.acceptNoticeFileName = acceptNoticeFileName == null ? null : acceptNoticeFileName.trim();
    }

    /**
     * {@link #finishDocFileId}
     */
    public String getFinishDocFileId() {
        return finishDocFileId;
    }

    /**
     * {@link #finishDocFileId}
     */
    public void setFinishDocFileId(String finishDocFileId) {
        this.finishDocFileId = finishDocFileId == null ? null : finishDocFileId.trim();
    }

    /**
     * {@link #finishDocFileName}
     */
    public String getFinishDocFileName() {
        return finishDocFileName;
    }

    /**
     * {@link #finishDocFileName}
     */
    public void setFinishDocFileName(String finishDocFileName) {
        this.finishDocFileName = finishDocFileName == null ? null : finishDocFileName.trim();
    }

    /**
     * {@link #finishTime}
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * {@link #finishTime}
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * {@link #currentDeptId}
     */
    public Integer getCurrentDeptId() {
        return currentDeptId;
    }

    /**
     * {@link #currentDeptId}
     */
    public void setCurrentDeptId(Integer currentDeptId) {
        this.currentDeptId = currentDeptId;
    }

    /**
     * {@link #currentDeptName}
     */
    public String getCurrentDeptName() {
        return currentDeptName;
    }

    /**
     * {@link #currentDeptName}
     */
    public void setCurrentDeptName(String currentDeptName) {
        this.currentDeptName = currentDeptName == null ? null : currentDeptName.trim();
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
        return "serviceId";
    }

    @Override
    public Integer getIdValue() {
        return serviceId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.serviceId = id;
    }

    public static JSONObject toJSON(ApServiceInfo e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getAcceptTime() != null) {
            obj.put("acceptTimeStr", fmt.format(e.getAcceptTime()));
        }
        if (e.getFinishTime() != null) {
            obj.put("finishTimeStr", fmt.format(e.getFinishTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<ApServiceInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return ApServiceInfo.toJSON(this);
    }

    public static class Fields {
        public static final String SERVICE_ID = "serviceId";

        public static final String BUSINESS_NO = "businessNo";

        public static final String BUSINESS_DEPT_PERSON = "businessDeptPerson";

        public static final String STATUS = "status";

        public static final String CELLPHONE = "cellphone";

        public static final String DEPTCODE = "deptcode";

        public static final String DEPTNAME = "deptname";

        public static final String GUIDECODE = "guidecode";

        public static final String GUIDENAME = "guidename";

        public static final String BUSINESS_TYPE = "businessType";

        public static final String CREATE_USER_ID = "createUserId";

        public static final String CREATE_USER_NAME = "createUserName";

        public static final String CREATE_WINDOW_ID = "createWindowId";

        public static final String CREATE_WINDOW_NAME = "createWindowName";

        public static final String CREATE_CELLPHONE = "createCellphone";

        public static final String CREATE_TIME = "createTime";

        public static final String ACCEPT_USER_ID = "acceptUserId";

        public static final String ACCEPT_USER_NAME = "acceptUserName";

        public static final String ACCEPT_WINDOW_ID = "acceptWindowId";

        public static final String ACCEPT_WINDOW_NAME = "acceptWindowName";

        public static final String ACCEPT_CELLPHONE = "acceptCellphone";

        public static final String ACCEPT_TIME = "acceptTime";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String TIME_LIMIT = "timeLimit";

        public static final String CURRENT_USER_ID = "currentUserId";

        public static final String CURRENT_USER_NAME = "currentUserName";

        public static final String ACCEPT_NOTICE_FILE_ID = "acceptNoticeFileId";

        public static final String ACCEPT_NOTICE_FILE_NAME = "acceptNoticeFileName";

        public static final String FINISH_DOC_FILE_ID = "finishDocFileId";

        public static final String FINISH_DOC_FILE_NAME = "finishDocFileName";

        public static final String FINISH_TIME = "finishTime";

        public static final String CURRENT_DEPT_ID = "currentDeptId";

        public static final String CURRENT_DEPT_NAME = "currentDeptName";

        public static final String REMARK = "remark";
    }

    public static class Query {
        public static final String SERVICE_ID__NE = "ne_serviceId";

        public static final String SERVICE_ID__IN = "list_serviceId";

        public static final String SERVICE_ID__BEGIN = "begin_serviceId";

        public static final String SERVICE_ID__END = "end_serviceId";

        public static final String BUSINESS_NO__NE = "ne_businessNo";

        public static final String BUSINESS_NO__LIKE = "like_businessNo";

        public static final String BUSINESS_NO__IN = "list_businessNo";

        public static final String BUSINESS_NO__BEGIN = "begin_businessNo";

        public static final String BUSINESS_NO__END = "end_businessNo";

        public static final String BUSINESS_DEPT_PERSON__NE = "ne_businessDeptPerson";

        public static final String BUSINESS_DEPT_PERSON__LIKE = "like_businessDeptPerson";

        public static final String BUSINESS_DEPT_PERSON__IN = "list_businessDeptPerson";

        public static final String BUSINESS_DEPT_PERSON__BEGIN = "begin_businessDeptPerson";

        public static final String BUSINESS_DEPT_PERSON__END = "end_businessDeptPerson";

        public static final String STATUS__NE = "ne_status";

        public static final String STATUS__LIKE = "like_status";

        public static final String STATUS__IN = "list_status";

        public static final String STATUS__BEGIN = "begin_status";

        public static final String STATUS__END = "end_status";

        public static final String CELLPHONE__NE = "ne_cellphone";

        public static final String CELLPHONE__LIKE = "like_cellphone";

        public static final String CELLPHONE__IN = "list_cellphone";

        public static final String CELLPHONE__BEGIN = "begin_cellphone";

        public static final String CELLPHONE__END = "end_cellphone";

        public static final String DEPTCODE__NE = "ne_deptcode";

        public static final String DEPTCODE__IN = "list_deptcode";

        public static final String DEPTCODE__BEGIN = "begin_deptcode";

        public static final String DEPTCODE__END = "end_deptcode";

        public static final String DEPTNAME__NE = "ne_deptname";

        public static final String DEPTNAME__LIKE = "like_deptname";

        public static final String DEPTNAME__IN = "list_deptname";

        public static final String DEPTNAME__BEGIN = "begin_deptname";

        public static final String DEPTNAME__END = "end_deptname";

        public static final String GUIDECODE__NE = "ne_guidecode";

        public static final String GUIDECODE__LIKE = "like_guidecode";

        public static final String GUIDECODE__IN = "list_guidecode";

        public static final String GUIDECODE__BEGIN = "begin_guidecode";

        public static final String GUIDECODE__END = "end_guidecode";

        public static final String GUIDENAME__NE = "ne_guidename";

        public static final String GUIDENAME__LIKE = "like_guidename";

        public static final String GUIDENAME__IN = "list_guidename";

        public static final String GUIDENAME__BEGIN = "begin_guidename";

        public static final String GUIDENAME__END = "end_guidename";

        public static final String BUSINESS_TYPE__NE = "ne_businessType";

        public static final String BUSINESS_TYPE__LIKE = "like_businessType";

        public static final String BUSINESS_TYPE__IN = "list_businessType";

        public static final String BUSINESS_TYPE__BEGIN = "begin_businessType";

        public static final String BUSINESS_TYPE__END = "end_businessType";

        public static final String CREATE_USER_ID__NE = "ne_createUserId";

        public static final String CREATE_USER_ID__LIKE = "like_createUserId";

        public static final String CREATE_USER_ID__IN = "list_createUserId";

        public static final String CREATE_USER_ID__BEGIN = "begin_createUserId";

        public static final String CREATE_USER_ID__END = "end_createUserId";

        public static final String CREATE_USER_NAME__NE = "ne_createUserName";

        public static final String CREATE_USER_NAME__LIKE = "like_createUserName";

        public static final String CREATE_USER_NAME__IN = "list_createUserName";

        public static final String CREATE_USER_NAME__BEGIN = "begin_createUserName";

        public static final String CREATE_USER_NAME__END = "end_createUserName";

        public static final String CREATE_WINDOW_ID__NE = "ne_createWindowId";

        public static final String CREATE_WINDOW_ID__LIKE = "like_createWindowId";

        public static final String CREATE_WINDOW_ID__IN = "list_createWindowId";

        public static final String CREATE_WINDOW_ID__BEGIN = "begin_createWindowId";

        public static final String CREATE_WINDOW_ID__END = "end_createWindowId";

        public static final String CREATE_WINDOW_NAME__NE = "ne_createWindowName";

        public static final String CREATE_WINDOW_NAME__LIKE = "like_createWindowName";

        public static final String CREATE_WINDOW_NAME__IN = "list_createWindowName";

        public static final String CREATE_WINDOW_NAME__BEGIN = "begin_createWindowName";

        public static final String CREATE_WINDOW_NAME__END = "end_createWindowName";

        public static final String CREATE_CELLPHONE__NE = "ne_createCellphone";

        public static final String CREATE_CELLPHONE__LIKE = "like_createCellphone";

        public static final String CREATE_CELLPHONE__IN = "list_createCellphone";

        public static final String CREATE_CELLPHONE__BEGIN = "begin_createCellphone";

        public static final String CREATE_CELLPHONE__END = "end_createCellphone";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String ACCEPT_USER_ID__NE = "ne_acceptUserId";

        public static final String ACCEPT_USER_ID__LIKE = "like_acceptUserId";

        public static final String ACCEPT_USER_ID__IN = "list_acceptUserId";

        public static final String ACCEPT_USER_ID__BEGIN = "begin_acceptUserId";

        public static final String ACCEPT_USER_ID__END = "end_acceptUserId";

        public static final String ACCEPT_USER_NAME__NE = "ne_acceptUserName";

        public static final String ACCEPT_USER_NAME__LIKE = "like_acceptUserName";

        public static final String ACCEPT_USER_NAME__IN = "list_acceptUserName";

        public static final String ACCEPT_USER_NAME__BEGIN = "begin_acceptUserName";

        public static final String ACCEPT_USER_NAME__END = "end_acceptUserName";

        public static final String ACCEPT_WINDOW_ID__NE = "ne_acceptWindowId";

        public static final String ACCEPT_WINDOW_ID__LIKE = "like_acceptWindowId";

        public static final String ACCEPT_WINDOW_ID__IN = "list_acceptWindowId";

        public static final String ACCEPT_WINDOW_ID__BEGIN = "begin_acceptWindowId";

        public static final String ACCEPT_WINDOW_ID__END = "end_acceptWindowId";

        public static final String ACCEPT_WINDOW_NAME__NE = "ne_acceptWindowName";

        public static final String ACCEPT_WINDOW_NAME__LIKE = "like_acceptWindowName";

        public static final String ACCEPT_WINDOW_NAME__IN = "list_acceptWindowName";

        public static final String ACCEPT_WINDOW_NAME__BEGIN = "begin_acceptWindowName";

        public static final String ACCEPT_WINDOW_NAME__END = "end_acceptWindowName";

        public static final String ACCEPT_CELLPHONE__NE = "ne_acceptCellphone";

        public static final String ACCEPT_CELLPHONE__LIKE = "like_acceptCellphone";

        public static final String ACCEPT_CELLPHONE__IN = "list_acceptCellphone";

        public static final String ACCEPT_CELLPHONE__BEGIN = "begin_acceptCellphone";

        public static final String ACCEPT_CELLPHONE__END = "end_acceptCellphone";

        public static final String ACCEPT_TIME__NE = "ne_acceptTime";

        public static final String ACCEPT_TIME__IN = "list_acceptTime";

        public static final String ACCEPT_TIME__BEGIN = "begin_acceptTime";

        public static final String ACCEPT_TIME__END = "end_acceptTime";

        public static final String FILE_ID__NE = "ne_fileId";

        public static final String FILE_ID__LIKE = "like_fileId";

        public static final String FILE_ID__IN = "list_fileId";

        public static final String FILE_ID__BEGIN = "begin_fileId";

        public static final String FILE_ID__END = "end_fileId";

        public static final String FILE_NAME__NE = "ne_fileName";

        public static final String FILE_NAME__LIKE = "like_fileName";

        public static final String FILE_NAME__IN = "list_fileName";

        public static final String FILE_NAME__BEGIN = "begin_fileName";

        public static final String FILE_NAME__END = "end_fileName";

        public static final String TIME_LIMIT__NE = "ne_timeLimit";

        public static final String TIME_LIMIT__LIKE = "like_timeLimit";

        public static final String TIME_LIMIT__IN = "list_timeLimit";

        public static final String TIME_LIMIT__BEGIN = "begin_timeLimit";

        public static final String TIME_LIMIT__END = "end_timeLimit";

        public static final String CURRENT_USER_ID__NE = "ne_currentUserId";

        public static final String CURRENT_USER_ID__IN = "list_currentUserId";

        public static final String CURRENT_USER_ID__BEGIN = "begin_currentUserId";

        public static final String CURRENT_USER_ID__END = "end_currentUserId";

        public static final String CURRENT_USER_NAME__NE = "ne_currentUserName";

        public static final String CURRENT_USER_NAME__LIKE = "like_currentUserName";

        public static final String CURRENT_USER_NAME__IN = "list_currentUserName";

        public static final String CURRENT_USER_NAME__BEGIN = "begin_currentUserName";

        public static final String CURRENT_USER_NAME__END = "end_currentUserName";

        public static final String ACCEPT_NOTICE_FILE_ID__NE = "ne_acceptNoticeFileId";

        public static final String ACCEPT_NOTICE_FILE_ID__LIKE = "like_acceptNoticeFileId";

        public static final String ACCEPT_NOTICE_FILE_ID__IN = "list_acceptNoticeFileId";

        public static final String ACCEPT_NOTICE_FILE_ID__BEGIN = "begin_acceptNoticeFileId";

        public static final String ACCEPT_NOTICE_FILE_ID__END = "end_acceptNoticeFileId";

        public static final String ACCEPT_NOTICE_FILE_NAME__NE = "ne_acceptNoticeFileName";

        public static final String ACCEPT_NOTICE_FILE_NAME__LIKE = "like_acceptNoticeFileName";

        public static final String ACCEPT_NOTICE_FILE_NAME__IN = "list_acceptNoticeFileName";

        public static final String ACCEPT_NOTICE_FILE_NAME__BEGIN = "begin_acceptNoticeFileName";

        public static final String ACCEPT_NOTICE_FILE_NAME__END = "end_acceptNoticeFileName";

        public static final String FINISH_DOC_FILE_ID__NE = "ne_finishDocFileId";

        public static final String FINISH_DOC_FILE_ID__LIKE = "like_finishDocFileId";

        public static final String FINISH_DOC_FILE_ID__IN = "list_finishDocFileId";

        public static final String FINISH_DOC_FILE_ID__BEGIN = "begin_finishDocFileId";

        public static final String FINISH_DOC_FILE_ID__END = "end_finishDocFileId";

        public static final String FINISH_DOC_FILE_NAME__NE = "ne_finishDocFileName";

        public static final String FINISH_DOC_FILE_NAME__LIKE = "like_finishDocFileName";

        public static final String FINISH_DOC_FILE_NAME__IN = "list_finishDocFileName";

        public static final String FINISH_DOC_FILE_NAME__BEGIN = "begin_finishDocFileName";

        public static final String FINISH_DOC_FILE_NAME__END = "end_finishDocFileName";

        public static final String FINISH_TIME__NE = "ne_finishTime";

        public static final String FINISH_TIME__IN = "list_finishTime";

        public static final String FINISH_TIME__BEGIN = "begin_finishTime";

        public static final String FINISH_TIME__END = "end_finishTime";

        public static final String CURRENT_DEPT_ID__NE = "ne_currentDeptId";

        public static final String CURRENT_DEPT_ID__IN = "list_currentDeptId";

        public static final String CURRENT_DEPT_ID__BEGIN = "begin_currentDeptId";

        public static final String CURRENT_DEPT_ID__END = "end_currentDeptId";

        public static final String CURRENT_DEPT_NAME__NE = "ne_currentDeptName";

        public static final String CURRENT_DEPT_NAME__LIKE = "like_currentDeptName";

        public static final String CURRENT_DEPT_NAME__IN = "list_currentDeptName";

        public static final String CURRENT_DEPT_NAME__BEGIN = "begin_currentDeptName";

        public static final String CURRENT_DEPT_NAME__END = "end_currentDeptName";

        public static final String REMARK__NE = "ne_remark";

        public static final String REMARK__LIKE = "like_remark";

        public static final String REMARK__IN = "list_remark";

        public static final String REMARK__BEGIN = "begin_remark";

        public static final String REMARK__END = "end_remark";
    }
}