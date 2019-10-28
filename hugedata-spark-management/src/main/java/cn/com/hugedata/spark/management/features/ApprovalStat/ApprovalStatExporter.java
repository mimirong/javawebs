package cn.com.hugedata.spark.management.features.ApprovalStat;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import cn.com.hugedata.spark.commons.exception.ServiceException;

/**
 * 用于导出Excel文档的工具类.
 */
public class ApprovalStatExporter implements Closeable {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalStatExporter.class);

    /** 准备导出的Workbook. */
    private XSSFWorkbook workbook = new XSSFWorkbook();

    /** 准备导出的Sheet. */
    private XSSFSheet sheet = workbook.createSheet();
    
    /** 标题单元格样式. */
    private XSSFCellStyle headerCellStyle;
    
    /** 单元格样式：日期类型. */
    private XSSFCellStyle dateCellStyle;

    /** 单元格样式：文本类型. */
    private XSSFCellStyle stringCellStyle;
    
    /** 标题样式. */
    private XSSFCellStyle titleStyle;
    
    /** 副标题样式. */
    private XSSFCellStyle subtitleStyle;
    
    /** 每一列的宽度(单位为px需要转换). */
    private List<Integer> columnWidths;
    
    /** 列数. */
    private int columnCount = 0;
    
    /** 最后一行的行号. */
    private int lastRow = 0;
    
    private Map<String, XSSFCellStyle> derivedStyleMap = new TreeMap<>();
    
    /**
     * 初始化.
     */
    public ApprovalStatExporter(int columnCount) {
        this.columnCount = columnCount;

        // 默认字体
        XSSFFont defaultFont = workbook.createFont();
        defaultFont.setColor(HSSFColor.BLACK.index);
        defaultFont.setFontHeightInPoints((short) 10);

        // Date Cell
        XSSFDataFormat fmt = workbook.createDataFormat();
        fmt.putFormat((short) 200, "yyyy-MM-dd");
        
        dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat((short) 200);
        dateCellStyle.setFont(defaultFont);
        dateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        
        // String Cell
        stringCellStyle = workbook.createCellStyle();
        stringCellStyle.setWrapText(true);
        stringCellStyle.setFont(defaultFont);
        stringCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        
        // Header Cell
        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setFont(defaultFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Title
        XSSFFont titleFont = workbook.createFont();
        titleFont.setColor(HSSFColor.BLACK.index);
        titleFont.setFontHeightInPoints((short) 14);
        titleFont.setBold(true);
        
        titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setFont(titleFont);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Subtitle
        XSSFFont subtitleFont = workbook.createFont();
        subtitleFont.setColor(HSSFColor.BLACK.index);
        subtitleFont.setFontHeightInPoints((short) 10);
        
        subtitleStyle = workbook.createCellStyle();
        subtitleStyle.setAlignment(HorizontalAlignment.CENTER);
        subtitleStyle.setFont(subtitleFont);
        subtitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    @Override
    public void close() throws IOException {
        workbook.close();
    }
    
    /**
     * 设置每一列宽度，单位为px.
     * Excel中单位不为px，ExportHelper将对单位进行转换，但并不能保证完全一致
     * @param widths 每一列的宽度
     */
    public void columnWidths(List<Integer> widths) {
        this.columnWidths = widths;
    }

    /**
     * 增加标题行.
     * @param title
     */
    public void addTitle(String title, String subtitle) {
        // Title
        if (StringUtils.isNotEmpty(title)) {
            // 创建行
            XSSFRow row = sheet.createRow(lastRow++);
            
            // 合并单元格
            CellRangeAddress tr = new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, columnCount - 1);
            sheet.addMergedRegion(tr);
            
            // 创建单元格
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(title);
            cell.setCellStyle(titleStyle);
        }
        
        // Subtitle
        if (StringUtils.isNotEmpty(subtitle)) {
            // 创建行
            XSSFRow row = sheet.createRow(lastRow++);
            
            // 合并单元格
            CellRangeAddress tr = new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, columnCount - 1);
            sheet.addMergedRegion(tr);
            
            // 创建单元格
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(subtitle);
            cell.setCellStyle(subtitleStyle);
        }
    }
    
    /**
     * 导出表格头.
     * @param headers 表格头
     */
    public void addHeaders(List<String> headers) {
        XSSFRow row = sheet.createRow(lastRow++);
        for (int j = 0; j < headers.size(); j++) {
            XSSFCell cell = row.createCell(j);
            cell.setCellValue(headers.get(j));
            cell.setCellStyle(headerCellStyle);
        }
    }
 
    /**
     * 导出一行数据.
     * @param row  Excel行
     * @param data 导出的数据，支持数值类型、String、Date、Boolean
     * @param aligns 对齐方式
     */
    private void addRow(XSSFRow row, List<Object> data, List<String> aligns) {
        for (int j = 0; j < data.size(); j++) {
            XSSFCell cell = row.createCell(j);
            Object value = data.get(j);

            cell.setCellStyle(stringCellStyle);
            
            if (value == null) {
                // do nothing
            } else if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Long) {
                cell.setCellValue((Long) value);
            } else if (value instanceof Float) {
                cell.setCellValue((Float) value);
            } else if (value instanceof Double) {
                cell.setCellValue((Double) value);
            } else if (value instanceof Date) {
                cell.setCellValue((Date) value);
                cell.setCellStyle(dateCellStyle);
            } else if (value instanceof Boolean) {
                cell.setCellValue(((Boolean) value) ? "是" : "否");
            } else {
                cell.setCellValue(value.toString());
            }
            
            // 对齐
            String align = "";
            if (aligns.size() > j) {
                align = aligns.get(j);
            }
            
            switch (align) {
                case "left":
                    break;
                case "center":
                    cell.setCellStyle(deriveStyle(cell.getCellStyle(), HorizontalAlignment.CENTER));
                    break;
                case "right":
                    cell.setCellStyle(deriveStyle(cell.getCellStyle(), HorizontalAlignment.RIGHT));
                    break;
            }
        }
    }
    
    /**
     * 导出一行数据.
     */
    public void addRow(List<Object> data, List<String> aligns) {
        XSSFRow row = sheet.createRow(lastRow++);
        addRow(row, data, aligns);
    }

    /**
     * 生成Excel文件内容到输出流.
     * @param out               输出流
     * @throws ServiceException 处理失败
     */
    public void export(OutputStream out) throws ServiceException {
        try {
            for (int i = 0; i < columnCount; i++) {
                if (columnWidths != null && columnWidths.size() > i) {
                    sheet.setColumnWidth(i, columnWidths.get(i) * 25);
                } else {
                    sheet.autoSizeColumn(i);
                }
            }
            
            workbook.write(out);
        } catch (IOException e) {
            LOGGER.error("Failed to export excel file", e);
            throw new ServiceException("FailedGeneratExcelFile", "生成Excel文件失败");
        }
    }
    
    /**
     * 生成Excel文件为ResponseEntity.
     * @param fileNameEn        英文文件名(不可包含中文)
     * @param fileName          中文文件名
     * @return                  输出的ResponseEntity
     * @throws ServiceException 生成失败
     */
    public ResponseEntity<byte[]> export(String fileNameEn, String fileName) throws ServiceException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            export(buffer);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentLength(buffer.size());
            headers.add("Content-Disposition", "attachment; "
                    + "filename=" + fileNameEn + "; "
                    + "filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            
            return new ResponseEntity<byte[]>(buffer.toByteArray(), headers, HttpStatus.OK);
            
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Failed to export excel file", e);
            throw new ServiceException("FailedGeneratExcelFile", "生成Excel文件失败");
            
        } catch (IOException e) {
            LOGGER.error("Failed to export excel file", e);
            throw new ServiceException("FailedGeneratExcelFile", "生成Excel文件失败");
        }
    }

    private XSSFCellStyle deriveStyle(XSSFCellStyle from, HorizontalAlignment horizontalAlignment) {
        String key = from.getIndex() + "__" + horizontalAlignment;
        XSSFCellStyle style = derivedStyleMap.get(key);
        if (style == null) {
            style = workbook.createCellStyle();
            style.cloneStyleFrom(from);
            style.setAlignment(horizontalAlignment);
        }
        return style;
    }
}
