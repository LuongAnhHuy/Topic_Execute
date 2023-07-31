package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_08_Default_Dropdown_List_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	String firstName, lastName, day, month, year, email, password;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "An";
		lastName = "Nguyen";
		day = "1";
		month = "May";
		year = "1980";
		email = "nguyenan" + getRandomNumber() + "@gmail.com";
		password = "an123456";
	}

	@Test
	public void TC_01() {
		//Step 02
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		//Step 03
		driver.findElement(By.name("FirstName")).sendKeys(firstName);
		driver.findElement(By.name("LastName")).sendKeys(lastName);

		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		
		driver.findElement(By.name("Email")).sendKeys(email);
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.name("ConfirmPassword")).sendKeys(password);
		
		//Step 04
		driver.findElement(By.name("register-button")).click();
		
		//Step 05
		String loginSuccess = driver.findElement(By.xpath("//div[@class='result']")).getText();
		Assert.assertEquals(loginSuccess, "Your registration completed");
		
		//Step 06
//		if (isElementDisplayed(driver.findElement(By.xpath("//a[@class='ico-account']")))) {
//			clickToElement(driver.findElement(By.xpath("//a[@class='ico-account']")));
//			}
//		
//		if (isElementDisplayed(driver.findElement(By.xpath("//a[text()='My account']")))) {
//			clickToElement(driver.findElement(By.xpath("//a[text()='My account']")));
//			senkeyToElement(driver.findElement(By.name("Email")), email);
//			senkeyToElement(driver.findElement(By.name("Password")), password);
//			clickToElement(driver.findElement(By.xpath("//button[@class='button-1 login-button']")));
//			}
		
		
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		driver.findElement(By.name("Email")).sendKeys(email);
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
//		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		//Step 07
		String confirmDay = driver.findElement(By.name("DateOfBirthDay")).getAttribute("value");
		System.out.println("day: " + confirmDay);
		Assert.assertEquals(confirmDay, day);
		
		String confirmMonth = driver.findElement(By.name("DateOfBirthMonth")).getAttribute("value");
		System.out.println("month: " + confirmMonth);
		Assert.assertEquals(confirmMonth, "5");
		
		String confirmYear = driver.findElement(By.name("DateOfBirthYear")).getAttribute("value");
		System.out.println("year: " + confirmYear);
		Assert.assertEquals(confirmYear, year);
		
		
		
	}

	private void senkeyToElement(WebElement findElement, String email2) {
		// TODO Auto-generated method stub
		
	}

	private void clickToElement(WebElement findElement) {
		// TODO Auto-generated method stub
		
	}

	private boolean isElementDisplayed(WebElement findElement) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getRandomNumber () {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
