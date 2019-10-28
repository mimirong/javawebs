package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GaMessagePost extends BaseEntity<Integer> {
    /**
     * postId
     */
    private Integer postId;

    /**
     * postTime
     */
    private Date postTime;

    /**
     * postAddr
     */
    private String postAddr;

    /**
     * eventDesc
     */
    private String eventDesc;

    /**
     * postLevel
     */
    private String postLevel;

    /**
     * happenTime
     */
    private Date happenTime;

    /**
     * posterName
     */
    private String posterName;

    /**
     * posterMobile
     */
    private String posterMobile;

    /**
     * file1Id
     */
    private String file1Id;

    /**
     * file2Id
     */
    private String file2Id;

    /**
     * file3Id
     */
    private String file3Id;

    /**
     * file4Id
     */
    private String file4Id;

    /**
     * file5Id
     */
    private String file5Id;

    /**
     * file6Id
     */
    private String file6Id;

    /**
     * createrId
     */
    private Integer createrId;

    /**
     * createrName
     */
    private String createrName;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = 1608590575367960897L;

    /**
     * {@link #postId}
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * {@link #postId}
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * {@link #postTime}
     */
    public Date getPostTime() {
        return postTime;
    }

    /**
     * {@link #postTime}
     */
    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    /**
     * {@link #postAddr}
     */
    public String getPostAddr() {
        return postAddr;
    }

    /**
     * {@link #postAddr}
     */
    public void setPostAddr(String postAddr) {
        this.postAddr = postAddr == null ? null : postAddr.trim();
    }

    /**
     * {@link #eventDesc}
     */
    public String getEventDesc() {
        return eventDesc;
    }

    /**
     * {@link #eventDesc}
     */
    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc == null ? null : eventDesc.trim();
    }

    /**
     * {@link #postLevel}
     */
    public String getPostLevel() {
        return postLevel;
    }

    /**
     * {@link #postLevel}
     */
    public void setPostLevel(String postLevel) {
        this.postLevel = postLevel == null ? null : postLevel.trim();
    }

    /**
     * {@link #happenTime}
     */
    public Date getHappenTime() {
        return happenTime;
    }

    /**
     * {@link #happenTime}
     */
    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

    /**
     * {@link #posterName}
     */
    public String getPosterName() {
        return posterName;
    }

    /**
     * {@link #posterName}
     */
    public void setPosterName(String posterName) {
        this.posterName = posterName == null ? null : posterName.trim();
    }

    /**
     * {@link #posterMobile}
     */
    public String getPosterMobile() {
        return posterMobile;
    }

    /**
     * {@link #posterMobile}
     */
    public void setPosterMobile(String posterMobile) {
        this.posterMobile = posterMobile == null ? null : posterMobile.trim();
    }

    /**
     * {@link #file1Id}
     */
    public String getFile1Id() {
        return file1Id;
    }

    /**
     * {@link #file1Id}
     */
    public void setFile1Id(String file1Id) {
        this.file1Id = file1Id == null ? null : file1Id.trim();
    }

    /**
     * {@link #file2Id}
     */
    public String getFile2Id() {
        return file2Id;
    }

    /**
     * {@link #file2Id}
     */
    public void setFile2Id(String file2Id) {
        this.file2Id = file2Id == null ? null : file2Id.trim();
    }

    /**
     * {@link #file3Id}
     */
    public String getFile3Id() {
        return file3Id;
    }

    /**
     * {@link #file3Id}
     */
    public void setFile3Id(String file3Id) {
        this.file3Id = file3Id == null ? null : file3Id.trim();
    }

    /**
     * {@link #file4Id}
     */
    public String getFile4Id() {
        return file4Id;
    }

    /**
     * {@link #file4Id}
     */
    public void setFile4Id(String file4Id) {
        this.file4Id = file4Id == null ? null : file4Id.trim();
    }

    /**
     * {@link #file5Id}
     */
    public String getFile5Id() {
        return file5Id;
    }

    /**
     * {@link #file5Id}
     */
    public void setFile5Id(String file5Id) {
        this.file5Id = file5Id == null ? null : file5Id.trim();
    }

    /**
     * {@link #file6Id}
     */
    public String getFile6Id() {
        return file6Id;
    }

    /**
     * {@link #file6Id}
     */
    public void setFile6Id(String file6Id) {
        this.file6Id = file6Id == null ? null : file6Id.trim();
    }

    /**
     * {@link #createrId}
     */
    public Integer getCreaterId() {
        return createrId;
    }

    /**
     * {@link #createrId}
     */
    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    /**
     * {@link #createrName}
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * {@link #createrName}
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
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
        return "postId";
    }

    @Override
    public Integer getIdValue() {
        return postId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.postId = id;
    }

    public static JSONObject toJSON(GaMessagePost e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getPostTime() != null) {
            obj.put("postTimeStr", fmt.format(e.getPostTime()));
        }
        if (e.getHappenTime() != null) {
            obj.put("happenTimeStr", fmt.format(e.getHappenTime()));
        }
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<GaMessagePost> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return GaMessagePost.toJSON(this);
    }

    public static class Fields {
        public static final String POST_ID = "postId";

        public static final String POST_TIME = "postTime";

        public static final String POST_ADDR = "postAddr";

        public static final String EVENT_DESC = "eventDesc";

        public static final String POST_LEVEL = "postLevel";

        public static final String HAPPEN_TIME = "happenTime";

        public static final String POSTER_NAME = "posterName";

        public static final String POSTER_MOBILE = "posterMobile";

        public static final String FILE1_ID = "file1Id";

        public static final String FILE2_ID = "file2Id";

        public static final String FILE3_ID = "file3Id";

        public static final String FILE4_ID = "file4Id";

        public static final String FILE5_ID = "file5Id";

        public static final String FILE6_ID = "file6Id";

        public static final String CREATER_ID = "createrId";

        public static final String CREATER_NAME = "createrName";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String POST_ID__NE = "ne_postId";

        public static final String POST_ID__IN = "list_postId";

        public static final String POST_ID__BEGIN = "begin_postId";

        public static final String POST_ID__END = "end_postId";

        public static final String POST_TIME__NE = "ne_postTime";

        public static final String POST_TIME__IN = "list_postTime";

        public static final String POST_TIME__BEGIN = "begin_postTime";

        public static final String POST_TIME__END = "end_postTime";

        public static final String POST_ADDR__NE = "ne_postAddr";

        public static final String POST_ADDR__LIKE = "like_postAddr";

        public static final String POST_ADDR__IN = "list_postAddr";

        public static final String POST_ADDR__BEGIN = "begin_postAddr";

        public static final String POST_ADDR__END = "end_postAddr";

        public static final String EVENT_DESC__NE = "ne_eventDesc";

        public static final String EVENT_DESC__LIKE = "like_eventDesc";

        public static final String EVENT_DESC__IN = "list_eventDesc";

        public static final String EVENT_DESC__BEGIN = "begin_eventDesc";

        public static final String EVENT_DESC__END = "end_eventDesc";

        public static final String POST_LEVEL__NE = "ne_postLevel";

        public static final String POST_LEVEL__LIKE = "like_postLevel";

        public static final String POST_LEVEL__IN = "list_postLevel";

        public static final String POST_LEVEL__BEGIN = "begin_postLevel";

        public static final String POST_LEVEL__END = "end_postLevel";

        public static final String HAPPEN_TIME__NE = "ne_happenTime";

        public static final String HAPPEN_TIME__IN = "list_happenTime";

        public static final String HAPPEN_TIME__BEGIN = "begin_happenTime";

        public static final String HAPPEN_TIME__END = "end_happenTime";

        public static final String POSTER_NAME__NE = "ne_posterName";

        public static final String POSTER_NAME__LIKE = "like_posterName";

        public static final String POSTER_NAME__IN = "list_posterName";

        public static final String POSTER_NAME__BEGIN = "begin_posterName";

        public static final String POSTER_NAME__END = "end_posterName";

        public static final String POSTER_MOBILE__NE = "ne_posterMobile";

        public static final String POSTER_MOBILE__LIKE = "like_posterMobile";

        public static final String POSTER_MOBILE__IN = "list_posterMobile";

        public static final String POSTER_MOBILE__BEGIN = "begin_posterMobile";

        public static final String POSTER_MOBILE__END = "end_posterMobile";

        public static final String FILE1_ID__NE = "ne_file1Id";

        public static final String FILE1_ID__LIKE = "like_file1Id";

        public static final String FILE1_ID__IN = "list_file1Id";

        public static final String FILE1_ID__BEGIN = "begin_file1Id";

        public static final String FILE1_ID__END = "end_file1Id";

        public static final String FILE2_ID__NE = "ne_file2Id";

        public static final String FILE2_ID__LIKE = "like_file2Id";

        public static final String FILE2_ID__IN = "list_file2Id";

        public static final String FILE2_ID__BEGIN = "begin_file2Id";

        public static final String FILE2_ID__END = "end_file2Id";

        public static final String FILE3_ID__NE = "ne_file3Id";

        public static final String FILE3_ID__LIKE = "like_file3Id";

        public static final String FILE3_ID__IN = "list_file3Id";

        public static final String FILE3_ID__BEGIN = "begin_file3Id";

        public static final String FILE3_ID__END = "end_file3Id";

        public static final String FILE4_ID__NE = "ne_file4Id";

        public static final String FILE4_ID__LIKE = "like_file4Id";

        public static final String FILE4_ID__IN = "list_file4Id";

        public static final String FILE4_ID__BEGIN = "begin_file4Id";

        public static final String FILE4_ID__END = "end_file4Id";

        public static final String FILE5_ID__NE = "ne_file5Id";

        public static final String FILE5_ID__LIKE = "like_file5Id";

        public static final String FILE5_ID__IN = "list_file5Id";

        public static final String FILE5_ID__BEGIN = "begin_file5Id";

        public static final String FILE5_ID__END = "end_file5Id";

        public static final String FILE6_ID__NE = "ne_file6Id";

        public static final String FILE6_ID__LIKE = "like_file6Id";

        public static final String FILE6_ID__IN = "list_file6Id";

        public static final String FILE6_ID__BEGIN = "begin_file6Id";

        public static final String FILE6_ID__END = "end_file6Id";

        public static final String CREATER_ID__NE = "ne_createrId";

        public static final String CREATER_ID__IN = "list_createrId";

        public static final String CREATER_ID__BEGIN = "begin_createrId";

        public static final String CREATER_ID__END = "end_createrId";

        public static final String CREATER_NAME__NE = "ne_createrName";

        public static final String CREATER_NAME__LIKE = "like_createrName";

        public static final String CREATER_NAME__IN = "list_createrName";

        public static final String CREATER_NAME__BEGIN = "begin_createrName";

        public static final String CREATER_NAME__END = "end_createrName";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";
    }
}