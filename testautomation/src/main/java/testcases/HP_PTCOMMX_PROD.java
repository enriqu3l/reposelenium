package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import workflows.HappyPathsWF;

public class HP_PTCOMMX_PROD {
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.DEFAULTBROWSER, FrameworkConfig.URL_PTCOMMX_PROD_);
		Reporter.log("Browser Started");
	}
	
	/*
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}*/
	
	@Test (enabled=true, priority = 2)
	public void HappyPath_HotelFailure() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelFailure");
		Assert.assertTrue(false); //Hacer fallar la prueba
	}
	
	@Test (enabled=true, priority = 1)
	public void HappyPath_HotelDefault() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelDefault");
		HappyPathsWF.HPHotelDefault(driver);
		//Assert.assertTrue(false, "Se Fallo a proposito jojojo!!!");
	}
	
	@Test (enabled=false, priority = 1)
	public void HappyPath_HotelRandom() throws InterruptedException{		
		HappyPathsWF.HPHotelRandom(driver);
	}

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
