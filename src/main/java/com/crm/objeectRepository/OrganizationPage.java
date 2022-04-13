package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrgnImg;

	//3
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements( driver,this);
	}
	//4
	public WebElement getCreateOrgnImg(){
		return createOrgnImg;
	}
	
	//2
	public void createOrgnImg() {
		createOrgnImg.click();		
	}
}
