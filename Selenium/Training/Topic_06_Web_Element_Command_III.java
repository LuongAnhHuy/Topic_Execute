package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.http.Syntax;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_06_Web_Element_Command_III {
	private static final WebElement String = null;
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String iFirstName, iLastName, iEmail, iPassword;
	
	By myAccountButton = By.xpath("//div[@class=\"footer-container\"]//a[@title=\"My Account\"]");
	By createAnAccount = By.xpath("//div[@class=\"main-container col1-layout\"]//a[@title=\"Create an Account\"]");
	By firstName = By.id("firstname");
	By lastName = By.id("lastname");
	By email = By.id("email_address");
	By password = By.id("password");
	By confirmPassword = By.id("confirmation");
	By register = By.xpath("//button[@title=\"Register\"]");

	
	@BeforeMethod
	public void Open_Browser() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		iFirstName = "Huy";
		iLastName = "Luong Anh";
		iEmail ="huy" + getRandomNumber() + "@gmail.com";
		iPassword = "huy123456";
	}

	@Test
	public void TC_01_Register() {
		//Step 2
		driver.findElement(myAccountButton).click();
		
		//Step 3
		driver.findElement(createAnAccount).click();
		
		//Step 4
		driver.findElement(firstName).sendKeys(iFirstName);
		driver.findElement(lastName).sendKeys(iLastName);
		driver.findElement(email).sendKeys(iEmail);
		driver.findElement(password).sendKeys(iPassword);
		driver.findElement(confirmPassword).sendKeys(iPassword);
		
		//Step 5
		driver.findElement(register).click();
		
		//Step 6
		driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span[text()=\"Thank you for registering with Main Website Store.\"]")).isDisplayed();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span[text()=\"Thank you for registering with Main Website Store.\"]")).isDisplayed());
		
		//Step 7
		driver.findElement(By.xpath("//div[@class=\"page-title\"]//h1")).isDisplayed();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"page-title\"]//h1")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class=\"welcome-msg\"]//strong")).isDisplayed();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"welcome-msg\"]//strong")).isDisplayed());
		
		//Verify Information full name + email
		String Info = driver.findElement(By.xpath("//h3[text()=\"Contact Information\"]/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(Info.contains(iFirstName + " " + iLastName ));
		Assert.assertTrue(Info.contains(iEmail));
		
		
		//Step 8: Log Out
		driver.findElement(By.xpath("//div[@class=\"account-cart-wrapper\"]//span[text()=\"Account\"]")).click();
		driver.findElement(By.xpath("//a[@title=\"Log Out\"]")).click();
		
		//Step 9: Verify logout successfully
		WebDriverWait wait = new WebDriverWait(driver, 10); //20 seconds
		wait.until(ExpectedConditions.urlToBe("http://live.techpanda.org/index.php/"));
		
	
		String title = driver.getTitle();
		String expectTitle = "Home page";
		Assert.assertEquals(title, expectTitle);
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
		
		
	}
}
