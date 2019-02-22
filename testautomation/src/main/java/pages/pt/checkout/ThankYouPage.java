package pages.pt.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import helpers.DDManager;
import utility.FWUtils;

public class ThankYouPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(ThankYouPage.class);
	private String currentURL;
	
	public ThankYouPage(WebDriver _driver) {
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		wait.until(ExpectedConditions.urlContains("completar-reservacion"));
		logger.trace("--->CurrentURL:"+driver.getCurrentUrl());
	}
	
	//---------Elements-------
	@FindBy(how=How.CSS, using="#content h1")
	WebElement message;
	
	@FindBy(how=How.CSS, using="#content .reservation-message .text-brand")
	WebElement locator;
	
	@FindBy(how=How.CSS, using="#content .reservation-message p > strong:nth-child(2)")
	WebElement email;
	
	@FindBy(how=How.CSS, using="#content .innerModule p a.but")
	WebElement buttonSeeReservation;
	
	@FindBy(how=How.CSS, using="#createAccount .btn")
	WebElement buttonCreateAccount;
	
	@FindBy(how=How.CSS, using="#content .lastModule .ap_submit")
	WebElement linkReserveOtherRoom;
	
	@FindBy(how=How.LINK_TEXT, using="Regresar a la página principal")
	WebElement linkReturnToMainPage;
	
	public void verifyCheckOutCompleted(){
		wait.until( ExpectedConditions.visibilityOf(locator));
		Assert.assertTrue(message.getText().contains("Gracias"),"LAF>>>No se encontro el mensaje de: Gracias");
		logger.info("---> Localizador: "+locator.getText());
		saveScreenShot();
		saveLocator();
	}
	
	public void checkCurrentURLPage() {
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("completar-reservacion"));
	}
	
	public void clickButtonSeeReservation() {		
		buttonSeeReservation.click();
	}
	
	public void saveScreenShot() {
		FWUtils.ScreenShot(driver, locator.getText(), FWConfig.PATH_SCREENSHOOT_LOCATORS);
	}
	
	public void saveLocator() {
		DDManager.saveLocator(locator.getText());
	}
}
