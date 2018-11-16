package TestCases;

import java.awt.AWTException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import FrameworkConfig.GeneralConfig;
import Helper.BrowserFactory;
import WorkFlows.HappyPathsWF;

public class BasicFunctionalTestSuite {
	
	@BeforeMethod
	public void beforeMethod() {
		//Set Browser
		BrowserFactory.StartBrowser(GeneralConfig.DEFAULTBROWSER, GeneralConfig.PRODENV);
	}
	
	@Test
	public void HappyPath_HotelDefault() throws InterruptedException, AWTException{		
		HappyPathsWF.HPHotelDefault();
	}
	
	/*
	@Test
	public void HappyPath_HotelRandom() throws InterruptedException, AWTException{		
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
