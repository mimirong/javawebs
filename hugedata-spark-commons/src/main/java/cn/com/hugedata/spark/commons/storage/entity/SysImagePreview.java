package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysImagePreview extends BaseEntity<Integer> {
    /**
     * previewId
     */
    private Integer previewId;

    /**
     * originalFileId
     */
    private String originalFileId;

    /**
     * previewFileId
     */
    private String previewFileId;

    /**
     * width
     */
    private Integer width;

    /**
     * height
     */
    private Integer height;

    /**
     * previewType
     */
    private String previewType;

    /**
     * createTime
     */
    private Date createTime;

    private static final long serialVersionUID = 122643446613767443L;

    /**
     * {@link #previewId}
     */
    public Integer getPreviewId() {
        return previewId;
    }

    /**
     * {@link #previewId}
     */
    public void setPreviewId(Integer previewId) {
        this.previewId = previewId;
    }

    /**
     * {@link #originalFileId}
     */
    public String getOriginalFileId() {
        return originalFileId;
    }

    /**
     * {@link #originalFileId}
     */
    public void setOriginalFileId(String originalFileId) {
        this.originalFileId = originalFileId == null ? null : originalFileId.trim();
    }

    /**
     * {@link #previewFileId}
     */
    public String getPreviewFileId() {
        return previewFileId;
    }

    /**
     * {@link #previewFileId}
     */
    public void setPreviewFileId(String previewFileId) {
        this.previewFileId = previewFileId == null ? null : previewFileId.trim();
    }

    /**
     * {@link #width}
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * {@link #width}
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * {@link #height}
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * {@link #height}
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * {@link #previewType}
     */
    public String getPreviewType() {
        return previewType;
    }

    /**
     * {@link #previewType}
     */
    public void setPreviewType(String previewType) {
        this.previewType = previewType == null ? null : previewType.trim();
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
        return "previewId";
    }

    @Override
    public Integer getIdValue() {
        return previewId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.previewId = id;
    }

    public static JSONObject toJSON(SysImagePreview e) {
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

    public static List<JSONObject> toJSON(List<SysImagePreview> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return SysImagePreview.toJSON(this);
    }

    public static class Fields {
        public static final String PREVIEW_ID = "previewId";

        public static final String ORIGINAL_FILE_ID = "originalFileId";

        public static final String PREVIEW_FILE_ID = "previewFileId";

        public static final String WIDTH = "width";

        public static final String HEIGHT = "height";

        public static final String PREVIEW_TYPE = "previewType";

        public static final String CREATE_TIME = "createTime";
    }

    public static class Query {
        public static final String PREVIEW_ID__NE = "ne_previewId";

        public static final String PREVIEW_ID__IN = "list_previewId";

        public static final String PREVIEW_ID__BEGIN = "begin_previewId";

        public static final String PREVIEW_ID__END = "end_previewId";

        public static final String ORIGINAL_FILE_ID__NE = "ne_originalFileId";

        public static final String ORIGINAL_FILE_ID__LIKE = "like_originalFileId";

        public static final String ORIGINAL_FILE_ID__IN = "list_originalFileId";

        public static final String ORIGINAL_FILE_ID__BEGIN = "begin_originalFileId";

        public static final String ORIGINAL_FILE_ID__END = "end_originalFileId";

        public static final String PREVIEW_FILE_ID__NE = "ne_previewFileId";

        public static final String PREVIEW_FILE_ID__LIKE = "like_previewFileId";

        public static final String PREVIEW_FILE_ID__IN = "list_previewFileId";

        public static final String PREVIEW_FILE_ID__BEGIN = "begin_previewFileId";

        public static final String PREVIEW_FILE_ID__END = "end_previewFileId";

        public static final String WIDTH__NE = "ne_width";

        public static final String WIDTH__IN = "list_width";

        public static final String WIDTH__BEGIN = "begin_width";

        public static final String WIDTH__END = "end_width";

        public static final String HEIGHT__NE = "ne_height";

        public static final String HEIGHT__IN = "list_height";

        public static final String HEIGHT__BEGIN = "begin_height";

        public static final String HEIGHT__END = "end_height";

        public static final String PREVIEW_TYPE__NE = "ne_previewType";

        public static final String PREVIEW_TYPE__LIKE = "like_previewType";

        public static final String PREVIEW_TYPE__IN = "list_previewType";

        public static final String PREVIEW_TYPE__BEGIN = "begin_previewType";

        public static final String PREVIEW_TYPE__END = "end_previewType";

        public static final String CREATE_TIME__NE = "ne_createTime";

        public static final String CREATE_TIME__IN = "list_createTime";

        public static final String CREATE_TIME__BEGIN = "begin_createTime";

        public static final String CREATE_TIME__END = "end_createTime";
    }
}