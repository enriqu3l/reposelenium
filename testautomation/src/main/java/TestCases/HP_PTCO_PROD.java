package TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import FrameworkConfig.GeneralConfig;
import Helper.BrowserFactory;
import WorkFlows.HappyPathsWF;

public class HP_PTCO_PROD {
	@BeforeMethod
	public void beforeMethod() {
		//Set Browser
		BrowserFactory.StartBrowser(GeneralConfig.DEFAULTBROWSER, GeneralConfig.URL_PTCO_PROD);
	}
	
	@Test
	public void HappyPath_HotelDefault() throws InterruptedException{		
		HappyPathsWF.HPHotelDefault();
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
		//BrowserFactory.driver.close();	
	}
	
	@AfterTest
	public void End()
	{
		System.out.println("Test Finished");
	}
}
