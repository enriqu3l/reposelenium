package testcases.hphoteles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import workflows.hphoteles.WHPHoteles;

public class TCHP_PTAll {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_PTAll.class);
	String gtestName = "";
	String gURL = "";
	String gbrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void prerequisitos(String url, String browser, ITestContext itc) {
		logger.info("***************************** Starting BeforeTest **********************************");
		Reporter.log("Starting BeforeTest");
		logger.info("Starting BeforeTest");
		
		gURL = url;
		logger.trace("URL Seteada: "+gURL);
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
		
		//Seleccionando el browser que se usara para las pruebas
		gbrowser = browser;
		logger.trace("Browser Seteado: "+gbrowser);
		Assert.assertFalse(gbrowser.equals(""),"No se ha seteado un browser valido!");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		logger.info("***************************** Starting BeforeMethod **********************************");
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.BROWSER_DEFAULT, gURL);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	/*
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}*/
	
	@Test (enabled=false, priority = 1)
	public void HPHotelDefault() throws InterruptedException{
		Reporter.log("Starting test HPHotelDefault");
		logger.info("Starting test HPHotelDefault");
		WHPHoteles.HPHotelDefault(driver);
	}
	
	@Test (enabled=true, priority = 2)
	public void HPHotelRandom() throws InterruptedException{
		Reporter.log("Starting test HPHotelRandom");
		logger.info("Starting test HPHotelRandom");
		WHPHoteles.HPHotelRandom(driver);
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
	}
}
