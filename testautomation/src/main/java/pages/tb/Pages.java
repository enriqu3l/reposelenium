package pages.tb;

import org.openqa.selenium.WebDriver;

public class Pages {
	public static WebDriver driver;
	
	public static void setDriver(WebDriver _driver) {
		driver = _driver;
	}
	
	public static HomePage home() {
		return new HomePage(driver);
	}

	public static HotelListPage hotelList() {
		return new HotelListPage(driver);
	}
	
	public static RoomListPage roomList() {
		return new RoomListPage(driver);
	}
	
	public static ResDetailPage resDetail() {
		return new ResDetailPage(driver);
	}
	public static PayMethodPage payMethod() {
		return new PayMethodPage(driver);
	}
	
	public static ThankYouPage thankYou() {
		return new ThankYouPage(driver);
	}
}
