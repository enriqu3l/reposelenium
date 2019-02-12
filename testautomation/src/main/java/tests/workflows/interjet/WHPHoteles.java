package tests.workflows.interjet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import config.FrameworkConfig;
import helpers.DDManager;
import pages.interjet.Pages;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOHotelRes;

public class WHPHoteles {
	private static Logger logger = LogManager.getLogger(WHPHoteles.class);
	
	public static void HPHotelDefault(WebDriver driver){
		logger.info("Starting HPHotelDefault");
		Reporter.log("Staring HPHotelDefault");
		VOHotelRes voHotelResNew = DDManager.getHotelRes(FrameworkConfig.FILE_INTERJETHOTELRESDATA, 1);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.homePage.widgetSearchHotel(voHotelResNew);
		pages.homePage.widgetClickSearchButton();
		pages.hotelListPage_Initialize();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.removeAddOns();
		pages.resDetailPage.clickOnContinue();
		pages.infoViajeroPage_Initialize();
		pages.infoViajeroPage.clearAndFillForm(voClient);
		pages.infoViajeroPage.clickOnContinue();
		pages.payMethodPage_Initialize();
		pages.payMethodPage.fillCreditForm(voCreditCard);
		//pages.payMethodPage.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYouPage.verifyCheckOutCompleted();
		logger.info("Ending HPHotelDefault");
		Reporter.log("Ending HPHotelDefault");
	}
	
	public static void HPHotelRandom(WebDriver driver){
		logger.info("Starting HPHotelRandom");
		Reporter.log("Staring HPHotelRandom");
		VOHotelRes voHotelResNew = DDManager.getRandomHotelRes(FrameworkConfig.FILE_INTERJETHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FrameworkConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.homePage.widgetSearchHotel(voHotelResNew);
		/*pages.hotelListPage_Initialize();
		pages.hotelListPage.listSelectFirstHotelAvailable();
		pages.roomListPage_Initialize();
		pages.roomListPage.selectFirstRoom();
		pages.resDetailPage_Initialize();
		pages.resDetailPage.clearAndFillForm(voClient);
		//pages.resDetailPage.clickOnContinue();
		//pages.payMethodPage_Initialize();
		//pages.payMethodPage.fillCreditForm(DO_CreditCard);
		//pages.payMethodPage.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYouPage.verifyCheckOutCompleted();
		*/
		logger.info("Ending HPHotelRandom");
		Reporter.log("Ending HPHotelRandom");
	}
}
