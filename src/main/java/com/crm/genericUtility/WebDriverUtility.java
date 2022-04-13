package com.crm.genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.google.common.io.Files;


/**
 * This class is a collection of webdriver actions
 * @author SHASHI KUMAR
 *
 */
public class WebDriverUtility {

	/**
	 * This Method is used to wait implicit for specified time
	 * @param driver
	 * @param timeout
	 */
   public static void waitForPageLaoad(WebDriver driver,long timeout ) {
	   driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
   }
   /**
    * This method is used to wait  until element visible
    * @param driver
    * @param timeout
    * @param element
    */
   public static void waitUnitlElementVisible(WebDriver driver,long timeout,WebElement element ) {
	   WebDriverWait wait=new WebDriverWait(driver, timeout);
	   wait.until(ExpectedConditions.visibilityOf(element));
   }
   /**
    * This method is used to wait  until element visible with specific polling time
    * @param driver
    * @param timeout
    * @param element
    * @param pollingTimeout
    */
   public static void waitUnitlElementVisibleWithCustomPoll(WebDriver driver,long timeout,WebElement element,long pollingTimeout )  {
	   WebDriverWait wait=new WebDriverWait(driver, timeout);
	   wait.until(ExpectedConditions.visibilityOf(element));
	   wait.pollingEvery(Duration.ofSeconds(pollingTimeout));
	   wait.ignoring(Throwable.class);
   }
   /**
    * This method used to wait until element is clickable with customize time and polling period
    * @param element
    * @param timeout
    * @param pollingTime
 * @throws InterruptedException 
    */
   public static void customWaitTillElementClickable(WebElement element,int timeout,int pollingTime ) throws InterruptedException  {
	   int count=0;
	   while(count<=timeout)
	   {
		   try {
			   element.click();
			   break;
		   }
		   catch (NoSuchElementException e)
		   {
			 Thread.sleep(pollingTime);
			 count++;
		   }
	   }
   }
   /**
    * This method will maximize the browser window 
    * @param driver
    */
   public static void maximizeBrowser(WebDriver driver) {
	   driver.manage().window().maximize();
   } 
   
   /**
    * This method will used to open the application with maximize  
    * @param driver
    * @param url
    * @param timeout
    */
   public static void launchApplicationWithMaximize(WebDriver driver,String url,long timeout) {
	   driver.get(url);
	   maximizeBrowser(driver);
	   waitForPageLaoad(driver, timeout); 
   }
   
   /**
    * This method will used to open the application 
    * @param driver
    * @param url
    * @param timeout
    */
   public static void launchApplication(WebDriver driver,String url,long timeout) {
	   driver.get(url);
	   waitForPageLaoad(driver, timeout); 
   }
   /**
    * This method is used to switch to the particular window
    * @param driver
    * @param partialTitleText
    */
   public static void switchToWindow(WebDriver driver,String partialTitleText) {
	   Set<String> winIDs2=driver.getWindowHandles();
	   for (String id : winIDs2) {
		driver.switchTo().window(id);
		if(driver.getTitle().contains(partialTitleText)) {
			break;
		}
	}
   }
   /**
    * This method is used to move to  the cursor on element 
    * @param driver
    * @param element
    */
   public static void moveToElement(WebDriver driver,WebElement element) {
	   Actions action=new Actions(driver);
	   action.moveToElement(element).perform();
   }
   
   /**
    * This method is used to right click on element
    * @param driver
    * @param element
    */
   public static void rightClickonElement(WebDriver driver,WebElement element) {
	   Actions action=new Actions(driver);
	   action.contextClick(element).perform();
   }
   /**
    * This method is used to double click on element
    * @param driver
    * @param element
    */
   public static void doubleClickonElement(WebDriver driver,WebElement element) {
	   Actions action=new Actions(driver);
	   action.doubleClick(element).perform();
   }
   /**
    * Thia method is used to select the dropdown option by index
    * @param element
    * @param index
    */
   public static void select(WebElement element,int index) {
	   Select select=new Select(element);
	   select.selectByIndex(index);
   }
   /**
    * Thia method is used to select the dropdown option by visbleText
    * @param element
    * @param visble
    */
   public static void select(WebElement element,String visbleText) {
	   Select select=new Select(element);
	   select.selectByVisibleText(visbleText);
   }
   /**
    * Thia method is used to select the dropdown option by value 
    * @param value
    * @param element
    */
   public static void select(String value,WebElement element) {
	   Select select=new Select(element);
	   select.selectByValue(value);
   }
   /**
    * This method is used to quit the browser instance
    * @param driver
    */
   public static void closeBrowser(WebDriver driver) {
	   driver.quit();
   }
   
   /**
    * This method is used to switch the frame by index
    * @param driver
    * @param index
    */
   public static void frame(WebDriver driver,int index) {
	   driver.switchTo().frame(index);
   }
   
   /**
    * This method is used to switch the frame by nameOrID
    * @param driver
    * @param nameOrID
    */
   public static void frame(WebDriver driver,String nameOrID) {
	   driver.switchTo().frame(nameOrID);
   }
   
   /**
    * This method is used to switch the frame by element
    * @param driver
    * @param element
    */
   public static void frame(WebDriver driver,WebElement element) {
	   driver.switchTo().frame(element);
   }
   /**
    * This method is used to take the screen shot of failed test script
    * @param driver
    * @param FileName
    * @throws IOException
    */
   public static void takesScreenShotOfFailedScript(WebDriver driver,String FileName) throws IOException {
	   
	   TakesScreenshot ts = (TakesScreenshot) driver;
	     File src = ts.getScreenshotAs(OutputType.FILE);
	 File dst = new File("./screenShots/"+FileName+"_"+JavaUtility.getCurrentTimeAndDate()+".png");
	 //Files.copy(src, dst);
	FileUtils.copyFile(src,dst);
   }
   
   /**
    * This method is used to take the screen shot of failed test script and also it will return the absolute path of screen shot where it store
    * @param driver
    * @param FileName
 * @return 
    * @throws IOException
    */
public static String takesScreenShotandgetPath(WebDriver driver,String FileName) throws IOException {
	   
	   TakesScreenshot ts = (TakesScreenshot) driver;
	     File src = ts.getScreenshotAs(OutputType.FILE);
	 File dst = new File("./srceenShot/"+FileName+"_"+JavaUtility.getCurrentTimeAndDate()+".png");
	 //Files.copy(src, dst);
	FileUtils.copyFile(src,dst);
	String absolutePath = dst.getAbsolutePath();
	return absolutePath;
   }
/**
 * This method is created to oen the application through Javascript
 * @param driver
 * @param url
 */
public static void openApplicationThroughJS(WebDriver driver,String url) {
JavascriptExecutor jse=(JavascriptExecutor)driver;
jse.executeScript("window.location='"+url+"'");
}

/**
 * This method is created to send the data into perticular textfield through Javascript
 * @param driver
 * @param element
 * @param input
 */
public static void javaScriptSendkeysThrough(WebDriver driver,WebElement element,String input) {
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	jse.executeScript("arguments[0].value='"+input+"'",element);
}

/**
 * This method is created to click on the button/element through Javascript
 * @param driver
 * @param element
 */
public static void clickActionThroughJS(WebDriver driver,WebElement element) {
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	jse.executeScript("arguments[0].click();",element);
}
 
/**
 * This method is created to scroll the webpage until the element is present
 * @param driver
 * @param element
 */
public static void scrollTillElementTroughJS(WebDriver driver,WebElement element) {
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	jse.executeScript("arguments[0].scrollIntoView();",element);
}
public static void scrollDownToPageThroughJS(WebDriver driver,String upOrDown) {
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	jse.executeScript("window.scrollTo(0,"+upOrDown+"document.body.scrollHeight)");
}
/**
 * this method is used to return the poptext
 * @param driver
 * @return
 */
public static String getTextAlertPopup(WebDriver driver) {
	String popuptext=driver.switchTo().alert().getText();
	return popuptext;
}
/**
 * This method is used to dimiss the alert
 * @param driver
 */
public static void dismissAlertPopup(WebDriver driver) {
	driver.switchTo().alert().dismiss();
}
/**
 * This method is used to getthe  text 
 * @param driver
 */
public static void acceptAlertPopup(WebDriver driver) {
	driver.switchTo().alert().accept();
}

}