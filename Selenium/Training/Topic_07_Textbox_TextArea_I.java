package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class Topic_07_Textbox_TextArea_I {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String loginPage;
	String email, userID, password;
	String iName, iDob, iAddress, iCity, iState, iPin, iMobile, iEmail;
	String iGenderOutPut, iDobOutPut;
	String customerID;
	

	@BeforeClass
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		email = "test" + getRandomNumber() + "@gmail.com";
		
		iName = "Ronaldo";
		iGenderOutPut = "female";
		iDob = "01/01/1991";
		iDobOutPut = "1991-01-01";
		iAddress = "Califorlia";
		iCity ="Portugal";
		iState = "USA";
		iPin = "100000";
		iMobile = "2369851407";
		iEmail = "Ronaldo" + getRandomNumber() + "@gmail.vn";
	
	}

	@Test
	public void TC_01_Register_Account() {
		loginPage = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()=\"here\"]")).click();
		
		driver.switchTo().frame(driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0")));
			
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dismiss-button']")));
		
		driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
		
		
		driver.findElement(By.name("emailid")).sendKeys(email);
		
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[@class=\"accpage\" and text()=\"User ID :\"]/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[@class=\"accpage\" and text()=\"Password :\"]/following-sibling::td")).getText();
			
	}
	
	@Test
	public void TC_02_Login() {
		driver.get(loginPage);
		
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		driver.findElement(By.xpath("//marquee[@class=\"heading3\"]")).isDisplayed();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class=\"heading3\"]")).isDisplayed());
		
	}
	
	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(iName);
		
		driver.findElement(By.xpath("//input[@value='f']")).click();
		
		JavascriptExecutor  jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('type')",driver.findElement(By.name("dob")));
		driver.findElement(By.name("dob")).sendKeys(iDob);
		driver.findElement(By.name("addr")).sendKeys(iAddress);
		driver.findElement(By.name("city")).sendKeys(iCity);
		driver.findElement(By.name("state")).sendKeys(iState);
		driver.findElement(By.name("pinno")).sendKeys(iPin);
		driver.findElement(By.name("telephoneno")).sendKeys(iMobile);
		driver.findElement(By.name("emailid")).sendKeys(iEmail);
		driver.findElement(By.name("password")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
			
		//Verify thong tin sau khi New Customer thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3']")).isDisplayed());
			
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), iName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), iGenderOutPut);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), iDobOutPut);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), iAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), iCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), iState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), iPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), iMobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), iEmail);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
	}
	
//	@Test
//	public void TC_04_Edit_Customer() {
//		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
//		
//		driver.findElement(By.name("cusid")).sendKeys(customerID);
//		driver.findElement(By.name("AccSubmit")).click();
//		
//		String updateAddress = "test";
//		String updateCity = "test";
//		String updateState = "test";
//		String updatePin = "test";
//		String updateMobile = "test";
//		String updateEmail = "Ferguson" + getRandomNumber() + "@gmail.us";
//		
//		
//		driver.findElement(By.name("addr")).sendKeys(updateAddress);
//		driver.findElement(By.name("city")).sendKeys(updateCity);
//		driver.findElement(By.name("state")).sendKeys(updateState);
//		driver.findElement(By.name("pinno")).sendKeys(updatePin);
//		driver.findElement(By.name("telephoneno")).sendKeys(updateMobile);
//		driver.findElement(By.name("emailid")).sendKeys(updateEmail);
//		
//		//Verify thong tin sau khi edit thanh cong
//		
//		
//		
//	}

	@AfterClass
	public void afterMethod() {
//		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
		
	}
}