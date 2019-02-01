package pages.pt;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Pages {
	public WebDriver driver;
	
	public HomePageF homePage;
	public HotelListPageF hotelListPage;
	public RoomListPageF roomListPage;
	public ResDetailPageF resDetailPage;
	public PayMethodPageF payMethodPage;
	public ThankYouPageF thankYouPage;
	public StageHotelListPageF stageHotelListPage;
	
	public Pages(WebDriver _driver) {
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
	}
	
	public void homePage_Initialize() {
		homePage = new HomePageF(driver);
	}

	public void hotelListPage_Initialize() {
		hotelListPage = new HotelListPageF(driver);
	}
	
	public void roomListPage_Initialize() {
		roomListPage = new RoomListPageF(driver);
	}
	
	public void resDetailPage_Initialize() {
		resDetailPage = new ResDetailPageF(driver);
	}
	public void payMethodPage_Initialize() {
		payMethodPage = new PayMethodPageF(driver);
		//payMethod_page = PageFactory.initElements(BrowserFactory.driver, PayMethodPageF.class);
	}
	public void thankYouPage_Initialize() {
		thankYouPage = new ThankYouPageF(driver);
	}
	
	public void stageHotelListPage_Initialize() {
		stageHotelListPage = new StageHotelListPageF(driver);
	}
}
