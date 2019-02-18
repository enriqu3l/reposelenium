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

import config.FWConfig;
import helpers.BrowserFactory;
import helpers.DDManager;
import pages.pt.PagesNew;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TCHP_Hotel {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_Hotel.class);
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
		driver = BrowserFactory.startBrowser(gbrowser, gURL);
		itc.setAttribute("WebDriver", driver);
		PagesNew.setDriver(driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	/*
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}*/
	
	@Test (enabled=true, priority = 1)
	public void HPHotelDefault(){
		Reporter.log("Starting test HPHotelDefault");
		logger.info("Starting test HPHotelDefault");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		PagesNew.homeWidget().searchHotel(voResData);
		PagesNew.homeWidget().clickSearchHotelButton();
		PagesNew.hotelListPage().listSelectFirstHotelAvailable();
		PagesNew.roomListPage().selectFirstRoom();
		PagesNew.resDetailPage().clearAndFillForm(voClient);
		//PagesNew.resDetailPage().clickOnContinue();
		//PagesNew.payMethodPage().fillCreditForm(voCreditCard);
		//PagesNew.payMethodPage().clickOnCompleteReservation();
		//PagesNew.thankYouPage().verifyCheckOutCompleted();
	}
	
	@Test (enabled=false, priority = 2)
	public void HPHotelRandom(){
		Reporter.log("Starting test HPHotelRandom");
		logger.info("Starting test HPHotelRandom");
		VOResData voResData = DDManager.getResDataRandom(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		PagesNew.homeWidget().searchHotel(voResData);
		PagesNew.homeWidget().clickSearchHotelButton();
		PagesNew.hotelListPage().listSelectFirstHotelAvailable();
		PagesNew.roomListPage().selectFirstRoom();
		PagesNew.resDetailPage().clearAndFillForm(voClient);
		//PagesNew.resDetailPage().clickOnContinue();
		//PagesNew.payMethodPage().fillCreditForm(voCreditCard);
		//PagesNew.payMethodPage().clickOnCompleteReservation();
		//PagesNew.thankYouPage().verifyCheckOutCompleted();
	}
	
	@Test (enabled=false, priority = 3)
	public void HPHotelHPHotelUsingDataRow(){
		Reporter.log("Starting test HPHotelHPHotelUsingDataRow");
		logger.info("Starting test HPHotelHPHotelUsingDataRow");
		VOResData voResData = DDManager.getResData(FWConfig.FILE_HPHOTELRESDATA, 11);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		PagesNew.homeWidget().searchHotel(voResData);
		PagesNew.homeWidget().clickSearchHotelButton();
		PagesNew.hotelListPage().listSelectFirstHotelAvailable();
		PagesNew.roomListPage().selectFirstRoom();
		PagesNew.resDetailPage().clearAndFillForm(voClient);
		//PagesNew.resDetailPage().clickOnContinue();
		//PagesNew.payMethodPage().fillCreditForm(voCreditCard);
		//PagesNew.payMethodPage().clickOnCompleteReservation();
		//PagesNew.thankYouPage().verifyCheckOutCompleted();
	}

	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		driver.quit();
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
	}
}
