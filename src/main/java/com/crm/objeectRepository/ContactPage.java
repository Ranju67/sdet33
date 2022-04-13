package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	@FindBy (xpath="//img[@title='Create Contact...']")
    private WebElement createContactImg;
	
	//3
	public ContactPage(WebDriver driver) {
    	PageFactory.initElements( driver,this);
    }
	
	//4 getter
	public WebElement getCreateContactImg() {
    	return createContactImg;
	}
	
	//2
	public void createContactIMGClick() {
    	createContactImg.click();
	}
}
