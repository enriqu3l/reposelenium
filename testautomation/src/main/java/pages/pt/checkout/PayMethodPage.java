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
	
	public PayMethodPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/reservar/forma-pago"));
		logger.trace("--->CurrentURL:"+driver.getCurrentUrl());
	}
	
	//-----FormElements-----
	@FindBy(how=How.ID, using="cardNumber1")
	WebElement frmReserveInputCardNumber1;
	
	@FindBy(how=How.ID, using="cardMonthExpired1")
	WebElement frmReserveSelectCardMonthExpired1;
	
	@FindBy(how=How.ID, using="cardYearExpired1")
	WebElement frmReserveSelectCardYearExpired1;
	
	@FindBy(how=How.ID, using="cardHolderName1")
	WebElement frmReserveInputCardHolderName1;
	
	@FindBy(how=How.ID, using="cardSecurityCode1")
	WebElement frmReserveInputCardSecurityCode1;
	
	@FindBy(how=How.ID, using="cCountry1")
	WebElement frmReserveSelectCardCountry1;
	
	@FindBy(how=How.ID, using="cZipCode1")
	WebElement frmReserveInputCardZipCode1;
	
	@FindBy(how=How.CSS, using=".item-accordion.btn-accordion")
	WebElement frmReserveAccordionCodePromotions;
	
	@FindBy(how=How.ID, using="promocodeInput")
	WebElement frmReserveInputPromocode;
	
	@FindBy(how=How.ID, using="validatePromocode")
	WebElement frmReserveButtonValidatePromocode;
	
	@FindBy (how=How.CSS, using="#frmReserve .countdown-message .countdown-subtitle strong")
	WebElement frmReserveMessageLocator;
	
	//FinalButton
	@FindBy(how=How.ID, using="validatePayForms")
	WebElement frmReserveButtonValidatePayForms;
	
	
	//------------  pt.co Elements--------------
	@FindBy(how=How.ID, using="NationalIdCard1")
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
