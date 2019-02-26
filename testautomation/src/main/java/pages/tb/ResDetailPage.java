package pages.tb;

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
import valueobjects.VOClient;

public class ResDetailPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private String currentURL;
	
	public ResDetailPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,FWConfig.WAIT_TB);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, FWConfig.WAITPF_TB),this);
	}
	
	//-----FormElements-----
	@FindBy(how=How.ID, using="firstName")
	WebElement Input_firsName;
	
	@FindBy(how=How.ID, using="lastName")
	WebElement Input_lastName;
	
	@FindBy(how=How.ID, using="eMail")
	WebElement Input_eMail;
	
	@FindBy(how=How.ID, using="conMail")
	WebElement Input_conMail;
	
	@FindBy(how=How.ID, using="lada")
	WebElement Input_lada;
	
	@FindBy(how=How.ID, using="phone")
	WebElement Input_phone;
	
	@FindBy(how=How.ID, using="mobile")
	WebElement Input_mobile;
	
	@FindBy(how=How.ID, using="NationalIdCard")
	WebElement Input_nationalIdCard;
	
	@FindBy(how=How.ID, using="chkConfirm")
	WebElement CheckBox_chkConfirm;
	
	@FindBy(how=How.CSS, using="#frmReserve a.btn.btn-success.btn-form")
	WebElement Button_continue;
	
	//-----Information-----
	@FindBy(how=How.CSS, using=".aside .card-body .summary-description-name h5")
	WebElement descriptionName;
	
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
		Input_firsName.sendKeys(voClient.getName());
		Input_lastName.sendKeys(voClient.getLastName());
		Input_eMail.sendKeys(voClient.getEmail());
		Input_conMail.sendKeys(voClient.getEmail());
		Input_lada.sendKeys(Integer.toString(voClient.getLada()));
		Input_phone.sendKeys(Integer.toString(voClient.getPhone()));
		Input_mobile.sendKeys(Long.toString(voClient.getCellphone()));
		Input_nationalIdCard.sendKeys(Integer.toString(voClient.getNationalId()));
		checkPolitics();
	}
	
	public void clearForm() {
		Input_firsName.clear();
		Input_lastName.clear();
		Input_eMail.clear();
		Input_conMail.clear();
		Input_lada.clear();
		Input_phone.clear();
		Input_mobile.clear();
		Input_nationalIdCard.clear();
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
