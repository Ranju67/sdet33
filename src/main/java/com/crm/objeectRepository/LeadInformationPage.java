package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInformationPage {

	@FindBy(id="dtlview_Last Name")
	private WebElement lastNameTextField;

	@FindBy(id="dtlview_Company")
	private WebElement companyNameTextField;

	//3
	public  LeadInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//4
	//way:1
	public WebElement  getLastNameTextField() {
		return lastNameTextField;
	}
	public WebElement  getCompanyNameTextField() {
		return companyNameTextField;
	}

	//WAY:2
	
	public void lastNameTextField() {
		lastNameTextField.getText();
	}
	public void  companyNameTextField() {
		companyNameTextField.getText();
	}
	public void actualNames() {
		lastNameTextField();
		companyNameTextField();
	}
}
