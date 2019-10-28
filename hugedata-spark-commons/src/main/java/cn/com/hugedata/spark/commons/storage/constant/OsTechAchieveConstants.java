package cn.com.hugedata.spark.commons.storage.constant;

// 技术成果相关
public interface OsTechAchieveConstants {

    /* 成果形式 */
    String ACHIEVE_TYPE_NEW_PRODUCT = "NEW_PRODUCT"; // 新产品
    String ACHIEVE_TYPE_NEW_TECH = "NEW_TECH"; // 新技术
    String ACHIEVE_TYPE_NEW_CRAFT = "NEW_CRAFT"; // 新工艺
    String ACHIEVE_TYPE_NEW_MATERIAL = "NEW_MATERIAL"; // 新材料
    String ACHIEVE_TYPE_INVENTION_PATENT = "INVENTION_PATENT"; // 发明专利
    String ACHIEVE_TYPE_UTILITY_MODEL = "UTILITY_MODEL"; // 实用新型
    String ACHIEVE_TYPE_APPEARANCE_DESIGN = "APPEARANCE_DESIGN"; // 外观设计
    String ACHIEVE_TYPE_TECH_SECRET = "TECH_SECRET"; // 技术秘密

    /* 货币单位 */
    public static final String MONETARY_UNIT_YUAN = "YUAN"; // 元
    public static final String MONETARY_UNIT_TEN_THOUSND_YUAN = "TEN_THOUSND_YUAN"; // 万元
    public static final String MONETARY_UNIT_MILLION_YUAN = "MILLION_YUAN"; // 百万元

    /* 投资金额区间段 */
    public static final String INVESTMENT_VOLUME_SCOPE_1 = "lt_10"; //10万元以下
    public static final String INVESTMENT_VOLUME_SCOPE_2 = "10_50"; // 10~50万元
    public static final String INVESTMENT_VOLUME_SCOPE_3 = "50_100"; // 50~100万元
    public static final String INVESTMENT_VOLUME_SCOPE_4 = "gt_100"; // 100万元以上

    /* 投资成熟度 */
    String MATURITY_STAGE_MATURE_1 = "MATURE_1"; // 一般
    String MATURITY_STAGE_MATURE_2 = "MATURE_2"; // 成熟
    String MATURITY_STAGE_MATURE_3 = "MATURE_3"; // 很成熟
    String MATURITY_STAGE_MATURE_4 = "MATURE_4"; // 非常成熟
}
