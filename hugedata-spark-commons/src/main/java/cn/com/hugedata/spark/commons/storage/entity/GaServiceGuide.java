package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.GaServiceGuideConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaServiceGuide extends BaseEntity<Integer> implements GaServiceGuideConstants {
    /**
     * guideId
     */
    private Integer guideId;

    /**
     * deptCode
     */
    private String deptCode;

    /**
     * deptName
     */
    private String deptName;

    /**
     * guideName
     */
    private String guideName;

    /**
     * guideType
     */
    private String guideType;

    /**
     * serviceSubject
     */
    private String serviceSubject;

    /**
     * according
     */
    private String according;

    /**
     * precondition
     */
    private String precondition;

    /**
     * jointDept
     */
    private String jointDept;

    /**
     * legalTimeLimit
     */
    private String legalTimeLimit;

    /**
     * promisedTimeLimit
     */
    private String promisedTimeLimit;

    /**
     * isCharge
     */
    private Boolean isCharge;

    /**
     * chargeAccording
     */
    private String chargeAccording;

    /**
     * chargeStandard
     */
    private String chargeStandard;

    /**
     * address
     */
    private String address;

    /**
     * workTime
     */
    private String workTime;

    /**
     * telephone
     */
    private String telephone;

    /**
     * complaintTelephone
     */
    private String complaintTelephone;

    /**
     * flowImageFileId
     */
    private String flowImageFileId;

    /**
     * flowImageFileName
     */
    private String flowImageFileName;

    /**
     * sortIndex
     */
    private Integer sortIndex;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * isInternal
     */
    private Boolean isInternal;

    /**
     * conditions
     */
    private String conditions;

    /**
     * material
     */
    private String material;

    /**
     * process
     */
    private String process;

    private static final long serialVersionUID = 6885926865386307477L;

    /**
     * {@link #guideId}
     */
    public Integer getGuideId() {
        return guideId;
    }

    /**
     * {@link #guideId}
     */
    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    /**
     * {@link #deptCode}
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * {@link #deptCode}
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * {@link #deptName}
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * {@link #deptName}
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * {@link #guideName}
     */
    public String getGuideName() {
        return guideName;
    }

    /**
     * {@link #guideName}
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    /**
     * {@link #guideType}
     */
    public String getGuideType() {
        return guideType;
    }

    /**
     * {@link #guideType}
     */
    public void setGuideType(String guideType) {
        this.guideType = guideType == null ? null : guideType.trim();
    }

    /**
     * {@link #serviceSubject}
     */
    public String getServiceSubject() {
        return serviceSubject;
    }

    /**
     * {@link #serviceSubject}
     */
    public void setServiceSubject(String serviceSubject) {
        this.serviceSubject = serviceSubject == null ? null : serviceSubject.trim();
    }

    /**
     * {@link #according}
     */
    public String getAccording() {
        return according;
    }

    /**
     * {@link #according}
     */
    public void setAccording(String according) {
        this.according = according == null ? null : according.trim();
    }

    /**
     * {@link #precondition}
     */
    public String getPrecondition() {
        return precondition;
    }

    /**
     * {@link #precondition}
     */
    public void setPrecondition(String precondition) {
        this.precondition = precondition == null ? null : precondition.trim();
    }

    /**
     * {@link #jointDept}
     */
    public String getJointDept() {
        return jointDept;
    }

    /**
     * {@link #jointDept}
     */
    public void setJointDept(String jointDept) {
        this.jointDept = jointDept == null ? null : jointDept.trim();
    }

    /**
     * {@link #legalTimeLimit}
     */
    public String getLegalTimeLimit() {
        return legalTimeLimit;
    }

    /**
     * {@link #legalTimeLimit}
     */
    public void setLegalTimeLimit(String legalTimeLimit) {
        this.legalTimeLimit = legalTimeLimit == null ? null : legalTimeLimit.trim();
    }

    /**
     * {@link #promisedTimeLimit}
     */
    public String getPromisedTimeLimit() {
        return promisedTimeLimit;
    }

    /**
     * {@link #promisedTimeLimit}
     */
    public void setPromisedTimeLimit(String promisedTimeLimit) {
        this.promisedTimeLimit = promisedTimeLimit == null ? null : promisedTimeLimit.trim();
    }

    /**
     * {@link #isCharge}
     */
    public Boolean getIsCharge() {
        return isCharge;
    }

    /**
     * {@link #isCharge}
     */
    public void setIsCharge(Boolean isCharge) {
        this.isCharge = isCharge;
    }

    /**
     * {@link #chargeAccording}
     */
    public String getChargeAccording() {
        return chargeAccording;
    }

    /**
     * {@link #chargeAccording}
     */
    public void setChargeAccording(String chargeAccording) {
        this.chargeAccording = chargeAccording == null ? null : chargeAccording.trim();
    }

    /**
     * {@link #chargeStandard}
     */
    public String getChargeStandard() {
        return chargeStandard;
    }

    /**
     * {@link #chargeStandard}
     */
    public void setChargeStandard(String chargeStandard) {
        this.chargeStandard = chargeStandard == null ? null : chargeStandard.trim();
    }

    /**
     * {@link #address}
     */
    public String getAddress() {
        return address;
    }

    /**
     * {@link #address}
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * {@link #workTime}
     */
    public String getWorkTime() {
        return workTime;
    }

    /**
     * {@link #workTime}
     */
    public void setWorkTime(String workTime) {
        this.workTime = workTime == null ? null : workTime.trim();
    }

    /**
     * {@link #telephone}
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * {@link #telephone}
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * {@link #complaintTelephone}
     */
    public String getComplaintTelephone() {
        return complaintTelephone;
    }

    /**
     * {@link #complaintTelephone}
     */
    public void setComplaintTelephone(String complaintTelephone) {
        this.complaintTelephone = complaintTelephone == null ? null : complaintTelephone.trim();
    }

    /**
     * {@link #flowImageFileId}
     */
    public String getFlowImageFileId() {
        return flowImageFileId;
    }

    /**
     * {@link #flowImageFileId}
     */
    public void setFlowImageFileId(String flowImageFileId) {
        this.flowImageFileId = flowImageFileId == null ? null : flowImageFileId.trim();
    }

    /**
     * {@link #flowImageFileName}
     */
    public String getFlowImageFileName() {
        return flowImageFileName;
    }

    /**
     * {@link #flowImageFileName}
     */
    public void setFlowImageFileName(String flowImageFileName) {
        this.flowImageFileName = flowImageFileName == null ? null : flowImageFileName.trim();
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
     * {@link #updateTime}
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * {@link #updateTime}
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
     * {@link #conditions}
     */
    public String getConditions() {
        return conditions;
    }

    /**
     * {@link #conditions}
     */
    public void setConditions(String conditions) {
        this.conditions = conditions == null ? null : conditions.trim();
    }

    /**
     * {@link #material}
     */
    public String getMaterial() {
        return material;
    }

    /**
     * {@link #material}
     */
    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    /**
     * {@link #process}
     */
    public String getProcess() {
        return process;
    }

    /**
     * {@link #process}
     */
    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "guideId";
    }

    @Override
    public Integer getIdValue() {
        return guideId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.guideId = id;
    }

    public static JSONObject toJSON(GaServiceGuide e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        if (e.getUpdateTime() != null) {
            obj.put("updateTimeStr", fmt.format(e.getUpdateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<GaServiceGuide> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaServiceGuide.toJSON(this);
    }

    public static class Fields {
        public static final String GUIDE_ID = "guideId";

        public static final String DEPT_CODE = "deptCode";

        public static final String DEPT_NAME = "deptName";

        public static final String GUIDE_NAME = "guideName";

        public static final String GUIDE_TYPE = "guideType";

        public static final String SERVICE_SUBJECT = "serviceSubject";

        public static final String ACCORDING = "according";

        public static final String PRECONDITION = "precondition";

        public static final String JOINT_DEPT = "jointDept";

        public static final String LEGAL_TIME_LIMIT = "legalTimeLimit";

        public static final String PROMISED_TIME_LIMIT = "promisedTimeLimit";

        public static final String IS_CHARGE = "isCharge";

        public static final String CHARGE_ACCORDING = "chargeAccording";

        public static final String CHARGE_STANDARD = "chargeStandard";

        public static final String ADDRESS = "address";

        public static final String WORK_TIME = "workTime";

        public static final String TELEPHONE = "telephone";

        public static final String COMPLAINT_TELEPHONE = "complaintTelephone";

        public static final String FLOW_IMAGE_FILE_ID = "flowImageFileId";

        public static final String FLOW_IMAGE_FILE_NAME = "flowImageFileName";

        public static final String SORT_INDEX = "sortIndex";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";

        public static final String IS_INTERNAL = "isInternal";

        public static final String CONDITIONS = "conditions";

        public static final String MATERIAL = "material";

        public static final String PROCESS = "process";
    }

    public static class Query {
        public static final String GUIDE_ID__NE = "ne_guideId";

        public static final String GUIDE_ID__IN = "list_guideId";

        public static final String GUIDE_ID__BEGIN = "begin_guideId";

        public static final String GUIDE_ID__END = "end_guideId";

        public static final String DEPT_CODE__NE = "ne_deptCode";

        public static final String DEPT_CODE__LIKE = "like_deptCode";

        public static final String DEPT_CODE__IN = "list_deptCode";

        public static final String DEPT_CODE__BEGIN = "begin_deptCode";

        public static final String DEPT_CODE__END = "end_deptCode";

        public static final String DEPT_NAME__NE = "ne_deptName";

        public static final String DEPT_NAME__LIKE = "like_deptName";

        public static final String DEPT_NAME__IN = "list_deptName";

        public static final String DEPT_NAME__BEGIN = "begin_deptName";

        public static final String DEPT_NAME__END = "end_deptName";

        public static final String GUIDE_NAME__NE = "ne_guideName";

        public static final String GUIDE_NAME__LIKE = "like_guideName";

        public static final String GUIDE_NAME__IN = "list_guideName";

        public static final String GUIDE_NAME__BEGIN = "begin_guideName";

        public static final String GUIDE_NAME__END = "end_guideName";

        public static final String GUIDE_TYPE__NE = "ne_guideType";

        public static final String GUIDE_TYPE__LIKE = "like_guideType";

        public static final String GUIDE_TYPE__IN = "list_guideType";

        public static final String GUIDE_TYPE__BEGIN = "begin_guideType";

        public static final String GUIDE_TYPE__END = "end_guideType";

        public static final String SERVICE_SUBJECT__NE = "ne_serviceSubject";

        public static final String SERVICE_SUBJECT__LIKE = "like_serviceSubject";

        public static final String SERVICE_SUBJECT__IN = "list_serviceSubject";

        public static final String SERVICE_SUBJECT__BEGIN = "begin_serviceSubject";

        public static final String SERVICE_SUBJECT__END = "end_serviceSubject";

        public static final String ACCORDING__NE = "ne_according";

        public static final String ACCORDING__LIKE = "like_according";

        public static final String ACCORDING__IN = "list_according";

        public static final String ACCORDING__BEGIN = "begin_according";

        public static final String ACCORDING__END = "end_according";

        public static final String PRECONDITION__NE = "ne_precondition";

        public static final String PRECONDITION__LIKE = "like_precondition";

        public static final String PRECONDITION__IN = "list_precondition";

        public static final String PRECONDITION__BEGIN = "begin_precondition";

        public static final String PRECONDITION__END = "end_precondition";

        public static final String JOINT_DEPT__NE = "ne_jointDept";

        public static final String JOINT_DEPT__LIKE = "like_jointDept";

        public static final String JOINT_DEPT__IN = "list_jointDept";

        public static final String JOINT_DEPT__BEGIN = "begin_jointDept";

        public static final String JOINT_DEPT__END = "end_jointDept";

        public static final String LEGAL_TIME_LIMIT__NE = "ne_legalTimeLimit";

        public static final String LEGAL_TIME_LIMIT__LIKE = "like_legalTimeLimit";

        public static final String LEGAL_TIME_LIMIT__IN = "list_legalTimeLimit";

        public static final String LEGAL_TIME_LIMIT__BEGIN = "begin_legalTimeLimit";

        public static final String LEGAL_TIME_LIMIT__END = "end_legalTimeLimit";

        public static final String PROMISED_TIME_LIMIT__NE = "ne_promisedTimeLimit";

        public static final String PROMISED_TIME_LIMIT__LIKE = "like_promisedTimeLimit";

        public static final String PROMISED_TIME_LIMIT__IN = "list_promisedTimeLimit";

        public static final String PROMISED_TIME_LIMIT__BEGIN = "begin_promisedTimeLimit";

        public static final String PROMISED_TIME_LIMIT__END = "end_promisedTimeLimit";

        public static final String IS_CHARGE__NE = "ne_isCharge";

        public static final String IS_CHARGE__IN = "list_isCharge";

        public static final String IS_CHARGE__BEGIN = "begin_isCharge";

        public static final String IS_CHARGE__END = "end_isCharge";

        public static final String CHARGE_ACCORDING__NE = "ne_chargeAccording";

        public static final String CHARGE_ACCORDING__LIKE = "like_chargeAccording";

        public static final String CHARGE_ACCORDING__IN = "list_chargeAccording";

        public static final String CHARGE_ACCORDING__BEGIN = "begin_chargeAccording";

        public static final String CHARGE_ACCORDING__END = "end_chargeAccording";

        public static final String CHARGE_STANDARD__NE = "ne_chargeStandard";

        public static final String CHARGE_STANDARD__LIKE = "like_chargeStandard";

        public static final String CHARGE_STANDARD__IN = "list_chargeStandard";

        public static final String CHARGE_STANDARD__BEGIN = "begin_chargeStandard";

        public static final String CHARGE_STANDARD__END = "end_chargeStandard";

        public static final String ADDRESS__NE = "ne_address";

        public static final String ADDRESS__LIKE = "like_address";

        public static final String ADDRESS__IN = "list_address";

        public static final String ADDRESS__BEGIN = "begin_address";

        public static final String ADDRESS__END = "end_address";

        public static final String WORK_TIME__NE = "ne_workTime";

        public static final String WORK_TIME__LIKE = "like_workTime";

        public static final String WORK_TIME__IN = "list_workTime";

        public static final String WORK_TIME__BEGIN = "begin_workTime";

        public static final String WORK_TIME__END = "end_workTime";

        public static final String TELEPHONE__NE = "ne_telephone";

        public static final String TELEPHONE__LIKE = "like_telephone";

        public static final String TELEPHONE__IN = "list_telephone";

        public static final String TELEPHONE__BEGIN = "begin_telephone";

        public static final String TELEPHONE__END = "end_telephone";

        public static final String COMPLAINT_TELEPHONE__NE = "ne_complaintTelephone";

        public static final String COMPLAINT_TELEPHONE__LIKE = "like_complaintTelephone";

        public static final String COMPLAINT_TELEPHONE__IN = "list_complaintTelephone";

        public static final String COMPLAINT_TELEPHONE__BEGIN = "begin_complaintTelephone";

        public static final String COMPLAINT_TELEPHONE__END = "end_complaintTelephone";

        public static final String FLOW_IMAGE_FILE_ID__NE = "ne_flowImageFileId";

        public static final String FLOW_IMAGE_FILE_ID__LIKE = "like_flowImageFileId";

        public static final String FLOW_IMAGE_FILE_ID__IN = "list_flowImageFileId";

        public static final String FLOW_IMAGE_FILE_ID__BEGIN = "begin_flowImageFileId";

        public static final String FLOW_IMAGE_FILE_ID__END = "end_flowImageFileId";

        public static final String FLOW_IMAGE_FILE_NAME__NE = "ne_flowImageFileName";

        public static final String FLOW_IMAGE_FILE_NAME__LIKE = "like_flowImageFileName";

        public static final String FLOW_IMAGE_FILE_NAME__IN = "list_flowImageFileName";

        public static final String FLOW_IMAGE_FILE_NAME__BEGIN = "begin_flowImageFileName";

        public static final String FLOW_IMAGE_FILE_NAME__END = "end_flowImageFileName";

        public static final String SORT_INDEX__NE = "ne_sortIndex";

        public static final String SORT_INDEX__IN = "list_sortIndex";

        public static final String SORT_INDEX__BEGIN = "begin_sortIndex";

        public static final String SORT_INDEX__END = "end_sortIndex";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";

        public static final String IS_INTERNAL__NE = "ne_isInternal";

        public static final String IS_INTERNAL__IN = "list_isInternal";

        public static final String IS_INTERNAL__BEGIN = "begin_isInternal";

        public static final String IS_INTERNAL__END = "end_isInternal";

        public static final String CONDITIONS__NE = "ne_conditions";

        public static final String CONDITIONS__LIKE = "like_conditions";

        public static final String CONDITIONS__IN = "list_conditions";

        public static final String CONDITIONS__BEGIN = "begin_conditions";

        public static final String CONDITIONS__END = "end_conditions";

        public static final String MATERIAL__NE = "ne_material";

        public static final String MATERIAL__LIKE = "like_material";

        public static final String MATERIAL__IN = "list_material";

        public static final String MATERIAL__BEGIN = "begin_material";

        public static final String MATERIAL__END = "end_material";

        public static final String PROCESS__NE = "ne_process";

        public static final String PROCESS__LIKE = "like_process";

        public static final String PROCESS__IN = "list_process";

        public static final String PROCESS__BEGIN = "begin_process";

        public static final String PROCESS__END = "end_process";
    }
}