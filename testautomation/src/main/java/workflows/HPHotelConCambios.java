package workflows;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.DDManager;
import pages.Pages;
import valueobjects.VOCreditCard;
import valueobjects.VOHotelRes;

public class HPHotelConCambios {
	private static Logger logger = LogManager.getLogger(HPHotelConCambios.class);
	
	public static void HPHotelConCambioDeDestino(WebDriver driver) throws InterruptedException{
		logger.info("Starting workflow HPHotelConCambioDeDestino");
		Reporter.log("Staring workflow HPHotelConCambioDeDestino");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelRes DO_HotelRes = DDManager.getHotelResDefault();
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.SearchHotel(DO_HotelRes);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widget_changeDestin("Las Vegas (y alrededores), Nevada, Estados Unidos de América");
		pages.hotelList_page.widget_search();
		pages.hotelList_page.SelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.ClearFillandContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.FillCreditForm(DO_CreditCard);
		logger.info("Ending HPHotelConCambioDeDestino");
		Reporter.log("Ending HPHotelConCambioDeDestino");
	}
	
	public static void HPHotelConCambioDeFecha(WebDriver driver) throws InterruptedException{
		logger.info("Starting workflow HPHotelConCambioDeFecha");
		Reporter.log("Staring workflow HPHotelConCambioDeFecha");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelRes DO_HotelRes = DDManager.getHotelResDefault();
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.SearchHotel(DO_HotelRes);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widget_selectStartDate("10/03/2019");
		pages.hotelList_page.widget_selectEndDate("14/03/2019");
		pages.hotelList_page.widget_search();
		pages.hotelList_page.SelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.ClearFillandContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.FillCreditForm(DO_CreditCard);
		logger.info("Ending HPHotelConCambioDeFecha");
		Reporter.log("Ending HPHotelConCambioDeFecha");
	}
	
	public static void HPHotelConCambioDeOcupantes(WebDriver driver) throws InterruptedException{
		logger.info("Starting workflow HPHotelConCambioDeOcupantes");
		Reporter.log("Staring workflow HPHotelConCambioDeOcupantes");
		VOHotelRes DO_HotelRes = DDManager.getHotelResDefault();
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.SearchHotel(DO_HotelRes);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widget_changeAdults(4);
		pages.hotelList_page.widget_search();
		pages.hotelList_page.SelectFirstHotelAvailable();
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
