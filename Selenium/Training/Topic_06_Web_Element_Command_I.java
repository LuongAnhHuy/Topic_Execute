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

public class Topic_06_Web_Element_Command_I {
	WebDriver driver; 
	String projectPath = System.getProperty("user.dir");
  @BeforeMethod
  public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("http://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test
  public void f() {
	  //Khai báo biến cho element dùng nhiều lần
	  WebElement element = driver.findElement(By.id("email"));
	  
	  //Xóa dữ liệu trong textbox
	  element.clear();
	  
	  //Nhập dữ liệu vào editable field (textbox/ textarea/ dropdown)
	  element.sendKeys("huy12346"); //*
	  
	  //Click vào button/ link/ radio/ checkbox/ image/….
	  element.click();	//*
	  
	  //Trả về dữ liệu nằm trong attribute của element
	  element.getAttribute("placeholder");
	  element.getAttribute("value");	//*
	  
	  //Tên thẻ HTML
	  //Dùng By.id/ class/ css/ name
	  //Đầu ra của step này => đầu vào của step kia
	  element.getTagName();
	  element = driver.findElement(By.cssSelector("#save-info-button"));
	  String saveButtonTagName = element.getTagName();
	  driver.findElement(By.xpath("//" + saveButtonTagName + "[@name='email']"));
	  
	  //Lấy text của header/ link/ label/ error/ success message
	  element.getText();	//*
	  
	  //Kiểm tra 1 element có hiển thị hay không (hiện thị: người dùng nhìn thấy được và thao tác được)
	  element.isDisplayed();	//*
	  //Mong muốn hiển thị là True thì dùng assertTrue, muốn là False thì dùng assertFalse
	  Assert.assertTrue(element.isDisplayed());
	  
	  //Kiểm tra 1 element có thao tác được hay không (không bị disable/ không phải là readonly field)
	  element.isEnabled();	//*
	  
	  //Kiểm tra 1 element đã được chọn hay chưa (radio/ checkbox/ dropdown)
	  element.isSelected();	//*
	  
  }

  @AfterMethod
  public void afterMethod() {
  }

}
