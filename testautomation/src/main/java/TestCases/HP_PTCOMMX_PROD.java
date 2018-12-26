package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import Config.FrameworkConfig;
import Helper.BrowserFactory;
import WorkFlows.HappyPathsWF;

public class HP_PTCOMMX_PROD {
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() {
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.DEFAULTBROWSER, FrameworkConfig.URL_PTCOMMX_PROD_);
	}
	
	/*
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}*/
	
	@Test (enabled=false, priority = 1)
	public void HappyPath_HotelFailure() throws InterruptedException{		
		HappyPathsWF.HPHotelDefault(driver);
	}
	
	@Test (enabled=true, priority = 1)
	public void HappyPath_HotelDefault() throws InterruptedException{		
		HappyPathsWF.HPHotelDefault(driver);
		Assert.assertTrue(false, "Se Fallo a proposito jojojo!!!"); //Fallamos a proposito el test case
	}
	
	@Test (enabled=false, priority = 1)
	public void HappyPath_HotelRandom() throws InterruptedException{		
		HappyPathsWF.HPHotelRandom(driver);
	}
	
	@Test (enabled=false, priority = 1)
	public void HappyPath_destCol() throws InterruptedException{		
		
	}

	@AfterMethod
	public void Close()
	{
		driver.close();
	}
	
	@AfterTest
	public void End()
	{
		System.out.println("Test Finished");
	}

}
