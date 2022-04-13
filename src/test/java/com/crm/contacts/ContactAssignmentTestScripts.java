package com.crm.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objeectRepository.HomePage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactAssignmentTestScripts {

	public static void main(String[] args) throws Throwable {

		//Property File Operation

		FileUtility.intiallizePropertyFile(ConstantPath.propertyFilePath);

		String url = FileUtility.fetchDataFromProperty("url");
		String username = FileUtility.fetchDataFromProperty("username");
		String password = FileUtility.fetchDataFromProperty("password");
		String browser = FileUtility.fetchDataFromProperty("browser");
		String excelPath = FileUtility.fetchDataFromProperty("excelPath");
		String excelSheet = FileUtility.fetchDataFromProperty("excelSheet");
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
		
		VtigerLoginPage vlgn=new VtigerLoginPage(driver);
		vlgn.loginAction(username, password);

		//step:5 login to the application
		/*WebElement usrName = driver.findElement(By.name("user_name"));  //.sendKeys(username);
		WebDriverUtility.javaScriptSendkeysThrough(driver,usrName , username);

		WebElement pwd = driver.findElement(By.name("user_password"));  //.sendKeys(password);
		WebDriverUtility.javaScriptSendkeysThrough(driver, pwd, password);

		WebElement login = driver.findElement(By.id("submitButton"));  //.click();
		WebDriverUtility.clickActionThroughJS(driver, login);*/

		// CREATE CONTACT LINK
		/*WebElement contactLink = driver.findElement(By.xpath("//a[text()='Contacts']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, contactLink);

		WebElement crtCntct = driver.findElement(By.xpath("//img[@title='Create Contact...']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, crtCntct);*/
		
		HomePage hmp=new HomePage(driver);
		hmp.clickContact();

		//click choose file button 34tsc
		WebElement chooseBtn =driver.findElement(By.xpath("//input[@name='imagename']"));
		WebDriverUtility.scrollTillElementTroughJS(driver, chooseBtn);
		WebDriverUtility.clickActionThroughJS(driver, chooseBtn);

	}

}
