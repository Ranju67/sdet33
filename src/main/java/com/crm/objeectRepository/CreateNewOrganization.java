package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//1
public class CreateNewOrganization {
	//2
	
	@FindBy(name="accountname")
	private WebElement organizationName;

	@FindBy(name="industry")
	private WebElement dropdownIndustry;

	@FindBy(name="accounttype")
	private WebElement drpdwnType;

	@FindBy(xpath="(//input[@type='button'])[1]")
	private WebElement saveButton;


	// 3
	public CreateNewOrganization(WebDriver driver) {
		PageFactory.initElements( driver,this);
	}
	//4 way:1

	
	public WebElement getOrganizationName() {
		return organizationName;
	}
	public WebElement getDropdownIndustry() {
		return dropdownIndustry;
	}
	public WebElement getDrpdwnType () {
		return drpdwnType;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	//4 way:2

	
	public void orgsend(String expOrganizationName) {
		organizationName.sendKeys(expOrganizationName);

	}
	public void dropdownIndustry(String industryTypeDropDwn) {

		Select sel=new Select(dropdownIndustry);
		sel.selectByValue(industryTypeDropDwn);
	}
	public void drpdwnType(String typeDrpDwn) {

		Select sel=new Select(drpdwnType);
		sel.selectByValue(typeDrpDwn);
	}
	public void saveButton() {
		saveButton.click();
	}
	public void clickAction() {
		saveButton();
	}

}


