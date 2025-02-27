package main.Utility;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

  public String path;
  public FileInputStream fis;
  public FileOutputStream fileOut;
  public CellStyle style;
  private XSSFWorkbook workbook;
  private XSSFSheet sheet;
  private XSSFRow row;
  private XSSFCell cell;

  public ExcelUtil(String path) {

    this.path = path;
  }

  public int getRowCount(String sheetName) throws IOException {
    fis = new FileInputStream(path);
    workbook = new XSSFWorkbook(fis);
    sheet = workbook.getSheet(sheetName);
    int rwoCount = sheet.getLastRowNum();
    workbook.close();
    fis.close();
    return rwoCount;

  }

  public int getCellCount(String sheetName, int rowNum) throws IOException {
    fis = new FileInputStream(path);
    workbook = new XSSFWorkbook(fis);
    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(rowNum);
    int cellCount = row.getLastCellNum();
    workbook.close();
    fis.close();
    return cellCount;

  }

  public String getCellData(String sheetName, int rowNum, int columNum) throws IOException {

    fis = new FileInputStream(path);
    workbook = new XSSFWorkbook(fis);
    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(rowNum);
    cell = row.getCell(columNum);

    DataFormatter formatter = new DataFormatter();

    String data;

    try {
      data = formatter.formatCellValue(cell);
    } catch (Exception e) {
      data = "";
    }
    workbook.close();
    fis.close();
    return data;

  }
}
