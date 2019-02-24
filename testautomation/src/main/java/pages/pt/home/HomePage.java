package pages.pt.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import pages.pt.Pages;

public class HomePage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HomePage.class);
	
	public Widget widget;
	
	//Constructor
	public HomePage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		widget = new Widget(driver);
	}
	
	public void goToHard() {
		driver.get(FWConfig.BASEURL_PTCOMMX_PROD);
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("pricetravel."));
	}
	
	public void goTo() {
		Pages.topnav(driver).clickLogo();
	}

	public boolean isAt() {
		return driver.getTitle().contains("PriceTravel - Viaja fácil, sin pretextos");
	}
}