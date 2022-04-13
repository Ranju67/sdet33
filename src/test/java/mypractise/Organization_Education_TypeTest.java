package mypractise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Organization_Education_TypeTest {

	public static void main(String[] args) throws SQLException, IOException, Exception {

	//step:1 Fetch the data from the external file and store in variable

    FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
    Properties properties=new Properties();
    properties.load(fis);
    
    String url = properties.getProperty("url");
    String username = properties.getProperty("username");
    String password= properties.getProperty("password");
    String browser = properties.getProperty("browser");
    String timeout = properties.getProperty("timeout");
    String excelPath = properties.getProperty("excelPath");
    String excelSheet = properties.getProperty("excelSheet");
    long timeoutLong=Long.parseLong(timeout);
    
    //random method
    Random ran=new Random();
    int randomNumber = ran.nextInt(100);
    
    // fetch the data from Excel
    FileInputStream fis1=new FileInputStream(excelPath);
    Workbook wbk = WorkbookFactory.create(fis1);
    
    String orgName = wbk.getSheet(excelSheet).getRow(11).getCell(1).getStringCellValue();
	String industry=wbk.getSheet(excelSheet).getRow(11).getCell(2).getStringCellValue();
	String type=wbk.getSheet(excelSheet).getRow(11).getCell(3).getStringCellValue();    if(browser.equalsIgnoreCase("Chrome")) {
    	
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
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    
    //step:4 open the url and navigate to application
    driver.get(url);
    
    //step:5 login to the application
    driver.findElement(By.name("user_name")).sendKeys(username);
    driver.findElement(By.name("user_password")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	
	//step:6 create Organization 
	driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();
	driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	
	 String expOrganizationName = orgName+randomNumber;
	driver.findElement(By.name("accountname")).sendKeys(expOrganizationName);
	WebElement dropdownIndustry = driver.findElement(By.name("industry"));

	Select select=new Select(dropdownIndustry);
	select.selectByValue(industry);
	
	WebElement drpdwnType = driver.findElement(By.name("accounttype"));
	Select select1=new Select(drpdwnType);
	select1.selectByValue(type);
	
	driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
	
	//verify the organization

	String actualOrganizationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
	String actualIndustryName = driver.findElement(By.id("dtlview_Industry")).getText();
	String actualTypeName = driver.findElement(By.id("dtlview_Type")).getText();

	if(expOrganizationName.equalsIgnoreCase(actualOrganizationName) && actualIndustryName.equalsIgnoreCase(industry) && actualTypeName.equalsIgnoreCase(type)) 
	{
		System.out.println("Organization created successfully with Industy and Type");
	
		//pass status using write method
	    wbk.getSheet(excelSheet).getRow(11).createCell(4).setCellValue("pass");
        FileOutputStream fos=new FileOutputStream(excelPath);
        wbk.write(fos);
	}
	Thread.sleep(5000);
	//Sign out /logout from application
	driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
	driver.findElement(By.linkText("Sign Out")).click();
	
	//close the workbook
			wbk.close();
			driver.quit();
	   }
	}
}
