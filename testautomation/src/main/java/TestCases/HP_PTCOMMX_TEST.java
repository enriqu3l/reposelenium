package TestCases;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import FrameworkConfig.GeneralConfig;
import Helper.BrowserFactory;
import WorkFlows.HappyPathsWF;

public class HP_PTCOMMX_TEST {
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() {
		//Set Browser
		BrowserFactory.StartBrowser(GeneralConfig.DEFAULTBROWSER, GeneralConfig.URL_PTCOMMX_TEST);
	}
	
	@Test
	public void HappyPath_HotelDefault() throws InterruptedException, AWTException{		
		HappyPathsWF.HPHotelDefault(driver);
	}
	
	/*
	@Test
	public void HappyPath_HotelRandom() throws InterruptedException{		
		HappyPathsWF.HPHotelRandom();
	}
	
	@Test
	public void HappyPath_destCol() throws InterruptedException{		
		
	}*/

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