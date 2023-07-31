package Training;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		//Neu la mutiple thi tra ve la true, ko tra ve la false
		select.isMultiple();
		Assert.assertFalse(select.isMultiple());
		
		//Kiem tra xem da chon dung item A chua
		select.getFirstSelectedOption().getText();
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		
		//Get ra tong so item trong dropdown la bao nhieu items => Verify bang bao nhieu
		select.getOptions().size();
		Assert.assertEquals(select.getOptions().size(), 32);
		
		// Dễ Fail
		driver.findElement(By.xpath("")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("")).getText(), "29");
		
		// 1 - driver.findElement(By.xpath("")) -> Founded
		// 2 - getText();
		// 3 - assertEquals
		
		// Dễ pass hơn 
		Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
		// 1 - driver.findElement(By.xpath("//div[@class='result_count']/span[text()='29']"))
		// Chưa tìm thấy: Chờ tiếp - mỗi nửa giây nó lại tìm 1 lần cho đến khi hết timeout (implicitlyWait)
		// Nếu thấy
		// 2 - Check isDisplayed() -> True/False
		// 3 - assertTrue
		
		List<WebElement> storeName = driver.findElements(By.xpath(""));
		Assert.assertEquals(storeName.size(), 29);
		
		// foreach để in ra list được lấy ở search result (29 địa chỉ) ở step trên
		for (WebElement store : storeName) {
			System.out.println(store.getText());
			
		}
		
		// Behavior (hành vi) của 1 dropdown như nhau - end user: 
		// 1 - Click vào 1 element cho nó xổ ra các item bên trong
		// 2 - Chờ cho tất cả các item được load lên thành công
		// 3 - Nếu item mình chọn nằm trong cái view (có thể nhìn thấy đc) -> thì click vào 
		// 4 - Nếu item mình cần chọn không nhìn thấy (che bên dưới) thì scroll xuống và click vào
		
	}

	@AfterClass
	public void afterMethod() {
	}

}
