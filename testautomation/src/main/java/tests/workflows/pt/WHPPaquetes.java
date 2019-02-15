package tests.workflows.pt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import config.FWConfig;
import helpers.DDManager;
import pages.pt.Pages;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class WHPPaquetes {
	private static Logger logger = LogManager.getLogger(WHPPaquetes.class);
	
	public static void hpPackageDefault(WebDriver driver){
		logger.info("Starting HPHotelDefault");
		Reporter.log("Staring HPHotelDefault");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPPACKAGERESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.homePage.widgetSearchPackage(voResData);
		pages.homePage.widgetClickSearchPackageButton();
		pages.packageListPage_Initialize();
		pages.packageListPage.listSelectFirstHotelAvailable();
		//pages.roomListPage_Initialize();
		//pages.roomListPage.selectFirstRoom();
		//pages.resDetailPage_Initialize();
		//pages.resDetailPage.clearAndFillForm(voClient);
		//pages.resDetailPage.clickOnContinue();
		//pages.payMethodPage_Initialize();
		//pages.payMethodPage.fillCreditForm(voCreditCard);
		//pages.payMethodPage.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYouPage.verifyCheckOutCompleted();
		logger.info("Ending HPHotelDefault");
		Reporter.log("Ending HPHotelDefault");
	}
	
	public static void hpPackageRandom(WebDriver driver){
		logger.info("Starting HPHotelRandom");
		Reporter.log("Staring HPHotelRandom");
		VOResData voResData = DDManager.getResDataRandom(FWConfig.FILE_HPPACKAGERESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.homePage.widgetSearchPackage(voResData);
		pages.homePage.widgetClickSearchPackageButton();
		pages.packageListPage_Initialize();
		pages.packageListPage.listSelectFirstHotelAvailable();
		//pages.roomListPage_Initialize();
		//pages.roomListPage.selectFirstRoom();
		//pages.resDetailPage_Initialize();
		//pages.resDetailPage.clearAndFillForm(voClient);
		//pages.resDetailPage.clickOnContinue();
		//pages.payMethodPage_Initialize();
		//pages.payMethodPage.fillCreditForm(DO_CreditCard);
		//pages.payMethodPage.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYouPage.verifyCheckOutCompleted();
		logger.info("Ending HPHotelRandom");
		Reporter.log("Ending HPHotelRandom");
	}
	
	public static void hpPackageUsingDataRow(WebDriver driver, int dataRow){
		logger.info("Starting HPHotelDefault");
		Reporter.log("Staring HPHotelDefault");
		VOResData voResData = DDManager.getResData(FWConfig.FILE_HPPACKAGERESDATA, dataRow);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages pages = new Pages(driver);
		pages.homePage_Initialize();
		pages.homePage.widgetSearchPackage(voResData);
		pages.homePage.widgetClickSearchPackageButton();
		pages.packageListPage_Initialize();
		pages.packageListPage.listSelectFirstHotelAvailable();
		//pages.roomListPage_Initialize();
		//pages.roomListPage.selectFirstRoom();
		//pages.resDetailPage_Initialize();
		//pages.resDetailPage.clearAndFillForm(voClient);
		//pages.resDetailPage.clickOnContinue();
		//pages.payMethodPage_Initialize();
		//pages.payMethodPage.fillCreditForm(voCreditCard);
		//pages.payMethodPage.clickOnCompleteReservation();
		//pages.thankYouPage_Initialize();
		//pages.thankYouPage.verifyCheckOutCompleted();
		logger.info("Ending HPHotelDefault");
		Reporter.log("Ending HPHotelDefault");
	}
}
