package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import workflows.HPAllProducts;

public class HP_PTCOM_PROD {
	WebDriver driver;
	Logger logger = LogManager.getLogger(HP_PTCOM_PROD.class);
	
	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.DEFAULTBROWSER, FrameworkConfig.URL_PTCOM_PROD);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test
	public void HappyPath_HotelDefault() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelDefault");
		logger.info("Starting test HappyPath_HotelDefault");
		HPAllProducts.HPHotelDefault(driver);
	}
	
	/*
	@Test
	public void HappyPath_HotelRandom() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelRandom");
		logger.info("Starting test HappyPath_HotelRandom");
		HappyPathsWF.HPHotelRandom();
	}*/

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
