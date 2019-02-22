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

public class TCHP_HotelConCambios {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_HotelConCambios.class);
	String gTestName = "";
	String gURL = "";
	String gBrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void prerequisitos(String url, String browser, ITestContext itc) {
		logger.info("***************************** Starting @BeforeTest **********************************");
		Reporter.log("Starting BeforeTest");
		gTestName = itc.getName();
		logger.trace("Launching "+gTestName);
		gURL = url;
		gBrowser = browser;
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
		Assert.assertFalse(gBrowser.equals(""),"No se ha seteado un browser valido!");
		logger.trace("URL Seteada:"+gURL);
		logger.trace("Browser Seteado:"+gBrowser);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext itc) {
		logger.info("***************************** Starting @BeforeMethod **********************************");
		Reporter.log("Starting Browser");
		driver = BrowserFactory.startBrowser(gBrowser, gURL);
		itc.setAttribute("WebDriver", driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeDestino() throws InterruptedException{
		Reporter.log("Starting @HPHotelConCambioDeDestino");
		logger.info("Starting @HPHotelConCambioDeDestino");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.home_Initialize();
		pages.home.widget.searchHotel(voResData);
		pages.home.widget.clickSearchHotelButton();
		pages.hotelList_Initialize();
		//Lo tengo hardcodeado a Las vegas, necesito hacerlo dinamico con un archivo o una funcion
		pages.hotelList.widget.setDestin("Las Vegas (y alrededores), Nevada, Estados Unidos de América");
		pages.hotelList.widget.clickSubmit();
		pages.hotelList.list.listSelectFirstHotelAvailable();
		pages.roomList_Initialize();
		pages.roomList.selectFirstRoom();
		pages.resDetail_Initialize();
		pages.resDetail.clearAndFillForm(voClient);
		pages.resDetail.clickOnContinue();
		pages.payMethod_Initialize();
		pages.payMethod.fillCreditForm(voCreditCard);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeFecha() throws InterruptedException{		
		Reporter.log("Starting @HPHotelConCambioDeFecha");
		logger.info("Starting @HPHotelConCambioDeFecha");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.home_Initialize();
		pages.home.widget.searchHotel(voResData);
		pages.home.widget.clickSearchHotelButton();
		pages.hotelList_Initialize();
		pages.hotelList.widget.setStartDate("10/03/2019");
		pages.hotelList.widget.setEndDate("14/03/2019");
		pages.hotelList.widget.clickSubmit();
		pages.hotelList.list.listSelectFirstHotelAvailable();
		pages.roomList_Initialize();
		pages.roomList.selectFirstRoom();
		pages.resDetail_Initialize();
		pages.resDetail.clearAndFillForm(voClient);
		pages.resDetail.clickOnContinue();
		pages.payMethod_Initialize();
		pages.payMethod.fillCreditForm(DO_CreditCard);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeOcupantes() throws InterruptedException{		
		Reporter.log("Starting @HPHotelConCambioDeOcupantes");
		logger.info("Starting @HPHotelConCambioDeOcupantes");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.home_Initialize();
		pages.home.widget.searchHotel(voResData);
		pages.home.widget.clickSearchHotelButton();
		pages.hotelList_Initialize();
		pages.hotelList.widget.setAdults(4);
		pages.hotelList.widget.clickSubmit();
		pages.hotelList.list.listSelectFirstHotelAvailable();
		pages.roomList_Initialize();
		pages.roomList.selectFirstRoom();
		pages.resDetail_Initialize();
		pages.resDetail.clearAndFillForm(voClient);
		pages.resDetail.clickOnContinue();
		pages.payMethod_Initialize();
		pages.payMethod.fillCreditForm(DO_CreditCard);
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
