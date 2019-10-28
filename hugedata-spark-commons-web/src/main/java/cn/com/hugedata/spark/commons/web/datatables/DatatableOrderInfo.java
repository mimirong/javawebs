package cn.com.hugedata.spark.commons.web.datatables;

/**
 * 保存一个Datatables查询时所传的排序信息.
 * @author 高鹏
 */
public class DatatableOrderInfo {
    /**
     * 字段名.
     */
    private int column;

    /**
     * 排序(asc/desc).
     */
    private String dir;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    
}
