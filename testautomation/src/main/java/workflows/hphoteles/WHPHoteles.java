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

public class WHPHoteles {
	private static Logger logger = LogManager.getLogger(WHPHoteles.class);
	
	public static void HPHotelDefault(WebDriver driver) throws InterruptedException{
		logger.info("Starting HPHotelDefault");
		Reporter.log("Staring HPHotelDefault");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelRes voHotelResNew = DDManager.getHotelResDefault(FrameworkConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.widgetSearchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.clearAndFillForm(voClient);
		//pages.resDetail_page.clickOnContinue();
		//pages.payMethodPage_Initialize();
		//pages.payMethod_page.fillCreditForm(voCreditCard);
		//pages.payMethod_page.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYou_page.verifyCheckOutCompleted();
		logger.info("Ending HPHotelDefault");
		Reporter.log("Ending HPHotelDefault");
	}
	
	public static void HPHotelRandom(WebDriver driver) throws InterruptedException{
		logger.info("Starting HPHotelRandom");
		Reporter.log("Staring HPHotelRandom");
		VOHotelRes voHotelResNew = DDManager.getRandomHotelRes(FrameworkConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.widgetSearchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.clearAndFillForm(voClient);
		//pages.resDetail_page.clickOnContinue();
		//pages.payMethodPage_Initialize();
		//pages.payMethod_page.fillCreditForm(DO_CreditCard);
		//pages.payMethod_page.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYou_page.verifyCheckOutCompleted();
		logger.info("Ending HPHotelRandom");
		Reporter.log("Ending HPHotelRandom");
	}
	
	//PDV
	public static void HPPDVHotel(WebDriver driver) throws InterruptedException{
		logger.info("Starting HPHotelDefault");
		Reporter.log("Staring HPHotelDefault");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		//Estoy usando la data de GeneralConfig por la fecha plusMonth porque aun no implemento en el Excel
		VOHotelRes voHotelResNew = DDManager.getHotelResDefault(FrameworkConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.home_page.widgetSearchHotel(voHotelResNew);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomList_page.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetail_page.clearAndFillForm(voClient);
		pages.resDetail_page.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethod_page.fillCreditForm(DO_CreditCard);
		//pages.payMethod_page.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYou_page.verifyCheckOutCompleted();
		logger.info("Ending HPHotelDefault");
		Reporter.log("Ending HPHotelDefault");
	}
}
