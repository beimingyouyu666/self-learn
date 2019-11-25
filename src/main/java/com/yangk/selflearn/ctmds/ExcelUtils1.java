package com.yangk.selflearn.ctmds;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description poi导出excel工具类
 * @Author yangkun
 * @Date 2019/10/8
 * @Version 1.0
 * @blame yangkun
 */
@Slf4j
public class ExcelUtils1 {

    public static final int ROW_ACCESS_WINDOW_SIZE = 100;
    public static final int SHEET_MAX_ROW = 100000;

    private List list;
    private List<ExcelHeaderInfo1> excelHeaderInfo1s;
    private Map<String, ExcelFormat1> formatInfo;
    private Class clazz;

    public ExcelUtils1(List list, List<ExcelHeaderInfo1> excelHeaderInfo1s) {
        this.list = list;
        this.excelHeaderInfo1s = excelHeaderInfo1s;
    }

    public ExcelUtils1(List list, List<ExcelHeaderInfo1> excelHeaderInfo1s, Map<String, ExcelFormat1> formatInfo, Class clazz) {
        this.list = list;
        this.excelHeaderInfo1s = excelHeaderInfo1s;
        this.formatInfo = formatInfo;
        this.clazz = clazz;
    }

    public HSSFWorkbook getWorkbook() {
//        Workbook workbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);
        HSSFWorkbook workbook = new HSSFWorkbook();
        String[][] datas = transformData();
        Field[] fields = clazz.getDeclaredFields();
        HSSFCellStyle headStyle = setHeadCellStyle(workbook);
        HSSFCellStyle contentStyle = setContentCellStyle(workbook);
        int pageRowNum = 0;
        HSSFSheet sheet = null;

        long startTime = System.currentTimeMillis();
        log.info("开始处理excel文件。。。");

        for (int i = 0; i < datas.length; i++) {
            // 如果是每个sheet的首行
            if (i % SHEET_MAX_ROW == 0) {
                // 创建新的sheet
                sheet = createSheet(workbook, i);
                pageRowNum = 0; // 行号重置为0
                for (int j = 0; j < getHeaderRowNum(excelHeaderInfo1s); j++) {
                    sheet.createRow(pageRowNum++);
                }
                createHeader(sheet, headStyle);
            }
            // 创建内容
            HSSFRow row = sheet.createRow(pageRowNum++);
            createContent(row, contentStyle, datas, i, fields);
        }
        log.info("处理文本耗时" + (System.currentTimeMillis() - startTime) + "ms");
        return workbook;
    }


    // 创建表头
    private void createHeader(HSSFSheet sheet, HSSFCellStyle style) {
        for (ExcelHeaderInfo1 excelHeaderInfo1 : excelHeaderInfo1s) {
            Integer lastRow = excelHeaderInfo1.getLastRow();
            Integer firstRow = excelHeaderInfo1.getFirstRow();
            Integer lastCol = excelHeaderInfo1.getLastCol();
            Integer firstCol = excelHeaderInfo1.getFirstCol();

            // 行距或者列距大于0才进行单元格融合
            if ((lastRow - firstRow) != 0 || (lastCol - firstCol) != 0) {
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
            }
            // 获取当前表头的首行位置
            HSSFRow row = sheet.getRow(firstRow);
            // 在表头的首行与首列位置创建一个新的单元格
            HSSFCell cell = row.createCell(firstCol);
            // 赋值单元格
            cell.setCellValue(excelHeaderInfo1.getTitle());
            cell.setCellStyle(style);

            // 设置列宽
            sheet.setColumnWidth(firstCol, sheet.getColumnWidth(firstCol) * 32 / 12);
//            sheet.setColumnWidth(firstCol, 20);
        }
    }

    // 创建正文
    private void createContent(HSSFRow row, HSSFCellStyle style, String[][] content, int i, Field[] fields) {
        List<String> columnNames = getBeanProperty(fields);
        for (int j = 0; j < columnNames.size(); j++) {
            // 如果格式化Map为空，默认为字符串格式
            if (formatInfo == null) {
                row.createCell(j).setCellValue(content[i][j]);
                continue;
            }
            if (formatInfo.containsKey(columnNames.get(j))) {
                switch (formatInfo.get(columnNames.get(j)).getValue()) {
                    case "STRING":
                        HSSFCell cellString = row.createCell(j);
                        cellString.setCellStyle(style);
                        cellString.setCellValue(content[i][j]);
                        break;
                    case "DOUBLE":
                        HSSFCell cellDouble = row.createCell(j);
                        cellDouble.setCellStyle(style);
                        cellDouble.setCellValue(Double.parseDouble(content[i][j]));
                        break;
                    case "INTEGER":
                        HSSFCell cellInteger = row.createCell(j);
                        cellInteger.setCellValue(Integer.parseInt(content[i][j]));
                        break;
                    case "PERCENT":
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
                        HSSFCell cellPercent = row.createCell(j);
                        cellPercent.setCellStyle(style);
                        cellPercent.setCellValue(Double.parseDouble(content[i][j]));
                        break;
                    case "DATE":
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm:ss"));
                        HSSFCell cellDate = row.createCell(j);
                        cellDate.setCellStyle(style);
//                        row.createCell(j).setCellValue(this.stringDate2String(content[i][j]));
                        cellDate.setCellValue(this.stringDate2String(content[i][j]));
                        break;
                    default:
                        break;
                }
            } else {
                row.createCell(j).setCellValue(content[i][j]);
            }
        }
    }

    // 将原始数据转成二维数组
    private String[][] transformData() {
        int dataSize = this.list.size();
        String[][] datas = new String[dataSize][];
        if (CollectionUtils.isEmpty(this.list)){
            return datas;
        }
        // 获取报表的列数
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        // 获取实体类的字段名称数组
        List<String> columnNames = this.getBeanProperty(fields);
        for (int i = 0; i < dataSize; i++) {
            datas[i] = new String[fields.length];
            for (int j = 0; j < fields.length; j++) {
                try {
                    // 赋值
                    datas[i][j] = BeanUtils.getProperty(list.get(i), columnNames.get(j));
                } catch (Exception e) {
                    log.error("获取对象属性值失败");
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }

    // 获取实体类的字段名称数组
    private List<String> getBeanProperty(Field[] fields) {
        List<String> columnNames = new ArrayList<>();
        for (Field field : fields) {
            String[] strings = field.toString().split("\\.");
            String columnName = strings[strings.length - 1];
            columnNames.add(columnName);
        }
        return columnNames;
    }

    // 新建表格
    private static HSSFSheet createSheet(HSSFWorkbook workbook, int i) {
        Integer sheetNum = i / SHEET_MAX_ROW;
        workbook.createSheet("sheet" + sheetNum);
        //动态指定当前的工作表
        return workbook.getSheetAt(sheetNum);
    }

    // 获取标题总行数
    private static Integer getHeaderRowNum(List<ExcelHeaderInfo1> headerInfos) {
        Integer maxRowNum = 0;
        for (ExcelHeaderInfo1 excelHeaderInfo1 : headerInfos) {
            Integer tmpRowNum = excelHeaderInfo1.getLastRow();
            if (tmpRowNum > maxRowNum){
                maxRowNum = tmpRowNum;
            }
        }
        return maxRowNum + 1;
    }

    // 设置表头表格样式
    private static HSSFCellStyle setHeadCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置单元格基本样式
        setBase(style);
        // 设置单元格边框线
        setBorder(style);
        // 设置字体
        setFont(workbook, style,(short)14);
        return style;
    }

    // 设置总体表格样式
    private static HSSFCellStyle setContentCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置单元格基本样式
        setBase(style);
        // 设置单元格边框线
        setBorder(style);
        // 设置字体
        setFont(workbook, style,(short)10);

        return style;
    }

    private static void setBase(HSSFCellStyle style) {
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(false);
    }

    private static void setBorder(HSSFCellStyle style) {
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
    }

    private static void setFont(HSSFWorkbook workbook, HSSFCellStyle style, Short fontPoint) {
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints(fontPoint);//设置字体大小
        style.setFont(font);
    }

    /**
     * @Description 标准时间字符串转时间类型
     * @Author yangkun
     * @Date 2019/10/10
     * @Param [strDate]
     * @Return
     **/
    private Date string2Date(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (Exception e) {
            log.error("字符串转日期错误");
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Description 将时间类型的字符串转换成字符串（因为时间类型放到数组里面后变成了这种字符串“Thu May 16 19:23:27 CST 2019”）
     * @Author yangkun
     * @Date 2019/10/10
     * @Param [strDate]
     * @Return
     **/
    private String stringDate2String(String strDate) {
        if (strDate == null){
            return "";
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(strDate);
        } catch (ParseException e) {
            log.info("stringDate2String error");
            return "";
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");

    }

    // 发送响应结果
    public void sendHttpResponse(HttpServletResponse response, String fileName, HSSFWorkbook workbook) {
        try {
            fileName += System.currentTimeMillis() + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
