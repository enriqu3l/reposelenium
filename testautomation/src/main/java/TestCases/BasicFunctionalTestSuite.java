package TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Helper.BrowserFactory;
import WorkFlows.HappyPathsWF;

public class BasicFunctionalTestSuite {
	
	@BeforeMethod
	public void beforeMethod() {
		//This will launch browser and specific url
		//BrowserFactory.StartBrowser("chrome", "https://www.pricetravel.com.mx");
		BrowserFactory.StartBrowser("chrome", "https://www.priceres.co/quest-group");
		
	}
	
	@Test
	public void HappyPath_default() throws InterruptedException{		
		HappyPathsWF.HPHotelDefault();
	}
	
	/*@Test
	public void HappyPath_destUSA() throws InterruptedException{		
		
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
