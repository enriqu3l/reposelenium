package pages.tb;

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

import utility.FWUtils;

public class ResDetailPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	private String currentURL;
	
	public ResDetailPageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
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
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("detalles-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("detalles-reservacion"));
	}
	
	public void clearFillandContinue() {
		clearForm();
		fillFormDefaultData();
		clickOnContinue();
	}
	
	public void fillFormDefaultData() {
		wait.until(ExpectedConditions.presenceOfElementLocated(FWUtils.toByVal(Input_firsName)));
		Input_firsName.sendKeys("Enrique");
		Input_lastName.sendKeys("Carrillo");
		Input_eMail.sendKeys("enriquecarrillo119999@gmail.com");
		Input_conMail.sendKeys("enriquecarrillo119999@gmail.com");
		Input_lada.sendKeys("33");
		Input_phone.sendKeys("33443344");
		Input_mobile.sendKeys("3344334433");
		checkPolitics();
	}
	
	public void clearForm() {
		wait.until(ExpectedConditions.presenceOfElementLocated(FWUtils.toByVal(Input_firsName)));
		Input_firsName.clear();
		Input_lastName.clear();
		Input_eMail.clear();
		Input_conMail.clear();
		Input_lada.clear();
		Input_phone.clear();
		Input_mobile.clear();
		uncheckPolitics();
	}
	
	public void checkPolitics() {
		if(!CheckBox_chkConfirm.isSelected()) {
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
