package com.recruit.exportExcel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.recruit.pojo.Message2student;





public class OfferOwnerService  {

    static String[] excelHeader = { "学号", "姓名", "Email"};
    public static HSSFWorkbook export(List<Message2student> infoViewList, String sheetName) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
         // sheet.SetColumnWidth(i, 100 * 256);
        }

        for (int i = 0; i < infoViewList.size(); i++) {
            row = sheet.createRow(i + 1);
            Message2student infoView = infoViewList.get(i);
            row.createCell(0).setCellValue(infoView.getStudentId());
            sheet.autoSizeColumn(0);
            row.createCell(1).setCellValue(infoView.getName());
            sheet.autoSizeColumn(1);
            row.createCell(2).setCellValue(infoView.getMail());
            sheet.autoSizeColumn(2);
        }
        return wb;
    }
}
