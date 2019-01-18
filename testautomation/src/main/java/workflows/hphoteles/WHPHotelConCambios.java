package workflows.hphoteles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.DDManager;
import pages.Pages;
import valueobjects.VOCreditCard;
import valueobjects.VOHotelResNew;

public class WHPHotelConCambios {
	private static Logger logger = LogManager.getLogger(WHPHotelConCambios.class);
	
	public static void HPHotelConCambioDeDestino(WebDriver driver){
		logger.info("Starting workflow HPHotelConCambioDeDestino");
		Reporter.log("Starting workflow HPHotelConCambioDeDestino");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelResNew voHotelResNew = DDManager.getHotelResNewDefault();
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.searchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widgetChangeDestin("Las Vegas (y alrededores), Nevada, Estados Unidos de Am�rica");
		pages.hotelList_page.widgetClickSubmit();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.ClearFillandContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.FillCreditForm(DO_CreditCard);
		logger.info("Ending HPHotelConCambioDeDestino");
		Reporter.log("Ending HPHotelConCambioDeDestino");
	}
	
	public static void HPHotelConCambioDeFecha(WebDriver driver){
		logger.info("Starting workflow HPHotelConCambioDeFecha");
		Reporter.log("Staring workflow HPHotelConCambioDeFecha");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelResNew voHotelResNew = DDManager.getHotelResNewDefault();
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.searchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widgetChangeStartDate("10/03/2019");
		pages.hotelList_page.widgetChangeEndDate("14/03/2019");
		pages.hotelList_page.widgetClickSubmit();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.ClearFillandContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.FillCreditForm(DO_CreditCard);
		logger.info("Ending HPHotelConCambioDeFecha");
		Reporter.log("Ending HPHotelConCambioDeFecha");
	}
	
	public static void HPHotelConCambioDeOcupantes(WebDriver driver){
		logger.info("Starting workflow HPHotelConCambioDeOcupantes");
		Reporter.log("Staring workflow HPHotelConCambioDeOcupantes");
		VOHotelResNew voHotelResNew = DDManager.getHotelResNewDefault();
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.searchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widgetChangeAdults(4);
		pages.hotelList_page.widgetClickSubmit();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.ClearFillandContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.FillCreditForm(DO_CreditCard);
		logger.info("Ending HPHotelConCambioDeOcupantes");
		Reporter.log("Ending HPHotelConCambioDeOcupantes");
	}
}
