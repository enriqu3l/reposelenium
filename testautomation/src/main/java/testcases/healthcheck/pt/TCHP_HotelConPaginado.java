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

import config.FWConfig;
import helpers.BrowserFactory;
import helpers.DDManager;
import pages.pt.Pages;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TCHP_HotelConPaginado {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_HotelConPaginado.class);
	
	@BeforeMethod
	public void beforeMethod(ITestContext itc) {
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.startBrowser(FWConfig.BROWSER_DEFAULT, FWConfig.URL_PTCOMMX_PROD);
		itc.setAttribute("WebDriver", driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test
	public void HPHotelConCambioDePagina() throws InterruptedException{
		Reporter.log("Starting test HPHotelConCambioDePagina");
		logger.info("Starting test HPHotelConCambioDePagina");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homeWidget_Initialize();
		pages.homeWidget.searchHotel(voResData);
		pages.homeWidget.clickSearchHotelButton();
		pages.hotelListPage_Initialize();
		pages.hotelListPage.pagingClickOnNextPage();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomListPage.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.clearAndFillForm(voClient);
		pages.resDetailPage.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethodPage.fillCreditForm(voCreditCard);
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
		System.out.println("Test Finished");
	}
}
