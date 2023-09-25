package commons;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	// - Define 1 biến là WebDriverWait
	private WebDriverWait explicitWait;
	
	// - Define thư viện Alert
	private Alert alert;
	
	// - Define thư viện Select
	private Select select;
	
	// - Define thư viện JavaExecutor
	private JavascriptExecutor jsExecutor;
	
	// - Define thư viện Actions
	private Actions action;
	
	
	// define ra biến url, => tránh việc fix cứng url làm sai ý nghĩa của commons
	public void openUrl (WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getPageTitle (WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrl (WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void backToPage (WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refreshToPage (WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void forwardToPage (WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void waitToAlertPresence (WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert (WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void cancelAlert (WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public void sendkeyToAlert (WebDriver driver, String value) {
		alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}
	
	
	public String getTextInAlert (WebDriver driver) {
		alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	// Xpath
	public By byXpath(String locator) {
		return By.xpath(locator);
	}
	
	
	// Find Element
	public WebElement find(WebDriver driver, String locator ) {
		return driver.findElement(byXpath(locator));
	}
	
	// Find list Elements
	public List <WebElement> finds(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}
	
	
	// Click To Element
	public void clickToElement (WebDriver driver, String locator) {
		find(driver, locator).click();
	}
	
	// Senkey to Element
	public void sendkeyToElement (WebDriver driver, String locator, String value) {
		find(driver, locator).clear();
		find(driver, locator).sendKeys(value);
	}
	
	// Select Item in Dropdow List
	public void selectItemInDropdow (WebDriver driver, String locator, String itemValue) {
		select = new Select(find(driver, locator));
		select.selectByVisibleText(itemValue);
	}
	
	// Get text sau khi được select trong Dropdown
	public String getFirstSelectedItemInDropdow (WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	// Verify Dropdown list là Multiple 
	public boolean isMultipleDropdown (WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.isMultiple();
	}
	
	// Select item in custom dropdown list
	public void selectItemInCustomDropdown (WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		// 1 - Click vào thẻ (cha) để xổ ra tất cả các item con
			find(driver, parentLocator).click();
			
		// 2 - Chờ cho tất cả các item con được load ra
			explicitWait = new WebDriverWait(driver, 20);
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childLocator)));
			
		// 3 - Đưa tất cả các item trong Dropdown vào 1 list để kiểm tra
			List <WebElement> allItems = finds(driver, childLocator);
			
			// 4 - Chạy qua tất cả các giá trị đang có trong list
			for (WebElement item : allItems) {
				// 5 - Kiểm tra xem text của các giá trị có item nào bằng với text mong muốn không
				if (item.getText().equals(expectedItem)) {
					// 6 - Scroll xuống đến đúng item này
					jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					
					// 7 - Click vào item này
					item.click();
					// 8 - Thoát vòng lặp
					break;
				}	
			}
	}
	
	
	// Get attribute value
	public String getElementAttribute (WebDriver driver, String locator, String attributeValue) {
		return find(driver, locator).getAttribute(attributeValue);
	}
	// Get text
	public String getTextElement (WebDriver driver, String locator) {
		return find(driver, locator).getText();
	}
	
	// Tính tổng element giống nhau có trong page đấy
	public int countElementsSize (WebDriver driver, String locator) {
		return finds(driver, locator).size();
	}
	
	// Check to Checkbox
	public void checkToCheckbox (WebDriver driver, String locator) {
		// Phủ định (!) của isSelected là isUnSelected
		if (!find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}
	
	// Uncheck to Checkbox
	public void uncheckToCheckbox (WebDriver driver, String locator) {
		// Nếu đã selected rồi thì click thêm 1 lần nữa để uncheck
		if (find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}

	// isDisplay
	public boolean isControlDisplay (WebDriver driver, String locator) {
		return find(driver, locator).isDisplayed();
	}
	
	// isEnabled
	public boolean isControlEnable (WebDriver driver, String locator) {
		return find(driver, locator).isEnabled();
	}
	
	// isSelected
	public boolean isControlSelected (WebDriver driver, String locator) {
		return find(driver, locator).isSelected();
	}
	
	// Tương tác với Frame/ iFrame
	public void switchToFrame (WebDriver driver, String locator) {
		driver.switchTo().frame(find(driver, locator));
	}
	// Chuyển về defaultPage để tương tác sau khi switch qua Frame để tương tác với nó
	public void switchToDefaultPage (WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	// Double click: mô phỏng hành vi của người dùng = hàm Action (Khai báo thư viện Actions)
	public void doubleClickToElement (WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(find(driver, locator)).perform();
	}
	
	// Right click: mô phỏng hành vi của người dùng = hàm Action (Khai báo thư viện Actions)
	public void rightClickToElement (WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(find(driver, locator)).perform();
	}
	
	// Hover to Element: mô phỏng hành vi của người dùng = hàm Action (Khai báo thư viện Actions)
	public void hoverToElement (WebDriver driver, String locator) {
			action = new Actions(driver);
			action.moveToElement(find(driver, locator)).perform();
		}
	
	// Drag And Drop to Element: mô phỏng hành vi của người dùng = hàm Action (Khai báo thư viện Actions)
	public void dragAndDropElement (WebDriver driver, String locator, String sourceLocator, String targetLocator) {
			action = new Actions(driver);
			action.dragAndDrop(find(driver, sourceLocator), find(driver, targetLocator)).perform();;
		}	
	
	// Send keyBoard to Element: mô phỏng hành vi của người dùng = hàm Action (Khai báo thư viện Actions)
	public void sendKeyBoardToElement (WebDriver driver, String locator, Keys key) {
			action = new Actions(driver);
			action.sendKeys(find(driver, locator), key).perform();
	}
	
	}
