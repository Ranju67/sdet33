package com.crm.lead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objeectRepository.CreateNewLeadsPage;
import com.crm.objeectRepository.HomePage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLeadTest {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
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


		//2 USING ExcelUtility
		//fetch dat from the excel
		//FileInputStream fis1=new FileInputStream(excelPath);
		//Workbook wbk = WorkbookFactory.create(fis);
		ExcelUtility.openExcel(excelPath);
		//String lastName= wbk.getSheet(excelSheet).getRow(17).getCell(1).getStringCellValue();
		//String CompanyName=wbk.getSheet(excelSheet).getRow(17).getCell(2).getStringCellValue();
		String lastName = ExcelUtility.fetchData(excelSheet, 17, 1);
		String companyName = ExcelUtility.fetchData(excelSheet, 17, 2);


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

		//step:5 login to the application
		/*WebElement usrName = driver.findElement(By.name("user_name"));  //.sendKeys(username);
		WebDriverUtility.javaScriptSendkeysThrough(driver,usrName , username);

		WebElement pwd = driver.findElement(By.name("user_password"));  //.sendKeys(password);
		WebDriverUtility.javaScriptSendkeysThrough(driver, pwd, password);

		WebElement login = driver.findElement(By.id("submitButton"));  //.click();
		WebDriverUtility.clickActionThroughJS(driver, login);*/

		//USING POM
		VtigerLoginPage login=new VtigerLoginPage(driver);
		login.loginAction(username, password);

		//Create Leads Link
		//driver.findElement(By.linkText("Leads")).click();
		//driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();

		HomePage hmpg=new HomePage(driver);
		hmpg.clickLeads();

		String expLastName = lastName+randomNumber;

		String expCompany=companyName+randomNumber;

		//driver.findElement(By.name("lastname")).sendKeys(expLastName);//textfield
		// driver.findElement(By.name("company")).sendKeys(expCompany);//textfield
		//driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		
		CreateNewLeadsPage cnlpg=new CreateNewLeadsPage(driver);
		cnlpg.leadsSendKeys(expLastName, expCompany);

		//delete the lead Information 
		
	//driver.findElement(By.xpath("(//input[@name='Delete'])[1]")).click();

		cnlpg.leadDeleteButton();
		
		driver.switchTo().alert().accept();
		//WebDriverUtility.acceptAlertPopup(driver);
		
		Thread.sleep(2000);
	
		//driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
	//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		hmpg.signOutLOgo();
		hmpg.signOutButton();
		
		
		
	}

}
