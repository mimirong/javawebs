package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PmDocType extends BaseEntity<Integer> {
    /**
     * typeId
     */
    private Integer typeId;

    /**
     * typeName
     */
    private String typeName;

    /**
     * docType
     */
    private String docType;

    private static final long serialVersionUID = 6443131511612871465L;

    /**
     * {@link #typeId}
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * {@link #typeId}
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * {@link #typeName}
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * {@link #typeName}
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * {@link #docType}
     */
    public String getDocType() {
        return docType;
    }

    /**
     * {@link #docType}
     */
    public void setDocType(String docType) {
        this.docType = docType == null ? null : docType.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "typeId";
    }

    @Override
    public Integer getIdValue() {
        return typeId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.typeId = id;
    }

    public static JSONObject toJSON(PmDocType e) {
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        return obj;
    }

    public static List<JSONObject> toJSON(List<PmDocType> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return PmDocType.toJSON(this);
    }

    public static class Fields {
        public static final String TYPE_ID = "typeId";

        public static final String TYPE_NAME = "typeName";

        public static final String DOC_TYPE = "docType";
    }

    public static class Query {
        public static final String TYPE_ID__NE = "ne_typeId";

        public static final String TYPE_ID__IN = "list_typeId";

        public static final String TYPE_ID__BEGIN = "begin_typeId";

        public static final String TYPE_ID__END = "end_typeId";

        public static final String TYPE_NAME__NE = "ne_typeName";

        public static final String TYPE_NAME__LIKE = "like_typeName";

        public static final String TYPE_NAME__IN = "list_typeName";

        public static final String TYPE_NAME__BEGIN = "begin_typeName";

        public static final String TYPE_NAME__END = "end_typeName";

        public static final String DOC_TYPE__NE = "ne_docType";

        public static final String DOC_TYPE__LIKE = "like_docType";

        public static final String DOC_TYPE__IN = "list_docType";

        public static final String DOC_TYPE__BEGIN = "begin_docType";

        public static final String DOC_TYPE__END = "end_docType";
    }
}