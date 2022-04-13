package com.crm.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleData3ITERATING {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wbk = WorkbookFactory.create(fis);
		Sheet sh = wbk.getSheet("Login");
		
		for (int i =1; i <=sh.getLastRowNum(); i++) {
				String username = sh.getRow(i).getCell(0).getStringCellValue();
				String Password = sh.getRow(i).getCell(1).getStringCellValue();

				System.out.println(username+"==========="+Password);
		}
	}
}
