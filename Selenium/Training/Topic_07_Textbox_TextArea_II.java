package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Textbox_TextArea_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String userLogin, passLogin;
	String firstName, lastName, employeeID, username, password, confirmPassword;
	String confirmFirstName, confirmLastName, confirmID;
	String number, textArea, confirmNumber, confirmTextArea;

	@BeforeClass
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		userLogin = "Admin";
		passLogin = "admin123";
		firstName = "Ronaldo";
		lastName = "Cristiano";
		username = "test" + getRandomNumber() ;
		password = "test123456";
		confirmPassword = "test123456";
		number = "200699";
		textArea = "TestA \nTestB";
	}

  @Test
  public void TC_01_Login() {
	  //Step 2
	  driver.findElement(By.name("username")).sendKeys(userLogin);
	  driver.findElement(By.name("password")).sendKeys(passLogin);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  
	  //Step 3
	  driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='PIM']")).click();
	  
	  //Step 4
	  driver.findElement(By.xpath("//a[@class='oxd-topbar-body-nav-tab-item' and text() = 'Add Employee']")).click();
	  
	  //Step 5
	  driver.findElement(By.name("firstName")).sendKeys(firstName);
	  driver.findElement(By.name("lastName")).sendKeys(lastName);
	  
	  //Get value of EmployeeID field
	  employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/child::input[@class='oxd-input oxd-input--active']")).getAttribute("value");
	  System.out.println("employeeID:  " + employeeID);
	  
//	  driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	  driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
	  
	  driver.findElement(By.xpath("//label[@class='oxd-label oxd-input-field-required' and text()='Username']/parent::div/following-sibling::div/child::input")).sendKeys(username);
	  driver.findElement(By.xpath("//label[@class='oxd-label oxd-input-field-required' and text()='Password']/parent::div/following-sibling::div/child::input")).sendKeys(password);
	  driver.findElement(By.xpath("//label[@class='oxd-label oxd-input-field-required' and text()='Confirm Password']/parent::div/following-sibling::div/child::input")).sendKeys(confirmPassword);
	  
	  //Step 6
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  
	  //step 7
	  confirmFirstName = driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value");
	  confirmLastName = driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
	  confirmID = driver.findElement(By.xpath("//label[@class='oxd-label' and text() ='Employee Id']/parent::div/following-sibling::div/child::input")).getAttribute("value");
	  
	  Assert.assertEquals(confirmFirstName, firstName);
	  Assert.assertEquals(confirmLastName, lastName);
	  Assert.assertEquals(confirmID, employeeID);
	  
	  //Step 8
	  driver.findElement(By.xpath("//a[@class='orangehrm-tabs-item' and text() ='Immigration']")).click();
	  
	  //Step 9
	  driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title' and text()='Assigned Immigration Records']/following-sibling::button")).click();
	  
	  //Step 10
	  driver.findElement(By.xpath("//label[@class='oxd-label oxd-input-field-required']/parent::div/following-sibling::div/child::input")).sendKeys(number);
	  driver.findElement(By.xpath("//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")).sendKeys(textArea);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  
	  //Step 11
	  driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
	  
	  //Step 12
	  confirmNumber = driver.findElement(By.xpath("//label[@class='oxd-label oxd-input-field-required']/parent::div/following-sibling::div/child::input")).getAttribute("value");
	  System.out.println("Confirm Number: " + confirmNumber);
	  confirmTextArea = driver.findElement(By.xpath("//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")).getAttribute("value");
	  System.out.println("Confirm Text Area: " + confirmTextArea);
	  
//	  Assert.assertEquals(confirmNumber, number);
//	  Assert.assertEquals(confirmTextArea, textArea);
	  
	  //Step 13
	  driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
	  driver.findElement(By.xpath("//a[@role='menuitem' and text()='Logout']")).click();
	  
	  //Step 14
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(username);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  
	  //Step 15
	  driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='My Info']")).click();
	  
	  //Step 16
	  Assert.assertEquals(confirmFirstName, firstName);
	  Assert.assertEquals(confirmLastName, lastName);
	  Assert.assertEquals(confirmID, employeeID);
	  
	  //Step 17
	  driver.findElement(By.xpath("//a[@class='orangehrm-tabs-item' and text() ='Immigration']")).click();
	  driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
	  
	  //Step 18
	  Assert.assertEquals(confirmNumber, number);
	  Assert.assertEquals(confirmTextArea, textArea);
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
  }

}
