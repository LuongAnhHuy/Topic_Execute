package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_10_Alert_I {
	WebDriver driver;
	
	// Khai báo biến cho thư viện Alert
	Alert alert;
	
	// Khai báo explicitWait
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		
		// Khởi tạo explicitWait
		// Phải khởi tạo sau khi driver được new lên
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	// Có 3 loại alert
	// 1 - Accept Alert
	// 2 - Confirm Alert
	// 3 - Prompt Alert
	// 4 - Authentication Alert

	@Test
	public void TC_01_Accept_Alert () {
		driver.get("https://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.name("btnLogin")).click();
		
		// Wait cho alert xuất hiện trong vòng xx (là số giây của explicit wait) giây
		// Wait + Switch to alert
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// 1 - Switch to alert
		alert = driver.switchTo().alert();
		
		// 2 - Accept alert: alert sẽ biến mất (OK)
		alert.accept();
		
		// 3 - Cancel alert: alert sẽ biến mất (Cancel)
		alert.dismiss();
		
		// 4 - Get text of alert
		alert.getText();
		
		// 5 - Verify text in alert
		Assert.assertEquals(alert.getText(), "text");
		
		// 6 - sendkeys to alert
		alert.sendKeys("");
	}
	@Test
	public void TC_02_Confirm_Alert () {
	}
	
	@Test
	public void TC_03_Prompt_Alert () {
	}
	
	@Test
	public void TC_04_Authentication_Alert () {
		
		String id = "admin";
		String password = "admin";
		
		// Khởi tạo biến string cho url
		String url = "https://" + id + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		
		driver.get(url);
		
		// Verify message login successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		}
	

	@AfterMethod
	public void afterMethod() {
	}

}
