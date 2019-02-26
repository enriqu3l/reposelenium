package testbases.pt;

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

import helpers.BrowserFactory;
import pages.pt.Pages;

public class TB_Smook {
	protected WebDriver driver;
	protected Logger logger = LogManager.getLogger(TB_Smook.class);
	protected String gtestName;
	protected String gURL = "";
	protected String gbrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void setup(String url, String browser, ITestContext itc) {
		logger.info("************************* Starting BeforeTest ******************************");
		Reporter.log("Starting BeforeTest");
		logger.info("Starting BeforeTest");
		gURL = url;
		gbrowser = browser;
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
		Assert.assertFalse(gbrowser.equals(""),"No se ha seteado un browser valido!");
		logger.trace("URL Seteada: "+gURL);
		logger.trace("Browser Seteado: "+gbrowser);
		driver = BrowserFactory.startBrowser(gbrowser, gURL);
		itc.setAttribute("WebDriver", driver);
		Pages.setDriver(driver);
	}
	
	@BeforeMethod
	public void BeforeMethod(ITestContext itc) {
		logger.info("Starting BeforeMethod");
	}

	@AfterMethod
	public void AfterMethod(ITestContext itc)
	{
		logger.info("Starting BeforeMethod");
	}
	
	@AfterTest
	public void Close()
	{
		Reporter.log("Closing Browser");
		logger.info("Closing Browser");
		driver.quit();
	}
}