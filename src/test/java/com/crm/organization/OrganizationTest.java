package com.crm.organization;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.objeectRepository.CreateNewOrganization;
import com.crm.objeectRepository.OrganizationInformationPage;
import com.crm.objeectRepository.OrganizationPage;

@Listeners(com.crm.genericUtility.ListenerImplimentation.class)
public class OrganizationTest extends BaseClass {

	@Test(groups="Smoke")
	public void createOrganizationTest() throws Throwable {

		OrganizationPage orgpage=new OrganizationPage(driver);
		CreateNewOrganization createNorg=new CreateNewOrganization(driver);
		OrganizationInformationPage orgipg=new OrganizationInformationPage(driver);

		//store variable 
		String orgName=ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheet"), 8, 1)+JavaUtility.generateRndomNumber(1000);


		//click on organization tab 
		homepage.clickOrganization();

		//click on create organization 

		orgpage.createOrgnImg();	

		//create organization and save
		createNorg.orgsend(orgName);
        createNorg.saveButton();
		
		//step;7 verify the orgniztion
		String actOrgName=orgipg.verifyActualOrgName();
	

		//verification using Assertion Harad Assert
		//Assert.assertTrue(actOrgName.contains("SDET33"),actOrgName );
		
		//verification using Assertion... soft Assert
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(actOrgName.contains("SDET"));
		sa.assertAll();

		Reporter.log("organization name",true);
		
		
		//if block else block for verification
		/*if (orgName.equalsIgnoreCase(actOrgName)) {
			Reporter.log("Organization created successfully", true);
		}
		else {
			Reporter.log("Organization created is not created successfully",true);
		}*/
		
		
	}
}

