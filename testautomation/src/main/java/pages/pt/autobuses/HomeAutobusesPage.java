package pages.pt.autobuses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import pages.pt.Pages;
import pages.pt.general.TopNavigation;

public class HomeAutobusesPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HomeAutobusesPage.class);
	
	//Constructor
	public HomeAutobusesPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/autobuses"));
		logger.trace("--->CurrentURL:"+driver.getCurrentUrl());
	}
	
	public void goTo() {
		Pages.topnav(driver).clickAutobuses();
	}
	
	public boolean isAt() {
		return driver.getTitle().contains("Reserva autobuses");
	}
}
