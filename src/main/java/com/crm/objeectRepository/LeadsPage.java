package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {

	@FindBy(xpath="//img[@title='Create Lead...']")
	private WebElement createLeadsImg;

	//3
	public LeadsPage(WebDriver driver){
		PageFactory.initElements( driver,this);
	}
	//4

	public WebElement getCreateLeadsImg() {
		return createLeadsImg;
	}

	//4----2
	public void createLeadsImg() {
		createLeadsImg.click();
	}
}
