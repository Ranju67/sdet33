package mypractise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;

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
		String excelPath =FileUtility.fetchDataFromProperty("excelPath");
		String excelSheet =FileUtility.fetchDataFromProperty("excelSheet");

		long timeoutLong=Long.parseLong(timeout);

		//2.Fetch the data from excel=============================================>ExcelUtility With Constant Path
		ExcelUtility.openExcel(ConstantPath.ExcelPath);
        String orgnizationName = ExcelUtility.fetchData(excelSheet, 8, 1);
        
		//3 generate the Random number===========================================>JavaUtility
		/*Random ran=new Random();
				int randomNumber = ran.nextInt(1000);*/
		int randomNumber = JavaUtility.generateRndomNumber(1000);

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
			System.out.println("Browser is not specified Properly");
		}

		//step:3 Do basic config for browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//step:4 open the url and navigate to application
		driver.get(url);

		//step:5 login to the application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		//step:6 create organization
		driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		String expOrganizationName=orgnizationName+randomNumber;

		driver.findElement(By.name("accountname")).sendKeys(expOrganizationName);
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		//verify the Organization
		String actualOrganizationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actualOrganizationName.equals(expOrganizationName)) {
			System.out.println("Organization Nmae is successfully created");

	// pass the status value using write method===================================>ExcelUtility WriteDataInExistRoeMethod
			ExcelUtility.writeDataInExistingRow(excelPath, excelSheet, 8, 4, "QSPIDER");
			/*wbk.getSheet(excelSheet).getRow(8).createCell(4).setCellValue("PASS");
			FileOutputStream fos=new FileOutputStream(excelPath);
			wbk.write(fos);
			System.out.println("pass");*/
		}

		//to logout close 
		WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(logo).click().perform();

		// click on sing out
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		ExcelUtility.closeExcel();//=========================================>ExcelUtility close ()
		driver.quit();
	}
}
