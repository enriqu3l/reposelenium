package testbases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import config.FWConfig;
import helpers.BrowserFactory;
import helpers.DDManager;
import testcases.regression.pt.TCSPAHLWidget;

public class TBRegressionSPAHL {
	protected WebDriver driver;
	protected Logger logger = LogManager.getLogger(TCSPAHLWidget.class);

	@BeforeMethod
	public void prerequisites(ITestContext itc) {
		Reporter.log("Starting prerequisites");
		logger.info("Starting prerequisites");
		// String url = "https://www.pricetravel.com/hoteles/cancun-area?checkin=2019-02-01&checkout=2019-02-03&placetype=3&placeid=69364&source=SPA-Hotel-List&rooms=1&room1.adults=2&agekids=";
		// String url = "https://stage-spa.pricetravel.com/hoteles/cancun-area?room1.adults=2&room1.kids=0&room1.agekids=&checkin=2019%2F02%2F02&checkout=2019%2F02%2F04&rooms=1&adults=2&kids=0&agekids=&pdisplay=Canc%C3%BAn%20(y%20alrededores),%20M%C3%A9xico&placeid=69364&placetype=3&puri=cancun-area&quotelist=true&returningfromairport=&startingfromairport=&actiontype=1";
		String url = DDManager.getLandingPageHLDefault(FWConfig.FILE_HOTELLANDINGPAGEDATA);
		driver = BrowserFactory.startBrowser(FWConfig.BROWSER_DEFAULT, url);
		itc.setAttribute("WebDriver", driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}

	@AfterMethod
	public void Close() {
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		driver.quit();
	}

	@AfterTest
	public void End() {
		Reporter.log("Test Finished");
		logger.info("Test Finished");
	}}
