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
import workflows.HPHotelConCambios;

public class HP_HotelConCambios {
	WebDriver driver;
	Logger logger = LogManager.getLogger(HP_HotelConCambios.class);
	String gtestName = "";
	String gURL = "";
	String gbrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void prerequisitos(String url, String browser, ITestContext itc) {
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
		logger.trace("URL Seteada URL: "+gURL);
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
		
		//Seleccionando el browser que se usara para las pruebas
		gbrowser = browser;
		logger.trace("Browser Seteado: "+gbrowser);
		Assert.assertFalse(gbrowser.equals(""),"No se ha seteado un browser valido!");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		logger.info("***************************** Starting BeforeMethod **********************************");
		Reporter.log("Starting Browser");
		//Set Browser
		driver = BrowserFactory.StartBrowser(gbrowser, gURL);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test (enabled=false, priority = 1, groups = { "Group1" })
	public void test_HPHotelConCambioDeDestino() throws InterruptedException{
		Reporter.log("Starting test_HPHotelConCambioDeDestino");
		logger.info("Starting test_HPHotelConCambioDeDestino");
		HPHotelConCambios.HPHotelConCambioDeDestino(driver);
	}
	
	@Test (enabled=false, priority = 1, groups = { "Group1" })
	public void test_HPHotelConCambioDeFecha() throws InterruptedException{		
		Reporter.log("Starting test_HPHotelConCambioDeFecha");
		logger.info("Starting test_HPHotelConCambioDeFecha");
		HPHotelConCambios.HPHotelConCambioDeFecha(driver);
	}
	
	@Test (enabled=true, priority = 1, groups = { "Group1" })
	public void test_HPHotelConCambioDeOcupantes() throws InterruptedException{		
		Reporter.log("Starting test_HPHotelConCambioDeOcupantes");
		logger.info("Starting test_HPHotelConCambioDeOcupantes");
		HPHotelConCambios.HPHotelConCambioDeOcupantes(driver);
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
