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
import valueobjects.VOClient;

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
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/reservar/detalles-reservacion"));
	}
	
	//--------- Form Reservation Elements ---------
	@FindBy(how=How.ID, id="firstName")
	WebElement frmReserveInputFirsName;
	
	@FindBy(how=How.ID, id="lastName")
	WebElement frmReserveInputLastName;
	
	@FindBy(how=How.ID, id="eMail")
	WebElement frmReserveInputEmail;
	
	@FindBy(how=How.ID, id="conMail")
	@CacheLookup
	WebElement frmReserveInputConMail;
	
	@FindBy(how=How.ID, id="lada")
	@CacheLookup
	WebElement frmReserveInputLada;
	
	@FindBy(how=How.ID, id="phone")
	@CacheLookup
	WebElement frmReserveInputPhone;
	
	@FindBy(how=How.ID, id="mobile")
	@CacheLookup
	WebElement frmReserveInputMobile;
	
	@FindBy(how=How.ID, id="chkConfirm")
	@CacheLookup
	WebElement frmReserveCheckBoxChkConfirm;
	
	@FindBy(how=How.CSS, css="#frmReserve a.btn.btn-success.btn-form")
	@CacheLookup
	WebElement frmReserveButtonContinue;
	
	
	//--------- Resumen de la Reservacion ---------
	@FindBy(how=How.CSS, id=".aside .card-body .summary-description-name h5")
	@CacheLookup
	WebElement descriptionName;
	
	
	//------------  pt.co Elements --------------
	@FindBy(how=How.ID, id="NationalIdCard")
	WebElement frmReserveInputNationalIdCard;
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("detalles-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("detalles-reservacion"));
	}
	
	//Aun no implemento assertions en esta pagina
	public void BasicAssertions() {
		Assert.assertEquals(descriptionName.getText(), "");
	}
	
	public void clearAndFillForm(VOClient voClient) {
		clearForm();
		fillForm(voClient);
	}
	
	public void fillForm(VOClient voClient) {
		logger.info("Starting fillFormDefaultData()");
		wait.until(ExpectedConditions.visibilityOf(frmReserveInputFirsName));
		frmReserveInputFirsName.sendKeys(voClient.getName());
		frmReserveInputLastName.sendKeys(voClient.getLastName());
		frmReserveInputEmail.sendKeys(voClient.getEmail());
		frmReserveInputConMail.sendKeys(voClient.getEmail());
		frmReserveInputLada.sendKeys(Integer.toString(voClient.getLada()));
		frmReserveInputPhone.sendKeys(Integer.toString(voClient.getPhone()));
		frmReserveInputMobile.sendKeys(Long.toString(voClient.getCellphone()));
		
		//Se agrego este If para el campo Cedula de Ciudadania
		//de la pagina de pt.co
		if(driver.getCurrentUrl().contains(".co/")) {
			logger.info("fillForm() - Sending national card number for pt.co");
			frmReserveInputNationalIdCard.sendKeys(Integer.toString(voClient.getNationalId()));
		}
		checkPolitics();
		logger.info("Starting fillFormDefaultData()");
	}
	
	public void fillForm2() {
		logger.info("Starting fillFormDefaultData()");
		wait.until(ExpectedConditions.presenceOfElementLocated(BasicUtils.toByVal(frmReserveInputFirsName)));
		frmReserveInputFirsName.sendKeys("Enrique");
		frmReserveInputLastName.sendKeys("Carrillo");
		frmReserveInputEmail.sendKeys("enriquecarrillo119999@gmail.com");
		frmReserveInputConMail.sendKeys("enriquecarrillo119999@gmail.com");
		frmReserveInputLada.sendKeys("33");
		frmReserveInputPhone.sendKeys("33443344");
		frmReserveInputMobile.sendKeys("3344334433");
		
		//Se agrego este If para el campo Cedula de Ciudadania
		//de la pagina de pt.co
		if(driver.getCurrentUrl().contains(".co/")) {
			logger.info("fillFormDefaultData() - Sending national card number for pt.co");
			frmReserveInputNationalIdCard.sendKeys("33444");
		}
		checkPolitics();
		logger.info("Starting fillFormDefaultData()");
	}
	
	public void clearForm() {
		logger.info("Starting clearForm()");
		wait.until(ExpectedConditions.presenceOfElementLocated(BasicUtils.toByVal(frmReserveInputFirsName)));
		frmReserveInputFirsName.clear();
		frmReserveInputLastName.clear();
		frmReserveInputEmail.clear();
		frmReserveInputConMail.clear();
		frmReserveInputLada.clear();
		frmReserveInputPhone.clear();
		frmReserveInputMobile.clear();
		uncheckPolitics();
		logger.info("Ending clearForm()");
	}
	
	public void checkPolitics() {
		if(!frmReserveCheckBoxChkConfirm.isSelected()) {
			logger.warn("checkPolitics() - checkbox was selected!");
			frmReserveCheckBoxChkConfirm.click();
		}
	}
	
	public void uncheckPolitics() {
		if(frmReserveCheckBoxChkConfirm.isSelected()) {
			frmReserveCheckBoxChkConfirm.click();
		}
	}
	
	public void clickOnContinue() {
		frmReserveButtonContinue.click();
	}
}
