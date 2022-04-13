package practiseAllTypeORGCNCTDRPDWN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objeectRepository.CreateNewOrganization;
import com.crm.objeectRepository.HomePage;
import com.crm.objeectRepository.OrganizationPage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationUsingExcelSheet {

	public static void main(String[] args) throws Throwable  {

		//1.Fetch the data by using property file =======================FileUtility

		FileUtility.intiallizePropertyFile(ConstantPath.propertyFilePath);
		String url =FileUtility.fetchDataFromProperty("url");
		String username =FileUtility.fetchDataFromProperty("username");
		String password= FileUtility.fetchDataFromProperty("password");
		String browser =FileUtility.fetchDataFromProperty ("browser");
		String timeout = FileUtility.fetchDataFromProperty("timeout");
		//String excelPath =FileUtility.fetchDataFromProperty("excelPath");
		String excelSheet =FileUtility.fetchDataFromProperty("excelSheet");

		long timeoutLong=Long.parseLong(timeout);

		//1 generate the Random number===========================================>JavaUtility
		/*Random ran=new Random();
				int randomNumber = ran.nextInt(1000);*/
		int randomNumber = JavaUtility.generateRndomNumber(1000);

		//2.Fetch the data from excel=============================================>ExcelUtility With Constant Path
		ExcelUtility.openExcel(ConstantPath.ExcelPath);

		String orgnizationName = ExcelUtility.fetchData(excelSheet, 8, 1);


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

		//driver.manage().window().maximize();
		WebDriverUtility.maximizeBrowser(driver);	

		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverUtility.waitForPageLaoad(driver, timeoutLong);

		//step:3 open the url and navigate to application
		WebDriverUtility.launchApplication(driver, url, timeoutLong);

		driver.get(url);
		VtigerLoginPage login=new VtigerLoginPage(driver);
		login.loginAction(username, password);

		//step:5 login to the application
		/*WebElement usrName = driver.findElement(By.name("user_name"));  //.sendKeys(username);
		WebDriverUtility.javaScriptSendkeysThrough(driver,usrName , username);

		WebElement pwd = driver.findElement(By.name("user_password"));  //.sendKeys(password);
		WebDriverUtility.javaScriptSendkeysThrough(driver, pwd, password);

		WebElement login = driver.findElement(By.id("submitButton"));  //.click();
		WebDriverUtility.clickActionThroughJS(driver, login);

		//step:6 create organization

		WebElement orgLink = driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, orgLink);*/
		HomePage hmpg=new HomePage(driver);
		hmpg.clickOrganization();
		
		OrganizationPage opg=new OrganizationPage(driver);
		opg.createOrgnImg();
		
		//WebElement createOrg = driver.findElement(By.xpath("//img[@alt='Create Organization...']"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, createOrg);

		String expOrganizationName=orgnizationName+randomNumber;

		//====>instead using sendkey this() use generic utility methods 
		
		//WebElement actualOrgName = driver.findElement(By.name("accountname"));//.sendKeys(expOrganizationName);
		
		//WebDriverUtility.javaScriptSendkeysThrough(driver, actualOrgName, expOrganizationName);
		
		CreateNewOrganization cno=new CreateNewOrganization(driver);
		cno.orgsend(expOrganizationName);
		
		/*WebElement savebutton = driver.findElement(By.xpath("//input[contains(@value,'Save')]"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, savebutton);*/
         cno.clickAction();


		//verify the Organization
		/*String actualOrganizationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actualOrganizationName.equals(expectedOrgName)) {

			System.out.println("Organization Name is successfully created");
		}

		// pass the status value using write method===================================>ExcelUtility WriteDataInExistRoeMethod
		
		ExcelUtility.writeDataInExistingRow(excelPath, excelSheet, 8, 4, "PASS");
		/*wbk.getSheet(excelSheet).getRow(8).createCell(4).setCellValue("PASS");
		FileOutputStream fos=new FileOutputStream(excelPath);
		wbk.write(fos);
		System.out.println("pass");
		ExcelUtility.closeExcel();//=========================================>ExcelUtility close ()

		//to logout close 
		WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		/*Actions actions = new Actions(driver);
		actions.moveToElement(logo).click().perform();*/
		/*WebDriverUtility.clickActionThroughJS(driver, logo);

		// click on sing out
		WebElement signout = driver.findElement(By.xpath("//a[text()='Sign Out']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, signout);

		//driver.quit();
		WebDriverUtility.closeBrowser(driver);*/
         hmpg.signOutLOgo();
         hmpg.signOutButton();

	}
}
