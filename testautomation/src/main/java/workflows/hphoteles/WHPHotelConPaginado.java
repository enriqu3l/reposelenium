package workflows.hphoteles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.DDManager;
import pages.Pages;
import valueobjects.VOCreditCard;
import valueobjects.VOHotelResNew;

public class WHPHotelConPaginado {
	private static Logger logger = LogManager.getLogger(WHPHotelConPaginado.class);
	
	public static void HPHotelConCambioDePagina(WebDriver driver) throws InterruptedException{
		logger.info("Starting workflow HPHotelConCambioDeDestino");
		Reporter.log("Starting workflow HPHotelConCambioDeDestino");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelResNew voHotelResNew = DDManager.getHotelResNewDefault();
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.searchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.pagingClickOnNextPage();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.ClearFillandContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.FillCreditForm(voCreditCard);
		logger.info("Ending HPHotelConCambioDeDestino");
		Reporter.log("Ending HPHotelConCambioDeDestino");
	}	
}
