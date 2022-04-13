package com.crm.practice;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleData {

	public static void main(String[] args) throws Exception {
		
  FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
	Workbook wbk = WorkbookFactory.create(fis);
	Sheet sh = wbk.getSheet("Login");
	for (int i = 0; i < sh.getLastRowNum(); i++) {
		String userName= sh.getRow(i).getCell(0).toString();
		
		if (userName.equalsIgnoreCase("admin2")) {
			String password = sh.getRow(i).getCell(1).toString();
			System.out.println("password :" + password);
		}
	  }
  }	
}
