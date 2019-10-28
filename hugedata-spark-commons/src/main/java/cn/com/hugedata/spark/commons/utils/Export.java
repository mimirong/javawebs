package cn.com.hugedata.spark.commons.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Export {
	private static final String SIGN = "@";
	
	/**
	 * 导出公共方法
	 * @param resultList 结果列表
	 * @param sheetName 表单名
	 * @param arrayName 行名称数组
	 * @param arrayValue map行参数名数组
	 * @return
	 */
	public HSSFWorkbook export(List<Map<String, Object>> resultList,String sheetName,String [] arrayName,String[] arrayValue) {
		// 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 设置单元格文本格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat(SIGN));
        
        // 生成一个sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 创建新行(row),并将单元格(cell)放入其中. 行号从0开始计算.
        // 定义第一行
        HSSFRow row = sheet.createRow(0);
        // 定义第一列
        // 由该字段判断应该创建几列
        int keylen = arrayName.length;
        for (int i = 0; i < keylen; i++) {
            // 定义第i列
            HSSFCell cell = row.createCell(i);
            // 给第i列赋值
            cell.setCellValue(arrayName[i]);
            // 设置为文本格式
            cell.setCellStyle(cellStyle);
        }
        if (CollectionUtils.isNotEmpty(resultList)) {
            // 放入值
            int len = resultList.size();
            Map<String, Object> myLibraryInfoMap;
            for (int i = 0; i < len; i++) {
                myLibraryInfoMap = resultList.get(i);
                // 创建新的一行
                HSSFRow newRow = sheet.createRow(i + 1);
                for (int j = 0; j < keylen; j++) {
                    // 定义第i列
                    HSSFCell newCell = newRow.createCell(j);
                    // 给第i列赋值
                    newCell.setCellValue(MapUtils.getString(myLibraryInfoMap,arrayValue[j], ""));
                    // 设置为文本格式
                    newCell.setCellStyle(cellStyle);
                }
            }
        }
        return workbook;
	}
	
}

