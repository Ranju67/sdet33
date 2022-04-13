package com.crm.genericUtility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.objeectRepository.HomePage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public HomePage homepage;
    public static WebDriver sdriver;

	/**
	 * this Annotation is used to open the DATABase using generic utility
	 * @throws Throwable 
	 */
	@BeforeSuite(groups={"Smoke","Regression"})
	public void openDatabase() throws Throwable {
		FileUtility.intiallizePropertyFile(ConstantPath.propertyFilePath);
		DataBaseUtility.getMySqlDatabaseConnection(ConstantPath.DatabaseUrl,FileUtility.fetchDataFromProperty("dbUserName") ,FileUtility.fetchDataFromProperty("dbPassword"));
		ExcelUtility.openExcel(ConstantPath.ExcelPath);
	
	}

	/**
	 * this annotation is used to launch the browser
	 */
	//@Parameters("browser")
	@BeforeClass(groups={"Smoke","Regression"})
	public void launchBrowser() {

		long timeout=JavaUtility.convertStringToLong(FileUtility.fetchDataFromProperty("timeout"));
		//String browser=FileUtility.fetchDataFromProperty("browser");
         String browser=System.getProperty("browser");
     
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else {
			Reporter.log("browser is not specified properly");
		}
               String url=System.getProperty("url");
         //  String url=FileUtility.fetchDataFromProperty("url");
		WebDriverUtility.launchApplicationWithMaximize(driver, url, timeout);
	sdriver=driver;
	
	}  


	/**
	 * This annotation is used to login the application 
	 * @throws Throwable
	 */
	@BeforeMethod(groups={"Smoke","Regression"})

	public void loginApplication() throws Throwable {

		VtigerLoginPage  loginpage=new VtigerLoginPage(driver);
		loginpage.loginAction(FileUtility.fetchDataFromProperty("username"), FileUtility.fetchDataFromProperty("password"));

		homepage=new HomePage (driver);
		

	}
	/**
	 * this annotation is used to logout the application 
	 * @throws Throwable
	 */
	@AfterMethod(groups={"Smoke","Regression"})
	public void logoutFromApplication() throws Throwable {
		homepage.signOutLOgo();
		homepage.signOutButton();
	}
	/**
	 * this annotation is used to close the browser 
	 */
	@AfterClass(groups={"Smoke","Regression"})

	public void  closeBrowser() {
		WebDriverUtility.closeBrowser(driver);
	}
	/**
	 * THis Annotation used to close the connection 
	 * @throws Throwable 
	 */
	@AfterSuite(groups={"Smoke","Regression"})

	public void closeDBConnection() throws Throwable {
		DataBaseUtility.closeDatabaseConnection();
		ExcelUtility.closeExcel();

	}

}