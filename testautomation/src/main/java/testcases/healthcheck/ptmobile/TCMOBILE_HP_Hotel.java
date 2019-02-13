package testcases.healthcheck.ptmobile;

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

import helpers.MobileBrowserFactory;
import tests.workflows.pt.WHPHoteles;

public class TCMOBILE_HP_Hotel {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCMOBILE_HP_Hotel.class);
	String gtestName = "";
	String gURL = "";
	String gbrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void setConfigVariables(String url, String browser, ITestContext itc) {
		logger.info("***************************** Starting BeforeTest **********************************");
		Reporter.log("Starting BeforeTest");
		logger.info("Starting BeforeTest");
		gURL = url;
		gbrowser = browser;
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
		Assert.assertFalse(gbrowser.equals(""),"No se ha seteado un browser valido!");
		logger.trace("URL Seteada: "+gURL);
		logger.trace("Browser Seteado: "+gbrowser);
	}
	
	@BeforeMethod
	public void prerequisites(ITestContext itc) {
		logger.info("***************************** Starting BeforeMethod **********************************");
		Reporter.log("Starting Browser");
		//Set Browser
		//driver = MobileBrowserFactory.StartBrowser("NEXUS_5X_API_24","7.0",gbrowser, gURL);
		driver = MobileBrowserFactory.StartBrowser("Samsung Galaxy S6","5.1",gbrowser, gURL);
		itc.setAttribute("WebDriver", driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test (enabled=true, priority = 1)
	public void HPHotelDefault(){
		Reporter.log("Starting test HPHotelDefault");
		logger.info("Starting test HPHotelDefault");
		WHPHoteles.HPHotelDefault(driver);
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
	}
}
