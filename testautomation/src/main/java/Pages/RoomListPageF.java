package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.BasicUtils;

public class RoomListPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	
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
	
	//Cuando se complica la seleccion de una elemento por cuestiones de carga de paginas
	//de tipo SPA o que usan alguna funcion AJAX, se utilizara BY junto con un ExplicitWait
	//By elements
	//---------NON PageFactory Elements-------------
	//private By BYallSearchResults = By.cssSelector(".hotel-rooms-table .room-table");
	//private By BYroomButton = By.cssSelector(".hotel-rooms-table .room-table .room-table-actions .btn.btn-primary");
	
	public void SelectRoomDefault() {
		System.out.println("RoomListF - Tamaño de la lista antes: "+allSearchResults.size());
		
		//Makes a simple scrooll in order to see the buttons, is not relevant!!!
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,700)");
		//-----------------------------------------------------
		//Cambié el scroll por un Action, hasta ahora parece que esta funcionando bien ;)...
		Actions actions = new Actions(driver);
		actions.moveToElement(roomButton).perform();
		//-----------------------------------------------------
		
		wait.until( ExpectedConditions.elementToBeClickable(roomButton) );
		wait.until( ExpectedConditions.visibilityOfElementLocated(BasicUtils.toByVal(roomButton)) );
		
		System.out.println("RoomListF - Tamaño de la lista despues: "+allSearchResults.size());
		
		//Falla al momento de querer dar click porque aun se encuentra el spiner encima del elemento
		roomButton.click();
	}
	
	//NOT READY! Necesitamos un trato especial con los botones
	//El PageFactory y AjaxElemetLocatorFactory nos ayuda a esperar a que
	//los elementos estaticos esten listos pero no podemos (ni debemos) crear
	//un webelement para cada boton, esos los tenemos que crear en esta funcion
	public void SelectRoom(int itemList) throws InterruptedException {
		//Aun no funciona correctamente, necesita un sleep para funcionar
		//wait.until( ExpectedConditions.presenceOfElementLocated(BYallSearchResults));
		//List<WebElement> allSearchResults = driver.findElements(BYallSearchResults);
		//System.out.println("->>>>>>>>Tamaño de la lista: "+allSearchResults.size());
		
		//allSearchResults.get(itemList-1).findElement(BYroomButton).click();
		
		System.out.println("->>>>>>>>Tamaño de la lista: "+allSearchResults.size());
	}
}
