package pages.tb;

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
	private String currentURL;
	
	PayMethodPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,FWConfig.WAIT_TB);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, FWConfig.WAITPF_TB),this);
	}
	
	//-----FormElements-----
	@FindBy(how=How.ID, id="cardNumber1")
	WebElement Input_cardNumber1;
	
	@FindBy(how=How.ID, id="cardMonthExpired1")
	WebElement Select_cardMonthExpired1;
	
	@FindBy(how=How.ID, id="cardYearExpired1")
	WebElement Select_cardYearExpired1;
	
	@FindBy(how=How.ID, id="cardHolderName1")
	WebElement Input_cardHolderName1;
	
	@FindBy(how=How.ID, id="cardSecurityCode1")
	WebElement Input_cardSecurityCode1;
	
	@FindBy(how=How.ID, id="NationalIdCard1")
	WebElement Input_nationalIdCard1;
	
	@FindBy(how=How.ID, id="cCountry1")
	WebElement Select_cardCountry1;
	
	@FindBy(how=How.CSS, css=".item-accordion.btn-accordion")
	WebElement Accordion_codePromotions;
	
	@FindBy(how=How.ID, id="promocodeInput")
	WebElement Input_promocode;
	
	@FindBy(how=How.ID, id="validatePromocode")
	WebElement Button_validatePromocode;
	
	//FinalButton
	@FindBy(how=How.ID, id="validatePayForms")
	WebElement Button_validatePayForms;
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("forma-pago"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("forma-pago"));
	}
	
	public void fillCreditForm(VOCreditCard voCreditCard) {
		Input_cardNumber1.sendKeys(Long.toString(voCreditCard.getCardNumber()));
		Select month = new Select (Select_cardMonthExpired1);
		month.selectByVisibleText(voCreditCard.getMonth());
		Select year = new Select(Select_cardYearExpired1);
		year.selectByVisibleText(voCreditCard.getYear());
		Input_cardHolderName1.sendKeys(voCreditCard.getHolderName());
		Input_cardSecurityCode1.sendKeys(Integer.toString(voCreditCard.getCCV()));
		Select country = new Select(Select_cardCountry1);
		country.selectByValue(voCreditCard.getCountry());
		Input_nationalIdCard1.sendKeys(Integer.toString(voCreditCard.getNationalId()));
	}
	
	public void clearCreditForm() {
		Input_cardNumber1.clear();
		Select month = new Select (Select_cardMonthExpired1);
		month.deselectAll();
		Select year = new Select(Select_cardYearExpired1);
		year.deselectAll();
		Input_cardHolderName1.clear();
		Input_cardSecurityCode1.clear();
		Select country = new Select(Select_cardCountry1);
		country.deselectAll();
		Input_nationalIdCard1.clear();
	}
	
	public void clickOnCompleteReservation() {
		Button_validatePayForms.click();
	}
	
	public void enterCodePromotions(String code) {
		Accordion_codePromotions.click();
		Input_promocode.sendKeys(code);
		Button_validatePromocode.click();
		
		//Falta validar que el codigoProm sea valido
		Assert.assertEquals("current", "expected");
	}
}
