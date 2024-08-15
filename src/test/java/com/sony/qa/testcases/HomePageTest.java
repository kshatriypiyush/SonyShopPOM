package com.sony.qa.testcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sony.qa.base.TestBase;
import com.sony.qa.pages.HomePage;
import com.sony.qa.util.TestUtil;
import com.sony.qa.util.Xls_Reader;

public class HomePageTest extends TestBase {

	HomePage homePage;
	Xls_Reader data;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		data = TestUtil.getTestData();
		homePage = new HomePage();
	}
	
	
	@Test(priority = 1)
	public void homePageTitleTest() {
		String Title = homePage.validateHomePageTitle();
		Assert.assertEquals(Title, data.getCellData(TestUtil.sheetName, "PageTitle", 3));
	}
	
	
	@Test(priority = 2)
	public void verifyTotalElementInHeaderMenu() {
		int actualTotalElement = homePage.headerMenuTotalElement();
		Double expectedNumberOfElement = Double.valueOf(data.getCellData(TestUtil.sheetName, "TotalElements", 3));
		Assert.assertEquals(actualTotalElement, expectedNumberOfElement);
	}
	
	@Test(priority = 3)
	public void numberOfSuggestionInSearchForHeadset() throws InterruptedException {
		int actualSuggestedNumber = homePage.searchProduct();
		Double expectedNumberOfSearchProduct = Double.valueOf(data.getCellData(TestUtil.sheetName, "TotalElements", 4));
		Assert.assertEquals(actualSuggestedNumber, expectedNumberOfSearchProduct);
	}
	
	@Test(priority = 4)
	public void totalNumberOfPaymentMethodAvailable() {
		int actualNumberOfPaymentMethod = homePage.acceptedPaymentType().size();
		Double expectedNumberOfPaymentMethod = Double.valueOf(data.getCellData(TestUtil.sheetName, "TotalElements", 2));
		Assert.assertEquals(actualNumberOfPaymentMethod, expectedNumberOfPaymentMethod);
	}
	
	
	@Test(priority = 5)
	public void isTheirAnySpecialOfferGoingOn() {
		Boolean specialOfferIsThere = false;
		if(homePage.specialOfferDetails().size()>0) {
			specialOfferIsThere = true;
		}
		Assert.assertEquals(specialOfferIsThere, true);
	}
	
	
	@Test( priority = 6, dependsOnMethods = "isTheirAnySpecialOfferGoingOn")
	public void verifySpecialOfferDetails() {
		String actualOffer = "";
		for(WebElement wb: homePage.specialOfferDetails()) {
			actualOffer = wb.getText();
		}
		Assert.assertEquals(actualOffer, data.getCellData(TestUtil.sheetName, "Offer", 2));
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
