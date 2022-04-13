package com.crm.genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	static Workbook wb;
	/**
	 * 
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 * @throws Throwable
	 */
	public static String fetchData(String sheetName,int rowNumber,int cellNumber) throws Throwable {
    Sheet sh = wb.getSheet(sheetName);
	String data = sh.getRow(rowNumber).getCell(cellNumber).getStringCellValue();
	return data;
	}
	
	/**
	 * 
	 * @param path
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param data
	 * @throws Throwable
	 */
public static void writeDataInNewRow(String path,String sheetName,int rowNumber,int cellNumber,String data) throws Throwable
{
	Sheet sh = wb.getSheet(sheetName);
	sh.createRow(rowNumber).createCell(cellNumber).setCellValue(data);
	FileOutputStream fosExcel=new FileOutputStream(path);
	wb.write(fosExcel);
	System.out.println("Data is written successfully");
}
/**
 * 
 * @param path
 * @param sheetName
 * @param rowNumber
 * @param cellNumber
 * @param data
 * @throws Throwable
 */

public static void writeDataInExistingRow(String path,String sheetName,int rowNumber,int cellNumber,String data) throws Throwable  {
 Sheet sh = wb.getSheet(sheetName);
 sh.getRow(rowNumber).createCell(cellNumber).setCellValue(data);
 FileOutputStream fosExcel=new FileOutputStream(path);
 wb.write(fosExcel);
 System.out.println("Data is written successfully");
}
/**
 * 
 * @param path
 * @throws Throwable
 */
public static void openExcel(String path) throws Throwable {
	FileInputStream fisExcel=new FileInputStream(path);
	wb=WorkbookFactory.create(fisExcel);
	System.out.println("Excel open successfully");
}

/**
 * 
 * @throws Throwable
 */
public static void closeExcel()throws Throwable {
	wb.close();
	System.out.println("Excel closed successfully");
}

public static Object[][] fetchMultipleData(String excelSheet){
	
	Sheet sh=wb.getSheet(excelSheet);
	Object[][] arr=new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
 for(int i=0 ; i<sh.getLastRowNum();i++) {
	
	 for(int j=0 ; j<sh.getRow(0).getLastCellNum() ; j++) {
		 
		 arr[i][j]=sh.getRow(i+1).getCell(j).toString();
	 }
 }
         return arr;

    }
}