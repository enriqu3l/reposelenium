package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Config.FrameworkConfig;
import Helper.BrowserFactory;
import Utility.BasicUtils;
import WorkFlows.HappyPathsWF;

public class HP_PTCOMMX_TEST {
	WebDriver driver;
	//Aun no funciona para reservas en colombia
	
	@BeforeMethod
	public void beforeMethod() {
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.DEFAULTBROWSER, FrameworkConfig.URL_PTCOMMX_TEST);
	}
	
	
	@Test
	public void HappyPath_HotelDefault() throws InterruptedException{		
		HappyPathsWF.HPHotelDefault(driver);
	}
	
	/*
	@Test
	public void HappyPath_HotelRandom() throws InterruptedException{		
		HappyPathsWF.HPHotelRandom(driver);
	}*/
	
	/*
	@Test
	public void HappyPath_destCol() throws InterruptedException{		
		
	}*/

	@AfterMethod
	public void Close()
	{
		//driver.close();	
	}
	
	@AfterTest
	public void End()
	{
		System.out.println("Test Finished");
	}

}
