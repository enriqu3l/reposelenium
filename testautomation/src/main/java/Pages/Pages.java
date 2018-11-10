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
		//PageFactory.initElements(new AjaxElementLocatorFactory(BrowserFactory.driver, 15),home_page);
		//home_page = PageFactory.initElements(BrowserFactory.driver, HomePageF.class);
	}

	public static void hotelListPage_Initialize() {
		hotelList_page = new HotelListPageF(driver);
		//PageFactory.initElements(new AjaxElementLocatorFactory(BrowserFactory.driver, 15),hotelList_page);
		//hotelList_page = PageFactory.initElements(BrowserFactory.driver, HotelListPageF.class);
	}
	
	public static void roomListPage_Initialize() {
		roomList_page = new RoomListPageF(driver);
		//PageFactory.initElements(new AjaxElementLocatorFactory(BrowserFactory.driver, 15),roomList_page);
		//roomList_page = PageFactory.initElements(BrowserFactory.driver, RoomListPageF.class);
	}
	
	public static void resDetailPage_Initialize() {
		resDetail_page = new ResDetailPageF(driver);
		//PageFactory.initElements(new AjaxElementLocatorFactory(BrowserFactory.driver, 15),resDetail_page);
		//resDetail_page = PageFactory.initElements(BrowserFactory.driver, ResDetailPageF.class);
	}
	public static void payMethodPage_Initialize() {
		payMethod_page = new PayMethodPageF(driver);
		//PageFactory.initElements(new AjaxElementLocatorFactory(BrowserFactory.driver, 15),payMethod_page);
		//payMethod_page = PageFactory.initElements(BrowserFactory.driver, PayMethodPageF.class);
	}
}
