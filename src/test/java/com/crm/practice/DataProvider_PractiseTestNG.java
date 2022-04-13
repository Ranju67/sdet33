package com.crm.practice;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;

public class DataProvider_PractiseTestNG {

	@Test(dataProvider ="dataprovider_exccel")
	public void parameters(String USERNAME,String PASSWORD) {
		Reporter.log(USERNAME +"=========="+PASSWORD,true);
	}
	
	@DataProvider
	public Object[][] dataprovider_exccel() throws Throwable{
		ExcelUtility.openExcel(ConstantPath.ExcelPath);
		Object[][] arr = ExcelUtility.fetchMultipleData("DataProvider");
		return arr;
	}
	
	
}
