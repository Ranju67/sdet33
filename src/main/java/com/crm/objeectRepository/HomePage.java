package com.crm.objeectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//CONTACT MODEL
	
    @FindBy(xpath="//a[@href='index.php?module=Contacts&action=index']")
    private WebElement contactLink;
    
  
    // 2 ORGANIZATION MODEL
    
    @FindBy(xpath="//a[@href='index.php?module=Accounts&action=index']")
    		private WebElement orgnizationLink;
    
    
    //3 LEADMODEL
    
    @FindBy(linkText="Leads")
    private WebElement leadsLink;

    //4PURCHASE ORDER
    
    @FindBy(linkText="More")
    private WebElement moreLink;
    
    @FindBy(name="Purchase Order")
    private WebElement purchaseOrderLink;
    
    //LOGOUT 
    
    @FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement signOutLogo;
    
    @FindBy(xpath="//a[text()='Sign Out']")
    private WebElement singOutButton;
    
    //Step:3 
    public HomePage(WebDriver driver) {
    	PageFactory.initElements(driver,this);
    }
    
    //Step:4
    //way:1
    //1.contact
    
    public WebElement getContactLink() {
    	return contactLink;
    }
    
    //2.organization
    public WebElement orgnizationLink() {
    	return orgnizationLink;
     }
    //3.leads
    public WebElement getLeadsLink() {
    	return leadsLink ;
    }
   
    //4.more
    public WebElement getMoreLink() {
    	return moreLink;
    }
    public WebElement getPurchaseOrderLink() {
    	return purchaseOrderLink;
    }
    public WebElement getSignOutLOgo() {
		return signOutLogo;
	}
	public WebElement getSignOutButton() {
		return singOutButton;
	}
    
    //way:2
    
    //1 contact
    
    public void clickContact() {
    	contactLink.click();
    }
    //orgnization
    public void clickOrganization() {
    	orgnizationLink.click();
    	 
    }
    // 3 Leads
    public void clickLeads() {
    	leadsLink.click();
    }
    // purchase
   public void moreClick() {
    	moreLink.click();
   }
  
    public void clickPurchase() {
    	purchaseOrderLink.click();
      }
    public void signOutLOgo() {
		signOutLogo.click();
	}
	public void signOutButton() {
		singOutButton.click();
    }
   }