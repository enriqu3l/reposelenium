package tests.workflows.hoteles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import config.FrameworkConfig;
import helpers.DDManager;
import pages.Pages;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOHotelRes;

public class WHPHotelConCambios {
	private static Logger logger = LogManager.getLogger(WHPHotelConCambios.class);
	
	public static void HPHotelConCambioDeDestino(WebDriver driver){
		logger.info("Starting workflow HPHotelConCambioDeDestino");
		Reporter.log("Starting workflow HPHotelConCambioDeDestino");
		VOHotelRes voHotelResNew = DDManager.getHotelResDefault(FrameworkConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.homePage.widgetSearchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		//Lo tengo hardcodeado a Las vegas, necesito hacerlo dinamico con un archivo o una funcion
		pages.hotelListPage.widgetSelectDestin("Las Vegas (y alrededores), Nevada, Estados Unidos de América");
		pages.hotelListPage.widgetClickSubmit();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomListPage.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.clearAndFillForm(voClient);
		pages.resDetailPage.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethodPage.fillCreditForm(voCreditCard);
		logger.info("Ending HPHotelConCambioDeDestino");
		Reporter.log("Ending HPHotelConCambioDeDestino");
	}
	
	public static void HPHotelConCambioDeFecha(WebDriver driver){
		logger.info("Starting workflow HPHotelConCambioDeFecha");
		Reporter.log("Staring workflow HPHotelConCambioDeFecha");
		VOHotelRes voHotelResNew = DDManager.getHotelResDefault(FrameworkConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.homePage.widgetSearchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSelectStartDate("10/03/2019");
		pages.hotelListPage.widgetSelectEndDate("14/03/2019");
		pages.hotelListPage.widgetClickSubmit();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomListPage.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.clearAndFillForm(voClient);
		pages.resDetailPage.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethodPage.fillCreditForm(DO_CreditCard);
		logger.info("Ending HPHotelConCambioDeFecha");
		Reporter.log("Ending HPHotelConCambioDeFecha");
	}
	
	public static void HPHotelConCambioDeOcupantes(WebDriver driver){
		logger.info("Starting workflow HPHotelConCambioDeOcupantes");
		Reporter.log("Staring workflow HPHotelConCambioDeOcupantes");
		VOHotelRes voHotelResNew = DDManager.getHotelResDefault(FrameworkConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.homePage.widgetSearchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelListPage.widgetSelectAdults(4);
		pages.hotelListPage.widgetClickSubmit();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomListPage.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.clearAndFillForm(voClient);
		pages.resDetailPage.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethodPage.fillCreditForm(DO_CreditCard);
		logger.info("Ending HPHotelConCambioDeOcupantes");
		Reporter.log("Ending HPHotelConCambioDeOcupantes");
	}
}
