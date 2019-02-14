package testcases.healthcheck.pt;

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
import tests.workflows.pt.WHPHoteles;
import tests.workflows.pt.WHPPaquetes;

public class TCHP_Paquete {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_Paquete.class);
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
		driver = BrowserFactory.StartBrowser(gbrowser, gURL);
		itc.setAttribute("WebDriver", driver);
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
		WHPPaquetes.hpPackageDefault(driver);
	}
	
	@Test (enabled=false, priority = 2)
	public void HPHotelRandom() throws InterruptedException{
		Reporter.log("Starting test HPHotelRandom");
		logger.info("Starting test HPHotelRandom");
		WHPPaquetes.hpPackageRandom(driver);
	}
	
	@Test (enabled=true, priority = 3)
	public void HPHotelHPHotelUsingDataRow() throws InterruptedException{
		Reporter.log("Starting test HPHotelHPHotelUsingDataRow");
		logger.info("Starting test HPHotelHPHotelUsingDataRow");
		WHPPaquetes.hpPackageUsingDataRow(driver, 11);
	}

	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		//driver.close(); //Close current browser window
		//driver.quit(); //Close all browser windows
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
	}
}