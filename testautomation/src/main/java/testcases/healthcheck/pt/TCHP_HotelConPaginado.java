package testcases.healthcheck.pt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import tests.workflows.pt.WHPHotelConPaginado;

public class TCHP_HotelConPaginado {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_HotelConPaginado.class);
	
	@BeforeMethod
	public void beforeMethod(ITestContext itc) {
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.BROWSER_DEFAULT, FrameworkConfig.URL_PTCOMMX_PROD);
		itc.setAttribute("WebDriver", driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test
	public void HPHotelConCambioDePagina() throws InterruptedException{
		Reporter.log("Starting test HPHotelConCambioDePagina");
		logger.info("Starting test HPHotelConCambioDePagina");
		WHPHotelConPaginado.HPHotelConCambioDePagina(driver);
	}

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
		System.out.println("Test Finished");
	}
}
