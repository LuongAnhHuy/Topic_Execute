package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.NewCustomerPageUI;

public class NewCustomerPageObject extends AbstractPage{
	WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToNameTextbox(String name) {
		waitToElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX, name);
	}

	public void inputToDOBTextbox(String dateOfBirth) {
		waitToElementVisible(driver, NewCustomerPageUI.DOB_TEXTBOX);
		removeAtributteInDOM(driver, NewCustomerPageUI.DOB_TEXTBOX, "type");
		sendkeyToElement(driver, NewCustomerPageUI.DOB_TEXTBOX, dateOfBirth);
	}

	public void inputToAddressTextArea(String address) {
		waitToElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTAREA);
		sendkeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXTAREA, address);
	}

	public void inputToCityTextbox(String city) {
		waitToElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, city);
	}

	public void inputToStateTextbox(String state) {
		waitToElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, state);
	}

	public void inputToPinTextbox(String pin) {
		waitToElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, pin);
	}

	public void inputToPhoneTextbox(String phone) {
		waitToElementVisible(driver, NewCustomerPageUI.MOBILE_NUMBER_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.MOBILE_NUMBER_TEXTBOX, phone);
	}

	public void inputToEmailTextbox(String email) {
		waitToElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		waitToElementVisible(driver, NewCustomerPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, NewCustomerPageUI.PASSWORD_TEXTBOX, password);
	}

	public void clickToSubmitButton() {
		waitToElementClickable(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewCustomerPageUI.SUBMIT_BUTTON);
	}

	public String getSuccessMessage() {
		waitToElementVisible(driver, NewCustomerPageUI.NEW_SUCCESS_MESSAGE);
		return getTextElement(driver, NewCustomerPageUI.NEW_SUCCESS_MESSAGE);
	}
	
	public void clickToLogOutLink() {
		waitToElementClickable(driver, NewCustomerPageUI.LOGOUT_LINK);
		clickToElement(driver, NewCustomerPageUI.LOGOUT_LINK);
	}



}
