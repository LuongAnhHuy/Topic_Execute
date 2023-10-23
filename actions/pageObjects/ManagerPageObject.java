package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.ManagerPageUI;
import pageUI.NewCustomerPageUI;

public class ManagerPageObject extends AbstractPage {
	WebDriver driver;

	public ManagerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getWelcomeMessageText() {
		waitToElementVisible(driver, ManagerPageUI.WELCOME_TEXT);
		return getTextElement(driver, ManagerPageUI.WELCOME_TEXT);
	}

	public void openNewCustomerPage() {
		waitToElementVisible(driver, ManagerPageUI.NEW_CUSTOMER_LINK);
		clickToElement(driver, ManagerPageUI.NEW_CUSTOMER_LINK);
	}

	public void clickToLogOutLink() {
		waitToElementClickable(driver, NewCustomerPageUI.LOGOUT_LINK);
		clickToElementByJS(driver, NewCustomerPageUI.LOGOUT_LINK);
		//clickToElement(driver, NewCustomerPageUI.LOGOUT_LINK);
	}

}
