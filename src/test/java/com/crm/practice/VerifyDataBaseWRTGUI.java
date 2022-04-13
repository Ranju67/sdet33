package com.crm.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyDataBaseWRTGUI {

	public static void main(String[] args) throws SQLException, IOException {
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
	   // ..............>ITS RUNTIME POLYMORPHISM<...................
		if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver1=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver1=new FirefoxDriver();
		}
		else {
			System.out.println("Browser is not Specified Properly");
		}
		
		Connection connection=null;
		try {
		Driver driver=new Driver();
	    DriverManager.registerDriver(driver);
	    connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement=connection.createStatement();
	
		// update query 
		//enter data(DML)
		int result=statement.executeUpdate("insert into project values('ty_prj_203','SHASH','25/02/2022','Instgram','Created',9);");
		if (result==1) {
			System.out.println("1 row is affected");
		}
		else
			System.out.println("No rows is affected");
		}
		finally {
			connection.close();
			System.out.println("connection is closed");
		}
		
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(timeoutLong, TimeUnit.SECONDS);
		String expectedProjectName="WhatsApp";
		driver1.get(url);
		driver1.findElement(By.id("usernmae")).sendKeys(username);
		driver1.findElement(By.id("inputPassword")).sendKeys(password);
		driver1.findElement(By.xpath("//button[.='Sign in']")).click();
		driver1.findElement(By.linkText("Projects")).click();
		driver1.findElement(By.xpath("//span[.='Create Project']")).click();
		WebElement projectDetails = driver1.findElement(By.xpath("//th[.='ProjectId']/../../..//tbody"));
			String projDetails = projectDetails.getText();
			if(projDetails.contains(expectedProjectName)) {
				System.out.println("data is displayed in GUI");
			}
			else {
				System.out.println("data is not displayed in GUI");
			}
		driver1.quit();
		}
	}
