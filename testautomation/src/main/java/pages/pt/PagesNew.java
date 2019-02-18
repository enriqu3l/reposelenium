package pages.pt;

import org.openqa.selenium.WebDriver;

public class PagesNew {
	private static WebDriver driver;
	
	public static void setDriver(WebDriver _driver) {
		driver = _driver;
	}
	
	public static HomePage homePage(){
		return new HomePage(driver);
	}
	
	public static Widget homeWidget(){
		return new Widget(driver);
	}
	
	public static TopNavigation topNavigation() {
		return new TopNavigation(driver);
	}
	
	public static HotelListPage hotelListPage(){
		return new HotelListPage(driver);
	}
	
	public static RoomListPage roomListPage(){
		return new RoomListPage(driver);
	}
	
	public static ResDetailPage resDetailPage(){
		return new ResDetailPage(driver);
	}
	
	public static PayMethodPage payMethodPage(){
		return new PayMethodPage(driver);
	}
	
	public static ThankYouPage thankYouPage(){
		return new ThankYouPage(driver);
	}
	
	public static PackageListPage packageListPage(){
		return new PackageListPage(driver);
	}
	
	
	//+++++++++++ En caso de que no se quiera usar la funcion setDriver ++++++++++++++
	public static HomePage homePage(WebDriver driver){
		return new HomePage(driver);
	}
	
	public static Widget homeWidget(WebDriver driver){
		return new Widget(driver);
	}
	
	public static TopNavigation topNavigation(WebDriver driver) {
		return new TopNavigation(driver);
	}
	
	public static HotelListPage hotelListPage(WebDriver driver){
		return new HotelListPage(driver);
	}
	
	public static RoomListPage roomListPage(WebDriver driver){
		return new RoomListPage(driver);
	}
	
	public static ResDetailPage resDetailPage(WebDriver driver){
		return new ResDetailPage(driver);
	}
	
	public static PayMethodPage payMethodPage(WebDriver driver){
		return new PayMethodPage(driver);
	}
	
	public static ThankYouPage thankYouPage(WebDriver driver){
		return new ThankYouPage(driver);
	}
	
	public static PackageListPage packageListPage(WebDriver driver){
		return new PackageListPage(driver);
	}
}
