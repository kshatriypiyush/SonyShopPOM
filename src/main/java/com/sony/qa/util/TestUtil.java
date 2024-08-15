package com.sony.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sony.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	static String currentDir = System.getProperty("user.dir");
	public static String sheetName = "Data";
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	
	public static Xls_Reader getTestData(){
		Xls_Reader reader = new Xls_Reader(currentDir + "\\src\\main\\java\\com\\sony\\qa\\testdata\\testDataPOM.xlsx");
		return reader;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		// TODO Auto-generated method stub
		File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShotFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
}
