package WorkFlows;

import java.awt.AWTException;

import DataObjects.DOCreditCard;
import DataObjects.DOHotelRes;
import Helper.DOManager;
import Pages.Pages;

public class HappyPathsWF {
	
	public static void HPHotelDefault() throws InterruptedException, AWTException {
		//La funcion Default siempre leera el primer registro de los ExcelFiles
		DOHotelRes DO_HotelRes = DOManager.getHotelRes(1);
		DOCreditCard DO_CreditCard = DOManager.getCreditCard(1);
		
		Pages.homePage_Initialize();
		Pages.home_page.SearchHotel(DO_HotelRes);
		Pages.hotelListPage_Initialize();
		Pages.hotelList_page.SelectFirstHotel();
		Pages.roomListPage_Initialize();
		Pages.roomList_page.SelectRoomDefault();
		Pages.resDetailPage_Initialize();
		Pages.resDetail_page.checkCurrentURLPage();
		Pages.resDetail_page.ClearFillandContinue();
		Pages.payMethodPage_Initialize();
		Pages.payMethod_page.checkCurrentURLPage();
		Pages.payMethod_page.FillCreditForm(DO_CreditCard);
		Pages.payMethod_page.clickOnCompleteReservation();
		Pages.thankYouPage_Initialize();
		Pages.thankYou_page.verifyCheckOutCompleted();
		
	}
	
	public static void HPHotelRandom() throws InterruptedException, AWTException {
		DOHotelRes DO_HotelRes = DOManager.getRandomHotelRes();
		DOCreditCard DO_CreditCard = DOManager.getRandomCreditCard();
		
		Pages.homePage_Initialize();
		Pages.home_page.SearchHotel(DO_HotelRes);
		Pages.hotelListPage_Initialize();
		Pages.hotelList_page.SelectFirstHotel();
		Pages.roomListPage_Initialize();
		Pages.roomList_page.SelectRoomDefault();
		Pages.resDetailPage_Initialize();
		Pages.resDetail_page.checkCurrentURLPage();
		Pages.resDetail_page.ClearFillandContinue();
		Pages.payMethodPage_Initialize();
		Pages.payMethod_page.checkCurrentURLPage();
		Pages.payMethod_page.FillCreditForm(DO_CreditCard);
	}
	
	public static void HPPaqueteDefault() throws InterruptedException {
		//Aun falta codificar todo lo de paquetes!!!
		
		Pages.homePage_Initialize();
		Pages.home_page.SelectProduct("Paquete");
	}
}
