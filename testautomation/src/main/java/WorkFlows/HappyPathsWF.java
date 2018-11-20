package WorkFlows;

import DataObjects.DOCreditCard;
import DataObjects.DOHotelRes;
import Helper.DDManager;
import Pages.Pages;

public class HappyPathsWF {
	
	public static void HPHotelDefault() throws InterruptedException{
		//Aqui estoy utilizando una funcion del DOManager para generar el DefaultData
		DOHotelRes DO_HotelRes = DDManager.getHotelResDefault();
		DOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		
		Pages.homePage_Initialize();
		Pages.home_page.SearchHotel(DO_HotelRes);
		Pages.hotelListPage_Initialize();
		Pages.hotelList_page.SelectFirstHotel();
		Pages.roomListPage_Initialize();
		Pages.roomList_page.SelectRoomDefault();
		Pages.resDetailPage_Initialize();
		Pages.resDetail_page.ClearFillandContinue();
		Pages.payMethodPage_Initialize();
		Pages.payMethod_page.FillCreditForm(DO_CreditCard);
		//Pages.payMethod_page.clickOnCompleteReservation();
		//Pages.thankYouPage_Initialize();
		//Pages.thankYou_page.verifyCheckOutCompleted();
	}
	
	public static void HPHotelRandom() throws InterruptedException{
		DOHotelRes DO_HotelRes = DDManager.getRandomHotelRes();
		DOCreditCard DO_CreditCard = DDManager.getRandomCreditCard();
		
		Pages.homePage_Initialize();
		Pages.home_page.SearchHotel(DO_HotelRes);
		Pages.hotelListPage_Initialize();
		Pages.hotelList_page.SelectFirstHotel();
		Pages.roomListPage_Initialize();
		Pages.roomList_page.SelectRoomDefault();
		Pages.resDetailPage_Initialize();
		Pages.resDetail_page.ClearFillandContinue();
		Pages.payMethodPage_Initialize();
		Pages.payMethod_page.FillCreditForm(DO_CreditCard);
		//Pages.payMethod_page.clickOnCompleteReservation();
		//Pages.thankYouPage_Initialize();
		//Pages.thankYou_page.verifyCheckOutCompleted();
	}
	
	public static void HPPaqueteDefault() throws InterruptedException {
		//Aun falta codificar todo lo de paquetes!!!
		Pages.homePage_Initialize();
	}
}
