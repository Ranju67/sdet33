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
import com.crm.objeectRepository.OrganizationInformationPage;
import com.crm.objeectRepository.OrganizationPage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Organization_Education_TypeTest {

	public static void main(String[] args) throws Throwable {

		//step:1 Fetch the data from the external file and store in variable

		FileUtility.intiallizePropertyFile(ConstantPath.propertyFilePath);
		/* FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
    Properties properties=new Properties();
    properties.load(fis);*/

		String url = FileUtility.fetchDataFromProperty("url");
		String username = FileUtility.fetchDataFromProperty("username");
		String password= FileUtility.fetchDataFromProperty("password");
		String browser = FileUtility.fetchDataFromProperty("browser");
		String timeout = FileUtility.fetchDataFromProperty("timeout");
		String excelPath = FileUtility.fetchDataFromProperty("excelPath");
		String excelSheet = FileUtility.fetchDataFromProperty("excelSheet");
		long timeoutLong=Long.parseLong(timeout);

		//random method
		/*Random ran=new Random();
    int randomNumber = ran.nextInt(100);*/
		int ranNumber = JavaUtility.generateRndomNumber(1000);

		// fetch the data from Excel
		ExcelUtility.openExcel(excelPath);
		/*FileInputStream fis1=new FileInputStream(excelPath);
    Workbook wbk = WorkbookFactory.create(fis);*/

		String orgName =ExcelUtility.fetchData(excelSheet, 5, 1);
		//String lastName=ExcelUtility.fetchData(excelSheet, 5, 2);
		String industryTypeDropDwn = ExcelUtility.fetchData(excelSheet, 11, 2);
		String typeDrpDwn = ExcelUtility.fetchData(excelSheet, 11, 3);

		//step:2 launch the browser	
		WebDriver driver=null;

		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firfox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else {
			System.out.println("browser is not specified properly");
		}

		//step:3 configure the browser
		WebDriverUtility.maximizeBrowser(driver);
		//driver.manage().window().maximize();

		WebDriverUtility.waitForPageLaoad(driver, timeoutLong);
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//step:4 open the url and navigate to application

		//driver.get(url);
		WebDriverUtility.launchApplication(driver, url, timeoutLong);
		VtigerLoginPage lgn=new VtigerLoginPage(driver);
		lgn.loginAction(username, password);

		//step:5 login to the application
		/*WebElement un = driver.findElement(By.name("user_name"));//.sendKeys(username);
		WebDriverUtility.javaScriptSendkeysThrough(driver, un, username);

		WebElement pwd = driver.findElement(By.name("user_password"));//.sendKeys(password);
		WebDriverUtility.javaScriptSendkeysThrough(driver, pwd, password);

		WebElement saveButton = driver.findElement(By.id("submitButton"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, saveButton);*/

		//step:6 create Organization 

		HomePage hmpg=new HomePage(driver);
		hmpg.clickOrganization();

		//WebElement orgTextField = driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, orgTextField);*/

		OrganizationPage orpg=new OrganizationPage(driver);
		orpg.createOrgnImg();

		//WebElement crtContact = driver.findElement(By.xpath("//img[@alt='Create Organization...']"));//.click();
		// WebDriverUtility.clickActionThroughJS(driver, crtContact);

		String expOrganizationName = orgName+ranNumber;

		CreateNewOrganization crtno=new CreateNewOrganization(driver);
		crtno.orgsend(expOrganizationName);

		//WebElement expOrgName = driver.findElement(By.name("accountname"));//.sendKeys(expOrganizationName);
		//WebDriverUtility.javaScriptSendkeysThrough(driver, expOrgName, expOrganizationName);	

		CreateNewOrganization crno=new CreateNewOrganization(driver);
		crno.dropdownIndustry(industryTypeDropDwn);

		crno.drpdwnType(typeDrpDwn);

		/*WebElement dropdownIndustry = driver.findElement(By.name("industry"));
	WebDriverUtility.select(industryTypeDropDwn, dropdownIndustry);
		//Select select=new Select(dropdownIndustry);
		//select.selectByValue(industryTypeDropDwn);

		WebElement drpdwnType = driver.findElement(By.name("accounttype"));
		WebDriverUtility.select(typeDrpDwn, drpdwnType);
		/*Select select1=new Select(drpdwnType);
	      select1.selectByValue(typeDrpDwn);*/


		//WebElement savebtn = driver.findElement(By.cssSelector("input[title='Save [Alt+S]']"));//.click();
		// WebDriverUtility.clickActionThroughJS(driver, savebtn);

		// verify the organization find Actual NAMES
		CreateNewOrganization cnro1=new CreateNewOrganization(driver);
		cnro1.clickAction();

		/*String actualOrganizationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		String actualIndustryName = driver.findElement(By.id("dtlview_Industry")).getText();
		String actualTypeName = driver.findElement(By.id("dtlview_Type")).getText();*/


		OrganizationInformationPage oripg=new OrganizationInformationPage(driver);
		oripg.verifyActualOrgName();
		oripg.actualdrpdwn();
		oripg.typeactualdrpdwn();


		/*if(expOrganizationName.equalsIgnoreCase(actualOrganizationName) && actualIndustryName.equalsIgnoreCase(industryTypeDropDwn) && actualTypeName.equalsIgnoreCase(typeDrpDwn)) 
		{
			System.out.println("Organization created successfully with Industy and Type");
		}

		//pass status using write method

		ExcelUtility.writeDataInExistingRow(excelPath, excelSheet, 11, 4, "PASS");
		// wbk.getSheet(excelSheet).getRow(11).createCell(4).setCellValue("pass");
		// FileOutputStream fos=new FileOutputStream(excelPath);*/
		// wbk.write(fos);

		//Thread.sleep(5000);

		//Sign out /logout from application

		//WebElement logOutImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, logOutImg);

		hmpg.signOutLOgo();
		hmpg.signOutButton();

		//WebElement singOut = driver.findElement(By.xpath("//a[@href='index.php?module=Users&action=Logout']"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, singOut);

		//close the workbook
		//ExcelUtility.closeExcel();
		//driver.quit();
	}
}