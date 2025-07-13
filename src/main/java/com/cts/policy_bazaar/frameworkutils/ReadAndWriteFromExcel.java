package com.cts.policy_bazaar.frameworkutils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFromExcel {
    public static void writeLinksData(List<String> data) {
        try (FileOutputStream file = new FileOutputStream("C:\\Users\\2408439\\CTS_Java_Training\\Practicals\\IntelliJ_Selenium_Projects\\Hackathon_Project\\testdata\\Scenario1_TestData.xlsx")) {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("data1");
            int size = data.size();
            XSSFRow first_row = sheet.createRow(0);
            first_row.createCell(0).setCellValue("Links");
            for (int i = 1; i <= size; i++) {
                XSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(data.get(i - 1));
            }
            wb.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "excelTestData")
    public static String[][] readData(Method testName) {
        ArrayList<String[]> res = new ArrayList<>();
        try (FileInputStream file = new FileInputStream("C:\\Users\\2408439\\CTS_Java_Training\\Practicals\\IntelliJ_Selenium_Projects\\Hackathon_Project\\testdata\\Scenario1_TestData.xlsx")) {
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
