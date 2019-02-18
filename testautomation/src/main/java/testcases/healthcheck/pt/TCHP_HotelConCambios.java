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
	String gtestName = "";
	String gURL = "";
	String gbrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void prerequisitos(String url, String browser, ITestContext itc) {
		logger.info("***************************** Starting @BeforeTest **********************************");
		Reporter.log("Starting BeforeTest");
		logger.info("Starting BeforeTest");
		
		//Metodo 1 - Leyendo el nombre del test
		gtestName = itc.getName();
		if(gtestName.equals("HPPTCOMMX")) {gURL = FWConfig.URL_PTCOMMX_PROD;}
		else if(gtestName.equals("HPPTCOM")) {gURL = FWConfig.URL_PTCOM_PROD;}
		else if(gtestName.equals("HPPTCO")) {gURL = FWConfig.URL_PTCO_PROD;}
		
		//Metodo 2 - Obteniendo un parametro
		//URL = url;
		logger.trace("URL Seteada URL: "+gURL);
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
		
		//Seleccionando el browser que se usara para las pruebas
		gbrowser = browser;
		logger.trace("Browser Seteado: "+gbrowser);
		Assert.assertFalse(gbrowser.equals(""),"No se ha seteado un browser valido!");
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext itc) {
		logger.info("***************************** Starting @BeforeMethod **********************************");
		Reporter.log("Starting Browser");
		driver = BrowserFactory.startBrowser(gbrowser, gURL);
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
		pages.homeWidget_Initialize();
		pages.homeWidget.searchHotel(voResData);
		pages.homeWidget.clickSearchHotelButton();
		pages.hotelListPage_Initialize();
		//Lo tengo hardcodeado a Las vegas, necesito hacerlo dinamico con un archivo o una funcion
		pages.hotelListPage.widgetSetDestin("Las Vegas (y alrededores), Nevada, Estados Unidos de América");
		pages.hotelListPage.widgetClickSubmit();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomListPage.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.clearAndFillForm(voClient);
		pages.resDetailPage.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethodPage.fillCreditForm(voCreditCard);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeFecha() throws InterruptedException{		
		Reporter.log("Starting @HPHotelConCambioDeFecha");
		logger.info("Starting @HPHotelConCambioDeFecha");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homeWidget_Initialize();
		pages.homeWidget.searchHotel(voResData);
		pages.homeWidget.clickSearchHotelButton();
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSetStartDate("10/03/2019");
		pages.hotelListPage.widgetSetEndDate("14/03/2019");
		pages.hotelListPage.widgetClickSubmit();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomListPage.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.clearAndFillForm(voClient);
		pages.resDetailPage.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethodPage.fillCreditForm(DO_CreditCard);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeOcupantes() throws InterruptedException{		
		Reporter.log("Starting @HPHotelConCambioDeOcupantes");
		logger.info("Starting @HPHotelConCambioDeOcupantes");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homeWidget_Initialize();
		pages.homeWidget.searchHotel(voResData);
		pages.homeWidget.clickSearchHotelButton();
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSetAdults(4);
		pages.hotelListPage.widgetClickSubmit();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomListPage.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.clearAndFillForm(voClient);
		pages.resDetailPage.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethodPage.fillCreditForm(DO_CreditCard);
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
