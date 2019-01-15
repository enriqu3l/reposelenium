package workflows;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.DDManager;
import pages.Pages;
import valueobjects.VOCreditCard;
import valueobjects.VOHotelRes;

public class HPHotelConPaginado {
	private static Logger logger = LogManager.getLogger(HPHotelConPaginado.class);
	
	public static void HPHotelConCambioDePagina(WebDriver driver) throws InterruptedException{
		logger.info("Starting workflow HPHotelConCambioDeDestino");
		Reporter.log("Starting workflow HPHotelConCambioDeDestino");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelRes DO_HotelRes = DDManager.getHotelResDefault();
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.SearchHotel(DO_HotelRes);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.goToNextPage();
		pages.hotelList_page.SelectFirstHotelAvailable();
		logger.info("Ending HPHotelConCambioDeDestino");
		Reporter.log("Ending HPHotelConCambioDeDestino");
	}	
}
