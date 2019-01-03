package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.BasicUtils;

public class ResDetailPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(ResDetailPageF.class);
	private String currentURL;
	
	public ResDetailPageF(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, 20),this);
		logger.info("Launched initElements");
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/reservar/detalles-reservacion"));
	}
	
	//-----FormElements-----
	@FindBy(how=How.ID, id="firstName")
	WebElement Input_firsName;
	
	@FindBy(how=How.ID, id="lastName")
	WebElement Input_lastName;
	
	@FindBy(how=How.ID, id="eMail")
	WebElement Input_eMail;
	
	@FindBy(how=How.ID, id="conMail")
	@CacheLookup
	WebElement Input_conMail;
	
	@FindBy(how=How.ID, id="lada")
	@CacheLookup
	WebElement Input_lada;
	
	@FindBy(how=How.ID, id="phone")
	@CacheLookup
	WebElement Input_phone;
	
	@FindBy(how=How.ID, id="mobile")
	@CacheLookup
	WebElement Input_mobile;
	
	@FindBy(how=How.ID, id="chkConfirm")
	@CacheLookup
	WebElement CheckBox_chkConfirm;
	
	@FindBy(how=How.CSS, css="#frmReserve a.btn.btn-success.btn-form")
	@CacheLookup
	WebElement Button_continue;
	
	//-----Information-----
	@FindBy(how=How.CSS, id=".aside .card-body .summary-description-name h5")
	@CacheLookup
	WebElement descriptionName;
	
	//------------  pt.co Elements--------------
		@FindBy(how=How.ID, id="NationalIdCard")
		WebElement Input_nationalIdCard;
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("detalles-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("detalles-reservacion"));
	}
	
	//Aun no implemento assertions en esta pagina
	public void BasicAssertions() {
		Assert.assertEquals(descriptionName.getText(), "");
	}
	
	public void ClearFillandContinue() {
		clearForm();
		fillFormDefaultData();
		clickOnContinue();
	}
	
	public void fillFormDefaultData() {
		logger.info("Starting fillFormDefaultData()");
		wait.until(ExpectedConditions.presenceOfElementLocated(BasicUtils.toByVal(Input_firsName)));
		Input_firsName.sendKeys("Enrique");
		Input_lastName.sendKeys("Carrillo");
		Input_eMail.sendKeys("enriquecarrillo119999@gmail.com");
		Input_conMail.sendKeys("enriquecarrillo119999@gmail.com");
		Input_lada.sendKeys("33");
		Input_phone.sendKeys("33443344");
		Input_mobile.sendKeys("3344334433");
		
		//Se agrego este If para el campo Cedula de Ciudadania
		//de la pagina de pt.co
		if(driver.getCurrentUrl().contains(".co/")) {
			logger.info("fillFormDefaultData() - Sending national card number for pt.co");
			Input_nationalIdCard.sendKeys("33444");
		}
		checkPolitics();
		logger.info("Starting fillFormDefaultData()");
	}
	
	public void clearForm() {
		logger.info("Starting clearForm()");
		wait.until(ExpectedConditions.presenceOfElementLocated(BasicUtils.toByVal(Input_firsName)));
		Input_firsName.clear();
		Input_lastName.clear();
		Input_eMail.clear();
		Input_conMail.clear();
		Input_lada.clear();
		Input_phone.clear();
		Input_mobile.clear();
		uncheckPolitics();
		logger.info("Ending clearForm()");
	}
	
	public void checkPolitics() {
		if(!CheckBox_chkConfirm.isSelected()) {
			logger.warn("checkPolitics() - checkbox was selected!");
			CheckBox_chkConfirm.click();
		}
	}
	
	public void uncheckPolitics() {
		if(CheckBox_chkConfirm.isSelected()) {
			CheckBox_chkConfirm.click();
		}
	}
	
	public void clickOnContinue() {
		Button_continue.click();
	}
}
