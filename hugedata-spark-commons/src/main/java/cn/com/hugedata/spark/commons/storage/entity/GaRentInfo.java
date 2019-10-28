package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.storage.constant.GaRentInfoConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaRentInfo extends BaseEntity<Integer> implements GaRentInfoConstants {
    /**
     * rentInfoId
     */
    private Integer rentInfoId;

    /**
     * buildingNo
     */
    private String buildingNo;

    /**
     * floor
     */
    private String floor;

    /**
     * roomNo
     */
    private String roomNo;

    /**
     * area
     */
    private String area;

    /**
     * isRent
     */
    private Boolean isRent;

    /**
     * companyName
     */
    private String companyName;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    private static final long serialVersionUID = 513136269442926939L;

    /**
     * {@link #rentInfoId}
     */
    public Integer getRentInfoId() {
        return rentInfoId;
    }

    /**
     * {@link #rentInfoId}
     */
    public void setRentInfoId(Integer rentInfoId) {
        this.rentInfoId = rentInfoId;
    }

    /**
     * {@link #buildingNo}
     */
    public String getBuildingNo() {
        return buildingNo;
    }

    /**
     * {@link #buildingNo}
     */
    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    /**
     * {@link #floor}
     */
    public String getFloor() {
        return floor;
    }

    /**
     * {@link #floor}
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * {@link #roomNo}
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * {@link #roomNo}
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    /**
     * {@link #area}
     */
    public String getArea() {
        return area;
    }

    /**
     * {@link #area}
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * {@link #isRent}
     */
    public Boolean getIsRent() {
        return isRent;
    }

    /**
     * {@link #isRent}
     */
    public void setIsRent(Boolean isRent) {
        this.isRent = isRent;
    }

    /**
     * {@link #companyName}
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * {@link #companyName}
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
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

    @Override
    public String getIdPropertyName() {
        return "rentInfoId";
    }

    @Override
    public Integer getIdValue() {
        return rentInfoId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.rentInfoId = id;
    }

    public static JSONObject toJSON(GaRentInfo e) {
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

    public static List<JSONObject> toJSON(List<GaRentInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaRentInfo.toJSON(this);
    }

    public static class Fields {
        public static final String RENT_INFO_ID = "rentInfoId";

        public static final String BUILDING_NO = "buildingNo";

        public static final String FLOOR = "floor";

        public static final String ROOM_NO = "roomNo";

        public static final String AREA = "area";

        public static final String IS_RENT = "isRent";

        public static final String COMPANY_NAME = "companyName";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDATE_TIME = "updateTime";
    }

    public static class Query {
        public static final String RENT_INFO_ID__NE = "ne_rentInfoId";

        public static final String RENT_INFO_ID__IN = "list_rentInfoId";

        public static final String RENT_INFO_ID__BEGIN = "begin_rentInfoId";

        public static final String RENT_INFO_ID__END = "end_rentInfoId";

        public static final String BUILDING_NO__NE = "ne_buildingNo";

        public static final String BUILDING_NO__LIKE = "like_buildingNo";

        public static final String BUILDING_NO__IN = "list_buildingNo";

        public static final String BUILDING_NO__BEGIN = "begin_buildingNo";

        public static final String BUILDING_NO__END = "end_buildingNo";

        public static final String FLOOR__NE = "ne_floor";

        public static final String FLOOR__LIKE = "like_floor";

        public static final String FLOOR__IN = "list_floor";

        public static final String FLOOR__BEGIN = "begin_floor";

        public static final String FLOOR__END = "end_floor";

        public static final String ROOM_NO__NE = "ne_roomNo";

        public static final String ROOM_NO__LIKE = "like_roomNo";

        public static final String ROOM_NO__IN = "list_roomNo";

        public static final String ROOM_NO__BEGIN = "begin_roomNo";

        public static final String ROOM_NO__END = "end_roomNo";

        public static final String AREA__NE = "ne_area";

        public static final String AREA__LIKE = "like_area";

        public static final String AREA__IN = "list_area";

        public static final String AREA__BEGIN = "begin_area";

        public static final String AREA__END = "end_area";

        public static final String IS_RENT__NE = "ne_isRent";

        public static final String IS_RENT__IN = "list_isRent";

        public static final String IS_RENT__BEGIN = "begin_isRent";

        public static final String IS_RENT__END = "end_isRent";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";

        public static final String UPDATE_TIME__NE = "ne_updateTime";

        public static final String UPDATE_TIME__IN = "list_updateTime";

        public static final String UPDATE_TIME__BEGIN = "begin_updateTime";

        public static final String UPDATE_TIME__END = "end_updateTime";
    }
}