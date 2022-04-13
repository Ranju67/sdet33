package practiseAllTypeORGCNCTDRPDWN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objeectRepository.ContactPage;
import com.crm.objeectRepository.CreateNewContact;
import com.crm.objeectRepository.HomePage;
import com.crm.objeectRepository.VtigerLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactNameUsingExcelSheet {

	public static void main(String[] args) throws Throwable {

		//1.Fetch The Data from external file and store in variable

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

		//2.generate the random number
		int randomNumber = JavaUtility.generateRndomNumber(1000);
		/*Random ran=new Random();
		int randomNumber = ran.nextInt(1000);*/

		//3.Fetch the data from excel
		ExcelUtility.openExcel(excelPath);
		String lastName = ExcelUtility.fetchData(excelSheet, 2, 1);

		/*FileInputStream fis1=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wbk = WorkbookFactory.create(fis1);
		Sheet sheet = wbk.getSheet(excelSheet);
		Row row = sheet.getRow(2);
		Cell cell = row.getCell(1);
		String LastName = cell.getStringCellValue();*/

		//4. launch the browser
		
		WebDriver driver=null;
		//RunTime Polymorphism
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

		//5. Do basic config for browser

		//driver.manage().window().maximize();
		WebDriverUtility.maximizeBrowser(driver);

		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverUtility.waitForPageLaoad(driver, timeoutLong);

		//6. open the url and navigate to application
		driver.get(url);
		//WebDriverUtility.launchApplication(driver, url, timeoutLong);

		VtigerLoginPage login=new VtigerLoginPage(driver);
		login.loginAction(username, password);

		//7. login to the application
		//WebElement un = driver.findElement(By.name("user_name"));//.sendKeys(username);
		//WebDriverUtility.javaScriptSendkeysThrough(driver, un, username);

		//WebElement pwd = driver.findElement(By.name("user_password"));//.sendKeys(password);
		//WebDriverUtility.javaScriptSendkeysThrough(driver, pwd, password);

		//WebElement saveButton = driver.findElement(By.id("submitButton"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, saveButton);

		//8.create contact
		//WebElement contactLink = driver.findElement(By.xpath("//a[text()='Contacts']"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, contactLink);
		HomePage hmpg=new HomePage(driver);
		hmpg.clickContact();

		//WebElement crtCntct = driver.findElement(By.xpath("//img[@title='Create Contact...']"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, crtCntct);
        
		ContactPage cn=new ContactPage(driver);
		cn.createContactIMGClick();

		String expLastName = lastName+randomNumber;       //CONCATINATION WITH RANDOM NUMBER

		//WebElement lastNm = driver.findElement(By.name("lastname"));//.sendKeys(expLastName);
		//WebDriverUtility.javaScriptSendkeysThrough(driver, lastNm, expLastName);

		CreateNewContact cnc=new CreateNewContact(driver);
		cnc.lastnameSendKeys(expLastName);

		//WebElement svb = driver.findElement(By.cssSelector("input[type='submit']"));//.click();
		//WebDriverUtility.clickActionThroughJS(driver, svb);

		//String actualContactName = driver.findElement(By.id("dtlview_Last Name")).getText();

		//9.VERIFY THE CONTACT
		/*if (expLastName.equalsIgnoreCase(actualContactName)) {
			System.out.println("Last Name successfully created in contact model");
		}

		//pass status using write method
		try {
			ExcelUtility.writeDataInExistingRow(excelPath, excelSheet, 2, 4, "PASS");
		} catch (Throwable e1) {
			System.out.println("WRITE THE DATA In Existing ROW");
		}

		/*wbk.getSheet(excelSheet).getRow(2).createCell(4).setCellValue("Pass");
		FileOutputStream fos=new FileOutputStream(excelPath);
		wbk.write(fos);*/

		/*Thread.sleep(5000);
		//10.Sign out logout from application
		WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, logo);

		WebElement singOut = driver.findElement(By.xpath("//a[text()='Sign Out']"));//.click();
		WebDriverUtility.clickActionThroughJS(driver, singOut);*/
		
		hmpg.signOutLOgo();
		hmpg.signOutButton();

		//10. close the workbook
		/*wbk.close();
		ExcelUtility.closeExcel();
		driver.quit();*/
	}
}
