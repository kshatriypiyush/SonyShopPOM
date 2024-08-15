package com.sony.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;


import com.sony.qa.util.TestUtil;
import com.sony.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	static WebDriver originalDriver;
	public static Properties prop;
	static String currentDir = System.getProperty("user.dir");
	static WebEventListener listener;
	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream( currentDir + "\\src\\main\\java\\com\\sony\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("Firefox")) {
			originalDriver = new FirefoxDriver();
		} else if (browserName.equals("Chrome")) {
			originalDriver = new ChromeDriver();
		}

		//e_driver = new EventFiringWebDriver(driver);
		listener = new WebEventListener();
		driver = new EventFiringDecorator<>(listener).decorate(originalDriver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

		driver.get(prop.getProperty("homePageUrl"));

	}

}
