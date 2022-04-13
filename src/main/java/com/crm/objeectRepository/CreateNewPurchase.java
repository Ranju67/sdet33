package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewPurchase {
	
	
	@FindBy(name="subject")
	private WebElement subjectTextField;

	@FindBy(name="vendor_name")
	private WebElement vendorNameTextField;
	
	@FindBy(xpath="(//input[@value='  Save  '])[1]")
	private WebElement saveButton;
	
	//3
	public CreateNewPurchase (WebDriver driver){
		PageFactory.initElements(driver,this);
	}
	
	//4
	
	public WebElement getSubjectTextField() {
		return subjectTextField;
	}
	public WebElement getVendorNameTextField() {
		return vendorNameTextField;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}

	//4 WAY:2
	
	public  void purchaseSend(String subjectName) {
		subjectTextField.sendKeys(subjectName);
		//vendorNameTextField.sendKeys(vendorName);
    }
	
	public void saveClick() {
		saveButton.click();
	}
}