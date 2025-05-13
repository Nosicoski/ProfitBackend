package com.juanma.profit.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportarExcel {

    /**
     * Exporta un TableModel a un archivo .xlsx
     * @param model El TableModel de la JTable
     * @param file  Archivo destino (.xlsx)
     * @throws IOException si hay un fallo de E/S
     */
    public static void exportar(TableModel model, File file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Ventas");

            // 1. Cabeceras
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(model.getColumnName(col));
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // 2. Datos
            for (int row = 0; row < model.getRowCount(); row++) {
                Row excelRow = sheet.createRow(row + 1);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    Cell cell = excelRow.createCell(col);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else if (value instanceof Boolean) {
                        cell.setCellValue((Boolean) value);
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // 3. Autoajusta ancho de columnas
            for (int col = 0; col < model.getColumnCount(); col++) {
                sheet.autoSizeColumn(col);
            }

            // 4. Graba el fichero
            try (FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            }
        }
    }
}
