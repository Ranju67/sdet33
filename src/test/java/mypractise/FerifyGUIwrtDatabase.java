package mypractise;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

    public class FerifyGUIwrtDatabase {
    	
	public static void main(String[] args) throws IOException, SQLException {

		Connection connection=null;
		  
   try {
	   
	   WebDriver driver= null;
	 FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
	 Properties properties = new Properties();
	 properties.load(fis);
	 
	 String url = properties.getProperty("url");
	 String username = properties.getProperty("username");
	 String password = properties.getProperty("password");
	 String browser = properties.getProperty("browser");
	 String timeout= properties.getProperty("timeout");
	 long timeoutLong=Long.parseLong( timeout);
	 
		if (browser.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firfox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else {
			System.out.println("Browser is not specified properly");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.id("usernmae")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		
		String expectedprojectName="Metro";
		
		driver.findElement(By.name("projectName")).sendKeys(expectedprojectName);
		driver.findElement(By.name("createdBy")).sendKeys("Modi");
		WebElement status = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		
		Select select=new Select(status);
		select.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Driver driver1=new Driver();
		DriverManager.registerDriver(driver1);
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = connection.createStatement();
		
		//step:4
		ResultSet result = statement.executeQuery("select * from project;");
		
		while(result.next()) {
			String actualProjectName = result.getString("project_name");
			
		if(actualProjectName.equals(expectedprojectName)) {
			
				System.out.println("Data is stored in database");
			}
		}
		driver.quit();
   }
 
   finally {
		connection.close();
		System.out.println("connection is closed");
	}
      }
   }
