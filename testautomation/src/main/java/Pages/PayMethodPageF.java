package Pages;

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

import DataObjects.DOCreditCard;

public class PayMethodPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	private String currentURL;
	
	PayMethodPageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30),this);
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
	
	@FindBy(how=How.ID, id="cCountry1")
	WebElement Select_cardCountry1;
	
	@FindBy(how=How.ID, id="cZipCode1")
	WebElement Input_cardZipCode1;
	
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
	
	public void ClearFillandContinue(){
		clearCreditForm();
		FillCreditFormDefaultData();
		clickOnCompleteReservation();
	}
	
	public void FillCreditFormDefaultData() {
		Input_cardNumber1.sendKeys("1111111111114444");
		Select month = new Select (Select_cardMonthExpired1);
		month.selectByVisibleText("02");
		Select year = new Select(Select_cardYearExpired1);
		year.selectByVisibleText("2021");
		Input_cardHolderName1.sendKeys("Virginia Chavez");
		Input_cardSecurityCode1.sendKeys("014");
		Select country = new Select(Select_cardCountry1);
		country.selectByValue("MX");
		Input_cardZipCode1.sendKeys("44777");
	}
	
	public void FillCreditForm(DOCreditCard DOCard) {
		Input_cardNumber1.sendKeys(Long.toString(DOCard.getCardNumber()));
		Select month = new Select (Select_cardMonthExpired1);
		month.selectByVisibleText(DOCard.getMonth());
		Select year = new Select(Select_cardYearExpired1);
		year.selectByVisibleText(DOCard.getYear());
		Input_cardHolderName1.sendKeys(DOCard.getHolderName());
		Input_cardSecurityCode1.sendKeys(Integer.toString(DOCard.getCCV()));
		Select country = new Select(Select_cardCountry1);
		country.selectByValue(DOCard.getCountry());
		Input_cardZipCode1.sendKeys(Integer.toString(DOCard.getCP()));
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
		Input_cardZipCode1.clear();
	}
	
	public void clickOnCompleteReservation() {
		Button_validatePayForms.click();
	}
	
	public boolean pay1or2cards(int opc) {
		if(opc==1){
			
			
			return true;
		}else if(opc==2){
			
			
			return true;
		}else {
			return false; 
		}
		
	}
	
	public void enterCodePromotions(String code) {
		Accordion_codePromotions.click();
		Input_promocode.sendKeys(code);
		Button_validatePromocode.click();
		
		//Falta validar que el codigoProm sea valido
		Assert.assertEquals("current", "expected");
	}
}
