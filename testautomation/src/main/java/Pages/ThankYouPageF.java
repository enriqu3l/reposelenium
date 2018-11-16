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

import FrameworkConfig.GeneralConfig;
import Helper.DOManager;
import Utility.BasicUtils;

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
	@FindBy(how=How.CSS, css="#content h1")
	WebElement message;
	
	@FindBy(how=How.CSS, css="#content .reservation-message .text-brand")
	WebElement locator;
	
	@FindBy(how=How.CSS, css="#content .innerModule p a.but")
	WebElement Button_seeReservation;
	
	public void verifyCheckOutCompleted(){
		checkCurrentURLPage();
		
		if(message.getText().contains("Gracias")) {
			System.out.println("Se ha generado el localizador: "+ locator.getText());
		}
		
		BasicUtils.ScreenShot(driver, locator.getText(), GeneralConfig.SCREENSHOOT_PATH);
	}
	
	public void checkCurrentURLPage() {
		//Este wait no es necesario dado que estoy usando AjaxElementFactory
		//wait.until(ExpectedConditions.urlContains("completar-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("completar-reservacion"));
	}
	
	public void seeReservationButtonClick() {		
		Button_seeReservation.click();
	}
	
	//Aun no la usaré, hasta hacer la modificacion para que el archivo LocatorsGenerated.xlsx
	//sea alojado directamente en C en vez de alojarlo dentro del proyecto
	public void SaveLocator() {
		//Assert.assertTrue(locator.getText());
		
		System.out.println("ThankYouPage -> Localizador: "+locator.getText());
		DOManager.saveLocator(locator.getText());
		
		//Verificar que se halla guardado correctamente
		//Assert.assertEquals(locator.getText(), getLocator(locator.getText()));
	}
}
