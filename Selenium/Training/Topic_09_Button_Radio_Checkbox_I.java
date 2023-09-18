package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_09_Button_Radio_Checkbox_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	// Khai báo JavascriptExecutor
	JavascriptExecutor jsExecutor;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Ép kiểu tường minh cho (jsExecutor) với (driver) vì cả 2 đều là InterFace
		// jsExecutor = () driver;
		jsExecutor = (JavascriptExecutor) driver;
	}
	

	@Test
	public void TC_01() {
		By loginButton = By.xpath("//button[@class='fhs-btn-login']");
		By errorMessageEmail = By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']");
		By errorMessagePassword = By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']");
		
		driver.get("https://www.fahasa.com/customer/account/create");
		
		// Xử lý iframe hiển thị
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
//		driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-1cx0z1q']")).click();
		
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		
		// Verify login Button is disable
		driver.findElement(loginButton).isEnabled();
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		//Input to Email, password
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
		
		// Verify login Button is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		// Refesh lai page
		driver.navigate().refresh();
		
		// Navigate lai Login page
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		
		// Remove disabled attribute của Login button
		// Javascript Executor
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));
		
		// Click to Login button
		driver.findElement(loginButton).click();
		
		// Verify error message hiển thị
		Assert.assertEquals(driver.findElement(errorMessageEmail).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(errorMessagePassword).getText(), "Thông tin này không thể để trống");
		
		// Verify login button with background color = RED
		String rgbaColor = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println("RGBA = " + rgbaColor);
		
		// Convert từ RGB --> Hexa = asHex(); toUpperCase() lấy ra kqua có chữ Hoa;
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		System.out.println("Hexa Color = " + hexaColor);
		
		Assert.assertEquals(hexaColor, "#C92127");
	}

	@Test
	public void TC_02_Default_Radio_button () {
		
		// Thẻ input: vừa click vừa verify với thẻ này ==> Default
		// Không click bằng thẻ input được vì nó bị ẩn đi ==> Custom 
		
		By petrol2 = By.xpath("//label[@for='engine3']/preceding-sibling::input");
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		// Verify radio button (Petrol2.0) is not selected
		// Chưa selected => trả ra False => assertFalse của False = True
		Assert.assertFalse(driver.findElement(petrol2).isSelected());
		
		driver.findElement(petrol2).click();
		
		// Verìy radio button (Petrol2.0) is selected
		Assert.assertTrue(driver.findElement(petrol2).isSelected());
	}
	
	@Test
	public void TC_03_Default_Checkbox () {
		
		// Thẻ input: vừa click vừa verify với thẻ này ==> Default
		// Không click bằng thẻ input được vì nó bị ẩn đi ==> Custom 
		
		By luggageCheckbox = By.xpath("//label[@for='eq3']/preceding-sibling::input");
		By rearCheckbox = By.xpath("");
		
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		// Verify checkbox is not selected
		Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());
		
		driver.findElement(luggageCheckbox).click();
		
		// Verify checkbox is selected
		Assert.assertTrue(driver.findElement(luggageCheckbox).isSelected());
		
		driver.findElement(luggageCheckbox).click();
		
		// Verify lại checkbox sau khi click lại checkbox đã được chọn
		Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());
		
		
	}
	
	// Viết hàm kiểm tra hành vi của checkbox
	public void checkToCheckbox (By by) {
		// Hành vi checkbox chưa được chọn
		// (!) phủ định của selected là ==> not selected
		if (!driver.findElement(by).isSelected()) {
			// Nếu là not selected ==> thì click ==> để check
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckbox (By by) {
		// Hành vi checkbox được chọn
		// Nếu checkbox được chọn rồi thì click ==> để uncheckbox
		if (driver.findElement(by).isSelected()) {
			// Nếu là selected ==> thì click ==> để uncheck
			driver.findElement(by).click();
		}
	}
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
