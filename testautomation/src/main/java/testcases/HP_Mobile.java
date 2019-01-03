package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import config.FrameworkConfig;
import helpers.BrowserFactory;

public class HP_Mobile {
	WebDriver driver;
	Logger logger = LogManager.getLogger(HP_Mobile.class);
	
	@BeforeTest
	public void prerequisitos() {
		//Aqui colocar todos los prerequisitos necesario antes de ejecutar las pruebas
		//Resetear variables
		//Eliminar contenido de algun archivo
		//Eliminar datos temporales
	}
	
	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.BROWSER_DEFAULT, FrameworkConfig.URL_PTCOMMX_PROD);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}
	
	@Test (enabled=true, priority = 2)
	public void mobile_Failure(){
		Reporter.log("Starting test test_Failure");
		logger.info("Starting test test_Failure");
		Assert.assertTrue(false); //Hacer fallar la prueba
	}
	
	@Test (enabled=true, priority = 1)
	public void mobile_Chrome(){
		Reporter.log("Starting test mobile_Chrome");
		logger.info("Starting test mobile_Chrome");
	}
	
	@Test (enabled=true, priority = 1)
	public void mobile_Firefox() {		
		Reporter.log("Starting test mobile_Firefox");
		logger.info("Starting test mobile_Firefox");
	}
	
	@Test (enabled=true, priority = 1)
	public void mobile_Safari(){		
		Reporter.log("Starting test mobile_Safari");
		logger.info("Starting test mobile_Safari");
	}
	
	@Test (enabled=true, priority = 1)
	public void login(){		
		Reporter.log("Starting test login");
		logger.info("Starting test login");
	}

	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		driver.close();
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
	}
}
