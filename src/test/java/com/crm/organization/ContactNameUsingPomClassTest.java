package com.crm.organization;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.objeectRepository.ContactInformationPage;
import com.crm.objeectRepository.ContactPage;
import com.crm.objeectRepository.CreateNewContact;

public class ContactNameUsingPomClassTest extends BaseClass {

	@Test(groups={"Smoke","Regression"})
	public void createContactTest() throws Throwable   {

		ContactPage cnpg=new ContactPage(driver);
		CreateNewContact cncpg=new CreateNewContact(driver);
		ContactInformationPage cipg=new ContactInformationPage(driver);

		String createContact = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheet"), 2, 1)+JavaUtility.generateRndomNumber(1000);

		//create contact tab
		homepage.clickContact();

		//create contact image
		cnpg.createContactIMGClick();

		//create contact and save
		cncpg.lastnameSendKeys(createContact);
		cncpg.someActionLastName();

		//verify the actualname 
		String actualLastName = cipg.verify();
		//Assert.fail();

		if(createContact.equalsIgnoreCase(actualLastName)) {
			Reporter.log("Contact created successfully",true);
		}
		else {
			Reporter.log("Contact is not created successfully",true);
		}

	}
}