package com.sony.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sony.qa.base.TestBase;

public class LoginPage extends TestBase {

	//Page Factory
	@FindBy(id="customer_email")
	WebElement username;
	
	@FindBy(id="customer_password")
	WebElement password;
	
	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	@CacheLookup
	WebElement loginButton;
	

	@FindBy(xpath = "//div[@class='account-message message--error']")
	@CacheLookup
	WebElement invalidLoginErr;
	
	@FindBy(className = "site-header-account-link")
	WebElement navigateToLoginPage;
	
	//Initializing Page Objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void openLoginPage() {
		navigateToLoginPage.click();
	}
	
	//Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public void login(String userName, String pwd) throws InterruptedException {
		Thread.sleep(2000);
		username.sendKeys(userName);
		password.sendKeys(pwd);
		Thread.sleep(2000);
		loginButton.click();
		Thread.sleep(2000);
	}
	
	public String readInvalidLoginErr() {
		String actualErr = invalidLoginErr.getText();
		return actualErr;
	}
	
	
}
