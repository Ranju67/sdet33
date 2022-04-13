package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {
   
	@FindBy(xpath="//img[@title='Create Purchase Order...']")
    private WebElement createPurchaseOrderIMG;
	
	
	//3
	public PurchasePage (WebDriver driver){
		PageFactory.initElements(driver,this);
	}
	
	//4
	public WebElement getCreatePurchaseOrderIMG() {
    	return createPurchaseOrderIMG;
    }
	//2
	public void clickCreateNewPurchase() {
		createPurchaseOrderIMG.click();
	}
}
