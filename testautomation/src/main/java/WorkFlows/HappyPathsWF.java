package WorkFlows;

import java.awt.AWTException;

import DataObjects.DOCreditCard;
import DataObjects.DOHotelRes;
import Helper.DOManager;
import Pages.Pages;

public class HappyPathsWF {
	
	public static void HPHotelDefault() throws InterruptedException {
		Pages.homePage_Initialize();
		Pages.home_page.SearchHotelDefault();
		Pages.hotelListPage_Initialize();
		Pages.hotelList_page.SelectFirstHotel();
		Pages.roomListPage_Initialize();
		Pages.roomList_page.SelectRoomDefault();
		Pages.resDetailPage_Initialize();
		Pages.resDetail_page.checkCurrentURLPage();
		Pages.resDetail_page.ClearFillandContinue();
		Pages.payMethodPage_Initialize();
		Pages.payMethod_page.checkCurrentURLPage();
		Pages.payMethod_page.FillCreditFormDefaultData();
	}
	
	public static void HPHotelResRandomData() throws InterruptedException, AWTException {
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
