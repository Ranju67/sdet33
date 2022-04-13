package com.crm.organization;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.objeectRepository.CreateNewOrganization;
import com.crm.objeectRepository.OrganizationInformationPage;
import com.crm.objeectRepository.OrganizationPage;


public class Organization_Education_TypePOMClassTest extends BaseClass {
@Test

	public void organizationDropDown() throws Throwable {
		OrganizationPage orgpage=new OrganizationPage(driver);
		CreateNewOrganization createNorg=new CreateNewOrganization(driver);
		OrganizationInformationPage orgipg=new OrganizationInformationPage(driver);

		//store variable 
		String orgName= ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheet"),11 ,1)+JavaUtility.generateRndomNumber(1000);

		String dropdown1 = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheet"),11 ,2);
		String dropdown2 = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheet"),11 ,3);

		//click on Create organaization tab
		homepage.clickOrganization();
		
		//click on create new organization image
		orgpage.createOrgnImg();
		
		//send orgnization name
		createNorg.orgsend(orgName);
		createNorg.dropdownIndustry(dropdown1);
		createNorg.drpdwnType(dropdown2);
		createNorg.saveButton();
		
		//verify org information page
		String actualOrgN = orgipg.verifyActualOrgName();
		orgipg.actualdrpdwn();
		orgipg.typeactualdrpdwn();
		
		//verify actuala names
		Assert.assertTrue(actualOrgN.equals(orgName));
		
		
	}
}