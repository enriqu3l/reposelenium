package pages;

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

import config.FrameworkConfig;
import helpers.DDManager;
import utility.BasicUtils;

public class ThankYouPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(ThankYouPageF.class);
	private String currentURL;
	
	public ThankYouPageF(WebDriver _driver) {
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, 30),this);
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
		wait.until( ExpectedConditions.presenceOfElementLocated(BasicUtils.toByVal(locator)));
		wait.until( ExpectedConditions.visibilityOfElementLocated(BasicUtils.toByVal(locator)));
		//checkCurrentURLPage();
		Assert.assertTrue(message.getText().contains("Gracias"),"LAF>>>No se encontro el mensaje de: Gracias");
		logger.info("---> Localizador: "+locator.getText());
		BasicUtils.ScreenShot(driver, locator.getText(), FrameworkConfig.PATH_SCREENSHOOT_LOCATORS);
		DDManager.saveLocator(locator.getText());
	}
	
	public void checkCurrentURLPage() {
		//Este wait no es necesario dado que estoy usando AjaxElementFactory
		//wait.until(ExpectedConditions.urlContains("completar-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("LAF>>>completar-reservacion"));
	}
	
	public void seeReservationButtonClick() {		
		Button_seeReservation.click();
	}
	
	//Por ahora esta funcion no esta siendo utilizada
	public void SaveLocator() {
		//Assert.assertTrue(locator.getText());
		//logger.info("---> Localizador: "+locator.getText());
		//BasicUtils.ScreenShot(driver, locator.getText(), FrameworkConfig.SCREENSHOOT_PATH);
		//DDManager.saveLocator(locator.getText());
	}
}
