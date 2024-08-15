package com.sony.qa.testcases;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sony.qa.base.TestBase;
import com.sony.qa.pages.LoginPage;
import com.sony.qa.util.TestUtil;
import com.sony.qa.util.Xls_Reader;
public class LoginPageTest extends TestBase {

	LoginPage loginpage;
	Xls_Reader data;
	public LoginPageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		data = TestUtil.getTestData();
		loginpage = new LoginPage();
		Thread.sleep(2000);
		loginpage.openLoginPage();
	}
	
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, data.getCellData(TestUtil.sheetName, "PageTitle", 2));
	}
	
	@Test(priority = 2)
	public void loginTestWrongCredentials() throws InterruptedException {
		loginpage.login(prop.getProperty("wrongUsername"), prop.getProperty("wrongPassword"));
		String actualErr = loginpage.readInvalidLoginErr();
		Assert.assertEquals(actualErr, data.getCellData(TestUtil.sheetName, "Errors", 2));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
}
