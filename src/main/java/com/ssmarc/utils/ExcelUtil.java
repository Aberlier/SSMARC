package com.ssmarc.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */
public class ExcelUtil {
    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @param wb        HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 30 * 256);
        sheet.setColumnWidth(2, 50 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 10 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        //声明列对象
        HSSFCell cell = null;
        cell = row.createCell(0);
        cell.setCellValue("主要的");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("次要的");
        cell.setCellStyle(style);
        //创建标题
        for (int i = 0; i < title.length; i++) {

            cell = row.createCell(i+2);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
                /*if (values[i][j] != null) {
                    sheet.setColumnWidth(i, values[i][j].toString().length() * 512);
                }*/
            }
        }
        return wb;
    }

    //发送响应流方法
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "gbk");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 生成表格数据
     *
     * @param response
     * @param matrix   表头与表体
     * @param title    嘴上上面标题
     * @param title    嘴上上面标题
     * @throws UnsupportedEncodingException
     */
    public void outputByDataSXSSF(HttpServletResponse response, List<List<String>> matrix, String title, String titles) throws UnsupportedEncodingException {
        Workbook workbook = null;
        try {
            // 生成workBook，导出Excel对象
            //ExcelSheetBean excelSheetBean = new ExcelSheetBean(matrix, title);
            // List<ExcelSheetBean> excelSheetBeans = new ArrayList<>();
            // excelSheetBeans.add(excelSheetBean);
            workbook = generateWorkbooks(matrix, title);
            downloadExport(workbook, response, titles);
        } catch (IllegalArgumentException | IllegalAccessException
                | InvocationTargetException | ClassNotFoundException
                | IntrospectionException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Workbook generateWorkbooks(List<List<String>> matrix, String title) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, UnsupportedEncodingException {
        Workbook workBook = new SXSSFWorkbook();
        Integer maxColumnIndex = 0;
        if (matrix.size() > 0) {
            maxColumnIndex = matrix.get(0).size() - 1;
        }
        Sheet sheet = workBook.createSheet();
                /*if (title != null) {
                    workBook.setSheetName(i, title);
                }*/
        // ExcelUtil.createFontssf(workBook);
        // 标题行
        Row row0 = sheet.createRow(0);
        Cell cell = row0.createCell(0);
        CellRangeAddress range0 = new CellRangeAddress(0, 0, 0, maxColumnIndex);
        row0.setHeightInPoints(100);
        sheet.addMergedRegion(range0);
        cell.setCellValue(title);


        // 标题样式
        CellStyle style = workBook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 指定单元格居中对齐
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 设置单元格垂直居中
        //style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        Font font = workBook.createFont();
        font.setFontName("黑体");
        font.setFontHeight((short) 400);
        style.setFont(font);
        style.setWrapText(true);//设置自动换行
        cell.setCellStyle(style);


        // 标题行样式
        CellStyle stylehang = workBook.createCellStyle();
        Font fonts = workBook.createFont();
        fonts.setFontName("宋体");
        fonts.setFontHeight((short) 200);
        stylehang.setFont(fonts);


        CellStyle Headhang = workBook.createCellStyle();
        Font Headfonts = workBook.createFont();
        Headfonts.setFontName("宋体");
        Headfonts.setFontHeight((short) 200);
        Headhang.setFont(fonts);
        Headhang.setAlignment(HorizontalAlignment.CENTER);

        //内容行
        if (matrix != null && matrix.size() > 0) {
            Row currRows = sheet.createRow(1);
            List<String> rowDatas = matrix.get(0);
            if (rowDatas != null && rowDatas.size() > 0) {
                for (int k = 0; k < rowDatas.size(); k++) {
                    Cell currCell = currRows.createCell(k);
                    currCell.setCellValue(rowDatas.get(k));
                    currCell.setCellStyle(Headhang);
                }
            }

            for (int j = 1; j < matrix.size(); j++) {
                Row currRow = sheet.createRow(1 + j);
                List<String> rowData = matrix.get(j);
                rowData.removeAll(Collections.singleton(null));
                if (rowData != null && rowData.size() > 0) {
                    for (int k = 0; k < rowData.size(); k++) {
                        Cell currCell = currRow.createCell(k);
                        currCell.setCellValue(rowData.get(k));
                        currCell.setCellStyle(stylehang);
                    }
                }
            }
        }
        int maxColumn = maxColumnIndex;
        for (int j = 0; j <= maxColumn; j++) {
            // sheet.autoSizeColumn(j);
            sheet.setColumnWidth(j, sheet.getColumnWidth(j) * 19 / 5);
        }
        return workBook;
    }

    /*// SXSSFWorkbook样式
    public static void createFontssf(SXSSFWorkbook workbook) {
        // 表头
        CellStyle fontStyle = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font1.setFontName("黑体");
        font1.setFontHeightInPoints((short) 14);// 设置字体大小
        fontStyle.setFont(font1);
        fontStyle.setAlignment(HorizontalAlignment.CENTER); // 居中

        // 表头2
        CellStyle fontContentStyle = workbook.createCellStyle();
        Font font3 = workbook.createFont();
        fontContentStyle.setFont(font3);
        fontContentStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        fontContentStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

        // 内容
        CellStyle fontStyle2 = workbook.createCellStyle();
        Font font2 = workbook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 10);// 设置字体大小
        fontStyle2.setFont(font2);
        fontStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
        fontStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
        fontStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
        fontStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
        fontStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        fontStyle2.setWrapText(true);//设置自动换行
    }*/

    public void downloadExport(Object obj, HttpServletResponse response, String title) {
        response.reset();
        try {
            title = URLEncoder.encode(title, "UTF-8");
        } catch (Exception e) {
            e.getMessage();
        }
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + title + ".xlsx");
        //response.setContentType("multipart/form-data");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        OutputStream output;
        try {
            output = response.getOutputStream();
            SXSSFWorkbook workbook = (SXSSFWorkbook) obj;
            workbook.write(output);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
