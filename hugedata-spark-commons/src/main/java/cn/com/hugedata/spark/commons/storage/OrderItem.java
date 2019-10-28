package cn.com.hugedata.spark.commons.storage;

/**
 * MyBatis排序项.
 */
public class OrderItem {

    public static final String ORDER_KEY = "order";
    
    public static final String ASC = "asc";
    
    public static final String DESC = "desc";
    
    /**
     * 排序属性.
     */
    private String column;
    
    /**
     * 升序/降序.
     * "asc","desc"
     */
    private String dir;

    public OrderItem() {
        this.dir = "";
    }

    public OrderItem(String column) {
        this.column = column;
        this.dir = "";
    }

    public OrderItem(String column, String dir) {
        this.column = column;
        this.dir = dir;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    
}
