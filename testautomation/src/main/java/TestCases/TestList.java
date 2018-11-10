package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Helper.BrowserFactory;
import Pages.HomePageF;
import Pages.HotelListPageF;

public class TestList {
	WebDriver driver;
	HomePageF home_page;
	HotelListPageF hotelList_page;
	
	@BeforeMethod
	public void beforeMethod() {
		//This will launch browser and specific url
		//driver = BrowserFactory.StartBrowser("firefox", "https://www.pricetravel.com/hoteles/cancun-area?room1.adults=2&room1.kids=0&room1.agekids=&checkin=2019%2F02%2F04&checkout=2019%2F02%2F05&rooms=1&adults=2&kids=0&agekids=&pdisplay=Canc%C3%BAn%20(y%20alrededores),%20M%C3%A9xico&placeid=69364&placetype=3&puri=cancun-area&quotelist=true&returningfromairport=&startingfromairport=&actiontype=1");
		driver = BrowserFactory.StartBrowser("firefox", "https://www.pricetravel.com");
	}
	
	@Test
	public void testList() throws InterruptedException{
		System.out.println("Si esta entrando al metodo VerifySearchPackage!");
		
		home_page = PageFactory.initElements(driver, HomePageF.class);
		home_page.SearchHotel("Hoteles", "Cancún (y alrededores), México");
		
		hotelList_page = PageFactory.initElements(driver, HotelListPageF.class);
		Thread.sleep(10000); 
		//Seleccionar el item 3 de la lista
		hotelList_page.SelectHotel(3);
	}

	@AfterMethod
	public void Close()
	{
		//driver.quit();	
	}
	
	@AfterTest
	public void End()
	{
		System.out.println("Test Finished");
	}
}
