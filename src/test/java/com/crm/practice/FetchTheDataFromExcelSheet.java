package com.crm.practice;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchTheDataFromExcelSheet {

	public static void main(String[] args) throws Exception{
   //step:1 we should convert the physical file into java readable object
		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		
		//step:2 open the excel file using workbookFactory class and create(--)
		Workbook wbk = WorkbookFactory.create(fis);
		
		//step:3 we should get the control of particular sheet by using getSheet(---)
		Sheet sht = wbk.getSheet("SDET33");
		
		//step:4 we should get the control of particular row by using getRow(---)
		Row rw = sht.getRow(1);
		
		//step:5 we should get the control of particular cell by using getCell(---)
		Cell cell = rw.getCell(0);
		
		//step:6 read / fetch the data by using getStringCellValue(--),toString()
		String data = cell.getStringCellValue();
		System.out.println(data);
		
		//step:7 close the workbook by using "close()
		wbk.close();
	}

}
