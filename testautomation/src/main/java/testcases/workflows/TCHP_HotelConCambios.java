package testcases.workflows;

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
import tests.workflows.hoteles.WHPHotelConCambios;

public class TCHP_HotelConCambios {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCHP_HotelConCambios.class);
	String gtestName = "";
	String gURL = "";
	String gbrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void prerequisitos(String url, String browser, ITestContext itc) {
		logger.info("***************************** Starting @BeforeTest **********************************");
		Reporter.log("Starting BeforeTest");
		logger.info("Starting BeforeTest");
		
		//Metodo 1 - Leyendo el nombre del test
		gtestName = itc.getName();
		if(gtestName.equals("HPPTCOMMX")) {gURL = FrameworkConfig.URL_PTCOMMX_PROD;}
		else if(gtestName.equals("HPPTCOM")) {gURL = FrameworkConfig.URL_PTCOM_PROD;}
		else if(gtestName.equals("HPPTCO")) {gURL = FrameworkConfig.URL_PTCO_PROD;}
		
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
		logger.info("***************************** Starting @BeforeMethod **********************************");
		Reporter.log("Starting Browser");
		driver = BrowserFactory.StartBrowser(gbrowser, gURL);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeDestino() throws InterruptedException{
		Reporter.log("Starting @HPHotelConCambioDeDestino");
		logger.info("Starting @HPHotelConCambioDeDestino");
		WHPHotelConCambios.HPHotelConCambioDeDestino(driver);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeFecha() throws InterruptedException{		
		Reporter.log("Starting @HPHotelConCambioDeFecha");
		logger.info("Starting @HPHotelConCambioDeFecha");
		WHPHotelConCambios.HPHotelConCambioDeFecha(driver);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeOcupantes() throws InterruptedException{		
		Reporter.log("Starting @HPHotelConCambioDeOcupantes");
		logger.info("Starting @HPHotelConCambioDeOcupantes");
		WHPHotelConCambios.HPHotelConCambioDeOcupantes(driver);
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