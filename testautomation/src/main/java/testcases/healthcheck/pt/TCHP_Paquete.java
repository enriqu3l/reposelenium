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
import pages.pt.Pages;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TCHP_Paquete {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_Paquete.class);
	String gtestName = "";
	String gURL = "";
	String gbrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void setup(String url, String browser, ITestContext itc) {
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
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	/*
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}*/
	
	@Test (enabled=false, priority = 1)
	public void HPHotelDefault(){
		Reporter.log("Starting test HPHotelDefault");
		logger.info("Starting test HPHotelDefault");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPPACKAGERESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.home_Initialize();
		pages.home.widget.searchPackage(voResData);
		pages.home.widget.clickSearchPackageButton();
		pages.packageList_Initialize();
		pages.packageList.listSelectFirstHotelAvailable();
		pages.browser.switchToNewTabIfOpened();
		pages.packageRoomList_Initialize();
		pages.packageRoomList.selectFirstRoom();
		pages.packageResDetail_Initialize();
		pages.packageResDetail.clearAndFillForm(voClient);
		pages.packageResDetail.clickContinue();
		pages.payMethod_Initialize();
		pages.payMethod.fillCreditForm(voCreditCard);
		//pages.payMethod.clickOnCompleteReservation();
		//pages.thankYou_Initialize();
		//pages.thankYou.verifyCheckOutCompleted();
	}
	
	@Test (enabled=false, priority = 2)
	public void HPHotelRandom(){
		Reporter.log("Starting test HPHotelRandom");
		logger.info("Starting test HPHotelRandom");
		VOResData voResData = DDManager.getResDataRandom(FWConfig.FILE_HPPACKAGERESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.home_Initialize();
		pages.home.widget.searchPackage(voResData);
		pages.home.widget.clickSearchPackageButton();
		pages.packageList_Initialize();
		pages.packageList.listSelectFirstHotelAvailable();
		pages.browser.switchToNewTabIfOpened();
		pages.packageRoomList_Initialize();
		pages.packageRoomList.selectFirstRoom();
		pages.packageResDetail_Initialize();
		pages.packageResDetail.clearAndFillForm(voClient);
		pages.packageResDetail.clickContinue();
		pages.payMethod_Initialize();
		pages.payMethod.fillCreditForm(DO_CreditCard);
		//pages.payMethod.clickOnCompleteReservation();
		//pages.thankYou_Initialize();
		//pages.thankYou.verifyCheckOutCompleted();
	}
	
	@Test (enabled=true, priority = 3)
	public void HPHotelHPHotelUsingDataRow(){
		Reporter.log("Starting test HPHotelHPHotelUsingDataRow");
		logger.info("Starting test HPHotelHPHotelUsingDataRow");
		VOResData voResData = DDManager.getResData(FWConfig.FILE_HPPACKAGERESDATA, 1);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.home_Initialize();
		pages.home.widget.searchPackage(voResData);
		pages.home.widget.clickSearchPackageButton();
		pages.packageList_Initialize();
		pages.packageList.listSelectFirstHotelAvailable();
		pages.browser.switchToNewTabIfOpened();
		pages.packageRoomList_Initialize();
		pages.packageRoomList.selectFirstRoom();
		pages.packageResDetail_Initialize();
		pages.packageResDetail.clearAndFillForm(voClient);
		//pages.packageResDetail.clickContinue();
		//pages.payMethod_Initialize();
		//pages.payMethod.fillCreditForm(voCreditCard);
		//pages.payMethod.clickOnCompleteReservation();
		//pages.thankYou_Initialize();
		//pages.thankYou.verifyCheckOutCompleted();
	}

	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		//driver.quit(); //Close all browser windows
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
	}
}
