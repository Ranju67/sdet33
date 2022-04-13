package com.crm.contacts;


import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objeectRepository.ContactPage;
import com.crm.objeectRepository.HomePage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactAssignmentTsc_92 {
	public static void main (String [] args) throws Throwable {

		//Property File Operation

		FileUtility.intiallizePropertyFile(ConstantPath.propertyFilePath);

		String url = FileUtility.fetchDataFromProperty("url");
		String username = FileUtility.fetchDataFromProperty("username");
		String password = FileUtility.fetchDataFromProperty("password");
		String browser = FileUtility.fetchDataFromProperty("browser");
		//String excelPath = FileUtility.fetchDataFromProperty("excelPath");
		//String excelSheet = FileUtility.fetchDataFromProperty("excelSheet");
		String timeout = FileUtility.fetchDataFromProperty("timeout");
		long timeoutLong=Long.parseLong(timeout);

		//1 generate the Random number===========================================>JavaUtility
		/*Random ran=new Random();
							int randomNumber = ran.nextInt(1000);*/
		int randomNumber = JavaUtility.generateRndomNumber(1000);


		//step:3 launch the browser
		WebDriver driver=null;

		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firfox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();

		}else {
			System.out.println("Browser is not specified Properly");
		}

		//WebDRiverUtility
		//driver.manage().window().maximize();
		WebDriverUtility.maximizeBrowser(driver);	

		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverUtility.waitForPageLaoad(driver, timeoutLong);

		//step:3 open the url and navigate to application
		WebDriverUtility.launchApplication(driver, url, timeoutLong);
		//driver.get(url);
		
		VtigerLoginPage vln=new VtigerLoginPage(driver);
		vln.loginAction(username, password);

		//step:5 login to the application
		/*WebElement usrName = driver.findElement(By.name("user_name"));  //.sendKeys(username);
		WebDriverUtility.javaScriptSendkeysThrough(driver,usrName , username);

		WebElement pwd = driver.findElement(By.name("user_password"));  //.sendKeys(password);
		WebDriverUtility.javaScriptSendkeysThrough(driver, pwd, password);

		WebElement login = driver.findElement(By.id("submitButton"));  //.click();
		WebDriverUtility.clickActionThroughJS(driver, login);*/
		
		HomePage hmp=new HomePage(driver);
		hmp.clickContact();
	
		// CREATE CONTACT LINK
	/*	WebElement contactLink = driver.findElement(By.xpath("//a[text()='Contacts']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, contactLink);*/

		ContactPage cpg=new ContactPage(driver);
		cpg.createContactIMGClick();
		
		/*WebElement crtCntct = driver.findElement(By.xpath("//img[@title='Create Contact...']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, crtCntct);*/

		String lastName1 = "raj Signh";
		 String expectedLastName1 = lastName1+randomNumber;

		//driver.findElement(By.name("lastname"));//.sendKeys(expectedLastName1);
		//WebDriverUtility.javaScriptSendkeysThrough(driver, expectedLastName1, lastName);

		//click on report to img
		WebElement plusImg = driver.findElement(By.xpath("//td[contains(.,'Reports To')]/following-sibling::td//img[1][@title='Select']"));
		WebDriverUtility.clickActionThroughJS(driver, plusImg);
		
		Set<String>windowIds1=driver.getWindowHandles();
		for (String wid : windowIds1) {
			driver.switchTo().window(wid);

			/*if(driver.getTitle().contains("Contacts")) {
		break;
			}*/
		}
		
	driver.findElement(By.xpath("//a[text()=' Test Yantra604']")).click();
	
 /*driver.findElement(By.xpath("(//input[@title='Clear'])[2]")).click();
 
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
	driver.findElement(By.linkText("Sign Out")).click();*/

	}

}

