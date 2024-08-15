package com.sony.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sony.qa.base.TestBase;

public class HomePage extends TestBase {

	//Page Factory - PO
	@FindBy(id="boost-sd__search-widget-init-input-0")
	WebElement searchBar;
	
	@FindBy(xpath="//ul[@class='boost-sd__instant-search-product-list-items']/li")
	@CacheLookup
	List<WebElement> searchDropDownProducts;
	
	@FindBy(xpath="(//ul[@class='navmenu navmenu-depth-1'])[1]/li")
	@CacheLookup
	List<WebElement> headerMenuList;
	
	@FindBy(xpath = "(//ul[@class='payment-icons'])[1]/li")
	@CacheLookup
	List<WebElement> acceptedPaymentType;
	
	@FindBy(xpath = "(//div[@id='shopify-section-static-announcement'])[1]")
	@CacheLookup
	List<WebElement> ongoingSpecialOfferBar;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public int headerMenuTotalElement() {
		return headerMenuList.size();
	}
	
	public int searchProduct() throws InterruptedException {
		searchBar.sendKeys(prop.getProperty("searchProduct"));
		Thread.sleep(2000);
		return searchDropDownProducts.size();
	}
	
	public List<WebElement> acceptedPaymentType() {
		return acceptedPaymentType;
	}
	
	public List<WebElement> specialOfferDetails(){
		return ongoingSpecialOfferBar;
	}
}
