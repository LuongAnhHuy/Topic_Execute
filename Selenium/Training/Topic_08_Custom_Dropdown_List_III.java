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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_08_Custom_Dropdown_List_III {
	WebDriver driver;
	
	// Khai báo explicitWait
	WebDriverWait explicitWait;
	
	// Khai báo JavascriptExecutor
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");

		driver = new ChromeDriver();
		// Driver ID
		
		//Wait để apply cho các trạng thái của element (visible/ invisible/ presence/ clickable, ...)
		// Visible: có thể nhìn thấy và thao tác đc
		// Invisible: không nhìn thấy ko thao tác đc
		// Presence: Không quan tâm có visible hay invisible hay không, chỉ quan tâm item xuất hiện trong HTML (DOM)
		explicitWait = new WebDriverWait(driver, 15);
		
		// Interface ko được new
		// cách khởi tạo jsExecutor = cách ép kiểu, ép kiểu driver qua jsExecutor
		// để có thể khởi tạo dữ liệu cho jsExecutor ép kiểu driver qua jsExecutor (Trong java gọi là ép kiểu tường minh(Reference casting))
		jsExecutor = (JavascriptExecutor) driver;
		
		//Wait để tìm element (findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// - WebDriverWait
	// - JavascripExecutor
	// - Vòng lặp for
	// - Biểu thức điều kiện If
	// - Do selenium không hỗ trợ scroll nên phải dùng JavascriptExecutor để hỗ trợ
	
	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// define biến
		By parent = By.xpath("//span[@id='number-button']");
		By child = By.xpath("//ul[@id='number-menu']/li/div");
		
		selectItemInDropdown(parent, child, "5");
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '5']")));
		
		selectItemInDropdown(parent, child, "19");
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '19']")));
		
		selectItemInDropdown(parent, child, "10");
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '10']")));
		
		selectItemInDropdown(parent, child, "15");
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '15']")));
		
	}

	@Test
	public void TC_02_ReactJS () {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		//Define biến
		By parentReact = By.xpath("//div[@role='listbox']");
		By childReact = By.xpath("//div[@role='listbox']//div/span");
		
		
		selectItemInDropdown(parentReact, childReact, "Christian");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Christian']")));
		
		selectItemInDropdown(parentReact, childReact, "Jenny Hess");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Jenny Hess']")));
		
		selectItemInDropdown(parentReact, childReact, "Stevie Feliciano");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Stevie Feliciano']")));
	}
	
	@Test
	public void TC_03_VueJS () {
		
	}
	
	@Test
	public void TC_04 () {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");		
		
		// Chờ cho cái loading icon biến mất trong vòng xx giây
		boolean itemLoading =  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='kd-loader']")));
		Assert.assertTrue(itemLoading);
		
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	// Tạo hàm select để có thể chọn nhiều giá trị mà ko bị lặp lại code
	public void selectItemInDropdown (By parentBy, By childBy, String expectedTextItem) {
				// 1 - click vào 1 element cho nó xổ ra tất cả item
				driver.findElement(parentBy).click();
				
				// 2 - Wait cho tất cả các element được load ra (có trong HTML/ DOM)
				// Dùng presence of all element để lấy hết các giá trị có trong dropdown list
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
				
				// Store lại tất cả element (item của dropdown))
				List <WebElement> allItems = driver.findElements(childBy) ;
				System.out.println("All item = " + allItems.size());
				
				for (WebElement item : allItems) {
					// Nếu getText trong list element đc và so sánh = 5
					// Dùng trim() để xóa cách khoảng trắng/ tab trước text để verify Equals
					if (item.getText().trim().equals(expectedTextItem)) {
						// Nếu item được get ở trên = 5 hiển thị thì click
						if (item.isDisplayed()) { // 3 - Nếu item mình cần chọn nằm trong view (nhìn thấy đc) thì click vào
							item.click();
							//Hoặc
						} else { // 4 - Nếu item mình cần chọn không nằm trong view ko nhìn thấy (che bên dưới) thì scroll xuống và click vào
							// Hàm scroll trong JavaExecutor
							jsExecutor.executeScript("argument[0].scrollIntoView(true);", item);
							item.click();
						}
					}
				}
	}
	public boolean isElementDisplayed(By by) {
		  WebElement element = driver.findElement(by);
		  if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			//Nếu như hiển thị thì để là return true
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			//Nếu như không hiển thị để là return false
			return false;
		}
	  }

}
