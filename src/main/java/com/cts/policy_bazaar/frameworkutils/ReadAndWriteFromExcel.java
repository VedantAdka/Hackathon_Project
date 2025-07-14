package com.cts.policy_bazaar.frameworkutils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFromExcel {
    public static void writeDataForScenario1(List<String> data, String colName, int colNo) {
        File filePath=new File("testdata/TestData_Scenario1.xlsx");
        XSSFWorkbook wb;
        XSSFSheet sheet;
        try {
            if(filePath.exists()){
                FileInputStream fis=new FileInputStream(filePath);
                wb=new XSSFWorkbook(fis);
                sheet= wb.getSheet("data1");
                fis.close();
            }else{
                wb=new XSSFWorkbook();
                sheet=wb.createSheet("data1");
            }
            int size = data.size();
            XSSFRow first_row = sheet.getRow(0);
            if(first_row==null){
                first_row=sheet.createRow(0);
            }
            first_row.createCell(colNo).setCellValue(colName);
            for (int i = 1; i <= size; i++) {
                XSSFRow row = sheet.getRow(i);
                if(row==null){
                    row=sheet.createRow(i);
                }
                row.createCell(colNo).setCellValue(data.get(i - 1));
            }
            FileOutputStream fileOut=new FileOutputStream(filePath);
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeDataForScenario3(List<String> data, String colName, int colNo) {
        File filePath=new File("testdata/TestData_Scenario2.xlsx");
        XSSFWorkbook wb;
        XSSFSheet sheet;
        try {
            if(filePath.exists()){
                FileInputStream fis=new FileInputStream(filePath);
                wb=new XSSFWorkbook(fis);
                sheet= wb.getSheet("data1");
                fis.close();
            }else{
                wb=new XSSFWorkbook();
                sheet=wb.createSheet("data1");
            }
            int size = data.size();
            XSSFRow first_row = sheet.getRow(0);
            if(first_row==null){
                first_row=sheet.createRow(0);
            }
            first_row.createCell(colNo).setCellValue(colName);
            for (int i = 1; i <= size; i++) {
                XSSFRow row = sheet.getRow(i);
                if(row==null){
                    row=sheet.createRow(i);
                }
                row.createCell(colNo).setCellValue(data.get(i - 1));
            }
            FileOutputStream fileOut=new FileOutputStream(filePath);
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @DataProvider(name = "excelTestData")
    public static String[][] readData(Method testName) {
        ArrayList<String[]> res = new ArrayList<>();
        try (FileInputStream file = new FileInputStream("testdata/Scenario3_TestData.xlsx")) {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet s = wb.getSheetAt(0);
            int noOfRows = s.getPhysicalNumberOfRows();
            for (int r = 1; r < noOfRows; r++) {
                XSSFRow row = s.getRow(r);
                int noOfCell = row.getPhysicalNumberOfCells();
                String testCaseName = row.getCell(2).getStringCellValue().trim().replaceAll(" ", "").toLowerCase();
                String runType = row.getCell(3).getStringCellValue().toLowerCase();
                if (testCaseName.equals(testName.getName().toLowerCase()) && runType.equals("y")) {
                    ArrayList<String> data = new ArrayList<>();
                    for (int c = 4; c < noOfCell; c++) {
                        XSSFCell cell = row.getCell(c);
                        switch (cell.getCellType()) {
                            case NUMERIC:
                                data.add(String.valueOf((long) cell.getNumericCellValue()));
                                break;
                            case STRING:
                                data.add(cell.getStringCellValue());
                                break;
                            default:
                                data.add("");
                                break;
                        }
                    }
                    res.add(data.toArray(new String[0]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toArray(new String[0][0]);
    }
}
