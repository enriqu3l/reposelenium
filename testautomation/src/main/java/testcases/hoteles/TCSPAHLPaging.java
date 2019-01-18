package testcases.hoteles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import workflows.hphoteles.WHPHotelConPaginado;
import workflows.hphoteles.WHPHoteles;

public class TCSPAHLPaging {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCSPAHLPaging.class);
	
	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.BROWSER_DEFAULT, FrameworkConfig.URL_PTCOMMX_PROD);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test
	public void HappyPath_HotelDefault() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelDefault");
		logger.info("Starting test HappyPath_HotelDefault");
		WHPHotelConPaginado.HPHotelConCambioDePagina(driver);
	}

	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		//driver.close();	
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
		System.out.println("Test Finished");
	}
}
