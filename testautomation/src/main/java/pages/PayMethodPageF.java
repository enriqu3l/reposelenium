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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import valueobjects.VOCreditCard;

public class PayMethodPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(PayMethodPageF.class);
	private String currentURL;
	
	PayMethodPageF(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, 30),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/reservar/forma-pago"));
	}
	
	//-----FormElements-----
	@FindBy(how=How.ID, id="cardNumber1")
	WebElement frmReserveInputCardNumber1;
	
	@FindBy(how=How.ID, id="cardMonthExpired1")
	WebElement frmReserveSelectCardMonthExpired1;
	
	@FindBy(how=How.ID, id="cardYearExpired1")
	WebElement frmReserveSelectCardYearExpired1;
	
	@FindBy(how=How.ID, id="cardHolderName1")
	WebElement frmReserveInputCardHolderName1;
	
	@FindBy(how=How.ID, id="cardSecurityCode1")
	WebElement frmReserveInputCardSecurityCode1;
	
	@FindBy(how=How.ID, id="cCountry1")
	WebElement frmReserveSelectCardCountry1;
	
	@FindBy(how=How.ID, id="cZipCode1")
	WebElement frmReserveInputCardZipCode1;
	
	@FindBy(how=How.CSS, css=".item-accordion.btn-accordion")
	WebElement frmReserveAccordionCodePromotions;
	
	@FindBy(how=How.ID, id="promocodeInput")
	WebElement frmReserveInputPromocode;
	
	@FindBy(how=How.ID, id="validatePromocode")
	WebElement frmReserveButtonValidatePromocode;
	
	@FindBy (how=How.CSS, css="#frmReserve .countdown-message .countdown-subtitle strong")
	WebElement frmReserveMessageLocator;
	
	//FinalButton
	@FindBy(how=How.ID, id="validatePayForms")
	WebElement frmReserveButtonValidatePayForms;
	
	
	//------------  pt.co Elements--------------
	@FindBy(how=How.ID, id="NationalIdCard1")
	WebElement frmReserveInputNationalIdCard1;
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("forma-pago"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("forma-pago"));
	}
	
	public void fillCreditForm(VOCreditCard voCreditCard) {
		logger.info("Starting FillCreditForm()");
		
		verifyLocatorIsDisplayed();
		
		frmReserveInputCardNumber1.sendKeys(Long.toString(voCreditCard.getCardNumber()));
		Select month = new Select (frmReserveSelectCardMonthExpired1);
		month.selectByVisibleText(voCreditCard.getMonth());
		Select year = new Select(frmReserveSelectCardYearExpired1);
		year.selectByVisibleText(voCreditCard.getYear());
		frmReserveInputCardHolderName1.sendKeys(voCreditCard.getHolderName());
		frmReserveInputCardSecurityCode1.sendKeys(Integer.toString(voCreditCard.getCCV()));
		Select country = new Select(frmReserveSelectCardCountry1);
		country.selectByValue(voCreditCard.getCountry());
		
		//Se agrego este If para el campo Cedula de Ciudadania
		//de la pagina de pt.co
		if(driver.getCurrentUrl().contains(".co/")){
			logger.info("FillCreditForm() - Sending national card number for pt.co");
			frmReserveInputNationalIdCard1.sendKeys("33444");
		}else {
			frmReserveInputCardZipCode1.sendKeys(Integer.toString(voCreditCard.getCP()));
		}
		logger.info("Ending FillCreditForm()");
	}
	
	private void verifyLocatorIsDisplayed() {
		//Aqui el codigo para verificar que se creo el Localizador
		
		//Por lo pronto solo reviso que el elemento "frmReserveMessageLocator" no este vacio
		if(frmReserveMessageLocator.getText().isEmpty()) {
			logger.error("El localizador no se muestra en la pagina. Localizador: "+frmReserveMessageLocator.getText());
			Assert.fail("LAF>>>El localizador no se muestra en la pagina");
		}else {
			logger.trace("Localizador: "+frmReserveMessageLocator.getText());
		}
	}

	public void clearCreditForm() {
		frmReserveInputCardNumber1.clear();
		Select month = new Select (frmReserveSelectCardMonthExpired1);
		month.deselectAll();
		Select year = new Select(frmReserveSelectCardYearExpired1);
		year.deselectAll();
		frmReserveInputCardHolderName1.clear();
		frmReserveInputCardSecurityCode1.clear();
		Select country = new Select(frmReserveSelectCardCountry1);
		country.deselectAll();
		frmReserveInputCardZipCode1.clear();
	}
	
	public void clickOnCompleteReservation() {
		frmReserveButtonValidatePayForms.click();
	}
	
	//En construccion!!
	public boolean selectPayWith1Card(int opc) {
		
		return false;
	}
	public boolean selectPayWith2Cards(int opc) {
		
		return false;
	}
	
	
	public void enterCodePromotions(String code) {
		frmReserveAccordionCodePromotions.click();
		frmReserveInputPromocode.sendKeys(code);
		frmReserveButtonValidatePromocode.click();
		
		//Falta verificar que el codigoProm exista y que sea valido
		//Assert.assertEquals("current", "expected");
	}
}
