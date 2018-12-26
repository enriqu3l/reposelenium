package WorkFlows;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import DataObjects.DOCreditCard;
import DataObjects.DOHotelRes;
import Helper.DDManager;
import Pages.HomePageF;
import Pages.Pages;

public class HappyPathsWF {
	private static Logger logger = LogManager.getLogger(HappyPathsWF.class);
	
	public static void HPHotelDefault(WebDriver driver) throws InterruptedException{
		logger.info("Starting HPHotelDefault");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		DOHotelRes DO_HotelRes = DDManager.getHotelResDefault();
		DOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.SearchHotel(DO_HotelRes);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.SelectFirstHotel();
		pages.roomListPage_Initialize();
		pages.roomList_page.SelectRoomDefault();
		pages.resDetailPage_Initialize();
		//pages.resDetail_page.ClearFillandContinue();
		//pages.payMethodPage_Initialize();
		//pages.payMethod_page.FillCreditForm(DO_CreditCard);
		//pages.payMethod_page.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYou_page.verifyCheckOutCompleted();
		logger.info("Starting HPHotelDefault");
	}
	
	public static void HPHotelRandom(WebDriver driver) throws InterruptedException{
		logger.info("Starting HPHotelRandom");
		DOHotelRes DO_HotelRes = DDManager.getRandomHotelRes();
		DOCreditCard DO_CreditCard = DDManager.getRandomCreditCard();
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.SearchHotel(DO_HotelRes);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.SelectFirstHotel();
		pages.roomListPage_Initialize();
		pages.roomList_page.SelectRoomDefault();
		pages.resDetailPage_Initialize();
		//pages.resDetail_page.ClearFillandContinue();
		//pages.payMethodPage_Initialize();
		//pages.payMethod_page.FillCreditForm(DO_CreditCard);
		//pages.payMethod_page.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYou_page.verifyCheckOutCompleted();
		logger.info("Ending HPHotelRandom");
	}
	
	public static void HPPaqueteDefault(WebDriver driver) throws InterruptedException {
		logger.info("Starting HPPaqueteDefault");
		//Aun falta codificar todo lo de paquetes!!!
	}
}
