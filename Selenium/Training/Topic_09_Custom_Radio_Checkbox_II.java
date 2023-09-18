package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_09_Custom_Radio_Checkbox_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Custom_Radio () {
		
		// => Hàm isSelected chỉ verify được với thẻ input
		
		driver.get("https://material.angular.io/components/radio/examples");
		
		By customRadio = By.xpath("//input[@value='Winter']");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(customRadio));
		Assert.assertTrue(driver.findElement(customRadio).isSelected());
		
	}

	@Test
	public void TC_02_Custom_checkbox () {
		
		// => Hàm isSelected chỉ verify được với thẻ input
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
		By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");
		
		// 1 - Verify checkbox được chọn
		// => Hành vi: Nếu web mở ra mà checkbox không được chọn --> thì click để check to checkbox
		checkToCheckboxByJS(checkedCheckbox);
		checkToCheckboxByJS(indeterminateCheckbox);
		
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
		
		// 2 - Verify checkbox không được chọn
		// => Hành vi: Nếu web mở ra mà checkbox đã được chọn --> thì click để uncheck to checkbox
		uncheckToCheckboxByJS(checkedCheckbox);
		uncheckToCheckboxByJS(indeterminateCheckbox);
		
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
		
		
	}
	@Test
	public void TC_03_Bai_Tap () {
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By radioCanTho = By.xpath("//div[@data-value='Cần Thơ']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		Assert.assertEquals(driver.findElement(radioCanTho).getAttribute("aria-checked"), "false");
		
		checkToCheckboxByJS(radioCanTho);
		
		Assert.assertEquals(driver.findElement(radioCanTho).getAttribute("aria-checked"), "true");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
		List <WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
		
		// 1 - Click all
		// Dùng vòng lặp foreach
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
		}
		
		// 2 - Verify all
		// Dùng vòng lặp foreach
		for (WebElement checkbox : checkboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
			}
	}
	
		

	public void checkToCheckboxByJS (By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}	
	}
	
	public void uncheckToCheckboxByJS (By by) {
		if (driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
