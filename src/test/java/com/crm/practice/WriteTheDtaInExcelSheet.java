package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteTheDtaInExcelSheet {

	public static void main(String[] args) throws Exception {

		//Step:1 we should convert the physical file into java readable object
		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
	
		//strep:2 open the excel by using "WorkbookFactory" class and create(--)
		Workbook wbk = WorkbookFactory.create(fis);
		
		//step:3 WE should get the control of particular sheet by using "getSheet(--)"

		Sheet sheet = wbk.getSheet("SDET33");
		
		//step:4 WE should get the control of particular row by using "getRow(--)"
		Row row = sheet.getRow(1);
		
		//step:5 we should create perticular cell
		Cell cell = row.createCell(1);//createCell() to pass the value 
		
		//step:6 Store/write the data
		cell.setCellValue("Pass");//return type is void
		
		//step:7 we should specify the path of excel by using FileOutputStream
		FileOutputStream fos=new FileOutputStream("./src/test/resources/TestData.xlsx");
		 
		//step:8 we should save the data by using write()
		wbk.write(fos);//retun type void
		
		//step:9 close the workbook
		wbk.close();
		System.out.println("Data is stored in Excel");
	}

}
