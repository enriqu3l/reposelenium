package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import workflows.WHPAllProducts;

public class TCHP_PTCOMMX_PROD {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_PTCOMMX_PROD.class);
	
	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.BROWSER_DEFAULT, FrameworkConfig.URL_PTCOMMX_PROD);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	/*
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}*/
	
	@Test (enabled=true, priority = 1)
	public void HappyPath_HotelDefault() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelDefault");
		logger.info("Starting test HappyPath_HotelDefault");
		WHPAllProducts.HPHotelDefault(driver);
	}
	
	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		driver.close();
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
	}
}
