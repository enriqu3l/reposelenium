package pages.tb;

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
	private String currentURL;
	private static Logger logger = LogManager.getLogger(ThankYouPage.class);
	
	public ThankYouPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,FWConfig.WAIT_TB);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, FWConfig.WAITPF_TB),this);
	}
	
	//---------Elements-------
	@FindBy(how=How.CSS, css="#content h1")
	WebElement message;
	
	@FindBy(how=How.CSS, css="#content .reservation-message .text-brand")
	WebElement locator;
	
	@FindBy(how=How.CSS, css="#content .reservation-message p > strong:nth-child(2)")
	WebElement email;
	
	@FindBy(how=How.CSS, css="#content .innerModule p a.but")
	WebElement Button_seeReservation;
	
	@FindBy(how=How.CSS, css="#createAccount .btn")
	WebElement Button_createAccount;
	
	@FindBy(how=How.CSS, css="#content .lastModule .ap_submit")
	WebElement Link_reserveOtherRoom;
	
	@FindBy(how=How.LINK_TEXT, linkText="Regresar a la página principal")
	WebElement Link_returnToMainPage;
	
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
		Button_seeReservation.click();
	}
	
	public void saveScreenShot() {
		FWUtils.ScreenShot(driver, locator.getText(), FWConfig.PATH_SCREENSHOOT_LOCATORS);
	}
	
	public void saveLocator() {
		DDManager.saveLocator(locator.getText());
	}
}
