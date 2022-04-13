package practiseAllTypeORGCNCTDRPDWN;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objeectRepository.ContactPage;
import com.crm.objeectRepository.CreateNewContact;
import com.crm.objeectRepository.CreateNewOrganization;
import com.crm.objeectRepository.HomePage;
import com.crm.objeectRepository.OrganizationInformationPage;
import com.crm.objeectRepository.OrganizationPage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws Throwable {

		WebDriver driver=null;

		//step:1 fecth the data from external file and store in variable
		FileUtility.intiallizePropertyFile(ConstantPath.propertyFilePath);
		/*FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
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

		//Random Method
		/*Random ran=new Random();
         int randomNumber = ran.nextInt(1000);*/
		int randomNum = JavaUtility.generateRndomNumber(1000);

		//USING ExcelUtility

		ExcelUtility.openExcel(excelPath);
		String organizationName = ExcelUtility.fetchData(excelSheet, 5, 1);
		String lastName = ExcelUtility.fetchData(excelSheet, 5, 2);

		/*FileInputStream fis1=new FileInputStream(excelPath);
		    Workbook wbk = WorkbookFactory.create(fis);*/


		//step:2 launch the browser
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

		//step:3 Do basic config for browser
		WebDriverUtility.maximizeBrowser(driver);
		//driver.manage().window().maximize();

		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverUtility.waitForPageLaoad(driver, timeoutLong);


		//step:4 open the url and navigate to application
		driver.get(url);
		//WebDriverUtility.launchApplication(driver, url, timeoutLong);

		VtigerLoginPage lgn=new VtigerLoginPage(driver);
		lgn.loginAction(username, password);


		/*step:5 login to the application
		WebElement userName = driver.findElement(By.name("user_name"));//.sendKeys(username);
		WebDriverUtility.javaScriptSendkeysThrough(driver, userName, username);

		WebElement pwd = driver.findElement(By.name("user_password"));//.sendKeys(password);
		WebDriverUtility.javaScriptSendkeysThrough(driver, pwd, password);

		WebElement loginbtn = driver.findElement(By.id("submitButton"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, loginbtn);*/

		HomePage hmpg=new HomePage(driver);
		hmpg.clickOrganization();

		/*step:6 create organization
		WebElement organization = driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, organization);*/

		OrganizationPage orgpg=new OrganizationPage(driver);
		orgpg.createOrgnImg();

		/*WebElement crtORG = driver.findElement(By.xpath("//img[@alt='Create Organization...']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, crtORG);*/

		String expOrganizationName=organizationName+randomNum;

		/*WebElement expOrg = driver.findElement(By.name("accountname"));//.sendKeys(expOrganizationName);
		WebDriverUtility.javaScriptSendkeysThrough(driver, expOrg, expOrganizationName);

		WebElement saveBtn = driver.findElement(By.xpath("//input[contains(@value,'Save')]"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, saveBtn);*/

		CreateNewOrganization crno=new CreateNewOrganization(driver);
		crno.orgsend(expOrganizationName);


		//step:7 verify the organization

		//String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		crno.saveButton();

		OrganizationInformationPage oipg=new OrganizationInformationPage(driver);
		oipg.verifyActualOrgName();

		/*if(actOrgName.equalsIgnoreCase(expOrganizationName)) {
			System.out.println("organization created successfully");
		}else {
			System.out.println("organization is created successfully");
		}
		Thread.sleep(1000);*/

		//step:8 create contact

		HomePage hmpg1=new HomePage(driver);
		hmpg1.clickContact();

		/*WebElement contact = driver.findElement(By.xpath("//a[(text()='Contacts')and contains(@href,'Contacts&action')]"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, contact);*/

		ContactPage cpg=new ContactPage(driver);
		cpg.createContactIMGClick();
		
		/*WebElement crtContct = driver.findElement(By.xpath("//img[@title='Create Contact...']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, crtContct); */

		CreateNewContact cnct=new CreateNewContact(driver);
		cnct.lastnameSendKeys(lastName);

		String expLastName=lastName+randomNum;

		WebElement lastnameTF = driver.findElement(By.xpath("//input[@name='lastname']"));//.sendKeys(expLastName);
		WebDriverUtility.javaScriptSendkeysThrough(driver, lastnameTF, expLastName);

		driver.findElement(By.xpath("//td[contains(.,'Organization Name')]/following-sibling::td//img[@title='Select']")).click();

		Set<String>windowIds1=driver.getWindowHandles();
		for (String wid : windowIds1) {
			driver.switchTo().window(wid);
			if(driver.getTitle().contains("Accounts")) {
				break;
			}
		}
		driver.findElement(By.id("Search_txt")).sendKeys(expOrganizationName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("sdet33")).click();

		Set<String> windowIds2 = driver.getWindowHandles();
		for (String wid : windowIds2) {
			driver.switchTo().window(wid);

			if(driver.getTitle().contains("Contacts")) {
				break;
			}
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

		String actOrgName1 = driver.findElement(By.linkText("sdet33")).getText();

		if(actualLastName.equalsIgnoreCase(expLastName) && actOrgName1.equalsIgnoreCase(expOrganizationName)){

			System.out.println("Contact created Successfully");
		}else {
			System.out.println("Contact didn't create successfully");
		}

		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.linkText("Sign Out")).click();

		ExcelUtility.writeDataInExistingRow(excelPath, excelSheet, 5, 4, "PASS");

		//
		ExcelUtility.closeExcel();
		driver.quit();
	}

}
