package cn.com.hugedata.spark.commons.storage.constant;

public interface GaPmBillConstants {

    /** 账单类型：租金. */
    String BILL_TYPE_RENT = "RENT";

    /** 账单类型：保证金. */
    String BILL_TYPE_EARNEST = "EARNEST";

    /** 账单类型：水费. */
    String BILL_TYPE_WATER = "WATER";

    /** 账单类型：电费. */
    String BILL_TYPE_POWER = "POWER";

    /** 账单类型：物业费. */
    String BILL_TYPE_PROPERTY = "PROPERTY";
    
    
    /** 状态：未缴费、未退费. */
    String STATUS_CREATED = "CREATED";

    /** 状态：已缴费、已退费. */
    String STATUS_FINISHED = "FINISHED";
}
