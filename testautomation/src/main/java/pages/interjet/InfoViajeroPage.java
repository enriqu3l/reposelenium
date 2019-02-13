package pages.interjet;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FrameworkConfig;
import helpers.WaitFor;
import utility.BasicUtils;
import valueobjects.VOClient;

public class InfoViajeroPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(InfoViajeroPage.class);
	private String currentURL;
	
	public InfoViajeroPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FrameworkConfig.WAIT_INTERJET);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FrameworkConfig.WAITPF_INTERJET),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/reservar/informacion-viajero"));
	}
	
	//--------- Form Reserve ---------------------
	@FindBy(how=How.CSS, css="#frmReserve #travelerContainer .formTitle")
	WebElement frmReserveFormTitle;
	
	@FindBy(how=How.ID, id="acceptedPolicies")
	@CacheLookup
	WebElement frmReserveCheckBoxPolicies;
	
	@FindBy(how=How.CSS, css=".checkoutFooter a.btn")
	@CacheLookup
	WebElement frmReserveButtonContinue;
	
	//--------- Form Reserve ---------
	@FindBy(how=How.ID, id="Titularname")
	WebElement frmReserveInputTitularName;
	
	@FindBy(how=How.ID, id="TitularlastName")
	WebElement frmReserveInputTitularLastName;
	
	@FindBy(how=How.ID, id="Titularemail")
	WebElement frmReserveInputTitularEmail;
	
	@FindBy(how=How.ID, id="TitularconfirmEmail")
	@CacheLookup
	WebElement frmReserveInputTitularConMail;
	
	@FindBy(how=How.ID, id="Titulartel")
	@CacheLookup
	WebElement frmReserveInputTitularPhone;
	
	@FindBy(how=How.ID, id="Titularcellphone")
	@CacheLookup
	WebElement frmReserveInputTitularMobile;
	
	
	//--------- Form Reserve With Insurance ---------
	@FindBy(how=How.ID, id="adultTitle0")
	WebElement frmReserveSelectAdultTitle;
	
	@FindBy(how=How.ID, id="Adultoname")
	WebElement frmReserveInputAdultoName;
	
	@FindBy(how=How.ID, id="AdultolastName")
	WebElement frmReserveInputLastName;
	
	@FindBy(how=How.ID, id="birthDateDayTitular")
	WebElement frmReserveSelectDirthdateDay;
	
	@FindBy(how=How.ID, id="birthDateMonthTitular")
	WebElement frmReserveSelectDirthdateMonth;
	
	@FindBy(how=How.ID, id="birthDateYearTitular")
	WebElement frmReserveSelectDirthdateYear;
	
	@FindBy(how=How.ID, id="Adultoemail")
	WebElement frmReserveInputEmail;
	
	@FindBy(how=How.ID, id="AdultoconfirmEmail")
	@CacheLookup
	WebElement frmReserveInputConMail;
	
	@FindBy(how=How.ID, id="Adultotel")
	@CacheLookup
	WebElement frmReserveInputPhone;
	
	@FindBy(how=How.ID, id="Adultocellphone")
	@CacheLookup
	WebElement frmReserveInputMobile;
	
	
	//------------  pt.co Elements --------------
	@FindBy(how=How.ID, id="NationalIdCard")
	WebElement frmReserveInputNationalIdCard;
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("detalles-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("detalles-reservacion"));
	}
	
	public void clearAndFillForm(VOClient voClient) {
		clearForm();
		fillForm(voClient);
	}
	
	public void fillForm(VOClient voClient) {
		waitForContentToBeReady();
		logger.info("Starting fillForm()");
		wait.until(ExpectedConditions.visibilityOf(frmReserveInputTitularName));
		frmReserveInputTitularName.sendKeys(voClient.getName());
		frmReserveInputTitularLastName.sendKeys(voClient.getLastName());
		//Me falta agregar la fecha de nacimiento
		frmReserveInputTitularEmail.sendKeys(voClient.getEmail());
		frmReserveInputTitularConMail.sendKeys(voClient.getEmail());
		String phoneNumber = Integer.toString(voClient.getLada()) + Integer.toString(voClient.getPhone());
		frmReserveInputTitularPhone.sendKeys(phoneNumber);
		frmReserveInputTitularMobile.sendKeys(Long.toString(voClient.getCellphone()));
		checkPolitics();
		logger.info("Ending fillForm()");
	}

	public void fillFormWithInsurance(VOClient voClient) {
		waitForContentToBeReady();
		logger.info("Starting fillForm()");
		wait.until(ExpectedConditions.visibilityOf(frmReserveInputAdultoName));
		Select adultTitle = new Select(frmReserveSelectAdultTitle);
		String at="";
		if(voClient.getGender().equals("M")){at="Sr";}else if(voClient.getGender().equals("F")){at="Sra";}
		adultTitle.selectByValue(at);
		frmReserveInputAdultoName.sendKeys(voClient.getName());
		frmReserveInputLastName.sendKeys(voClient.getLastName());
		Select birthdayDay = new Select(frmReserveSelectDirthdateDay);
		int day = voClient.getBornDateLocalDateFormat().getDayOfMonth();
		birthdayDay.selectByVisibleText(Integer.toString(day));
		Select birthdayMonth = new Select(frmReserveSelectDirthdateMonth);
		int month = voClient.getBornDateLocalDateFormat().getMonthOfYear();
		birthdayMonth.selectByVisibleText(Integer.toString(month));
		Select birthdayYear = new Select(frmReserveSelectDirthdateYear);
		int year = voClient.getBornDateLocalDateFormat().getYear();
		birthdayMonth.selectByVisibleText(Integer.toString(year));
		//Me falta agregar la fecha de nacimiento
		frmReserveInputEmail.sendKeys(voClient.getEmail());
		frmReserveInputConMail.sendKeys(voClient.getEmail());
		String phoneNumber = Integer.toString(voClient.getLada()) + Integer.toString(voClient.getPhone());
		frmReserveInputPhone.sendKeys(phoneNumber);
		frmReserveInputMobile.sendKeys(Long.toString(voClient.getCellphone()));
		checkPolitics();
		logger.info("Ending fillForm()");
	}
	
	public void clearForm() {
		waitForContentToBeReady();
		logger.info("Starting clearForm()");
		frmReserveInputTitularName.clear();
		frmReserveInputTitularLastName.clear();
		//Me falta agregar la fecha de nacimiento
		frmReserveInputTitularEmail.clear();
		frmReserveInputTitularConMail.clear();
		frmReserveInputTitularPhone.clear();
		frmReserveInputTitularMobile.clear();
		uncheckPolitics();
		logger.info("Ending clearForm()");
	}
	
	public void clearFormWithInsurance() {
		waitForContentToBeReady();
		logger.info("Starting clearFormWithInsurance()");
		wait.until(ExpectedConditions.visibilityOf(frmReserveSelectAdultTitle));
		Select adultTitle = new Select(frmReserveSelectAdultTitle);
		adultTitle.deselectAll();
		frmReserveInputAdultoName.clear();
		frmReserveInputLastName.clear();
		Select birthdayDay = new Select(frmReserveSelectDirthdateDay);
		birthdayDay.deselectAll();
		Select birthdayMonth = new Select(frmReserveSelectDirthdateMonth);
		birthdayMonth.deselectAll();
		Select birthdayYear = new Select(frmReserveSelectDirthdateYear);
		birthdayYear.deselectAll();
		frmReserveInputEmail.clear();
		frmReserveInputConMail.clear();
		frmReserveInputPhone.clear();
		frmReserveInputMobile.clear();
		uncheckPolitics();
		logger.info("Ending clearForm()");
	}
	
	public void clickOnContinue() {
		frmReserveButtonContinue.click();
	}
	
	public void waitForContentToBeReady(){
		WaitFor.waitForJSandJQueryToLoad(driver);
	}
	
	
	//------------ Verifys ----------------------
	public void verifySummaryHotelNameToBe() {
		//codigo para verificar que el nombre del Hotel es correcto
		//Assert.assertEquals(descriptionName.getText(), "");
	}
	
	public void checkPolitics() {
		if(!frmReserveCheckBoxPolicies.isSelected()) {
			logger.warn("checkPolitics() - checkbox was selected!");
			frmReserveCheckBoxPolicies.click();
		}
	}
	
	public void uncheckPolitics() {
		if(frmReserveCheckBoxPolicies.isSelected()) {
			frmReserveCheckBoxPolicies.click();
		}
	}
}
