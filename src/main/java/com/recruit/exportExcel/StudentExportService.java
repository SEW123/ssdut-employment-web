package com.recruit.exportExcel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.recruit.pojo.Message2student;





public class StudentExportService  {
    static String[] excelHeader = { "学号", "姓名", "电话", "岗位", "备注", "状态"};
    public static HSSFWorkbook export(List<Message2student> infoViewList) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Student");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
           
            sheet.setColumnWidth(i, 100 * 256);
        }

        for (int i = 0; i < infoViewList.size(); i++) {
            row = sheet.createRow(i + 1);
            Message2student infoView = infoViewList.get(i);
            row.createCell(0).setCellValue(infoView.getStudentId());
            sheet.autoSizeColumn(0);
            row.createCell(1).setCellValue(infoView.getName());
            sheet.autoSizeColumn(1);
            row.createCell(2).setCellValue("18296381655");
            sheet.autoSizeColumn(2);
            row.createCell(3).setCellValue(infoView.getJob());
            sheet.autoSizeColumn(3);
            row.createCell(4).setCellValue(infoView.getNote());
            sheet.autoSizeColumn(4);
//          需要判断通知类型
            row.createCell(5).setCellValue("接受");
            sheet.autoSizeColumn(5);
        }
        return wb;
    }
}
