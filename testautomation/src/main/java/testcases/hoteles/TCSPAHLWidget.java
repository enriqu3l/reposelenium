package testcases.hoteles;

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
import workflows.hoteles.WSPAHLWidget;

public class TCSPAHLWidget {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCSPAHLWidget.class);
	
	@BeforeMethod
	public void prerequisites() {
		Reporter.log("Starting Browser");
		String url = "https://www.pricetravel.com/hoteles/cancun-area?room1.adults=2&room1.kids=0&room1.agekids=&checkin=2019%2F02%2F01&checkout=2019%2F02%2F03&rooms=1&adults=2&kids=0&agekids=&pdisplay=Canc%C3%BAn%20(y%20alrededores),%20M%C3%A9xico&placeid=69364&placetype=3&puri=cancun-area&quotelist=true&returningfromairport=&startingfromairport=&actiontype=1";
		driver = BrowserFactory.StartBrowser(FrameworkConfig.BROWSER_DEFAULT, url);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test
	public void test1() throws InterruptedException{
		Reporter.log("Starting test1");
		logger.info("Starting test1");
		WSPAHLWidget.destinationAutocompleteTest(driver);
	}
	
	@Test
	public void test2() throws InterruptedException{
		Reporter.log("Starting test2");
		logger.info("Starting test2");
		WSPAHLWidget.changeDestinTest(driver);
	}
	
	@Test (enabled=false)
	public void test3() throws InterruptedException{
		Reporter.log("Starting test3");
		logger.info("Starting test3");
		//HLWidget – Cambiar la fecha de salida
	}
	
	@Test (enabled=false)
	public void test4() throws InterruptedException{
		Reporter.log("Starting test4");
		logger.info("Starting test4");
		//HLWidget – Cambiar los menores
	}
	
	@Test (enabled=false)
	public void test5() throws InterruptedException{
		//HLWidget – Cambiar los adultos
	}
	
	@Test (enabled=false)
	public void test6() throws InterruptedException{
		//HLWidget – Cambiar habitaciónes
	}
	
	@Test (enabled=false)
	public void test7() throws InterruptedException{
		//HLWidget – Cambiar las edades
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
