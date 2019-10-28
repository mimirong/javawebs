package cn.com.hugedata.spark.commons.web.datatables;

/**
 * 保存一个Datatable查询时所传的列信息.
 * @author 高鹏
 */
public class DatatableColumnInfo {
    /**
     * 显示的字段.
     */
    private String data;
    
    /**
     * name?.
     */
    private String name;
    
    /**
     * 是否可以搜索.
     */
    private boolean searchable;
    
    /**
     * 是否可以排序.
     */
    private boolean orderable;

    /**
     * searchValue?.
     */
    private String searchValue;
    
    /**
     * searchRegex?.
     */
    private boolean searchRegex;
    
    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public boolean isSearchRegex() {
        return searchRegex;
    }

    public void setSearchRegex(boolean searchRegex) {
        this.searchRegex = searchRegex;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }
}
