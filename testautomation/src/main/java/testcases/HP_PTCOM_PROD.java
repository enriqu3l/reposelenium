package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import workflows.HappyPathsWF;

public class HP_PTCOM_PROD {
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.DEFAULTBROWSER, FrameworkConfig.URL_PTCOM_PROD);
		Reporter.log("Browser Started");
	}
	
	@Test
	public void HappyPath_HotelDefault() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelDefault");
		HappyPathsWF.HPHotelDefault(driver);
	}
	
	/*
	@Test
	public void HappyPath_HotelRandom() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelRandom");
		HappyPathsWF.HPHotelRandom();
	}*/

	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		driver.close();	
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		System.out.println("Test Finished");
	}
}
