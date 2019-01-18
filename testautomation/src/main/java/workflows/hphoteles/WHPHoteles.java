package workflows.hphoteles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.DDManager;
import pages.Pages;
import valueobjects.VOCreditCard;
import valueobjects.VOHotelResNew;

public class WHPHoteles {
	private static Logger logger = LogManager.getLogger(WHPHoteles.class);
	
	public static void HPHotelDefault(WebDriver driver) throws InterruptedException{
		logger.info("Starting HPHotelDefault");
		Reporter.log("Staring HPHotelDefault");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelResNew voHotelResNew = DDManager.getHotelResNewDefault();
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.searchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.ClearFillandContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.FillCreditForm(DO_CreditCard);
		//pages.payMethod_page.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYou_page.verifyCheckOutCompleted();
		logger.info("Ending HPHotelDefault");
		Reporter.log("Ending HPHotelDefault");
	}
	
	public static void HPHotelRandom(WebDriver driver) throws InterruptedException{
		logger.info("Starting HPHotelRandom");
		Reporter.log("Staring HPHotelRandom");
		VOHotelResNew voHotelResNew = DDManager.getHotelResNewDefault();
		VOCreditCard DO_CreditCard = DDManager.getRandomCreditCard();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.searchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.ClearFillandContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.FillCreditForm(DO_CreditCard);
		//pages.payMethod_page.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYou_page.verifyCheckOutCompleted();
		logger.info("Ending HPHotelRandom");
		Reporter.log("Ending HPHotelRandom");
	}
}
