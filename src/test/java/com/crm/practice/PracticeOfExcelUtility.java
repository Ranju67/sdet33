package com.crm.practice;

import com.crm.genericUtility.ExcelUtility;

public class PracticeOfExcelUtility {

	public static void main(String[] args) throws Throwable {
		//EXCEL SHEET 1
    ExcelUtility.openExcel("./src/test/resources/TestData.xlsx");
    ExcelUtility.writeDataInNewRow("./src/test/resources/TestData.xlsx","SDET33", 19, 1, "Staus");
    String orgName=ExcelUtility.fetchData("SDET33", 8, 1);
	System.out.println(orgName);
	
	ExcelUtility.writeDataInNewRow("./src/test/resources/TestData.xlsx","SDET33", 20, 1, "pass");
    String lastName = ExcelUtility.fetchData("SDET33", 8, 2);
    System.out.println(lastName);
    
    //Excel SHEET 2
    ExcelUtility.openExcel("./src/test/resources/TestData.xlsx");
    ExcelUtility.writeDataInExistingRow("./src/test/resources/TestData.xlsx","SDET33", 19, 1, "Staus");
    String orgName1=ExcelUtility.fetchData("SDET33", 8, 1);
	System.out.println(orgName1);
	
	ExcelUtility.writeDataInNewRow("./src/test/resources/TestData.xlsx","SDET33", 20, 1, "Pass");
    String lastName1 = ExcelUtility.fetchData("SDET33", 8, 2);
    System.out.println(lastName1);
    ExcelUtility.closeExcel();
	
	}

}
