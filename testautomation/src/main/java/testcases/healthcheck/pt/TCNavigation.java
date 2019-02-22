package testcases.healthcheck.pt;

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

import config.FWConfig;
import helpers.BrowserFactory;
import helpers.DDManager;
import pages.pt.Pages;
import pages.pt.PagesNew;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TCNavigation {
	WebDriver driver;
	Logger logger = LogManager.getLogger(TCNavigation.class);
	String gtestName = "";
	String gURL = "";
	String gbrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void setConfigVariables(String url, String browser, ITestContext itc) {
		logger.info("***************************** Starting BeforeTest **********************************");
		Reporter.log("Starting BeforeTest");
		logger.info("Starting BeforeTest");
		if(url.contains("ptcommx")) {gURL = url;}
		
		gURL = url;
		gbrowser = browser;
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
		Assert.assertFalse(gbrowser.equals(""),"No se ha seteado un browser valido!");
		logger.trace("URL Seteada: "+gURL);
		logger.trace("Browser Seteado: "+gbrowser);
		
		driver = BrowserFactory.startBrowser(gbrowser, gURL);
		itc.setAttribute("WebDriver", driver);
		PagesNew.setDriver(driver);
	}
	
	@BeforeMethod
	public void prerequisites(ITestContext itc) {
		logger.info("***************************** Starting BeforeMethod **********************************");
	}
	
	/*
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_1", "Test@123" }};
	}*/
	
	@Test (enabled=true, priority = 1)
	public void GoToHomePageTest(){
		Reporter.log("Starting GoToHomePageTest");
		logger.info("Starting GoToHomePageTest");
		PagesNew.home().isAt();
		Assert.assertTrue(PagesNew.home().isAt());
	}
	
	@Test (enabled=true, priority = 2)
	public void GoToHotelesTest(){
		Reporter.log("Starting GoToHotelesTest");
		logger.info("Starting GoToHotelesTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickHoteles();
		Assert.assertTrue(PagesNew.hoteles().isAt());
		logger.info("Ending GoToHotelesTest");
	}
	
	@Test (enabled=true, priority = 3)
	public void GoToPaquetesTest(){
		Reporter.log("Starting GoToPaquetesTest");
		logger.info("Starting GoToPaquetesTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickPaquetes();
		Assert.assertTrue(PagesNew.paquetes().isAt());
		logger.info("Ending GoToPaquetesTest");
	}
	
	@Test (enabled=true, priority = 4)
	public void GoToVuelosTest(){
		Reporter.log("Starting GoToVuelosTest");
		logger.info("Starting GoToVuelosTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickVuelos();
		Assert.assertTrue(PagesNew.vuelos().isAt());
		logger.info("Ending GoToVuelosTest");
	}
	
	@Test (enabled=true, priority = 5)
	public void GoToTrasladosTest(){
		Reporter.log("Starting GoToTrasladosTest");
		logger.info("Starting GoToTrasladosTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickTraslados();
		Assert.assertTrue(PagesNew.traslados().isAt());
		logger.info("Ending GoToPaquetesTest");
	}
	
	@Test (enabled=true, priority = 6)
	public void GoToToursTest(){
		Reporter.log("Starting GoToToursTest");
		logger.info("Starting GoToToursTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickTours();
		Assert.assertTrue(PagesNew.tours().isAt());
		logger.info("Ending GoToToursTest");
	}
	
	@Test (enabled=true, priority = 7)
	public void GoToAutosTest(){
		Reporter.log("Starting GoToAutosTest");
		logger.info("Starting GoToAutosTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickAutos();
		Assert.assertTrue(PagesNew.autos().isAt());
		logger.info("Ending GoToAutosTest");
	}
	
	@Test (enabled=true, priority = 8)
	public void GoToAutobusesTest(){
		Reporter.log("Starting GoToAutobusesTest");
		logger.info("Starting GoToAutobusesTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickAutobuses();
		Assert.assertTrue(PagesNew.autobuses().isAt());
		logger.info("Ending GoToAutobusesTest");
	}
	
	@Test (enabled=true, priority = 8)
	public void GoToCrucerosTest(){
		Reporter.log("Starting GoToCrucerosTest");
		logger.info("Starting GoToCrucerosTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickCruceros();
		Assert.assertTrue(PagesNew.cruceros().isAt());
		logger.info("Ending GoToCrucerosTest");
	}
	
	@Test (enabled=true, priority = 9)
	public void GoToOfertasTest(){
		Reporter.log("Starting GoToOfertasTest");
		logger.info("Starting GoToOfertasTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickOfertas();
		Assert.assertTrue(PagesNew.ofertas().isAt());
		logger.info("Ending GoToOfertasTest");
	}
	
	@Test (enabled=true, priority = 10)
	public void GoToGruposTest(){
		Reporter.log("Starting GoToGruposTest");
		logger.info("Starting GoToGruposTest");
		PagesNew.home().goTo();
		PagesNew.topnav().clickGrupos();
		Assert.assertTrue(PagesNew.grupos().isAt());
		logger.info("Ending GoToGruposTest");
	}

	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
		driver.quit();
	}
}
