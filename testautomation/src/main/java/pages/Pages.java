package pages;

import org.openqa.selenium.WebDriver;

public class Pages {
	public WebDriver driver;
	
	public HomePageF home_page;
	public HotelListPageF hotelList_page;
	public RoomListPageF roomList_page;
	public ResDetailPageF resDetail_page;
	public PayMethodPageF payMethod_page;
	public ThankYouPageF thankYou_page;
	
	public Pages(WebDriver driver) {
		this.driver = driver;
	}
	
	public void homePage_Initialize() {
		home_page = new HomePageF(driver);
	}

	public void hotelListPage_Initialize() {
		hotelList_page = new HotelListPageF(driver);
	}
	
	public void roomListPage_Initialize() {
		roomList_page = new RoomListPageF(driver);
	}
	
	public void resDetailPage_Initialize() {
		resDetail_page = new ResDetailPageF(driver);
	}
	public void payMethodPage_Initialize() {
		payMethod_page = new PayMethodPageF(driver);
		//payMethod_page = PageFactory.initElements(BrowserFactory.driver, PayMethodPageF.class);
	}
	
	public void thankYouPage_Initialize() {
		thankYou_page = new ThankYouPageF(driver);
	}
}
