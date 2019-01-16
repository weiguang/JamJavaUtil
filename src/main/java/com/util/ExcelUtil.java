package com.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 注意：HSSF是指2007年以前的,XSSF是指2007年版本以上的
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2018/09/12 10:19
 **/
public class ExcelUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    // excel默认宽度；
    private static int width = 256 * 14;
    // 默认字体
    private static String excelfont = "微软雅黑";

    /**
     * 生成一个excel文件,xlsx格式
     * @param sheetName 导出的SHEET名字 当前sheet数目只为1
     * @param headers 导出的表格的表头
     * @param ds_titles 导出的数据列名称
     * @param data  数据集，支持Collection<map>或者Collection<T>
     * @return wb  excel工作簿，生成的文件通过这个参数返回
     */
    public static SXSSFWorkbook  createExcel( String sheetName,
                              String[] headers, String[] ds_titles,  Collection data) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        createExcel(wb,sheetName,headers,ds_titles,data);
        return wb;
    }

    /**
     * 生成一个excel文件
     * @param wb  excel工作簿，生成的文件通过这个参数返回
     * @param sheetName 导出的SHEET名字 当前sheet数目只为1
     * @param headers 导出的表格的表头
     * @param ds_titles 导出的数据列名称
     * @param data  数据集，支持Collection<map>或者Collection<T>
     */
    public static void createExcel(Workbook wb, String sheetName,
                              String[] headers, String[] ds_titles, Collection data) {
        Sheet sheet = wb.createSheet(sheetName != null ? sheetName : "excel");// 创建一个sheet
        int CurrentRowNumber = 0;
        if (headers != null) { // 创建表头，如果没有跳过
            generateExcelTitle(headers,sheet);
            CurrentRowNumber++;
        }
        // 表格主体 解析list
        if (data != null) {
            List styleList = new ArrayList();
            for (int i = 0; i < ds_titles.length; i++) { // 列数
                CellStyle style = wb.createCellStyle();
                Font font = wb.createFont();
                font.setFontName(excelfont);
                font.setFontHeightInPoints((short) 10);
                style.setFont(font);
                styleList.add(style);
            }
            try {
                generateExcelData(data,sheet,ds_titles,styleList,CurrentRowNumber);
            } catch (Exception e) {
                log.error("excel", e);
            }
        }
    }

    /**
     * 生成excel文件的标题
     * @param headers 表头
     * @param sheet 工作表
     */
    public static void generateExcelTitle(String[] headers, Sheet sheet){
        if (headers != null) {
            Row row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                sheet.setColumnWidth((short) i, (short) width);
                Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }
        }
    }

    /**
     *
     * @param data 数据集，支持Collection<map>或者Collection<T>
     * @param sheet 工作表
     * @param ds_titles 导出的数据列名称
     * @param styleList 每一列的样式列表
     * @param startNumber 开始行号，如果没有表头，可以从0开始，有表头从表头之后的行开始
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void generateExcelData(Collection data, Sheet sheet, String[] ds_titles,
                                      List<CellStyle> styleList, int startNumber)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int CurrentRowNumber = startNumber;
        for (Object datum : data) {
            Row row = sheet.createRow(CurrentRowNumber++);
            for (int j = 0; j < ds_titles.length; j++) { // 列数
                Cell cell = row.createCell(j);
                Object o;
                String s = ds_titles[j];
                if (datum instanceof Map) {
                    o = ((Map<String, Object>) datum).get(s);
                } else { //利用反射调用对应的方法
                    String methodName = "get" + s.substring(0, 1).toUpperCase() + s.substring(1);
                    Method method = datum.getClass().getMethod(methodName);
                    o = method.invoke(datum);
                }
                if (o == null) {
                    cell.setCellValue("");
                } else {
                    cell.setCellValue(o.toString());
                }
                cell.setCellStyle(styleList.get(j));
            }
        }
    }

    /**
     * 把表格写到输出流
     * @param wb
     * @param output
     */
    public static void outputExcel(Workbook wb, OutputStream output) {
        try {
            wb.write(output);
            output.flush();
        } catch (Exception e) {
            log.error("output", e);
        }
    }
}
