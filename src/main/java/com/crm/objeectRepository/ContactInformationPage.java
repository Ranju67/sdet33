package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
 
	@FindBy(id="dtlview_Last Name")
	private WebElement actualName;
	
	//3
	 public ContactInformationPage(WebDriver driver) {
	    	PageFactory.initElements( driver,this);
	    }
	
	 //4
	public WebElement getActualName() {
    	return actualName;
    }
	//way:2
	public String verify() {
   	 return actualName.getText();
   }
}
