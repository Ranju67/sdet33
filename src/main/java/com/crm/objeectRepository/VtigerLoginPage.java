package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Step:1 WE Should create class and make it Public 
public class VtigerLoginPage {

	//Step:2 declaration===========>we will declare the locators as private using @FindBy
	@FindBy(name="user_name")
	private WebElement userNameTextField;

	@FindBy (name="user_password")
	private WebElement passWordTextField;

	@FindBy (id="submitButton")
	private WebElement loginButton;

	// ==============only once we initialize it ==============
	
	//Step:3 Initialization ---------->We will create public Costructors AND Initialize the Elemnets / variables
	public VtigerLoginPage(WebDriver dirver) {
	PageFactory.initElements( dirver,this);

	}
	//Step:4 Utilization------------>by developing public getters or/and Business library
	//getters
	//WAY:1 By creating public getters
	public WebElement getUserNameTextField() {
		return userNameTextField;
	}
	
	public WebElement getPasswordTextField() {
		return passWordTextField;
	}
	public WebElement getLoginButton() {
		return loginButton;
	}
	
	//Way:2 By crating business Library
	public void loginAction(String username,String password) {
		enterUN_pwd(username,password);
		loginButton.click();
	}
	
	public void enterUN_pwd(String username,String password) {
		userNameTextField.sendKeys(username);
		passWordTextField.sendKeys(password);
     }
  }