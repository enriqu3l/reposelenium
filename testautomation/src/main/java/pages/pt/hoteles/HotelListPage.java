package pages.pt.hoteles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import pages.pt.general.TopNavigation;
import pages.pt.hoteles.components.HLFilters;
import pages.pt.hoteles.components.HLList;
import pages.pt.hoteles.components.HLPaging;
import pages.pt.hoteles.components.HLWidget;

/**
 * Esta clase contiene todos los elementos, acciones y verificaciones necesarios para la pagina SPA Hotel List
 *
 */
public class HotelListPage extends HLGlobal{
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HotelListPage.class);
	
	public TopNavigation topnav;
	public HLWidget widget;
	public HLList list;
	public HLPaging paging;
	public HLFilters filters;
	
	//Constructor
	public HotelListPage(WebDriver _driver){
		super(_driver);
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		
		topnav = new TopNavigation(driver);
		widget = new HLWidget(driver);
		list = new HLList(driver);
		paging = new HLPaging(driver);
		filters = new HLFilters(driver);
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/hoteles"));
		//Esperar a que el contenido este cargado
		waitForContentToBeReady();
	}

	public boolean isAt() {
		//Verificar que estoy en esta pagina
		return driver.getTitle().contains("Hoteles en");
	}
}
