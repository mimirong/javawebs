package cn.com.hugedata.spark.commons.cache;

public class CacheKeys {

    /** 在Redis中保存首页Banner数据的Key. */
    public static final String KEY_HOME_BANNER = "HomeBanner";
    
    /** 在Redis中保存首页Banner数据的有效期. (单位为秒) */
    public static final int EXPIRE_HOME_BANNER = 60 * 30;

    
    /** 在Redis中保存园区风采图片数据的Key. */
    public static final String KEY_PARK_STYLE_IMAGE = "ParkStyleImage";

    /** 在Redis中保存园区风采图片数据的有效期. (单位为秒) */
    public static final int EXPIRE_PARK_STYLE_IMAGE = 60 * 30;
    
    /** 在Redis中保存文章列表信息的key. */
    public static final String KEY_ITMESSAGE_LIST = "ItMessageList";
    
    /** 在Redis中保存文章列表信息的有效期(单位为秒). */
    public static final long EXPIRE_ITMESSAGE_LIST = 60 * 30;
    
    
    /** 在Redis中保存文章列表信息的key. */
    public static final String KEY_ARTICLE_LIST = "ArticleList";
    
    /** 在Redis中保存文章列表信息的有效期(单位为秒). */
    public static final long EXPIRE_ARTICLE_LIST = 60 * 30;
    
    
    /** 在Redis中保存文章信息的key. */
    public static final String KEY_ARTICLE = "Article";
  
    /** 在Redis中保存文章信息的有效期(单位为秒). */
    public static final long EXPIRES_ARTICLE = 60 * 30;
    
    
    /** 在Redis中保存明星企业列表的key. */
    public static final String KEY_STAR_COMPANY = "StarCompany";
  
    /** 在Redis中保存明星企业列表的有效期(单位为秒). */
    public static final long EXPIRES_STAR_COMPANY = 60 * 30;

    /** 在Redis中保存办事指南列表信息的key. */
    public static final String KEY_SERVICE_GUIDE_LIST = "guideList";

    /** 在Redis中保存办事指南信息的key. */
    public static final String KEY_SERVICE_GUIDE = "guide";
    
}
