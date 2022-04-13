	package com.crm.practice;
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
	
	public class VerifyGUIWRTDatabase {
	
		//..................
		public static void main(String[] args) throws SQLException, IOException {
	    Connection connection=null;
	    
     try {
	    WebDriver driver1=null;
	    FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String browser = properties.getProperty("browser");
		String timeout = properties.getProperty("timeout");
		long timeoutLong=Long.parseLong( timeout);
		
		                //only browser cross verify 
		               // IT IS A RUN TIME POLYMORPHISM
			if(browser.equalsIgnoreCase("Chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver1=new ChromeDriver();
			}
			//WE USE MULTIPLE BROWSER USE ELSE IF
			else if(browser.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver1=new FirefoxDriver();
			}else {
				System.out.println("Browser is not Specified Properly");
			}
			
			driver1.manage().window().maximize();
			driver1.manage().timeouts().implicitlyWait(timeoutLong, TimeUnit.SECONDS);
			
			driver1.get(url);
			driver1.findElement(By.id("usernmae")).sendKeys(username);
			driver1.findElement(By.id("inputPassword")).sendKeys(password);
			driver1.findElement(By.xpath("//button[.='Sign in']")).click();
					
			driver1.findElement(By.linkText("Projects")).click();
			driver1.findElement(By.xpath("//span[.='Create Project']")).click();
			
			String expectedProjectName="LULU";
	
			driver1.findElement(By.name("projectName")).sendKeys(expectedProjectName);
			driver1.findElement(By.name("createdBy")).sendKeys("Shashikumar");
			WebElement status = driver1.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
			
			Select select=new Select(status);
			select.selectByVisibleText("Created");
			driver1.findElement(By.xpath("//input[@type='submit']")).click();
			
		    
		    Driver driver=new Driver();
		    DriverManager.registerDriver(driver);
		    //step:3 create connection
		    connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			Statement statement=connection.createStatement();
		
			//step:4 execute query 
			//for fetch data(DQL)
			ResultSet result=statement.executeQuery("select * from project;");
			
			while(result.next()) {
				String actualProjectName = result.getString("project_name");
				if(actualProjectName.equals(expectedProjectName)) {
					System.out.println("Data is stored in database");
				}
			}
			driver1.close();
			
			}
			//step:5 close connection
			finally {
				connection.close();
				System.out.println("connection is closed");
			}
		}
	}
