package pages.pt.hoteles;

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

import config.FWConfig;
import utility.BasicUtils;
import valueobjects.VOClient;

public class ResDetailPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(ResDetailPage.class);
	private String currentURL;
	
	public ResDetailPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/reservar/detalles-reservacion"));
		logger.trace("--->CurrentURL:"+driver.getCurrentUrl());
	}
	
	//--------- Form Reservation Elements ---------
	@FindBy(how=How.ID, using="firstName")
	WebElement frmReserveInputFirsName;
	
	@FindBy(how=How.ID, using="lastName")
	WebElement frmReserveInputLastName;
	
	@FindBy(how=How.ID, using="eMail")
	WebElement frmReserveInputEmail;
	
	@FindBy(how=How.ID, using="conMail")
	@CacheLookup
	WebElement frmReserveInputConMail;
	
	@FindBy(how=How.ID, using="lada")
	@CacheLookup
	WebElement frmReserveInputLada;
	
	@FindBy(how=How.ID, using="phone")
	@CacheLookup
	WebElement frmReserveInputPhone;
	
	@FindBy(how=How.ID, using="mobile")
	@CacheLookup
	WebElement frmReserveInputMobile;
	
	@FindBy(how=How.ID, using="chkConfirm")
	@CacheLookup
	WebElement frmReserveCheckBoxChkConfirm;
	
	@FindBy(how=How.CSS, using="#frmReserve a.btn.btn-success.btn-form")
	@CacheLookup
	WebElement frmReserveButtonContinue;
	
	
	//--------- Resumen de la Reservacion ---------
	@FindBy(how=How.CSS, using=".aside .card-body .summary-description-name h5")
	@CacheLookup
	WebElement summaryHotelName;
	
	@FindBy(how=How.CSS, using=".aside .card-body .list-note li:nth-child(1)")
	WebElement summaryStartDate;
	
	@FindBy(how=How.CSS, using=".aside .card-body .list-note li:nth-child(2)")
	WebElement summaryEndDate;
	
	@FindBy(how=How.CSS, using=".aside .summary-product-rates .summary-table-rates .ap_summaryTotalAmount")
	WebElement summaryTotalAmount;
	
	
	//------------  pt.co Elements --------------
	@FindBy(how=How.ID, using="NationalIdCard")
	WebElement frmReserveInputNationalIdCard;
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("detalles-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("detalles-reservacion"));
	}
	
	public void clearAndFillForm(VOClient voClient) {
		clearForm();
		printSummaryInfo();
		fillForm(voClient);
	}
	
	private void printSummaryInfo() {
		logger.info("Summary Hotel Name: " + summaryHotelName.getText());
		logger.info("Summary Start Date: " + summaryStartDate.getText());
		logger.info("Summary End Date: " + summaryEndDate.getText());
		logger.info("Summary Total Amount: "+ summaryTotalAmount.getText());
	}

	public void fillForm(VOClient voClient) {
		logger.info("Starting fillForm()");
		wait.until(ExpectedConditions.visibilityOf(frmReserveInputFirsName));
		frmReserveInputFirsName.sendKeys(voClient.getName());
		frmReserveInputLastName.sendKeys(voClient.getLastName());
		frmReserveInputEmail.sendKeys(voClient.getEmail());
		frmReserveInputConMail.sendKeys(voClient.getEmail());
		frmReserveInputLada.sendKeys(Integer.toString(voClient.getLada()));
		frmReserveInputPhone.sendKeys(Integer.toString(voClient.getPhone()));
		frmReserveInputMobile.sendKeys(Long.toString(voClient.getCellphone()));
		
		//Se agrego este If para el campo Cedula de Ciudadania de la pagina de pt.co
		if(driver.getCurrentUrl().contains(".co/")) {
			logger.info("fillForm() - Sending national card number for pt.co");
			frmReserveInputNationalIdCard.sendKeys(Integer.toString(voClient.getNationalId()));
		}
		checkPolitics();
		logger.info("Ending fillForm()");
	}
	
	public void clearForm() {
		logger.info("Starting clearForm()");
		wait.until(ExpectedConditions.visibilityOf(frmReserveInputFirsName));
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
	
	public void clickOnContinue() {
		frmReserveButtonContinue.click();
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
}
