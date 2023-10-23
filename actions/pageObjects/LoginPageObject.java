package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	
	// - Constructor: Hàm khởi tạo (hàm dựng)
	// 1 - Sẽ được gọi đầu tiên khi cái class này được khởi tạo
	// 2 - Hàm khởi tạo ko có kiểu trả về (không có type: ko có void, string,...)
	// 3 - Hàm khởi tạo bắt buộc trùng tên class
	// 4 - 1 class có thể có nhiều hàm khởi tạo cũng được
	// Note: Hàm khởi tạo: khác nhau bởi số lượng tham số hoặc kiểu dữ liệu
	
	// - Tính đa hình/ đa hình thái
	// - 1 hàm khởi tạo có nhiều hình thái khác nhau
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}



	public void clickToHereLink() {
		waitToElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
	}
	
	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}


	public void inputToUserIDTextbox(String userID) {
		waitToElementVisible(driver, LoginPageUI.USER_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_TEXTBOX, userID);
	}
	
	public void inputToPasswordTextbox(String password) {
		waitToElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public void clickToLoginButton() {
		waitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}


	public boolean isLoginFormDisplayed() {
		waitToElementVisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);
	}



	public void closeIframe() {
		waitToElementVisible(driver, LoginPageUI.IFRAME);
		switchToFrame(driver, LoginPageUI.IFRAME);
		clickToElement(driver, LoginPageUI.CLOSE_IFRAME);
	}



}
