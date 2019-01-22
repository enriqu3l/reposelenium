package workflows.hphoteles;

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

public class WHPHotelConPaginado {
	private static Logger logger = LogManager.getLogger(WHPHotelConPaginado.class);
	
	public static void HPHotelConCambioDePagina(WebDriver driver) throws InterruptedException{
		logger.info("Starting workflow HPHotelConCambioDeDestino");
		Reporter.log("Starting workflow HPHotelConCambioDeDestino");
		VOHotelRes voHotelResNew = DDManager.getHotelResDefault(FrameworkConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.widgetSearchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.pagingClickOnNextPage();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.clearAndFillForm(voClient);
		pages.resDetail_page.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.fillCreditForm(voCreditCard);
		logger.info("Ending HPHotelConCambioDeDestino");
		Reporter.log("Ending HPHotelConCambioDeDestino");
	}	
}
