package testcases.regression.pt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import helpers.DDManager;
import tests.components.pt.CSPAHLWidget2;

public class TCSPAHLWidget2 {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCSPAHLWidget2.class);
	
	@BeforeMethod
	public void prerequisites() {
		Reporter.log("Starting Browser");
		//String url = "https://www.pricetravel.com/hoteles/cancun-area?checkin=2019-02-01&checkout=2019-02-03&placetype=3&placeid=69364&source=SPA-Hotel-List&rooms=1&room1.adults=2&agekids=";
		//String url = "https://www.pricetravel.com/hoteles/las-vegas-y-alrededores-nevada-us?room1.adults=2&room1.kids=0&room1.agekids=&checkin=2019%2F02%2F01&checkout=2019%2F02%2F03&rooms=1&adults=2&kids=0&agekids=&pdisplay=Las%20Vegas%20(y%20alrededores),%20Nevada,%20Estados%20Unidos%20de%20Am%C3%A9rica&placeid=67907&placetype=3&puri=las-vegas-y-alrededores-nevada-us&quotelist=true&returningfromairport=&startingfromairport=&actiontype=1";
		String url = "https://stage-spa.pricetravel.com/hoteles/cancun-area?hotelName=&checkin=2019-02-02&checkout=2019-02-04&page=1&rooms=1&room1.adults=2&room1.kids=0&room1.agekids=";
		//String url = DDManager.getHotelListLPDefault(FrameworkConfig.FILE_HOTELLANDINGPAGEDATA);
		driver = BrowserFactory.StartBrowser(FrameworkConfig.BROWSER_DEFAULT, url);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test (enabled=true)
	public void test1(){
		Reporter.log("Starting test1");
		logger.info("Starting test1");
		CSPAHLWidget2.autocompleteDestinationTest(driver);
	}
	
	@Test (enabled=true)
	public void test2() {
		Reporter.log("Starting test2");
		logger.info("Starting test2");
		CSPAHLWidget2.datePickersFunctionalityTest(driver);
	}
	
	@Test (enabled=true)
	public void test3(){
		Reporter.log("Starting test3");
		logger.info("Starting test3");
		CSPAHLWidget2.searchWithEmptyFieldsTest(driver);
	}
	
	@Test (enabled=true)
	public void test4(){
		Reporter.log("Starting test4");
		logger.info("Starting test4");
		CSPAHLWidget2.searchDifferentDestinTest(driver);
	}
	
	@Test (enabled=true)
	public void test5(){
		Reporter.log("Starting test5");
		logger.info("Starting test5");
		CSPAHLWidget2.searchDifferentDatesTest(driver);
	}
	
	@Test (enabled=true)
	public void test6(){
		Reporter.log("Starting test6");
		logger.info("Starting test6");
		CSPAHLWidget2.searchDifferentOccupantsTest(driver);
	}
	
	@Test (enabled=true)
	public void test7(){
		Reporter.log("Starting test7");
		logger.info("Starting test7");
		CSPAHLWidget2.searchDifferentReservationTest(driver);
	}
	
	@Test (enabled=false)
	public void prueba(){
		Reporter.log("Starting prueba");
		logger.info("Starting prueba");
		CSPAHLWidget2.prueba(driver);
	}
	
	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		//driver.close();
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
		System.out.println("Test Finished");
	}
}