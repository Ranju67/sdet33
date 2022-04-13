package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactVtigerFecthTheDataFromDataBase {

	public static void main(String[] args) throws Throwable {

		Connection connection=null;
		String url=null;
		String username=null;
		String password=null;
		String last_name=null;

	try {
		Driver d=new Driver();
		DriverManager.registerDriver(d);
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33","root","root");
		Statement statement=connection.createStatement();
		ResultSet result = statement.executeQuery("select * from vtiger");
		while(result.next()) {
			url=result.getString(1);
			username=result.getString(2);
			password=result.getString(3);
			last_name=result.getString(4);
		}
		//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		//instead this we use this its create the path
		WebDriverManager.chromedriver().setup();
		WebDriver wdriver=new ChromeDriver();
		wdriver.manage().window().maximize();
		wdriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(wdriver, 20);
		wdriver.get("http://localhost:8888");
		wdriver.findElement(By.name("user_name")).sendKeys(username);
		wdriver.findElement(By.name("user_password")).sendKeys(password);
		wdriver.findElement(By.id("submitButton")).click();
		wdriver.findElement(By.linkText("Contacts")).click();
		wdriver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();

			
		String expLast_name = last_name;

		wdriver.findElement(By.name("lastname")).sendKeys(last_name);
		wdriver.findElement(By.cssSelector("input[type='submit']")).click();

		//verify contact
		String actualContactName = wdriver.findElement(By.id("dtlview_Last Name")).getText();

		if (expLast_name.equals(actualContactName)){
			System.out.println("Contact is created successfully");
			}
		WebElement logo = wdriver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(wdriver);
		actions.moveToElement(logo).click().perform();
		wdriver.findElement(By.linkText("Sign Out")).click();
		wdriver.close();
	}
	finally {
		connection.close();
		System.out.println("Connection is closed");
		}
	}
}
