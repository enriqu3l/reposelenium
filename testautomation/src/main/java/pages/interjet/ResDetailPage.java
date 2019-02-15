package pages.interjet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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
import helpers.WaitFor;
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
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_INTERJET);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_INTERJET),this);
		
		//Esperar a que la url sea la correcta      reservar/detalles-reservacion
		wait.until(ExpectedConditions.urlContains("/reservar/detalles-reservacion"));
	}
	
	@FindBy(how=How.CSS, css="#main #divComplete .banres .merchOption-contOpt a")
	WebElement buttonContinue;
	
	
	//--------- Summary Info ---------
	@FindBy(how=How.CSS, css="#right #summary #mainItem h3")
	WebElement summaryHotelName;
	
	@FindBy(how=How.CSS, css="#right #summary #mainItem .itemprice")
	WebElement summaryTotalAmount;
	
	By byRemoveInsuranceAddOnButton = By.cssSelector("#right #summary #insurance11AddOnDiv .lnkDel");
	By byRemoveTransferAddOnButton = By.cssSelector("#right #summary #transferAddOnDiv .lnkDel");
	By byRemoveCarAddOnButton = By.cssSelector("#right #summary #carAddOnDiv .lnkDel");
	
	public void clickOnContinue() {
		waitForContentToBeReady();
		buttonContinue.click();
	}
	
	public void removeAddOns() {
		waitForContentToBeReady();
		if(BasicUtils.existsElement(driver, byRemoveInsuranceAddOnButton)) {
			driver.findElement(byRemoveInsuranceAddOnButton).click();
			logger.info("Removing Insurance AddOn");
		}
		if(BasicUtils.existsElement(driver, byRemoveTransferAddOnButton)) {
			driver.findElement(byRemoveTransferAddOnButton).click();
			logger.info("Removing Transfer AddOn");
		}
		if(BasicUtils.existsElement(driver, byRemoveCarAddOnButton)) {
			driver.findElement(byRemoveCarAddOnButton).click();
			logger.info("Removing Car AddOn");
		}
	}
	
	public void checkCurrentURLPage() {
		wait.until(ExpectedConditions.urlContains("detalles-reservacion"));
		currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("detalles-reservacion"));
	}
	
	//------------ Verifys ----------------------
	public void verifySummaryHotelNameToBe(String hotelName) {
		//codigo para verificar que el nombre del Hotel es correcto
		//Assert.assertEquals(descriptionName.getText(), "");
	}
	
	private void printSummaryInfo() {
		logger.info("Summary Hotel Name: " + summaryHotelName.getText());
		logger.info("Summary Total Amount: "+ summaryTotalAmount.getText());
	}
	
	public void waitForContentToBeReady(){
		WaitFor.waitForJSandJQueryToLoad(driver);
	}
}
