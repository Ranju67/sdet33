package mypractise;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class UsingPropertyFile_OrganizationTest {

	public static void main(String[] args) throws IOException, SQLException {
		
		WebDriver driver=null;
	String expOrganizationName = "Qspider";
	
		//step:1 Fetch the data from the external file and store in variable

    FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
    Properties properties=new Properties();
    properties.load(fis);
    
    String url = properties.getProperty("url");
    String username = properties.getProperty("username");
    String password = properties.getProperty("password");
    String browser= properties.getProperty("browser");
    String timeout= properties.getProperty("timeout");
    long timeoutLong=Long.parseLong(timeout);
    
    //RUNTIME POLYMORPHISM
    if(browser.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
	}
	//WE USE MULTIPLE BROWSER USE ELSE IF
	else if(browser.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	}else {
		System.out.println("Browser is not Specified Properly");
	}
    
    WebDriverManager.chromedriver().setup();
    driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(url);
	driver.findElement(By.name("user_name")).sendKeys(username);
	driver.findElement(By.name("user_password")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	
	driver.findElement(By.linkText("Organizations")).click();
	driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	
	driver.findElement(By.name("accountname")).sendKeys(expOrganizationName);
	
	driver.findElement(By.name("button")).click();
	
	String actualOrgnizationName = driver.findElement(By.id("dtlview_Organization Name")).getText();
	
	System.out.println("actualOrgnizationName :"+ actualOrgnizationName);
	
	WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
   
	Actions actions = new Actions(driver);
    actions.moveToElement(logo).click().perform();
   
    driver.findElement(By.linkText("Sign Out")).click();
    System.out.println("Organization name is created successfully");
    
    driver.quit();
		}
	}
