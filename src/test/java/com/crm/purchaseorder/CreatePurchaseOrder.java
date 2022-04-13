package com.crm.purchaseorder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objeectRepository.CreateNewPurchase;
import com.crm.objeectRepository.HomePage;
import com.crm.objeectRepository.PurchasePage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatePurchaseOrder {


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
		//int randomNumber = JavaUtility.generateRndomNumber(1000);

		//2 USING ExcelUtility
		//fetch dat from the excel
		//FileInputStream fis1=new FileInputStream(excelPath);
		//Workbook wbk = WorkbookFactory.create(fis);

		ExcelUtility.openExcel(excelPath);
		//String subjectName=wbk.getSheet(excelSheet).getRow(22).getCell(1).getStringCellValue();

		String subjectName = ExcelUtility.fetchData(excelSheet, 22, 1);
		//String vendorName = ExcelUtility.fetchData(excelSheet, 22, 2);
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

		WebDriverUtility.maximizeBrowser(driver);	
		WebDriverUtility.waitForPageLaoad(driver, timeoutLong);
		WebDriverUtility.launchApplication(driver, url, timeoutLong);
		//driver.get(url);
		//step:3 open the url and navigate to application


		//step:5 login to the application
		/*WebElement usrName = driver.findElement(By.name("user_name"));  //.sendKeys(username);
		WebDriverUtility.javaScriptSendkeysThrough(driver,usrName , username);

		WebElement pwd = driver.findElement(By.name("user_password"));  //.sendKeys(password);
		WebDriverUtility.javaScriptSendkeysThrough(driver, pwd, password);

		WebElement login = driver.findElement(By.id("submitButton"));  //.click();
		WebDriverUtility.clickActionThroughJS(driver, login);*/

		VtigerLoginPage login=new VtigerLoginPage(driver);
		login.loginAction(username, password);


		//WebElement moreLink = driver.findElement(By.linkText("More"));
		//moreLink.click();

		HomePage hmpg=new HomePage(driver);
		hmpg.moreClick();
		hmpg.clickPurchase();
		//driver.findElement(By.name("Purchase Order")).click();
		
		PurchasePage ppg=new PurchasePage(driver);
		ppg.clickCreateNewPurchase();
		//driver.findElement(By.xpath("//img[@title='Create Purchase Order...']")).click();

		CreateNewPurchase cnpo=new CreateNewPurchase(driver);
		cnpo.purchaseSend(subjectName);
		//driver.findElement(By.name("subject")).sendKeys(subjectName);
		//driver.findElement(By.name("vendor_name")).sendKeys(vendorName);
		
		cnpo.saveClick();
	    WebDriverUtility.acceptAlertPopup(driver);
		//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	
		/*Set<String>windowIds1=driver.getWindowHandles();
		for (String wid : windowIds1) {
			driver.switchTo().window(wid);
			//if(driver.getTitle().contains("Accounts")) {
			//	break;
			driver.findElement(By.name("search_text")).sendKeys(vendorName);
			driver.findElement(By.name("search")).click();}*/
		
		//driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();

   }
}
