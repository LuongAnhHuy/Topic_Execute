package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextbox(String emailValue) {
		waitToElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void clickToSubmitButton() {
		waitToElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDText() {
		waitToElementVisible(driver, RegisterPageUI.USER_ID_TEXTBOX);
		return getTextElement(driver, RegisterPageUI.USER_ID_TEXTBOX);
	}

	public String getPasswordText() {
		waitToElementVisible(driver, RegisterPageUI.PASSWORD_ID_TEXTBOX);
		return getTextElement(driver, RegisterPageUI.PASSWORD_ID_TEXTBOX);
	}

	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
	}

}
