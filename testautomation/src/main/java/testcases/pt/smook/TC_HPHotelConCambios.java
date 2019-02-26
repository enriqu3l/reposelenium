package testcases.pt.smook;

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

public class TC_HPHotelConCambios {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TC_HPHotelConCambios.class);
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
		Pages.setDriver(driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeDestino(){
		Reporter.log("Starting @HPHotelConCambioDeDestino");
		logger.info("Starting @HPHotelConCambioDeDestino");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages.home().widget.searchHotel(voResData);
		Pages.home().widget.clickSearchHotelButton();
		//Lo tengo hardcodeado a Las vegas, necesito hacerlo dinamico con un archivo o una funcion
		Pages.hotelList().widget.setDestin("Las Vegas (y alrededores)");
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(voCreditCard);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeFecha(){		
		Reporter.log("Starting @HPHotelConCambioDeFecha");
		logger.info("Starting @HPHotelConCambioDeFecha");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages.home().widget.searchHotel(voResData);
		Pages.home().widget.clickSearchHotelButton();
		Pages.hotelList().widget.setStartDate(voResData.getStartDateAsLocalDate().plusDays(5).toString());
		Pages.hotelList().widget.setEndDate(voResData.getEndDateAsLocalDate().plusDays(5).toString());
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(DO_CreditCard);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeOcupantes(){		
		Reporter.log("Starting @HPHotelConCambioDeOcupantes");
		logger.info("Starting @HPHotelConCambioDeOcupantes");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages.home().widget.searchHotel(voResData);
		Pages.home().widget.clickSearchHotelButton();
		Pages.hotelList().widget.setAdults(4);
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(DO_CreditCard);
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
