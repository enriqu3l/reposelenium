package WorkFlows;

import Pages.Pages;

public class HappyPathsWF {
	
	public static void HPHotelDefault() throws InterruptedException {
		Pages.homePage_Initialize();
		Pages.home_page.verifyProductSelectedOnWidgetMenu("Hoteles");
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
	
	public static void HPPaqueteDefault() throws InterruptedException {
		Pages.homePage_Initialize();
		Pages.home_page.SearchHotel("Paquetes", "Cancún (y alrededores), México");
		Thread.sleep(10000);
		Pages.hotelListPage_Initialize();
		Pages.hotelList_page.SelectHotel(3);
	}
}
