package utils;

import controller.RequestController;
import model.Request;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelExporter {

    public static void exportRequestsToExcel() {
        List<Request> requests = RequestController.getAllRequests();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Solicitudes");

        // Header
        Row header = sheet.createRow(0);
        String[] columns = {"ID", "Usuario", "Producto", "Mensaje", "Fecha"};
        for(int i=0;i<columns.length;i++){
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Data
        int rowNum = 1;
        for(Request r : requests){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getId());
            row.createCell(1).setCellValue(r.getUserName());
            row.createCell(2).setCellValue(r.getProductName());
            row.createCell(3).setCellValue(r.getMessage());
            row.createCell(4).setCellValue(r.getRequestDate());
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("Solicitudes.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            JOptionPane.showMessageDialog(null, "Exportado correctamente a Solicitudes.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

