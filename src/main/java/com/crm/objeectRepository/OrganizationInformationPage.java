package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	@FindBy (id="dtlview_Organization Name")
	private WebElement actualOrgName;
	
	@FindBy(id="dtlview_Industry")
	private WebElement actualdrpdwn;
	
	@FindBy(id="dtlview_Type")
	private WebElement typeactualdrpdwn;
	
	//3 
	
	public OrganizationInformationPage(WebDriver driver) {
	PageFactory.initElements( driver,this);  
	}
	//4
	//way:1
	
	public WebElement getActualOrgName() {
		return actualOrgName;
	}
	public WebElement getActualDrpDwn() {
		return actualdrpdwn;
	} 
	public WebElement getTypeActualDrpDwn() {
		return typeactualdrpdwn;
	}
     //	way:2
	public String verifyActualOrgName() {
		return actualOrgName.getText();
	}
	public String actualdrpdwn() {
		return actualdrpdwn.getText();
	}
	public String typeactualdrpdwn() {
		return typeactualdrpdwn.getText();
	}
	
}