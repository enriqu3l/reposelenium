package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import workflows.HPAllProducts;

public class HP_PTALL {
	WebDriver driver;
	Logger logger = LogManager.getLogger(HP_PTALL.class);
	String gtestName = "";
	String gURL = "";
	
	@BeforeTest
	@Parameters({"url"})
	public void prerequisitos(String url, ITestContext itc) {
		logger.info("***************************** Starting BeforeTest **********************************");
		Reporter.log("Starting BeforeTest");
		logger.info("Starting BeforeTest");
		
		//Metodo 1 - Leyendo el nombre del test
		gtestName = itc.getName();
		if(gtestName.equals("HP_PTCOMMX")) {gURL = FrameworkConfig.URL_PTCOMMX_PROD;}
		else if(gtestName.equals("HP_PTCOM")) {gURL = FrameworkConfig.URL_PTCOM_PROD;}
		else if(gtestName.equals("HP_PTCO")) {gURL = FrameworkConfig.URL_PTCO_PROD;}
		
		//Metodo 2 - Obteniendo un parametro
		//URL = url;
		logger.trace("URL: "+gURL);
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		logger.info("***************************** Starting BeforeMethod **********************************");
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(FrameworkConfig.DEFAULTBROWSER, gURL);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	/*
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}*/
	
	@Test (enabled=true, priority = 1)
	public void HappyPath_HotelDefault() throws InterruptedException{
		Reporter.log("Starting test HappyPath_HotelDefault");
		logger.info("Starting test HappyPath_HotelDefault");
		HPAllProducts.HPHotelDefault(driver);
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
