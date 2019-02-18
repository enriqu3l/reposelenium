package pages.pt;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Pages {
	public WebDriver driver;
	
	public HomePage homePage;
	public Widget homeWidget;
	public TopNavigation topNavigation;
	public HotelListPage hotelListPage;
	public RoomListPage roomListPage;
	public ResDetailPage resDetailPage;
	public PayMethodPage payMethodPage;
	public ThankYouPage thankYouPage;
	public HotelListPageOld hotelListPageOld;
	public PackageListPage packageListPage;
	
	public Pages(WebDriver _driver) {
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
	}
	
	public void homePage_Initialize() {
		homePage = new HomePage(driver);
	}
	
	public void homeWidget_Initialize() {
		homeWidget = new Widget(driver);
	}
	
	public void topNavigation_Initialize() {
		topNavigation = new TopNavigation(driver);
	}

	public void hotelListPage_Initialize() {
		hotelListPage = new HotelListPage(driver);
	}
	
	public void roomListPage_Initialize() {
		roomListPage = new RoomListPage(driver);
	}
	
	public void resDetailPage_Initialize() {
		resDetailPage = new ResDetailPage(driver);
	}
	public void payMethodPage_Initialize() {
		payMethodPage = new PayMethodPage(driver);
		//payMethod_page = PageFactory.initElements(BrowserFactory.driver, PayMethodPageF.class);
	}
	public void thankYouPage_Initialize() {
		thankYouPage = new ThankYouPage(driver);
	}
	
	public void hotelListPageOld_Initialize() {
		hotelListPageOld = new HotelListPageOld(driver);
	}
	
	public void packageListPage_Initialize() {
		packageListPage = new PackageListPage(driver);
	}
}
