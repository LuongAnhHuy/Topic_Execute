package commons;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	// - Define 1 biến là WebDriverWait
	private WebDriverWait explicitWait;
	
	// - Define thư viện Alert
	private Alert alert;
	
	
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
	
	public By byXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement find(WebDriver driver, String locator ) {
		return driver.findElement(byXpath(locator));
	}
	
	public List <WebElement> finds(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}
	
	public void clickToElement (WebDriver driver, String locator) {
		find(driver, locator).click();
		
	}
	
	}
