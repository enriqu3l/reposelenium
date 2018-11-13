package Pages;

import org.openqa.selenium.WebDriver;

import Helper.BrowserFactory;

public class Pages {
	public static WebDriver driver = BrowserFactory.driver;
	
	public static HomePageF home_page;
	public static HotelListPageF hotelList_page;
	public static RoomListPageF roomList_page;
	public static ResDetailPageF resDetail_page;
	public static PayMethodPageF payMethod_page;
	
	public Pages() {
		//Constructor
	}
	
	public static void homePage_Initialize() {
		home_page = new HomePageF(driver);
	}

	public static void hotelListPage_Initialize() {
		hotelList_page = new HotelListPageF(driver);
	}
	
	public static void roomListPage_Initialize() {
		roomList_page = new RoomListPageF(driver);
	}
	
	public static void resDetailPage_Initialize() {
		resDetail_page = new ResDetailPageF(driver);
	}
	public static void payMethodPage_Initialize() {
		payMethod_page = new PayMethodPageF(driver);
		//payMethod_page = PageFactory.initElements(BrowserFactory.driver, PayMethodPageF.class);
	}
	
}
