package testcases.pt.smook;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.pt.Pages;
import testbases.pt.TB_Smook;

public class TC_Navigation extends TB_Smook{

	@Test (enabled=true, priority = 1)
	public void goToHomePageTest(){
		Reporter.log("Starting GoToHomePageTest");
		logger.info("Starting GoToHomePageTest");
		Pages.home();
		Assert.assertTrue(Pages.home().isAt());
	}
	
	@Test (enabled=true, priority = 2)
	public void goToHotelesTest(){
		Reporter.log("Starting GoToHotelesTest");
		logger.info("Starting GoToHotelesTest");
		Pages.home().goTo();
		Pages.topnav().clickHoteles();
		Assert.assertTrue(Pages.hoteles().isAt());
		logger.info("Ending GoToHotelesTest");
	}
	
	@Test (enabled=true, priority = 3)
	public void goToPaquetesTest(){
		Reporter.log("Starting GoToPaquetesTest");
		logger.info("Starting GoToPaquetesTest");
		Pages.home().goTo();
		Pages.topnav().clickPaquetes();
		Assert.assertTrue(Pages.paquetes().isAt());
		logger.info("Ending GoToPaquetesTest");
	}
	
	@Test (enabled=true, priority = 4)
	public void goToVuelosTest(){
		Reporter.log("Starting GoToVuelosTest");
		logger.info("Starting GoToVuelosTest");
		Pages.home().goTo();
		Pages.topnav().clickVuelos();
		Assert.assertTrue(Pages.vuelos().isAt());
		logger.info("Ending GoToVuelosTest");
	}
	
	@Test (enabled=true, priority = 5)
	public void goToTrasladosTest(){
		Reporter.log("Starting GoToTrasladosTest");
		logger.info("Starting GoToTrasladosTest");
		Pages.home().goTo();
		Pages.topnav().clickTraslados();
		Assert.assertTrue(Pages.traslados().isAt());
		logger.info("Ending GoToPaquetesTest");
	}
	
	@Test (enabled=true, priority = 6)
	public void goToToursTest(){
		Reporter.log("Starting GoToToursTest");
		logger.info("Starting GoToToursTest");
		Pages.home().goTo();
		Pages.topnav().clickTours();
		Assert.assertTrue(Pages.tours().isAt());
		logger.info("Ending GoToToursTest");
	}
	
	@Test (enabled=true, priority = 7)
	public void goToAutosTest(){
		Reporter.log("Starting GoToAutosTest");
		logger.info("Starting GoToAutosTest");
		Pages.home().goTo();
		Pages.topnav().clickAutos();
		Assert.assertTrue(Pages.autos().isAt());
		logger.info("Ending GoToAutosTest");
	}
	
	@Test (enabled=true, priority = 8)
	public void goToAutobusesTest(){
		Reporter.log("Starting GoToAutobusesTest");
		logger.info("Starting GoToAutobusesTest");
		Pages.home().goTo();
		Pages.topnav().clickAutobuses();
		Assert.assertTrue(Pages.autobuses().isAt());
		logger.info("Ending GoToAutobusesTest");
	}
	
	@Test (enabled=true, priority = 8)
	public void goToCrucerosTest(){
		Reporter.log("Starting GoToCrucerosTest");
		logger.info("Starting GoToCrucerosTest");
		Pages.home().goTo();
		Pages.topnav().clickCruceros();
		Assert.assertTrue(Pages.cruceros().isAt());
		logger.info("Ending GoToCrucerosTest");
	}
	
	@Test (enabled=true, priority = 9)
	public void goToOfertasTest(){
		Reporter.log("Starting GoToOfertasTest");
		logger.info("Starting GoToOfertasTest");
		Pages.home().goTo();
		Pages.topnav().clickOfertas();
		Assert.assertTrue(Pages.ofertas().isAt());
		logger.info("Ending GoToOfertasTest");
	}
	
	@Test (enabled=true, priority = 10)
	public void goToGruposTest(){
		Reporter.log("Starting GoToGruposTest");
		logger.info("Starting GoToGruposTest");
		Pages.home().goTo();
		Pages.topnav().clickGrupos();
		Assert.assertTrue(Pages.grupos().isAt());
		logger.info("Ending GoToGruposTest");
	}
}
