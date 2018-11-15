package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Helper.DOManager;

public class ThankYouPageF {
	WebDriverWait wait;
	WebDriver driver;
	String currentURL;
	
	public ThankYouPageF(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
	}
	
	//---------Elements-------
	@FindBy(how=How.CSS, css="#content .reservation-message .text-brand")
	WebElement locator;
	
	@FindBy(how=How.CSS, css="#content .innerModule p a.but")
	WebElement Button_seeReservation;
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("completar-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("completar-reservacion"));
	}
	
	public void SaveLocator() {
		//Colorcar aqui el codigo para guardar el número de localizador que se ha generado
		//Assert.assertTrue(locator.getText());
		
		DOManager.saveLocator(locator.getText());
		
		//Verificar que se halla guardado correctamente
		//Assert.assertEquals(locator.getText(), getLocator(locator.getText()));
	}
}
