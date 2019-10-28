package cn.com.hugedata.spark.commons.storage.entity;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OsRequireInfo extends BaseEntity<Integer> {
    /**
     * requireId
     */
    private Integer requireId;

    /**
     * requireArea
     */
    private String requireArea;

    /**
     * requireTitle
     */
    private String requireTitle;

    /**
     * deadDate
     */
    private Date deadDate;

    /**
     * offerDate
     */
    private Date offerDate;

    /**
     * hopePrice
     */
    private Double hopePrice;

    /**
     * priceUnit
     */
    private String priceUnit;

    /**
     * isChat
     */
    private String isChat;

    /**
     * isQuick
     */
    private String isQuick;

    /**
     * keyWord
     */
    private String keyWord;

    /**
     * paymentMethod
     */
    private String paymentMethod;

    /**
     * invoiceType
     */
    private String invoiceType;

    /**
     * freightPayer
     */
    private String freightPayer;

    /**
     * isCod
     */
    private String isCod;

    /**
     * requireNum
     */
    private Integer requireNum;

    /**
     * numUnit
     */
    private String numUnit;

    /**
     * contactArea
     */
    private String contactArea;

    /**
     * contactAddr
     */
    private String contactAddr;

    /**
     * contacter
     */
    private String contacter;

    /**
     * contactPhone
     */
    private String contactPhone;

    /**
     * email
     */
    private String email;

    /**
     * publishTime
     */
    private Date publishTime;

    /**
     * deleted
     */
    private String deleted;

    /**
     * userId
     */
    private Integer userId;

    /**
     * companyId
     */
    private Integer companyId;

    /**
     * companyName
     */
    private String companyName;

    /**
     * readNum
     */
    private Integer readNum;

    /**
     * requireDesc
     */
    private String requireDesc;

    private static final long serialVersionUID = -3197989382182724590L;

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
     * {@link #requireArea}
     */
    public String getRequireArea() {
        return requireArea;
    }

    /**
     * {@link #requireArea}
     */
    public void setRequireArea(String requireArea) {
        this.requireArea = requireArea == null ? null : requireArea.trim();
    }

    /**
     * {@link #requireTitle}
     */
    public String getRequireTitle() {
        return requireTitle;
    }

    /**
     * {@link #requireTitle}
     */
    public void setRequireTitle(String requireTitle) {
        this.requireTitle = requireTitle == null ? null : requireTitle.trim();
    }

    /**
     * {@link #deadDate}
     */
    public Date getDeadDate() {
        return deadDate;
    }

    /**
     * {@link #deadDate}
     */
    public void setDeadDate(Date deadDate) {
        this.deadDate = deadDate;
    }

    /**
     * {@link #offerDate}
     */
    public Date getOfferDate() {
        return offerDate;
    }

    /**
     * {@link #offerDate}
     */
    public void setOfferDate(Date offerDate) {
        this.offerDate = offerDate;
    }

    /**
     * {@link #hopePrice}
     */
    public Double getHopePrice() {
        return hopePrice;
    }

    /**
     * {@link #hopePrice}
     */
    public void setHopePrice(Double hopePrice) {
        this.hopePrice = hopePrice;
    }

    /**
     * {@link #priceUnit}
     */
    public String getPriceUnit() {
        return priceUnit;
    }

    /**
     * {@link #priceUnit}
     */
    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit == null ? null : priceUnit.trim();
    }

    /**
     * {@link #isChat}
     */
    public String getIsChat() {
        return isChat;
    }

    /**
     * {@link #isChat}
     */
    public void setIsChat(String isChat) {
        this.isChat = isChat == null ? null : isChat.trim();
    }

    /**
     * {@link #isQuick}
     */
    public String getIsQuick() {
        return isQuick;
    }

    /**
     * {@link #isQuick}
     */
    public void setIsQuick(String isQuick) {
        this.isQuick = isQuick == null ? null : isQuick.trim();
    }

    /**
     * {@link #keyWord}
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * {@link #keyWord}
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    /**
     * {@link #paymentMethod}
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * {@link #paymentMethod}
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
    }

    /**
     * {@link #invoiceType}
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * {@link #invoiceType}
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    /**
     * {@link #freightPayer}
     */
    public String getFreightPayer() {
        return freightPayer;
    }

    /**
     * {@link #freightPayer}
     */
    public void setFreightPayer(String freightPayer) {
        this.freightPayer = freightPayer == null ? null : freightPayer.trim();
    }

    /**
     * {@link #isCod}
     */
    public String getIsCod() {
        return isCod;
    }

    /**
     * {@link #isCod}
     */
    public void setIsCod(String isCod) {
        this.isCod = isCod == null ? null : isCod.trim();
    }

    /**
     * {@link #requireNum}
     */
    public Integer getRequireNum() {
        return requireNum;
    }

    /**
     * {@link #requireNum}
     */
    public void setRequireNum(Integer requireNum) {
        this.requireNum = requireNum;
    }

    /**
     * {@link #numUnit}
     */
    public String getNumUnit() {
        return numUnit;
    }

    /**
     * {@link #numUnit}
     */
    public void setNumUnit(String numUnit) {
        this.numUnit = numUnit == null ? null : numUnit.trim();
    }

    /**
     * {@link #contactArea}
     */
    public String getContactArea() {
        return contactArea;
    }

    /**
     * {@link #contactArea}
     */
    public void setContactArea(String contactArea) {
        this.contactArea = contactArea == null ? null : contactArea.trim();
    }

    /**
     * {@link #contactAddr}
     */
    public String getContactAddr() {
        return contactAddr;
    }

    /**
     * {@link #contactAddr}
     */
    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr == null ? null : contactAddr.trim();
    }

    /**
     * {@link #contacter}
     */
    public String getContacter() {
        return contacter;
    }

    /**
     * {@link #contacter}
     */
    public void setContacter(String contacter) {
        this.contacter = contacter == null ? null : contacter.trim();
    }

    /**
     * {@link #contactPhone}
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * {@link #contactPhone}
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    /**
     * {@link #email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * {@link #email}
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * {@link #publishTime}
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * {@link #publishTime}
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * {@link #deleted}
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * {@link #deleted}
     */
    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    /**
     * {@link #userId}
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * {@link #userId}
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * {@link #companyId}
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * {@link #companyId}
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
     * {@link #readNum}
     */
    public Integer getReadNum() {
        return readNum;
    }

    /**
     * {@link #readNum}
     */
    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    /**
     * {@link #requireDesc}
     */
    public String getRequireDesc() {
        return requireDesc;
    }

    /**
     * {@link #requireDesc}
     */
    public void setRequireDesc(String requireDesc) {
        this.requireDesc = requireDesc == null ? null : requireDesc.trim();
    }

    @Override
    public String getIdPropertyName() {
        return "requireId";
    }

    @Override
    public Integer getIdValue() {
        return requireId;
    }

    @Override
    public void setIdValue(Integer id) {
        this.requireId = id;
    }

    public static JSONObject toJSON(OsRequireInfo e) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (e == null) {
            return null;
        }
        JSONObject obj = (JSONObject) JSON.toJSON(e);
        if (e.getDeadDate() != null) {
            obj.put("deadDateStr", fmt.format(e.getDeadDate()));
        }
        if (e.getOfferDate() != null) {
            obj.put("offerDateStr", fmt.format(e.getOfferDate()));
        }
        if (e.getPublishTime() != null) {
            obj.put("publishTimeStr", fmt.format(e.getPublishTime()));
        }
        return obj;
    }

    public static List<JSONObject> toJSON(List<OsRequireInfo> list) {
        List<JSONObject> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            retList.add(toJSON(list.get(i)));
        }
        return retList;
    }

    @Override
    public JSONObject toJSON() {
        return OsRequireInfo.toJSON(this);
    }

    public static class Fields {
        public static final String REQUIRE_ID = "requireId";

        public static final String REQUIRE_AREA = "requireArea";

        public static final String REQUIRE_TITLE = "requireTitle";

        public static final String DEAD_DATE = "deadDate";

        public static final String OFFER_DATE = "offerDate";

        public static final String HOPE_PRICE = "hopePrice";

        public static final String PRICE_UNIT = "priceUnit";

        public static final String IS_CHAT = "isChat";

        public static final String IS_QUICK = "isQuick";

        public static final String KEY_WORD = "keyWord";

        public static final String PAYMENT_METHOD = "paymentMethod";

        public static final String INVOICE_TYPE = "invoiceType";

        public static final String FREIGHT_PAYER = "freightPayer";

        public static final String IS_COD = "isCod";

        public static final String REQUIRE_NUM = "requireNum";

        public static final String NUM_UNIT = "numUnit";

        public static final String CONTACT_AREA = "contactArea";

        public static final String CONTACT_ADDR = "contactAddr";

        public static final String CONTACTER = "contacter";

        public static final String CONTACT_PHONE = "contactPhone";

        public static final String EMAIL = "email";

        public static final String PUBLISH_TIME = "publishTime";

        public static final String DELETED = "deleted";

        public static final String USER_ID = "userId";

        public static final String COMPANY_ID = "companyId";

        public static final String COMPANY_NAME = "companyName";

        public static final String READ_NUM = "readNum";

        public static final String REQUIRE_DESC = "requireDesc";
    }

    public static class Query {
        public static final String REQUIRE_ID__NE = "ne_requireId";

        public static final String REQUIRE_ID__IN = "list_requireId";

        public static final String REQUIRE_ID__BEGIN = "begin_requireId";

        public static final String REQUIRE_ID__END = "end_requireId";

        public static final String REQUIRE_AREA__NE = "ne_requireArea";

        public static final String REQUIRE_AREA__LIKE = "like_requireArea";

        public static final String REQUIRE_AREA__IN = "list_requireArea";

        public static final String REQUIRE_AREA__BEGIN = "begin_requireArea";

        public static final String REQUIRE_AREA__END = "end_requireArea";

        public static final String REQUIRE_TITLE__NE = "ne_requireTitle";

        public static final String REQUIRE_TITLE__LIKE = "like_requireTitle";

        public static final String REQUIRE_TITLE__IN = "list_requireTitle";

        public static final String REQUIRE_TITLE__BEGIN = "begin_requireTitle";

        public static final String REQUIRE_TITLE__END = "end_requireTitle";

        public static final String DEAD_DATE__NE = "ne_deadDate";

        public static final String DEAD_DATE__IN = "list_deadDate";

        public static final String DEAD_DATE__BEGIN = "begin_deadDate";

        public static final String DEAD_DATE__END = "end_deadDate";

        public static final String OFFER_DATE__NE = "ne_offerDate";

        public static final String OFFER_DATE__IN = "list_offerDate";

        public static final String OFFER_DATE__BEGIN = "begin_offerDate";

        public static final String OFFER_DATE__END = "end_offerDate";

        public static final String HOPE_PRICE__NE = "ne_hopePrice";

        public static final String HOPE_PRICE__IN = "list_hopePrice";

        public static final String HOPE_PRICE__BEGIN = "begin_hopePrice";

        public static final String HOPE_PRICE__END = "end_hopePrice";

        public static final String PRICE_UNIT__NE = "ne_priceUnit";

        public static final String PRICE_UNIT__LIKE = "like_priceUnit";

        public static final String PRICE_UNIT__IN = "list_priceUnit";

        public static final String PRICE_UNIT__BEGIN = "begin_priceUnit";

        public static final String PRICE_UNIT__END = "end_priceUnit";

        public static final String IS_CHAT__NE = "ne_isChat";

        public static final String IS_CHAT__LIKE = "like_isChat";

        public static final String IS_CHAT__IN = "list_isChat";

        public static final String IS_CHAT__BEGIN = "begin_isChat";

        public static final String IS_CHAT__END = "end_isChat";

        public static final String IS_QUICK__NE = "ne_isQuick";

        public static final String IS_QUICK__LIKE = "like_isQuick";

        public static final String IS_QUICK__IN = "list_isQuick";

        public static final String IS_QUICK__BEGIN = "begin_isQuick";

        public static final String IS_QUICK__END = "end_isQuick";

        public static final String KEY_WORD__NE = "ne_keyWord";

        public static final String KEY_WORD__LIKE = "like_keyWord";

        public static final String KEY_WORD__IN = "list_keyWord";

        public static final String KEY_WORD__BEGIN = "begin_keyWord";

        public static final String KEY_WORD__END = "end_keyWord";

        public static final String PAYMENT_METHOD__NE = "ne_paymentMethod";

        public static final String PAYMENT_METHOD__LIKE = "like_paymentMethod";

        public static final String PAYMENT_METHOD__IN = "list_paymentMethod";

        public static final String PAYMENT_METHOD__BEGIN = "begin_paymentMethod";

        public static final String PAYMENT_METHOD__END = "end_paymentMethod";

        public static final String INVOICE_TYPE__NE = "ne_invoiceType";

        public static final String INVOICE_TYPE__LIKE = "like_invoiceType";

        public static final String INVOICE_TYPE__IN = "list_invoiceType";

        public static final String INVOICE_TYPE__BEGIN = "begin_invoiceType";

        public static final String INVOICE_TYPE__END = "end_invoiceType";

        public static final String FREIGHT_PAYER__NE = "ne_freightPayer";

        public static final String FREIGHT_PAYER__LIKE = "like_freightPayer";

        public static final String FREIGHT_PAYER__IN = "list_freightPayer";

        public static final String FREIGHT_PAYER__BEGIN = "begin_freightPayer";

        public static final String FREIGHT_PAYER__END = "end_freightPayer";

        public static final String IS_COD__NE = "ne_isCod";

        public static final String IS_COD__LIKE = "like_isCod";

        public static final String IS_COD__IN = "list_isCod";

        public static final String IS_COD__BEGIN = "begin_isCod";

        public static final String IS_COD__END = "end_isCod";

        public static final String REQUIRE_NUM__NE = "ne_requireNum";

        public static final String REQUIRE_NUM__IN = "list_requireNum";

        public static final String REQUIRE_NUM__BEGIN = "begin_requireNum";

        public static final String REQUIRE_NUM__END = "end_requireNum";

        public static final String NUM_UNIT__NE = "ne_numUnit";

        public static final String NUM_UNIT__LIKE = "like_numUnit";

        public static final String NUM_UNIT__IN = "list_numUnit";

        public static final String NUM_UNIT__BEGIN = "begin_numUnit";

        public static final String NUM_UNIT__END = "end_numUnit";

        public static final String CONTACT_AREA__NE = "ne_contactArea";

        public static final String CONTACT_AREA__LIKE = "like_contactArea";

        public static final String CONTACT_AREA__IN = "list_contactArea";

        public static final String CONTACT_AREA__BEGIN = "begin_contactArea";

        public static final String CONTACT_AREA__END = "end_contactArea";

        public static final String CONTACT_ADDR__NE = "ne_contactAddr";

        public static final String CONTACT_ADDR__LIKE = "like_contactAddr";

        public static final String CONTACT_ADDR__IN = "list_contactAddr";

        public static final String CONTACT_ADDR__BEGIN = "begin_contactAddr";

        public static final String CONTACT_ADDR__END = "end_contactAddr";

        public static final String CONTACTER__NE = "ne_contacter";

        public static final String CONTACTER__LIKE = "like_contacter";

        public static final String CONTACTER__IN = "list_contacter";

        public static final String CONTACTER__BEGIN = "begin_contacter";

        public static final String CONTACTER__END = "end_contacter";

        public static final String CONTACT_PHONE__NE = "ne_contactPhone";

        public static final String CONTACT_PHONE__LIKE = "like_contactPhone";

        public static final String CONTACT_PHONE__IN = "list_contactPhone";

        public static final String CONTACT_PHONE__BEGIN = "begin_contactPhone";

        public static final String CONTACT_PHONE__END = "end_contactPhone";

        public static final String EMAIL__NE = "ne_email";

        public static final String EMAIL__LIKE = "like_email";

        public static final String EMAIL__IN = "list_email";

        public static final String EMAIL__BEGIN = "begin_email";

        public static final String EMAIL__END = "end_email";

        public static final String PUBLISH_TIME__NE = "ne_publishTime";

        public static final String PUBLISH_TIME__IN = "list_publishTime";

        public static final String PUBLISH_TIME__BEGIN = "begin_publishTime";

        public static final String PUBLISH_TIME__END = "end_publishTime";

        public static final String DELETED__NE = "ne_deleted";

        public static final String DELETED__LIKE = "like_deleted";

        public static final String DELETED__IN = "list_deleted";

        public static final String DELETED__BEGIN = "begin_deleted";

        public static final String DELETED__END = "end_deleted";

        public static final String USER_ID__NE = "ne_userId";

        public static final String USER_ID__IN = "list_userId";

        public static final String USER_ID__BEGIN = "begin_userId";

        public static final String USER_ID__END = "end_userId";

        public static final String COMPANY_ID__NE = "ne_companyId";

        public static final String COMPANY_ID__IN = "list_companyId";

        public static final String COMPANY_ID__BEGIN = "begin_companyId";

        public static final String COMPANY_ID__END = "end_companyId";

        public static final String COMPANY_NAME__NE = "ne_companyName";

        public static final String COMPANY_NAME__LIKE = "like_companyName";

        public static final String COMPANY_NAME__IN = "list_companyName";

        public static final String COMPANY_NAME__BEGIN = "begin_companyName";

        public static final String COMPANY_NAME__END = "end_companyName";

        public static final String READ_NUM__NE = "ne_readNum";

        public static final String READ_NUM__IN = "list_readNum";

        public static final String READ_NUM__BEGIN = "begin_readNum";

        public static final String READ_NUM__END = "end_readNum";

        public static final String REQUIRE_DESC__NE = "ne_requireDesc";

        public static final String REQUIRE_DESC__LIKE = "like_requireDesc";

        public static final String REQUIRE_DESC__IN = "list_requireDesc";

        public static final String REQUIRE_DESC__BEGIN = "begin_requireDesc";

        public static final String REQUIRE_DESC__END = "end_requireDesc";
    }
}