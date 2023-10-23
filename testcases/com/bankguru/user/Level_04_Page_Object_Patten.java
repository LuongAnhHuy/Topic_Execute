package com.bankguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;
import pageObjects.LoginPageObject;
import pageObjects.ManagerPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.GetElementText;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_04_Page_Object_Patten extends AbstractPage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String loginPageUrl, userID, password;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	ManagerPageObject managerPage;
	NewCustomerPageObject newCustomerPage;
	
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Pre-conditions - Step 01: Open browser + Navigate to app url");
	  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://demo.guru99.com/v4/");
	  
	  loginPage = new LoginPageObject(driver);
  }

  @Test
  public void TC_01_Register_To_System () {
	  System.out.println("Register - Step 01: Get Login Page Url");
	  loginPageUrl = loginPage.getLoginPageUrl();
//	  loginPageUrl = getCurrentUrl(driver);
	  

	  
	  System.out.println("Register - Step 02: Click to 'here' link");
	  loginPage.clickToHereLink();
//	  clickToElement(driver, "//a[text()='here']");
	  
	  loginPage.closeIframe();
	  
	  registerPage = new RegisterPageObject(driver);
	  
	  System.out.println("Register - Step 03: Input to Email textbox");
	  registerPage.inputToEmailTextbox("automationfc@gmail.com");
//	  sendkeyToElement(driver, "//input[@name='emailid']", "automationfc@gmail.com");
	  
	  System.out.println("Register - Step 04: Click to Submit button");
	  registerPage.clickToSubmitButton();
//	  clickToElement(driver, "//input[@name='btnLogin']");
	  
	  System.out.println("Register - Step 05: Get UserID/ Password information");
	  userID = registerPage.getUserIDText();
	  password = registerPage.getPasswordText();  
//	  userID = getTextElement(driver, "//td[@class='accpage' and text() ='User ID :']//following-sibling::td");
//	  password = getTextElement(driver, "//td[@class='accpage' and text() ='Password :']//following-sibling::td");
  }
  
  @Test
  public void TC_02_Login_To_System () {
	  System.out.println("Login - Step 01: Open Login Page");
	  registerPage.openLoginPage(loginPageUrl);
//	  openUrl(driver, loginPageUrl);
	  
	  loginPage = new LoginPageObject(driver);
	  
	  System.out.println("Login - Step 02: Input to UserID/ Password textbox");
	  loginPage.inputToUserIDTextbox(userID);
	  loginPage.inputToPasswordTextbox(password);
//	  sendkeyToElement(driver, "//input[@name='uid']", userID);
//	  sendkeyToElement(driver, "//input[@name='password']", password);
	  
	  System.out.println("Login - Step 03: Click to Login button");
	  loginPage.clickToLoginButton();
//	  clickToElement(driver, "//input[@name='btnLogin']");
	  
	  managerPage = new ManagerPageObject(driver);
	  
	  System.out.println("Login - Step 04: Navigate to Home Page");
	  Assert.assertEquals(managerPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
//	  Assert.assertEquals(getTextElement(driver, "//marquee[@class='heading3']"), "Welcome To Manager's Page of Guru99 Bank");
  }
  
  @Test
  public void TC_03_New_Customer() {
	  System.out.println("NewCustomer - Step 01: Open new Customer Page");
	  managerPage.openNewCustomerPage();
	  newCustomerPage = new NewCustomerPageObject(driver);
	  
	  System.out.println("NewCustomer - Step 02: Input to Name textbox ");
	  newCustomerPage.inputToNameTextbox("Jonh Roonet");
	  
	  System.out.println("NewCustomer - Step 03: Input to Date of Birth textbox");
	  newCustomerPage.inputToDOBTextbox("01/01/1990");
	  
	  System.out.println("NewCustomer - Step 04: Input to Address area");
	  newCustomerPage.inputToAddressTextArea("226 PO Address");
	  
	  System.out.println("NewCustomer - Step 05: Input to City textbox");
	  newCustomerPage.inputToCityTextbox("Califolina");
	  
	  System.out.println("NewCustomer - Step 06: Input to State textbox");
	  newCustomerPage.inputToStateTextbox("NewYork");
	  
	  System.out.println("NewCustomer - Step 07: Input to Pin textbox");
	  newCustomerPage.inputToPinTextbox("567889");
	  
	  System.out.println("NewCustomer - Step 08: Input to Phone textbox");
	  newCustomerPage.inputToPhoneTextbox("0978687125");
	  
	  System.out.println("NewCustomer - Step 09: Input to Email textbox");
	  newCustomerPage.inputToEmailTextbox("automationfc" + randomNumber() + "@gmail.com");
	  
	  System.out.println("NewCustomer - Step 10: Input to Password textbox");
	  newCustomerPage.inputToPasswordTextbox("haha123456");
	  
	  System.out.println("NewCustomer - Step 11: Click to Submit button");
	  newCustomerPage.clickToSubmitButton();
	  
	  System.out.println("NewCustomer - Step 12: Verify success message");
	  Assert.assertEquals(newCustomerPage.getSuccessMessage(), "Customer Registered Successfully!!!");
  }
  
  @Test
  public void TC_O4_LogOut () {
	  System.out.println("LogOut - Step 01: Click to Logout link");
	  managerPage = new ManagerPageObject(driver);
	  managerPage.clickToLogOutLink();
	  
	  System.out.println("LogOut - Step 02: Accept Alert");
	  acceptAlert(driver);
	  
	  loginPage = new LoginPageObject(driver);
	  
	  System.out.println("LogOut - Step 02: Verify Login Form Displayed");
	  Assert.assertTrue(loginPage.isLoginFormDisplayed());
	  
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int randomNumber() {
	  Random rand = new Random();
	  return rand.nextInt(999);
  }

}
