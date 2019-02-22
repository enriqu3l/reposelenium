package pages.interjet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import valueobjects.VOCreditCard;

public class PayMethodPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(PayMethodPage.class);
	private String currentURL;
	
	PayMethodPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_INTERJET);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_INTERJET),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/reservar-ingresar-pago"));
	}
	
	//-----FormElements-----
	@FindBy(how=How.ID, id="creditcard-number")
	WebElement frmReserveInputCardNumber;
	
	@FindBy(how=How.ID, id="creditcard-expmonth")
	WebElement frmReserveSelectCardMonthExpired;
	
	@FindBy(how=How.ID, id="creditcard-expyear")
	WebElement frmReserveSelectCardYearExpired;
	
	@FindBy(how=How.ID, id="cardHolder")
	WebElement frmReserveInputCardHolderName;
	
	@FindBy(how=How.ID, id="securityCode")
	WebElement frmReserveInputCardSecurityCode;
	
	@FindBy(how=How.ID, id="PaymentInformation.creditCardInformation.zipcode")
	WebElement frmReserveInputCardZipCode;
	
	@FindBy (how=How.CSS, css="#ErrorMessage .alert strong")
	WebElement messageLocator;
	
	@FindBy(how=How.ID, id="completeReservationButton")
	WebElement buttonCompleteReservation;
	
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("forma-pago"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("forma-pago"));
	}
	
	public void fillCreditForm(VOCreditCard voCreditCard) {
		logger.info("Starting FillCreditForm()");
		
		verifyLocatorIsDisplayed();
		
		frmReserveInputCardNumber.sendKeys(Long.toString(voCreditCard.getCardNumber()));
		Select month = new Select (frmReserveSelectCardMonthExpired);
		month.selectByVisibleText(voCreditCard.getMonth());
		Select year = new Select(frmReserveSelectCardYearExpired);
		year.selectByVisibleText(voCreditCard.getYear());
		frmReserveInputCardHolderName.sendKeys(voCreditCard.getHolderName());
		frmReserveInputCardSecurityCode.sendKeys(Integer.toString(voCreditCard.getCCV()));
		frmReserveInputCardZipCode.sendKeys(Integer.toString(voCreditCard.getCP()));
		logger.info("Ending FillCreditForm()");
	}
	
	public void verifyLocatorIsDisplayed() {
		//Aqui el codigo para verificar que se creo el Localizador
		
		//Por lo pronto solo reviso que el elemento "frmReserveMessageLocator" no este vacio
		if(messageLocator.getText().isEmpty()) {
			logger.error("El localizador no se muestra en la pagina. Localizador:"+messageLocator.getText());
			Assert.fail("LAF>>>El localizador no se muestra en la pagina");
		}else {
			logger.trace("Localizador: "+messageLocator.getText());
		}
	}

	public void clearCreditForm() {
		frmReserveInputCardNumber.clear();
		Select month = new Select (frmReserveSelectCardMonthExpired);
		month.deselectAll();
		Select year = new Select(frmReserveSelectCardYearExpired);
		year.deselectAll();
		frmReserveInputCardHolderName.clear();
		frmReserveInputCardSecurityCode.clear();
		frmReserveInputCardZipCode.clear();
	}
	
	public void clickCompleteReservation() {
		buttonCompleteReservation.click();
	}
}
