package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OsRequireAtt extends BaseEntity<Integer> {
    /**
     * attId
     */
    private Integer attId;

    /**
     * requireId
     */
    private Integer requireId;

    /**
     * fileId
     */
    private String fileId;

    /**
     * fileName
     */
    private String fileName;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = 1012959980480663070L;

    /**
     * {@link #attId}
     */
    public Integer getAttId() {
        return attId;
    }

    /**
     * {@link #attId}
     */
    public void setAttId(Integer attId) {
        this.attId = attId;
    }

    /**
     * {@link #requireId}
     */
    public Integer getRequireId() {
        return requireId;
    }

    /**
     * {@link #requireId}
     */
    public void setRequireId(Integer requireId) {
        this.requireId = requireId;
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
        return "attId";
    }

    @Override
    public Integer getIdValue() {
        return attId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.attId = id;
    }

    public static JSONObject toJSON(OsRequireAtt e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getCreateTime() != null) {
            obj.put("createTimeStr", fmt.format(e.getCreateTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<OsRequireAtt> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return OsRequireAtt.toJSON(this);
    }

    public static class Fields {
        public static final String ATT_ID = "attId";

        public static final String REQUIRE_ID = "requireId";

        public static final String FILE_ID = "fileId";

        public static final String FILE_NAME = "fileName";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String ATT_ID__NE = "ne_attId";

        public static final String ATT_ID__IN = "list_attId";

        public static final String ATT_ID__BEGIN = "begin_attId";

        public static final String ATT_ID__END = "end_attId";

        public static final String REQUIRE_ID__NE = "ne_requireId";

        public static final String REQUIRE_ID__IN = "list_requireId";

        public static final String REQUIRE_ID__BEGIN = "begin_requireId";

        public static final String REQUIRE_ID__END = "end_requireId";

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

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";
    }
}