package cn.com.hugedata.spark.commons.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.storage.base.BaseEntity;

/**
 * 表示分页信息.
 */
public class PageEntity<E> {

    /**
     * 页记录偏移索引
     */
    private int pageOffset;

    /**
     * 每页展示数据条数
     */
    private int pageSize;
    /***
     * 数据总记录数
     */
    private int recordsTotal;
    
    /**
     * 数据集合
     */
    private Collection<E> data;
    
    /**
     * 当前页数据条数
     */
    private int recordsFiltered;

    /**
     * 排序信息.
     */
    private List<Map<String, String>> order;
    
    /**
     * 附加信息.
     */
    private Object tag;

    public PageEntity() {
    }

    public PageEntity(Collection<E> data, int pageOffset, int pageSize, int recordsTotal) {
        this.data = data;
        this.pageOffset = pageOffset;
        this.pageSize = pageSize;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
    }
    
    /**
     * 将当前分页信息对象转为JSONObject.
     * @return JSONObject
     */
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("pageOffset", this.getPageOffset());
        obj.put("pageSize", this.getPageSize());
        obj.put("recordsTotal", this.getRecordsTotal());
        obj.put("recordsFiltered", this.getRecordsFiltered());
        obj.put("page", this.getPage());
        obj.put("previousPageStart", this.getPreviousPageStart());
        obj.put("hasPrevious", this.getHasPrevious());
        obj.put("nextPageStart", this.getNextPageStart());
        obj.put("hasNext", this.getHasNext());
        obj.put("pageCount", this.getPageCount());
        if (data.isEmpty()) {
            obj.put("data", new ArrayList<>());
        } else {
            List<Object> list = new ArrayList<>();
            for (E item : data) {
                if (item == null) {
                    list.add(null);
                }
                if (item instanceof BaseEntity<?>) {
                    BaseEntity<?> base = (BaseEntity<?>) item;
                    list.add(base.toJSON());
                } else {
                    list.add(JSON.toJSON(item));
                }
            }
            obj.put("data", list);
        }
        return obj;
    }
    
    /**
     * 获取下一页开始记录.
     * @return 下一页开始记录
     */
    public int getNextPageStart() {
        return pageOffset + pageSize;
    }
    
    /**
     * 获取上一页开始记录.
     * @return 上一页开始记录
     */
    public int getPreviousPageStart() {
        return pageOffset - pageSize;
    }

    /**
     * 判断是否存在上一页数据.
     * @return 是否存在上一页数据
     */
    public boolean getHasPrevious() {
        return getPage() > 1;
    }

    /**
     * 判断是否存在下一页数据.
     * @return 是否存在下一页数据
     */
    public boolean getHasNext() {
        return getPage() < getPageCount();
    }
    
    /**
     * 获得分页数量.
     * @return 总页数
     */
    public int getPageCount() {
        return (recordsTotal - 1) / pageSize + 1;
    }
    
    /**
     * 获取当前页码.
     * @return 页码
     */
    public int getPage() {
        return pageOffset  / pageSize + 1;
    }
    
    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public Collection<E> getData() {
        return data;
    }

    public void setData(Collection<E> data) {
        this.data = data;
    }

    public List<Map<String, String>> getOrder() {
        return order;
    }

    public void setOrder(List<Map<String, String>> order) {
        this.order = order;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
