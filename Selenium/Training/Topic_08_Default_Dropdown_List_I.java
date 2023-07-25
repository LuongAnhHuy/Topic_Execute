package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class Topic_08_Default_Dropdown_List_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	//Khai bao thu vien Select
	Select select;
	

	@BeforeClass
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Tuong_Tac_Voi_Dropdown_List() {
		
		//New Select len voi element de tuong tac
		//Chi dc new khi element co mat tai man hinh
		select = new Select(driver.findElement(By.xpath("")));
		
		//Chon 1 item
		select.selectByIndex(0);		
		select.selectByValue("1");
		select.selectByVisibleText("1");
		
		//Bo chon 1 item
		
		//Kiem tra dropdown nay co phai multiple select(1 cai dropdown cho phep chon nhieu gia tri cung 1 luc) ko
		select.isMultiple();
		Assert.assertFalse(select.isMultiple());
		
		//Kiem tra xem da chon dung item A chua
		select.getFirstSelectedOption().getText();
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		
		//Get ra tong so item trong dropdown la bao nhieu items => Verify bang bao nhieu
		select.getOptions().size();
		Assert.assertEquals(select.getOptions().size(), 32);
		
		
	}

	@AfterClass
	public void afterMethod() {
	}

}
