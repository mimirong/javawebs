package cn.com.hugedata.spark.commons.service.preview;

public enum ImagePreviewTypes {

    /** 文章图片. */
    ARTICLE("ARTICLE", 400, 300, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 通知公告 - 图片新闻. */
    NOTICE_NEWS("NOTICE_NEWS", 520, 340, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 公共技术服务 - 人力资源服务. */
    TECH_HUMAN_RESOURCE("TECH_HUMAN_RESOURCE", 100, 72, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 公共技术服务 - 企业信息服务. */
    TECH_COMPANIES_INFORMATION("TECH_COMPANIES_INFORMATION", 100, 72, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 公共技术服务 - 项目招商服务. */
    TECH_PROJECT_OSINVEST("TECH_PROJECT_OSINVEST", 100, 72, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 公共技术服务 - 知识产权服务. */
    TECH_INTELLECTUAL("TECH_INTELLECTUAL", 208, 72, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 公共技术服务 - 诚信体系服务. */
    TECH_CREDIT_SYSTEM("TECH_CREDIT_SYSTEM", 428, 72, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 首页大图 . */
    HOME_IMAGE("HOME_IMAGE", 480, 270, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 通知公告Banner . */
    NOTICE_BANNER("NOTICE_BANNER", 960, 120, "PNG", ResizeMode.KEEP_ORIGINAL_RATIO),

    /** 服务外包->成果转化->会议培训. */
    OS_MEETING_TRAINING("OS_MEETING_TRAINING", 520, 340, "PNG", ResizeMode.FIT_TARGET_SIZE),

    /** 服务外包 - 服务项目. */
    OS_SERVICE_PROJECT_ORIGINAL("OS_SERVICE_PROJECT_ORIGINAL", 380, 380, "PNG", ResizeMode.FIT_TARGET_SIZE),
    OS_SERVICE_PROJECT_PREVIEW("OS_SERVICE_PROJECT_PREVIEW", 70, 70, "PNG", ResizeMode.FIT_TARGET_SIZE),
    OS_SERVICE_PROJECT_COVER("OS_SERVICE_PROJECT_COVER", 120, 120, "PNG", ResizeMode.FIT_TARGET_SIZE);

    //=====================================================================================
    
    private String name;
    
    private int width;
    
    private int height;
    
    private String format;
    
    private ResizeMode resizeMode;

    private ImagePreviewTypes(String name, int width, int height, String format, ResizeMode resizeMode) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.format = format;
        this.resizeMode = resizeMode;
    }
    
    public String getExtension() {
        switch (format) {
            case "JPEG":
            case "JPG":
                return "jpg";
            case "PNG":
                return "png";
            default:
                return "";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public ResizeMode getResizeMode() {
        return resizeMode;
    }

    public void setResizeMode(ResizeMode resizeMode) {
        this.resizeMode = resizeMode;
    }
}
