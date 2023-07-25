package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_06_Web_Element_Command_II {
	WebDriver driver; 
	String projectPath = System.getProperty("user.dir");
  @BeforeMethod
  public void Open_Browser() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  

//  @Test
//  public void TC_01_Is_Displayed() {
//	  WebElement emailTextbox = driver.findElement(By.id("mail"));
//	  
//	  if (emailTextbox.isDisplayed()) {
//		emailTextbox.sendKeys("123456");
//		System.out.println("Email textbox is displayed");
//	} else {
//		System.out.println("Email textbox is not displayed");
//	}
//  }
  
  
  
  
  
  @Test
  public void TC_02_Is_Displayed_Refactor() {
	  
	  By emailTextboxBy = By.id("email");
	  By ageOver18UnderBy = By.id("over_18");
	  By educationTextareaBy = By.id("edu");
	  By user5TextBy = By.xpath("//h5[text()= 'Name: User5']");
	  
	  if (isElementDisplayed(emailTextboxBy)) {
		  senkeyToElement(emailTextboxBy, "Automation Testing");
	  }
	  if (isElementDisplayed(ageOver18UnderBy)) {
		  clickToElement(ageOver18UnderBy);
	  }
	  if (isElementDisplayed(educationTextareaBy)) {
		  senkeyToElement(educationTextareaBy, "Automation Testing");
	  }
	  
	  //Đang mong đợi thằng user5 không hiển thị nên dùng assertFalse
	  Assert.assertFalse(isElementDisplayed(user5TextBy));
  }
  
  
  public boolean isElementDisplayed(By by) {
	  WebElement element = driver.findElement(by);
	  if (element.isDisplayed()) {
		System.out.println("Element [" + by + "] is displayed");
		//Nếu như hiển thị thì để là return true
		return true;
	} else {
		System.out.println("Element [" + by + "] is not displayed");
		//Nếu như không hiển thị để là return false
		return false;
	}
  }
  
  //By by, String value vì sendkey sẽ send 1 value
  
  public void senkeyToElement(By by, String value) {
	  WebElement element = driver.findElement(by);
	  element.clear();
	  element.sendKeys(value);
  }
  
  public void clickToElement(By by) {
	  WebElement element = driver.findElement(by);
	  element.click();
	  
  }
  
	
}





//@AfterMethod
//  public void Close_Browser() {
//	driver.quit();
//  }

