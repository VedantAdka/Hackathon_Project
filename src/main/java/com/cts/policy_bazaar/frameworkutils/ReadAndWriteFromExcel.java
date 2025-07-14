package com.cts.policy_bazaar.frameworkutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

public class ReadAndWriteFromExcel {

    private static final String EXCEL_PATH = "testdata/Scenario1_2_3_TestData.xlsx";

    @DataProvider(name = "excelTestData")
    public Object[][] readData(Method testMethod) {
        List<Object[]> result = new ArrayList<>();
        String methodName = testMethod.getName().trim().toLowerCase();

        try (FileInputStream file = new FileInputStream(EXCEL_PATH)) {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            int totalParams = testMethod.getParameterCount(); // includes rowNum
            int excelParams = totalParams - 1;
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int r = 1; r < rowCount; r++) {
                XSSFRow row = sheet.getRow(r);
                if (row == null) continue;

                Cell tcNameCell = row.getCell(2); // Column C
                Cell runTypeCell = row.getCell(3); // Column D

                if (tcNameCell == null || runTypeCell == null) continue;

                String tcName = tcNameCell.getStringCellValue().trim().replaceAll(" ", "").toLowerCase();
                String runType = runTypeCell.getStringCellValue().trim().toLowerCase();

                if (tcName.equals(methodName) && runType.equals("y")) {
                    List<String> dataRow = new ArrayList<>();

                    for (int c = 4; c < 4 + excelParams; c++) {
                        XSSFCell cell = row.getCell(c);
                        if (cell == null) {
                            dataRow.add("");
                        } else {
                            switch (cell.getCellType()) {
                                case STRING:
                                    dataRow.add(cell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    dataRow.add(String.valueOf((long) cell.getNumericCellValue()));
                                    break;
                                default:
                                    dataRow.add("");
                            }
                        }
                    }

                    // Add row number as final argument
                    dataRow.add(String.valueOf(r));
                    result.add(dataRow.toArray());
                }
            }

            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toArray(new Object[0][0]);
    }

    public static void  writeResult(String status, int rowNum) {
        try (FileInputStream file = new FileInputStream(EXCEL_PATH)) {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);

            // Column L = 11 (0-indexed)
            XSSFCell statusCell = row.getCell(11);
            if (statusCell == null) {
                statusCell = row.createCell(11);
            }
            statusCell.setCellValue(status);

            try (FileOutputStream out = new FileOutputStream(EXCEL_PATH)) {
                wb.write(out);
            }

            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeDataForScenario1(List<String> data, String colName, int colNo) {
        File filePath = new File("testdata/TestData_Scenario1.xlsx");
        XSSFWorkbook wb;
        XSSFSheet sheet;
        try {
            if (filePath.exists()) {
                FileInputStream fis = new FileInputStream(filePath);
                wb = new XSSFWorkbook(fis);
                sheet = wb.getSheet("data1");
                fis.close();
            } else {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("data1");
            }
            int size = data.size();
            XSSFRow first_row = sheet.getRow(0);
            if (first_row == null) {
                first_row = sheet.createRow(0);
            }
            first_row.createCell(colNo).setCellValue(colName);
            for (int i = 1; i <= size; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    row = sheet.createRow(i);
                }
                row.createCell(colNo).setCellValue(data.get(i - 1));
            }
            FileOutputStream fileOut = new FileOutputStream(filePath);
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDataForScenario3(List<String> data, String colName, int colNo) {
        File filePath = new File("testdata/TestData_Scenario3.xlsx");
        XSSFWorkbook wb;
        XSSFSheet sheet;
        try {
            if (filePath.exists()) {
                FileInputStream fis = new FileInputStream(filePath);
                wb = new XSSFWorkbook(fis);
                sheet = wb.getSheet("data1");
                fis.close();
            } else {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("data1");
            }
            int size = data.size();
            XSSFRow first_row = sheet.getRow(0);
            if (first_row == null) {
                first_row = sheet.createRow(0);
            }
            first_row.createCell(colNo).setCellValue(colName);
            for (int i = 1; i <= size; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    row = sheet.createRow(i);
                }
                row.createCell(colNo).setCellValue(data.get(i - 1));
            }
            FileOutputStream fileOut = new FileOutputStream(filePath);
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
