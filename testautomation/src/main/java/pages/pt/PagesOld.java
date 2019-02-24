package pages.pt;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.pt.checkout.PayMethodPage;
import pages.pt.checkout.ThankYouPage;
import pages.pt.general.TopNavigation;
import pages.pt.home.HomePage;
import pages.pt.hoteles.HotelListPage;
import pages.pt.hoteles.HotelListPageOld;
import pages.pt.hoteles.ResDetailPage;
import pages.pt.hoteles.RoomListPage;
import pages.pt.paquetes.PackageListPage;
import pages.pt.paquetes.PackageResDetailPage;
import pages.pt.paquetes.PackageRoomListPage;

public class PagesOld {
	public WebDriver driver;
	public Browser browser;
	public TopNavigation topnav;
	public HomePage home;
	public HotelListPage hotelList;
	public RoomListPage roomList;
	public ResDetailPage resDetail;
	public PayMethodPage payMethod;
	public ThankYouPage thankYou;
	public HotelListPageOld hotelListOld;
	public PackageListPage packageList;
	public PackageRoomListPage packageRoomList;
	public PackageResDetailPage packageResDetail;
	
	public PagesOld(WebDriver _driver) {
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		topnav = new TopNavigation(driver);
		browser = new Browser(driver);
	}
	
	public void home_Initialize() {
		home = new HomePage(driver);
	}

	public void hotelList_Initialize() {
		hotelList = new HotelListPage(driver);
	}
	
	public void roomList_Initialize() {
		roomList = new RoomListPage(driver);
	}
	
	public void resDetail_Initialize() {
		resDetail = new ResDetailPage(driver);
	}
	public void payMethod_Initialize() {
		payMethod = new PayMethodPage(driver);
		//payMethod_page = PageFactory.initElements(BrowserFactory.driver, PayMethodPageF.class);
	}
	public void thankYou_Initialize() {
		thankYou = new ThankYouPage(driver);
	}
	
	public void hotelListOld_Initialize() {
		hotelListOld = new HotelListPageOld(driver);
	}
	
	public void packageList_Initialize() {
		packageList = new PackageListPage(driver);
	}
	
	public void packageRoomList_Initialize() {
		packageRoomList = new PackageRoomListPage(driver);
	}
	
	public void packageResDetail_Initialize() {
		packageResDetail = new PackageResDetailPage(driver);
	}
}
