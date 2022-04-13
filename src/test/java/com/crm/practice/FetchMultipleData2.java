package com.crm.practice;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleData2 {
	public static void main(String[] args) throws Throwable{
	FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
	Workbook wbk = WorkbookFactory.create(fis);
	Sheet sh = wbk.getSheet("Login");
	for (int i = 1; i <=sh.getLastRowNum(); i++) {
		 int rowCount = sh.getLastRowNum();
		System.out.println(rowCount);
		for (int j = 0; j < sh.getRow(i).getLastCellNum(); j++) {
		  System.out.println(sh.getRow(i).getLastCellNum());
		}
	  }
  }
}


