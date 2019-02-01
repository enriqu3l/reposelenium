package pages.interjet;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Pages {
	public WebDriver driver;
	
	public HomeHoteles homePage;
	public HotelListPage hotelListPage;
	
	public Pages(WebDriver _driver) {
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
	}
	
	public void homePage_Initialize() {
		homePage = new HomeHoteles(driver);
	}
	
	public void hotelListPage_Initialize() {
		hotelListPage = new HotelListPage(driver);
	}
}
