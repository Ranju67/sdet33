package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadsPage {

	//Step:2
	
	@FindBy(name="lastname")
	private WebElement lastNameTextField;
	
	@FindBy(name="company")
	private WebElement companyNameTextField;
	
	@FindBy(xpath="(//input[@value='  Save  '])[1]")
	private WebElement saveButton;

	@FindBy(name="Delete")
	private WebElement deleteButton;
	
	
	//Step:3

	public CreateNewLeadsPage(WebDriver driver){
		PageFactory.initElements( driver,this);
	}
	
	//Step:4
	//way:1   GETTER
	
	
	
	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}
	
	public WebElement getCompanyNameTextField() {
		return companyNameTextField;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	public WebElement getDeleteButton() {
		return deleteButton;
    }

	//WAY:2 BUSSINESS LIBRARY
	
	
	
	public void leadsSendKeys(String expLastName,String expCompany) {
		lastNameTextField.sendKeys(expLastName);
		companyNameTextField.sendKeys(expCompany);
		clickSaveButton();
	}
	public void clickSaveButton() {
		saveButton.click();
	}
	public void leadDeleteButton() {
		deleteButton.click();
	}
}
