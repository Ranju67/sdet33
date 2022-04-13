package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContact {
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement lastNameTextField;

	@FindBy(xpath="//input[@type='submit'][1]")
	private WebElement saveButton;
	
	@FindBy(name="lastname")
	private WebElement lastNmaeTF1;
    
    //3
    public CreateNewContact(WebDriver driver) {
    	PageFactory.initElements( driver,this);
    }
    //4 getter
    
    public WebElement getLastNameTextField() {
    	return lastNameTextField;
    }
    public WebElement getSaveButton() {
    	return saveButton;
    }
    public WebElement getLastNmaeTF1() {
    	return lastNmaeTF1;
    }
    
	//Way:2 By crating business Library
    
    
    public void lastnameSendKeys(String expLastName) {
    	lastNameTextField.sendKeys(expLastName);
    }
    public void someActionLastName() {
    	saveButton.click();
    }
    public void lastNmaeTF1(String expectedLastName1){
    	lastNmaeTF1.sendKeys(expectedLastName1);
    }
    
    //erase ge baribeku  
	
}
