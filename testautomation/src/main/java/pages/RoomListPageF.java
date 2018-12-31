package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoomListPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(RoomListPageF.class);
	
	public RoomListPageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30),this);
	}
	
	//+++++++++PageFactory Elements+++++++++++++++++
	@FindBy(how=How.CSS, css=".hotel-rooms-table .room-table")
	private List<WebElement> allSearchResults;
	
	@FindBy(how=How.CSS, css=".hotel-rooms-table .room-table .room-table-actions .btn.btn-primary")
	private WebElement roomButton;
	
	By loaderOverlayPage = By.cssSelector(".loader-overlay.ng-trigger");
	
	//Los lementos que se cargan al inicio junto con la pagina se declaran con @FindBy
	//Los elementos que se cargan despues, por cuestion de AJAX o por una SPA se declararan
	//usando By elements

	//---------NON PageFactory Elements-------------
	//private By BYrate = By.cssSelector("");
	//private By BYroomButton = By.cssSelector(".hotel-rooms-table .room-table .room-table-actions .btn.btn-primary");
	
	public void SelectRoomDefault() {
		logger.info("Starting SelectRoomDefault()");
		//Esperar a que se quite el overlay, falla en Test porque no es SPA
		wait.until(ExpectedConditions.attributeContains(loaderOverlayPage, "style", "display: none; opacity: 0;"));
		
		logger.trace("Tama�o de la lista de resultados: "+allSearchResults.size());
		
		//Cambi� el scroll por un Action, hasta ahora parece que esta funcionando bien ;)...
		Actions actions = new Actions(driver);
		actions.moveToElement(roomButton).perform();
		//-----------------------------------------------------
		
		wait.until( ExpectedConditions.elementToBeClickable(roomButton) );
		wait.until( ExpectedConditions.visibilityOf(roomButton));
		
		roomButton.click();
		logger.info("Ending SelectRoomDefault()");
	}
	
	//NOT READY! Necesitamos un trato especial con los botones
	//El PageFactory y AjaxElemetLocatorFactory nos ayuda a esperar a que
	//los elementos estaticos esten listos pero no podemos (ni debemos) crear
	//un webelement para cada boton, esos los tenemos que crear dinamicamente en esta funcion
	public void SelectRoom(int itemList) throws InterruptedException {
		//Falta implementar esta funcion
	}
}