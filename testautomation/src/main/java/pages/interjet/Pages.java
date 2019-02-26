package pages.interjet;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Pages {
	public static WebDriver driver;
	
	public static void setDriver(WebDriver _driver) {
		driver = _driver;
	}
	
	public static HomeHoteles homePage() {
		return new HomeHoteles(driver);
	}
	
	public static HotelListPage hotelListPage() {
		return new HotelListPage(driver);
	}

	public static ResDetailPage resDetailPage() {
		return new ResDetailPage(driver);
	}
	
	public static InfoViajeroPage infoViajeroPage() {
		return new InfoViajeroPage(driver);
	}
	
	public static PayMethodPage payMethodPage() {
		return new PayMethodPage(driver);
	}
	
	public static ThankYouPage thankYouPage() {
		return new ThankYouPage(driver);
	}
}
