package cn.com.hugedata.spark.commons.web.datatables;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * Datatables工具类.
 * @author 高鹏
 */
public final class DatatableUtils {

    /**
     * 列信息参数：字段名.
     */
    private static final String PARAM_COLUMNS_DATA = "columns[%d][data]";

    /**
     * 列信息参数：name?.
     */
    private static final String PARAM_COLUMNS_NAME = "columns[%d][name]";

    /**
     * 列信息参数：是否可搜索.
     */
    private static final String PARAM_COLUMNS_SEARCHABLE = "columns[%d][searchable]";

    /**
     * 列信息参数：是否可排序.
     */
    private static final String PARAM_COLUMNS_ORDERABLE = "columns[%d][orderable]";

    /**
     * 列信息参数：searchValue?.
     */
    private static final String PARAM_COLUMNS_SEARCH_VALUE = "columns[%d][search][value]";

    /**
     * 列信息参数：searchRegex?.
     */
    private static final String PARAM_COLUMNS_SEARCH_REGEX = "columns[%d][search][regex]";
    
    /**
     * 排序参数名称：需要排序的列.
     */
    private static final String PARAM_ORDER_COLUMN = "order[%d][column]";
    
    /**
     * 排序参数名称：排序(asc/desc).
     */
    private static final String PARAM_ORDER_DIR = "order[0][dir]";

    /**
     * 不允许创建实例.
     */
    private DatatableUtils() {
    }

    /**
     * 从Http请求加载Datatables所传的列信息.
     * @param request Http请求
     * @return 列信息 
     */
    public static List<DatatableColumnInfo> loadColumnInfoFromRequest(HttpServletRequest request) {
        List<DatatableColumnInfo> cols = new ArrayList<DatatableColumnInfo>();
        int i = 0;
        while (true) {
            // 判断是否存在参数
            String param = String.format(PARAM_COLUMNS_DATA, i);
            String data = request.getParameter(param);
            if (data == null) {
                break;
            }
            
            DatatableColumnInfo col = new DatatableColumnInfo();
            col.setData(data);
            col.setName(request.getParameter(String.format(PARAM_COLUMNS_NAME, i)));
            col.setSearchable("true".equals(request.getParameter(String.format(PARAM_COLUMNS_SEARCHABLE, i))));
            col.setOrderable("true".equals(request.getParameter(String.format(PARAM_COLUMNS_ORDERABLE, i))));
            col.setSearchValue(request.getParameter(String.format(PARAM_COLUMNS_SEARCH_VALUE, i)));
            col.setSearchRegex("true".equals(request.getParameter(String.format(PARAM_COLUMNS_SEARCH_REGEX, i))));
            cols.add(col);
            
            i++;
        }
        return cols;
    }
    
    /**
     * 从Http请求加载Datatables所传的排序信息.
     * @param request Http请求
     * @return 排序信息
     */
    public static List<DatatableOrderInfo> loadOrderInfoFromRequest(HttpServletRequest request) {
        List<DatatableOrderInfo> orders = new ArrayList<DatatableOrderInfo>();
        int i = 0;
        while (true) {
            String orderColumn = request.getParameter(String.format(PARAM_ORDER_COLUMN, i));
            String orderDir = request.getParameter(String.format(PARAM_ORDER_DIR, i));
            
            if (StringUtils.isEmpty(orderColumn)) {
                break;
            }
            
            DatatableOrderInfo order = new DatatableOrderInfo();
            order.setColumn(Integer.parseInt(orderColumn));
            order.setDir(orderDir);
            orders.add(order);
            
            i++;
        }
        return orders;
    }
}
