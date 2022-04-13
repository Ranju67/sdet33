package mypractise;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriver driver=null;
		
		//step:1 fecth the data from external file and store in variable
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);

		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password= properties.getProperty("password");
		String browser = properties.getProperty("browser");
		String timeout = properties.getProperty("timeout");
		long timeoutLong=Long.parseLong(timeout);

		//step:2 launch the browser


		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
		}
		else if(browser.equalsIgnoreCase("firfox")) {
			WebDriverManager.firefoxdriver().setup();
		}else {
			System.out.println("Browser is not specified Properly");
		}
		//step:3 Do basic config for browser
		driver=new ChromeDriver();
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
		
		String expOrganizationName="sdet33";
		
		driver.findElement(By.name("accountname")).sendKeys(expOrganizationName);
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		//step:7 verify the organization
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(expOrganizationName.equals(actOrgName)) {
			System.out.println("organization created successfully");
		}else {
			System.out.println("organization is created successfully");
		}
		Thread.sleep(1000);

		//step:8 create contact

		driver.findElement(By.xpath("//a[(text()='Contacts')and contains(@href,'Contacts&action')]")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		String expLastName="infy";

		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expLastName);
		
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
		driver.quit();
	}

}
