package pages.pt.paquetes;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import pages.pt.paquetes.components.PRLWidget;

public class PackageRoomListPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(PackageRoomListPage.class);
	
	public PRLWidget widget;
	
	public PackageRoomListPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		widget = new PRLWidget(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/paquetes/"));
		waitForContentToBeReady();
	}
	
	//+++++++++PageFactory Elements+++++++++++++++++
	@FindBy(how=How.CSS, using=".hotel-rooms-table .room-table[data-has-price='true']")
	private List<WebElement> allSearchResults;
	
	@FindBy(how=How.CSS, using=".hotel-rooms-table .room-table .room-table-actions .btn.btn-primary")
	private WebElement roomButton;
	
	By byLoaderOverlayPage = By.cssSelector(".loader-overlay");
	By byLoaderButton = By.cssSelector(".spinner");
	
	//Los lementos que se cargan al inicio junto con la pagina se declaran con @FindBy
	//Los elementos que se cargan despues, por cuestion de AJAX o por una SPA se declararan
	//usando By elements

	//---------NON PageFactory Elements-------------
	//private By BYrate = By.cssSelector("");
	//private By BYroomButton = By.cssSelector(".hotel-rooms-table .room-table .room-table-actions .btn.btn-primary");
	
	public void selectFirstRoom() {
		logger.info("Starting selectFirstRoom()");
		logger.trace("Cantidad de bloques (rooms) en la lista de resultados: "+allSearchResults.size());
		
		//Cambié el scroll por un Action, hasta ahora parece que esta funcionando bien ;)...
		//Actions actions = new Actions(driver);
		//(actions.moveToElement(roomButton).perform();
		//Workaround for firefox
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", roomButton);
		//-----------------------------------------------------
		logger.trace("Before wait");
		roomButton.click();
		logger.info("Ending SelectRoomDefault()");
	}
	
	//En construccion!
	public void SelectRoom(int itemList){
		//Falta implementar esta funcion
	}
	
	//++++++++++++++++++++++++++++ Wait for Overlay ++++++++++++++++++++++++++++++++++++++++++++
	public void waitForOverlay() {
		//Esperar a que se quite el overlay
		//wait.until(WaitFor.attributeValue(loaderOverlayPage, "style", "display: none;"));
		wait.until(ExpectedConditions.attributeContains(byLoaderOverlayPage, "style", "display: none;"));
	}
	
	public void waitForLoaderButtons() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byLoaderButton));
	}
	
	public void waitForContentToBeReady() {
		waitForOverlay();
		waitForLoaderButtons();
		logger.info("waitForContentToBeReady -> Content Loaded");
	}
}
