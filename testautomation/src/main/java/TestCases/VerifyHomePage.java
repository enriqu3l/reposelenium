package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Helper.BrowserFactory;
import Pages.HomePageF;

public class VerifyHomePage {
	WebDriver driver;
	HomePageF home_page;
	
	@BeforeMethod
	public void beforeMethod() {
		//This will launch browser and specific url
		driver = BrowserFactory.StartBrowser("firefox", "https://www.pricetravel.com.mx/");

		//Create Page Object using Page Factory
		home_page = PageFactory.initElements(driver, HomePageF.class);

	}
	
	@Test
	public void VerifySearchProduct() throws InterruptedException{
		System.out.println("Si esta entrando al metodo VerifySearchPackage!");
		
		//Calling the method
		//home_page.SearchProduct("Paquetes", "Cancún (CUN)", "Mérida");
		home_page.SearchHotel("Hoteles", "Cancún (y alrededores), México");
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
